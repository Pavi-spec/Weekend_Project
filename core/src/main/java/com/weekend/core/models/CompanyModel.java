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

public class CompanyModel {

    @ChildResource(name = "employees")
    private List<Employee> employees;

    @ChildResource(name = "projects")
    private List<Project> projects;

    @ChildResource(name = "address")
    private Address address;

    public List<Employee> getEmployees() {

        return employees;

    }

    public List<Project> getProjects() {

        return projects;

    }

    public Address getAddress() {

        return address;

    }

    // =========================
    // INNER CLASS 1
    // =========================

    @Model(adaptables = Resource.class)
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

    // =========================
    // INNER CLASS 2
    // =========================

    @Model(adaptables = Resource.class)
    public static class Project {

        @ValueMapValue
        private String projectName;

        @ValueMapValue
        private String technology;

        public String getProjectName() {

            return projectName;

        }

        public String getTechnology() {

            return technology;

        }
    }

    // =========================
    // INNER CLASS 3
    // =========================

    @Model(adaptables = Resource.class)
    public static class Address {

        @ValueMapValue
        private String city;

        @ValueMapValue
        private String country;

        public String getCity() {

            return city;

        }

        public String getCountry() {

            return country;

        }
    }
}