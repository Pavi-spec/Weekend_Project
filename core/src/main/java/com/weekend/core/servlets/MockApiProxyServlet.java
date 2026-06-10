package com.weekend.core.servlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.resourceTypes=weekend/components/mockapiproxy",
                "sling.servlet.selectors=products",
                "sling.servlet.extensions=json",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        }
)
public class MockApiProxyServlet extends SlingSafeMethodsServlet {

    private static final String DEFAULT_API =
            "https://fakestoreapi.com/products";

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");

        try {

            // 1. Read component properties (optional from dialog)
            ValueMap properties = request.getResource().getValueMap();

            String apiEndpoint = properties.get("apiEndpoint", DEFAULT_API);
            int limit = properties.get("limit", 5);

            // 2. Call external API
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest apiRequest = HttpRequest.newBuilder()
                    .uri(URI.create(apiEndpoint))
                    .GET()
                    .build();

            HttpResponse<String> apiResponse =
                    client.send(apiRequest, HttpResponse.BodyHandlers.ofString());

            // 3. Parse JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(apiResponse.body());

            ArrayNode limitedArray = mapper.createArrayNode();

            int count = 0;

            if (root.isArray()) {
                for (JsonNode node : root) {
                    if (count >= limit) break;
                    limitedArray.add(node);
                    count++;
                }
            }

            // 4. Return response
            response.getWriter().write(limitedArray.toString());

        } catch (Exception e) {
            response.setStatus(500);
            response.getWriter().write("{\"error\":\"API Error: " + e.getMessage() + "\"}");
        }
    }
}