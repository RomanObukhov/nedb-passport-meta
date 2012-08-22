package kz.bee.nedb.passport_meta.web;

import java.util.Map;
import kz.bee.nedb.passport_meta.entity.SPClass;
import kz.bee.nedb.passport_meta.entity.wrapper.SPClassWrapper;
import kz.bee.nedb.passport_meta.service.SPClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SPClassController {
    @Autowired
    private SPClassService spClassService;
    
    @RequestMapping("/sp-class")
    public String spClass(Map<String, Object> map, @RequestParam(value="id", required=false, defaultValue="") Long id) {
        map.put("spClasses", SPClassWrapper.sort(spClassService.list()));
        
        SPClass spClass;
        if (id != null) {
            spClass = spClassService.select(id);
            if (spClass == null) {
                spClass = new SPClass();
            }
        } else {
            spClass = new SPClass();
        }
        
        map.put("spClass", spClass);
        
        return "sp-class";
    }
    
    @RequestMapping(value="/sp-class-save", method= RequestMethod.POST)
    public String spClassSave(
            @RequestParam(value="id", required=false, defaultValue="") Long id,
            @RequestParam(value="rname", required=false, defaultValue="") String rname,
            @RequestParam(value="kname", required=false, defaultValue="") String kname,
            @RequestParam(value="ename", required=false, defaultValue="") String ename,
            @RequestParam(value="tag", required=false, defaultValue="") Long tag
            )
    {
        SPClass spClass = null;
        
        if (id != null) {
            spClass = spClassService.select(id);
        }
        
        if (spClass == null) {
            spClass = new SPClass();
        }
        
        spClass.setRname(rname);
        spClass.setKname(kname);
        spClass.setEname(ename);
        spClass.setTag(tag);
        
        spClassService.save(spClass);
        
        return "redirect:/sp-class";
    }
    
    @RequestMapping("sp-class-delete")
    public String spClassDelete(@RequestParam(value="id", required=true) Long id) {
        spClassService.delete(id);
        
        return "redirect:/sp-class";
    }
}