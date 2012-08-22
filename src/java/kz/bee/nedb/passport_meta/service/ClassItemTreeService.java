package kz.bee.nedb.passport_meta.service;

import java.sql.Timestamp;
import java.util.List;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;

public interface ClassItemTreeService {
    public ClassItemTree select(Long id);
    public List<ClassItemTree> list();
    public List<ClassItemTree> list(Long spId);
    public List<ClassItemTree> listOrgTypeCitLink();
    public List<ClassItemTree> list(Long spId, Long parId);
    public void save(ClassItemTree classItemTree);
    public void save(
        Long id,
        Long parId,
        Long spId,
        String rname,
        String kname,
        String ename,
        Timestamp begDate,
        Timestamp endDate,
        String code,
        Long attr1
    );
    public void delete(ClassItemTree classItemTree);
    public void delete(Long id);
    public ClassItemTree clone(Long id);
}