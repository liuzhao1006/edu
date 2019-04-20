/**
 * Created by xiong on 2017/8/16.
 */

$(function(){
    $('#btnsubmit').click(function(){
        SaveDialog('/system/userRewardInfo/doEdit',null,null,true,function(){
            _table.ajax.reload();
        });
        return false;
    })
});
