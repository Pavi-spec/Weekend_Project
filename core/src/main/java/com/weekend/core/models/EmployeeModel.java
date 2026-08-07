package com.weekend.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class EmployeeModel {

    @ChildResource(name = "employees")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    @Model(
            adaptables = Resource.class,
            defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
    )
    public static class Employee {

        @ValueMapValue
        private String name;

        @ValueMapValue
        private String role;

        public String getName() {
            return name;
        }

        public String getRole() {
            return role;
        }
    }
}