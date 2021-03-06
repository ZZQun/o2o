package com.zzq.o2o.service;

import java.util.List;

import com.zzq.o2o.dto.ProductCategoryExecution;
import com.zzq.o2o.entity.ProductCategory;
import com.zzq.o2o.exception.ProductCategoryException;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下所有商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	
	/**
	 * 批量新增商品类别
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
		throws ProductCategoryException;
	
	/**
	 * 将此类别下的商品里的类别id置为空，再删除掉商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
		throws ProductCategoryException;
}
