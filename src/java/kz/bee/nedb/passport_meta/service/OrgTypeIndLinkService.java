/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.service;

import java.util.List;
import java.util.Map;
import kz.bee.nedb.passport_meta.entity.OrgTypeIndLink;

/**
 *
 * @author zhanat
 */
public interface OrgTypeIndLinkService {
    public List<OrgTypeIndLink> list();
    public OrgTypeIndLink select(Long id);
    public void delete(OrgTypeIndLink orgTypeIndLink);
    public void delete(Long id);
    public void save(OrgTypeIndLink orgTypeIndLink);
    public void edit(Long passIndLinkId, Map<String, String[]> params);
    public String toTable();
}
