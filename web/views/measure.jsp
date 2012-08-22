<%-- 
    Document   : measure
    Created on : Jul 5, 2012, 5:12:44 PM
    Author     : zhanat
--%>

<%@page language="java" contentType="text/html  charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><spring:message code="measure.title"/></title>

        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/>
        <jsp:include page="templates/scripts.jsp"/>

        <script type="text/javascript">
            jQuery(function(){
                jQuery('#measure').addClass('ahover').css("color", "#ed1e24");
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
            <div id="content">

                <div class="panel">
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="measure.title"/>&Prime;</h2>
                </div>

                <div class="box round">
                    <form method="post" action="save-measure">
                        <table id="enter"> 
                            <tr>
                                <td><spring:message code="label.id"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty measure.id}">
                                            <input type="text" disabled value="<spring:message code="label.new"/>"/>
                                            <input type="hidden" name="id" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" disabled value="${measure.id}"/>
                                            <input type="hidden" name="id" value="${measure.id}"/>
                                        </c:otherwise>
                                    </c:choose>
                            </tr>
                            <tr>
                                <td><spring:message code="label.rname"/></td>
                                <td><input type="text" name="rname" value="${measure.rname}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.srname"/></td>
                                <td><input type="text" name="shortRname" value="${measure.shortRname}"/></td>
                            </tr>
                            <tr>
                                <td><spring:message code="label.multiplier"/></td>
                                <td><input type="text" name="multiplier" value="${measure.multiplier}"/></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="float-right"><input type="submit" value="<spring:message code="label.save"/>"/></span>
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
                                        <th><spring:message code="label.rname"/></th>
                                        <th><spring:message code="label.srname"/></th>                    
                                        <th><spring:message code="label.multiplier"/></th>
                                        <th><spring:message code="label.action"/></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${wrapList}" var="wrap">
                                    <tr>
                                        <td>${wrap.id}</td>
                                        <td><a href="?id=${wrap.id}">${wrap.rname}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.shortRname}</a></td>
                                        <td><a href="?id=${wrap.id}">${wrap.multiplier}</a></td>

                                        <td><a href="delete-measure?id=${wrap.id}"><spring:message code="label.delete"/></a></td>
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