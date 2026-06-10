package com.weekend.core.services.impl;

import com.weekend.core.services.MessageService;

import org.osgi.service.component.annotations.Component;

@Component(
        service = MessageService.class,
        property = {
                "service.ranking:Integer=20"
        }
)

public class FunnyMessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {

        return "Bring popcorn also 😄";

    }
}