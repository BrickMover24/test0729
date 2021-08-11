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
    <form action="/act/page.json" method="get">
        名称：<input name="data['name']" /><br />
        当前页：<input name="page['currentPage']" /><br />
        每页条数：<input name="page['rowsPerPage']" /><br />
        <button>提交</button>
    </form>
</body>
</html>
