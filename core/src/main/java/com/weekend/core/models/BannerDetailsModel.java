package com.weekend.core.models;

import java.util.ArrayList;
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
public class BannerDetailsModel {

    @ValueMapValue
    private String description;

    @ValueMapValue
    private Boolean showBanner;

    @ChildResource(name = "bannerItems")
    private Resource bannerItems;

    public String getDescription() {
        return description;
    }

    public Boolean getShowBanner() {
        return showBanner;
    }

    public List<Resource> getBannerItems() {

        List<Resource> items = new ArrayList<>();

        if (bannerItems != null) {
            bannerItems.getChildren().forEach(items::add);
        }

        return items;
    }
}