package com.weekend.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class ProductBean {

    @ValueMapValue
    private String productName;

    @ValueMapValue
    private String price;

    @ValueMapValue
    private String category;

    public String getProductName() {
        return productName;
    }

    public String getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }
}