package org.guili.ecshop.controller.product;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.bean.hunsha.HunshaZhaoTag;
import org.guili.ecshop.bean.hunsha.HunshaZhaoTagVO;
import org.guili.ecshop.business.impl.product.HunShaType;
import org.guili.ecshop.business.product.IHunshaService;
import org.guili.ecshop.business.product.IHunshaZhaoService;
import org.guili.ecshop.controller.common.BaseProfileController;
import org.guili.ecshop.util.common.UrlHelper;
import org.guili.ecshop.util.common.page.Pager;
import org.guili.ecshop.util.common.page.PagerHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.collect.Lists;

/**
 * 婚纱controller
 * @author Administrator
 *
 */
@Controller
public class HunShaController extends BaseProfileController {
	
	//58抓取类
//	@Resource(name="hunShaDPSpider")
//	private IHunshaService hunShaDPSpider;
	@Resource(name="hrtizHunShaSpider")
	private IHunshaService hrtizHunShaSpider;
	@Resource(name="hunshaZhaoService")
	private IHunshaZhaoService hunshaZhaoService;
	private static final int PAGE_SIZE=21;
	
	//测试宠物抓取
//	@RequestMapping(value={ "/addDP.htm"},method={RequestMethod. GET})
//	public String test58(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
//							@RequestParam(value = "url", required = false) String url) throws Exception{
//		if(url==null ||url.isEmpty()){
//			return "redirect:/errors/404.htm";
//		}
//		if(url.startsWith("http://")){
//		}else{
//			url="http://"+url;
//		}
//		hrtizHunShaSpider.batCrawlHunsha(url);
//		return "redirect:/chongwu/fabu_success.htm";
//	}
	
	
	/**
	 * 婚纱首页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={ "/index.htm", "/" })
	public String home(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value = "adressId", required = false) Integer adressId,
			@RequestParam(value = "companyId", required = false) Integer companyId,
			@RequestParam(value = "pageNum", required = false) Integer pageNum
			) throws Exception{
		List<HunshaZhao> goodProductList=new ArrayList<HunshaZhao>();
		HunShaPageParam hunShaPageParam=new HunShaPageParam();

		//用于回显的url部分
		String urlParam=this.setParams(adressId,companyId,hunShaPageParam);
		
		//查询照片集总数
		int totalRow = hunshaZhaoService.selectHunshaZhaoCount(hunShaPageParam);
		
		//拼接分页参数
		Pager pager=null;
		String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
		if(pageNum!=null){
			pager = PagerHelper.getPager(pageNum, totalRow,PAGE_SIZE); //初始化分页对象  
		}else{
			pager = PagerHelper.getPager(1, totalRow,PAGE_SIZE); 	  //初始化分页对象  
		}
		pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
		request.setAttribute("pager", pager); 						  //将分页信息保存在Request对象  
		//分页参数
		request.setAttribute("urlParam", urlParam);
		
		//查询一页数据
		goodProductList=hunshaZhaoService.selectHunshaZhaoPage(pager.getStartRow(), pager.getPageSize(),hunShaPageParam);
		
//		Map<String, Map<String, PetAddress>> addressPetMap=petAddressService.initAddressPetMap();
//		List<PetAddress> petAddressList=petAddressService.selectAllPetAddress();
		//商品列表信息回显
		request.setAttribute("bList", goodProductList);
//		request.setAttribute("petAddressList", petAddressList);
		request.setAttribute("pageParam", hunShaPageParam);
		//设置菜单栏
		request.setAttribute("menu", "dog");
		return "hunsha/hunsha_home";
	}
	
	/**
	 * 理财产品首页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param url
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={ "/hunsha.htm"},method={RequestMethod. GET})
	public String index(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value = "typeId", required = false) Integer typeId,
			@RequestParam(value = "companyId", required = false) Integer companyId,
			@RequestParam(value = "pageNum", required = false) Integer pageNum
			) throws Exception{
		List<HunshaZhao> goodProductList=new ArrayList<HunshaZhao>();
		HunShaPageParam hunShaPageParam=new HunShaPageParam();

		//用于回显的url部分
		String urlParam=this.setParams(typeId,companyId,hunShaPageParam);
		
		//查询照片集总数
		int totalRow = hunshaZhaoService.selectHunshaZhaoCount(hunShaPageParam);
		
		//拼接分页参数
		Pager pager=null;
		String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
		if(pageNum!=null){
			pager = PagerHelper.getPager(pageNum, totalRow,PAGE_SIZE); //初始化分页对象  
		}else{
			pager = PagerHelper.getPager(1, totalRow,PAGE_SIZE); 	  //初始化分页对象  
		}
		pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
		request.setAttribute("pager", pager); 						  //将分页信息保存在Request对象  
		//分页参数
		request.setAttribute("urlParam", urlParam);
		
		//查询一页数据
		goodProductList=hunshaZhaoService.selectHunshaZhaoPage(pager.getStartRow(), pager.getPageSize(),hunShaPageParam);
		
		//商品列表信息回显
		request.setAttribute("bList", goodProductList);
//		request.setAttribute("petAddressList", petAddressList);
		request.setAttribute("pageParam", hunShaPageParam);
		//设置菜单栏
//		request.setAttribute("menu", "dog");
		return "hunsha/hunsha_home";
	}

	//设置回显参数
	private String setParams(Integer typeId,Integer companyId,HunShaPageParam cwPageParam){
		StringBuffer urlParam=new StringBuffer();
		urlParam.append("hunsha-");
		//构造地址
		if(typeId!=null){
			urlParam.append(typeId+"-");
			cwPageParam.setTypeId(typeId);
		}else{
			urlParam.append("-");
		}
		//构造公司id
		if(companyId!=null){
			urlParam.append(companyId+"-");
			cwPageParam.setCompanyId(companyId);
		}else{
			urlParam.append("-");
		}
        return urlParam.toString();
	}
	
	
	@RequestMapping(value={"/hunsha/detail.htm"},method={RequestMethod. GET})
	public String detail(HttpServletRequest request,HttpServletResponse response,
					ModelMap modelMap,@RequestParam(value = "id", required = false) Integer id 
					) throws Exception{
		//宠物详细信息
		HunshaZhao hunshaZhao=hunshaZhaoService.selectHunshaZhao(id);
		//相关宠物
		if(hunshaZhao==null){
			request.setAttribute("hunshaZhao", hunshaZhao);
			return "redirect:/errors/404.htm";
		}
		request.setAttribute("hunshaZhao", hunshaZhao);
		return "hunsha/hunsha_detail";
	}
	
	//标签添加
	@RequestMapping(value={ "/hunsha/toAddTag.htm"},method={RequestMethod.GET})
	public String toAddTag(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value = "typeId", required = false) Integer typeId,
			@RequestParam(value = "companyId", required = false) Integer companyId,
			@RequestParam(value = "pageNum", required = false) Integer pageNum
			) throws Exception{
		List<HunshaZhao> goodProductList=new ArrayList<HunshaZhao>();
		HunShaPageParam hunShaPageParam=new HunShaPageParam();

		//用于回显的url部分
		String urlParam=this.setParams(typeId,companyId,hunShaPageParam);
		//查询照片集总数
		int totalRow = hunshaZhaoService.selectHunshaZhaoCount(hunShaPageParam);
		
		//拼接分页参数
		Pager pager=null;
		String basePath=UrlHelper.getRealPath(request); 			  //基础绝对路径
		if(pageNum!=null){
			pager = PagerHelper.getPager(pageNum, totalRow,PAGE_SIZE); //初始化分页对象  
		}else{
			pager = PagerHelper.getPager(1, totalRow,PAGE_SIZE); 	  //初始化分页对象  
		}
		pager.setLinkUrl(basePath+request.getRequestURI()); 		  //设置跳转路径  
		request.setAttribute("pager", pager); 						  //将分页信息保存在Request对象  
		//分页参数
		request.setAttribute("urlParam", urlParam);
		
		//查询一页数据
		goodProductList=hunshaZhaoService.selectHunshaZhaoPage(pager.getStartRow(), pager.getPageSize(),hunShaPageParam);
		
		//商品列表信息回显
		request.setAttribute("bList", goodProductList);
//		request.setAttribute("petAddressList", petAddressList);
		request.setAttribute("pageParam", hunShaPageParam);
		return "hunsha/hunsha_back_home";
	}
	
	@RequestMapping(value={"/hunsha/addTagDetail.htm"},method={RequestMethod. GET})
	public String addTagDetail(HttpServletRequest request,HttpServletResponse response,
					ModelMap modelMap,@RequestParam(value = "id", required = false) Integer id 
					) throws Exception{
		//婚纱详细信息
		HunshaZhao hunshaZhao=hunshaZhaoService.selectHunshaZhao(id);
		
		HunShaType[] hunShaTypes=HunShaType.values();
		//婚纱标签列表
		
		if(hunshaZhao==null){
			return "redirect:/errors/404.htm";
		}
		//构建tags
		hunshaZhao.setTags(this.createHunshaZhaoTags(id));
		
		request.setAttribute("product", hunshaZhao);
		request.setAttribute("hunShaTypes", hunShaTypes);
		return "hunsha/hunsha_back_one";
	}
	
	/**
	 * 添加婚纱标签
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param keywords
	 * @param hunshaId
	 * @param tagIds
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value={ "/hunsha/addTag.htm"},method={RequestMethod.POST})
	public String addCategory(HttpServletRequest request,HttpServletResponse response,ModelMap modelMap,
			@RequestParam(value = "keywords", required = false) String keywords,
			@RequestParam(value = "hunshaId", required = false) Integer hunshaId,
			@RequestParam(value = "tagIds", required = false) String tagIds
			 ) throws Exception{
		
		HunshaZhaoTagVO hunshaZhaoTagVO =this.createHunshaZhaoTagVO(keywords, hunshaId);
		//构造婚纱标签
		if(StringUtils.isEmpty(tagIds)){
			return "redirect:/hunsha/addTagDetail.htm?id="+hunshaId;
		}
		
		hunshaZhaoTagVO.setHunshaZhaoTags(this.createHunshaZhaoTags(tagIds, hunshaId));
		//更新婚纱照信息
		hunshaZhaoService.updateHunshaZhao(hunshaZhaoTagVO);
		
		return "redirect:/hunsha/addTagDetail.htm?id="+hunshaId;
	}
	
	/**
	 * 构建婚纱vo
	 * @param keywords
	 * @param hunshaId
	 * @return
	 */
	private HunshaZhaoTagVO createHunshaZhaoTagVO(String keywords,Integer hunshaId){
		HunshaZhaoTagVO hunshaZhaoTagVO =new HunshaZhaoTagVO();
		hunshaZhaoTagVO.setKeywords(keywords);
		hunshaZhaoTagVO.setHunshaId(hunshaId);
		return hunshaZhaoTagVO;
	}
	
	private List<HunshaZhaoTag> createHunshaZhaoTags(Integer hunsha_id){
		List<HunshaZhaoTag> hunshaZhaoTags=Lists.newArrayList();
		try {
			hunshaZhaoTags = hunshaZhaoService.selectByHunshaId(hunsha_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hunshaZhaoTags;
	}
	
	/**
	 * 构建婚纱标签
	 * @param tagIds
	 * @param hunshaId
	 * @return
	 */
	private List<HunshaZhaoTag> createHunshaZhaoTags(String tagIds,Integer hunshaId){
		List<HunshaZhaoTag> hunshaZhaoTags =Lists.newArrayList();
		String[] tagids=tagIds.split(",");
		for(String tagid:tagids){
			HunshaZhaoTag hunshaZhaoTag=new HunshaZhaoTag();
			hunshaZhaoTag.setTag_id(Integer.parseInt(tagid));
			hunshaZhaoTag.setHunsha_id(hunshaId);
			hunshaZhaoTag.setTag_name(HunShaType.getName(hunshaZhaoTag.getTag_id()));
			hunshaZhaoTags.add(hunshaZhaoTag);
		}
		return hunshaZhaoTags;
	}
}
