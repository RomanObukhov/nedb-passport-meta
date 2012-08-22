/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.PassIndSpLink;

/**
 *
 * @author zhanat
 */
public interface PassIndSpLinkDAO {
    public PassIndSpLink select(Long id);
    public List<PassIndSpLink> list();
    public void save(PassIndSpLink passIndSpLink);
    public void delete(PassIndSpLink passIndSpLink);
    public void delete(Long id);
}
