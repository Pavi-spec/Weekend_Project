package com.weekend.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

@Model(adaptables = SlingHttpServletRequest.class)
public class MockApiModel {

    @SlingObject
    private SlingHttpServletRequest request;

    private List<Map<String, Object>> products = new ArrayList<>();

    public List<Map<String, Object>> getProducts() {
        return products;
    }

    @PostConstruct
    protected void init() {

        try {

            // Servlet URL (your existing setup)
            String resourcePath = request.getResource().getPath();

            String servletUrl =
                    "http://localhost:4502"
                            + resourcePath
                            + ".products.json";

            URL url = new URL(servletUrl);

            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            String cookie = request.getHeader("Cookie");

            if (cookie != null) {
                connection.setRequestProperty("Cookie", cookie);
            }

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));

            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            reader.close();

            // Convert JSON → List of Map (SAFE for HTL)
            ObjectMapper mapper = new ObjectMapper();

            List<Map<String, Object>> list =
                    mapper.readValue(result.toString(), List.class);

            products.addAll(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}