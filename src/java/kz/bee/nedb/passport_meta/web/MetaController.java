/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.bee.nedb.passport_meta.web;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import kz.bee.nedb.passport_meta.entity.*;
import kz.bee.nedb.passport_meta.entity.wrapper.*;
import kz.bee.nedb.passport_meta.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author zhanat
 */
@Controller
public class MetaController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private PassportService passportService;
    @Autowired
    private IndicatorService indicatorService;
    @Autowired
    private MeasureService measureService;
    @Autowired
    private PassIndLinkService passIndLinkService;
    @Autowired
    private PassIndSpLinkService passIndSpLinkService;
    @Autowired
    private SPClassService spClassService;
    @Autowired
    private OrgTypeCitLinkService orgTypeCitLinkService;
    @Autowired
    private OrgTypeIndLinkService orgTypeIndLinkService;
    @Autowired
    private ClassItemTreeService classItemTreeService;

    //<editor-fold defaultstate="collapsed" desc="Passport">
    @RequestMapping("/passport")
    public String passport(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        List<Passport> passports = passportService.list();

        map.put("wrapList", PassportWrapper.buildTree(PassportWrapper.wrapList(passports)));
        map.put("edit", messageSource.getMessage("label.edit", null, "Изменить", LocaleContextHolder.getLocale()));
        map.put("delete", messageSource.getMessage("label.delete", null, "Удалить", LocaleContextHolder.getLocale()));

        Passport passport;
        if (id != null) {
            passport = passportService.select(id);

            if (passport == null) {
                passport = new Passport();
                map.put("optionList", PassportWrapper.buildTree(PassportWrapper.wrapList(passports)));
            } else {
                map.put("optionList", PassportWrapper.availableItems(PassportWrapper.wrapList(passports), passport.getId()));
            }
        } else {
            passport = new Passport();
            map.put("optionList", PassportWrapper.buildTree(PassportWrapper.wrapList(passports)));
        }
        map.put("passport", passport);

        return "passport";
    }

    @RequestMapping(value = "/save-passport", method = RequestMethod.POST)
    public String savePassport(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            @RequestParam(value = "parId", required = false, defaultValue = "") Long parId,
            @RequestParam(value = "rname", required = false, defaultValue = "") String rname,
            @RequestParam(value = "passtype", required = false, defaultValue = "") Long passtype) {

        Passport passport = null;
        if (id != null) {
            passport = passportService.select(id);
        }

        if (passport == null) {
            passport = new Passport();
        }

        passport.setParId(parId);
        passport.setRname(rname.trim());
        passport.setKname(rname.trim());
        passport.setEname(rname.trim());
        passport.setPasstype(passtype);

        passportService.save(passport);

        return "redirect:/passport";
    }

    @RequestMapping("/delete-passport")
    public String deletePassport(@RequestParam(value = "id", required = true) Long id) {

        passportService.delete(id);
        return "redirect:/passport";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Indicator">
    @RequestMapping("/indicator")
    public String indicator(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        List<Indicator> indicators = indicatorService.list();

        map.put("wrapList", indicators);

        Indicator indicator;
        if (id != null) {
            indicator = indicatorService.select(id);
            if (indicator == null) {
                indicator = new Indicator();
            }
        } else {
            indicator = new Indicator();
        }
        map.put("indicator", indicator);

        return "indicator";
    }

    @RequestMapping(value = "/save-indicator", method = RequestMethod.POST)
    public String saveIndicator(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            //            @RequestParam(value = "passId", required = false, defaultValue = "") Long passId,
            @RequestParam(value = "rname", required = false, defaultValue = "") String rname) {

        Indicator indicator = null;
        if (id != null) {
            indicator = indicatorService.select(id);
        }

        if (indicator == null) {
            indicator = new Indicator();
        }

//        indicator.setPassId(passId);
        indicator.setRname(rname.trim());
        indicator.setKname(rname.trim());
        indicator.setEname(rname.trim());

        indicatorService.save(indicator);

        return "redirect:/indicator";
    }

    @RequestMapping(value = "/delete-indicator/{indicatorID}")
    public String deleteIndicator(@PathVariable Long indicatorID) {

        indicatorService.delete(indicatorID);
        return "redirect:/indicator";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Measure">
    @RequestMapping("/measure")
    public String measure(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {

        map.put("wrapList", measureService.list());

        Measure measure;
        if (id != null) {
            measure = measureService.select(id);
            if (measure == null) {
                measure = new Measure();
            }
        } else {
            measure = new Measure();
        }

        map.put("measure", measure);

        return "measure";
    }

    @RequestMapping(value = "/save-measure", method = RequestMethod.POST)
    public String saveMeasure(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            @RequestParam(value = "parId", required = false, defaultValue = "") Long parId,
            @RequestParam(value = "rname", required = false, defaultValue = "") String rname,
            @RequestParam(value = "shortRname", required = false, defaultValue = "") String shortRname,
            @RequestParam(value = "multiplier", required = false, defaultValue = "") Double multiplier) {

        Measure measure = null;

        if (id != null) {
            measure = measureService.select(id);
        }

        if (measure == null) {
            measure = new Measure();
        }

        measure.setParId(parId);
        measure.setRname(rname.trim());
        measure.setKname(rname.trim());
        measure.setEname(rname.trim());
        measure.setShortRname(shortRname.trim());
        measure.setShortEname(shortRname.trim());
        measure.setShortKname(shortRname.trim());
        measure.setMultiplier(multiplier);

        measureService.save(measure);

        return "redirect:/measure";
    }

    @RequestMapping("/delete-measure")
    public String deleteMeasure(@RequestParam(value = "id", required = true) Long id) {

        measureService.delete(id);
        return "redirect:/measure";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PassIndLink">
    @RequestMapping("/pass-ind-link")
    public String passIndLink(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        List<Passport> passports = passportService.list();
        List<Indicator> indicators = indicatorService.list();
        List<Measure> measures = measureService.list();
        List<PassIndLink> passIndLinks = passIndLinkService.list();

        map.put("indicatorList", IndicatorWrapper.wrapList(indicators));
        map.put("measureList", MeasureWrapper.wrapList(measures));
        map.put("passportTree", PassportWrapper.buildTree(PassportWrapper.wrapList(passports)));
        map.put("wrapList", PassIndLinkWrapper.wrapList(passIndLinks, passports, indicators, measures));

        PassIndLink passIndLink;
        if (id != null) {
            passIndLink = passIndLinkService.select(id);
            if (passIndLink == null) {
                passIndLink = new PassIndLink();
            }
        } else {
            passIndLink = new PassIndLink();
        }
        map.put("passIndLink", passIndLink);

        return "pass-ind-link";
    }

    @RequestMapping(value = "/save-pass-ind-link", method = RequestMethod.POST)
    public String savePassIndLink(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            @RequestParam(value = "indId", required = false, defaultValue = "") Long indId,
            @RequestParam(value = "passId", required = false, defaultValue = "") Long passId,
            @RequestParam(value = "measureId", required = false, defaultValue = "") Long measureId,
            @RequestParam(value = "datatype", required = false, defaultValue = "") int datatype,
            @RequestParam(value = "dataformat", required = false, defaultValue = "") String dataformat,
            @RequestParam(value = "ordernum", required = false, defaultValue = "") int ordernum,
            @RequestParam(value = "tag", required = false, defaultValue = "") int tag,
            @RequestParam(value = "dataFormatRmessage", required = false, defaultValue = "") String dataFormatRmessage,
            @RequestParam(value = "dataFormatKmessage", required = false, defaultValue = "") String dataFormatKmessage,
            @RequestParam(value = "dataFormatEmessage", required = false, defaultValue = "") String dataFormatEmessage) {

        PassIndLink passIndLink = null;

        if (id != null) {
            passIndLink = passIndLinkService.select(id);
        }

        if (passIndLink == null) {
            passIndLink = new PassIndLink();
        }

        passIndLink.setDatatype(datatype);
        passIndLink.setIndId(indId);
        passIndLink.setPassId(passId);
        passIndLink.setMeasureId(measureId);
        passIndLink.setDataformat(dataformat);
        passIndLink.setOrdernum(ordernum);
        passIndLink.setTag(tag);
        passIndLink.setDataFormatEmessage(dataFormatEmessage);
        passIndLink.setDataFormatKmessage(dataFormatKmessage);
        passIndLink.setDataFormatRmessage(dataFormatRmessage);
        passIndLinkService.save(passIndLink);

        return "redirect:/pass-ind-link";
    }

    @RequestMapping("/delete-pass-ind-link")
    public String deletePassIndLink(@RequestParam(value = "id", required = true) Long id) {

        passIndLinkService.delete(id);
        return "redirect:/pass-ind-link";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="PassIndSpLink">
    @RequestMapping("/pass-ind-sp-link")
    public String passIndSpLink(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        map.put("edit", messageSource.getMessage("label.edit", null, "Изменить", LocaleContextHolder.getLocale()));
        map.put("delete", messageSource.getMessage("label.delete", null, "Удалить", LocaleContextHolder.getLocale()));
        map.put("horizontal", messageSource.getMessage("item.horizontal", null, "Горизонталь", LocaleContextHolder.getLocale()));
        map.put("vertical", messageSource.getMessage("item.vertical", null, "Вертикаль", LocaleContextHolder.getLocale()));

        List<Passport> passports = passportService.list();
        List<Indicator> indicators = indicatorService.list();
        List<Measure> measures = measureService.list();
        List<SPClass> spClasses = spClassService.list();
        List<PassIndLink> passIndLinks = passIndLinkService.list();
        List<PassIndSpLink> passIndSpLinks = passIndSpLinkService.list();

        List<PassIndLinkWrapper> passIndLinkWrappers = PassIndLinkWrapper.wrapList(passIndLinks, passports, indicators, measures);
        List<PassIndSpLinkWrapper> passIndSpLinkWrappers = PassIndSpLinkWrapper.wrapList(passIndSpLinks, passIndLinkWrappers, spClasses);
        List<PassIndSpLinkWrapper> wrapTree = PassIndSpLinkWrapper.buildTree(passIndSpLinkWrappers, spClasses);

        map.put("spClassList", SPClassWrapper.wrapList(spClasses));
        map.put("passIndLinkWrapList", passIndLinkWrappers);
        map.put("wrapList", wrapTree);

        PassIndSpLink passIndSpLink;
        if (id != null) {
            passIndSpLink = passIndSpLinkService.select(id);
            if (passIndSpLink == null) {
                passIndSpLink = new PassIndSpLink();
                map.put("wrapTree", wrapTree);
            } else {
                map.put("wrapTree", PassIndSpLinkWrapper.availableItems(passIndSpLinkWrappers, passIndSpLink.getId()));
            }
        } else {
            passIndSpLink = new PassIndSpLink();
            map.put("wrapTree", wrapTree);
        }
        map.put("passIndSpLink", passIndSpLink);

        return "pass-ind-sp-link";
    }

    @RequestMapping(value = "/save-pass-ind-sp-link", method = RequestMethod.POST)
    public String savePassIndSpLink(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            @RequestParam(value = "parId", required = false, defaultValue = "") Long parId,
            @RequestParam(value = "passIndLinkId", required = false, defaultValue = "") Long passIndLinkId,
            @RequestParam(value = "spId", required = false, defaultValue = "") Long spId,
            @RequestParam(value = "orient", required = false, defaultValue = "") Boolean orient) {

        PassIndSpLink passIndSpLink = null;
        if (id != null) {
            passIndSpLink = passIndSpLinkService.select(id);
        }

        if (passIndSpLink == null) {
            passIndSpLink = new PassIndSpLink();
        }

        passIndSpLink.setParId(parId);
        passIndSpLink.setPassIndLinkId(passIndLinkId);
        passIndSpLink.setSpId(spId);
        passIndSpLink.setOrient(orient == null ? null : (orient ? 1L : 0L));

        passIndSpLinkService.save(passIndSpLink);

        return "redirect:/pass-ind-sp-link";
    }

    @RequestMapping("delete-pass-ind-sp-link")
    public String deletePassIndSpLink(@RequestParam(value = "id", required = true) Long id) {

        passIndSpLinkService.delete(id);
        return "redirect:/pass-ind-sp-link";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OrgTypeCitLink">
    @RequestMapping("org-type-cit-link")
    public String orgTypeCitLink(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {
        map.put("delete", messageSource.getMessage("label.delete", null, "Удалить", LocaleContextHolder.getLocale()));

        List<OrgTypeCitLink> orgTypeCitLinks = orgTypeCitLinkService.list();
        List<ClassItemTree> classItemTrees = classItemTreeService.listOrgTypeCitLink();
        List<ClassItemTree> otClassItemTrees = classItemTreeService.list(80L);
        List<SPClass> spClasses = spClassService.list();

        List<ClassItemTreeWrapper> classItemTreeWrappers = ClassItemTreeWrapper.wrapList(classItemTrees, spClasses);
        List<ClassItemTreeWrapper> otClassItemTreeWrappers = ClassItemTreeWrapper.wrapList(otClassItemTrees, spClasses);

        map.put("classItemTrees", classItemTreeWrappers);
        //ClassItemTreeWrapper.buildTree(classItemTreeWrappers, spClasses)
        map.put("otClassItemTrees", otClassItemTreeWrappers);
        map.put("wrapList", OrgTypeCitLinkWrapper.wrapList(orgTypeCitLinks, classItemTrees, otClassItemTrees));

        OrgTypeCitLink orgTypeCitLink;
        if (id != null) {
            orgTypeCitLink = orgTypeCitLinkService.select(id);
            if (orgTypeCitLink == null) {
                orgTypeCitLink = new OrgTypeCitLink();
            }
        } else {
            orgTypeCitLink = new OrgTypeCitLink();
        }
        map.put("orgTypeCitLink", orgTypeCitLink);

        return "org-type-cit-link";
    }

    @RequestMapping(value = "/save-org-type-cit-link", method = RequestMethod.POST)
    public String saveOrgTypeCitLink(
            @RequestParam(value = "id", required = false, defaultValue = "") Long id,
            @RequestParam(value = "classItemTreeId", required = false, defaultValue = "") Long classItemTreeId,
            @RequestParam(value = "otClassItemTreeId", required = false, defaultValue = "") Long otClassItemTreeId) {

        OrgTypeCitLink orgTypeCitLink = null;
        if (id != null) {
            orgTypeCitLink = orgTypeCitLinkService.select(id);
        }

        if (orgTypeCitLink == null) {
            orgTypeCitLink = new OrgTypeCitLink();
        }

        orgTypeCitLink.setClassItemTreeId(classItemTreeId);
        orgTypeCitLink.setOtClassItemTreeId(otClassItemTreeId);

        orgTypeCitLinkService.save(orgTypeCitLink);

        return "redirect:/org-type-cit-link";
    }

    @RequestMapping("delete-org-type-cit-link")
    public String deleteOrgTypeCitLink(@RequestParam(value = "id", required = true) Long id) {

        orgTypeCitLinkService.delete(id);
        return "redirect:/org-type-cit-link";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="OrgTypeIndLink">
    @RequestMapping("org-type-ind-link")
    public String orgTypeIndLink(Map<String, Object> map, @RequestParam(value = "id", required = false, defaultValue = "") Long id) {

        map.put("wrapList", orgTypeIndLinkService.toTable());

        return "org-type-ind-link";
    }

    @RequestMapping(value = "edit-org-type-ind-link", method = RequestMethod.POST)
    public String editOrgTypeIndLink(Map<String, Object> map,
            @RequestParam(value = "passIndLinkId", required = true) Long passIndLinkId,
            final HttpServletRequest request) {
        
        orgTypeIndLinkService.edit(passIndLinkId, request.getParameterMap());
        
        return  "redirect:/org-type-ind-link";
    }
    //</editor-fold>
}
