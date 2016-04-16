package org.guili.ecshop.impl.hunsha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;
import org.guili.ecshop.dao.hunsha.IHunshaZhaoDao;
import org.guili.ecshop.util.BasicSqlSupport;

/**
 * 婚纱dao
 * @author Administrator
 *
 */
public class HunshaZhaoDao extends BasicSqlSupport implements IHunshaZhaoDao {
	private static Logger logger=Logger.getLogger(HunshaZhaoDao.class);

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
		paramMap.put("adressId", hunShaPageParam.getAdressId());
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
		paramMap.put("adressId", hunShaPageParam.getAdressId());
		paramMap.put("companyId", hunShaPageParam.getCompanyId());
		petSaleInfoList=this.session.selectList("org.guili.ecshop.dao.hunsha.IHunshaZhaoDao.selectHunshaZhaoPage", paramMap);
		logger.debug("success!size is:"+petSaleInfoList.size());
		return petSaleInfoList;
	}
	
}
