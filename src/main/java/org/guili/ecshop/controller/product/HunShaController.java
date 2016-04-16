package org.guili.ecshop.controller.product;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
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

	//设置回显参数
	private String setParams(Integer adressId,Integer companyId,HunShaPageParam cwPageParam){
		StringBuffer urlParam=new StringBuffer();
		urlParam.append("dog-");
		//构造地址
		if(adressId!=null){
			urlParam.append(adressId+"-");
			cwPageParam.setAdressId(adressId);
		}else{
			urlParam.append("-");
		}
		//构造公司id
		if(companyId!=null){
			urlParam.append(companyId+"-");
			cwPageParam.setAdressId(companyId);
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
}
