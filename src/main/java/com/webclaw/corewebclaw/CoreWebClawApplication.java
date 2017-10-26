package com.webclaw.corewebclaw;

import com.webclaw.corewebclaw.service.SMELinkWebClawServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreWebClawApplication implements CommandLineRunner {

    @Autowired
    private SMELinkWebClawServiceImpl SMELinkWebClawService;

    public static void main(String[] args) {
        SpringApplication.run(CoreWebClawApplication.class, args);
    }

    @Override
    public void run(String... arg) throws Exception {
        SMELinkWebClawService.exportData();
    }
}
