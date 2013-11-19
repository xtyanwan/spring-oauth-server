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
    <title>Unity login</title>
</head>
<body>
<h3>Unity login</h3>

<form action="${contextPath}/login.do" method="post">

    <label for="username">Username:</label>
    <input type="text" id="username" name="j_username" value="admin"/>
    <br/>
    <br/>
    <label for="password">Password:</label>
    <input type="password" name="j_password" id="password" value="honyee2013"/>
    <br/>
    <input type="submit" value="Login"/>
</form>
</body>
</html>