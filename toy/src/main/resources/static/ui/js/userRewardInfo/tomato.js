/*document.write("<script language='javascript' src='/plugins/BootstrapMultiselectCommon.js'></script>");*/
var oTable02;
var ids=[];
//数据管理
var dataManager = {
    //把dt的相关数据设置到浏览器本地缓存
    "setData": function (data) {
    },
    //更新浏览器本地缓存里dt的相关数据
    "updateData": function (data) {
    },
    //从浏览器本地缓存获取dt的所有相关数据
    "getData": function () {
        inittable();
        var id=$("#id").val();//获得奖惩Id
        var result=[];
        //获得关联人员奖惩信息
        $.ajax({
            type:'post',
            url:'/system/userRewardInfo/getUserRewardInfo',
            async:false,
            dataType:"json",
            data:{"id":id},
            success:function(data){
                $.each(data,function (index,item) {
                    var temp={};
                    addRow();
                    multiselect.initselect('qq'+(i-1),false,true,null,null);
                    temp.id=item.id;
                    temp.userId=item.userId;
                    temp.idCard=item.idCard;
                    temp.vehicleNo=item.vehicleNo;
                    temp.action=creater.createButton();
                    result.push(temp);
                    $("#qq"+(i-1)).val(item.userId);
                    var selectText=$("#qq"+(i-1)).find("option[value='"+item.userId+"']").text();
                    $("#qq"+(i-1)).parent().find(".multiselect-selected-text").html(selectText);
                    $("#idCard"+(i-1)).val(item.idCard);
                    $("#vehicle"+i).val(item.vehicleNo);
                })
            }
        });
    },
    //重新初始化数据
    "reInitData": function () {

    },
    "setVisibleForRow": function (flag) {

    },
    "getVisibleForRow": function () {

    }
};
var i=0;
var creater = {
    //操作列的按钮创建
    "createButton": function (type) {
        if (type === 'save') {
            return '<a class="delete" href="#">删除</a>';
        } else {
            return '<a class="delete" href="#">删除</a>';
        }
    },
    //input生成
    "createInput": function (val,type,name,difEdit) {
        if(type==0){
            var groupId=$("#groupIds").val();
            var str="<select name='"+name+"' id='qq"+i+"' onchange='a("+i+")' class='form-control'>";
            str+="<option value=''>-请选择-</option>";
            $.ajax({
                type:'post',
                url:'/system/userRewardInfo/getUserName',
                async:false,
                dataType:"json",
                data:{"groupId":groupId},
                success:function(data){
                    $.each(data,function (index,item) {
                        str+="<option value='"+item.id+"'>"+item.userName+"</option>";
                    })
                    str+="</select>";
                }
            });
            if(difEdit!=null){
                $("select[name='vehicleId']").val(difEdit);
            }
            return str;
        }else if(type==1){
            return '<input name="'+name+'" type="text" id="idCard'+i+'"  class="form-control">';
        }else if (type==2){
            i++;
            return '<input name="'+name+'" type="text" id="vehicle'+i+'"  class="form-control">';
        }
    },
    //生成一个行对象
    "createRowObj": function (json) {
        var actionTem = creater.createButton();
        var userName = json ? json.userName || "" : "";
        var idCard = json ? json.idCard || "" : "";
        var vehicleNo = json ? json.vehicleNo || "" : "";
        var action = json ? json.action || actionTem : actionTem;
        return {
            "userName": userName,
            "idCard": idCard,
            "vehicleNo": vehicleNo,
            "action": action
        };
    }
};

function a(id) {
    var sum= $("input[name='vehicleNo']:last").attr("id").substring(7);
    if ($("#qq"+id+"").val()==''){
        $("#qq"+id+"").val('');
        $("#idCard"+id+"").val("");
        $("#vehicle"+(id+1)+"").val("");
        return false;
    }
    for(var i=0;i<sum;i++) {
        var userid = $("#qq" + i + "").val();
        var newid=$("#qq"+id+"").val();

        if ((userid != ''||userid!=null) && newid != ''&&i!=id){
            if(userid==newid){
                Commonalert("不能添加重复人员");
                $("#qq"+id+"").val('');
                $("#idCard"+id+"").val("");
                $("#vehicle"+(id+1)+"").val("");
                return false;
            }
        }
    }
    b($("#qq"+id+"").val(),id);
}
function b(a,b){
    $.ajax({
        type:'post',
        url:'/system/userRewardInfo/getUser',
        async:false,
        dataType:"json",
        data:{"id":a},
        success:function(data){
            $("#idCard"+b+"").val(data.idCard);
            $("#vehicle"+(b+1)+"").val(data.registrationNo);
        }
    });

}

$(function(){
    if($("#id").length == 0 || $("#id").val() == undefined){
        inittable([]);
    }else{
        dataManager.getData();
    }


    $('#userRewardInfoDataTable').on("click", "a.edit",function (e) {
        e.preventDefault();
        var nRow = $(this).parents('tr')[0];
        if (this.innerHTML == "保存") {
            saveRow(oTable02, nRow);
        }
        else {
            editRow(oTable02, nRow);
        }
    });

    $('#userRewardInfoDataTable').on("click", "a.delete", function (e) {
        e.preventDefault();
        var nRow = $(this).parents('tr')[0];
        oTable02.row(nRow).remove().draw(false);
    });
    $(".jconfirm-content #groupIds").change(function () {  //重选机构时，清空原来添加的所有记录
        oTable02.rows().remove().draw(false);
    })
});

function inittable(tabledata){
    oTable02 = $('#userRewardInfoDataTable').DataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION_NOPAGE,{
        columnDefs: [
            {'title': "姓名", 'targets': 0},
            {'title': "身份证", 'targets': 1},
            {'title': "车牌号", 'targets': 2},
            {'title': "操作", 'targets': 3}
        ],
        bPaginate:false,
        bLengthChange:false,
        bFilter:false,
        bInfo:false,
        "dom": "BR<'row'<'col-md-6'l><'col-md-6'f>r>" +
        "t" +
        "<'row'<'col-md-4 sm-center'i><'col-md-4'><'col-md-4 text-right sm-center'p>>",
        "order": [[2, "asc"], [0, "des"]],
        "columns": [
            {
                "data": "userName",
                "render": function (data, type, row, meta) {
                    return data;
                }
            },
            {
                "data": "idCard",
                "render": function (data, type, row, meta) {
                    return data;
                }
            },{
                "data": "vehicleNo",
                "render": function (data, type, row, meta) {
                    return data;
                }
            },

            {"data": "action"}
        ],
        "data":tabledata,
        "buttons": [
            {
                text: '添加人员',
                action: function (e, dt, node, config) {
                    e.preventDefault();
                    addRow();
                    multiselect.initselect('qq'+(i-1),false,true,null,null);
                }
            }
        ]
    }));
}

//把当前行变为可编辑状态
function editRow(oTable02, nRow) {
    var aData = oTable02.row(nRow).data();
    var jqTds = $('>td', nRow);
    $(jqTds[0]).html(creater.createInput(aData.userName,0,'userName',null));
    $(jqTds[1]).html(creater.createInput(aData.idCard,1,'idCard',null));
    $(jqTds[2]).html(creater.createInput(aData.vehicleNo,2,'vehicleNo',null));
    $(jqTds[3]).html(creater.createButton("save"));
    oTable02.draw();
}
//添加一行
function addRow() {
    var aiNew = oTable02.row.add(creater.createRowObj());
    var nRow = oTable02.row(aiNew[0]).node();
    editRow(oTable02, nRow);
    $(aiNew).find('td:last-child').addClass('actions text-center');
}
//保存行，把数据写入dt的内部数据对象，并重绘表格
function saveRow(oTable02, nRow) {

    var jqInputs = $('input', nRow);
    var rowObj = oTable02.row(nRow);
    var id = rowObj.data().id;
    var json = {
        id: id,
        userName: $(jqInputs[0]).val(),
        idCard: $(jqInputs[1]).val(),
        vehicleNo:$(jqInputs[2]).val(),
        action: creater.createButton()
    };
    var tmpobj = creater.createRowObj(json);
    rowObj.data(tmpobj).draw();
    dataManager.updateData(oTable02.data());
}



