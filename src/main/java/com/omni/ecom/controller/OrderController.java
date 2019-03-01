package com.omni.ecom.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omni.ecom.HibernateUtil;
import com.omni.ecom.model.Item;
import com.omni.ecom.model.Order;
import com.omni.ecom.model.User;
import com.omni.ecom.repository.ItemRepository;
import com.omni.ecom.repository.UserRepository;

import ch.qos.logback.classic.Logger;

@Controller
public class OrderController {
	@Autowired
	UserRepository urepo;
	
	@Autowired
	ItemRepository irepo;
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public ResponseEntity<Object> getOrders() {
		
		List<User> r = urepo.findAll();
		Hibernate.initialize(r);
		Iterator<User> ri = r.iterator();
		while(ri.hasNext())
		{
			User i = ri.next();
			Iterator<Order> it = i.getOrders().iterator();
			while(it.hasNext())
			{
				Order o = it.next();
				o.getItem().setOrders(null);
				o.setUser(null);
			}
		}
		
		return new ResponseEntity<>(r, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<Object> addOrder(@RequestBody Order order) {
		
		Long itemId = order.getItem().getId();
		Long userId = order.getUser().getId();
		int quantity = order.getQuantity();
		Optional<Item> ri = irepo.findById(itemId);
		Optional<User> ru = urepo.findById(userId);
		if(!ri.isPresent())
		{
			return new ResponseEntity<>("no Item", HttpStatus.OK);
		}
		Item i = ri.get();
		int iq = i.getQuantity();
		if(iq==-1)
		{
			return new ResponseEntity<>("out of stock", HttpStatus.OK);
		}
		else if(iq==-2)
		{
			return new ResponseEntity<>("no Item", HttpStatus.OK);
		}
		else if(iq<quantity)
		{
			return new ResponseEntity<>("less Items", HttpStatus.OK);
		}
		if(!ru.isPresent())
		{
			return new ResponseEntity<>("no User", HttpStatus.OK);
		}
		i.setQuantity(iq-quantity);
		if(!ru.isPresent())
		{
			User u = new User(order.getUser().getName(),new Order(i,quantity));
			return new ResponseEntity<>("Successfully ordered", HttpStatus.OK);
		}
		
		return new ResponseEntity<>("Successfully ordered", HttpStatus.OK);
	}

}
