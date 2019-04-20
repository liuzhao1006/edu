<header class="main-header">
 <!-- Logo -->
 <a href="/index.html" class="logo">
    <!-- mini logo for sidebar mini 50x50 pixels -->
   <#-- <span class="logo-mini">&lt;#&ndash;<b>${(systemSubName)!'KGA'}</b>&ndash;&gt; <img src="/app/images/minilogo.png" alt=""></span>-->
    <!-- logo for regular state and mobile devices -->
    <span class="logo-lg"><#--<b>${(systemName)!'KangarooAdmin'}</b>--> <img src="/app/css/images/logo.png" alt="" class="logopic">智慧安全管理云平台</span>
</a>
    <input id="globalUserId" type="hidden" value="${(me.id!)?c}" ></input>
    <input id="globalGroupId" type="hidden" value="${(me.unitId!)?c}" ></input>
    <input id="globalUserType" type="hidden" value="${(me.userType)!}" ></input>
 <!-- Header Navbar -->
 <nav class="navbar navbar-static-top" role="navigation">
   <!-- Sidebar toggle button-->
   <a href="#" class="sidebar-toggle wapMenu" data-toggle="offcanvas" role="button"><i class="fa-navicon fa"></i><#--<img src="/app/images/menuL.png" alt="">--></a>
   <!-- Navbar Right Menu -->
   <div class="navbar-custom-menu">
     <ul class="nav navbar-nav">
         <li class="onTime-menu backHome">
             <a href="#" onclick="GetUrlInfo('/tbRiskIndexAlarmRemindUser/testJump');"><i class="fa fa-mail-reply"></i>报警测试</a>
         </li>
         <li class="onTime-menu backHome">
             <a  target="_self" href="/index"><i class="fa fa-mail-reply"></i>返回首页</a>
         </li>
         <li class="onTime-menu">
             <a  target="_blank" href="http://47.92.29.57:8013/usecar/monitor/location.html?&userId=${(me.id!)?c}&groupId=${(me.unitId!)?c}"><i class="fa fa-line-chart"></i>动态监控</a>
         </li>
         <li class="onTime-menu ">
             <a  target="_blank" href="http://47.92.29.57:15801/Login/Login1?user=${(me.userName)!}&pwd=${(me.password)!}&code=1"><i class="fa fa-hdd-o"></i>安全学习</a>
         </li>
       <!-- Notifications Menu -->
       <li class="dropdown notifications-menu">
         <!-- Menu toggle button -->
         <a href="#" onclick="GetUrlInfo('/tbRiskIndexAlarmRemindUser/preList');" class="dropdown-toggle" data-toggle="dropdown">
           <i class="fa fa-bell"></i>
           事件提醒
           <span class="label label-warning"></span>
         </a>
       </li>
       <!-- User Account Menu -->
       <li class="dropdown user user-menu notifications-menu">
         <!-- Menu Toggle Button   /system/me/info-->
         <a href="#"  class="dropdown-toggle" data-toggle="dropdown" title="Admnin" data-placement="bottom">
           <#--<img src="${(me.userImg)!}" class="user-image" alt="User Image">-->
             <i class="fa fa-user"></i>
           <span class="hidden-xs">${(me.userName)!'游客'}</span>
             <i class="fa fa-angle-down"></i>
         </a>
           <ul class="dropdown-menu" id="ul">
               <li> <a href="#" onclick="GetUserInfo();">用户中心</a></li>
               <li> <a href="#" onclick="ChangePwd();">修改密码</a></li>
               <li> <a href="/login/logout">退出系统</a></li>
           </ul>
       </li>
      </ul>
    </div>
  </nav>
</header>
<script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>
    $(function(){
        var params ={};
        params.companyId="";
        params.remindUserId="";
        $.ajax({
            type: 'post',
            async: false,
            dataType: "json",
            contentType:"application/json",
            data:JSON.stringify(params),
            url: '/tbRiskIndexAlarmRemindUser/getAlarmRemindDataCount',
            cache: false,
            success: function (data) {
                $('.label-warning').html(data);
            },
            error: function (a) {
               // alert(a);
            }
        });
    });
     function GetUserInfo(){
            GetDialog('/system/me/info','用户中心','','/system/me/updateUser',null,function(){
                return false;
            });
       }
    function ChangePwd(){
        GetDialog('/system/me/pwd','修改密码','1','/system/me/doChangePwd',null,function(){
            return false;
        });
    }

</script>