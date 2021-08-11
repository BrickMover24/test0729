<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/inc/commons-head.jsp" %>
	<script type="text/javascript">

		jQuery(function ($) {
			function pageList(currentPage, rowsPerPage) {
				var data = {
					currentPage: currentPage,
					rowsPerPage, // 相当于rowsPerPage: rowsPerPage
					// search在后台是一个map类型
					"search['owner']": $("#searchForm :input[name=owner]").val(),
					"search['name']": $("#searchForm :input[name=name]").val(),
					"search['customerName']": $("#searchForm :input[name=customerName]").val(),
					"search['contactsName']": $("#searchForm :input[name=contactsName]").val(),
					"search['stage']": $("#searchForm :input[name=stage]").val(),
					"search['type']": $("#searchForm :input[name=type]").val(),
					"search['source']": $("#searchForm :input[name=source]").val()
				};
				// JSON.stringify({name:1}) ==> '{"name":1}'
				$.ajax({
					url: "/transaction/page.json",
					data: data,
					success(page) {
						// 初始化表格数据
						var arr = [];
						$(page.data).each(function (i) {
							arr.push(`<tr>
							<td><input type="checkbox" /></td>
							<td><a style="text-decoration:none;" href="detail.jsp?id=`+this.id+`">`+this.name+`</a></td>
							<td>`+this.customer.name+`</td>
							<td>`+this.stage+`</td>
							<td>`+this.type+`</td>
							<td>`+this.owner+`</td>
							<td>`+this.source+`</td>
							<td>`+this.contacts.fullName+`</td>
						</tr>`);
						});
						$("#dataBody").html( arr.join("") );

						// 初始化分页控件
						$("#page").bs_pagination({
							currentPage: page.currentPage,          // 页码
							rowsPerPage: page.rowsPerPage,          // 每页显示的记录条数
							maxRowsPerPage: page.maxRowsPerPage,    // 每页最多显示的记录条数
							totalPages: page.totalPages,            // 总页数
							totalRows: page.totalRows,              // 总记录数
							visiblePageLinks: page.visiblePageLinks,// 显示几个卡片
							// 页码发生变化时执行的函数
							onChangePage: function (event, data) {
								pageList(data.currentPage, data.rowsPerPage);
							}
						});
					}
				})
			}

			pageList();

			$("#searchBtn").click(function () {
				pageList();
			});


		});

	</script>
</head>
<body>

<!-- 导入交易的模态窗口 -->
<div class="modal fade" id="importClueModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">导入交易</h4>
			</div>
			<div class="modal-body" style="height: 350px;">
				<div style="position: relative;top: 20px; left: 50px;">
					请选择要上传的文件：<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
				</div>
				<div style="position: relative;top: 40px; left: 50px;">
					<input type="file">
				</div>
				<div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;" >
					<h3>重要提示</h3>
					<ul>
						<li>给定文件的第一行将视为字段名。</li>
						<li>请确认您的文件大小不超过5MB。</li>
						<li>从XLS/XLSX文件中导入全部重复记录之前都会被忽略。</li>
						<li>复选框值应该是1或者0。</li>
						<li>日期值必须为MM/dd/yyyy格式。任何其它格式的日期都将被忽略。</li>
						<li>日期时间必须符合MM/dd/yyyy hh:mm:ss的格式，其它格式的日期时间将被忽略。</li>
						<li>默认情况下，字符编码是UTF-8 (统一码)，请确保您导入的文件使用的是正确的字符编码方式。</li>
						<li>建议您在导入真实数据之前用测试文件测试文件导入功能。</li>
					</ul>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal">导入</button>
			</div>
		</div>
	</div>
</div>

<div>
	<div style="position: relative; left: 10px; top: -10px;">
		<div class="page-header">
			<h3>交易列表</h3>
		</div>
	</div>
</div>

<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">

	<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

		<div class="btn-toolbar" role="toolbar" style="height: 80px;">
			<form id="searchForm" class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">所有者</div>
						<input name="owner" class="form-control" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">名称</div>
						<input name="name" class="form-control" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">客户名称</div>
						<input name="customerName" class="form-control" type="text">
					</div>
				</div>

				<br>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">阶段</div>
						<select name="stage" options="stage" class="form-control">
							<option></option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">类型</div>
						<select options="transactionType" name="type" class="form-control">
							<option></option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">来源</div>
						<select options="source" name="source" class="form-control" id="create-clueSource">
							<option></option>
						</select>
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">联系人名称</div>
						<input name="contactsName" class="form-control" type="text">
					</div>
				</div>

				<button id="searchBtn" type="button" class="btn btn-default">查询</button>
			</form>
		</div>
		<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;top: 10px;">
			<div class="btn-group" style="position: relative; top: 18%;">
				<button type="button" class="btn btn-primary" onclick="window.location.href='save.jsp';"><span class="glyphicon glyphicon-plus"></span> 创建</button>
				<button type="button" class="btn btn-default" onclick="window.location.href='edit.jsp';"><span class="glyphicon glyphicon-pencil"></span> 修改</button>
				<button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
			</div>
			<div class="btn-group" style="position: relative; top: 18%;">
				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#importClueModal"><span class="glyphicon glyphicon-import"></span> 导入</button>
				<button type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 导出</button>
			</div>

		</div>
		<div style="position: relative;top: 10px;">
			<table class="table table-hover">
				<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox" /></td>
					<td>名称</td>
					<td>客户名称</td>
					<td>阶段</td>
					<td>类型</td>
					<td>所有者</td>
					<td>来源</td>
					<td>联系人名称</td>
				</tr>
				</thead>
				<tbody id="dataBody"></tbody>
			</table>
		</div>

		<div id="page" style="position: relative;top: 20px;">

		</div>

	</div>

</div>
</body>
</html>