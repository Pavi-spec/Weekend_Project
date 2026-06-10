package com.weekend.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(
        adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)

public class FaqModel {

    @ChildResource(name = "faqItems")
    private List<Resource> faqItems;

    public List<Resource> getFaqItems() {
        return faqItems;
    }
}
