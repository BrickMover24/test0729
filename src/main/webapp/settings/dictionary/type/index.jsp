<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

	<meta charset="UTF-8">
	<!--禁用静态页面缓存-->
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Expires" content="0" />

<link href="/static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/static/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script>
	jQuery(function ($) {
       $.ajax({
		   url:"type/list.json",
		   success(data){
		   	var arr=[];
		   	$(data).each(function (i) {
                arr.push(`<tr>
                           <td><input name="code" value="`+this.code+`" type="checkbox" /></td>
                           <td>`+(i+1)+`</td>
                           <td>`+this.code+`</td>
                           <td>`+this.name+`</td>
                           <td>`+this.description+`</td>
                          </tr>`)
			})
			   $("#dataBody").html(arr.join(""));
		   }
	   });

		$("#editBtn").click(function () {
			// 获取选中的复选框
			var $cks = $(":checkbox[name=code]:checked");
			if ( $cks.size() != 1 ) {
				alert("必须且只能选择一项！");
				return ;
			}
			location = "edit.jsp?code=" + $cks.val();
		})
	})

</script>

</head>
<body>

	<div>
		<div style="position: relative; left: 30px; top: -10px;">
			<div class="page-header">
				<h3>字典类型列表</h3>
			</div>
		</div>
	</div>
	<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
		<div class="btn-group" style="position: relative; top: 18%;">
		  <button type="button" class="btn btn-primary" onclick="window.location.href='save.jsp'"><span class="glyphicon glyphicon-plus"></span> 创建</button>
		  <button id="editBtn" type="button" class="btn btn-default" onclick="window.location.href='edit.jsp'"><span class="glyphicon glyphicon-edit"></span> 编辑</button>
		  <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
		</div>
	</div>
	<div style="position: relative; left: 30px; top: 20px;">
		<table class="table table-hover">
			<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" /></td>
					<td>序号</td>
					<td>编码</td>
					<td>名称</td>
					<td>描述</td>
				</tr>
			</thead>
			<tbody id="dataBody"></tbody>
		</table>
	</div>
</body>
</html>