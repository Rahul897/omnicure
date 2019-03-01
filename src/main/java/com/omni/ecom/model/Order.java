package com.omni.ecom.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_table")
public class Order implements Serializable{
	@Id
    @ManyToOne
    @JoinColumn
    private User user;

    @Id
    @ManyToOne
    @JoinColumn
    private Item item;
    
    private Integer quantity;
    
    public Order()
    {
    	super();
    }
    
    public Order(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }
    
    public Order(Item item,User user, Integer quantity) {
        this.item = item;
        this.user = user;
        this.quantity = quantity;
    }
    
    
    public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Item getItem() {
		return item;
	}


	public void setItem(Item item) {
		this.item = item;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order that = (Order) o;
        return Objects.equals(user.getName(), that.user.getName()) &&
                Objects.equals(item.getName(), that.item.getName()) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getName(), item.getName(), quantity);
    }

}
