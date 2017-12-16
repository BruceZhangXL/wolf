package org.wolf.service;

import org.lion.domain.HelloDomain;

public interface ItemService {
	String queryItemDraftNameByItemDraftId(Long itemDraftId);
	
	HelloDomain sayHi(String username);
}
