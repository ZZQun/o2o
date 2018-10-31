package com.zzq.o2o.dao;

import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.Product;
import com.zzq.o2o.entity.ProductCategory;
import com.zzq.o2o.entity.Shop;

//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;
	
	@Test
	@Ignore
	public void testAInsertProduct() throws Exception{
		Shop shop2 = new Shop();
		shop2.setShopId(2L);
		ProductCategory pc14 = new ProductCategory();
		pc14.setProductCategoryId(14L);
		Product product1 = new Product();
		product1.setProductName("测试2");
		product1.setProductDesc("测试2描述");
		product1.setImgAddr("test2");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop2);
		product1.setProductCategory(pc14);
		int effectedNum1 = productDao.insertProduct(product1);
		System.out.println("测试2商品新增影响行数：" + effectedNum1);
	}
	
	@Test
	@Ignore
	public void testCQueryProductByProductId() throws Exception{
		productDao.queryProductById(2);
	}
	
	@Test
	@Ignore
	public void testDUpdateProduct() throws Exception{
		Product product = new Product();
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(13L);
		pc.setProductCategoryId(14L);
		product.setProductId(6L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("你好");
		int effectedNum = productDao.updateProduct(product);
		System.out.println("更新影响的行数：" + effectedNum);
	}
	
	@Test
	@Ignore
	public void testBQueryProductList() throws Exception{
		Product productCondition = new Product();
		List<Product> productList = productDao.queryProductList(productCondition, 0, 4);
		System.out.println("查询数据返回行数：" + productList.size());
		int count = productDao.queryProductCount(productCondition);
		System.out.println("查询商品总数：" + count);
		productCondition.setProductName("测试");
		productList = productDao.queryProductList(productCondition, 0, 6);
		System.out.println("查询商品名为测试返回行数：" + productList.size());
		count = productDao.queryProductCount(productCondition);
		System.out.println("查询商品名为测试返回行数：" + count);
	}
	
	@Test
	public void testEUpdateProductCategoryToNull() {
		int effectedNum = productDao.updateProductCategoryToNull(19L);
		System.out.println("影响行数：" + effectedNum);
	}
}
