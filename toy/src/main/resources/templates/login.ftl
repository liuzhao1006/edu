<link rel="stylesheet" href="/plugins/validationEngine/validationEngine.jquery.css">
<!DOCTYPE html>
<html>
<#include "./common/head.ftl">
<body class="hold-transition login-page">
<div class="login-box">
   <#-- <div class="login-logo">
        <b>${(systemName)!'KangarooAdmin'}</b>
    </div>-->
    <!-- /.login-logo -->
    <form id="form1" action="/login/doLogin" data-validator-option="{theme:'bootstrap', timely:2, theme:'simple_bottom'}" method="post" onsubmit="return dosubmit()">
        <div class="login-box-body">
     <#--       <p class="login-box-msg">请输入用户名和密码登录</p>
        <#if error??>
            <div  class="alert alert-danger alert-dismissible">
                <h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle"></i> ${error!}</h4>
            </div>
        </#if>-->
         <div class="logoDiv">
             <img src="/ui/css/images/minLogo.png" alt="logo">
         </div>
            <div class="form-group has-feedback mg">
                <input type="hidden" name="return_url" value="${return_url!}">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <input type="text" class="form-control validate[required]" id="userName"  name="userName" placeholder="用户名" autocomplete="off">

            </div>
            <div class="form-group has-feedback mg">
                <span class="glyphicon glyphicon-lock form-control-feedback" ></span>
                <input type="password" class="form-control validate[required]" name="password"  id="password" placeholder="密码" autocomplete="off">

            </div>
            <div class="form-group has-feedback">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="form-group has-feedback mg">
                            <input type="text" class="form-control validate[required,CheckCaptcha]" id="captcha" name="captcha" placeholder="验证码"  size="5" autocomplete="off">
                            <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div id="aimg" class="form-group has-feedback">
                            <img alt="如果看不清楚，请单击图片刷新！" class="pointer img" src="/login/captcha" onclick="changeImg();">
                            <a href="#" onclick="changeImg();"><i class="fa fa-undo" title="点击刷新"></i></a>
                        </div>
                    </div>
                <#if loginError??>
                     <script>alert('${loginError!}');</script>
                </#if>
                </div>
            </div>

            <div class="row">
                <button type="submit" id="mySubmit" class="btn btn-primary btn-block btn-flat">登录</button>
            </div>
            <!-- /.social-auth-links -->
        </div>
    </form>
    <!-- /.login-box-body -->
</div>
<!-- REQUIRED JS SCRIPTS -->
<!-- jQuery 2.1.4 -->
<script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>

<script src="/plugins/validationEngine/jquery.validationEngine.js"></script>

<script src="/plugins/validationEngine/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript">
    var isCommitted = false;//表单是否已经提交标识，默认为false
    $(function(){

        $("#form1").validationEngine();
        $("#userName").focus();

    });
    function changeImg() {
        $('.img').attr('src','/login/captcha?'+new Date().getTime());
    }

    function dosubmit() {
        if(!$("#form1").validationEngine("validate")){
            isCommitted=false;
            return false;
        }
        if(isCommitted===false){
            isCommitted = true;//提交表单后，将表单是否已经提交标识设置为true
            return true;//返回true让表单正常提交
        }else{
            return false;//返回false那么表单将不提交
        }
    }
</script>
</body>
</html>

