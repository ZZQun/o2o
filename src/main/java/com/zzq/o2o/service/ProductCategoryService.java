package com.zzq.o2o.service;

import java.util.List;

import com.zzq.o2o.entity.ProductCategory;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下所有商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
}
