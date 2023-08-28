package com.mongo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

	public interface ItemRepository extends MongoRepository<Item, String> {
		@Query("{name:'?0'}")
	    Item findItemByName(String name,String title);
	    
	    @Query(value="{category:'?0'}", fields="{'name' : 0,'title:0'}")
	    List<Item> findAll(String category);
	    
	    public long count();

}
