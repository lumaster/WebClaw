package com.webclaw.corewebclaw.service;

import com.webclaw.corewebclaw.config.Constant;
import com.webclaw.corewebclaw.dao.CompanyRepository;
import com.webclaw.corewebclaw.model.Company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import us.codecraft.xsoup.Xsoup;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;

@Service
public class SMELinkWebClawServiceImpl implements WebClawService {

    @Value("${SMELink.folder.path}")
    private String folderPath;

    @Value("${output.folder.path}")
    private String outputFolderPath;

    @Autowired
    private CompanyRepository companyRepository;

    public void exportData(int limitLoop) throws IOException {
        int loopIndex = 0;
        int iteratorCount = 0;
        boolean isNotRunAll = limitLoop != 0 ? true : false;
        Path pathMain = Paths.get(folderPath);
        Iterator<Path> iterator = Files.walk(pathMain)
                .filter(path -> path.getFileName().toString().contains(".html") || path.getFileName().toString()
                        .contains(".html.tmp")).iterator();
        while (iterator.hasNext()) {
            iteratorCount++;
            Path path = iterator.next();
            if (Files.isRegularFile(path)) {

                String companyThaiName = null;
                String companyEnglishName = null;
                String companyType = null;
                String companyNumber = null;
                LocalDateTime registerDate = null;
                String companyStatus = null;
                BigDecimal registeredCapital = BigDecimal.ZERO;
                String address = null;
                String businessType = null;
                String businessSize = null;
                String phoneNumber = null;

                Company company = new Company();

                String content = new String(Files.readAllBytes(path));
                Document doc = Jsoup.parse(content);
                if (!content.contains("</html>") || doc.select(".fontCompany").isEmpty()) {
//                    System.out.println("Skip: " + path);
                    company.setPath(path.getFileName().toString());
                    company.setGenStatus(0);
                    companyRepository.save(company);
                    continue;
                }

                if (isNotRunAll && loopIndex >= limitLoop) {
                    break;
                }
                loopIndex++;
//                System.out.println("loaded: " + path);
                companyThaiName = doc.select(".fontCompany").first().childNode(0).toString();
                companyEnglishName = doc.select(".fontCompany").first().childNode(2).toString();
                Elements select = doc.select(".fontCompanyProfile");
                for (int index = 0; index <= select.size(); index++) {
                    switch (index) {
                        case 0:
                            companyType = select.get(0).childNode(1).toString();
                            break;
                        case 1:
                            companyNumber = select.get(1).childNode(1).toString();
                            break;
                        case 2:
                            try {
                                registerDate = LocalDate
                                        .parse(format(select.get(2).childNode(1).toString()), Constant.FORMATTER)
                                        .atStartOfDay();
                            } catch (Exception e) {
                                registerDate = null;
                            }
                            break;
                        case 3:
                            companyStatus = select.get(3).childNode(1).toString();
                            break;
                        case 4:
                            registeredCapital = new BigDecimal(format(select.get(4).childNode(1).toString()));
                            break;
                        case 5:
                            address = select.get(5).childNode(1).toString();
                            break;
                        case 6:
                            businessType = Xsoup.compile(
                                    "/html/body/table[5]/tbody/tr/td/table/tbody/tr/td[2]/table[2]/tbody/tr/td/table[3]/tbody/tr/td/table/tbody/tr[7]/td/table/tbody/tr/td[2]/text()")
                                    .evaluate(doc).get();
                            break;
                        case 7:
                            businessSize = select.get(7).childNode(1).toString();
                            break;
                        case 8:
                            phoneNumber = select.get(8).childNode(1).toString();
                            break;
                        default:
                            break;
                    }
                }

                company.setThaiName(format(companyThaiName));
                company.setEngName(format(companyEnglishName));
                company.setCompanyType(format(companyType));
                company.setCompanyNumber(format(companyNumber));
                company.setRegisterDate(registerDate);
                company.setCompanyStatus(format(companyStatus));
                company.setRegisteredCapital(registeredCapital);
                company.setAddress(format(address));
                company.setBusinessType(format(businessType));
                company.setBusinessSize(format(businessSize));
                company.setPhoneNumber(format(phoneNumber));
                company.setPath(path.getFileName().toString());
                company.setGenStatus(1);
                companyRepository.save(company);
                //companyList.add(company);
            }
        }

        System.out.println(String.format("System found %s files in html and html.tmp type", iteratorCount));
        System.out.println(String.format("System had been clawed only for %s file", loopIndex));

    }

    private String format(String text) {
        if (text == null) {
            return null;
        }
        return text.replaceFirst(":", "").trim();
    }
}
