package com.zzq.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.HeadLine;

public class HeadLineDaoTest extends BaseTest{
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	public void testQueryArea() {
		List<HeadLine> headLineList = headLineDao.queryHeadLine(new HeadLine());
		System.out.println("查询返回行数：" + headLineList.size());
	}
}
