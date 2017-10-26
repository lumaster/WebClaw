package com.webclaw.corewebclaw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

/**
 * Created by Winiton Chanapolchai on 21/10/2560.
 */
@Service
public class LogService {

    private static final Logger LOGGER = LoggerFactory.getLogger("claw.logger");

    public void logEvent(String event) {
        MDC.put("event", event);
    }

    public void logInfo(String message) {
        LOGGER.info(message);
    }

}
