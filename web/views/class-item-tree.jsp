<%@page import="org.springframework.context.support.MessageSourceResourceBundle"%>
<%@page import="kz.bee.nedb.passport_meta.entity.wrapper.ClassItemTreeWrapper"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="class-item-tree.title"/></title>

        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/>
        <jsp:include page="templates/scripts.jsp"/>

        <script type="text/javascript">
            (function($) {
                $(document).ready(function() {
                    $("#sp-class-select").bind("change", function() {
                        document.location.replace("?spId=" + $("#sp-class-select>option:selected").attr("value"))
                    });
                    
                    $("#begDate, #endDate").datepicker({
                        autoSize: true, // подгоняет размер поля ввода под формат даты
                        changeMonth: true, // возможность выбрать месяц
                        changeYear: true, // возможность выбрать год
                        constrainInput: true, // не дает вводить символы, которые не подходят по формату
                        dateFormat: "dd.mm.yy", // формат даты
                        firstDay: 1, // первый день - понедельник (воскресенье - 0)
                        navigationAsDateFormat: true, // навигация согласно формату даты (надо глянуть, что за чудо-навигация)
                        showButtonPanel: true, // показ кнопков
                        showWeek: true, // показ колонки недели
                        
            <jsp:include page="templates/jquery-datepicker-localization.jsp"/>
                        });
                    });
                })(jQuery);

                jQuery(function(){
                    jQuery('#class-item-tree').addClass('ahover').css("color", "#ed1e24");
                });            
        </script>     
    </head>

    <body>
        <div id="wspace">
            <div class="header">
                <jsp:include page="templates/language.jsp"/>
                <h1><a href="${startPath}"></a></h1>
            </div>
            <div class="menu"><jsp:include page="templates/navigation.jsp"/></div>
            <div class="content">

                <div class="panel">
                    <h2><spring:message code="label.dictionary"/></h2>
                </div>

                <div class="box round">
                    <c:choose>
                        <c:when test="${not empty spClasses}">
                            <select id="sp-class-select">
                                <c:if test="${empty selectedSPClass}">
                                    <option selected>...</option>
                                </c:if>
                                <c:forEach items="${spClasses}" var="spClass">
                                    <c:choose>
                                        <c:when test="${(not empty selectedSPClass) && (selectedSPClass.id eq spClass.id)}">
                                            <option value="${spClass.id}" selected>${spClass.getNameByLang(lang)}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${spClass.id}">${spClass.getNameByLang(lang)}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <spring:message code="label.no-elements"/>
                        </c:otherwise>
                    </c:choose>
                </div> 

                <div class="panel">
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="class-item-tree.title"/>&Prime;</h2>
                </div>
                <div class="box round">
                    <c:if test="${not empty selectedClassItemTree}">               
                        <form action="class-item-tree" method="get">
                            <input type="hidden" name="spId" value="${selectedSPClass.id}"/>
                            <input type="submit" value="<spring:message code="label.new"/>"/>
                        </form>

                        <form action="save-class-item-tree" method="post">
                            <table id="enter">
                                <tr>
                                    <td>
                                        <spring:message code="label.id"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${(not empty selectedClassItemTree) && (not empty selectedClassItemTree.id)}">
                                                <input type="hidden" name="id" value="${selectedClassItemTree.id}"/>
                                                <input type="text" disabled value="${selectedClassItemTree.id}"  size="50"/>
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="label.parId"/>
                                    </td>
                                    <td>
                                        <select name="parId">
                                            <c:choose>
                                                <c:when test="${empty selectedClassItemTree.parId}">
                                                    <option selected value=""><spring:message code="label.no-parent"/></option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value=""><spring:message code="label.no-parent"/></option>
                                                </c:otherwise>
                                            </c:choose>
                                            <c:if test="${not empty availableParents}">
                                                <c:forEach items="${availableParents}" var="parent">
                                                    <c:choose>
                                                        <c:when test="${parent.id eq selectedClassItemTree.parId}">
                                                            <option value="${parent.id}" selected>${parent.getNameByLang(lang)}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${parent.id}">${parent.getNameByLang(lang)}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="label.dictionary"/>
                                    </td>
                                    <td>
                                        <%--<input type="hidden" name="spId" value="${selectedSPClass.id}"/>
                                        <input type="text" disabled value="${selectedSPClass.getNameByLang(lang)}" size="50"/>--%>
                                        <select name="spId">
                                            <option value="">...</option>
                                            <c:forEach items="${spClasses}" var="spClass">
                                                <option value="${spClass.id}"
                                                        <c:if test="${(not empty selectedSPClass) && (selectedSPClass.id eq spClass.id)}">
                                                            selected
                                                        </c:if>
                                                        >${spClass.getNameByLang(lang)}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="label.code"/>
                                    </td>
                                    <td>
                                        <input type="text" name="code" value="${selectedClassItemTree.code}" size="50"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="label.rname"/>
                                    </td>
                                    <td>
                                        <input type="text" name="rname" value="${selectedClassItemTree.rname}" size="50"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="label.kname"/>
                                    </td>
                                    <td>
                                        <input type="text" name="kname" value="${selectedClassItemTree.kname}" size="50"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="label.ename"/>
                                    </td>
                                    <td>
                                        <input type="text" name="ename" value="${selectedClassItemTree.ename}" size="50"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="class-item-tree.field-beg-date"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty selectedClassItemTree.begDate}">
                                                <input type="text" name="begDate" id="begDate" value="${dateFormat.format(selectedClassItemTree.begDate)}" size="50"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" name="begDate" id="begDate" value="" size="50"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="class-item-tree.field-end-date"/>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${not empty selectedClassItemTree.endDate}">
                                                <input type="text" name="endDate" id="endDate" value="${dateFormat.format(selectedClassItemTree.endDate)}" size="50"/>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" name="endDate" id="endDate" value="" size="50"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                                <c:if test="${selectedSPClass.id eq 60}">
                                    <tr>
                                        <td>
                                            <spring:message code="class-item-tree.field-type-of-terrain"/>
                                        </td>
                                        <td>
                                            <select name="attr1">
                                                <option value="0"
                                                        <c:if test="${selectedClassItemTree.attr1 eq 0}">
                                                            selected
                                                        </c:if>
                                                        >
                                                    <spring:message code="class-item-tree.k-0"/>
                                                </option>
                                                <option value="1"
                                                        <c:if test="${selectedClassItemTree.attr1 eq 1}">
                                                            selected
                                                        </c:if>
                                                        >
                                                    <spring:message code="class-item-tree.k-1"/>
                                                </option>
                                                <option value="2"
                                                        <c:if test="${selectedClassItemTree.attr1 eq 2}">
                                                            selected
                                                        </c:if>
                                                        >
                                                    <spring:message code="class-item-tree.k-2"/>
                                                </option>
                                                <option value="3"
                                                        <c:if test="${selectedClassItemTree.attr1 eq 3}">
                                                            selected
                                                        </c:if>
                                                        >
                                                    <spring:message code="class-item-tree.k-3"/>
                                                </option>
                                                <option value="4"
                                                        <c:if test="${selectedClassItemTree.attr1 eq 4}">
                                                            selected
                                                        </c:if>
                                                        >
                                                    <spring:message code="class-item-tree.k-4"/>
                                                </option>
                                            </select>
                                        </td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td colspan="2">
                                        <span class="float-right">
                                            <input type="submit" value="<spring:message code="label.save"/>"/>
                                        </span>
                                    </td>
                                </tr>
                            </table>
                        </form>
                        <c:if test="${(not empty selectedSPClass.id) && (not empty selectedClassItemTree.id)}">
                            <hr/>
                            <form action="clone-class-item-tree" method="get">
                                <input type="hidden" name="spId" value="${selectedSPClass.id}"/>
                                <input type="hidden" name="id" value="${selectedClassItemTree.id}"/>
                                <table width="100%">
                                    <tr>
                                        <td width="100%">
                                            <span class="float-right">
                                                <input type="submit" value="<spring:message code="label.clone"/>"/>
                                            </span>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </c:if>
                    </c:if>
                </div> 

                <div class="elembox">
                    <c:choose>
                        <c:when test="${empty classItemTreeList}">
                            <spring:message code="label.no-elements"/>
                        </c:when>
                        <c:otherwise>
                            <table class="datatable">
                                <caption><spring:message code="label.table-of-elements"/></caption>
                                <thead>
                                    <tr>
                                        <th><spring:message code="label.id"/></th>
                                        <th><spring:message code="class-item-tree.field-tree-id"/></th>
                                        <th><spring:message code="label.code"/></th>
                                        <th><spring:message code="label.rname"/></th>
                                        <th><spring:message code="label.kname"/></th>
                                        <th><spring:message code="label.ename"/></th>
                                        <th><spring:message code="class-item-tree.field-beg-date"/></th>
                                        <th><spring:message code="class-item-tree.field-end-date"/></th>
                                        <th><spring:message code="class-item-tree.field-upd-time"/></th>
                                        <c:if test="${selectedSPClass.id eq 60}">
                                            <th><spring:message code="class-item-tree.field-type-of-terrain"/>
                                        </c:if>
                                        <%--<th><spring:message code="label.action"/></th>--%>
                                    </tr>
                                </thead>
                                <c:forEach items="${classItemTreeList}" var="classItemTree">
                                    ${classItemTree.toHTML(
                                      0,
                                      lang,
                                      "dotted-border",
                                      edit,
                                      delete,
                                      city,
                                      village,
                                      k,
                                      dateFormat,
                                      dateTimeFormat
                                      )}
                                </c:forEach>
                            </table>
                        </c:otherwise>
                    </c:choose>
                </div>          
            </div> <!--   content     -->
            <div id="footer">
                <jsp:include page="templates/footer.jsp"/>
            </div>
        </div> <!--   wspace     -->

    </body>
</html>