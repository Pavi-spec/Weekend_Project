package com.weekend.core.models;

import org.apache.sling.api.resource.Resource;

public class TopicCard {

    private final String image;
    private final String title;
    private final String description;
    private final String pagePath;
    private final boolean openInNewTab;

    public TopicCard(Resource resource) {

        this.image = resource.getValueMap()
                .get("image", String.class);

        this.title = resource.getValueMap()
                .get("title", String.class);

        this.description = resource.getValueMap()
                .get("description", String.class);

        this.pagePath = resource.getValueMap()
                .get("pagePath", String.class);

        this.openInNewTab = resource.getValueMap()
                .get("openInNewTab", false);
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPagePath() {
        return pagePath;
    }

    public boolean isOpenInNewTab() {
        return openInNewTab;
    }

    public boolean hasImage() {
        return image != null && !image.trim().isEmpty();
    }

    public boolean hasDescription() {
        return description != null
                && !description.trim().isEmpty();
    }

    public boolean hasPagePath() {
        return pagePath != null
                && !pagePath.trim().isEmpty();
    }
}