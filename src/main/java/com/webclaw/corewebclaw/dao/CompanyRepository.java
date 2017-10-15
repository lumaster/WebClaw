package com.webclaw.corewebclaw.dao;

import com.webclaw.corewebclaw.model.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Winiton Chanapolchai on 15/10/2560.
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

}
