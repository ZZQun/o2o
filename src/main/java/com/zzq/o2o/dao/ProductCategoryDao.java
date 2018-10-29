package com.zzq.o2o.dao;

import java.util.List;

import com.zzq.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 通过shop id查询店铺商品类别
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategory(long shopId);
}
