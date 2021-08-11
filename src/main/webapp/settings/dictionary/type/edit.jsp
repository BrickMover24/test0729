<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <!--禁用静态页面缓存-->
    <meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <link href="/static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <link href="/static/jquery/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        jQuery(function ($) {
            $.ajax({
                url: "/type/get.json?code=${param.code}",
                success(type) {
                    $("#code").val(type.code)
                    $("#name").val(type.name)
                    $("#description").val(type.description)
                }
            })

            $("#saveBtn").click(function () {
                $.ajax({
                    url: "/type/update.do",
                    type: "put",
                    data: {
                        "code": $("#code").val(),
                        "name": $("#name").val(),
                        "description": $("#description").val()
                    },
                    success(data) {
                        if (data.success) {
                            location = "index.jsp";
                            //location = "/settings/dictionary/type/index.html";
                        }
                        if (data.msg) {
                            alert(data.msg);
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>

<div style="position:  relative; left: 30px;">
    <h3>修改字典类型</h3>
    <div style="position: relative; top: -40px; left: 70%;">
        <button id="saveBtn" type="button" class="btn btn-primary">更新</button>
        <button type="button" class="btn btn-default" onclick="window.history.back();">取消</button>
    </div>
    <hr style="position: relative; top: -40px;">
</div>
<form class="form-horizontal" role="form">

    <div class="form-group">
        <label for="create-code" class="col-sm-2 control-label">编码<span
                style="font-size: 15px; color: red;">*</span></label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" readonly id="code" style="width: 200%;" placeholder="编码作为主键，不能是中文"
                   value="sex">
        </div>
    </div>

    <div class="form-group">
        <label for="create-name" class="col-sm-2 control-label">名称</label>
        <div class="col-sm-10" style="width: 300px;">
            <input type="text" class="form-control" id="name" style="width: 200%;" value="性别">
        </div>
    </div>

    <div class="form-group">
        <label for="create-describe" class="col-sm-2 control-label">描述</label>
        <div class="col-sm-10" style="width: 300px;">
            <textarea class="form-control" rows="3" id="description" style="width: 200%;">描述信息</textarea>
        </div>
    </div>
</form>

<div style="height: 200px;"></div>
</body>
</html>