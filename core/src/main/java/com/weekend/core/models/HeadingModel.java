package com.weekend.core.models;

import javax.inject.Inject;

import org.apache.sling.models.annotations.Model;

import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class)

public class HeadingModel {

    @Inject
    private String heading;

    public String getHeading() {

        return heading;

    }
}