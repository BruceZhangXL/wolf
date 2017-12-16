package org.wolf.service.impl;

import javax.annotation.Resource;

import org.lion.domain.HelloDomain;
import org.lion.export.HelloService;
import org.springframework.stereotype.Service;
import org.wolf.dao.ItemDraftMapper;
import org.wolf.service.ItemService;


@Service("itemService")
public class ItemServiceImpl implements ItemService{
	@Resource
	private ItemDraftMapper itemDraftMapper;
	
	@Resource
	private HelloService helloService;
	
	@Override
	public String queryItemDraftNameByItemDraftId(Long itemDraftId) {
		return itemDraftMapper.selectByPrimaryKey(itemDraftId);
	}

	@Override
	public HelloDomain sayHi(String username) {
		return helloService.sayHello(username);
	}

}
