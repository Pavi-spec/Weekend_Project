package com.weekend.core.services.impl;

import com.weekend.core.services.MessageService;

import org.osgi.service.component.annotations.Component;

@Component(
        service = MessageService.class,
        property = {
                "service.ranking:Integer=3"
        }
)

public class TamilMessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {
        return "நாம் சனிக்கிழமை படம் பார்க்க போகிறோம்";

    }
}