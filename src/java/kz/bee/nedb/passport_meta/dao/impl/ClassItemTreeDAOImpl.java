package kz.bee.nedb.passport_meta.dao.impl;

import java.sql.Timestamp;
import java.util.List;
import kz.bee.nedb.passport_meta.dao.ClassItemTreeDAO;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassItemTreeDAOImpl implements ClassItemTreeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ClassItemTree select(Long id) {
        List<ClassItemTree> classItemTrees = sessionFactory.getCurrentSession().createQuery("from ClassItemTree where id = :id").setLong("id", id).list();
        if ((classItemTrees == null) || classItemTrees.isEmpty()) {
            return null;
        } else {
            return classItemTrees.get(0);
        }
    }

    @Override
    public List<ClassItemTree> list() {
        return sessionFactory.getCurrentSession().createQuery("from ClassItemTree order by spId, code").list();
    }
    
    @Override
    public List<ClassItemTree> list(Long spId) {
        return sessionFactory.getCurrentSession().createQuery("from ClassItemTree where spId = :spId order by code").setLong("spId", spId).list();
    }
    
    @Override
    public List<ClassItemTree> list(Long spId, Long parId) {
        return sessionFactory.getCurrentSession().createQuery("from ClassItemTree where spId = :spId and parId = :parId order by code").setLong("spId", spId).setLong("parId", parId).list();
    }

    @Override
    public void save(ClassItemTree classItemTree) {
        sessionFactory.getCurrentSession().saveOrUpdate(classItemTree);
    }

    @Override
    public void delete(ClassItemTree classItemTree) {
        sessionFactory.getCurrentSession().delete(classItemTree);
    }

    @Override
    public void delete(Long id) {
        ClassItemTree classItemTree = select(id);
        if (classItemTree != null) {
            delete(classItemTree);
        }
    }
    
    @Override
    public ClassItemTree clone(Long id) {
        if (id == null) {
            return null;
        } else {
            ClassItemTree classItemTree = select(id);
            
            if (classItemTree == null) {
                return null;
            } else {
                Timestamp currentTime = new Timestamp(System.currentTimeMillis());
                
                ClassItemTree clonedClassItemTree = new ClassItemTree(classItemTree);
                clonedClassItemTree.setBegDate(currentTime);
                clonedClassItemTree.setEndDate(null);
                clonedClassItemTree.setUpdTime(currentTime);
                sessionFactory.getCurrentSession().save(clonedClassItemTree);
                
                classItemTree.setEndDate(currentTime);
                sessionFactory.getCurrentSession().saveOrUpdate(classItemTree);
                
                List<ClassItemTree> children = list(clonedClassItemTree.getSpId(), classItemTree.getId());
                for (ClassItemTree child: children) {
                    child.setParId(clonedClassItemTree.getId());
                    save(child);
                }
                
                return clonedClassItemTree;
            }
        }
    }

    @Override
    public List<ClassItemTree> listOrgTypeCitLink() {
        return sessionFactory.getCurrentSession().createQuery("from ClassItemTree where spId not in (60, 80, 61, 62)  order by spId, code").list();
    }
}