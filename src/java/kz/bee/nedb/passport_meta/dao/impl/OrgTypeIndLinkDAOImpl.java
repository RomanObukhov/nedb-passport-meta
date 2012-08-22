/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao.impl;

import java.util.List;
import kz.bee.nedb.passport_meta.dao.OrgTypeIndLinkDAO;
import kz.bee.nedb.passport_meta.entity.OrgTypeIndLink;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author zhanat
 */
@Repository
public class OrgTypeIndLinkDAOImpl implements OrgTypeIndLinkDAO{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public OrgTypeIndLink select(Long id) {
        List<OrgTypeIndLink> orgTypeCitLinks = sessionFactory.getCurrentSession().createQuery("from OrgTypeIndLink where id = :id").setLong("id", id).list();
        if ((orgTypeCitLinks == null) || orgTypeCitLinks.isEmpty()) {
            return null;
        } else {
            return orgTypeCitLinks.get(0);
        }
    }

    @Override
    public OrgTypeIndLink select(Long passIndLinkId, Long classItemTreeId) {
        List<OrgTypeIndLink> orgTypeCitLinks = sessionFactory.getCurrentSession().createQuery("from OrgTypeIndLink where passIndLinkId = :passIndLinkId and classItemTreeId=:classItemTreeId")
                .setLong("passIndLinkId", passIndLinkId)
                .setLong("classItemTreeId", classItemTreeId)
                .list();
        if ((orgTypeCitLinks == null) || orgTypeCitLinks.isEmpty()) {
            return null;
        } else {
            return orgTypeCitLinks.get(0);
        }
    }
    
    @Override
    public List<OrgTypeIndLink> list() {
        return sessionFactory.getCurrentSession().createQuery("from OrgTypeIndLink order by id").list();
    }

    @Override
    public List<OrgTypeIndLink> list(Long passIndLinkId) {
        return sessionFactory.getCurrentSession().createQuery("from OrgTypeIndLink where passIndLinkId=:passIndLinkId order by id").setLong("passIndLinkId", passIndLinkId).list();
    }
    
    @Override
    public void save(OrgTypeIndLink orgTypeIndLink) {
        
        sessionFactory.getCurrentSession().saveOrUpdate(orgTypeIndLink);
    }

    @Override
    public void delete(OrgTypeIndLink orgTypeIndLink) {
        sessionFactory.getCurrentSession().delete(orgTypeIndLink);
    }

    @Override
    public void delete(Long id) {
        OrgTypeIndLink orgTypeIndLink = select(id);
        if (orgTypeIndLink != null) {
            delete(orgTypeIndLink);
        }
    }

    @Override
    public void clear(Long passIndLinkId) {
        sessionFactory.getCurrentSession().createQuery("delete from OrgTypeIndLink where passIndLinkId = :passIndLinkId")
                .setLong("passIndLinkId", passIndLinkId).executeUpdate();
    }

}
