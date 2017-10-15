package com.webclaw.corewebclaw.model;

import com.webclaw.corewebclaw.model.converter.TimestampConverter;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "sme_link")
public class Company {

    @Id
    @Column(name = "sme_link_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sme_link_sme_link_id_seq")
    @SequenceGenerator(name = "sme_link_sme_link_id_seq", sequenceName = "sme_link_sme_link_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "thai_name")
    private String thaiName;

    @Column(name = "eng_name")
    private String engName;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "company_number")
    private String companyNumber;

    @Column(name = "register_date")
    @Convert(converter = TimestampConverter.class)
    private LocalDateTime registerDate;

    @Column(name = "company_status")
    private String companyStatus;

    @Column(name = "register_capital")
    private BigDecimal registeredCapital;

    @Column(name = "address")
    private String address;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "business_size")
    private String businessSize;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "path")
    private String path;

    @Column(name = "gen_status")
    private int genStatus;

    public Company() {
    }

    public Company(
            String thaiName, String engName, String companyType, String companyNumber, LocalDateTime registerDate,
            String companyStatus, BigDecimal registeredCapital, String address, String businessType,
            String businessSize, String phoneNumber, String path, int genStatus) {
        this.thaiName = thaiName;
        this.engName = engName;
        this.companyType = companyType;
        this.companyNumber = companyNumber;
        this.registerDate = registerDate;
        this.companyStatus = companyStatus;
        this.registeredCapital = registeredCapital;
        this.address = address;
        this.businessType = businessType;
        this.businessSize = businessSize;
        this.phoneNumber = phoneNumber;
        this.path = path;
        this.genStatus = genStatus;
    }
}
