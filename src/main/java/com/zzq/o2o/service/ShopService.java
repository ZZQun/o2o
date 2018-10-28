package com.zzq.o2o.service;



import java.io.InputStream;



import com.zzq.o2o.dto.ShopExecution;
import com.zzq.o2o.entity.Shop;
import com.zzq.o2o.exception.ShopOperationException;

public interface ShopService {
	/**
	 * 通过店铺id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	/**
	 * 更新点店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;
	/**
	 * 注册店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException; 
}
