/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.MeasureDAO;
import kz.bee.nedb.passport_meta.entity.Measure;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class MeasureDAOImpl implements MeasureDAO{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Measure> list() {
        List<Measure> list = sessionFactory.getCurrentSession().createQuery("from Measure order by id").list();
        return list;
    }

    @Override
    public Measure select(Long id) {
        
        List<Measure> list = sessionFactory.getCurrentSession().createQuery("from Measure where id = :id").setLong("id", id).list();
        if ((list == null) || list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void save(Measure measure) {
        sessionFactory.getCurrentSession().saveOrUpdate(measure);
    }

    @Override
    public void delete(Measure measure) {
        sessionFactory.getCurrentSession().delete(measure);
    }

    @Override
    public void delete(Long id) {
        Measure measure = select(id);
        if (measure != null) {
            delete(measure);
        }
    }
    
}
