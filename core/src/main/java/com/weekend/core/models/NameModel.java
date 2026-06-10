package com.weekend.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class)
public class NameModel {

    private String name = "Pavi";

    public String getName() {
        return name;
    }
}
