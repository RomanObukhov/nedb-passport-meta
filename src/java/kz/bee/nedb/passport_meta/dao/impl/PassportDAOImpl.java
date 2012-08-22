/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.PassportDAO;
import kz.bee.nedb.passport_meta.entity.Passport;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class PassportDAOImpl implements PassportDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Passport select(Long id) {
        
        List<Passport> lp = sessionFactory.getCurrentSession().createQuery("from Passport where id = :id").setLong("id", id).list();
        if ((lp == null) || lp.isEmpty()) {
            
            return null;
        } else {
            
            return lp.get(0);
        }
    }

    @Override
    public List<Passport> list() {
        List<Passport> ls = sessionFactory.getCurrentSession().createQuery("from Passport order by id").list();
        return ls;
    }

    @Override
    public void save(Passport passport) {
        sessionFactory.getCurrentSession().saveOrUpdate(passport);
    }

    @Override
    public void delete(Passport passport) {
        sessionFactory.getCurrentSession().delete(passport);
    }

    @Override
    public void delete(Long id) {

        Passport passport = select(id);
        if (passport != null) {
            delete(passport);
        }  
    }
    
}
