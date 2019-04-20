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
    .nums-box > .inner{
        padding:10px 20px;
    }
    .index-box .row .indexbox{
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
    .innerInfo p{
        font-size: 12px;
        color: #999;
    }
    .innerInfo p span{
        color: #666;
        margin-right:10px;
    }
    .index-box .row .searchBox{
        height:123px;
        line-height: 123px;
        border: none;
        background-color: #fff;
        padding:0;
        text-align: center;
        margin-top: 5px;
    }
    .index-box .chartDiv{
        background-color: #fff;
        border:0;
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
    .topRow .col-sm-6{
        padding:5px 2px;
    }
    .dayData .col-sm-6{
        padding:0 2px;
    }
    .index-box .dayData{
        padding: 0 22px;
    }
    .dayData .titTxt p{
        padding: 0 20px;
    }
    .dayData .inner{
        padding:10px 5px;
    }
    .dayData .titTxt .nums{
        padding:0 20px;
    }
    .chartBox{
        margin-top:20px;
    }
    .chartBox .col-sm-6{
        padding:0 10px;
        margin-bottom:20px;
    }
    .index-box .chartBox{
        padding:0 15px;
    }
    }
  .index-box .searchBox  #searchtext{
        width:76%;border:none;height: 40px;line-height: 40px;background-color: #f9f9f9;padding:0;margin-right:0;
    }
   .index-box .searchBox #searchtext:focus{
        border:none!important;
    }
    .titBox{
        overflow: hidden;
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
        <div class="row topRow">
            <div class=" col-md-3 col-sm-6">
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
                            <p class="direction">
                                危货 <span>${vehicleAllInfo.x?c}</span>
                                普货 <span>${vehicleAllInfo.h?c} </span>
                                客车 <span>${vehicleAllInfo.k?c}</span>
                            </p>
                        </div>
                    </div>


                </div>
            </div><!-- ./col -->

            <div class=" col-md-3 col-sm-6">
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
                            <p class="direction">
                                驾驶员<span>${employeeAllInfo.driver?c}</span>
                                押运员 <span>${employeeAllInfo.supercargo?c}</span>
                                装卸员 <span>${employeeAllInfo.loading?c}</span>
                            </p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->


            <div class="col-md-6 col-sm-12" style="padding:0 2px">
                <div class="searchBox  indexbox">
                    <div class="input-group" style="position: relative;height:40px;line-height: 40px;background-color: #f9f9f9;border:1px solid #f1f1f1;box-sizing: border-box;padding:0;width:90%;margin:6% auto;">
                        <div class="selectBox" style="position: relative;width:10%;height: 40px;overflow: hidden;float:left">
                            <select id="suggest_select" class="form-control" style="width:100px;height:40px;line-height:40px;margin-right:3px;position: absolute;top:0;left:-74px;border:none;z-index:2;background-color: #f9f9f9;">
                                <option value="1">从业人员</option>
                                <option value="2">车辆</option>
                                <option value="3">企业</option>
                            </select>
                        </div>

                    <#--<input type="text" id="input_employee_id"  name="input_employee_id" class="form-control" style="width:60%" placeholder="车牌号、姓名、身份证号">-->
                        <input type="hidden"  id="no" name="no" />
                        <input type="text"  id="searchtext" name="searchtext" class="form-control"  autocomplete="off"  onkeyup="fuzzySearch();" placeholder="从业人员/身份证号" style="width:76%;border:none;height: 40px;line-height: 40px;background-color: #f9f9f9;padding:0;margin-right:0;"/>
                        <div style="width:100%;position: absolute;top:39px;left:0;z-index:99;height:200px;overflow: auto">
                            <ul  id="suggest_ul"></ul>
                        </div>
                        <img src="/ui/css/images/searchIcon.png" alt="查询" style="width:23px;height:24px;vertical-align: middle;cursor: pointer" id="driver_employee">
                    </div>
                </div>
            </div><!-- ./col -->
            <div class="col-sm-4">
            </div>
            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>
        </div><!-- /.row -->

        <p class="tit">本月数据</p>
        <div class="row">
           <div class="col-sm-9" style="padding:0;">
               <div class="col-lg-3 col-md-6">
                   <!-- small box -->
                   <div class="small-box  indexbox top-box">
                       <div class="inner">
                           <div class="icon">
                               <i class="top-icon"><img src="../ui/css/images/vehicleNums.png" alt="车辆报警"></i>
                           </div>
                           <div class="innerInfo titTxt">
                               <p>车辆报警</p>
                               <p><span class="nums">${(index.month_alarm)!}</span>(条)</p>
                               <p class="direction">较上月同期 <span>${(index.month_term_alarm)!}</span></p>
                           </div>
                       </div>
                   </div>
               </div><!-- ./col -->

               <div class="col-lg-3 col-md-6">
                   <!-- small box -->
                   <div class="small-box indexbox top-box ">
                       <div class="inner">
                           <div class="icon">
                               <i class="top-icon"><img src="../ui/css/images/vehicleNums.png" alt="事故车次"></i>
                           </div>
                           <div class="innerInfo titTxt">
                               <p>事故车次</p>
                               <p><span class="nums">${(index.month_accident)!}</span>(条)</p>
                               <p  class="direction">较上月同期 <span>${(index.month_term_accident)!}</span></p>
                           </div>
                       </div>
                   </div>
               </div><!-- ./col -->

               <div class="col-lg-3 col-md-6">
                   <!-- small box -->
                   <div class="small-box  indexbox top-box">
                       <div class="inner">
                           <div class="icon">
                               <i class="top-icon"><img src="../ui/css/images/vehicleNums.png" alt="安全学习完成率"></i>
                           </div>
                           <div class="innerInfo titTxt">
                               <p>安全学习完成率</p>
                               <p><span class="nums">${(index.month_study)!}</span></p>
                               <p class="direction">较上月同期 <span>${(index.month_term_study)!}</span></p>
                           </div>
                       </div>
                   </div>
               </div><!-- ./col -->

               <div class="col-lg-3 col-md-6">
                   <!-- small box -->
                   <div class="small-box indexbox top-box">
                       <div class="inner">
                           <div class="icon">
                               <i class="top-icon"><img src="../ui/css/images/vehicleNums.png" alt="保险到期"></i>
                           </div>
                           <div class="innerInfo titTxt">
                               <p>车辆保险到期 </p>
                               <p><span class="nums">${(index.month_safe)!}</span>(条)</p>
                               <p class="direction">较上月同期 <span>${(index.month_term_safe)!}</span></p>
                           </div>
                       </div>
                   </div>
               </div><!-- ./col -->
           </div>
            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>
        </div><!-- /.row -->
        <p class="tit">本日数据</p>
        <div class="row dayData">
            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box  indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/svehicleAlarm.png" alt="车辆报警"></i>
                        </div>
                        <div class="innerInfo titTxt">
                             <p>车辆报警</p>
                            <p><span class="nums">${(index.day_alarm)!}</span>条</p>
                            <p class="direction">较上日同期${(index.day_term_alarm)!}</p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->

            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/svehicleYear.png" alt="车辆年审"></i>
                        </div>
                        <div class="innerInfo titTxt">
                            <p>车辆年审</p>
                            <p><span class="nums">${(index.day_annual)!}</span>条</p>
                            <p class="direction">较上日同期${(index.day_term_annual)!}</p>
                        </div>
                    </div>


                </div>
            </div><!-- ./col -->

            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box  indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/sVehicleXiu.png" alt="车辆维护"></i>
                        </div>
                        <div class="innerInfo titTxt">
                            <p>车辆维护</p>
                            <p><span class="nums">${(index.day_defend)!}</span>条</p>
                            <p class="direction">较上日同期${(index.day_term_defend)!}</p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->

            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/soutTime.png" alt="证件到期"></i>
                        </div>
                        <div class="innerInfo titTxt">
                            <p>证件到期</p>
                            <p><span class="nums">${(index.day_paper)!}</span>条</p>
                             <p class="direction">较上日同期${(index.day_term_paper)!}</p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->
            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/sVehicleNums.png" alt="事故车次"></i>
                        </div>
                        <div class="innerInfo titTxt">
                            <p>事故车次</p>
                            <p><span class="nums">${(index.day_accident)!}</span>条</p>
                            <p class="direction">较上日同期${(index.day_term_accident)!}</p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->
            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/scardOuttime.png" alt="保险到期"></i>
                        </div>
                        <div class="innerInfo titTxt">
                            <p>车辆保险到期</p>
                            <p><span class="nums">${(index.day_safe)!}</span>条</p>
                            <p class="direction">较上日同期${(index.day_term_safe)!}</p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->
            <div class="col-lg-2 col-md-3 col-sm-6">
                <!-- small box -->
                <div class="small-box indexbox">
                    <div class="inner">
                        <div class="icon">
                            <i class="day-icon"><img src="../ui/css/images/ssafeStudy.png" alt="安全学习完成人数"></i>
                        </div>
                        <div class="innerInfo titTxt">
                            <p style="margin-left:0">安全学习完成人数</p>
                            <p><span class="nums">${(index.day_study)!}</span>人</p>
                            <p class="direction">较上日同期${(index.day_term_study)!}</p>
                        </div>
                    </div>
                </div>
            </div><!-- ./col -->
            <!-- fix for small devices only -->
            <div class="clearfix visible-sm-block"></div>
        </div><!-- /.row -->

<div class="row chartBox">
    <input type="hidden" name="groupid" id="groupid" value="${(groupid)!}" >
        <div  class="col-sm-6 col-xs-12">
                <div class="chartDiv">
                    <p class="chartTit">企业风险分级趋势分析</p>
                    <#--<input type="text" name="marker2_daterange"  class="form-control date"
                           id="marker2_daterange" placeholder="开始日期  - 结束日期" style="width: 260px;">-->
                    <div id="marker2_chartmain" style="width:100%; height: 400px;margin-top:36px">
                        <div class="loadinggg" style="text-align: center;font-size:11px">加载中......</div>
                    </div>
                </div>
        </div>
        <div  class="col-sm-6 col-xs-12">
            <div class="chartDiv">
                <p  class="chartTit">联网联控考核统计</p>
                <input type="text" name="marker_daterange"  class="form-control date"
                       id="marker_daterange" placeholder="开始日期  - 结束日期" style="width: 260px;">
                <div id="marker_chartmain" style="width:100%; height: 400px;margin-top:36px">
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
                   <select name="risktype" id="risktype"  onchange="GetStackInfo()"class="form-control" style="width:94px;display: inline-block;margin-right: 5px;float: right">
                       <#list fxzbConfigSeconds  as config >
                           <option value="${(config.second_mark)!}"> ${(config.second_name)!}</option>
                       </#list>
                   </select>
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
                          id="chart_daterange" placeholder="开始日期  - 结束日期" style="width: 170px;">
                   <select name="industryType" id="industryType" onchange="GetChartInfo()" class="form-control" style="width:140px;display: inline-block;margin-right: 5px;float: right">
                       <#list companyTypeList as c>
                           <option value="${(c.second_mark)!}">${(c.second_name)!}</option>
                       </#list>
                   </select>
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
        $("#suggest_ul").hide(0);
        var select = $("#suggest_select").val();
        if(select==1) {
            $("#no").val($(thisLi).find("span").eq(1).html());
        }else{
            $("#no").val($(thisLi).find("span").eq(0).html());
        }
    }


    $(function(){
//载入时隐藏下拉li
        $("#suggest_ul").hide(0);
    });

    function fuzzySearch(){
//如果文本框为空，不发送请求
        if($("#searchtext").val().length==0||$("#searchtext").val().length>10){
            $("#suggest_ul").hide(0);
            return;
        }
//发送请求
        var groupid = $("#groupid").val();
        var text=$("#searchtext").val();
        $.ajax({
            type:'post',
            url:url,
            data:{test:text,
                  groupid:groupid},
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
                    $("#suggest_ul").show(300);
                }
                else {
                    $("#suggest_ul").hide(0);
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
            $("#suggest_ul").hide();
        });
        $("#suggest_ul").click(function(event){
            event.stopPropagation();
        });

    });


</script>
<script>
    var now = new Date();
    var end_date = new Date();
    end_date.setDate(end_date.getDate() - 6);
    var year1=end_date.getFullYear();
    var year2=now.getFullYear();
    var month1=end_date.getMonth()+1;
    var month2=now.getMonth()+1;
    var day1=end_date.getDate();
    var day2=now.getDate();
    $("#marker_daterange").val(year1+'/'+month1+'/'+day1+' '+'00:00:00'+ ' - ' +year2+'/'+month2+'/'+day2+' '+'23:59:59');
    $("#stack_daterange").val(year1+'/'+month1+'/'+day1+' '+'00:00:00'+ ' - ' +year2+'/'+month2+'/'+day2+' '+'23:59:59');
    $("#chart_daterange").val(year1+'/'+month1+'/'+day1+' '+'00:00:00'+ ' - ' +year2+'/'+month2+'/'+day2+' '+'23:59:59');
    var myChart1='';
    var myChart2='';
    var myChart3='';
    var myChart4='';
    //定义两个时间变量，用于存储x轴上的起始时间
    var starthour = "";
    var endhour = "";
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
        $('#driver_employee').on('click',function(){
            var select = $("#suggest_select").val();
            var no = $("#no").val();
            if(no!=""&&no!=null&&no!=undefined) {
                if (select == 1) {
                    window.open("/employeeindex?employee_no=" + no);//打开新页签
                } else if (select == 2) {
                    window.open("/vehicleindex?vehicle_no=" + no);
                } else {
                    window.open("/companyindex?company_no=" + no);
                }
            }else {
                Commonalert("请选择相应的人员、车辆或者企业，再查询");
                return false;
            }        })
        var groupid = $("#groupid").val();
        var industrytype=$("#industryType").val();
        var risktype=$("#risktype").val();
        //单折线图，，企业安全生产达标考核统计
        ChartInfo(0,0,groupid,industrytype);//默认传0，在后台获取当前时间

        //柱状图
        StockInfo(0,0,groupid,risktype);//默认传0，在后台获取当前时间
        
        //单折线
        MarkerInfo(0,0,groupid);//默认传0，在后台获取当前时间

        //多折线
        MarkersInfo(0,0,groupid);
        DateInfo();
    });
    
    function ChartInfo(begintime,endtime,groupid,industrytype) {
        //单折线图，企业安全生产达标考核统计
        $.ajax({
            type: 'post',
            async: false,
            url: "/loadRiskInfo",
            data:{begintime:begintime,endtime:endtime,groupid:groupid,industrytype:industrytype},
            cache: false,
            success: function (data) {
                //单折线图
                pieInfo(data);
            },
            error: function (a, b, c) {
               // alert(a);
               // console.log(a)
            }
        });
    }
    
    function StockInfo(begintime,endtime,groupid,risktype) {
        var params = {};
        params.begintime=begintime;
        params.endtime=endtime;
        params.groupid=groupid;
        params.risktype=risktype;

        //柱状图
        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: "/loadStackInfo",
            cache: false,
            success: function (data) {
                //柱状图
                stackInfo(data);
            },
            error: function (a, b, c) {
                //alert(a);
                //console.log(a)
            }
        });
    }

    function MarkerInfo(begintime,endtime,groupid) {
        //单折线
        $.ajax({
            type: 'post',
            async: false,
            url: "/loadMarkerInfo",
            data:{begintime:begintime,endtime:endtime,groupid:groupid},
            cache: false,
            success: function (data) {
                //单折线
                markerInfo(data);
            },
            error: function (a, b, c) {
               // alert(a);
               // console.log(a)
            }
        });
    }
    
    function MarkersInfo(begintime,endtime,groupid) {

        var params = {};
        //params.begintime="";
        //params.endtime="";
        params.groupId=groupid;
        //多折线
        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: "/loadMarkersInfo",
            cache: false,
            success: function (data) {
               marker2Info(data,'车辆动态监控报警统计','marker2_chartmain');
                starthour = data.hourList[0].split(":")[0];
                endhour = data.hourList[data.hourList.length-1].split(":")[0];
            },
            error: function (a, b, c) {
               // alert(a);
               // console.log(a)
            }
        });
    }

    //单折线，企业安全生产达标------onchange
    function GetChartInfo() {
        var groupid = $("#groupid").val();
        var industrytype=$("#industryType").val();
        var times=$("#chart_daterange").val();
        var begintime="";
        var endtime="";
        if(times!=null && times!="")
        {
            begintime=times.split('-')[0];
            endtime=times.split('-')[1];
            var bt = new Date(begintime);
            var et = new Date(endtime);
            var total = (et.getTime() - bt.getTime())/1000;
            var day = parseInt(total / (24*60*60));//计算整数天数
            if(day >= 7){
                Commonalert("日期间隔天数不能大于7天!");
                return false;
            }
            ChartInfo(begintime,endtime,groupid,industrytype);
        }else{
            ChartInfo(0,0,groupid,industrytype);
        }
    }
    //柱状图------onchange
    function GetStackInfo() {
        var groupid = $("#groupid").val();
        var risktype = $("#risktype").val();
        var times=$("#stack_daterange").val();
        var begintime="";
        var endtime="";
        if(times!=null && times!="")
        {
            begintime=times.split('-')[0];
            endtime=times.split('-')[1];
            var bt = new Date(begintime);
            var et = new Date(endtime);
            var total = (et.getTime() - bt.getTime())/1000;
            var day = parseInt(total / (24*60*60));//计算整数天数
            if(day >= 7){
                Commonalert("日期间隔天数不能大于7天!");
                return false;
            }
            StockInfo(begintime,endtime,groupid,risktype);
        }else{
            StockInfo(0,0,groupid,risktype);
        }
    }

    //单折线------onchange
    function GetMarkerInfo() {
        var groupid = $("#groupid").val();
        var times=$("#marker_daterange").val();
        var begintime="";
        var endtime="";
        if(times!=null && times!="")
        {
            begintime=times.split('-')[0];
            endtime=times.split('-')[1];
            var bt = new Date(begintime);
            var et = new Date(endtime);
            var total = (et.getTime() - bt.getTime())/1000;
            var day = parseInt(total / (24*60*60));//计算整数天数
            if(day >= 7){
                Commonalert("日期间隔天数不能大于7天!");
                return false;
            }
            MarkerInfo(begintime,endtime,groupid);
        }else{
            MarkerInfo(0,0,groupid);
        }
    }

    /*//多折线------onchange
    function GetMarker2Info() {
       /!* var times=$("#marker2_daterange").val();
        var begintime="";
        var endtime="";
        if(times!=null)
        {
            begintime=times.split('-')[0];
            endtime=times.split('-')[1];
        }*!/
        MarkersInfo(begintime,endtime);

    }*/


    //折线图
    function pieInfo(data) {
        option = {
            title: {
//                text: '企业安全生产达标考核统计'
            },
            grid:{
                left:5,
                right:20,
                top:80,
                bottom:50,
                containLabel:true,
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#283b56'
                    }
                }
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            dataZoom: {
                show: true,
                start: 40,
                end: 100,
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    data:data.title,
                    axisLabel:{
                        interval: 0
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    scale: true,
                    name: '分数'
                }
            ],
            series: data.rows
        };
        //初始化echarts实例
        myChart4 = echarts.init(document.getElementById('chartmain'));

        //使用制定的配置项和数据显示图表
        myChart4.setOption(option);
        myChart4.on("click", eConsole);
        myChart4.on("hover", eConsole);
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
//                        title="企业风险分级趋势分析";
                        /*var times=$("#marker2_daterange").val();
                        if(times!="")
                        {
                            begintime=times.split('-')[0];
                            endtime=times.split('-')[1];
                        }*/
                    }

                }


               // dialog= GetConformDialog('/information?name_info='+param.name+'&type='+param.componentSubType+'&seriesName='+param.seriesName+'&begintime='+begintime+'&endtime='+endtime+'',title,'');
            }else{

               // document.getElementById('hover-console').innerHTML = 'Event Console : ' + param.dataIndex;
                //alert();
            }

        }
    }

    function stackInfo(data) {
        option = {
            "title": {
//                "text": "企业风险统计",
                "subtext": "",
                x: "4%",
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
            legend: {
                data:['一般风险','中度风险','重大风险']
            },
            "toolbox": {
                "show": true,
                "feature": {
                    "restore": { },
                    "saveAsImage": { }
                }
            },
            "calculable": true,
            "xAxis": [
                {
                    "type": "category",
                    "scale": true,
                    "axisLabel": {
                        interval: 0,
                        "show": true,
                        "splitNumber": 5,
                        "textStyle": {
                            "fontFamily": "微软雅黑",
                            "fontSize": 12
                        }
                    },
                    "data": data.title,
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
                    "start": 40,
                    "end": 100
                }
            ],

            "series":[
                {
                    "name": "一般风险",
                    "type": "bar",
                    "stack": "风险数量", //相同名称的strack可以堆叠显示
                    "itemStyle": {
                        "normal": {
                            "color": "#000"
                        }
                    },
                    "data": data.data
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
                    "data": data.data
                },
                {
                    "name": "重大风险",
                    "type": "bar",
                    "stack": "风险数量",
                    "itemStyle": {
                        "normal": {
                            "color": "#86D560",
                        }
                    },
                    "data": data.data2
                }
            ]
        }
        //初始化echarts实例
        myChart3 = echarts.init(document.getElementById('stack_chartmain'));

        //使用制定的配置项和数据显示图表
        myChart3.setOption(option);
        myChart3.on("click", eConsole);
        myChart3.on("hover", eConsole);
    }
    //单折线图
    function markerInfo(data) {
        option = {
            title: {
//                text: '联网联控考核统计'
            },
            grid:{
                left:5,
                right:20,
                top:80,
                bottom:50,
                containLabel:true,
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#283b56'
                    }
                }
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {readOnly: false},
                    restore: {},
                    saveAsImage: {}
                }
            },
            dataZoom: {
                show: true,
                start: 40,
                end: 100,
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: true,
                    data:data.title,
                    axisLabel:{
                        interval: 0
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    scale: true,
                    name: '分数'
                }
            ],
            series: data.rows
        };
        //初始化echarts实例
        myChart2 = echarts.init(document.getElementById('marker_chartmain'));

        //使用制定的配置项和数据显示图表
        myChart2.setOption(option);
        myChart2.on("click", eConsole);
        myChart2.on("hover", eConsole);
    }

    //多折线图
    function marker2Info(data,title,chatName) {
          var datas=data;
          var overspeedNumList=datas.overspeedNumList;
          var tiredDrivingNumList=datas.tiredDrivingNumList;
         //data.overspeedNumList=[660, 841, 521, 820, 578, 727, 598, 792, 701, 660];
         //data.tiredDrivingNumList=[541, 513, 792, 701, 660, 729, 782, 660, 841, 521];
         //data.offsetNumList=[660, 841, 521, 820, 578, 727, 598, 792, 701, 660];
         //data.twoFiveNumList=[541, 513, 792, 701, 660, 729, 782, 660, 841, 521];
         //data.threeNightNumList=[660, 841, 521, 820, 578, 727, 598, 792, 701, 660];

        //data.companyNameList=['奥伦达部落','皇后镇休闲度假村','顶秀美泉小镇','印象山庄','普拉托休闲小镇','爱斐堡国际酒庄','古北水镇','青龙峡','云蒙山','雁栖湖'];
        option = {
            color:['#cc6666','#b22222','#A2CD5A','#9370DB','#A4D3EE'],
            title: {
//                text: title,
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
                data:data.hourList,
            },
            yAxis: [
                {
                    type:'value',
                    axisLabel:{
                        textStyle:{
                            color:'#000',
                        },
                    },
                    axisTick:{
                        alignWithLabel:true,
                        lineStyle:{
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
                start: 40,
                end: 100
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
                GetChartInfo();
            }
            if(ev.currentTarget.id=="stack_daterange")
            {
                GetStackInfo();
            }
            if(ev.currentTarget.id=="marker_daterange")
            {
                GetMarkerInfo();
            }
            /*if(ev.currentTarget.id=="marker2_daterange")
            {
                GetMarker2Info();
            }*/
        });

        $('.date').on('cancel.daterangepicker', function (ev, picker) {
            $(this).val('');
            if(ev.currentTarget.id=="marker_daterange")
            {
                var groupid=$("#groupid").val();
                MarkerInfo(0,0,groupid);
            }
            if(ev.currentTarget.id=="chart_daterange")
            {
                var groupid=$("#groupid").val();
                var industrytype=$("#industryType").val();
                ChartInfo(0,0,groupid,industryType);
            }
            if(ev.currentTarget.id=="stack_daterange")
            {
                var groupid=$("#groupid").val();
                var risktype=$("#risktype").val();
                StockInfo(0,0,groupid,risktype);
            }
        });
    }
    $(function(){
        myChart1.on('dblclick', function (params) {
            var groupid = $("#groupid").val();
            var times = params.name.split(":")[0];
            var now = new Date();
            var date = "";
            var year = "";
            var month = "";
            var day = "";
            if(parseInt(times)<=parseInt(endhour) && parseInt(times)<=parseInt(starthour)){
                year=now.getFullYear();
                month=now.getMonth();
                day= now.getDate();
            }else if(parseInt(times)>=parseInt(endhour) && parseInt(times)>=parseInt(starthour)){
                now.setDate(now.getDate() - 1);
                year=now.getFullYear();
                month=now.getMonth();
                day= now.getDate();
            }
            date = year +'/'+ month +'/'+ day + " "+times+':00:00';
            alert(Date.parse(new Date(date)));
            var risktype=3;
            var url='/tbRiskIndexAlarmRemindUser/preList?';
            redirect2safeProdList(url,groupid,Date.parse(new Date(date)),risktype);
        });

        myChart2.on('dblclick', function (params) {
            var groupid = $("#groupid").val();
            var times = params.name;
            var url = '/security/tbCompanyAssessmentScoreDetail/networkedcontrollist?';
            redirect2safeProdList(url,groupid,times,"");
        });

        myChart3.on('dblclick', function (params) {
            var groupid = $("#groupid").val();
            var times = params.name;
            var url='/tbRiskIndexAlarmRemindUser/preList?';
            var risktype = $("#risktype").val();
            redirect2safeProdList(url,groupid,times,risktype);
        });

    myChart4.on('dblclick', function (params) {
        var groupid = $("#groupid").val();
        var times = params.name;
        var industrytype = $("#industryType").val();
        var url='/security/tbCompanyAssessmentScoreDetail/index?';
        redirect2safeProdList(url,groupid,times,industrytype);
    });
});
function redirect2safeProdList(url,groupid,time,type){

    if(groupid!=null&&groupid!=""&&groupid!="undefined"){
        url+="&groupid="+groupid;
    }
    if(type!=null&&type!="undefined") {
        url += "&type=" + type;
    }
    if(time!=null&&time!="undefined") {
        url += "&time=" + time;
    }
    $("#sec_content_iframe").hide();
    $("#sec_content").html('');
    $('#external-frame').attr('src','');
    $("#sec_content").load(url);
    $("#sec_content").show();
}

</script>
</@footer>