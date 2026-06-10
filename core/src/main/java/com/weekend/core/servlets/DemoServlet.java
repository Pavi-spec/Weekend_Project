package com.weekend.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)

@SlingServletResourceTypes(
        resourceTypes = "weekend/components/page",
        selectors = "demoServlet",
        extensions = "json",
        methods = HttpConstants.METHOD_POST
)

public class DemoServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    // LOGGER OBJECT

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DemoServlet.class);

    @Override
    protected void doPost(
            final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp)
            throws ServletException, IOException {

        LOGGER.debug("Demo Servlet Started !!");

        resp.setContentType("application/json");

        resp.getWriter().write(
                "{\"message\":\"Demo Servlet Executed Successfully\"}"
        );

        LOGGER.debug("Demo Servlet Ended !!");
    }
}