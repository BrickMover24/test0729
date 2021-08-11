<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <link href="/static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/static/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css"
          rel="stylesheet"/>

    <script type="text/javascript" src="/static/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        jQuery(function ($) {
            $.ajax({
                url: "/type/list.json",
                success(data) {
                    var html = "";
                    $(data).each(function () {
                        html += '<option value="'+this.code+'">'+this.name+'</option>';
                    });
                    // html(xxx): 完全替换内容
                    // append(xxx): 在原有基础上追加
                    $("#create-dicTypeCode").append( html );
                }
            });

            $("#saveBtn").click(function () {
                $.ajax({
                    url: "/value/save.do",
                    type: "post",
                    data: {
                        value: $("#create-dicValue").val(),
                        text: $("#create-text").val(),
                        orderNo: $("#create-orderNo").val(),
                        typeCode: $("#create-dicTypeCode").val()
                    },
                    success(data) {
                        if (data.success) {
                            location = "index.jsp";
                        }
                        if (data.msg) {
                            alert(data.msg);
                        }
                    }
                })
            });

        })
    </script>
</head>
<body>

<div style="position:  relative; left: 30px;">
    <h3>新增字典值</h3>
    <div style="position: relative; top: -40px; left: 70%;">
        <button id="saveBtn" type="button" class="btn btn-primary">保存</button>
        <button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
    </div>
    <hr style="position: relative; top: -40px;">
</div>
<form class="form-horizontal" role="form">

    <div class="form-group">
        <label for="create-dicTypeCode" class="col-sm-2 control-label">字典类型编码<span style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <select class="form-control" id="create-dicTypeCode" style="width: 200%;">
                <option></option>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="create-dicValue" class="col-sm-2 control-label">字典值<span
                style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-dicValue" style="width: 200%;">
        </div>
    </div>

    <div class="form-group">
        <label for="create-text" class="col-sm-2 control-label">文本</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-text" style="width: 200%;">
        </div>
    </div>

    <div class="form-group">
        <label for="create-orderNo" class="col-sm-2 control-label">排序号</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="create-orderNo" style="width: 200%;">
        </div>
    </div>
</form>

<div style="height: 200px;"></div>
</body>
</html>