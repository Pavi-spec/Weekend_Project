package com.weekend.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

@Component(service = Servlet.class)

@SlingServletResourceTypes(
        resourceTypes = "weekend/components/product",
        methods = HttpConstants.METHOD_GET,
        selectors = "product",
        extensions = "json"
)

public class ProductGetServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log =
            LoggerFactory.getLogger(ProductGetServlet.class);

    @Override
    protected void doGet(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        log.info("Product GET Servlet Started");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            // Get current resource
            Resource resource = request.getResource();

            log.info("Resource Path: {}", resource.getPath());

            // Get properties of current resource
            ValueMap properties = resource.getValueMap();

            String productName =
                    properties.get("productName", "");

            String price =
                    properties.get("price", "");

            String description =
                    properties.get("description", "");

            log.info("Product Name: {}", productName);
            log.info("Price: {}", price);
            log.info("Description: {}", description);


            // Read query parameter
            String fields = request.getParameter("fields");

            log.info("Requested fields: {}", fields);


            // If query parameter asks for productName and price
            if ("productName,price".equals(fields)) {

                String jsonResponse = "{"
                        + "\"productName\":\"" + productName + "\","
                        + "\"price\":\"" + price + "\""
                        + "}";

                response.getWriter().write(jsonResponse);

                log.info("Returned productName and price only");

                return;
            }


            // Default response - return all fields
            String jsonResponse = "{"
                    + "\"productName\":\"" + productName + "\","
                    + "\"price\":\"" + price + "\","
                    + "\"description\":\"" + description + "\""
                    + "}";

            response.getWriter().write(jsonResponse);

            log.info("All product data sent successfully");

        } catch (Exception e) {

            log.error("Exception in Product GET Servlet", e);

            response.setStatus(
                    SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"Internal Server Error\"}"
            );
        }

        log.info("Product GET Servlet Completed");
    }
}