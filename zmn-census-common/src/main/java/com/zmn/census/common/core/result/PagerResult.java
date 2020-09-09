package com.zmn.census.common.core.result;


import com.zmn.census.common.utils.object.VoAndBeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Bootstrap Table返回值
 * @author zzr
 *
 */
public class PagerResult<T> implements Serializable {

	private static final long serialVersionUID = 3141067807832984876L;


	private List<T> data;

	/**总条数*/
	private long total;



	public PagerResult(){
	}

	public PagerResult(List<T> data, long total) {
		this.data = data;
		this.total = total;
	}


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public static <T> PagerResult<T> toVOPagerResult(PagerResult page, Class<T> clazz){
		PagerResult<T> pagerVoResult = new PagerResult<T>();
		pagerVoResult.setTotal(page.getTotal());
		pagerVoResult.setData(VoAndBeanUtils.toVOList(page.getData(),clazz));
		return pagerVoResult;
	}
}

