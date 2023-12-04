/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.Shopping;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Cart {

    private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> Cart) {
        this.cart = Cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> Cart) {
        this.cart = Cart;
    }

    public boolean add(Product product) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(product.getId())) {
                int quantity = this.cart.get(product.getId()).getQuantity() + product.getQuantity();
                product.setQuantity(quantity);
            }
            this.cart.put(product.getId(), product);
            check = true;
        } catch (Exception e) {
        }
        return check;
    }

    public boolean remove(String id) {
        boolean check = false;
        try {
            if (this.cart != null) {
                if (this.cart.containsKey(id)) {
                    this.cart.remove(id);
                    check = true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }

    public boolean edit(String id, Product laptop) {
        boolean check = false;
        try {
            if (this.cart != null){
                if (this.cart.containsKey(id)){
                    this.cart.replace(id, laptop);
                    check=true;
                }
            }
        } catch (Exception e) {
        }
        return check;
    }
}
