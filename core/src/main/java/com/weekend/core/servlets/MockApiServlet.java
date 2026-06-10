package com.weekend.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.http.client.fluent.Request;
import org.osgi.service.component.annotations.Component;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletPaths;

@Component(service = Servlet.class)
@SlingServletPaths("/bin/mockapi")
public class MockApiServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        String apiUrl =
                "https://jsonplaceholder.typicode.com/users";

        String json =
                Request.Get(apiUrl)
                        .execute()
                        .returnContent()
                        .asString();

        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}