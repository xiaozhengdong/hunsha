package org.guili.ecshop.impl.hunsha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.bean.hunsha.HunshaZhaoTag;
import org.guili.ecshop.dao.hunsha.IHunshaZhaoDao;
import org.guili.ecshop.util.BasicSqlSupport;

/**
 * 婚纱dao
 * @author Administrator
 *
 */
public class HunshaZhaoDao extends BasicSqlSupport implements IHunshaZhaoDao {
	private static Logger logger=Logger.getLogger(HunshaZhaoDao.class);

	private static final int max_keywords=200;
	
	@Override
	public Integer saveHunshaZhao(HunshaZhao hunshaZhao) throws Exception {
		int count=0;
		try {
			count=this.session.insert("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.saveHunshaZhao", hunshaZhao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public HunshaZhao selectHunshaZhao(Integer id) throws Exception {
		HunshaZhao hunshaZhao=new HunshaZhao();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.selectHunshaZhao", paramMap);
		if(list!=null && list.size()>0){
			hunshaZhao=(HunshaZhao)list.get(0);
		}else{
			hunshaZhao=null;
		}
		logger.debug("success!");
		return hunshaZhao;
	}

	@Override
	public Integer selectHunshaZhaoCount(HunShaPageParam hunShaPageParam) {
		int count=0;
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("typeId", hunShaPageParam.getTypeId());
		paramMap.put("companyId", hunShaPageParam.getCompanyId());
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.selectHunshaZhaoCount", paramMap);
		if(list!=null && list.size()>0){
			count=(Integer)list.get(0);
		}
		logger.debug("success!");
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HunshaZhao> selectHunshaZhaoPage(int start, int pageSize,
			HunShaPageParam hunShaPageParam) {
		List<HunshaZhao> petSaleInfoList=new ArrayList<HunshaZhao>();
		//拼sql参数
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("start", start);
		paramMap.put("pageSize", pageSize);
		paramMap.put("typeId", hunShaPageParam.getTypeId());
		paramMap.put("companyId", hunShaPageParam.getCompanyId());
		petSaleInfoList=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.selectHunshaZhaoPage", paramMap);
		logger.debug("success!size is:"+petSaleInfoList.size());
		return petSaleInfoList;
	}

	@Override
	public Integer saveHunshaZhaoTag(HunshaZhaoTag hunshaZhaoTag) throws Exception {
		//基础校验
		if(hunshaZhaoTag.getHunsha_id()==null || hunshaZhaoTag.getTag_id()==null){
			return 0;
		}
		
		int count=0;
		try {
			count=this.session.insert("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.saveHunshaZhaoTag", hunshaZhaoTag);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public Integer updateHunshaZhaoKeyWords(HunshaZhao hunshaZhao)
			throws Exception {
		//基础校验
		if(hunshaZhao.getId()==null || StringUtils.isEmpty(hunshaZhao.getKeywords())){
			return 0;
		}
		if(hunshaZhao.getKeywords().length()>max_keywords){
			hunshaZhao.setKeywords(hunshaZhao.getKeywords().substring(0, max_keywords));
		}
		
		//构造请求map
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", hunshaZhao.getId());
		paramMap.put("keywords", hunshaZhao.getKeywords());
		
		//发起请求
		@SuppressWarnings("rawtypes")
		int result=this.session.update("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.updateHunshaZhaoKeyWords", paramMap);
		
		logger.debug("success!");
		return result;
	}

	@Override
	public HunshaZhaoTag selectByTagIdAndHunshaId(Integer hunsha_id,
			Integer tag_id) throws Exception {
		
		HunshaZhaoTag hunshaZhaoTag=new HunshaZhaoTag();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("hunsha_id", hunsha_id);
		paramMap.put("tag_id", tag_id);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.selectByTagIdAndHunshaId", paramMap);
		if(list!=null && list.size()>0){
			hunshaZhaoTag=(HunshaZhaoTag)list.get(0);
		}else{
			hunshaZhaoTag=null;
		}
		logger.debug("success!");
		return hunshaZhaoTag;
	}
	
	@Override
	public List<HunshaZhaoTag> selectByHunshaId(Integer hunsha_id) throws Exception {
		
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("hunsha_id", hunsha_id);
		@SuppressWarnings("rawtypes")
		List list=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.selectByHunshaId", paramMap);
		logger.debug("success!");
		return list;
	}
	
	
}
