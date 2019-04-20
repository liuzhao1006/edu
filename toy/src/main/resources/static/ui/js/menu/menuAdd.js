/**
 * Created by xiong on 2017/8/24.
 */

$(function(){
    $('#btnsubmit').click(function(){
        SaveDialog('/system/menu/doAddDir', null, null,null,function(){
            _table.ajax.reload();
        });
        return false;
    })
    $('#btnsubmit1').click(function(){
        SaveDialog('/system/menu/doAddMenu', null, 'form2',null,function(){
            _table.ajax.reload();
        });
        return false;
    })
    $('#btnsubmit2').click(function(){
        SaveDialog('/system/menu/doAddAction',null, 'form3',null,function(){
            _table.ajax.reload();
        });
        return false;
    })

});

//todo此处渲染dropdownlist
var menuicons = menuicons;
var str="";
$.each(menuicons,function(i, val) {
    str +='<li data-option="'+i+'"><i class="fa '+i+'"></i>'+val+'</li>';
});
$(".model-select-option ul").html(str);


/*下拉框*/
function selectModel(){
    var box = $('div.model-select-box');
    var option = $('.model-select-option',box);
    var txt = $('input.model-select-text', box);
    var speed = 10;
    /*
     * 单击某个下拉列表时，显示当前下拉列表的下拉列表框
     * 并隐藏页面中其他下拉列表
     */
    txt.click(function(e) {
        option.not($(this).siblings('.model-select-option')).slideUp(speed);
        $(this).siblings('.model-select-option').slideToggle(speed);
        return false;
    });
    //点击选择，关闭其他下拉
    /*
     * 为每个下拉列表框中的选项设置默认选中标识 data-selected
     * 点击下拉列表框中的选项时，将选项的 data-option 属性的属性值赋给下拉列表的 data-value 属性，并改变默认选中标识 data-selected
     * 为选项添加 mouseover 事件
     */
    option.find('li').mousedown(function(){
        $(this).parent().parent().siblings('input.model-select-text').val($(this).text())
            .attr('data-value', $(this).attr('data-option'));
        var $label= $(".model-select-box").parent().siblings(".control-label");
        if( $label.find("i").size()>0){
            $label.find("i").remove();
            $label.append('<i class="fa '+$(this).attr("data-option")+'"></i>');
        }else{
            $label.append('<i class="fa '+$(this).attr("data-option")+'"></i>');
        }

        option.slideUp(speed);
        $(this).addClass('data-selected').siblings('li').removeClass('seleced data-selected');

        return false;

    })
}

selectModel();