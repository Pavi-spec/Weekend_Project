package com.weekend.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import java.io.IOException;

/**
 * Employee Registration Servlet
 */

@Component(
        service = Servlet.class,
        property = {
                "sling.servlet.paths=/bin/employee/register",
                "sling.servlet.methods=" + HttpConstants.METHOD_POST
        }
)

@ServiceDescription("Employee Registration Servlet")

public class EmployeeRegisterServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response)
            throws ServletException, IOException {

        // Set response type
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Get parameters
        String employeeName = request.getParameter("employeeName");
        String department = request.getParameter("department");

        // Validation
        if (employeeName == null || employeeName.trim().isEmpty()
                || department == null || department.trim().isEmpty()) {

            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);

            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"employeeName and department are required.\"}"
            );

            return;
        }

        // Success Response
        response.getWriter().write(String.format(
                "{\"status\":\"success\",\"employee\":{\"employeeName\":\"%s\",\"department\":\"%s\"}}",
                employeeName,
                department
        ));
    }
}