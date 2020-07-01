<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>

<!DOCTYPE html>
<html>
    <head>
        <title>登陆</title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/css/login.css"/>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>

    <body>
        <div class="container">
            <div id="main">
                <div><p id="title" class="h3 text-center">用户登录</p></div>
                <hr>
                <form id="login" class="form-horizontal" method="POST" action="/user/loginprocess">
                    <div class="form-group">
                        <label for="user" class="col-md-4 control-label">用户名：</label>
                        <div class="col-md-8">
                            <input class="form-control" type="text" name="user" id="user"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-md-4 control-label">密码：</label>
                        <div class="col-md-8">
                            <input class="form-control" type="password" name="password" id="password"/>
                        </div>
                    </div>
                    <div class='form-group ${requestScope.errMsg!=null?"show":"hidden"}'>
                        <label for="tip" class="col-md-4 control-label">提示：</label>
                        <div class="col-md-8">
                            <p class="form-control-static">${requestScope.errMsg}</p>
                        </div>
                    </div>
                    <hr>
                    <div id="submit">
                        <input type="submit" class="btn btn-default" value="提交"/>
                    </div>
                </form>
            </div>
        </div>
        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
        <!--js代码-->
        <script src="/script/login.js"></script>
    </body>
</html>