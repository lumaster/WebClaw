package com.webclaw.corewebclaw.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Company {
    private String thaiName;
    private String engName;
    private String companyType;
    private String companyNumber;
    private Date registerDate;
    private String companyStatus;
    private BigDecimal registeredCapital;
    private String address;
    private String businessType;
    private String businessSize;
    private String phoneNumber;
}
