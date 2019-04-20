/**
 * Created by xiong on 2017/8/16.
 */
/// <summary>保存数据方法</summary>
/// <param name="url" type="String">提交action的url地址</param>
/// <param name="redirectUrl" type="String">跳转的页面地址</param>
/// <param name="redirectType" type="String">页面浏览的方式</param>
/// <param name="formId" type="String">页面form的ID</param>
/// <returns type="">返回列表页面</returns>

function SaveDialog(url, formId, callback) {
        $('.btn-blue').attr('disabled',"true");
        var strUrl = url.split(',');
        if (strUrl.length > 1) {
            var strForm = formId.split(',');
            for (var i = 0; i < strUrl.length; i++) {
                $.ajax({
                    type: 'post',
                    async: false,
                    url: strUrl[i],
                    data: $(strForm[i]).serialize(),
                    cache: false,
                    success: function (data) {

                    },
                    error: function () {

                    }
                });
            }
            Commonalert("保存成功！");
            if ($.isFunction(callback)) {
                callback();
            }
            $(".formError").remove();
            $('.btn-blue').removeAttr("disabled");
        }
        else {
            var form = "#form1";
            if (formId != null) {
                form = "#" + formId;
            }
            var arrayInfo=new Array();
            if (typeof oTable02_attachment!="undefined") {
                for(var i=0;i<oTable02_attachment.data().length;i++)
                {
                    var objectInfo=new Object();
                    objectInfo.id=oTable02_attachment.data()[i].id;
                    objectInfo.title=oTable02_attachment.data()[i].title;
                    objectInfo.url=oTable02_attachment.data()[i].url;
                    arrayInfo.push(objectInfo);
                }
            }

            var boolresult=$(form).validationEngine("validate");
            if(boolresult) {
                $.ajax({
                    type: 'post',
                    async: false,
                    url: url,
                    data: $.param({'file': JSON.stringify(arrayInfo)}) + '&' + $(form).serialize(),
                    cache: false,
                    success: function (data) {
                        Commonalert("保存成功！");
                        if ($.isFunction(callback)) {
                            callback();
                        }
                        $(".formError").remove();
                    },
                    error: function (a) {
                        $(".formError").remove();
                    }
                });
                $('.btn-blue').removeAttr("disabled");
                return true;
            }
            else
            {
                $('.btn-blue').removeAttr("disabled");
                return false;

            }
        }

}

function DeleteDialog(url, id,callback) {
    $.ajax({
        type: 'post',
        async: false,
        url: url,
        data: {id:id},
        cache: false,
        success: function (data) {
            if($.isFunction(callback))
            {
                callback();
            }
            return false;
        },
        error:function (a,b,c) {
           // alert(a);
        }
    });
}

function  GetUrlInfo(url) {
    if(url.indexOf('http')>=0){
        //TODO 获取head里面的 groupId,拼接到url后面
        var userType=$("#globalUserType").val();
        var userId=  $("#globalUserId").val();
        var groupId=  $("#globalGroupId").val();
        if(userType==3){
            groupId="";
        }
        if(userId!=null&&userId!=""&&userId!="undefined"){
            url+="&userId="+userId;
        }
        if(groupId!=null&&groupId!="undefined") {
            url += "&groupId=" + groupId;
        }
        if(url.indexOf("getAbnomalVehicle.html")>-1 || url.indexOf("getAlarmSynAnalysis.html")>-1){
            $("#external-frame").height($(window).height()+200)
        }

        $("#sec_content_iframe").show();
        $("#sec_content").html('').hide();
        $('#external-frame').attr('src',url);
        $("#sec_content_iframe").show();
    }else{
        $("#sec_content_iframe").hide();
        $("#sec_content").html('').show();
        $('#external-frame').attr('src','');
        $("#sec_content").load(url);
        $("#sec_content").show();
    }

    $("#external-frame").height($(".content-wrapper").height()-5);
    return false;
}


//公共alert方法
function Commonalert(content) {
    $.alert({
        title: '您好!',
        content: '<i class="fa fa-check-circle saveOK"></i>'+content,
    });
}
//ajaxmodel,异步弹出框
//dataUrl 页面action  dataTitle 页面标题 dataMax 页面大小
function GetDialog(dataUrl,dataTitle,dataMax,url, formId, callback,heightradio) {
switch(dataMax){
    case "1":
        dataMax="col-md-6 col-md-offset-3";
        break;
    case "2":
        dataMax="col-md-8 col-md-offset-2";
        break;
    default:
        dataMax="col-md-10 col-md-offset-1";
        break;
     }
   var cons=$.confirm({
        animation: 'zoom',
        closeAnimation: 'scale',
        animationBounce: 1.5,
        title: dataTitle ? dataTitle : "标题",
        columnClass: dataMax ,
        content: 'url:'+dataUrl,
        closeIcon: true,
        cancelButton: false, // hides the cancel button.
        confirmButton: false, // hides the confirm button.
        backgroundDismiss:true,
       onContentReady: function() {
               $(".jconfirm-content .content").height($(window).height()*(heightradio||0.6));
           $(".jconfirm-closeIcon").click(function(){
               $(".formError").remove();
           })
           },
           buttons:{'关闭':{
              action:function(){
                  $(".formError").remove();
              }
           },
                 '保存': {
                btnClass: 'btn-blue',
                action: function(){
                    var form = "#form1";
                    var strForm="";
                    if (formId != null)
                    {
                        strForm = formId.split(',');
                        form = "#" + formId;
                    }

                        var result=SaveDialog(url, formId, function () {
                            if ($.isFunction(callback)) {
                                callback();
                                cons.close();
                                $(".formError").remove();

                            }
                            return false;
                        });
                        return result;
                }
            }
        }
    });
    return cons;
}


function GetConformDialog(dataUrl,dataTitle,dataMax,callback,heightradio,formId) {

    switch(dataMax){
        case "1":
            dataMax="col-md-6 col-md-offset-3";
            break;
        case "2":
            dataMax="col-md-8 col-md-offset-2";
            break;
        default:
            dataMax="col-md-10 col-md-offset-1";
            break;
    }
    var cons=$.confirm({
        animation: 'zoom',
        closeAnimation: 'scale',
        animationBounce: 1.5,
        title: dataTitle ? dataTitle : "标题",
        columnClass: dataMax,
        content: 'url:'+dataUrl,
        closeIcon: true,
        cancelButton: false, // hides the cancel button.
        confirmButton: false, // hides the confirm button.
        backgroundDismiss:true,
        onContentReady: function() {
            $(".jconfirm-content .content").height($(window).height()*(heightradio||0.6));
            $(".jconfirm-closeIcon").click(function(){
                $(".formError").remove();
            })
        },
        buttons:{'关闭':{
            action:function(){
                $(".formError").remove();
            }
        },
            '保存': {
                btnClass: 'btn-blue',
                action: function(){
                        if ($.isFunction(callback)) {
                            callback();
                            //cons.close();
                            $(".formError").remove();
                        }

                    return false;
                }
            }
        }
    });
    return cons;
}



function GetNormalDialog(dataUrl,dataTitle,dataMax,heightradio) {

    var cons=$.confirm({
        animation: 'zoom',
        closeAnimation: 'scale',
        animationBounce: 1.5,
        title: dataTitle ? dataTitle : "标题",
        columnClass: dataMax ? 'col-md-10 col-md-offset-1' : 'col-md-8 col-md-offset-3',
        content: 'url:'+dataUrl,
        closeIcon: true,
        cancelButton: false, // hides the cancel button.
        confirmButton: false, // hides the confirm button.
        backgroundDismiss:true,
        onContentReady: function() {
            $(".jconfirm-content .content").height($(window).height()*(heightradio||0.6));
            $(".jconfirm-closeIcon").click(function(){
                $(".formError").remove();
            })
        },
        buttons:{'关闭':{
            btnClass: 'close',
            action:function(){
                $(".formError").remove();
            }
        }}
    });
    return cons;
}

//彈出框選擇專用不帶保存【切記】
function GetNormalNotSaveDialog(dataUrl,dataTitle,dataMax,callback,colosebtntext,heightradio) {
    switch(dataMax){
        case "1":
            dataMax="col-md-6 col-md-offset-3";
            break;
        case "2":
            dataMax="col-md-8 col-md-offset-2";
            break;
        default:
            dataMax="col-md-10 col-md-offset-1";
            break;
    }

    var cons =$.confirm({
        animation: 'zoom',
        closeAnimation: 'scale',
        animationBounce: 1.5,
        title: dataTitle ? dataTitle : "标题",
        columnClass: dataMax,
        content: 'url:'+dataUrl,
        closeIcon: true,
        cancelButton: false, // hides the cancel button.
        confirmButton: false, // hides the confirm button.
        backgroundDismiss:true,
        onContentReady: function() {
            $(".jconfirm-content .content").height($(window).height()*(heightradio||0.6));
            $(".jconfirm-closeIcon").click(function(){
                $(".formError").remove();
            })
        },
        buttons:{
            cc:{
                text:colosebtntext || '关闭',
                action:function(){
                    if ($.isFunction(callback)) {
                        callback();

                    }
                    cons.close();
                    return false;
                }
            }
        }
    });
    return cons;
}



function DeleteOne(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定要删除该条记录吗?' ? '<i class="fa fa-question-circle questionIcon"></i>您确定要删除该条记录吗?' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                            if ($.isFunction(callback)) {
                                callback(json);
                            }
                    });
                }
            },
            '取消':{}
        }
    });
}


//批量删除确认框
function DeleteMore(deleteBatchUrl,backUrl) {
    var ids = [];
    $.each($("input:checked"),function(n,i){
        if($(this).val()!='root'){
            ids.push($(this).val());
        }
    });

    if(ids.length==0){
        $.alert({
            title: '提示',
            backgroundDismiss:true,
            content: "<i class='fa fa-question-circle ofexclamation'></i>请选择要删除的记录!",
            buttons:{"好的":{ btnClass: 'btn-blue'}}
        });
    }else{
        $.confirm({
            type: 'red',
            closeIcon: true,
            title: '警告',
            content: "<i class='fa fa-question-circle ofexclamation'></i>确认删除选中的【"+ids.length+"】条记录?",
            buttons: {
                '确认': {
                    btnClass: 'btn-blue',
                    action: function(){
                        $.post(deleteBatchUrl,{id:ids},function(json){
                            if(json.meta.success){
                                /*window.location.reload();*/
                                GetUrlInfo(backUrl);
                            }else{
                                $.alert({
                                    title: '提示',
                                    content: json.meta.message,
                                    buttons:{"好的":{ btnClass: 'btn-blue'}}
                                });
                            }
                        });
                        GetUrlInfo(backUrl);
                    }
                },
                '取消':{}
            }
        });
    }
}



function GetDropListInfo(id, controlId) {
    $("[arr='" + controlId + "']").empty();
            $.ajax({
                type: 'post',
                url: '/system/menu/json?pid=' + id,
                cache: false,
                async: false,
                success: function (data) {
                    if (data.datainfo != null) {
                        var datas=data.datainfo;
                        var option = "";
                        for (var i = 0; i < datas.length; i++) {

                            option += "<option value='" + datas[i].id + "'  >" + datas[i].text + "</option>";

                        }
                        $("[arr='" + controlId + "']").append(option);
                    }
        }
    });
}
/*
 appendid：图片上传DOM追加的div的id 确保同一页面id不能重复 必填
 formid:图片上传form的id 可任意定义 确保同一页面id不能重复 必填
 inputid：图片选择按钮id 确保同一页面id不能重复 必填
 imageshowid：图片展示id 确保同一页面id不能重复 必填
 picwidth：图片展示宽度 默认200px 非必填
 picheight：图片展示高度 默认200px 非必填
 imageurl：编辑时图片显示路径 新增不填，编辑必填
 */
function initsingleimageupload(appendid,formid,inputid,imageshowid,picwidth,picheight,imageurl,type){
    picwidth = picwidth||200;
    picheight = picheight||130;
    //创建html
    var html='<div style="top:10px;">';
    if(type==2){
        html+="<input type='hidden'  name='photo' id='photo'/>";
        html+="<img src=''  name='photo' id='"+imageshowid+"'>";
        html+="<button type='button' class='btn btn-default takePhoto' title='拍照'><i class='fa fa-camera-retro'></i></button>";
        html+="<form id='"+formid+"' style='margin-top: 5px;display: inline-block' enctype='multipart/form-data'>";
        html+="<div  class='file btn btn-default'  title='上传'><i class='fa fa-upload' ></i>";
        html+="<div style='width:100%;overflow: hidden;height:24px;position: absolute;top:0;left: 0'> <input type='file' accept='image/*' name='upfile' id='"+inputid+"'></div>";
        html+="</div>";
        html+="</form>";
    }else if(type==3){
        html+="<img src=''  name='photo' id='"+imageshowid+"' style='display: block;'>";
        html+="<button type='button' class='btn btn-default takePhoto' title='拍照' style='width: 58px;margin-left: 15px;margin-top: 2px;'><i class='fa fa-camera-retro'></i></button>";
        html+="<form id='"+formid+"' style='margin-top: 5px;display: inline-block' enctype='multipart/form-data'>";
        html+="<div  class='file btn btn-default'  title='上传'><i class='fa fa-upload' ></i>";
        html+="<div style='width:100%;overflow: hidden;height:24px;position: absolute;top:0;left: 0'> <input type='file' accept='image/*' name='upfile' id='"+inputid+"'></div>";
        html+="</div>";
        html+="</form>";
    }else{
        html+=" <img id='"+imageshowid+"' name='"+imageshowid+"' style='margin-bottom:8px;width: "+picwidth+"px;height: "+picheight+"px' />";
        html+="<form id='"+formid+"' style='margin-top: 5px' enctype='multipart/form-data'>";
        html+="<a href='javascript:;'  class='upload'>选择文件";
        html+="<input id='"+inputid+"' class='change' type='file' accept='image/*' name='upfile' style='width:50px'>";
        html+="</a>";
        html+="</form>";
    }
    html+="</div>";
    $('#'+appendid).append(html);
    if(imageurl!=null && imageurl!=undefined&& imageurl!=''){
        if($('#'+imageshowid).length >0){
            $('#'+imageshowid).attr('src',imageurl)
        }
    }
    $('#'+inputid).change(function(){

        $('#'+formid).ajaxSubmit({
            type: 'post',
            async: false,
            url: '/UEditor?action=uploadimage',
            contentType:'multipart/form-data',
            dataType: "json",
            data: $('#'+formid).serialize(),
            success: function(data) {
                if(data.state==="SUCCESS"){
                    if($('#'+imageshowid).length >0){
                        $('#'+imageshowid).attr('src',data.url).show();
                    }
                }else{
                    $.alert({
                        content: '上传图片失败',
                    });
                }
                $('#'+formid).resetForm(); // 提交后重置表单
            }
        });
    })
}

$(function(){
    /*左侧二级导航点击 字变白*/
    $(".treeview-menu li").click(function () {
        $(this).addClass("active").siblings("li").removeClass("active");
    })
    if (window.ActiveXObject || "ActiveXObject" in window) {
        $(".main-sidebar").height($(window).height()-$(".main-header").height()).css("overflow","auto!important");
    }else{
        $(".main-sidebar").height($(window).height()-$(".main-header").height()).panel({ iWheelStep: 44 });
    }
    $(".content-wrapper").height($(window).height()-$(".main-header").height()-$(".main-footer").height());
    $('input, textarea').placeholder();
    $(document).bind('click',function(){
        $(".user-menu").removeClass("open");
    });
    $(".user-menu").click(function(event){
        $(this).toggleClass("open");
         event.stopPropagation();
    });
$("#external-frame").height($(window).height()-90);
})
//获取操作列
function getOperatesColumn(){
    var options="";
    options="<i class='fa fa-info-circle detail' data-toggle='tooltip' data-placement='bottom' title='详情'></i>";
    if($("#editPermission").val() == 1){
        options += "<i class='fa fa-pencil edit' data-toggle='tooltip' data-placement='bottom' title='编辑'></i>";
    }
    if($("#deletePermission").val() == 1){
        options += "<i class='fa fa-trash delete'  data-toggle='tooltip' data-placement='bottom' title='删除'></i>";
    }
    return options;
}
//加载loading高度
function getLoadingH() {
    var tabH=$(".dataTables_processing").next(".dataTable").height()+47;
    $(".dataTables_processing").height(tabH).css({"top":"30px","line-height":(tabH+20)+"px"});
}
