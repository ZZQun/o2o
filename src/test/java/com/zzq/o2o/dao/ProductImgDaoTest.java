package com.zzq.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.ProductImg;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest extends BaseTest{
	
	@Autowired
	private ProductImgDao productImgDao;
	
	@Test
	@Ignore
	public void testABatchInsertProductImg() throws Exception{
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片1");
		productImg1.setImgDesc("图片1描述");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("图片2");
		productImg2.setImgDesc("图片2描述");
		productImg2.setPriority(2);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		System.out.println("新增影响行数：" + effectedNum);
		
	}
	
	@Test
	@Ignore
	public void testCDelteProductImgByProductId() throws Exception{
		long productId = 1;
		int effectedNum = productImgDao.deleteProductImgByProductId(productId);
		System.out.println("删除影响的行数：" + effectedNum);
	}
	
	@Test
	public void testBQueryProductImgListByProductId() throws Exception{
		long productId = 4;
		int effectedNum = productImgDao.queryProductImgList(productId).size();
		System.out.println("查询出来的行数：" + effectedNum);
	}
}
