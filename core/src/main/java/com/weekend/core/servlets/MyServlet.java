package com.weekend.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.resourceTypes=weekend/components/page",
                "sling.servlet.selectors=myServlet",
                "sling.servlet.extensions=json",
                "sling.servlet.methods=POST"
        }
)

public class MyServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(MyServlet.class);

    @Override
    protected void doPost(
            final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp)
            throws ServletException, IOException {

        LOGGER.info("POST Servlet Started");

        resp.setContentType("application/json");

        resp.getWriter().write(
                "{\"message\":\"Hello Pavi POST Servlet Working\"}"
        );

        LOGGER.info("POST Servlet Ended");
    }
}