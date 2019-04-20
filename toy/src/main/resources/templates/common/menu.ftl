<aside class="main-sidebar">
 <!-- sidebar: style can be found in sidebar.less -->
 <section class="sidebar">
   <!-- Sidebar user panel (optional) -->
  <#-- <div class="user-panel">
     <div class="pull-left image">
       <img src="${(me.userImg)!}" class="img-circle">
     </div>
     <div class="pull-left info">
       <p>${(me.userName)!'游客'}</p>
       <!-- Status &ndash;&gt;
       <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
     </div>
   </div>-->

    <!-- search form -->
    <#-- <form action="#" method="get" class="sidebar-form">
         <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索关键词">
          <span class="input-group-btn">
            <button type="submit" name="search" id="search-btn" class="btn btn-flat">查询</button>
          </span>
        </div>
      </form>-->
      <!-- /.search form -->
     <div class="menuBtn sidebar-toggle" data-toggle="offcanvas" role="button">
         <!-- Sidebar toggle button-->
         <i class="fa-navicon fa"></i> <#--<img src="/app/images/menuL.png" alt="">-->
     </div>
   <!-- Sidebar Menu -->
   <ul class="sidebar-menu">
     <!-- Optionally, you can add icons to the links -->
     <#--<li class="header">系统菜单</li>-->
     <#if treeMenus??>
	     <#list treeMenus as vo>
		     <li class="treeview <#if res?? && vo.sysMenu.id==res> active </#if> ">
		       <a href="javascript:void(0)"><i class="fa ${(vo.sysMenu.icon)!}"></i>
		       <span>${(vo.sysMenu.menuName)!}</span>  
		       <i class="fa fa-angle-right pull-right"></i>
		       </a>
		       <ul class="treeview-menu">
		         <#list vo.children as ch>
		         	<li><a onclick="GetUrlInfo('${(ch.url)!}?res=${(vo.sysMenu.id)!}&cur=${(ch.id)!}')"  <#--style="<#if cur?? && ch.id==cur> color: white </#if>"-->><#--<i class="fa ${(ch.icon)!}"></i>--> ${(ch.menuName)!}</a></li>
		         </#list>
		       </ul>
		     </li>
	     </#list>
     </#if>
   </ul><!-- /.sidebar-menu -->
 </section>
 <!-- /.sidebar -->
</aside>

