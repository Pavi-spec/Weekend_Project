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

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Iterator;

@Component(service = Servlet.class)

@SlingServletResourceTypes(
        resourceTypes = "weekend/components/employee",
        methods = HttpConstants.METHOD_DELETE,
        selectors = "delete",
        extensions = "json"
)
public class EmployeeDeleteServlet extends SlingAllMethodsServlet {

    @Override
    protected void doDelete(SlingHttpServletRequest request,
                            SlingHttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {

            String name = request.getParameter("name");
            String role = request.getParameter("role");

            Resource componentResource = request.getResource();

            Resource employeesResource =
                    componentResource.getChild("employees");

            if (employeesResource == null) {

                response.getWriter().write(
                        "{\"status\":\"error\",\"message\":\"Employees node not found\"}"
                );
                return;
            }

            ResourceResolver resolver =
                    request.getResourceResolver();

            Iterator<Resource> employeeIterator =
                    employeesResource.listChildren();

            boolean updated = false;

            while (employeeIterator.hasNext()) {

                Resource employee =
                        employeeIterator.next();

                ModifiableValueMap map =
                        employee.adaptTo(ModifiableValueMap.class);

                String empName =
                        map.get("name", "");

                String empRole =
                        map.get("role", "");

                // CASE 1:
                // name + role -> delete entire employee node

                if (name != null && role != null
                        && empName.equalsIgnoreCase(name)
                        && empRole.equalsIgnoreCase(role)) {

                    resolver.delete(employee);

                    updated = true;
                }

                // CASE 2:
                // only name -> remove name property

                else if (name != null
                        && role == null
                        && empName.equalsIgnoreCase(name)) {

                    map.remove("name");

                    updated = true;
                }

                // CASE 3:
                // only role -> remove role property

                else if (role != null
                        && name == null
                        && empRole.equalsIgnoreCase(role)) {

                    map.remove("role");

                    updated = true;
                }
            }

            resolver.commit();

            if (updated) {

                response.getWriter().write(
                        "{\"status\":\"success\",\"message\":\"Delete operation completed\"}"
                );

            } else {

                response.getWriter().write(
                        "{\"status\":\"failed\",\"message\":\"No matching employee found\"}"
                );
            }

        } catch (Exception e) {

            response.setStatus(
                    SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR
            );

            response.getWriter().write(
                    "{\"status\":\"error\",\"message\":\"Internal Server Error\"}"
            );
        }
    }
}