package com.weekend.core.models;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class BusReservationModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String from;

    @ValueMapValue
    private String to;

    @ValueMapValue
    private String date;

    @ValueMapValue
    private Double price;

    @ValueMapValue
    private Integer seats;

    @ValueMapValue
    private Boolean ac;

    @ValueMapValue
    private String buttonText;

    @ValueMapValue
    private String buttonLink;

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public String getImage() { return image; }

    public String getFrom() { return from; }

    public String getTo() { return to; }

    public String getDate() { return date; }

    public Double getPrice() { return price; }

    public Integer getSeats() { return seats; }

    public Boolean getAc() { return ac; }

    public String getButtonText() { return buttonText; }

    public String getButtonLink() { return buttonLink; }
}