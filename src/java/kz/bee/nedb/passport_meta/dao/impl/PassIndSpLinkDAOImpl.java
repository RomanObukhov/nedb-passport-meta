/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.PassIndSpLinkDAO;
import kz.bee.nedb.passport_meta.entity.PassIndSpLink;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class PassIndSpLinkDAOImpl implements PassIndSpLinkDAO{
    @Autowired
    private SessionFactory sessionFactory;    

    @Override
    public PassIndSpLink select(Long id) {
        List<PassIndSpLink> passIndSpLinks = sessionFactory.getCurrentSession().createQuery("from PassIndSpLink where id = :id").setLong("id", id).list();
        if ((passIndSpLinks == null) || passIndSpLinks.isEmpty()) {
            return null;
        } else {
            return passIndSpLinks.get(0);
        }
    }

    @Override
    public List<PassIndSpLink> list() {
        return sessionFactory.getCurrentSession().createQuery("from PassIndSpLink").list();
    }

    @Override
    public void save(PassIndSpLink passIndSpLink) {
        sessionFactory.getCurrentSession().saveOrUpdate(passIndSpLink);
    }

    @Override
    public void delete(PassIndSpLink passIndSpLink) {
        sessionFactory.getCurrentSession().delete(passIndSpLink);
    }

    @Override
    public void delete(Long id) {
        PassIndSpLink passIndSpLink = select(id);
        if (passIndSpLink != null) {
            delete(passIndSpLink);
        }
    }   
    
}
