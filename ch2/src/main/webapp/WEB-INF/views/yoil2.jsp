<%--
  Created by IntelliJ IDEA.
  User: yuki
  Date: 2022/11/05
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <title>Home</title>
</head>
<body>
year = <%=request.getParameter("year")%>
<P> ${myDate.year }년 ${myDate.month }월 ${myDate.day }일은 ${yoil }입니다. </P>
</body>
</html>
