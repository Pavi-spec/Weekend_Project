package com.weekend.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/welcome/message",
                "sling.servlet.methods=" + HttpConstants.METHOD_GET
        }
)

public class GetServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/plain");

        response.getWriter().write("Welcome to AEM GET Servlet");
    }
}