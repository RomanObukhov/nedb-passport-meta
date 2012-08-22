/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.OrgTypeIndLink;

/**
 *
 * @author zhanat
 */
public interface OrgTypeIndLinkDAO {
    public OrgTypeIndLink select(Long id);
    public OrgTypeIndLink select(Long passIndLinkId, Long classItemTreeId);
    public List<OrgTypeIndLink> list();
    public List<OrgTypeIndLink> list(Long passIndLinkId);    
    public void save(OrgTypeIndLink orgTypeIndLink);
    public void delete(OrgTypeIndLink orgTypeIndLink);
    public void delete(Long id);    
    public void clear(Long passIndLinkId);    
}
