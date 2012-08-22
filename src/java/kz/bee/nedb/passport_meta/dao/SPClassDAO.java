package kz.bee.nedb.passport_meta.dao;

import java.util.List;
import kz.bee.nedb.passport_meta.entity.SPClass;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public interface SPClassDAO {
    public SPClass select(Long id);
    public List<SPClass> list();
    public void save(SPClass spClass);
    public void delete(SPClass spClass);
    public void delete(Long id);
}