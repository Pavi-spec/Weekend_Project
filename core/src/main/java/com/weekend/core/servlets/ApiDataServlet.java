package com.weekend.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/users",
                "sling.servlet.methods=GET"
        }
)
public class ApiDataServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws IOException {

        URL url = new URL("https://jsonplaceholder.typicode.com/users");

        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader reader =
                new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));

        StringBuilder json = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        reader.close();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(json.toString());
    }
}