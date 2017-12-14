package org.wolf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.wolf.dao.ItemDraftMapper;
import org.wolf.service.ItemService;


@Service("itemService")
public class ItemServiceImpl implements ItemService{
	@Resource
	private ItemDraftMapper itemDraftMapper;
	
	@Override
	public String queryItemDraftNameByItemDraftId(Long itemDraftId) {
		return itemDraftMapper.selectByPrimaryKey(itemDraftId);
	}

}
