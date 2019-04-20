 var multiselectoption={
          nonSelectedText:'请选择',
          filterPlaceholder:'查询',
          selectAllText:'全选',
          nSelectedText:'选中',
          allSelectedText:'全部选中',
          selectedClass: null,
          maxHeight:200



};
 var multiselect ={
  initselect :function(selector,includeSelectAllOption,enableFiltering,setdataarray,onChange){
            $("#"+selector).multiselect('destroy');
            $("#"+selector).multiselect($.extend(true,{},multiselectoption, {
                    includeSelectAllOption:includeSelectAllOption,
                    enableFiltering:enableFiltering,
                    onChange:function(element, checked){
                        if( $.isFunction(onChange)){
                           onChange();
                        }
                    }
                  }));
                if(setdataarray!=undefined && setdataarray.length > 0){
                     $("#"+selector).multiselect('select', setdataarray);
                 }
            
  },
  initajaxcheckboxselect :function(selector,url,includeSelectAllOption,enableFiltering,setdataarray,onChange){
    if(url!=null && url!=undefined){
      $.ajax({
            type: 'get',
            async: false,
            url: url,
            cache: false,
            success: function (data) {
                var result =[];
                data.forEach(function(item,index){
                  var temp={};
                  temp.label= item.name;
                  temp.value=item.value;
                  result.push(temp);
                })
                 multiselect.initselect(selector,includeSelectAllOption,enableFiltering,setdataarray,onChange);
               $("#"+selector).multiselect('dataprovider', result);
            },
            error:function (a,b,c) {
                //alert(a);
            }
        });
    }
  },
}