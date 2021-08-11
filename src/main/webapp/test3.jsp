<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="/static/axios.min.js"></script>
    <script>
        var data = {
            currentPage: 1,
            rowsPerPage: 10,
            search: {
                name: "8"
            }
        };
        /*
        axios.get({
            url: "/act/page3.json",
            data,//: JSON.stringify(data),
            headers: {
                'X-Requested-With': 'XMLHttpRequest',
                "Content-Type": "application/json"
            }
        });*/
        var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
        xmlhttp.open("GET", "/act/page3.json");
        xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send(JSON.stringify(data));


        /*ajaxForm:监听表单的提交事件,当表单提交时,该插件会阻止表单的提交,然后从表单中提取
        action:url
        method:type
        所有包含name属性的表单元素:data
        ajaxForm中的函数:success*/


        /*$("#searchForm").ajaxForm(function (page) {
				var arr = [];
				$(page).each(function (i) {
					arr.push(`<tr class="`+(i%2==0?"active":"") +`">
									<td><input type="checkbox"/></td>
									<td><a style="text-decoration: none; cursor: pointer;"">` + this.name + `</a></td>
									<td>` + this.owner + `</td>
									<td>` + this.startDate + `</td>
									<td>` + this.endDate + `</td>
								</tr>`)
				})
				$("#dataBody").html(arr.join(""));

			}).submit();*/
    </script>
</head>
<body>

</body>
</html>
