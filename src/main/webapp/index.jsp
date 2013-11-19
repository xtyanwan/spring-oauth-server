<%--
 * Copyright (c) 2013 Honyee Industry Group Co., Ltd
 * www.honyee.biz
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Honyee Industry Group Co., Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Honyee Industry Group Co., Ltd.
--%>
<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h3>spring-oauth is work!</h3>
<a href="${contextPath}/logout.do">Logout</a>
<ul>
    <li>
        <a href="${contextPath}/user/overview.htm">User</a>
    </li>
    <li>
        <a href="${contextPath}/unity/dashboard.htm">Unity</a>
    </li>
    <li>
        <a href="${contextPath}/m/dashboard.htm">Mobile</a>
    </li>
</ul>
</body>
</html>