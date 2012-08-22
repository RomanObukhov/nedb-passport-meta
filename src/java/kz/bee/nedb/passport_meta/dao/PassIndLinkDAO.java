/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.PassIndLink;

/**
 *
 * @author zhanat
 */
public interface PassIndLinkDAO {
    public PassIndLink select(Long id);
    public List<PassIndLink> list();
    public void save(PassIndLink passIndLink);
    public void delete(PassIndLink passIndLink);
    public void delete(Long id);    
}
