package org.guili.ecshop.business.impl.product;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.business.product.IHunshaZhaoService;
import org.guili.ecshop.dao.hunsha.IHunshaZhaoDao;

/**
 * 婚纱照服务类
 * @author zhengdong.xiao
 *
 */
public class HunshaZhaoService implements IHunshaZhaoService {

	private static Logger logger=Logger.getLogger(HunshaZhaoService.class);
	private IHunshaZhaoDao  hunshaZhaoDao;
	@Override
	public HunshaZhao selectHunshaZhao(Integer id) throws Exception {
		 HunshaZhao hunshaZhao=hunshaZhaoDao.selectHunshaZhao(id);
		 //转换图片列表
		 return this.convertHunshaZhao(hunshaZhao);
	}
	
	/**
	 * String 转换为list
	 * @param hunshaZhao
	 * @return
	 */
	private HunshaZhao convertHunshaZhao(HunshaZhao hunshaZhao){
		if(hunshaZhao==null || StringUtils.isEmpty(hunshaZhao.getTupians())){
			return  hunshaZhao;
		}
		String[] alltupians=hunshaZhao.getTupians().split(",");
		List<String> tupianList=new ArrayList<String>();
		for(String tupian:alltupians){
			tupianList.add(tupian);
		}
		hunshaZhao.setAllTupians(tupianList);
		return hunshaZhao;
	}

	@Override
	public Integer saveHunshaZhao(HunshaZhao HunshaZhao) throws Exception {
		return hunshaZhaoDao.saveHunshaZhao(HunshaZhao);
	}

	public IHunshaZhaoDao getHunshaZhaoDao() {
		return hunshaZhaoDao;
	}

	public void setHunshaZhaoDao(IHunshaZhaoDao hunshaZhaoDao) {
		this.hunshaZhaoDao = hunshaZhaoDao;
	}

	@Override
	public Integer selectHunshaZhaoCount(HunShaPageParam hunShaPageParam) {
		return hunshaZhaoDao.selectHunshaZhaoCount(hunShaPageParam);
	}

	@Override
	public List<HunshaZhao> selectHunshaZhaoPage(int start, int pageSize,
			HunShaPageParam hunShaPageParam) {
		return hunshaZhaoDao.selectHunshaZhaoPage(start, pageSize, hunShaPageParam);
	}

}
