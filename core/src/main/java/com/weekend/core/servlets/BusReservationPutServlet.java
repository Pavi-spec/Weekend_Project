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
        resourceTypes = "weekend/components/busreservation",
        methods = HttpConstants.METHOD_PUT,
        selectors = "update",
        extensions = "json"
)

public class BusReservationPutServlet extends SlingAllMethodsServlet {

    private static final Logger log =
            LoggerFactory.getLogger(BusReservationPutServlet.class);

    @Override
    protected void doPut(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws ServletException, IOException {

        log.info(" Bus Reservation PUT Servlet Started");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            // Current Resource
            Resource resource = request.getResource();

            log.info(" Resource Path: {}", resource.getPath());

            // Resource Resolver
            ResourceResolver resolver =
                    request.getResourceResolver();

            // Editable JCR Properties
            ModifiableValueMap map =
                    resource.adaptTo(ModifiableValueMap.class);

            if (map == null) {

                log.error(" Resource is not modifiable");

                response.setStatus(
                        SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR
                );

                response.getWriter().write(
                        "{\"status\":\"error\",\"message\":\"Resource not modifiable\"}"
                );

                return;
            }

            // Request Parameters

            RequestParameter titleParam =
                    request.getRequestParameter("title");

            RequestParameter descriptionParam =
                    request.getRequestParameter("description");

            RequestParameter imageParam =
                    request.getRequestParameter("image");

            RequestParameter fromParam =
                    request.getRequestParameter("from");

            RequestParameter toParam =
                    request.getRequestParameter("to");

            RequestParameter dateParam =
                    request.getRequestParameter("date");

            RequestParameter priceParam =
                    request.getRequestParameter("price");

            RequestParameter seatsParam =
                    request.getRequestParameter("seats");

            RequestParameter acParam =
                    request.getRequestParameter("ac");

            RequestParameter buttonTextParam =
                    request.getRequestParameter("buttonText");

            RequestParameter buttonLinkParam =
                    request.getRequestParameter("buttonLink");

            // Update ONLY Sent Fields

            if (titleParam != null) {

                String title = titleParam.getString();

                map.put("title", title);

                log.info(" Updated Title: {}", title);
            }

            if (descriptionParam != null) {

                String description =
                        descriptionParam.getString();

                map.put("description", description);

                log.info("Updated Description");
            }

            if (imageParam != null) {

                String image =
                        imageParam.getString();

                map.put("image", image);

                log.info(" Updated Image");
            }

            if (fromParam != null) {

                String from =
                        fromParam.getString();

                map.put("from", from);

                log.info(" Updated From: {}", from);
            }

            if (toParam != null) {

                String to =
                        toParam.getString();

                map.put("to", to);

                log.info(" Updated To: {}", to);
            }

            if (dateParam != null) {

                String date =
                        dateParam.getString();

                map.put("date", date);

                log.info("Updated Date: {}", date);
            }

            if (priceParam != null) {

                String price =
                        priceParam.getString();

                map.put("price", price);

                log.info("Updated Price: {}", price);
            }

            if (seatsParam != null) {

                String seats =
                        seatsParam.getString();

                map.put("seats", seats);

                log.info(" Updated Seats: {}", seats);
            }

            if (acParam != null) {

                String ac =
                        acParam.getString();

                map.put("ac", ac);

                log.info("❄ Updated AC: {}", ac);
            }

            if (buttonTextParam != null) {

                String buttonText =
                        buttonTextParam.getString();

                map.put("buttonText", buttonText);

                log.info(" Updated Button Text");
            }

            if (buttonLinkParam != null) {

                String buttonLink =
                        buttonLinkParam.getString();

                map.put("buttonLink", buttonLink);

                log.info("🔗 Updated Button Link");
            }

            // Save Changes
            resolver.commit();

            log.info("Bus Reservation Updated Successfully");

            // JSON Response
            response.getWriter().write(
                    "{"
                            + "\"status\":\"success\","
                            + "\"message\":\"Bus Reservation Updated Successfully\""
                            + "}"
            );

        } catch (Exception e) {

            log.error(" Exception in BusReservationPutServlet", e);

            response.setStatus(
                    SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{"
                            + "\"status\":\"error\","
                            + "\"message\":\"Internal Server Error\""
                            + "}"
            );
        }

        log.info("Bus Reservation PUT Servlet Completed");
    }
}