<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="/static/jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="/static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        jQuery(function ($) {
            //列表
            function load() {
                $.ajax({
                    url: "http://localhost:/value/list.json",
                    success(data) {
                        var arr = [];
                        $(data).each(function (i) {
                            arr.push(`<tr class="`+(i%2==0?"active":"")+`">
                            <td><input name="id" value="`+this.id+`" type="checkbox"/></td>
                            <td>`+(i+1)+`</td>
                            <td>`+this.value+`</td>
                            <td>`+this.text+`</td>
                            <td>`+this.orderNo+`</td>
                            <td>`+this.typeCode+`</td>
                        </tr>`)
                        });
                        $("#dataBody").html( arr.join("") );
                    }
                })
            }
            load();

            //删除
            $("#deleteBtn").click(function () {
                // 获取选中的复选框
                var $cks = $(":checkbox[name=id]:checked");
                if ($cks.size() == 0) {
                    alert("请选择删除项！");
                    return ;
                }
                if ( confirm("确定删除吗？") ) {
                    var ids = [];
                    $cks.each(function () {
                        ids.push(this.value);
                    });

                    // [1,2,3] ===> "1,2,3"
                    ids = ids.join(",");
                    $.ajax({
                        url: "http://localhost:/value/delete.do?ids="+ids,
                        type: "delete",
                        success: function (data) {
                            if (data.success) {
                                load(); // 重新加载列表
                            }
                            if(data.msg) {
                                alert(data.msg);
                            }
                        }
                    })
                }
            })


           //列表回显

            $("#editBtn").click(function () {
                // 获取选中的复选框
                var $cks = $(":checkbox[name=id]:checked");
                if ( $cks.size() != 1 ) {
                    alert("必须且只能选择一项！");
                    return ;
                }
                location = "edit.jsp?id=" + $cks.val();
            })



        })
    </script>
</head>
<body>

<div>
    <div style="position: relative; left: 30px; top: -10px;">
        <div class="page-header">
            <h3>字典值列表</h3>
        </div>
    </div>
</div>
<div class="btn-toolbar" role="toolbar" style="background-color: #F7F7F7; height: 50px; position: relative;left: 30px;">
    <div class="btn-group" style="position: relative; top: 18%;">
        <button type="button" class="btn btn-primary" onclick="window.location.href='save.jsp'"><span
                class="glyphicon glyphicon-plus"></span> 创建
        </button>
        <button id="editBtn" type="button" class="btn btn-default"><span
                class="glyphicon glyphicon-edit"></span> 编辑
        </button>
        <button id="deleteBtn" type="button"
                class="btn btn-danger">
            <span class="glyphicon glyphicon-minus">
            </span> 删除</button>
    </div>
</div>
<div style="position: relative; left: 30px; top: 20px;">
    <table class="table table-hover">
        <thead>
        <tr style="color: #B3B3B3;">
            <td><input type="checkbox"/></td>
            <td>序号</td>
            <td>字典值</td>
            <td>文本</td>
            <td>排序号</td>
            <td>字典类型编码</td>
        </tr>
        </thead>
        <tbody id="dataBody"></tbody>
    </table>
</div>

</body>
</html>