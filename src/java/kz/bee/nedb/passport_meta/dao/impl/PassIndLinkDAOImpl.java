/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.PassIndLinkDAO;
import kz.bee.nedb.passport_meta.entity.PassIndLink;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class PassIndLinkDAOImpl implements PassIndLinkDAO{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public PassIndLink select(Long id) {
        List<PassIndLink> passIndLinks = sessionFactory.getCurrentSession().createQuery("from PassIndLink where id = :id").setLong("id", id).list();
        if ((passIndLinks == null) || passIndLinks.isEmpty()) {
            return null;
        } else {
            return passIndLinks.get(0);
        }
    }

    @Override
    public List<PassIndLink> list() {
        return sessionFactory.getCurrentSession().createQuery("from PassIndLink order by passId, indId, measureId").list();
    }

    @Override
    public void save(PassIndLink passIndLink) {
        
        sessionFactory.getCurrentSession().saveOrUpdate(passIndLink);
    }

    @Override
    public void delete(PassIndLink passIndLink) {
        sessionFactory.getCurrentSession().delete(passIndLink);
    }

    @Override
    public void delete(Long id) {
        PassIndLink passIndLink = select(id);
        if (passIndLink != null) {
            delete(passIndLink);
        }
    }
}
