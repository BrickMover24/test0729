<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/inc/commons-head.jsp"%>

<script type="text/javascript">
	jQuery(function($){
		$("#isCreateTransaction").click(function(){
			if(this.checked){
				$("#create-transaction2").show(200);
			}else{
				$("#create-transaction2").hide(200);
			}

		});


		$.ajax({
			url:"/clue/get.json?id=${param.id}",
			success(data){
              $("[text=company]").html(data.company);
				$("[text=fullName]").html(data.fullName);
				$("[text=owner]").html(data.owner);
			}
		})

		// 加载已关联的市场活动
		function loadRelActivities() {
			$.ajax({
				url: "/act/getByClueId.json?clueId=${param.id}",
				success(data) {
					var arr = [];
					$(data).each(function () {
						arr.push(`<tr activityId="`+this.id+`" activityName="`+this.name+`">
                                <td><input type="radio" name="activity"></td>
                                <td>`+this.name+`</td>
                                <td>`+this.startDate+`</td>
                                <td>`+this.endDate+`</td>
                                <td>`+this.owner+`</td>
                            </tr>`)
					})
					$("#dataBody").html( arr.join("") );
				}
			})
		}
		loadRelActivities();

		$("#dataBody").on("dblclick","tr",function () {
			//市场活动名称和id存放在tr的自定义属性中
			$("#activityName").val($(this).attr("activityName"));
			$("#activityId").val($(this).attr("activityId"));
			$(this).find(":radio").prop("checked",true);
			$("div.modal:visible").modal('hide');
		})

		$("#convertBtn").click(function () {

		})


	});
</script>

</head>
<body>
	
	<!-- 搜索市场活动的模态窗口 -->
	<div class="modal fade" id="searchActivityModal" role="dialog" >
		<div class="modal-dialog" role="document" style="width: 90%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title">搜索市场活动</h4>
				</div>
				<div class="modal-body">
					<div class="btn-group" style="position: relative; top: 18%; left: 8px;">
						<form class="form-inline" role="form">

						  <div class="form-group has-feedback">
						    <input type="text" class="form-control" style="width: 300px;" placeholder="请输入市场活动名称，支持模糊查询">
						    <span class="glyphicon glyphicon-search form-control-feedback"></span>
						  </div>
						</form>
					</div>
					<table id="activityTable" class="table table-hover" style="width: 900px; position: relative;top: 10px;">
						<thead>
							<tr style="color: #B3B3B3;">
								<td></td>
								<td>名称</td>
								<td>开始日期</td>
								<td>结束日期</td>
								<td>所有者</td>
								<td></td>
							</tr>
						</thead>
						<tbody id="dataBody"></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div id="title" class="page-header" style="position: relative; left: 20px;">
		<h4>转换线索 <small>李四先生-动力节点</small></h4>
	</div>
	<div id="create-customer" style="position: relative; left: 40px; height: 35px;">
		新建客户：<span text="company"></span>
	</div>
	<div id="create-contact" style="position: relative; left: 40px; height: 35px;">
		新建联系人：<span text="fullName"></span>
	</div>
	<div id="create-transaction1" style="position: relative; left: 40px; height: 35px; top: 25px;">
		<input type="checkbox" id="isCreateTransaction"/>
		为客户创建交易
	</div>
	<div id="create-transaction2" style="position: relative; left: 40px; top: 20px; width: 80%; background-color: #F7F7F7; display: none;" >
	
		<form>
			<%--id隐藏域--%>
		   <input type="hidden" name="activityId" id="activityId">
		  <div class="form-group" style="width: 400px; position: relative; left: 20px;">
		    <label for="amountOfMoney">金额</label>
		    <input type="text" class="form-control" id="amountOfMoney">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="tradeName">交易名称</label>
		    <input type="text" class="form-control" id="tradeName" value="动力节点-">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="expectedClosingDate">预计成交日期</label>
		    <input  name="estimatedclosingdate " date type="text" class="form-control" id="expectedClosingDate">
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="stage">阶段</label>
		    <select options="clueState" id="stage"  class="form-control">
		    	<option></option>

				<c:forEach items="${dicMap.stage}" var="v">
					<option>${v.text}</option>
				</c:forEach>
		    </select>
		  </div>
		  <div class="form-group" style="width: 400px;position: relative; left: 20px;">
		    <label for="activity">市场活动源&nbsp;&nbsp;
				<a href="javascript:void(0);" data-toggle="modal"
				   data-target="#searchActivityModal" style="text-decoration: none;">
					<span class="glyphicon glyphicon-search"></span></a></label>
		    <input type="text" class="form-control" name="activityName" id="activityName" placeholder="点击上面搜索" readonly>
		  </div>
		</form>
		
	</div>
	
	<div id="owner" style="position: relative; left: 40px; height: 35px; top: 50px;">
		记录的所有者：<br>
		<b text="owner">zhangsan</b>
	</div>
	<div id="operation" style="position: relative; left: 40px; height: 35px; top: 100px;">
		<input id="convertBtn" class="btn btn-primary" type="button" value="转换">
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input class="btn btn-default" type="button" value="取消">
	</div>
</body>
</html>