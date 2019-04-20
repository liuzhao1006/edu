<#include "./common/layout.ftl">
<@header>
</@header>
<@body>
<style>
    #suggest_ul li{
        padding:0 10px;
        width:100%;
        background-color: #fff;
        cursor: pointer;
        height:30px;
        line-height: 30px;
    }
    #suggest_ul li span{
        display: inline-block;
        width: 50%;
        vertical-align: middle;
    }
    #suggest_ul li span.lspan{
        width: 38%;
    }
    #suggest_ul li span.rspan{
        width: 62%;
    }

    .index-box{
        padding-top: 10px;
        padding-bottom: 20px;
    }
    .index-box .col-md-4{
        padding:0 2px;
    }
    .nums-box > .inner{
        padding:10px 7px;
    }
    .index-box .row .nums-box{
        background-color: #fff;
    }
    .indexbox:hover,.chartDiv:hover{
        box-shadow:1px 2px 11px 0px #D8D9DC;
        -moz-box-shadow: 1px 2px 11px 0px #D8D9DC;
        -ms-box-shadow: 1px 2px 11px 0px #D8D9DC;
        -o-box-shadow: 1px 2px 11px 0px #D8D9DC;
    }
    .nums-box .icon{
        width:auto;
        margin-bottom:10px;
    }
    .icon i,.icon .titTxt{
        display: inline-block;
        vertical-align: middle;
    }
    .icon i{
        margin-right:20px;
    }
    .titTxt p{
        font-size: 14px;
        color: #999;
        margin: 0;
    }
    .titTxt .nums{
        font-size: 30px;
        color: #fd7414;
    }
    .innerInfo div{
font-size: 12px;
        color: #999;
    }
    .innerInfo div span{
        color: #666;
        margin-right:2px;
    }
    .index-box .row .searchBox{
        height:113px;
        line-height: 113px;
        border: none;
        background-color: #fff;
        padding:0;
        text-align: center;
    }
    .index-box .chartDiv{
        background-color: #fff;
        border:0;
    }
    .index-box .col-sm-6{
        padding:20px 10px 0;
    }
    .chartTit{
        float: left;
        background:url("../ui/css/images/chartTit.png") no-repeat left center;
        padding-left:20px;
        font-size: 16px;
        color: #333;
    }
    .chartDiv{
        position: relative;
    }
    .danxuan{
        position: absolute;
        display: inline-block;
        top:50px;
        left:20px;
        color: #333;
        z-index:333;
    }
    .danxuan input[type='radio']{
        margin-left:10px;
    }
    #searchtext{
        width:76%;border:none;height: 40px;line-height: 40px;background-color: #f9f9f9;padding:0;
        margin-right:0;
    }
    #searchtext:focus{
        border:none!important;
    }
    .titBox{
        overflow: hidden;
    }
    #suggest_Box{
        display: none;
    }
</style>
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
<#--<section class="content-header">
    <h1>
        主面板
        <small>显示系统信息</small>
    </h1>
</section>-->

    <!-- Main content -->
    <section class="content" id="sec_content">
        <div class="index-box">
            <div class="row" style="padding:0 15px;">
                <div class="col-lg-8 col-md-12" style="padding:0 4px;">
                    <div class="row" style="padding-left:20px;padding-right: 0">
                        <div class="col-md-4 col-sm-12">
                            <!-- small box -->
                            <div class="nums-box indexbox">
                                <div class="inner">
                                    <div class="icon">
                                        <i class="day-icon"><img src="../ui/css/images/companyNums.png" alt="企业总数"></i>
                                      <div class="titTxt">
                                          <p>企业总数</p>
                                          <p><span class="nums">${companyAllInfo.companyCount?c}</span></p>
                                      </div>

                                    </div>
                                    <div class="innerInfo">
                                        <div class="direction">客运 <span> ${companyAllInfo.transport?c}</span> 货运 <span>${companyAllInfo.common?c}</span></div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- ./col -->
                        <div class="col-md-4 col-sm-12">
                            <!-- small box -->
                            <div class="nums-box indexbox">
                                <div class="inner">
                                    <div class="icon">
                                        <i class="day-icon"><img src="../ui/css/images/vehicleNums.png" alt="车辆总数"></i>
                                        <div class="titTxt">
                                            <p>车辆总数</p>
                                            <p><span class="nums">${vehicleAllInfo.vehicleCount?c}</span></p>
                                        </div>
                                    </div>
                                    <div class="innerInfo">
                                        <div class="direction">
                                            危货<span>${vehicleAllInfo.x?c}</span>
                                            普货 <span>${vehicleAllInfo.h?c}</span>
                                            客车 <span>${vehicleAllInfo.k?c}</span>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div><!-- ./col -->
                        <div class="col-md-4 col-sm-12">
                            <!-- small box -->
                            <div class="nums-box indexbox">
                                <div class="inner">
                                    <div class="icon">
                                        <i class="day-icon"><img src="../ui/css/images/people.png" alt="从业人员总数"></i>
                                        <div class="titTxt">
                                            <p>从业人员总数</p>
                                            <p><span class="nums">${employeeAllInfo.employeeCount?c}</p>
                                        </div>
                                    </div>
                                    <div class="innerInfo">
                                        <div class="direction">
                                            驾驶员  <span>${employeeAllInfo.driver?c} </span>
                                            押运员 <span>${employeeAllInfo.supercargo?c}</span>
                                            装卸员 <span>${employeeAllInfo.loading?c}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- ./col -->

                    </div>
                </div>
                <div class="col-lg-4 col-md-12 "  style="padding:0 10px 0 12px">
                    <div class="searchBox  indexbox">
                        <div class="input-group" style="position: relative;height:40px;line-height: 40px;background-color: #f9f9f9;border:1px solid #f1f1f1;box-sizing: border-box;padding:0;width:90%;margin:10% auto;">
                              <div class="selectBox" style="position: relative;width:10%;height: 40px;overflow: hidden;float:left">
                                  <select id="suggest_select" class="form-control" style="width:100px;height:40px;line-height:40px;margin-right:3px;position: absolute;top:0;left:-74px;border:none;z-index:2;background-color: #f9f9f9;">
                                      <option value="1">从业人员</option>
                                      <option value="2">车辆</option>
                                      <option value="3">企业</option>
                                  </select>
                              </div>
                            <input type="hidden"  id="no" name="no" />
                            <input type="text"  id="searchtext" name="searchtext" class="form-control"  autocomplete="off"  onkeyup="fuzzySearch();" placeholder="从业人员/身份证号" />
                            <div id="suggest_Box" style="width:100%;position: absolute;top:39px;left:0;z-index:99;height:200px;overflow: auto">
                                <ul  id="suggest_ul"></ul>
                            </div>
                            <img src="/ui/css/images/searchIcon.png" alt="查询" style="width:23px;height:24px;vertical-align: middle;cursor: pointer" id="driver_employee">
                        </div>
                    </div>
                </div><!-- ./col -->
                <!-- fix for small devices only -->
                <div class="clearfix visible-sm-block"></div>
            </div><!-- /.row -->

            <div class="row" style="padding:0 15px;">

                <div  class="col-sm-6 col-xs-12">
                    <div class="chartDiv">
                           <p class="chartTit">车辆动态监控报警统计</p>
                        <input type="text" name="marker2_daterange"  class="form-control date"
                               id="marker2_daterange" placeholder="开始日期  - 结束日期" style="width: 260px;">
                        <div class="danxuan">
                        <input type="radio" value="asc" name="marker_realtime_orderDesc" >递增
                        <input type="radio" value="desc" name="marker_realtime_orderDesc" checked>递减
                        </div>
                        <div id="marker2_chartmain" style="width:100%; height: 400px;margin-top:36px">
                            <div class="loadinggg" style="text-align: center;font-size:11px">加载中......</div>

                        </div>
                    </div>
                </div>
                <div  class="col-sm-6 col-xs-12">
                    <div class="chartDiv">
                       <div class="titBox">
                           <p class="chartTit">联网联控考核统计</p>
                           <input type="text" name="marker_daterange"  class="form-control date"
                                  id="marker_daterange" placeholder="开始日期  - 结束日期" style="width: 260px;">
                       </div>
                        <div class="danxuan">
                            <input type="radio" value="asc" name="marker_chartmain_orderDesc" checked>递增
                            <input type="radio" value="desc" name="marker_chartmain_orderDesc">递减
                        </div>
                        <div id="marker_chartmain" style="width:100%; height: 400px;margin-top:6px">
                            <div class="loadinggg" style="text-align: center;font-size:11px">加载中......</div>
                        </div>
                    </div>
                </div>
                <div  class="col-sm-6 col-xs-12">
                    <div class="chartDiv">
                       <div class="titBox">
                           <p class="chartTit">企业风险统计</p>
                           <input type="text"  name="stack_daterange"  class="form-control date"
                                  id="stack_daterange" placeholder="开始日期  - 结束日期" style="width: 260px;">
                           <select name="risktype" id="risktype"  onchange="riskAlarmLevelStatistics()"class="form-control" style="width:94px;display: inline-block;margin-right: 5px;float: right">
                               <#list fxzbConfigSeconds  as config >
                                   <option value="${(config.second_mark)!}"> ${(config.second_name)!}</option>
                               </#list>
                           </select>
                       </div>
                        <div class="danxuan">
                            <input type="radio" value="asc" name="stack_chartmain_orderDesc">递增
                            <input type="radio" value="desc" name="stack_chartmain_orderDesc" checked>递减
                        </div>
                        <div id="stack_chartmain" style="width:100%; height: 400px;">
                            <div class="loadinggg" style="text-align: center;font-size:11px">加载中......</div>
                        </div>
                    </div>
                </div>
                <div  class="col-sm-6 col-xs-12">
                    <div class="chartDiv">
                       <div class="titBox">
                           <p class="chartTit">企业安全生产达标考核统计</p>
                           <input type="text" name="chart_daterange"  class="form-control date"
                                  id="chart_daterange" placeholder="开始日期  - 结束日期" style="width: 170px;margin-right: 5px">
                           <select id="chartmain_TypeId" name="chartmain_TypeId" onchange="safeProdTotalScore()" class="form-control  readonly" style="width: 140px;float: right;margin-right: 5px;"
                                   data-rule="所属类型:required;">
                               <#list configSeconds  as config >
                                   <option value="${(config.second_mark)!}"> ${(config.second_name)!}</option>
                               </#list>
                           </select>
                         </div>
                        <div class="danxuan">
                            <input type="radio" value="asc" name="chartmain_orderDesc" checked>递增
                            <input type="radio" value="desc" name="chartmain_orderDesc">递减
                        </div>
                        <div id="chartmain" style="width:100%; height: 400px;">
                            <div class="loadinggg" style="text-align: center;font-size:11px">加载中......</div>
                        </div>
                    </div>
                </div>
            </div>
    </section>
    <section class="content" id="sec_content_iframe" style="display:none">
        <iframe src="" frameborder="0" scrolling="yes"  width="100%" id="external-frame" >
        </iframe>
    </section>
</div><!-- /.content-wrapper -->
</@body>
<@footer>
<script src="/plugins/chartjs/Chart.min.js"></script>
<script>
    $("#external-frame").height($(window).height()-90+"!important");
    $("input:radio[name=marker_realtime_orderDesc]").bind('change',function () {
        alarmRealtime();
    });

    $("input:radio[name=chartmain_orderDesc]").bind('change',function () {
        safeProdTotalScore();
    });
    $("#chartmain_TypeId").bind('change',function () {
        safeProdTotalScore();
    });
    $("input:radio[name=marker_chartmain_orderDesc]").bind('change',function () {
        networkedTotalScore();
    });
    $("input:radio[name=stack_chartmain_orderDesc]").bind('change',function () {
        riskAlarmLevelStatistics();
    });
    var url='/fuzzySearch/employee';
    $("#suggest_select").on('change',function () {
        $("#no").val("");
        var select = $("#suggest_select").val();
        $("#searchtext").val("");
        switch (select)
        {
            case "1" :
                $("#searchtext").attr('placeholder','姓名/身份证号');
                url='/fuzzySearch/employee';
                break;
            case "2":
                $("#searchtext").attr('placeholder','车牌号/道路运输证号');
                url='/fuzzySearch/vehicle';
                break;
            case "3":
                $("#searchtext").attr('placeholder','企业名称/许可证号');
                url='/fuzzySearch/company';
                break;
            default:
                url='';
        }


    })
    //赋值
    function onChangehoverLi(thisLi){
        $("#searchtext").val($(thisLi).find("span").eq(0).html());
        $("#suggest_Box").hide(0);
        var select = $("#suggest_select").val();
        if(select==1) {
            $("#no").val($(thisLi).find("span").eq(1).html());
        }else{
            $("#no").val($(thisLi).find("span").eq(0).html());
        }
    }


    $(function(){
//载入时隐藏下拉li
        $("#suggest_Box").hide(0);
    });

    function fuzzySearch(){
//如果文本框为空，不发送请求
        if($("#searchtext").val().length==0||$("#searchtext").val().length>10){
            $("#suggest_Box").hide(0);
            return;
        }
//发送请求
        var text=$("#searchtext").val();
        $.ajax({
            type:'post',
            url:url,
            data:{test:text},
            async:false,
            dataType:"json",
            success:function(data){
                var contents="";
                var select = $("#suggest_select").val();
                switch (select)
                {
                    case "1" :
                        contents=contents+"<li><span class='lspan'>从业人员</span><span class='rspan'>身份证号</span></li>"
                        break;
                    case "2":
                        contents=contents+"<li><span class='lspan'>车牌号</span><span class='rspan'>道路运输证号</span></li>"
                        break;
                    case "3":
                        contents=contents+"<li><span class='lspan'>企业名称</span><span class='rspan'>许可证号</span></li>"
                        break;
                    default:
                }
                if (data.length!=0){
                    $.each(data,function (index,item) {
                        contents=contents+"<li onclick='onChangehoverLi(this);' class='suggest_li"+(index+1)+"'><span title='"+item.searchFir+"' class='lspan' style='overflow: hidden;white-space: nowrap;text-overflow: ellipsis;'>"+item.searchFir+"</span><span class='rspan'>"+item.searchSec+"</span></li>";
                        if(index==9){
                            return false;
                        }
                    })
                    $("#suggest_ul").html(contents);
                    $("#suggest_Box").show(300);
                }
                else {
                    $("#suggest_Box").hide(0);
                }
            }
        });

    }

    //鼠标
    $(function(){

//按下按键后300毫秒显示下拉提示
        $("#searchtext").keyup(function(){
            setInterval(changehover,300);
            function changehover(){
                $("#suggest_ul li").hover(function(){ $(this).css("background","#eee");},function(){ $(this).css("background","#fff");});
            }
        });
        //点击下拉ul以外的区域隐藏ul
        $(document).click(function(){
            $("#suggest_Box").hide();

        });
        $("#suggest_ul").click(function(event){
            event.stopPropagation();
        });

    });


</script>
<script>
    var end_date = new Date();
    end_date.setDate(end_date.getDate());
    var year=end_date.getFullYear();
    var month=end_date.getMonth()+1;
    var day=end_date.getDate();
    $("#marker2_daterange").val(year+'/'+month+'/'+day+' '+'00:00:00'+ ' - ' +year+'/'+month+'/'+day+' '+'23:59:59');
    $("#marker_daterange").val(year+'/'+month+'/'+day+' '+'00:00:00'+ ' - ' +year+'/'+month+'/'+day+' '+'23:59:59');
    $("#stack_daterange").val(year+'/'+month+'/'+day+' '+'00:00:00'+ ' - ' +year+'/'+month+'/'+day+' '+'23:59:59');
    $("#chart_daterange").val(year+'/'+month+'/'+day+' '+'00:00:00'+ ' - ' +year+'/'+month+'/'+day+' '+'23:59:59');
    var myChart1='';
    var myChart2='';
    var myChart3='';
    var myChart4='';
    function p(s) {
        return s < 10 ? '0' + s: s;
    }

    function GetDateStr(AddDayCount) {
        var dd = new Date();
        dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
        var y = dd.getFullYear();
        var m = dd.getMonth()+1;//获取当前月份的日期
        var d = dd.getDate();
        var hh=0;       //获取当前小时数(0-23)
        var mm=0;     //获取当前分钟数(0-59)
        var ss=0;
        if(AddDayCount==0)
        {
            hh=23;       //获取当前小时数(0-23)
            mm=59;     //获取当前分钟数(0-59)
            ss=59;
        }



        return y+'-'+p(m)+"-"+p(d)+" "+p(hh)+':'+p(mm)+":"+p(ss);
    }
    function GetYearStr() {
        var dd = new Date();
        dd.setDate(dd.getDate());//获取AddDayCount天后的日期
        var y = dd.getFullYear();
        var m = 1;//获取当前月份的日期
        var d = 1;
        var hh=0;       //获取当前小时数(0-23)
        var mm=0;     //获取当前分钟数(0-59)
        var ss=0;




        return y+'-'+p(m)+"-"+p(d)+" "+p(hh)+':'+p(mm)+":"+p(ss);
    }

    $(function(){
      /*  $('#driver_vehicle').on('click',function(){
            dialog= GetConformDialog('/vehicleindex?vehicle_no='+$("#input_vehicle_id").val(),'车辆台账','');
        })*/
        $('#driver_employee').on('click',function(){
            var select = $("#suggest_select").val();
            var no = $("#no").val();
            if(no!=""&&no!=null&&no!=undefined) {
                if (select == 1) {
                    //window.location.href="/employeeindex?employee_no=" + no ;//在当前页面打开
                    window.open("/employeeindex?employee_no=" + no);//打开新页签
                    //dialog = GetConformDialog('/employeeindex?employee_no=' + no, '从业人员台账', '');
                } else if (select == 2) {
                    //window.location.href="/vehicleindex?vehicle_no=" + no ;
                    window.open("/vehicleindex?vehicle_no=" + no);
                    //dialog = GetConformDialog('/vehicleindex?vehicle_no=' + no, '车辆台账', '');
                } else {
                    //window.location.href="/companyindex?company_no=" + no ;
                      window.open("/companyindex?company_no=" + no);
                    //dialog = GetConformDialog('/companyindex?company_no=' + no, '车辆台账', '');
                }
            }else {
                Commonalert("请选择相应的人员、车辆或者企业，再查询");
                return false;
            }
        })

        //企业安全生产达标考核
        safeProdTotalScore();

        //柱状图
        riskAlarmLevelStatistics();

        //企业联网联控考核
        networkedTotalScore();

        //车辆动态监控报警统计
        alarmRealtime();
        DateInfo();
    });

    function getBeforeDate() {
        var date = new Date();
        var seperator1 = "/";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate()-7;
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + date.getHours() + seperator2 + date.getMinutes()
                + seperator2 + date.getSeconds();
        return currentdate;
    }

    //车辆动态监控报警统计------onchange
    function alarmRealtime() {
        var times=$("#marker2_daterange").val();
        var begintime="";
        var endtime="";
        if(times!=null&&times!=""&&times != undefined)
        {
            begintime=times.split('-')[0];
            endtime=times.split('-')[1];
            var bt = new Date(begintime);
            var et = new Date(endtime);
            var total = (et.getTime() - bt.getTime())/1000;
            var day = parseInt(total / (24*60*60));//计算整数天数
            if(day >= 3){
                Commonalert("日期间隔天数不能大于3天!");
                return false;
            }
        }
//        var threeDaysAgoTime=getBeforeDate();
//        if(begintime!=""&&begintime<threeDaysAgoTime){
//            Commonalert("不能查询三天以前的数据");
//            return false;
//        }
        MarkersInfo(begintime,endtime);

    }
    /**
     *车辆动态监控报警统计
     * @param begintime
     * @param endtime
     * @constructor
     */
    function MarkersInfo(begintime,endtime) {
        var params = {};
        params.orderDesc="desc";
        var orderValue = $('input:radio[name="marker_realtime_orderDesc"]:checked').val();
        if(orderValue!=null && orderValue != undefined && orderValue != ''){
            params.orderDesc = orderValue;
        }
        params.beginDate="";
        if(begintime!=null&&begintime!=undefined){
            params.beginDate=$.trim(begintime);
        }
        params.endDate="";
        if(endtime!=null&&endtime!=undefined){
            params.endDate=$.trim(endtime);
        }

        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: "/selectRealTimeAlarmCount",
            cache: false,
            success: function (data) {
                //图形
                realtimeChat(data,'','marker2_chartmain');
            },
            error: function (a, b, c) {
                //alert(a);
                //console.log(a)
            }
        });
    }

    //增加监听事件
    function eConsole(param) {
        //var mes = '【' + param.type + '】';
        if (typeof param.seriesIndex != 'undefined') {
            // mes += '  seriesIndex : ' + param.seriesIndex;
            // mes += '  dataIndex : ' + param.dataIndex;
            if (param.type == 'click') {
                var peiLenght= option.legend.data.length;
                var type=param.componentSubType;
                var title="";
                var begintime=GetYearStr();
                var endtime=GetDateStr(0);

                if(type=="pie")
                {
                    title="风险处置分析";
                    var times=$("#chart_daterange").val();
                    if(times!="")
                    {
                        begintime=times.split('-')[0];
                        endtime=times.split('-')[1];
                    }
                }
                if(type=="bar")
                {
                    title="风险分布统计";
                    var times=$("#stack_daterange").val();
                    if(times!="")
                    {
                        begintime=times.split('-')[0];
                        endtime=times.split('-')[1];
                    }
                }
                if(type=="line")
                {
                    if(param.seriesName=="安全指数")
                    {
                        title="风险趋势分析";
                        var times=$("#marker_daterange").val();
                        if(times!="")
                        {
                            begintime=times.split('-')[0];
                            endtime=times.split('-')[1];
                        }
                    }
                    else
                    {
//                        title="车辆动态监控报警统计";
                        var times=$("#marker2_daterange").val();
                        if(times!="")
                        {
                            begintime=times.split('-')[0];
                            endtime=times.split('-')[1];
                        }
                    }

                }

               // dialog= GetConformDialog('/information?name_info='+param.name+'&type='+param.componentSubType+'&seriesName='+param.seriesName+'&begintime='+begintime+'&endtime='+endtime+'',title,'');
            }else{

               // document.getElementById('hover-console').innerHTML = 'Event Console : ' + param.dataIndex;
                //alert();
            }

        }
    }

    //车辆动态监控报警统计
    function realtimeChat(data,title,chatName) {
        option = {
            color:['#cc6666','#b22222','#A2CD5A','#9370DB','#A4D3EE'],
            title: {
                text: title,
                top:'0%',
                textStyle: {
                    color:'#000',
                    fontSize:18,
                    fontWeight:'bold',

                }

            },
            legend:{
                right:'0%',
                top:'8%',
                textStyle: {
                    color: '#666',
                    fontSize: 10,
                },
                data:['超速','路线偏离','疲劳驾驶','2-5点禁行','夜间三级路面'],
            },
            tooltip:{
                show:true,
                trigger: 'axis',
                axisPointer: {
                    type:'cross',
                    crossStyle:{
                        color:'#ddd',

                    },

                },
            },

            toolbox:{
                right:20,
                feature:{
                    saveAsImage: {},
                    restore: {},
                    dataView: {},
                    dataZoom: {},
                    magicType: {
                        type:['line','bar']
                    }
                }
            },
            grid:{
                left:5,
                right:20,
                top:80,
                bottom:50,
                containLabel:true,
            },
            xAxis: {
                show:true,

                axisLabel:{
                    interval:'auto',
                    textStyle:{
                        color:'#000',
                        align: 'center'

                    },
                    formatter:function(value){
                        if(value.length>5){
                            value = value.substring(0,5)+"...";
                        }
                        return value;
                    }
                },
                axisTick:{
                    alignWithLabel:true,
                    lineStyle:{
                        color:'#000',

                    },
                },
                data:data.companyNameList,
            },
            yAxis: [
                {
                    type:'value',
                    axisLabel:{
                        textStyle:{
                            color:'#000',
                        },
                    },
                    splitLine:{
                        show:false,
                    },
                }
            ],
            dataZoom: [{
                show: true,
                height:30,
                bottom:5,
                start: 0,
                end: 50
            },
                { type: "inside"}
            ],
            series: [
                {
                    type: 'bar',
                    name:'超速',
                    stack:'总数',
                    data:data.overspeedNumList,
                    label: {
                        normal: {
                            //show:true,
                            position: 'insideTop',
                            offset:[0,20],
                            textStyle:{
                                color:'#000',
                            },
                        },
                        emphasis: {
                            textStyle:{
                                color:'#000',
                            },
                        },
                    },
                },
                {
                    type: 'bar',
                    name:'路线偏离',
                    stack:'总数',
                    data:data.offsetNumList,
                    label: {
                        normal: {
                            //show:true,
                            position: 'insideTop',
                            offset:[0,20],
                            textStyle:{
                                color:'#000',
                            },
                        },
                        emphasis: {
                            textStyle:{
                                color:'#fff',
                            },
                        },
                    },
                },
                {
                    type: 'bar',
                    name:'疲劳驾驶',
                    stack:'总数',
                    data:data.tiredDrivingNumList,
                    label: {
                        normal: {
                            //show:true,
                            position: 'insideTop',
                            offset:[0,20],
                            textStyle:{
                                color:'#000',
                            },
                        },
                        emphasis: {
                            textStyle:{
                                color:'#fff',
                            },
                        },
                    },
                },

                {
                    type: 'bar',
                    name:'2-5点禁行',
                    stack:'总数',
                    data:data.twoFiveNumList,
                    label: {
                        normal: {
                            //show:true,
                            position: 'insideTop',
                            offset:[0,20],
                            textStyle:{
                                color:'#000',
                            },
                        },
                        emphasis: {
                            textStyle:{
                                color:'#fff',
                            },
                        },
                    },
                },
                {
                    type: 'bar',
                    name:'夜间三级路面',
                    stack:'总数',
                    data:data.threeNightNumList,
                    label: {
                        normal: {
                            //show:true,
                            position: 'insideTop',
                            offset:[0,20],
                            textStyle:{
                                color:'#000',
                            },
                        },
                        emphasis: {
                            textStyle:{
                                color:'#fff',
                            },
                        },
                    },
                }
            ]
        };
        //初始化echarts实例
        myChart1 = echarts.init(document.getElementById(chatName));

        //使用制定的配置项和数据显示图表
        myChart1.setOption(option);
        myChart1.on("click", eConsole);
        myChart1.on("hover", eConsole);
    }
    //车辆动态监控报警统计(----完---)

    function getBoforeDate(before) {
        var now = new Date();
        now.setDate(now.getDate() - before);
        return now;
    }

    function getBoforeMonth(beforeMonth, day) {
        var now = new Date();
        now.setDate(day);
        now.setMonth(now.getMonth() - beforeMonth);
        return now;
    }

    function DateInfo() {
        var now = new Date();
        $('.date').daterangepicker({
            "showWeekNumbers": true,
            "showISOWeekNumbers": true,
            "ranges": {
                "今天": [
                    now,
                    now
                ],
                "昨天": [
                    getBoforeDate(1),
                    getBoforeDate(1)
                ],
                "最近7天": [
                    getBoforeDate(6),
                    now
                ],
                "最近30天": [
                    getBoforeDate(30),
                    now
                ],
                "本月": [
                    getBoforeMonth(0, 1),
                    getBoforeMonth(0, 31)
                ],
                "上个月": [
                    getBoforeMonth(1, 1),
                    getBoforeMonth(1, 31)
                ],
                "最近三个月": [
                    getBoforeMonth(2, 1),
                    getBoforeMonth(0, 31)
                ]
            },
            "locale": {
                "format": "YYYY/MM/DD",
                "separator": "-",
                "applyLabel": "应用",
                "cancelLabel": "取消",
                "fromLabel": "From",
                "toLabel": "To",
                "customRangeLabel": "自定义",
                "weekLabel": "W",
                "daysOfWeek": [
                    "日",
                    "一",
                    "二",
                    "三",
                    "四",
                    "五",
                    "六"
                ],
                "monthNames": [
                    "一月",
                    "二月",
                    "三月",
                    "四月",
                    "五月",
                    "六月",
                    "七月",
                    "八月",
                    "九月",
                    "十月",
                    "十一月",
                    "十二月"
                ],
                "firstDay": 1
            },
            "alwaysShowCalendars": true,
            "autoUpdateInput": false,
            "opens": "left",
            "buttonClasses": "btn btn-sm"
        }, function (start, end, label) {
            console.log("New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')");
        });

        $('.date').on('apply.daterangepicker', function (ev, picker) {

            $(this).val(picker.startDate.format('YYYY/MM/DD 00:00:00') + ' - ' + picker.endDate.format('YYYY/MM/DD 23:59:59'));
            if(ev.currentTarget.id=="chart_daterange")
            {
                safeProdTotalScore();
            }
            if(ev.currentTarget.id=="stack_daterange")
            {
                riskAlarmLevelStatistics();
            }
            if(ev.currentTarget.id=="marker_daterange")
            {
                networkedTotalScore();
            }
            if(ev.currentTarget.id=="marker2_daterange")
            {
                alarmRealtime();
            }
        });

        $('.date').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
        });
    }

    //安全生产达标考核得分
    function safeProdTotalScore() {
        var params = {};
        params.orderDesc = 'asc';
        var orderDesc = $('input:radio[name="chartmain_orderDesc"]:checked').val();
        if(orderDesc != undefined && orderDesc != ''){
            params.orderDesc = orderDesc;
        }
        params.riskType = 1;
        params.industryType = 1;
        var industryType = $.trim($('#chartmain_TypeId').val());
        if(industryType != undefined && industryType != ''){
            params.industryType = industryType;
        }
        var times=$("#chart_daterange").val();
        if(times!=null && times!="")
        {
            params.beginDate = times.split('-')[0];
            params.endDate = times.split('-')[1];
        }

        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: '/getTotalScore',
            cache: false,
            success: function (data) {
                //初始化echarts实例
                myChart4 = echarts.init(document.getElementById('chartmain'));
                //使用制定的配置项和数据显示图表
                myChart4.setOption(safeProdInfo(data));
                myChart4.on("click", eConsole);
                myChart4.on("hover", eConsole);
            },
            error: function (a) {
               // alert(a);
            }
        });
    }

    function safeProdInfo(data,title,charName) {
        option = {
            "title": {
                "text": title,
                "subtext": "",
                "top":'0%'
            },
            "grid":{
                "left":5,
                "right":20,
                "top":80,
                "bottom":50,
                "containLabel":true,
            },
            "tooltip":{
                "show":true,
                "trigger": 'axis',
                "axisPointer": {
                    "type":'cross',
                    "crossStyle":{
                        "color":'#ddd',
                    },
                },
            },
            "toolbox":{
                "right":20,
                "feature":{
                    "saveAsImage": {},
                    "restore": {},
                    "dataView": {},
                    "dataZoom": {},
                    "magicType": {
                        "type":['line','bar']
                    }
                }
            },
            "calculable": true,
            "xAxis": [
                {
                    "type": "category",
                    "scale": true,
                    "axisLabel": {
                        //"rotate": 45,
                        "show": true,
                        "textStyle": {
                            "fontFamily": "微软雅黑",
                            "fontSize": 12
                        },
                        formatter:function(value){
                            if(value.length>5){
                                value = value.substring(0,5)+"...";
                            }
                            return value;
                        }
                    },
                    "data": data.nameList,
                }
            ],
            "yAxis": [
                {
                    axisLabel:{
                        textStyle:{
                            color:'#000',
                        },
                    },
                    splitLine:{
                        show:false,
                    },
                }
            ],
            "dataZoom": [
                {
                    "show": true,
                    "height": 30,
                    "xAxisIndex": [
                        0
                    ],
                    "bottom":5,
                    "start": 0,
                    "end": 50
                },
                { type: "inside"}
            ],
            "series": [
                {
                    "name": "考核得分",
                    "type": "bar",
                    "itemStyle": {
                        "normal": {
                            "color": "#59ADF3"
                        }
                    },
                    "data":data.scoreList
                }
            ]
        };
        return option;
    }

    //联网联控考核得分
    function networkedTotalScore() {
        var params = {};
        params.orderDesc = 'asc';
        var orderDesc = $('input:radio[name="marker_chartmain_orderDesc"]:checked').val();
        if(orderDesc != undefined && orderDesc != ''){
            params.orderDesc = orderDesc;
        }
        params.riskType = 2;
        var times=$("#marker_daterange").val();
        if(times!=null && times!="")
        {
            params.beginDate = times.split('-')[0];
            params.endDate = times.split('-')[1];
        }

        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: '/getTotalScore',
            cache: false,
            success: function (data) {
                //初始化echarts实例
                myChart2 = echarts.init(document.getElementById('marker_chartmain'));
                //使用制定的配置项和数据显示图表
                myChart2.setOption(safeProdInfo(data));
                myChart2.on("click", eConsole);
                myChart2.on("hover", eConsole);
            },
            error: function (a) {
               // alert(a);
            }
        });
    }

    //企业风险统计
    function riskAlarmLevelStatistics() {
        var params = {};
        params.orderDesc = 'asc';
        var risktype = $.trim($('#risktype').val());
        params.riskType = risktype;
        var orderDesc = $('input:radio[name="stack_chartmain_orderDesc"]:checked').val();
        if(orderDesc != undefined && orderDesc != ''){
            params.orderDesc = orderDesc;
        }
        var times=$("#stack_daterange").val();
        if(times!=null && times!="")
        {
            params.beginDate = times.split('-')[0];
            params.endDate = times.split('-')[1];
        }

        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: '/getRiskAlarmLevelStatistics',
            cache: false,
            success: function (data) {
                riskAlarmLevel(data,'','stack_chartmain');
            },
            error: function (a) {
               // alert(a);
            }
        });
    }
    function riskAlarmLevel(data,title,charName) {
        option = {
            "title": {
                "text": title,
                "top":'0%',
            },
            "tooltip": {
                "trigger": "axis"
            },
            legend: {
                "right":'0%',
                top:'8%',
                textStyle: {
                    color: '#666',
                    fontSize: 10,
                },
                data:['重大风险','中度风险','一般风险']
            },
            toolbox:{
                right:20,
                feature:{
                    saveAsImage: {},
                    restore: {},
                    dataView: {},
                    dataZoom: {},
                    magicType: {
                        type:['line','bar']
                    }
                }
            },
            tooltip:{
                show:true,
                trigger: 'axis',
                axisPointer: {
                    type:'cross',
                    crossStyle:{
                        color:'#ddd',
                    },
                },
            },
            grid:{
                left:5,
                right:20,
                top:80,
                bottom:50,
                containLabel:true,
            },
            "calculable": true,
            "xAxis": [
                {
                    "type": "category",
                    "scale": true,
                    "axisLabel": {
                        //"rotate": 45, //倾斜角度
                        "show": true,
                        "splitNumber": 5,
                        "textStyle": {
                            "fontFamily": "微软雅黑",
                            "fontSize": 12
                        },
                        formatter:function(value){
                            if(value.length>5){
                                value = value.substring(0,5)+"...";
                            }
                            return value;
                        }
                    },
                    "data": data.nameList,
                }
            ],
            "yAxis": [
                {
                    axisLabel:{
                        textStyle:{
                            color:'#000',
                        },
                    },
                    splitLine:{
                        show:false,
                    },
                }
            ],
            "dataZoom": [
                {
                    "show": true,
                    "height": 30,
                    "bottom":5,
                    "start": 0,
                    "end": 50
                },
                { type: "inside"}
            ],
            "series": [
                {
                    "name": "重大风险",
                    "type": "bar",
                    "stack": "风险数量", //相同名称的strack可以堆叠显示
                    "itemStyle": {
                        "normal": {
                            "color": "#003366"
                        }
                    },
                    "data": data.seriousList
                },

                {
                    "name": "中度风险",
                    "type": "bar",
                    "stack": "风险数量",
                    "itemStyle": {
                        "normal": {
                            "color": "#59ADF3",
                        }
                    },
                    "data": data.moderateList
                },
                {
                    "name": "一般风险",
                    "type": "bar",
                    "stack": "风险数量",
                    "itemStyle": {
                        "normal": {
                            "color": "#86D560",
                        }
                    },
                    "data": data.mildList
                }
            ]
        };
        //初始化echarts实例
        myChart3 = echarts.init(document.getElementById(charName));
        //使用制定的配置项和数据显示图表
        myChart3.setOption(option);
        myChart3.on("click", eConsole);
        myChart3.on("hover", eConsole);

    }

    $(function() {
        myChart1.on('dblclick', function (params) {
            var groupname = params.name;
            var times =  $("#marker2_daterange").val();
            var begintime="";
            var endtime="";
            if(times!=null&&times!=""&&times != undefined)
            {
                begintime=Date.parse(new Date(times.split('-')[0]));
                endtime=Date.parse(new Date(times.split('-')[1]));
            }
            var time = begintime +'-'+endtime;
            var risktype=3;
            var url='/tbRiskIndexAlarmRemindUser/preList?';
            redirect2safeList(url,groupname,time,risktype);
        });
    });
    $(function() {
        myChart2.on('dblclick', function (params) {
            var groupname = params.name;
            var url='/security/tbCompanyAssessmentScoreDetail/index?';
            redirect2safeList(url,groupname,"","");
        });
    });
    $(function() {
        myChart3.on('dblclick', function (params) {
            var groupname = params.name;
            var times = $("#stack_daterange").val();
            var begintime = "";
            var endtime = "";
            if (times != null && times != "" && times != undefined) {
                begintime = Date.parse(new Date(times.split('-')[0]));
                endtime = Date.parse(new Date(times.split('-')[1]));
            }
            var time = begintime + '-' + endtime;
            var risktype = $.trim($('#risktype').val());
            var url = '/tbRiskIndexAlarmRemindUser/preList?';
            redirect2safeList(url, groupname, time, risktype);
        });
    });
    $(function() {
        myChart4.on('dblclick', function (params) {
            var groupname = params.name;
            var industryType = $.trim($('#chartmain_TypeId').val());
            var url='/security/tbCompanyAssessmentScoreDetail/index?';
            redirect2safeList(url,groupname,"",industryType);
        });
    });

    function redirect2safeList(url,groupname,times,type){
        if(groupname!=null&&groupname!=""&&groupname!="undefined"){
            url+="&groupname="+groupname;
        }
        if(type!=null&&type!="undefined") {
            url += "&type=" + type;
        }
        if(times!=null&&times!="undefined") {
            url += "&times=" + times;
        }
        $("#sec_content_iframe").hide();
        $("#sec_content").html('');
        $('#external-frame').attr('src','');
        $("#sec_content").load(url);
        $("#sec_content").show();
    }
</script>
</@footer>