package com.zzq.o2o.dao;

import com.zzq.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 通过shopId查询店铺
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);
	
	/**
	 * 更新店鋪信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
