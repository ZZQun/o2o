package com.zzq.o2o.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.coyote.http11.filters.VoidInputFilter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zzq.o2o.BaseTest;
import com.zzq.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	
	@Test
	public void testGetAreaList() {
		List<Area> areaList = areaService.getAreaList();
		assertEquals(2, areaList.size());
	}
}
