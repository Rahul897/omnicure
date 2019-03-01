# Omnicuris Assignment(https://omincure.herokuapp.com/item)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://omincure.herokuapp.com/item)

# Ecommerce Backend api deployed on Heroku

  - CRUD items
  - CRUD users
  - create and add orders

# /item requests

  - https://omincure.herokuapp.com/item/1 get request returns item with id 1
  - https://omincure.herokuapp.com/item get request returns all items
  - https://omincure.herokuapp.com/item post request with {"id":5,"name":"iphone","quantity":20,"orders":null} creates/updates item if ID exists/doesn't exist
  - https://omincure.herokuapp.com/item/1 delete request deletes item with id 1

# /user requests

  - https://omincure.herokuapp.com/user/3 get request returns item with id 3
  - https://omincure.herokuapp.com/user get request returns all items
  - https://omincure.herokuapp.com/user post request with {"id":4,"name":"raj","orders":null} creates/updates item if ID exists/doesn't exist
  - https://omincure.herokuapp.com/user/3 delete request deletes item with id 3
 
# /order requests

  - https://omincure.herokuapp.com/orders get request returns all orders of all users
  - https://omincure.herokuapp.com/order post request creates order with 
    {    "user":{"id":4,"name":"mad","orders":null},
    "item":{"id":1,"name":"galaxy","quantity":20,"orders":null},
    "quantity":10    } 
- json desciption    
    {    user object,
    item object,
    order quantity    }
 - only if
    - item and user exists
    - item quantity > order quantity
    - item is in stock (item quantity!=-1 for convienience)
    - item is not deleted (item quantity!=-2 for convienience)

# frameworks used
 - Spring boot
 - hibernate(JPA)
 - h2 database(for portability)
    
