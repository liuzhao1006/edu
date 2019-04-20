<#include "../../common/common.ftl">
<@header>
</@header>
<@body>
<style>
    #form1 .panel p{
        margin:5px 0;
    }
    #form1 .panel .panelTitle{
        margin:0 0 0 10px;
    }
</style>
<section class="content">
<div class="row box box-primary">
    <div class="col-md-12">
        <!-- general form elements -->
        <form id="form1" class="form-horizontal" data-validator-option="{theme:'bootstrap', timely:2, stopOnError:true}">
            <div class="box-body">
                <table cellpadding="5" class="dataTable" cellspacing="0" border="0" style="width: 100%;">
                  <tr>
                      <td>指标编号</td>
                      <td>指标名称</td>
                      <td>基准时间</td>
                      <td>上次提醒日期</td>
                      <td>责任人</td>
                      <td>状态</td>
                  </tr>
                    <#list information as acc>
                        <tr>
                            <td>${(acc.target_code)!}</td>
                            <td>${(acc.target_name)!}</td>
                            <td>${(acc.reference_time)!}</td>
                            <td>${(acc.last_remind_date)!}</td>
                            <td>${(acc.user_name)!}</td>
                           <#-- <td>${(acc.state)!}==0?'待提醒':${(acc.state)!}==1?"已提醒":${(acc.state)!}==2?"已处理":${(acc.state)!}==3?"已触发同类报警关闭":${(acc.state)!}==4?"已报警":""</td>-->
                            <td>${(acc.status_name)!}</td>
                        </tr>
                    </#list>
              </table>

            </div>
        </form>
    </div><!--/.col (left) -->
</div>
</section><!-- /.content -->
</@body>
<@footer>
</@footer>