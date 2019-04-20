$("#form1").validationEngine();
$("#form2").validationEngine();
$('.dateChoose').datetimepicker({
    minView: "month", //选择日期后，不会再跳转去选择时分秒
    language:  'zh-CN',
    format: 'yyyy-mm-dd',
    todayBtn:  1,
    autoclose: 1,
}).on('changeDate', function (){
    $(this).focus();
});
$(function(){
    var userType=$("#userType").val();
    switch(userType){
        case "1":
            $(".nav-tabs li a[href='#tab_2']").parents("li").hide();
            $(".nav-tabs li a[href='#tab_1']").parents("li").addClass("active");
            $("#tab_1").addClass("active");
            $("#tab_2").removeClass("active");
            break;
        case "2":
            $(".nav-tabs li a[href='#tab_1']").parents("li").hide();
            $(".nav-tabs li a[href='#tab_2']").parents("li").addClass("active");
            $("#tab_2").addClass("active");
            $("#tab_1").removeClass("active");
            break;
        default:
            break;
    }

});

$(function () {

    $("#unitId").change(function () {
        var unitId=$("#unitId").val();
        $("select[arr='departId']").empty();


        $.ajax({
            type: 'post',
            async: false,
            url:"/system/user/keShiJson",
            data: {
                unitId: unitId
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data.keShiList != null) {
                    var datas=data.keShiList;
                    var option = "";
                    for (var i = 0; i < datas.length; i++) {
                        option += "<option value='" + datas[i].id + "'  >" + datas[i].companyName + "</option>";
                    }
                    $("select[arr='departId']").append(option);

                }
            }
        })
    })

});
$(function () {
    $("#companyId").change(function () {
        $("#example-getting-started").attr("multiple","multiple");
        var unitId=$(".companyId").val();
        $("#deptId").empty();

        $.ajax({
            type: 'post',
            async: false,
            url:"/system/user/deptJson",
            data: {
                unitId: unitId
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data.deptList != null) {
                    var datas=data.deptList;
                    var option = "";
                    for (var i = 0; i < datas.length; i++) {
                        option += "<option value='" + datas[i].id + "'  >" + datas[i].companyName + "</option>";
                    }
                    $("#deptId").append(option);
                    var deptId=$(".deptId").val();
                    $("#example-getting-started").empty();
                    $.ajax({
                        type: 'post',
                        async: false,
                        url:"/system/user/postJson",
                        data: {
                            deptId: deptId
                        },
                        cache: false,
                        dataType: "JSON",
                        success: function (data) {
                            if (data.postList != null) {
                                var datas=data.postList;
                                var option = "";
                                for (var i = 0; i < datas.length; i++) {
                                    option += "<option value='" + datas[i].id + "'  >" + datas[i].companyName + "</option>";
                                }
                                $("#example-getting-started").append(option);

                            }
                            multiselect.initselect('example-getting-started',false,false,null,null);
                        }
                    });
                }
            }
        });
    })

});
$(function () {

    $(".deptId").change(function () {
        var deptId=$(".deptId").val();
        $("select[arr='postId']").empty();
        $.ajax({
            type: 'post',
            async: false,
            url:"/system/user/postJson",
            data: {
                deptId: deptId
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data.postList != null) {
                    var datas=data.postList;

                    var option = "";
                    for (var i = 0; i < datas.length; i++) {
                        option += "<option value='" + datas[i].id + "'  >" + datas[i].companyName + "</option>";
                    }
                    $("#example-getting-started").append(option);

                }
            }
        });
    })
});

//监管人员用户名验重
$(function () {
    $("#form1").validationEngine();

    $("#names").on('change', function () {

        var userName = $("#names").val();

        $.ajax({
            type: 'post',
            async: false,
            url: "/system/user/checkName",
            data: {
                userName: userName
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data == 1) {
                    Commonalert("该用户名已经存在，请更换其他名称！");
                    $("#names").val("");
                    return false;
                } else {

                }
            }
        })
    })
})
//企业人员用户名验重
$(function () {
    $("#form2").validationEngine();

    $("#name").on('change', function () {

        var userName = $("#name").val();

        $.ajax({
            type: 'post',
            async: false,
            url: "/system/user/checkName",
            data: {
                userName: userName
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data == 1) {
                    Commonalert("该用户名已经存在，请更换其他名称！");
                    $("#name").val("");
                    return false;
                } else {

                }
            }
        })
    })
});
//验证监管人员两次输入密码是否一致

$(function(){

    $("#password2").on('change',function () {
        if($("#password").val()!=$("#password2").val()){
            Commonalert("两次输入的密码不一致，请再次确认密码");
            $("#password2").val("");
            return false;
        }
    })
})
//验证企业人员两次输入密码是否一致

$(function(){

    $("#userPassword2").on('change',function () {
        if($("#userPassword").val()!=$("#userPassword2").val()){
            Commonalert("两次输入的密码不一致，请再次确认密码");
            $("#userPassword2").val("");
            return false;
        }
    })
});
var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;

$(function(){
    $("#password").on('change',function () {
        var pwd=$(".password").val();
        var result=reg.test(pwd);
        if(result==false){
            Commonalert("密码必须由 6-16位字母、数字组成");
            $("#password").val("");
            return false;
        }
    })
})

$(function(){
    $("#userPassword").on('change',function () {
        var userPwd=$("#userPassword").val();
        var results=reg.test(userPwd);
        if(results==false){
            Commonalert("密码必须由 6-16位字母、数字组成");
            $("#userPassword").val("");
            return false;
        }
    })
});
//身份证号验重
$(function () {
    $("#form1").validationEngine();
    $("#organizeIdCard").on('change', function () {
        var idCard = $("#organizeIdCard").val();
        $.ajax({
            type: 'post',
            async: false,
            url: "/system/user/checkIdCard",
            data: {
                idCard: idCard
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data == 1) {
                    Commonalert("该身份证号已经存在！");
                    $("#organizeIdCard").val("");
                    return false;
                } else {

                }
            }
        })
    })
});
//身份证号验重
$(function () {
    $("#form2").validationEngine();
    $("#idCard").on('change', function () {
        var idCard = $("#idCard").val();
        $.ajax({
            type: 'post',
            async: false,
            url: "/system/user/checkIdCard",
            data: {
                idCard: idCard
            },
            cache: false,
            dataType: "JSON",
            success: function (data) {
                if (data == 1) {
                    Commonalert("该身份证号已经存在！");
                    $("#idCard").val("");
                    return false;
                } else {

                }
            }
        })
    })
})