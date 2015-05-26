<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>注册client</title>
</head>
<body>
<a href="./">Home</a>

<h2>注册client</h2>

<div>
    <form:form commandName="formDto" cssClass="form-horizontal">
        <div class="form-group">
            <label for="clientId" class="col-sm-2 control-label">client_id</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" name="clientId" id="clientId" placeholder="client_id"
                       required="required"/>

                <p class="help-block">client_id必须输入,且必须唯一; 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.</p>
            </div>
        </div>
        <div class="form-group">
            <label for="clientSecret" class="col-sm-2 control-label">client_secret</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" name="clientSecret" id="clientSecret"
                       placeholder="client_secret"
                       required="required"/>

                <p class="help-block">client_secret必须输入,且长度至少8位; 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.</p>
            </div>
        </div>
        <div class="form-group">
            <label for="resourceIds" class="col-sm-2 control-label">resource_ids</label>

            <div class="col-sm-10">
                <select class="form-control" name="resourceIds" id="resourceIds">
                    <option value="unity-resource">unity-resource</option>
                    <option value="mobile-resource">mobile-resource</option>
                    <option value="unity-resource,mobile-resource">unity-resource,mobile-resource</option>
                </select>

                <p class="help-block">resourceIds必须选择; 可选值必须来源于与<code>security.xml</code>中标签<code>&lsaquo;oauth2:resource-server</code>的属性<code>resource-id</code>值
                </p>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-2"></div>
            <div class="col-sm-10">
                <button type="submit" class="btn btn-success">注册</button>
                <a href="client_details">取消</a>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>