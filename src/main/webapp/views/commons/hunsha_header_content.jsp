<%-- 剥离出来的头部 --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="preheader">
  		  <div class="pre-h-content">
            <div class="pre-h-left">
                 欢迎邮件： <span>womaime@163.com</span> 服务时间：9:00 - 21:00 
                  &nbsp;&nbsp;关注微博<a href="http://t.qq.com/licaime_admin" class="qq" target="_blank"></a>
		          <a href="http://weibo.com/u/5236761311" class="weibo" target="_blank"></a>
                 <!-- <a class="weixin"></a> -->
            </div>
            <div class="pre-h-right">
              <ul>
              <c:if test="${username==null || username=='guest'}">
              	   <li><a href="${_contextPath}/member/toLogin.htm">登录</a></li>
                	<li><a href="${_contextPath}/member/toRegister.htm">免费注册</a></li>
              </c:if>
              <c:if test="${username!=null && username!='guest'}">
              	   <li><span style=" display: inline-block;font-size: 13px;line-height: 50px;padding: 1px 10px;font-weight: 600;">欢迎${username}</span></li>
              	   <li><a href="${_contextPath}/member/loginout.htm">退出</a></li>
              </c:if>
              </ul>
            </div>
        </div>
</div>
<div class="prenav">
	  <div class="prenav-content">
	   <a class="prenav-img" href="http://www.womaime.com/index.htm">
	    <img src="/img/hunsha/hunsha.jpg">
	   </a>
	   <div class="prenav-wenzi">
	    <ul>
	     	<li class="${menu eq 'dog'?'cur':''}"><a href="http://www.womaime.com/index.htm">婚纱照</a></li>
	    </ul>
	   </div>
	  </div>
</div>