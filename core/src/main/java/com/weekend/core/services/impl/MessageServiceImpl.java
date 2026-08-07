package com.weekend.core.services.impl;

import com.weekend.core.services.MessageService;
import org.osgi.service.component.annotations.Component;
@Component(
        service = MessageService.class,
        property = {
                "service.ranking:Integer=2"
        }
)
public class MessageServiceImpl implements MessageService {

    @Override
    public String getMessage() {

        return "We are planning to go Movie coming Saturday";

    }
}
