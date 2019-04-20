/**
 * Created by xiong on 2017/8/17.
 */
var _table = null;
$(function(){
    var $table = $('.acelistTable');
    _table = $table.dataTable($.extend(true,{},CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
        ajax : function(data, callback, settings) {
            var params ={};
            params.menuName=$.trim($('#search').val());
            params.menuParentName=$.trim($('#searchParent').val());
            datatablecommon.datatablesajax(data, callback, settings,"/system/menu/getpagedata",params);
        },
        columnDefs: [
            { "orderable": true, "targets": [1,3,5] },
            { "orderable": false, "targets":[0,2,4,6] }
        ],
        columns: [
            //CONSTANT.DATA_TABLES.COLUMN.CHECKBOX,
            {data: "menuName", title: "菜单名称"},
            {data: "code", title: "编码"},
            {data: "url", title: "访问地址",render:function(data, type, row, meta){
                return data != null? data:"--";
            }},
          /*  {data: "icon", title: "显示图标",render:function(data, type, row, meta){
                return data != null? "<i class='fa data'></i>":"";
            }},*/
            {data: "deep", title: "深度"},
            {data: "resource", title: "资源",render:function(data, type, row, meta){
                return data != null? data:"--";
            }},
            {data: "sort", title: "排序"},
            {data: "deep", title: "类型",render:function(data, type, row, meta){
                return data == 1? "目录":data==2?"菜单":"功能";
            }},

            {data: '' , title: "操作",render:function(data, type, row, meta){

                // "<i class='fa fa-pencil edit' data-toggle='tooltip' data-placement='bottom' title='编辑'></i>"
                //     + "<i class='fa fa-trash delete'  data-toggle='tooltip' data-placement='bottom'title='删除'></i>";
            return getOperatesColumn();
            }}
        ],
         drawCallback: function(settings) {      $("table.dataTable thead th").removeClass("sorting").hover(function(){
             $(this).addClass("sorting");
         },function(){
             $(this).removeClass("sorting");
         });
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
        //点击编辑按钮
        var item = _table.row($(this).closest('tr')).data();
        DeleteOne('/system/menu/delete',item.id,function(){
            _table.ajax.reload();
        });
    }).on("click",".edit",function() {
        //点击删除按钮
        var item = _table.row($(this).closest('tr')).data();
        var itemId=$('.highlight').index('tr');
        GetDialog('/system/menu/edit/'+item.id,'编辑','2','/system/menu/doEdit', null, function(){

            $.ajaxSetup( {
                async : false//设置get、post同步
            });
            _table.ajax.reload();
            $('.dataTable tr').eq(itemId).addClass("highlight");
            return false;
        },"0.4");
    }).on("click",".detail",function(){
        var item =_table.row($(this).closest('tr')).data();
        GetNormalDialog('/system/menu/details/'+item.id,'详情','',0.4);
    });

  /*  $('#btn_search').click(function(){
        //_table.ajax.reload();
        // _table.draw(false);
        _table.draw();
    })*/

    $('#btnadd').click(function(){
        GetDialog('/system/menu/add','创建','2','/system/menu/doAddDir,/system/menu/doAddMenu,/system/menu/doAddAction','#form1,#form2,#form3',function(){
            _table.ajax.reload();
            return false;
        },"0.4");
    });
    //高亮显示
    $('table.dataTable tbody').on( 'click', 'tr', function () {
        $('.dataTable tr.highlight').removeClass('highlight');
        $(this).addClass('highlight');
    } );
});

function SearchInfo() {
    _table.draw();
}
