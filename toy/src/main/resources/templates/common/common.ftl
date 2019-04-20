<#macro header>
<!DOCTYPE html>
<html>
<style>
    .upload{
        padding: 4px 10px;
        height: 20px;
        line-height: 20px;
        position: relative;
        border: 1px solid #999;
        text-decoration: none;
        color: #666;
    }
    .change{
        position: absolute;
        overflow: hidden;
        right: 0;
        top: 0;
        opacity: 0;
    }
    .colstylescroll{
        margin:0px;
        padding:15px 0px 15px 0px;
        vertical-align:middle; overflow-x:auto;  width:160px;  height:60px;  overflow-y:auto;
    }
</style>
</#macro>
<#macro body>
<div>
    <#nested>
</div>
</#macro>
<!-- /body -->
<#macro footer>
    <#nested>
</#macro>
</html>