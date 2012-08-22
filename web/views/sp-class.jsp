<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="sp-class.title"/></title>

        <jsp:include page="templates/init.jsp"/>
        <jsp:include page="templates/styles.jsp"/>
        <jsp:include page="templates/scripts.jsp"/>

        <script type="text/javascript">
            jQuery(function(){
                jQuery('#sp-class').addClass('ahover').css("color", "#ed1e24");
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
                    <h2><spring:message code="label.editing-element"/>&nbsp;&Prime;<spring:message code="sp-class.title"/>&Prime;</h2>
                </div>
                <div class="box round">
                    <form action="sp-class-save" method="post">
                        <table id="enter">
                            <tr>
                                <td>
                                    <spring:message code="label.id"/>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty spClass.id}">
                                            <input type="text" disabled value="<spring:message code="label.new"/>"/>
                                            <input type="hidden" name="id" value=""/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" disabled value="${spClass.id}"/>
                                            <input type="hidden" name="id" value="${spClass.id}"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="label.rname"/>
                                </td>
                                <td>
                                    <input type="text" name="rname" value="${spClass.rname}" size="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="label.kname"/>
                                </td>
                                <td>
                                    <input type="text" name="kname" value="${spClass.kname}" size="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="label.ename"/>
                                </td>
                                <td>
                                    <input type="text" name="ename" value="${spClass.ename}" size="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    Tag
                                </td>
                                <td>
                                    <input type="text" name="tag" value="${spClass.tag}" size="50"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <span class="float-right">
                                        <input type="submit" value="<spring:message code="label.save"/>"/>
                                    </span>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>                    

                <div class="elembox">
                    <c:choose>
                        <c:when test="${empty spClasses}">
                            <spring:message code="label.no-elements"/>
                        </c:when>
                        <c:otherwise>
                            <table class="datatable">
                                <caption><spring:message code="label.table-of-elements"/></caption>
                                <thead>
                                    <tr>
                                        <th><spring:message code="label.id"/></th>
                                        <th><spring:message code="label.rname"/></th>
                                        <th><spring:message code="label.kname"/></th>
                                        <th><spring:message code="label.ename"/></th>
                                        <th>Tag</th>
                                        <th><spring:message code="label.action"/></th>
                                    </tr>
                                </thead>
                                <c:forEach items="${spClasses}" var="spClassItem">
                                    <tr>
                                        <td>${spClassItem.id}</td>
                                        <td><a href="?id=${spClassItem.id}">${spClassItem.rname}</a></td>
                                        <td><a href="?id=${spClassItem.id}">${spClassItem.kname}</a></td>
                                        <td><a href="?id=${spClassItem.id}">${spClassItem.ename}</a></td>
                                        <td><a href="?id=${spClassItem.id}">${spClassItem.tag}</a></td>

                                        <td>
                                            <a href="sp-class-delete?id=${spClassItem.id}"><spring:message code="label.delete"/></a>
                                        </td>
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