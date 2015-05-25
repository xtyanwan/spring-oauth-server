<%--
 * 
 * @author Shengzhao Li
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>client_details</title>

    <style>
        .list-group li:hover {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
<a href="./">Home</a>

<div class="row">
    <div class="col-md-10">
        <h3>client_details</h3>
    </div>
    <div class="col-md-2">
        <div class="pull-right">
            <a href="register_client" class="btn btn-success btn-sm">注册client</a>
        </div>
    </div>
</div>

<hr/>

<div>
    <ul class="list-group">
        <li class="list-group-item">
            <div class="pull-right">
                <a href="#">test</a>
                <a href="#" class="text-danger">delete</a>
            </div>
            <h3 class="list-group-item-heading">
                mobile-client
                <small>password,refresh_token</small>
            </h3>

            <div class="list-group-item-text text-muted">
                client_id: <span class="text-danger">mobile-client</span>&nbsp;
                client_secret: <span class="text-primary">mobile</span>&nbsp;
                <br/>
                authorized_grant_types: <span class="text-primary">password,refresh_token</span>&nbsp;
                resource_ids: <span class="text-primary">mobile-resource</span>&nbsp;
                <br/>
                scope: <span class="text-primary">read,write</span>&nbsp;
                web_server_redirect_uri: <span class="text-primary">...</span>&nbsp;
                <br/>
                authorities: <span class="text-primary">ROLE_CLIENT</span>&nbsp;
                access_token_validity: <span class="text-primary">...</span>&nbsp;
                refresh_token_validity: <span class="text-primary">...</span>&nbsp;
                <br/>
                create_time: <span class="text-primary">2015-05-08 21:12:22</span>&nbsp;
                archived: <span class="text-primary">false</span>&nbsp;
                trusted: <span class="text-primary">false</span>&nbsp;
                additional_information: <span class="text-primary">...</span>&nbsp;
            </div>
        </li>
        <li class="list-group-item">
            <div class="pull-right">
                <a href="#">test</a>
                <a href="#" class="text-danger">delete</a>
            </div>
            <h3 class="list-group-item-heading">
                unity-client
                <small>authorization_code,refresh_token,implicit</small>
            </h3>

            <div class="list-group-item-text text-muted">
                client_id: <span class="text-danger">unity-client</span>&nbsp;
                client_secret: <span class="text-primary">unity</span>&nbsp;
                <br/>
                authorized_grant_types: <span class="text-primary">authorization_code,refresh_token,implicit</span>&nbsp;
                resource_ids: <span class="text-primary">unity-resource</span>&nbsp;
                <br/>
                scope: <span class="text-primary">read,write</span>&nbsp;
                web_server_redirect_uri: <span class="text-primary">...</span>&nbsp;
                <br/>
                authorities: <span class="text-primary">ROLE_CLIENT</span>&nbsp;
                access_token_validity: <span class="text-primary">...</span>&nbsp;
                refresh_token_validity: <span class="text-primary">...</span>&nbsp;
                <br/>
                create_time: <span class="text-primary">2015-05-08 21:12:22</span>&nbsp;
                archived: <span class="text-primary">false</span>&nbsp;
                trusted: <span class="text-primary">false</span>&nbsp;
                additional_information: <span class="text-primary">...</span>&nbsp;
            </div>
        </li>
    </ul>
    <p class="help-block">
        每一个item对应<code>oauth_client_details</code>表中的一条数据.
    </p>
</div>
</body>
</html>