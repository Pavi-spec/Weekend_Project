package com.weekend.core.servlets;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.resourceTypes=weekend/components/productcatalog",
                "sling.servlet.extensions=json",
                "sling.servlet.methods=GET"
        }
)
public class ProductCatalogServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");

        Resource resource = request.getResource();
        Resource productsNode = resource.getChild("products");

        JsonArray array = new JsonArray();

        if (productsNode != null) {

            for (Resource item : productsNode.getChildren()) {

                JsonObject obj = new JsonObject();

                obj.addProperty("productName",
                        item.getValueMap().get("productName", ""));

                obj.addProperty("price",
                        item.getValueMap().get("price", ""));

                obj.addProperty("category",
                        item.getValueMap().get("category", ""));

                array.add(obj);
            }
        }

        JsonObject result = new JsonObject();
        result.add("products", array);

        response.getWriter().write(result.toString());
    }
}