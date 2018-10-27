package com.zzq.o2o.service;



import org.springframework.web.multipart.commons.CommonsMultipartFile;


import com.zzq.o2o.dto.ShopExecution;
import com.zzq.o2o.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop,CommonsMultipartFile shopImg); 
}
