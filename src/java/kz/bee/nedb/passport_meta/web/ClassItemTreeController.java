package kz.bee.nedb.passport_meta.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;
import kz.bee.nedb.passport_meta.entity.SPClass;
import kz.bee.nedb.passport_meta.entity.wrapper.ClassItemTreeWrapper;
import kz.bee.nedb.passport_meta.service.ClassItemTreeService;
import kz.bee.nedb.passport_meta.service.SPClassService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClassItemTreeController {
    private static final Logger LOGGER = Logger.getLogger(ClassItemTreeController.class);
    private static final SimpleDateFormat JAVASCRIPT_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    
    @Autowired
    private ClassItemTreeService classItemTreeService;
    @Autowired
    private SPClassService spClassService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value="/class-item-tree")
    public String classItemTree(
            Map<String, Object> map,
            @RequestParam(value="id", required=false, defaultValue="") Long id,
            @RequestParam(value="spId", required=false, defaultValue="") Long spId
    ) {
        map.put("lang", LocaleContextHolder.getLocale().getLanguage());
        map.put("dateFormat", JAVASCRIPT_DATE_FORMAT);
        map.put("dateTimeFormat", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));
        map.put("edit", messageSource.getMessage("label.edit", null, "Изменить", LocaleContextHolder.getLocale()));
        map.put("delete", messageSource.getMessage("label.delete", null, "Удалить", LocaleContextHolder.getLocale()));
        map.put("city", messageSource.getMessage("label.city", null, "Город", LocaleContextHolder.getLocale()));
        map.put("village", messageSource.getMessage("label.village", null, "Село", LocaleContextHolder.getLocale()));
        
        String[] k = new String[5];
        for (int i = 0; i < 5; i++) {
            k[i] = messageSource.getMessage("class-item-tree.k-" + i, null, "K-" + i, LocaleContextHolder.getLocale());
        }
        map.put("k", k);
        
        List<SPClass> spClasses = spClassService.list();
        map.put("spClasses", spClasses);
        
        if (spId != null) {
            SPClass selectedSPClass = null;
            for (SPClass spClass: spClasses) {
                if (spClass.getId().equals(spId)) {
                    selectedSPClass = spClass;
                    break;
                }
            }
            
            map.put("selectedSPClass", selectedSPClass);
            
            List<ClassItemTreeWrapper> classItemTreeWrappers = ClassItemTreeWrapper.wrapList(classItemTreeService.list(spId), spClasses);
            
            if ((classItemTreeWrappers != null) && !classItemTreeWrappers.isEmpty()) {
                map.put("classItemTreeList", ClassItemTreeWrapper.buildTree(classItemTreeWrappers, spClasses));
                if (id != null) {
                    for (ClassItemTreeWrapper classItemTreeWrapper: classItemTreeWrappers) {
                        if (id.equals(classItemTreeWrapper.getId())) {
                            map.put("selectedClassItemTree", classItemTreeWrapper);
                            map.put("availableParents", ClassItemTreeWrapper.availableItems(classItemTreeWrappers, id));
                            break;
                        }
                    }
                } else {
                    map.put("selectedClassItemTree", new ClassItemTree(new Timestamp(System.currentTimeMillis())));
                    map.put("availableParents", classItemTreeWrappers);
                }
            } else {
                map.put("selectedClassItemTree", new ClassItemTree(new Timestamp(System.currentTimeMillis())));
                map.put("availableParents", classItemTreeWrappers);
            }
        }
        
        return "class-item-tree";
    }
    
    @RequestMapping(value="/child-class-item-tree")
    public String childClassItemTree(
            Map<String, Object> map
    ) {
        return "child-class-item-tree";
    }
    
    @RequestMapping(value="/save-class-item-tree", method= RequestMethod.POST)
    public String saveClassItemTree(
            @RequestParam(value="id", required=false, defaultValue="") Long id,
            @RequestParam(value="parId", required=false, defaultValue="") Long parId,
            @RequestParam(value="spId", required=false, defaultValue="") Long spId,
            @RequestParam(value="rname", required=false, defaultValue="") String rname,
            @RequestParam(value="kname", required=false, defaultValue="") String kname,
            @RequestParam(value="ename", required=false, defaultValue="") String ename,
            @RequestParam(value="begDate", required=false, defaultValue="") String begDate,
            @RequestParam(value="endDate", required=false, defaultValue="") String endDate,
            @RequestParam(value="code", required=false, defaultValue="") String code,
            @RequestParam(value="attr1", required=false, defaultValue="") Long attr1
    ) throws ParseException {
        Timestamp endDateTimestamp;
        try {
            endDateTimestamp = new Timestamp(JAVASCRIPT_DATE_FORMAT.parse(endDate).getTime());
        } catch (ParseException ex) {
            LOGGER.warn("parsing endDate: " + endDate + " as " + JAVASCRIPT_DATE_FORMAT.toPattern(), ex);
            endDateTimestamp = null;
        }
        
        classItemTreeService.save(id, parId, spId, rname, kname, ename, new Timestamp(JAVASCRIPT_DATE_FORMAT.parse(begDate).getTime()), endDateTimestamp, code, attr1);
        
        String redirectParams = "?";
        if (id != null) {
            redirectParams = redirectParams + "id=" + id.toString();
        }
        
        if (spId != null) {
            if (!redirectParams.equals("?")) {
                redirectParams = redirectParams + "&";
            }
            redirectParams = redirectParams + "spId=" + spId.toString();
        }
        
        if (redirectParams.equals("?")) {
            return "redirect:/class-item-tree";
        } else {
            return "redirect:/class-item-tree" + redirectParams;
        }
    }
    
    @RequestMapping(value="/delete-class-item-tree")
    public String deleteClassItemTree(
            @RequestParam(value="spId", required=false, defaultValue="") Long spId,
            @RequestParam(value="id", required=false, defaultValue="") Long id
    ) {
        classItemTreeService.delete(id);
        
        if (spId == null) {
            return "redirect:/class-item-tree";
        } else {
            return "redirect:/class-item-tree?spId=" + spId.toString();
        }
    }
    
    @RequestMapping(value="/clone-class-item-tree")
    public String cloneClassItemTree(
            @RequestParam(value="spId", required=false, defaultValue="") Long spId,
            @RequestParam(value="id", required=false, defaultValue="") Long id
    ) {
        if (spId == null) {
            return "redirect:/class-item-tree";
        } else if (id == null) {
            return "redirect:/class-item-tree?spId=" + spId.toString();
        } else {
            ClassItemTree clonedClassItemTree = classItemTreeService.clone(id);
            if (clonedClassItemTree == null) {
                return "redirect:/class-item-tree?spId=" + spId.toString() + "&id=" + id.toString();
            } else {
                return "redirect:/class-item-tree?spId=" + spId.toString() + "&id=" + clonedClassItemTree.getId().toString();
            }
        }
    }
}