<%-- 
    Document   : passindlink
    Created on : Jul 5, 2012, 5:12:44 PM
    Author     : zhanat
--%>

<%@page language="java" contentType="text/html  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><spring:message code="pass-ind-link.title"/></title>

        <jsp:include page="templates/scripts.jsp"/>
        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/>

        <script type="text/javascript">
            jQuery(function(){
                jQuery('#pass-ind-link').addClass('ahover').css("color", "#ed1e24");
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
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="pass-ind-link.title"/>&Prime;</h2>
                </div>
                <div class="box round">
                    <form method="post" action="save-pass-ind-link">
                        <table id="enter"> 
                            <tr>
                                <td><spring:message code="label.id"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndLink.id}">
                                            <input type="text" disabled value="<spring:message code="label.new"/>"/>
                                            <input type="hidden" name="id" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" disabled value="${passIndLink.id}"/>
                                            <input type="hidden" name="id" value="${passIndLink.id}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <!--            indicator-->
                            <tr>
                                <td><spring:message code="indicator.title"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndLink.indId}">
                                            <select name="indId" >
                                                <option value="">...</option>
                                                <c:forEach items="${indicatorList}" var="item">
                                                    ${item.toOption(0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="indId" >
                                                <option value="">...</option>
                                                <c:forEach items="${indicatorList}" var="item">
                                                    ${item.toOption(passIndLink.indId)} 
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr>
                            <!--            passport-->
                            <tr>
                                <td><spring:message code="passport.title"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndLink.passId}">
                                            <select name="passId" >
                                                <option value="">...</option>
                                                <c:forEach items="${passportTree}" var="item">
                                                    ${item.toOption(0, 0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="passId" >
                                                <c:forEach items="${passportTree}" var="item">
                                                    ${item.toOption(0, passIndLink.passId)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr>
                            <!--            measure-->
                            <tr>
                                <td><spring:message code="measure.title"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty passIndLink.measureId}">
                                            <select name="measureId" >
                                                <option value="">...</option>
                                                <c:forEach items="${measureList}" var="item">
                                                    ${item.toOption(0)}
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <select name="measureId" >
                                                <option value="">...</option>
                                                <c:forEach items="${measureList}" var="item">
                                                    ${item.toOption(passIndLink.measureId)}
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>         
                                </td>
                            </tr>    
                            <tr>
                                <td><spring:message code="label.datatype"/></td>
                                <td><input type="text" name="datatype" value="${passIndLink.datatype}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.dataformat"/></td>
                                <td><input type="text" name="dataformat" value="${passIndLink.dataformat}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.ordernum"/></td>
                                <td><input type="text" name="ordernum" value="${passIndLink.ordernum}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.tag"/></td>
                                <td><input type="text" name="tag" value="${passIndLink.tag}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.data-format-rmessage"/></td>
                                <td><input type="text" name="dataFormatRmessage" value="${passIndLink.dataFormatRmessage}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.data-format-kmessage"/></td>
                                <td><input type="text" name="dataFormatKmessage" value="${passIndLink.dataFormatKmessage}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.data-format-emessage"/></td>
                                <td><input type="text" name="dataFormatEmessage" value="${passIndLink.dataFormatEmessage}"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="float-right"><input type="submit" value="<spring:message code="label.save"/>" /></span>
                                </td>                    
                            </tr>
                        </table>
                    </form>
                </div>                    

                <div class="elembox">
                    <c:choose>
                        <c:when test="${empty wrapList}">
                            <spring:message code="label.no-elements"/>
                        </c:when>
                        <c:otherwise>
                            <table class="datatable">
                                <caption><spring:message code="label.table-of-elements"/></caption>
                                <thead>
                                    <tr>
                                        <th><spring:message code="label.id"/></th>
                                        <th><spring:message code="passport.title"/></th>
                                        <th><spring:message code="indicator.title"/></th>
                                        <th><spring:message code="label.ordernum"/></th>
                                        <th><spring:message code="measure.title"/></th>
                                        <th><spring:message code="label.datatype"/></th>
                                        <th><spring:message code="label.dataformat"/></th>
                                        <th><spring:message code="label.tag"/></th>
                                        <th><spring:message code="label.data-format-rmessage"/></th>
                                        <th><spring:message code="label.data-format-kmessage"/></th>
                                        <th><spring:message code="label.data-format-emessage"/></th>
                                        <th><spring:message code="label.action"/></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${wrapList}" var="wrap">
                                    <tr>
                                        <td>${wrap.id}</td>
                                        <td><a href="?id=${wrap.id}">${wrap.passport.rname}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.indicator.rname}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.ordernum}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.measure.rname}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.datatype}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.dataformat}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.tag}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.dataFormatRmessage}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.dataFormatKmessage}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.dataFormatEmessage}</a></td>

                                        <td><a href="delete-pass-ind-link?id=${wrap.id}"><spring:message code="label.delete"/></a></td>
                                    </tr>
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
