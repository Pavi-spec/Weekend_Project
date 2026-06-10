package com.weekend.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.api.servlets.HttpConstants;

import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import org.osgi.service.component.annotations.Component;

import org.apache.sling.servlets.annotations.SlingServletResourceTypes;

import com.weekend.core.models.BusReservationModel;

@Component(service = Servlet.class)

@SlingServletResourceTypes(
        resourceTypes = "weekend/components/busreservation",
        methods = HttpConstants.METHOD_GET,
        extensions = "json"
)

public class BusReservationServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(
            SlingHttpServletRequest request,
            SlingHttpServletResponse response)

            throws ServletException, IOException {

        Resource resource = request.getResource();

        BusReservationModel model =
                resource.adaptTo(BusReservationModel.class);

        response.setContentType("application/json");

        response.getWriter().write(

                "{"
                        + "\"title\":\"" + model.getTitle() + "\","
                        + "\"description\":\"" + model.getDescription() + "\","
                        + "\"image\":\"" + model.getImage() + "\","
                        + "\"from\":\"" + model.getFrom() + "\","
                        + "\"to\":\"" + model.getTo() + "\","
                        + "\"date\":\"" + model.getDate() + "\","
                        + "\"price\":" + model.getPrice() + ","
                        + "\"seats\":" + model.getSeats() + ","
                        + "\"ac\":" + model.getAc() + ","
                        + "\"buttonText\":\"" + model.getButtonText() + "\","
                        + "\"buttonLink\":\"" + model.getButtonLink() + "\""
                        + "}"
        );
    }
}