package com.weekend.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.request.RequestParameter;

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
        resourceTypes = "weekend/components/custombanner",
        methods = HttpConstants.METHOD_DELETE,
        selectors = "delete",
        extensions = "json"
)

public class CustomBannerDeleteServlet
        extends SlingAllMethodsServlet {

    private static final Logger log =
            LoggerFactory.getLogger(
                    CustomBannerDeleteServlet.class
            );

    @Override
    protected void doDelete(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        log.info(" Custom Banner DELETE Servlet Started");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            // Current Resource
            Resource resource =
                    request.getResource();

            log.info(" Resource Path: {}",
                    resource.getPath());

            // Resource Resolver
            ResourceResolver resolver =
                    request.getResourceResolver();

            // Editable Properties
            ModifiableValueMap map =
                    resource.adaptTo(
                            ModifiableValueMap.class
                    );

            if (map == null) {

                log.error(" Resource not modifiable");

                response.setStatus(
                        SlingHttpServletResponse
                                .SC_INTERNAL_SERVER_ERROR
                );

                response.getWriter().write(
                        "{"
                                + "\"status\":\"error\","
                                + "\"message\":\"Resource not modifiable\""
                                + "}"
                );

                return;
            }

            // Read field parameter

            RequestParameter fieldParam =
                    request.getRequestParameter("field");

            if (fieldParam == null) {

                log.error(" Field parameter missing");

                response.setStatus(
                        SlingHttpServletResponse
                                .SC_BAD_REQUEST
                );

                response.getWriter().write(
                        "{"
                                + "\"status\":\"error\","
                                + "\"message\":\"Field parameter required\""
                                + "}"
                );

                return;
            }

            // Field Name
            String field =
                    fieldParam.getString();

            log.info("🗑 Field To Delete: {}",
                    field);

            // Check Property Exists

            if (map.containsKey(field)) {

                // Delete Property
                map.remove(field);

                // Save Changes
                resolver.commit();

                log.info(" Field Deleted Successfully");

                response.getWriter().write(
                        "{"
                                + "\"status\":\"success\","
                                + "\"deletedField\":\""
                                + field +
                                "\""
                                + "}"
                );

            } else {

                log.error(" Field Not Found");

                response.setStatus(
                        SlingHttpServletResponse
                                .SC_NOT_FOUND
                );

                response.getWriter().write(
                        "{"
                                + "\"status\":\"error\","
                                + "\"message\":\"Field not found\""
                                + "}"
                );
            }

        } catch (Exception e) {

            log.error(
                    " Exception in CustomBannerDeleteServlet",
                    e
            );

            response.setStatus(
                    SlingHttpServletResponse
                            .SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{"
                            + "\"status\":\"error\","
                            + "\"message\":\"Internal Server Error\""
                            + "}"
            );
        }

        log.info("🏁 Custom Banner DELETE Servlet Completed");
    }
}