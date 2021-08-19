<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/inc/commons-head.jsp"%>
    <title>ECharts</title>
    <!-- 引入 echarts.js -->
    <script src="/static/echarts.min.js"></script>

</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1100px;height:700px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));


    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '交易状态统计图表',
            subtext: '来自数据统计',
            left: 'left'
        },
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
            left: 'center'
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: [
                    {value: 1048, name: '搜索引擎'},
                    {value: 735, name: '直接访问'},
                    {value: 580, name: '邮件营销'},
                    {value: 484, name: '联盟广告'},
                    {value: 300, name: '视频广告'}
                ]
            }
        ]
    };
        jQuery (function($){
                   $.ajax({
                       url:"/transaction/charts.json",
                       success(data){
                           option.series[0].data=data;
                           // 使用刚指定的配置项和数据显示图表。
                           myChart.setOption(option);
                       }
                   })
               })

</script>
</body>
</html>