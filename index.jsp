<%@ page contentType="text/html;charset=utf-8" language="java" errorPage="" %>
<%@ page import="java.util.*" %>
<%@ page import="pub.*" %>
<%@ page import="pri.*" %>
<%@ page import="tools.*" %>

<%
    IndexProcess.processPub(request, response);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>首页</title>
        <meta charset="utf-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="/css/index.css">
    </head>
    <body>
        <div class="container">
            <div id="header" class="text-right">
                <div id="message">
                       
                    <div id="logined" class='${sessionScope.username==null?"hidden":""}'>
                        <div class="btn btn-info">${sessionScope.username}</div>
                        <a class="btn btn-warning" href="/user/logout">注销</a>
                    </div>
                    <div id="unlogin" class='${sessionScope.username==null?"":"hidden"}'>
                        <a class="btn btn-primary" href="/user/login">登录</a>
                    </div>
                </div>
            </div>
            <div id="main">
                <ul class="nav nav-tabs">
                    <li id="public" role="presentation" class="active"><a href="/">公共</a></li>
                    <li id="private" role="presentation" class='${sessionScope.username==null?"hidden":""}'><a href="/pri">私有</a></li>
                </ul>
            </div>
            <div>
                <div id="pub">
                    <table class="table table-bordered" id="pub-table">
                        <thead>
                            <tr class="row">
                                <th class="col-md-1 text-center">#</th>
                                <th class="col-md-6 text-center">文件名</th>
                                <th class="col-md-1 text-center">拥有者</th>
                                <th class="col-md-1 text-center">大小</th>
                                <th class="col-md-3 text-center">选项</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                if(request.getAttribute("pubfiles") != null) {
                                    List<PublicFile> pubFiles = (List<PublicFile>)request.getAttribute("pubfiles");
                                    for(int i = 0; i < pubFiles.size(); i++) {
                                        PublicFile pubFile = pubFiles.get(i);
                                        String optionDown = "<a class='btn btn-primary' href='/down/pub/" + pubFile.getId() + "'>下载</a>";
                                        String optionDel = "<a class='btn btn-primary' href='/del/pub/" + pubFile.getId() + "'>删除</a>";
                                        if(session.getAttribute("username") == null || !session.getAttribute("username").equals(pubFile.getOwner())) {
                                            optionDel = "";
                                        }
                                        out.println("<tr class='row'>");
                                        out.println("<th class='col-md-1 text-center'>" + pubFile.getId() + "</th>");
                                        out.println("<th class='col-md-6'>" + pubFile.getFileName() + "</th>");
                                        out.println("<th class='col-md-1 text-center'>" + pubFile.getOwner() + "</th>");
                                        out.println("<th class='col-md-1 text-center'>" + pubFile.getSize() + "</th>");
                                        out.println("<th class='col-md-3 text-center'>" + optionDown + " " + optionDel + "</th>");
                                        out.println("</tr>");
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                
                </div>
                
            </div>
            <div id="footer"></div>
        </div>

        <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
        <script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
        <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
        <!--js代码-->
        <script src="/script/index.js"></script>
    </body>
</html>