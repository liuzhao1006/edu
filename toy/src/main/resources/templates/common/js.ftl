<link rel="stylesheet" href="/plugins/validationEngine/validationEngine.jquery.css">
<!-- 确认提示组件 -->
<div id="confirm-modal" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false" style="display: none;">
  <div class="modal-body">
    <p>您确定要删除该条记录吗?</p>
  </div>
  <div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-default">取消</button>
    <button type="button"  class="btn btn-primary del">删除</button>
  </div>
</div>
<!-- REQUIRED JS SCRIPTS -->
<!-- jQuery 2.1.4 -->
<script src="/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="/plugins/jQuery/jquery.form.js"></script>
<script src="/app/js/webTakePhoto.js"></script>
<!-- Bootstrap 3.3.5 -->
<script src="/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/plugins/bootstrap/js/bootstrap-multiselect.js"></script>
<script type="text/javascript" src="/plugins/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" src="/plugins/datatables/extention/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="/plugins/datatables/extention/jszip.min.js"></script>
<#--<script type="text/javascript" src="/plugins/datatables/extention/pdfmake.js"></script>-->
<#--<script type="text/javascript" src="/plugins/datatables/extention/vfs_fonts.js"></script>-->
<script type="text/javascript" src="/plugins/datatables/extention/buttons.html5.min.js"></script>
<script type="text/javascript" src="/plugins/datatables/extention/buttons.print.min.js"></script>
<script type="text/javascript" src="/plugins/datatables/extention/buttons.flash.min.js"></script>
<script type="text/javascript" src="/plugins/datatables/extention/buttons.colVis.min.js"></script>
<script type="text/javascript" src="/app/js/datatablescommon/constant.js"></script>
<script type="text/javascript" src="/app/js/datatablescommon/datatablecommon.js"></script>
<!--左侧菜单滚动条-->
<script type="text/javascript" src="/app/js/zUI.js"></script>
<!-- iCheck 1.0.1 -->
<script src="/plugins/iCheck/icheck.min.js"></script>
<!-- nice-validator-1.0.8 -->
<#--<script src="/plugins/nice-validator-1.0.8/jquery.validator.js?local=zh-CN"></script>-->
<!--jquery-confirm  -->
<script src="/plugins/jquery-confirm/jquery-confirm.min.js"></script>
<!-- Select2 -->
<script src="/plugins/select2/select2.full.min.js"></script>
<!--时间控件-->
<script src="/plugins/daterangepicker/moment.min.js"></script>
<script src="/plugins/daterangepicker/daterangepicker.js"></script>
<script src="/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script src="/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<!-- jquery.cookie -->
<script src="/plugins/jquery.cookie.js"></script>
<script src="/plugins/BootstrapMultiselectCommon.js"></script>
<#--<script src="/plugins/validationEngine/jquery.validate.unobtrusive.js"></script>-->
<script src="/plugins/validationEngine/jquery.validationEngine.js"></script>
<script src="/plugins/validationEngine/jquery.validationEngine-zh_CN.js"></script>
<#--<script src="/plugins/jquery.validate.min.js"></script>
<script src="/plugins/jquery.validationEngine.js"></script>
<script src="/plugins/jquery.metadata.js"></script>&ndash;&gt;
<script src="/plugins/jquery.validationEngine-zh_CN.js"></script>-->
<#--
<script src="/plugins/jquery.validate.min.js"></script>
<script src="/plugins/jquery.metadata.js"></script>-->
<#--
<script src="/plugins/jquery.validate.min.js"></script>
<script src="/plugins/jquery.metadata.js"></script>

<script src="/plugins/jquery.validationEngine.js"></script>
<script src="/plugins/jquery.validationEngine-zh_CN.js"></script>
<script src="/plugins/roadui.validate.js"></script>-->

<!-- xlsx -->
<script src="/plugins/jquery.table2excel.js"></script>
<!-- AdminLTE App -->
<script src="/app/js/app.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="/app/js/demo.js"></script>
<!-- 自定义系统初始化话JS -->
<script src="/app/js/Common.js"></script>
<script src="/app/js/init.js"></script>
<script src="/app/js/eCharts.js"></script>


<!--文本框提示字-->
<script src="/app/js/jquery.placeholder.js"></script>




<script type="text/javascript">

$(function(){
    $('#example-getting-started').multiselect();
	//select2
	$(".select2").select2();
	//iCheck for checkbox and radio inputs
	$('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
       checkboxClass: 'icheckbox_minimal-blue',
       radioClass: 'iradio_minimal-blue'
    });
    // Custom theme
/*    $.validator.setTheme('bootstrap', {
        validClass: 'has-success',
        invalidClass: 'has-error',
        bindClassTo: '.form-group',
        formClass: 'n-default n-bootstrap',
        msgClass: 'n-right'
    });*/
});

//发起ajax请求时session过期处理
$(document).ajaxError(function(event, jqXHR, options){
    if(jqXHR.status==302){
        window.location.href="/login/logout";
        return;
    }
});
</script>


