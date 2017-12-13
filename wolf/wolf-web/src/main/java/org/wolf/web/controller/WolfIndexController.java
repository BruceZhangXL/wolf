package org.wolf.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("index")
public class WolfIndexController {
	@Resource
	private RedisTemplate redisTemplate;
	
	/**
	 * wolf 首页
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("")
	public String wolfIndexForHandle(HttpServletRequest request,Model model){
		return "wolfIndex";
	}
	
	@RequestMapping("fetchData")
	@ResponseBody
	public String fetchDataFromRedisForHandle(String name){
		return "key:"+name+",value:"+redisTemplate.opsForValue().get(name);
	}
	
	
}
