/**
 * Created by xiong on 2017/8/17.
 */
var user_table = null;
$(function(){
    var $userTable = $('#userTable');
    user_table = $userTable.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {

        ajax : function(data, callback, settings) {
            var params ={};
            params.sysUserName=$.trim($('#sysUserName').val());
            params.idCard=$.trim($('#idCard').val());
            params.groupId=$.trim($('#groupId').val());
            params.isOpen=$('#isOpen').val();
            params.menuId=$('#menuId').val();
            datatablecommon.datatablesajax(data, callback, settings,"/system/user/getpagedata",params);

        },
        columnDefs: [
            { "orderable": true, "targets": [3,4]},
            { "orderable": false, "targets": [0,1,2,5,6,7,8]}
        ],
        columns: [
            // CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {data: "userName", title: "用户名"},
            {data: "sex" , title: "性别",render:function(data, type, row, meta){
                return data == 0? "男":"<span style='color: red; '>女</span>";
            }},
            {data: "sysUserName", title: "姓名",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "idCard" , title: "身份证号",render:CONSTANT.DATA_TABLES.RENDER.IDANDPHONE},
            {data: "telephone" , title: "联系方式",render:CONSTANT.DATA_TABLES.RENDER.IDANDPHONE},
            {data: "address" , title: "地址",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "postName" , title: "岗位",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "isOpen" , title: "人员状态",render:function (data, type, row, meta) {
                return data == false? "启用":"<span style='color: red; '>禁用</span>" }},
            {data: '' , title: "操作",render:function(data, type, row, meta){
                var  s=getOperatesColumn();
                if($("#editPermission").val() == 1) {
                    s+="<i class='fa fa-repeat reset'  data-toggle='tooltip' data-placement='bottom' title='重置密码'></i>";
                    if(row.isOpen==0) {
                        s += "<i class='fa fa-unlock  isOpen isDisable '  data-toggle='tooltip' data-placement='bottom' title='禁用'></i>";
                    }else{
                        s += "<i class='fa  fa-unlock-alt recoverOpen isDisable '  data-toggle='tooltip' data-placement='bottom' title='启用'></i>";
                    }
                    if(row.isLeave==0){
                        s += "<i class='fa fa-calendar-minus-o isLeave isDisable '  data-toggle='tooltip' data-placement='bottom' title='请假'></i>"
                    }else{
                        s += "<i class='fa fa-calendar-plus-o recoverLeave isDisable '  data-toggle='tooltip' data-placement='bottom' title='销假'></i>"
                    }
                    if(row.isReappoint==0){
                        s+="<i class='fa fa-user-times isReappoint isDisable'  data-toggle='tooltip' data-placement='bottom' title='离职'></i>";
                    }else{
                        s += "<i class='fa fa-user-plus recoverReappoint isDisable'  data-toggle='tooltip' data-placement='bottom' title='重聘'></i>";
                    }
                    if(row.isCancel==0){
                        s+="<i class='fa fa-sign-out isCancel isClick'  data-toggle='tooltip' data-placement='bottom' title='注销'></i>";
                    }else{
                        s+="<i class='fa fa-sign-in recoverCancel'  data-toggle='tooltip' data-placement='bottom' title='取消注销'></i>";
                    }

                }
                return s;

            }},

        ],
        drawCallback: function(settings) {
            $("table.dataTable thead th").removeClass("sorting").hover(function(){                 $(this).addClass("sorting");             },function(){                 $(this).removeClass("sorting");             });
            $(":checkbox[name='cb-check-all']").prop('checked', false);
            getLoadingH();
            //注销时，人员状态按钮禁用
            if($(".fa").hasClass("recoverCancel")){
                $(".recoverCancel").parents("td").find(".isDisable").attr("disabled",true).css("cursor","not-allowed").click(function () {
                    return false;
                });
            }
            //禁用时，不能请假
            if($(".fa").hasClass("recoverOpen")){
                $(".recoverOpen").parents("td").find(".isLeave").attr("disabled",true).css("cursor","not-allowed").click(function () {
                    return false;
                });
            }

            if($(".fa").hasClass("recoverReappoint")){
                $(".recoverReappoint").parents("td").find(".isClick").attr("disabled",true).css("cursor","not-allowed").click(function () {
                    return false;
                });
            }


        }
    })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
    $userTable.on("change",":checkbox",function() {
        if ($(this).is("[name='cb-check-all']")) {
            //全选
            $(":checkbox",$userTable).prop("checked",$(this).prop("checked"));
        }else{
            //一般复选
            var checkbox = $("tbody :checkbox",$userTable);
            $(":checkbox[name='cb-check-all']",$userTable).prop('checked', checkbox.length == checkbox.filter(':checked').length);
        }
    }).on("click",".td-checkbox",function(event) {
        //点击单元格即点击复选框
        !$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    }).on("click",".delete",function() {
        //点击删除按钮
        var item = user_table.row($(this).closest('tr')).data();
        DeleteOne('/system/user/delete',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".reset",function() {
        //点击重置密码按钮
        var item = user_table.row($(this).closest('tr')).data();
        resetPwd('/system/user/resetPwd',item.id,function(){
            user_table.ajax.reload();
        });

    }).on("click",".edit",function() {
        var itemId=$('.highlight').index('tr');
        var item = user_table.row($(this).closest('tr')).data();
        var type=item.userType;  //编辑人员的类型
        var result = selectUserAuth();//登录人员的类型
        if ((result == 1&&type==1)||(result==3&&type==1)) {
            var edit=GetConformDialog('/system/user/edit/' + item.id, '编辑', '', function () {
                SaveDialog('/system/user/doEditCompany', 'form1', function () {

                    $.ajaxSetup( {
                        async : false//设置get、post同步
                    });
                    user_table.ajax.reload();
                    $('.dataTable tr').eq(itemId).addClass("highlight");
                    edit.close();
                });

                return false;
            });
        }else if((result==3&&type==2)||(result==1&&type==2)||(result==2&&type==2)) {
          var edit=GetConformDialog('/system/user/edit/' + item.id, '编辑', '', function () {
                SaveDialog('/system/user/doEditUser', 'form2', function () {
                    $.ajaxSetup( {
                        async : false//设置get、post同步
                    });
                    user_table.ajax.reload();
                    $('.dataTable tr').eq(itemId).addClass("highlight");
                    edit.close();
                });

                return false;
            },'','','form2');

        }else if((result==1&&type==3)||(result==2&&type==1)||(result==2&&type==3)){
            Commonalert("很抱歉，您没有该权限！！！");
        }else {
           var edit=GetConformDialog('/system/user/edit/' + item.id, '编辑', '', function () {
                SaveDialog('/system/user/doEditCompany', 'form1', function () {
                    $.ajaxSetup( {
                        async : false//设置get、post同步
                    });
                    user_table.ajax.reload();
                    $('.dataTable tr').eq(itemId).addClass("highlight");
                    edit.close();
                });

                return false;
            });
        }
    }).on("click",".isOpen",function() {
        var item = user_table.row($(this).closest('tr')).data();
        use('/system/user/open',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".recoverOpen",function() {
        var item = user_table.row($(this).closest('tr')).data();
        recover('/system/user/recoverOpen',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".isLeave",function() {
        var item = user_table.row($(this).closest('tr')).data();
        leave('/system/user/leave',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".recoverLeave",function() {
        var item = user_table.row($(this).closest('tr')).data();
        recover('/system/user/recoverLeave',item.id,function() {
            user_table.ajax.reload();
        });
    }).on("click",".isCancel",function() {
        var item = user_table.row($(this).closest('tr')).data();
        var that=this;
        cancel('/system/user/Cancel',item.id,function(){
            user_table.ajax.reload();
        });

    }).on("click",".recoverCancel",function() {
        var item = user_table.row($(this).closest('tr')).data();
        recover('/system/user/recoverCancel',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".isReappoint",function() {
        var item = user_table.row($(this).closest('tr')).data();
        reappoint('/system/user/Reappoint',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".recoverReappoint",function(){
        //恢复正常
        var item = user_table.row($(this).closest('tr')).data();
        recover('/system/user/recoverReappoint',item.id,function(){
            user_table.ajax.reload();
        });
    }).on("click",".detail",function(){
        var item = user_table.row($(this).closest('tr')).data();
        GetNormalDialog('/system/user/details/'+item.id,'详情','');
    });
    $('#btn_search').click(function(){
        user_table.draw();
    });
    $('#btnadd').click(function(){
      var add= GetConformDialog('/system/user/add/','创建','',function () {

            var hideUserType = $('#userType').val();

            if(hideUserType==1){
             var isClose= SaveDialog('/system/user/doAddCompany','form1',function(){
                    user_table.ajax.reload();
                });
             if(isClose==true){
                 add.close();
             }else{
                 return false;
             }
                return false;
            }else if(hideUserType==2){
              var isClose=SaveDialog('/system/user/doAddUser', 'form2',function(){
                    user_table.ajax.reload();
                });
                if(isClose==true){
                    add.close();
                }else{
                    return false;
                }
            }else if(hideUserType==3){
                if( $(".nav-tabs li a[href='#tab_1']").parents("li").hasClass('active')){
                  var isClose=SaveDialog('/system/user/doAddCompany','form1',function(){
                        user_table.ajax.reload();
                    });
                    if(isClose==true){
                        add.close();
                    }else{
                        return false;
                    }
                }else{
                 var isClose=SaveDialog('/system/user/doAddUser','form2',function(){
                        user_table.ajax.reload();
                    });
                    if(isClose==true){
                        add.close();
                    }else{
                        return false;
                    }
                }
            }
        });
    });
    //高亮显示
    $('table.dataTable tbody').on( 'click', 'tr', function () {
        $('.dataTable tr.highlight').removeClass('highlight');
        $(this).addClass('highlight');
    } );
});
function use(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定该账号禁用吗?' ? '您确定该账号禁用吗?' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                        if($.isFunction(callback))
                        {
                            callback();
                        }
                    });
                }
            },
            '取消':{}
        }
    });
}
//请假
function leave(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定该人员请假吗?' ? '您确定该人员请假吗?' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                        if($.isFunction(callback))
                        {
                            callback();
                        }
                    });
                }
            },
            '取消':{}
        }
    });
}
//离职
function reappoint(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定该人员离职吗?' ? '您确定该人员离职吗?' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                        if($.isFunction(callback))
                        {
                            callback();
                        }
                    });
                }
            },
            '取消':{}
        }
    });
}

//注销
function cancel(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定该人员注销吗?' ? '您确定该人员注销吗?' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                        if($.isFunction(callback))
                        {
                            callback();
                        }
                    });
                }
            },
            '取消':{}
        }
    });
}
function recover(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定该人员恢复正常吗?' ? '您确定该人员恢复正常吗?' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                        if($.isFunction(callback))
                        {
                            callback();
                        }
                    });
                }
            },
            '取消':{}
        }
    });
}

function resetPwd(url, id,callback) {
    $.confirm({
        closeIcon: true,
        title: '警告',
        content: '您确定要重置密码吗?' ? '您确定要重置密码吗??' : '确认操作?',
        buttons: {
            '确认': {
                btnClass: 'btn-blue',
                action: function(){
                    $.post(url,{id:id},function(json){
                        if($.isFunction(callback))
                        {
                            callback();
                        }
                    });
                }
            },
            '取消':{}
        }
    });
}

function selectUserAuth(){
    var type;
    $.ajax({
        type: 'post',
        async: false,
        url:"/system/user/userJson",
        data: {
        },
        cache: false,
        dataType: "JSON",
        success: function (data) {
            type=data.user.userType;
        }
    })
    return type;
}