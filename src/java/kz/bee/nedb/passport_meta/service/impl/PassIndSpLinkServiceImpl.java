/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.PassIndSpLinkDAO;
import kz.bee.nedb.passport_meta.entity.PassIndSpLink;
import kz.bee.nedb.passport_meta.service.PassIndSpLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class PassIndSpLinkServiceImpl implements PassIndSpLinkService{
     @Autowired
    private PassIndSpLinkDAO passIndSpLinkDAO;

    @Override
    @Transactional
    public List<PassIndSpLink> list() {
        return passIndSpLinkDAO.list();
    }

    @Override
    @Transactional
    public PassIndSpLink select(Long id) {
        return passIndSpLinkDAO.select(id);
    }

    @Override
    @Transactional
    public void delete(PassIndSpLink passIndSpLink) {
        passIndSpLinkDAO.delete(passIndSpLink);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        passIndSpLinkDAO.delete(id);
    }

    @Override
    @Transactional
    public void save(PassIndSpLink passIndSpLink) {
        passIndSpLinkDAO.save(passIndSpLink);
    }
    
}
