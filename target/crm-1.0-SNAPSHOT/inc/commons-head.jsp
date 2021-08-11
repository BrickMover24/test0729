<meta charset="UTF-8">
<link href="/static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/static/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>

<%--分页控件--%>
<link href="/static/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/static/bs_pagination/en.js"></script>
<script type="text/javascript" src="/static/bs_pagination/jquery.bs_pagination.min.js"></script>

<%--日历控件--%>
<link href="/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/static/bootstrap-datetimepicker/locale/bootstrap-datetimepicker.zh-CN.js"></script>

<%--表单插件--%>
<script type="text/javascript" src="/static/jquery/jquery-form.js"></script>

<%--自动补全插件--%>
<script type="text/javascript" src="/static/bs_typeahead/bootstrap3-typeahead.min.js"></script>

<script>
    jQuery(function ($) {
       // if($("select[owner]").size())
        // 初始化日期控件
        $("input[date]").datetimepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd",//显示格式
            minView: "month", // 日期时间选择器所能够提供的最精确的时间选择视图。
            initialDate: new Date(),//初始化当前日期
            autoclose: true, //选中自动关闭
            pickerPosition: "bottom-right"
        }).attr("autocomplete", "off");

        $("input[datetime]").datetimepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd hh:ii:ss",//显示格式
            minView: "hour", // 日期时间选择器所能够提供的最精确的时间选择视图。
            initialDate: new Date(),//初始化当前日期
            autoclose: true, //选中自动关闭
            pickerPosition: "bottom-right"
        }).attr("autocomplete", "off");
    })
</script>

<%--所有者下拉列表--%>
<script>
    jQuery(function ($) {
     if($("select[owner]").size())

        // 初始化所有者下拉框
        $.ajax({
            url: "/user/owners.json",
            success(data) {
                var arr = [];
                // data: ["10000|翟总"]
                $(data).each(function () {
                    arr.push("<option>" + this + "</option>");
                })
                $("select[name=owner]").html( arr.join("") );
            }
        });

        //调用后台下拉选项服务接口
        /*if($("select[source]").size()){
            $.ajax({
                url:"/options.json?typeCode=source",
                success(data){
                    var arr=[];
                    $(data).each(function () {
                        arr.push("<option>"+this.text+"</option>");
                    });

                    $("#select[name=source]").append(arr.join(""));
                }
            });
        }*/

              $("select[options]").each(function () {
              var typeCode=$(this).attr("options");
              var that=this;
              $.ajax({
                  url:"/options.json?typeCode="+typeCode,
                  success(data){
                  var arr = [];
                  $(data).each(function () {
                  arr.push("<option>"+this.text+"</option>");
               });

              $(that).append(arr.join(""));
          }
      });
    })

    });
</script>