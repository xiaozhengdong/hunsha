<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shopel" uri="/WEB-INF/pagetag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="image/x-icon" href="/ico/hunqing.ico" rel="shortcut icon" />
		<link rel="canonical" href="http://www.womaime.com" />
		<title>婚纱照|婚纱图片|韩式婚纱照|中式婚纱摄影|古装武侠风格婚纱照|个性创意风格婚纱照|欧美风格婚纱照|性感风格婚纱照|时尚风格婚纱照  - 我买么-婚纱照网</title>
		<meta name="keywords" content="我买么-婚纱照网,韩式婚纱摄影,中式婚纱摄影,古装武侠风格婚纱照,个性创意风格婚纱照,欧美风格婚纱照,性感风格婚纱照,时尚风格婚纱照"/>
		<meta name="description" content="我买么-婚纱照网,是新婚佳人挑选自己最喜欢的婚纱照的网站. 
提供全国最全面最丰富的婚纱照。囊括海量专业的婚纱摄影机构提供的婚纱摄影图片，艺术照图片。婚纱照类型包括：
中式婚纱-中国红婚纱照，韩式婚纱摄影-唯美韩式婚纱照，欧美风格婚纱照，古典风格-怀旧复古风婚纱照，性感风格婚纱照，小清新风格婚纱照，时尚风格婚纱照，情侣装风格婚纱照,学生装风格婚纱照,古装武侠风格婚纱照,个性创意风格婚纱照等。"/>
		<!-- 公共setting配置 -->
		<%@include file="/views/commons/config.jsp" %>
		<%@include file="/views/commons/cssandscript.jsp" %>
		<link rel="stylesheet" href="${domain_css}/css/licai/style.css?${version_css}" />
		<link rel="stylesheet" href="${domain_css}/css/chongwu/home.css?${version_css}" />
		<script type="text/javascript">
			//延迟加载
			$(document).ready(function () {
	            $(".b_f_item_c_image").lazyload({
		            effect: "fadeIn",
		            threshold : 200 
	            });
	        });
		</script>
		<style>
		.noshow{
			display:none;
		}
		</style>
	</head>
	<body>
	<div class="noshow" >
	我买么-婚纱照网,是新婚佳人挑选自己最喜欢的婚纱照的网站. 
提供全国最全面最丰富的婚纱照。囊括海量专业的婚纱摄影机构提供的婚纱摄影图片，艺术照图片。婚纱照类型包括：
中式婚纱-中国红婚纱照，韩式婚纱摄影-唯美韩式婚纱照，欧美风格婚纱照，古典风格-怀旧复古风婚纱照，性感风格婚纱照，小清新风格婚纱照，时尚风格婚纱照，情侣装风格婚纱照,学生装风格婚纱照,古装武侠风格婚纱照,个性创意风格婚纱照等。
		</div>
		<jsp:include page="/views/commons/hunsha_header_content.jsp"></jsp:include>
		<div class="p_goods_container">
            <ul class="clearfix pro_list">
            <c:forEach  var="product" items="${bList}" varStatus="index">
				<li class="brand_new">
				<div class="act-item-wrap" data-bid="391365" id="j_brand_391365">
		             <div class="b_f_con_img_box">
		                 <a class="b_f_item_c" target="_blank" href="/hunsha/addTagDetail.htm?id=${product.id}">
		                 <img width="310" class="b_f_item_c_image" src="${product.headTupian }"   data-original="${product.headTupian }" alt="${hunshaZhao.groupName}-${hunshaZhao.companyName}-${hunshaZhao.keywords}">
		                 </a>
		             </div>
		             <div class="b_f_info">
		                  <p class="b_f_brand">
		                  <a href="/hunsha/detail-${product.id}.htm" target="_blank">${product.groupName }</a>
		                  <a class="s_info_story is_story1" href="/hunsha/addTagDetail.htm?id=${product.id}" target="_blank"><span>${product.groupName }</span><i class="s_info_story_arrow"></i></a>
		                  </p>
		             </div>
		         </div>
		         </li>
		    </c:forEach>
            </ul>
        </div>
		<jsp:include page="/views/commons/hunsha_footer.jsp"></jsp:include>
		<div class="none">
		<!-- 百度统计 -->
			<script type="text/javascript">
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
			document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F19d3582fea421029f25811115d6cdf0e' type='text/javascript'%3E%3C/script%3E"));
			</script>
		</div>
	</body>
</html>