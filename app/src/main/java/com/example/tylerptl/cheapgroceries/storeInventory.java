package com.example.tylerptl.cheapgroceries;
import com.example.tylerptl.cheapgroceries.SubClasses.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Tyler on 5/3/2018.
 * Due to other methods, which pull down values from the source code of the actual site returning
 * random values, this class will store a fake inventory which will allow consistent price returns.
 *
 */

public class storeInventory {
    private Bacon bacon = new Bacon();
    private Bread bread = new Bread();
    private Coffee coffee = new Coffee();
    private Eggs eggs = new Eggs();
    private Milk milk = new Milk();
    private Rice rice = new Rice();
    private Oats oats = new Oats();
    private static ArrayList<String> itemTypes = new ArrayList<>();
    ArrayList<String> list = new ArrayList<>();

    public static ArrayList<String> getItemTypes() { return itemTypes; }

    public HashMap<String, Double> getShoppingCart() {
        return shoppingCart;
    }

    HashMap<String, Double> shoppingCart;

    public Double getTotalPrice() {
        return totalPrice;
    }

    Double totalPrice = 0.00;

    /**
     * This constructor will allow the app to check if the search term inputted by the user
     * is carried by the stores.+
     */
    storeInventory(){
        Collections.addAll(itemTypes, "bacon","bread","coffee","eggs","milk","rice","oats");

    }

    storeInventory(ArrayList list){
        bacon.populateInventory();
        bread.populateInventory();
        coffee.populateInventory();
        eggs.populateInventory();
        milk.populateInventory();
        rice.populateInventory();
        oats.populateInventory();
        this.list = list;
        shoppingCart = new HashMap<>();
       // Collections.addAll(itemTypes, "bacon","bread","coffee","eggs","milk","rice","oats");
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

