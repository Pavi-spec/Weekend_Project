package com.weekend.core.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class DynamicListModel {

    @ValueMapValue
    private String parentPath;

    @ValueMapValue
    private int limit;

    @SlingObject
    private ResourceResolver resourceResolver;

    private List<Page> childPages = new ArrayList<>();

    @PostConstruct
    protected void init() {

        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);

        if (pageManager != null && parentPath != null) {

            Page parentPage = pageManager.getPage(parentPath);

            if (parentPage != null) {

                Iterator<Page> children = parentPage.listChildren();

                int count = 0;

                while (children.hasNext() && count < limit) {

                    Page childPage = children.next();

                    childPages.add(childPage);

                    count++;
                }
            }
        }
    }

    public List<Page> getChildPages() {
        return childPages;
    }
}