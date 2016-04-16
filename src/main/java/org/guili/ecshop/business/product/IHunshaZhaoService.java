package org.guili.ecshop.business.product;

import java.util.List;

import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;

/**
 * 婚纱服务接口
 * @ClassName:   IHunshaService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author:      guilige 
 * @date         2014-1-13 下午4:11:27 
 */
public interface IHunshaZhaoService {
	
	
	/**
	 *查询某个婚纱照集的婚纱照
	 * @return
	 */
	public HunshaZhao selectHunshaZhao(Integer id) throws Exception;
	
	/**
	 *查询婚纱照相片集数量
	 * @return
	 */
	public Integer selectHunshaZhaoCount(HunShaPageParam hunShaPageParam) ;
	
	/**
	 * 条件查询婚纱照
	 * @param start
	 * @param pageSize
	 * @param hunShaPageParam
	 * @return
	 */
	public List<HunshaZhao> selectHunshaZhaoPage(int start, int pageSize,
			HunShaPageParam hunShaPageParam);
	
	/**
	 *保存婚纱公司
	 * @return
	 */
	public Integer saveHunshaZhao(HunshaZhao HunshaZhao) throws Exception;
	
}
