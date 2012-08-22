package kz.bee.nedb.passport_meta.service;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.SPClass;

public interface SPClassService {
    public List<SPClass> list();
    public SPClass select(Long id);
    public void delete(SPClass spClass);
    public void delete(Long id);
    public void save(SPClass spClass);
}