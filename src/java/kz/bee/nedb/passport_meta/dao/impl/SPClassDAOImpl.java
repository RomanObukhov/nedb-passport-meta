package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.SPClassDAO;
import kz.bee.nedb.passport_meta.entity.SPClass;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SPClassDAOImpl implements SPClassDAO {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public SPClass select(Long id) {
        List<SPClass> spClasses = sessionFactory.getCurrentSession().createQuery("from SPClass where id = :id").setLong("id", id).list();
        if ((spClasses == null) || spClasses.isEmpty()) {
            return null;
        } else {
            return spClasses.get(0);
        }
    }

    @Override
    public List<SPClass> list() {
        return sessionFactory.getCurrentSession().createQuery("from SPClass").list();
    }

    @Override
    public void save(SPClass spClass) {        
        sessionFactory.getCurrentSession().saveOrUpdate(spClass);
    }

    @Override
    public void delete(SPClass spClass) {
        sessionFactory.getCurrentSession().delete(spClass);
    }

    @Override
    public void delete(Long id) {
        SPClass spClass = select(id);
        if (spClass != null) {
            delete(spClass);
        }
    }
}