package com.omni.ecom.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.omni.ecom.model.Order;
import com.omni.ecom.model.User;
import com.omni.ecom.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository urepo;
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
		
		Optional<User> r = urepo.findById(id);
		if(!r.isPresent())
		{
			return new ResponseEntity<>("no User", HttpStatus.OK);
		}
		User i = r.get();
		Hibernate.initialize(i);
		Iterator<Order> it = i.getOrders().iterator();
		while(it.hasNext())
		{
			Order o = it.next();
			o.getItem().setOrders(null);
			o.setUser(null);
		}
		
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllUser() {
		
		List<User> i = urepo.findAll();
		Iterator<User> it = i.iterator();
		while(it.hasNext())
		{
			it.next().setOrders(null);
		}
				
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> 
	      updateUser(@RequestBody User user) {
		Long id = user.getId();
		Optional<User> r = urepo.findById(id);
		if(!r.isPresent())
		{
			urepo.save(user);
			return new ResponseEntity<>("new User added", HttpStatus.OK);
		}
		User i = r.get();
		i.setName(user.getName());
		
		urepo.save(i);
		
		return new ResponseEntity<>("User is updated successsfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> 
	      deleteUser(@PathVariable("id") Long id) {
		Optional<User> r = urepo.findById(id);
		if(!r.isPresent())
			return new ResponseEntity<>("no User", HttpStatus.OK);
		
		User i = r.get();
		
		urepo.delete(i);
		
		return new ResponseEntity<>("User is deleted successsfully", HttpStatus.OK);
	}

}
