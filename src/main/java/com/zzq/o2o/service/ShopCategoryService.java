package com.zzq.o2o.service;

import java.util.List;

import com.zzq.o2o.entity.ShopCategory;

public interface ShopCategoryService {
	
	public static final String SCLISTKEY = "shopcategorylist";
	/**
	 * 根据查询条件获取商铺类别列表
	 * @param shopCategory
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
