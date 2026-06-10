package com.weekend.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

@Model(adaptables = Resource.class)
public class ProductCatalogModel {

    @Self
    private Resource resource;

    private List<ProductBean> products;

    @PostConstruct
    protected void init() {

        products = new ArrayList<>();

        Resource productsResource = resource.getChild("products");

        if (productsResource != null) {

            for (Resource item : productsResource.getChildren()) {

                ProductBean bean = item.adaptTo(ProductBean.class);

                if (bean != null) {
                    products.add(bean);
                }
            }
        }
    }

    public List<ProductBean> getProducts() {
        return products;
    }
}