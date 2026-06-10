package com.weekend.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(service = Servlet.class)
@SlingServletResourceTypes(
        resourceTypes = "weekend/components/employee",
        methods = HttpConstants.METHOD_POST,
        selectors = "save",
        extensions = "json"
)
public class EmployeePostServlet extends SlingAllMethodsServlet {

    private static final Logger log =
            LoggerFactory.getLogger(EmployeePostServlet.class);

    @Override
    protected void doPost(SlingHttpServletRequest request,
                          SlingHttpServletResponse response)
            throws ServletException, IOException {

        log.info(" Employee Servlet Started");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            // Get current resource
            Resource resource = request.getResource();
            log.info("Resource Path: {}", resource.getPath());

            // Get ResourceResolver
            ResourceResolver resolver = request.getResourceResolver();

            // Read parameters
            String name = request.getParameter("name");
            String role = request.getParameter("role");

            log.info(" Name: {}", name);
            log.info(" Role: {}", role);

            // Validation
            if (name == null || name.trim().isEmpty()
                    || role == null || role.trim().isEmpty()) {

                log.error("Name or Role is missing");

                response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(
                        "{\"status\":\"error\",\"message\":\"Name and Role are required\"}"
                );
                return;
            }

            // Adapt resource to modifiable map
            ModifiableValueMap map = resource.adaptTo(ModifiableValueMap.class);

            if (map == null) {
                log.error(" Resource is not modifiable");

                response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(
                        "{\"status\":\"error\",\"message\":\"Resource not modifiable\"}"
                );
                return;
            }

            // Save values to JCR
            map.put("name", name);
            map.put("role", role);

            // Commit changes
            resolver.commit();

            log.info(" Data saved successfully in JCR");

            // Success response
            response.getWriter().write(
                    "{ \"status\":\"success\", " +
                            "\"name\":\"" + name + "\", " +
                            "\"role\":\"" + role + "\" }"
            );

        } catch (Exception e) {

            log.error(" Exception in Employee Servlet", e);

            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"Internal Server Error\"}"
            );
        }

        log.info(" Employee Servlet Ended");
    }
}