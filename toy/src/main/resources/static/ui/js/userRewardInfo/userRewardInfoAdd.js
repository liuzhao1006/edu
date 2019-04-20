/**
 * Created by xiong on 2017/8/17.
 */

$(function(){
    $('#btnsubmit').click(function(){
        SaveDialog('/system/userRewardInfo/doAdd',null,null,true,function(){
            _table.ajax.reload();
        });
        return false;
    })
});