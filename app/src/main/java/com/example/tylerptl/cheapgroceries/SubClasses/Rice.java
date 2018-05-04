package com.example.tylerptl.cheapgroceries.SubClasses;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Tyler on 5/3/2018.
 */

public class Rice {
    private HashMap<String, Double> list = new HashMap<String, Double>();
    private Double low; // lower end of price
    private Double high;    // High end of price
    private Double range;
    private Double cheapestPrice;
    private String cheapestName;

    public Rice() {
        low = 4.50;
        high = 11.75;
        range = high - low;
    }
    public Double randomVal(){
        return (Math.random() * range) + (low <= high ? low : high);
    }

    public void populateInventory(){
        list.put("Dawat", randomVal());
        list.put("Lal Qilla", randomVal());
        list.put("Hanuman Basmati", randomVal());
        list.put("Kohinoor Foods Limited", randomVal());
        list.put("Amira Basmati", randomVal());
        list.put("Tilda ", randomVal());
    }

    public Double getCheapestPrice(){
        cheapestPrice = (Double) Collections.min(list.values());
        return cheapestPrice;
    }

    public String getCheapestName(){
        String lowestKey="";
        Double lowestNum = Double.MAX_VALUE;
        for(String key : list.keySet()){
            Double valueTest = list.get(key);
            if(valueTest < lowestNum){
                lowestKey = key;

            }
        }
        return lowestKey;
    }
}
