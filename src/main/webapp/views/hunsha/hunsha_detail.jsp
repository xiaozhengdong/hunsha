<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shopel" uri="/WEB-INF/pagetag.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link type="image/x-icon" href="${pageContext.request.contextPath}/ico/hunqing.ico" rel="shortcut icon" />
		<link rel="canonical" href="http://www.womaime.com" />
		<title>${hunshaZhao.groupName}|${hunshaZhao.companyName}|${hunshaZhao.keywords}- 我买么-婚纱照网</title>
		<meta name="keywords" content="${hunshaZhao.groupName}|${hunshaZhao.companyName}|${hunshaZhao.keywords}- 我买么-婚纱照网"/>
		<meta name="description" content="${hunshaZhao.groupName}|${hunshaZhao.companyName}|${hunshaZhao.keywords}- 我买么-婚纱照网"/>
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
		.contentmain {
		width: 960px;
		margin: 0 auto;
		}
		img {
		max-width: 960px;
		}
		.noshow{
			display:none;
		}
		</style>
	</head>
	<body>
		<div class="noshow" >
		${hunshaZhao.groupName}|${hunshaZhao.companyName}|${hunshaZhao.keywords}- 我买么-婚纱照网
		我买么-婚纱照网,是新婚佳人挑选自己最喜欢的婚纱照的网站. 
提供全国最全面最丰富的婚纱照。囊括海量专业的婚纱摄影机构提供的婚纱摄影图片，艺术照图片。婚纱照类型包括：
中式婚纱-中国红婚纱照，韩式婚纱摄影-唯美韩式婚纱照，欧美风格婚纱照，古典风格-怀旧复古风婚纱照，性感风格婚纱照，小清新风格婚纱照，时尚风格婚纱照，情侣装风格婚纱照,学生装风格婚纱照,古装武侠风格婚纱照,个性创意风格婚纱照等。
		</div>
		<jsp:include page="/views/commons/hunsha_header_content.jsp"></jsp:include>
		<div class="contentmain" style="margin-top:60px">
      		<div style="text-align:center;">
      		<div>${hunshaZhao.groupName}|${hunshaZhao.companyName}|${hunshaZhao.keywords}- 我买么-婚纱照网</div>
			<c:forEach  var="product" items="${hunshaZhao.allTupians}" varStatus="index">
					<img src="${product}" alt="${hunshaZhao.groupName}-${hunshaZhao.companyName}-${hunshaZhao.keywords}" />
			</c:forEach>
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