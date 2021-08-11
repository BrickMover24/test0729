<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/8/3
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--data和page都是pojo中的map类型--%>
    <form action="/act/page2.json" method="get">
        <%-- 传统请求： search是map类型，必须使用['参数名']的方式传递参数 --%>
        名称：<input name="search['name']" /><br />
        当前页：<input name="currentPage" /><br />
        每页条数：<input name="rowsPerPage" /><br />
        <button>提交</button>
    </form>
</body>
</html>
