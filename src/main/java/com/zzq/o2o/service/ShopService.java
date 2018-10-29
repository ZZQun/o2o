package com.zzq.o2o.service;



import java.io.InputStream;



import com.zzq.o2o.dto.ShopExecution;
import com.zzq.o2o.entity.Shop;
import com.zzq.o2o.exception.ShopOperationException;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回列表数据
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	/**
	 * 通过店铺id获取店铺信息
	 * @param shopId
	 * @return
	 */
	public Shop getByShopId(long shopId);
	/**
	 * 更新点店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	public ShopExecution modifyShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException;
	/**
	 * 注册店铺信息，包括图片处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	public ShopExecution addShop(Shop shop,InputStream shopImgInputStream,String fileName) throws ShopOperationException; 
}
