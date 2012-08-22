/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.OrgTypeCitLink;

/**
 *
 * @author zhanat
 */
public interface OrgTypeCitLinkDAO {
    public OrgTypeCitLink select(Long id);
    public List<OrgTypeCitLink> list();
    public void save(OrgTypeCitLink orgTypeCitLink);
    public void delete(OrgTypeCitLink orgTypeCitLink);
    public void delete(Long id);    
}
