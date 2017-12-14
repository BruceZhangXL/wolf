package org.wolf.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.wolf.service.ItemService;
import org.wolf.service.TestApplication;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)  
public class ItemServiceTest {
	@Resource
	private ItemService itemService;
	
	@Test
	public void testQueryItemDraftNameByItemDraftId(){
		String name=itemService.queryItemDraftNameByItemDraftId(225763L);
		System.out.println(name);
	}
}
