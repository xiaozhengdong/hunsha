<%-- 剥离出来的头部 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="preheader">
		  		  <div class="pre-h-content">
		            <div class="pre-h-left">
		                 欢迎邮件： <span>licaime@163.com</span> 服务时间：9:00 - 21:00 
		                  &nbsp;&nbsp;关注微博<a href="http://t.qq.com/licaime_admin" class="qq" target="_blank"></a>
		                 <a href="http://weibo.com/u/5236761311" class="weibo" target="_blank"></a>
		                 <!-- <a class="weixin"></a> -->
		            </div>
		            <div class="pre-h-right">
		              <ul>
		                <li><a href="">登录</a></li>
		                <li><a href="">免费注册</a></li>
		                <!-- <li><a href=""><i></i>新手专区</a></li> -->
		              </ul>
		            </div>
		        </div>
		</div>
		<div class="prenav">
			  <div class="prenav-content">
			   <a class="prenav-img" href="${_contextPath}/index.htm">
			    	<img src="../../img/licai/licaime1.png">
			   </a>
			   <div class="prenav-wenzi">
				     <ul>
					     <li class="${menu eq 'dog'?'cur':''}"><a href="${_contextPath}/dog.htm">宠物狗</a></li>
					     <%-- <li class="${menu eq 'cat'?'cur':''}"><a href="">宠物猫</a></li> --%>
					     <li  class="${menu eq 'user'?'cur':''}"><a href="${_contextPath}/member/userinfo.htm">账户中心</a></li>
				    </ul>
			   </div>
			  </div>
		</div>