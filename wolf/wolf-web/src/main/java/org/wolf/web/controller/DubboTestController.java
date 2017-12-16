package org.wolf.web.controller;

import javax.annotation.Resource;

import org.lion.domain.HelloDomain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wolf.service.ItemService;

@Controller
@RequestMapping("dubbo")
public class DubboTestController {
	@Resource
	private ItemService itemService;
	
	@RequestMapping("")
	@ResponseBody
	public HelloDomain sayHi(String name){
		return itemService.sayHi(name);
	}
}
