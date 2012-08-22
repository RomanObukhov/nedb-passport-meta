/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.PassIndLinkDAO;
import kz.bee.nedb.passport_meta.entity.PassIndLink;
import kz.bee.nedb.passport_meta.service.PassIndLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class PassIndLinkServiceImpl implements PassIndLinkService{
    @Autowired
    private PassIndLinkDAO passIndLinkDAO;
    
    @Override
    @Transactional
    public List<PassIndLink> list() {
        return passIndLinkDAO.list();
    }

    @Override
    @Transactional
    public PassIndLink select(Long id) {
        return passIndLinkDAO.select(id);
    }

    @Override
    @Transactional
    public void delete(PassIndLink passIndLink) {
        passIndLinkDAO.delete(passIndLink);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        passIndLinkDAO.delete(id);
    }

    @Override
    @Transactional
    public void save(PassIndLink passIndLink) {
        passIndLinkDAO.save(passIndLink);
    }
    
}
