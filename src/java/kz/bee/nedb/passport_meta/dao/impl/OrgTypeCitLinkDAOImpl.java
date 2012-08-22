/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.OrgTypeCitLinkDAO;
import kz.bee.nedb.passport_meta.entity.OrgTypeCitLink;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class OrgTypeCitLinkDAOImpl implements OrgTypeCitLinkDAO{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public OrgTypeCitLink select(Long id) {
        List<OrgTypeCitLink> orgTypeCitLinks = sessionFactory.getCurrentSession().createQuery("from OrgTypeCitLink where id = :id").setLong("id", id).list();
        if ((orgTypeCitLinks == null) || orgTypeCitLinks.isEmpty()) {
            return null;
        } else {
            return orgTypeCitLinks.get(0);
        }
    }

    @Override
    public List<OrgTypeCitLink> list() {
        return sessionFactory.getCurrentSession().createQuery("from OrgTypeCitLink order by id").list();
    }

    @Override
    public void save(OrgTypeCitLink orgTypeCitLink) {
        
        sessionFactory.getCurrentSession().saveOrUpdate(orgTypeCitLink);
    }

    @Override
    public void delete(OrgTypeCitLink orgTypeCitLink) {
        sessionFactory.getCurrentSession().delete(orgTypeCitLink);
    }

    @Override
    public void delete(Long id) {
        OrgTypeCitLink orgTypeCitLink = select(id);
        if (orgTypeCitLink != null) {
            delete(orgTypeCitLink);
        }
    }
}
