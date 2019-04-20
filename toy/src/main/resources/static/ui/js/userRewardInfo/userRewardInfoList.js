/**
 * Created by xiong on 2017/8/17.
 */
var user_reward_table = null;
$(function(){
    var $table = $('#tableUserRewardInfo');
    user_reward_table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax : function(data, callback, settings) {
            var params ={};
            var daterange1 = $('#daterange1').val();
            var daterange2 = $('#daterange2').val();
            var startDate ='';
            var endDate = '';
            if(daterange1!=null && daterange1!=''){
                startDate = daterange1;
            }
            if(daterange2!=null && daterange2!=''){
                endDate = daterange2;
            }
            params.startDate=$.trim(startDate);
            params.endDate=$.trim(endDate);
            params.companyName=$.trim($('#search').val());
            params.groupId=$("#groupId").val();
            params.menuId=$("#menuId").val();
            datatablecommon.datatablesajax(data, callback, settings,"/system/userRewardInfo/getpagedata",params);
        },
        columnDefs: [
            { "orderable": false, "targets": [0,3,4,5,7,8,9,10]},
            { "orderable": true, "targets": [1,2,6]},
            {"bVisible":false,"targets": [7,8,9]}
        ],
        columns: [
            //CONSTANT.DATA_TABLES.COLUMN.CreatedRow,
            {data: "username", title: "姓名",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "idcard", title: "身份证号",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "vehicleno", title: "车牌号",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "rewardType", title: "奖惩类型",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "rewardCompany" , title: "奖惩单位",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "companyName" , title: "所属机构",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "rewardDate" , title: "奖惩时间",render:CONSTANT.DATA_TABLES.RENDER.SHORTDATE},
            {data: "contents" , title: "内容",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "result" , title: "结果",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: "remark" , title: "备注",render:CONSTANT.DATA_TABLES.RENDER.ELLIPSIS},
            {data: '' , title: "操作",render:function(data, type, row, meta){
                return getOperatesColumn();
              }}
        ],
         drawCallback: function(settings) {         $("table.dataTable thead th").removeClass("sorting").hover(function(){                 $(this).addClass("sorting");             },function(){                 $(this).removeClass("sorting");             });
             $(":checkbox[name='cb-check-all']").prop('checked', false);
             getLoadingH();
        }
    })).api();//此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象

    $table.on("change",":checkbox",function() {
        if ($(this).is("[name='cb-check-all']")) {
            //全选
            $(":checkbox",$table).prop("checked",$(this).prop("checked"));
        }else{
            //一般复选
            var checkbox = $("tbody :checkbox",$table);
            $(":checkbox[name='cb-check-all']",$table).prop('checked', checkbox.length == checkbox.filter(':checked').length);
        }
    }).on("click",".td-checkbox",function(event) {
        //点击单元格即点击复选框
        !$(event.target).is(":checkbox") && $(":checkbox",this).trigger("click");
    }).on("click",".delete",function() {
        //点击删除按钮
        var item = user_reward_table.row($(this).closest('tr')).data();
        DeleteOne('/system/userRewardInfo/delete',item.id,function(){
            user_reward_table.ajax.reload();
        });

    }).on("click",".edit",function() {
        //点击编辑按钮
        var itemId=$('.highlight').index('tr');
        var item = user_reward_table.row($(this).closest('tr')).data();
        GetDialog('system/userRewardInfo/edit/'+item.id,'编辑','','/system/userRewardInfo/doEdit', null,function() {

            $.ajaxSetup( {
                async : false//设置get、post同步
            });
            user_reward_table.ajax.reload();
            $('.dataTable tr').eq(itemId).addClass("highlight");
            return false;
        });
        return false;
        /*var item = user_reward_table.row($(this).closest('tr')).data();
        var dialog = GetConformDialog('system/userRewardInfo/edit/'+item.id,'编辑人员奖惩记录','',function(){
            if($("#userRewardInfoDataTable tr").eq(1).find("select[name='userName']").val()==undefined){
                Commonalert("人员不能为空");
                return false;
            }
            SaveDialog('/system/userRewardInfo/doEdit',null,function(){
                $('#rewardDate').hide();
                dialog.close();
                user_reward_table.ajax.reload();
            });
            return false;
        });*/
    }).on("click",".detail",function() {
        var item = user_reward_table.row($(this).closest('tr')).data();
        GetNormalDialog('/system/userRewardInfo/detail/'+item.id,'详情','');
    });

    $('#btn_search').click(function(){
        //user_reward_table.ajax.reload();
        // user_reward_table.draw(false);
        user_reward_table.draw();
    })


    $('#btnAdd').click(function(){
        var dialog = GetConformDialog('/system/userRewardInfo/add','添加人员奖惩记录','',function(){
            if($("#userRewardInfoDataTable tr").eq(1).find("select[name='userName']").val()==undefined){
                Commonalert("人员不能为空");
                return false;
            }
            SaveDialog('/system/userRewardInfo/doAdd',null,function(){
                dialog.close();
                $('#rewardDate').hide();
                user_reward_table.ajax.reload();
            });
            return false;
        });
    })




    $('#tableUserRewardInfo').on('click', 'td.details-control', function () {

        var tr = $(this).closest('tr');
        var row = user_reward_table.row( tr );
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr.addClass('shown');
        }
    } );
    //高亮显示
    $('table.dataTable tbody').on( 'click', 'tr', function () {
        $('.dataTable tr.highlight').removeClass('highlight');
        $(this).addClass('highlight');
    } );
});
function format (row) {
    var html = "";
    html = '<table cellpadding="5" class="dataTable" cellspacing="0" border="0" style="margin-left: 110px;width: 54%;float: left;">';
    $.ajax({
        type: 'post',
        async: false,
        url: '/system/userRewardInfo/getUserRewardInfo',
        data: {id: row.id},
        cache: false,
        success: function (data) {
            var json = data;

            $.each(JSON.parse(json),function (index,user){
                if ($.trim($('#search').val())==''||user.userName.indexOf($.trim($('#search').val()))==-1){
                    html +='<tr>' +
                        '<td style="text-align: right">姓名:</td>' +
                        '<td>' + user.userName + '</td>' +
                        '<td style="text-align: right">身份证号:</td>' +
                        '<td>' + user.idCard+ '</td>' +
                        '<td style="text-align: right">车牌号:</td>' +
                        '<td>' + user.vehicleNo+ '</td>' +
                        '</tr>';
                }else {
                    html +='<tr style="background-color: #01ff70">' +
                        '<td style="text-align: right">姓名:</td>' +
                        '<td>' + user.userName + '</td>' +
                        '<td style="text-align: right">身份证号:</td>' +
                        '<td>' + user.idCard+ '</td>' +
                        '<td style="text-align: right">车牌号:</td>' +
                        '<td>' + user.vehicleNo+ '</td>' +
                        '</tr>';
                }


            })
            html += '</table>';

        },
        error: function (a, b, c) {
            console.log(a)
        }
    });
    return html;
}
