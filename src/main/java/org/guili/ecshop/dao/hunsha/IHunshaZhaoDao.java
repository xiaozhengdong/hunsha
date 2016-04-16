package org.guili.ecshop.dao.hunsha;
import java.util.List;

import org.guili.ecshop.bean.hunsha.HunShaPageParam;
import org.guili.ecshop.bean.hunsha.HunshaZhao;

/**
 * 婚纱公司dao
 * @author guili
 */
public interface IHunshaZhaoDao {
	
	/**
	 *查询婚纱公司
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
