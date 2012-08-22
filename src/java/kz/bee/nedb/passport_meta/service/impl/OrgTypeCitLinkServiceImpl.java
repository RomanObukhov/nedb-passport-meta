/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.OrgTypeCitLinkDAO;
import kz.bee.nedb.passport_meta.entity.OrgTypeCitLink;
import kz.bee.nedb.passport_meta.service.OrgTypeCitLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author zhanat
 */
@Service
public class OrgTypeCitLinkServiceImpl implements OrgTypeCitLinkService{
    @Autowired
    private OrgTypeCitLinkDAO  orgTypeCitLinkDAO;
    
    @Override
    @Transactional
    public List<OrgTypeCitLink> list() {
        return orgTypeCitLinkDAO.list();
    }

    @Override
    @Transactional
    public OrgTypeCitLink select(Long id) {
        return orgTypeCitLinkDAO.select(id);
    }

    @Override
    @Transactional
    public void delete(OrgTypeCitLink orgTypeCitLink) {
        orgTypeCitLinkDAO.delete(orgTypeCitLink);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        orgTypeCitLinkDAO.delete(id);
    }

    @Override
    @Transactional
    public void save(OrgTypeCitLink orgTypeCitLink) {
        orgTypeCitLinkDAO.save(orgTypeCitLink);
    }
    
}
