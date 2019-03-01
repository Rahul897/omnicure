package com.omni.ecom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.omni.ecom.model.Item;
import com.omni.ecom.model.Order;
import com.omni.ecom.model.User;
import com.omni.ecom.repository.ItemRepository;
import com.omni.ecom.repository.UserRepository;

@SpringBootApplication
public class EcomApplication implements CommandLineRunner{
	@Autowired
	ItemRepository irepo;
	
	@Autowired
	UserRepository urepo;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(EcomApplication.class, args);
		
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Item i1 = new Item("galaxy",20);
		Item i2 = new Item("iphone",30);
		
		irepo.saveAll(Arrays.asList(i1,i2));
		
 		User u1 = new User("rahul",new Order(i1,10));
 		urepo.save(u1);
 		
// 		irepo.deleteById(1L);
 		
		User u2 = new User("anu",new Order(i2,10));
		urepo.save(u2);
		
	}

}
