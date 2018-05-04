package com.example.tylerptl.cheapgroceries;
import com.example.tylerptl.cheapgroceries.SubClasses.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tyler on 5/3/2018.
 * Due to other methods, which pull down values from the source code of the actual site returning
 * random values, this class will store a fake inventory which will allow consistent price returns.
 *
 */

public class samsInventory {
    Bacon bacon = new Bacon();
    Bread bread = new Bread();
    Coffee coffee = new Coffee();
    Eggs eggs = new Eggs();
    Milk milk = new Milk();
    Rice rice = new Rice();
    Oats oats = new Oats();
    ArrayList<String> list;

    public HashMap<String, Double> getShoppingCart() {
        return shoppingCart;
    }

    HashMap<String, Double> shoppingCart;

    public Double getTotalPrice() {
        return totalPrice;
    }

    Double totalPrice = 0.00;



    samsInventory(ArrayList list){
        bacon.populateInventory();
        bread.populateInventory();
        coffee.populateInventory();
        eggs.populateInventory();
        milk.populateInventory();
        rice.populateInventory();
        oats.populateInventory();
        this.list = list;
        shoppingCart = new HashMap<>();

    }

    public void searchInventory(){
        double cheapestPrice;
        for(String str : list){
            if(str.toLowerCase().equals("bacon")){
                cheapestPrice = bacon.getCheapestPrice();
                shoppingCart.put(bacon.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
            if(str.toLowerCase().equals("bread")){
                cheapestPrice = bread.getCheapestPrice();
                shoppingCart.put(bread.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
            if(str.toLowerCase().equals("coffee")){
                cheapestPrice = coffee.getCheapestPrice();
                shoppingCart.put(coffee.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
            if(str.toLowerCase().equals("eggs")){
                cheapestPrice = eggs.getCheapestPrice();
                shoppingCart.put(eggs.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
            if(str.toLowerCase().equals("milk")){
                cheapestPrice = milk.getCheapestPrice();
                shoppingCart.put(milk.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
            if(str.toLowerCase().equals("oats")){
                cheapestPrice = oats.getCheapestPrice();
                shoppingCart.put(oats.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
            if(str.toLowerCase().equals("rice")){
                cheapestPrice = rice.getCheapestPrice();
                shoppingCart.put(rice.getCheapestName(), cheapestPrice);
                totalPrice += cheapestPrice;
            }
        }
    }



}

