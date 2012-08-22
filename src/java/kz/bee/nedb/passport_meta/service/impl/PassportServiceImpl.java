/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.PassportDAO;
import kz.bee.nedb.passport_meta.entity.Passport;
import kz.bee.nedb.passport_meta.service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class PassportServiceImpl implements PassportService{
    
    @Autowired
    PassportDAO passportDAO;     

    
    @Override
    @Transactional
    public Passport select(Long id) {        
        return passportDAO.select(id);
    }
    
    @Override
    @Transactional
    public List<Passport> list() {
        return passportDAO.list();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        passportDAO.delete(id);
    }    

    @Override
    @Transactional
    public void delete(Passport passport) {        
        passportDAO.delete(passport);
    }
    
    @Override
    @Transactional
    public void save(Passport passport) {        
        passportDAO.save(passport);
    }

}
