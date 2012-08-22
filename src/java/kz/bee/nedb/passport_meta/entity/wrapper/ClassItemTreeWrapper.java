package kz.bee.nedb.passport_meta.entity.wrapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import kz.bee.nedb.passport_meta.entity.ClassItemTree;
import kz.bee.nedb.passport_meta.entity.SPClass;

public class ClassItemTreeWrapper extends ClassItemTree {
    private List<ClassItemTreeWrapper> children = new ArrayList<ClassItemTreeWrapper>();
    private SPClass spClass = null;
    
    public static List<ClassItemTreeWrapper> wrapList(List<ClassItemTree> classItemTrees, List<SPClass> spClasses) {
        if ((classItemTrees == null) || classItemTrees.isEmpty()) {
            return null;
        } else {
            HashMap<Long, SPClass> spClassMap = new HashMap<Long, SPClass>();
            for (SPClass spClass: spClasses) {
                spClassMap.put(spClass.getId(), spClass);
            }
            
            ArrayList<ClassItemTreeWrapper> classItemTreeWrappers = new ArrayList<ClassItemTreeWrapper>();
            for (ClassItemTree classItemTree: classItemTrees) {
                classItemTreeWrappers.add(new ClassItemTreeWrapper(classItemTree, spClassMap.get(classItemTree.getSpId())));
            }
//            TreeMap<Long, ClassItemTreeWrapper> classItemTreeWrapperMap = new TreeMap<Long, ClassItemTreeWrapper>();
//            for (ClassItemTree classItemTree: classItemTrees) {
//                classItemTreeWrapperMap.put(classItemTree.getId(), new ClassItemTreeWrapper(classItemTree, spClassMap.get(classItemTree.getSpId())));
//            }
            
//            ArrayList<ClassItemTreeWrapper> result = new ArrayList<ClassItemTreeWrapper>(classItemTreeWrapperMap.values());
            
//            for (ClassItemTreeWrapper classItemTreeWrapper: result) {
//                System.out.println("--------------" + classItemTreeWrapper.getId());
//            }
            
            return classItemTreeWrappers;
        }
    }
    
    public static List<ClassItemTreeWrapper> buildTree(List<ClassItemTreeWrapper> classItemTreeWrappers, List<SPClass> spClasses) {
        if ((classItemTreeWrappers == null) || classItemTreeWrappers.isEmpty()) {
            return null;
        } else {
            TreeMap<Long, ClassItemTreeWrapper> classItemTreeWrapperMap = new TreeMap<Long, ClassItemTreeWrapper>();
            for (ClassItemTreeWrapper classItemTreeWrapper: classItemTreeWrappers) {
                if ((classItemTreeWrapper != null) && (classItemTreeWrapper.getId() != null)) {
                    classItemTreeWrapperMap.put(classItemTreeWrapper.getId(), new ClassItemTreeWrapper(classItemTreeWrapper));
                }
            }
            
            ArrayList<ClassItemTreeWrapper> result = new ArrayList<ClassItemTreeWrapper>();
            for (ClassItemTreeWrapper classItemTreeWrapper: classItemTreeWrapperMap.values()) {
                if (classItemTreeWrapper.getParId() == null) {
                    result.add(classItemTreeWrapper);
                } else {
                    ClassItemTreeWrapper parentClassItemTreeWrapper = classItemTreeWrapperMap.get(classItemTreeWrapper.getParId());
                    if (parentClassItemTreeWrapper != null) {
                        parentClassItemTreeWrapper.children.add(classItemTreeWrapper);
                    }
                }
            }
            
            return result;
        }
    }
    
    public static List<ClassItemTreeWrapper> availableItems(List<ClassItemTreeWrapper> classItemTreeWrappers, Long excludedId) {
        if ((classItemTreeWrappers == null) || classItemTreeWrappers.isEmpty() || (excludedId == null)) {
            return null;
        } else {
            ArrayList<ClassItemTreeWrapper> result = new ArrayList<ClassItemTreeWrapper>();
            
            for (ClassItemTreeWrapper classItemTreeWrapper: classItemTreeWrappers) {
                if (!excludedId.equals(classItemTreeWrapper.getId())) {
                    result.add(classItemTreeWrapper);
                    List<ClassItemTreeWrapper> children = availableItems(classItemTreeWrapper.children, excludedId);
                    if ((children != null) && !children.isEmpty()) {
                        result.addAll(children);
                    }
                }
            }
            
            TreeMap<Long, ClassItemTreeWrapper> classItemTreeWrapperMap = new TreeMap<Long, ClassItemTreeWrapper>();
            for (ClassItemTreeWrapper classItemTreeWrapper: result) {
                classItemTreeWrapperMap.put(classItemTreeWrapper.getId(), classItemTreeWrapper);
            }
            
            result = new ArrayList<ClassItemTreeWrapper>(classItemTreeWrapperMap.values());
            
//            for (ClassItemTreeWrapper classItemTreeWrapper: result) {
//                System.out.println("--------------" + classItemTreeWrapper.getId());
//            }
            
            return result;
        }
    }
    
    public ClassItemTreeWrapper(ClassItemTree classItemTree) {
        super();
        
        this.setId(classItemTree.getId());
        this.setParId(classItemTree.getParId());
        this.setSpId(classItemTree.getSpId());
        this.setRname(classItemTree.getRname());
        this.setKname(classItemTree.getKname());
        this.setEname(classItemTree.getEname());
        this.setBegDate(classItemTree.getBegDate());
        this.setEndDate(classItemTree.getEndDate());
        this.setUpdTime(classItemTree.getUpdTime());
        this.setTreeId(classItemTree.getTreeId());
        this.setCode(classItemTree.getCode());
        this.setAttr1(classItemTree.getAttr1());
    }
    
    public ClassItemTreeWrapper(ClassItemTree classItemTree, SPClass spClass) {
        super();
        
        this.setId(classItemTree.getId());
        this.setParId(classItemTree.getParId());
        this.setSpId(classItemTree.getSpId());
        this.setRname(classItemTree.getRname());
        this.setKname(classItemTree.getKname());
        this.setEname(classItemTree.getEname());
        this.setBegDate(classItemTree.getBegDate());
        this.setEndDate(classItemTree.getEndDate());
        this.setUpdTime(classItemTree.getUpdTime());
        this.setTreeId(classItemTree.getTreeId());
        this.setCode(classItemTree.getCode());
        this.setAttr1(classItemTree.getAttr1());
        this.spClass = spClass;
    }
    
    public ClassItemTreeWrapper(ClassItemTreeWrapper classItemTreeWrapper) {
        super();
        
        this.setId(classItemTreeWrapper.getId());
        this.setParId(classItemTreeWrapper.getParId());
        this.setSpId(classItemTreeWrapper.getSpId());
        this.setRname(classItemTreeWrapper.getRname());
        this.setKname(classItemTreeWrapper.getKname());
        this.setEname(classItemTreeWrapper.getEname());
        this.setBegDate(classItemTreeWrapper.getBegDate());
        this.setEndDate(classItemTreeWrapper.getEndDate());
        this.setUpdTime(classItemTreeWrapper.getUpdTime());
        this.setTreeId(classItemTreeWrapper.getTreeId());
        this.setCode(classItemTreeWrapper.getCode());
        this.setAttr1(classItemTreeWrapper.getAttr1());
        this.spClass = classItemTreeWrapper.spClass;
    }
    
    public List<ClassItemTreeWrapper> getChildren() {
        return children;
    }
    
    public SPClass getSPClass() {
        return spClass;
    }
    
    public String getNameByLang(String lang) {
        if ("kk".equals(lang)) {
            return getKname();
        } else if ("en".equals(lang)) {
            return getEname();
        } else {
            return getRname();
        }
    }
    
    public String toHTML(int level, String lang, String cssClass, String editString, String deleteString, String cityString, String villageString, String[] k, SimpleDateFormat dateFormat, SimpleDateFormat dateTimeFormat) {
        StringBuilder stringBuilder = new StringBuilder("<tr>");
        
        stringBuilder
                .append("<td>");
        for (int i = 0; i < level; i++) {
            stringBuilder.append("&nbsp;&nbsp;");
        }
        
        if (level > 0) {
            stringBuilder.append("|-");
        }
        
        stringBuilder
                .append(getId() == null ? "" : getId().toString())
                .append("</td><td>")
                .append(getTreeId() == null ? "" : getTreeId().toString())
                .append("</td><td>")
                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getCode() == null ? "" : getCode())
                .append("</a></td><td>")
                
                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getRname())
                .append("</a></td><td>")

                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getKname())
                .append("</a></td><td>")

                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getEname())
                .append("</a></td><td>")

                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getBegDate() == null ? "" : dateFormat.format(getBegDate()))
                .append("</a></td><td>")

                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getEndDate() == null ? "" : dateFormat.format(getEndDate()))
                .append("</a></td><td>")

                .append("<a href=\"?spId=")
                .append(getSpId() == null ? "" : getSpId().toString())
                .append("&id=")
                .append(getId() == null ? "" : getId().toString())
                .append("\">")
                .append(getUpdTime() == null ? "" : dateTimeFormat.format(getUpdTime()))
                .append("</a></td><td>");

        if (Long.valueOf(60L).equals(getSpId())) {
            stringBuilder
                .append(getAttr1() == null ? "" : k[getAttr1().intValue()])
                .append("</td><td>");
        }
//        stringBuilder
//                .append("<a href=\"delete-class-item-tree?spId=")
//                .append(getSpId() == null ? "" : getSpId().toString())
//                .append("&id=")
//                .append(getId() == null ? "" : getId().toString())
//                .append("\">")
//                .append(deleteString)
//                .append("</a></td></tr>");
        
        if ((children != null) && !children.isEmpty()) {
            for (ClassItemTreeWrapper child: children) {
                stringBuilder.append(child.toHTML(level + 1, lang, cssClass, editString, deleteString, cityString, villageString, k, dateFormat, dateTimeFormat));
            }
        }
        
        return stringBuilder.toString();
    }
    
    public String toOption(int level, Long selectedId) {
        StringBuilder stringBuilder = new StringBuilder("<option ")
                .append(getId().equals(selectedId) ? " selected " : "")                
                .append(" value=\"")
                .append(getId() == null ? "" : getId().toString())
                .append("\">");
        
        for (int i = 0; i < level; i++) {
            stringBuilder.append("&nbsp;&nbsp;");
        }
        
        if (level > 0) {
            stringBuilder.append("|-");
        }
        
        stringBuilder
                .append(getSPClass().getRname())
                .append("&nbsp;&raquo;&nbsp;")
                .append(getCode())                
                .append("&nbsp;&raquo;&nbsp;")
                .append(getRname())
                .append("</option>");
                
        if ((children != null) && !children.isEmpty()) {
            for (ClassItemTreeWrapper child: children) {
                stringBuilder.append(child.toOption((level + 1), selectedId));
            }
        }
        
        return stringBuilder.toString();
    }
}