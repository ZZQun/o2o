package com.zzq.o2o.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.HeadLine;

public class HeadLineServiceTest extends BaseTest{

	@Autowired
	private HeadLineService headLineService;
	
	@Test
	public void testAGetHeadLineList() throws IOException {
		HeadLine headLineCondition = new HeadLine();
		headLineCondition.setEnableStatus(1);
		List<HeadLine> headLineList = headLineService.getHeadLineList(headLineCondition);
		System.out.println("查询返回行数：" + headLineList.size());
	}
}
