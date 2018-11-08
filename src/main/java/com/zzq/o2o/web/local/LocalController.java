package com.zzq.o2o.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/local")
public class LocalController {

	@RequestMapping(value="/accountbind",method=RequestMethod.GET)
	public String shopOperation() {
		return "local/accountbind";
	}
}
