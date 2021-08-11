<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/inc/commons-head.jsp"%>

	<script type="text/javascript">
		jQuery(function ($) {
			function pageList(currentPage, rowsPerPage) {
				var data = {
					currentPage: currentPage,
					rowsPerPage, // 相当于rowsPerPage: rowsPerPage
						"search['name']": $("#searchForm :input[name=name]").val(),
						"search['owner']": $("#searchForm :input[name=owner]").val(),
						"search['startDate']": $("#searchForm :input[name=startDate]").val(),
						"search['endDate']": $("#searchForm :input[name=endDate]").val()
				};
				// JSON.stringify({name:1}) ==> '{"name":1}'
				$.ajax({
					url: "/act/page.json",
					//type: "post",
					data: data,
					/*headers: {
						"Content-Type":"application/json"
					},*/
					success(page) {
						// 初始化表格数据
						var arr = [];
						$(page.data).each(function (i) {
							arr.push(`<tr class="`+(i%2==0?"active":"")+`">
                                <td><input name="id" value="`+this.id+`" type="checkbox"/></td>
                                <td><a href="detail.jsp?id=`+this.id+`" style="text-decoration: none; cursor: pointer;">`+this.name+`</a></td>
                                <td>`+this.owner+`</td>
                                <td>`+this.startDate+`</td>
                                <td>`+this.endDate+`</td>
                                <td>`+this.cost+`</td>
                                <td>`+this.description+`</td>
                            </tr>` )
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
			})


/*ajaxForm:监听表单的提交事件,当表单提交时,该插件会阻止表单的提交,然后从表单中提取
        action:url
        method:type
        所有包含name属性的表单元素:data
        ajaxForm中的函数:success*/

		$("#editBtn").click(function () {
			// 获取选中的市场活动复选框
			var $cks = $(":checkbox[name=id]:checked");
			if ($cks.size() != 1) {
				alert("必须且只能选择一项！");
				// return false可以阻止窗口的弹出，而return只是简单的结束函数
				return false;
			}

			$.ajax({
				url: "/act/get.json?id=" + $cks.val(),
				success(data) {
					// 为含有name属性的元素复制（回显）
					$("#editForm :input[name]").each(function () {
						this.value = data[this.name];
					});
				}
			})

		})



			$("#updateBtn").click(function () {
				var data = {}; // new HashMap
				$("#editForm:input[name]").each(function () {
					data[this.name] = this.value; // map.put
				});
				$.ajax({
					url: "/act/update.do",
					type: "put",
					data: data,
					success(data) {
						if (data.success) {
							// 关闭窗口
							$("#editActivityModal").modal('hide');
							pageList();

						}
						if (data.msg) {
							alert(data.msg);
						}
					}
				})
			})

			//导出文件
			$("#exportBtn").click(function() {
                    location="/act/export.do"
			})
			//导入文件
			$("#importBtn").click(function() {
				//ajax文件上传使用
				var formData = new FormData();
				//获取所有选择的文件数组
				var files=$("#upFile").prop("files");
				formData.append("upFile",files[0]);

				$.ajax({
					url:"/act/import.do",
					type:"post",//必须是post
					data:formData,
					contentType:false,processData:false,
					success:function (data) {
						if (data.success) {
							//重新加载列表
							pageList();
							// 关闭窗口
							$("#editActivityModal").modal('hide');
						}
						if (data.msg) {
							alert(data.msg);
						}
					}
				})
			})




			/*$("#saveBtn").click(function () {
				//将表单直接以ajax形式提交
				$("#save-activity").ajaxSubmit(function (data) {
                            if (data.success){
                            	$("#searchForm").submit();
                            	$("div.modal:visible").modal('hide');
							}

                            if (data.msg){
                            	alert(data.msg);
							}
				});
			})*/


            $("#saveBtn").click(function () {
                $.ajax({
                    url:"/act/save.do",
                    type:"post",
                    data:{
                        id:$("#create-id").val(),
                    	owner:$("#create-owner").val(),
                        name:$("#create-name").val(),
                        startDate:$("#create-startDate").val(),
                        endDate:$("#create-endDate").val(),
						cost:$("#create-cost").val(),
                        description:$("#create-description").val()
                    },
                    success(data){
                        if(data.success){
                            $("#createActivityModal").modal("hide");
							pageList();
                        }
                        if (data.msg) {
                            alert(data.msg);
                        }

                    }
                })
            });


			$("#deleteBtn").click(function () {

			})
		});
	</script>
</head>
<body>

<!-- 创建市场活动的模态窗口 -->
<div class="modal fade" id="createActivityModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel1">创建市场活动</h4>
			</div>
			<div class="modal-body">

				<form id="save-activity" action="/act/save.do" method="post" class="form-horizontal" role="form">
                  <input type="hidden" id="create-id">
					<div class="form-group">
						<label for="create-owner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<select name="owner" owner class="form-control" id="create-owner">

							</select>
						</div>
						<label for="create-name" class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="name" type="text" class="form-control" id="create-name">
						</div>
					</div>

					<div class="form-group">
						<label  for="create-startDate" class="col-sm-2 control-label">开始日期</label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="startDate" date type="text" class="form-control" id="create-startDate">
						</div>
						<label for="create-endDate" class="col-sm-2 control-label">结束日期</label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="endDate" date type="text" class="form-control" id="create-endDate">
						</div>
					</div>
					<div class="form-group">

						<label for="create-cost" class="col-sm-2 control-label">成本</label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="cost"  type="text" class="form-control" id="create-cost">
						</div>
					</div>
					<div class="form-group">
						<label for="create-description" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea name="description" class="form-control" rows="3" id="create-description"></textarea>
						</div>
					</div>
				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="saveBtn" type="button" class="btn btn-primary" >保存</button>
			</div>
		</div>
	</div>
</div>

<!-- 修改市场活动的模态窗口 -->
<div class="modal fade" id="editActivityModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2">修改市场活动</h4>
			</div>
			<div class="modal-body">

				<form id="editForm" class="form-horizontal" role="form">

					<%--修改条件id放在隐藏域中--%>
					<input name="id" type="hidden"id="id" >

					<div class="form-group">
						<label for="edit-marketActivityOwner" class="col-sm-2 control-label">所有者<span
								style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<select name="owner" owner class="form-control" id="edit-marketActivityOwner">

							</select>
						</div>
						<label for="edit-marketActivityName" class="col-sm-2 control-label">名称<span
								style="font-size: 15px; color: red;">*</span></label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="name" type="text" class="form-control" id="edit-marketActivityName" value="发传单">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-startTime" class="col-sm-2 control-label">开始日期</label>
						<div class="col-sm-10" style="width: 300px;">
							<input autocomplete="off" date name="startDate" type="text" class="form-control" id="edit-startTime" value="2020-10-10">
						</div>
						<label for="edit-endTime" class="col-sm-2 control-label">结束日期</label>
						<div class="col-sm-10" style="width: 300px;">
							<input autocomplete="off" date name="endDate" type="text" class="form-control" id="edit-endTime" value="2020-10-20">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-cost" class="col-sm-2 control-label">成本</label>
						<div class="col-sm-10" style="width: 300px;">
							<input name="cost" type="text" class="form-control" id="edit-cost" value="5,000">
						</div>
					</div>

					<div class="form-group">
						<label for="edit-describe" class="col-sm-2 control-label">描述</label>
						<div class="col-sm-10" style="width: 81%;">
							<textarea  name="description" class="form-control" rows="3" id="edit-describe">市场活动Marketing，是指品牌主办或参与的展览会议与公关市场活动，包括自行主办的各类研讨会、客户交流会、演示会、新产品发布会、体验会、答谢会、年会和出席参加并布展或演讲的展览会、研讨会、行业交流会、颁奖典礼等</textarea>
						</div>
					</div>

				</form>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button id="updateBtn" type="button" class="btn btn-primary">更新</button>
			</div>
		</div>
	</div>
</div>

<!-- 导入市场活动的模态窗口 -->
<div class="modal fade" id="importActivityModal" role="dialog">
	<div class="modal-dialog" role="document" style="width: 85%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">导入市场活动</h4>
			</div>
			<div class="modal-body" style="height: 350px;">
				<div style="position: relative;top: 20px; left: 50px;">
					请选择要上传的文件：
					<small style="color: gray;">[仅支持.xls或.xlsx格式]</small>
				</div>
				<div style="position: relative;top: 40px; left: 50px;">
					<input id="upFile" type="file">
				</div>
				<div style="position: relative; width: 400px; height: 320px; left: 45% ; top: -40px;">
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
				<button id="importBtn" type="button" class="btn btn-primary" data-dismiss="modal">导入</button>
			</div>
		</div>
	</div>
</div>

<div>
	<div style="position: relative; left: 10px; top: -10px;">
		<div class="page-header">
			<h3>市场活动列表</h3>
		</div>
	</div>
</div>
<div style="position: relative; top: -20px; left: 0px; width: 100%; height: 100%;">
	<div style="width: 100%; position: absolute;top: 5px; left: 10px;">

		<div class="btn-toolbar" role="toolbar" style="height: 80px;">
			<form id="searchForm" action="/act/page3.json"  method="post" class="form-inline" role="form" style="position: relative;top: 8%; left: 5px;">

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">名称</div>
						<input id="name" name="name" class="form-control" type="text">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">所有者</div>
						<input  id="owner" name="owner" class="form-control" type="text">
					</div>
				</div>


				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">开始日期</div>
						<input autocomplete="off" date id="startDate" name="startDate" class="form-control" type="text" id="startTime"/>
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<div class="input-group-addon">结束日期</div>
						<input autocomplete="off" date id="endDate" name="endDate" class="form-control" type="text" id="endTime">
					</div>
				</div>

				<%--<button id="searchBtn" type="button" class="btn btn-default">查询</button>--%>
				<button id="searchBtn" type="button" class="btn btn-default">查询</button>

			</form>
		</div>
		<div class="btn-toolbar" role="toolbar"
			 style="background-color: #F7F7F7; height: 50px; position: relative;top: 5px;">
			<div class="btn-group" style="position: relative; top: 18%;">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#createActivityModal">
					<span class="glyphicon glyphicon-plus"></span> 创建
				</button>
				<button id="editBtn" type="button" class="btn btn-default" data-toggle="modal" data-target="#editActivityModal"><span
						class="glyphicon glyphicon-pencil"></span> 修改
				</button>
				<button id="deleteBtn" type="button" class="btn btn-danger"><span class="glyphicon glyphicon-minus"></span> 删除</button>
			</div>
			<div class="btn-group" style="position: relative; top: 18%;">
				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#importActivityModal">
					<span class="glyphicon glyphicon-import"></span> 导入
				</button>
				<button id="exportBtn" type="button" class="btn btn-default"><span class="glyphicon glyphicon-export"></span> 导出
				</button>
			</div>
		</div>
		<div style="position: relative;top: 10px;">
			<table class="table table-hover">
				<thead>
				<tr style="color: #B3B3B3;">
					<td><input type="checkbox"/></td>
					<td>名称</td>
					<td>所有者</td>
					<td>开始日期</td>
					<td>结束日期</td>
					<td>成本</td>
					<td>描述</td>

				</tr>
				</thead>
				<tbody id="dataBody"></tbody>
			</table>
		</div>

		<div id="page" style="position: relative;top: 30px;">

		</div>

	</div>

</div>
</body>
</html>