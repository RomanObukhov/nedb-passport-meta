package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;

public interface ClassItemTreeDAO {
    public ClassItemTree select(Long id);
    public List<ClassItemTree> list();
    public List<ClassItemTree> list(Long spId);
    public List<ClassItemTree> listOrgTypeCitLink();    
    public List<ClassItemTree> list(Long spId, Long parId);
    public void save(ClassItemTree classItemTree);
    public void delete(ClassItemTree classItemTree);
    public void delete(Long id);
    public ClassItemTree clone(Long id);
}