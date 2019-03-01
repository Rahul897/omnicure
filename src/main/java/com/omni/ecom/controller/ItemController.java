package com.omni.ecom.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omni.ecom.model.Item;
import com.omni.ecom.repository.ItemRepository;

@Controller
public class ItemController {
	
	@Autowired
	ItemRepository irepo;
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getItem(@PathVariable("id") Long id) {
		
		Optional<Item> r = irepo.findById(id);
		if(!r.isPresent())
		{
			return new ResponseEntity<>("no Item", HttpStatus.OK);
		}
		Item i = r.get();
		i.setOrders(null);
		
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllItem() {
		
		List<Item> i = irepo.findAll();
		Iterator<Item> it = i.iterator();
		while(it.hasNext())
		{
			Item item = it.next();
			item.setOrders(null);
			if(item.getQuantity()==-2)
				it.remove();
		}
				
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public ResponseEntity<Object> 
	      updateItem(@RequestBody Item item) {
		Long id = item.getId();
		Optional<Item> r = irepo.findById(id);
		if(!r.isPresent())
		{
			irepo.save(item);
			return new ResponseEntity<>("new User added", HttpStatus.OK);
		}
		Item i = r.get();
		i.setName(item.getName());
		i.setQuantity(item.getQuantity());
		
		irepo.save(i);
		
		return new ResponseEntity<>("Item is updated successsfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> 
	      deleteItem(@PathVariable("id") Long id) {
		Optional<Item> r = irepo.findById(id);
		if(!r.isPresent())
			return new ResponseEntity<>("no Item", HttpStatus.OK);
		
		Item i = r.get();
		i.setQuantity(-2);
		
		irepo.save(i);
		
		return new ResponseEntity<>("Item is deleted successsfully", HttpStatus.OK);
	}

}
