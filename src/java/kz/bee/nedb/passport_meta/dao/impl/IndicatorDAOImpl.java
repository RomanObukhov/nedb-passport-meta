/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.IndicatorDAO;
import kz.bee.nedb.passport_meta.entity.Indicator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class IndicatorDAOImpl implements IndicatorDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Indicator> list() {
        
        List<Indicator> ls = sessionFactory.getCurrentSession().createQuery("from Indicator order by id").list();
        return ls;
    }
    
    @Override
    public Indicator select(Long id) {
        
        List<Indicator> list = sessionFactory.getCurrentSession().createQuery("from Indicator where id = :id").setLong("id", id).list();
        if ((list == null) || list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void save(Indicator indicator) {               
        sessionFactory.getCurrentSession().saveOrUpdate(indicator);
    }

    @Override
    public void delete(Indicator indicator) {               
        sessionFactory.getCurrentSession().delete(indicator);
    }
    
    @Override
    public void delete(Long id) {
        
        Indicator indicator = select(id);
        if (indicator != null) {
            delete(indicator);
        }  
    }

}
