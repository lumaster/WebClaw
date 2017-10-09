package com.webclaw.corewebclaw;

import com.webclaw.corewebclaw.service.SMELinkWebClawServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class CoreWebClawApplication implements CommandLineRunner {

	@Autowired
	private SMELinkWebClawServiceImpl SMELinkWebClawService;

	public static void main(String[] args) {
		SpringApplication.run(CoreWebClawApplication.class, args);
	}

	@Override
	public void run(String... arg) throws Exception {
		System.out.println("Start Clawing from file..");
	    if(arg.length == 1){
			System.out.println(String.format("Limit Clawing only %s file", arg[0]));
			SMELinkWebClawService.exportData(Integer.parseUnsignedInt(arg[0]));
        }else{
			SMELinkWebClawService.exportData(0);
		}
		System.out.println("Done Clawing from file..");


	}
}
