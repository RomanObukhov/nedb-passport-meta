/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.OrgTypeCitLink;

/**
 *
 * @author zhanat
 */
public interface OrgTypeCitLinkService {
    public List<OrgTypeCitLink> list();
    public OrgTypeCitLink select(Long id);
    public void delete(OrgTypeCitLink orgTypeCitLink);
    public void delete(Long id);
    public void save(OrgTypeCitLink orgTypeCitLink);
}
