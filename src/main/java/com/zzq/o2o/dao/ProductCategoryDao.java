package com.zzq.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzq.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 通过shop id查询店铺商品类别
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> queryProductCategory(long shopId);
	
	/**
	 * 批量新增商品类别
	 * @param productCategoryList
	 * @return
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	/**
	 * 删除指定商品类别
	 * @param productCategoryId
	 * @return
	 */
	//传入两个参数，MyBatis配置文档需要@Param注解才可识别出
	int deleteProductCategory(@Param("productCategoryId")long productCategoryId,@Param("shopId")long shopId);
}
