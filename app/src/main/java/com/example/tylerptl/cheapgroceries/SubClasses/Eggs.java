package com.example.tylerptl.cheapgroceries.SubClasses;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Tyler on 5/3/2018.
 */

public class Eggs {
    private HashMap<String, Double> list = new HashMap<String, Double>();
    private Double low; // lower end of price
    private Double high;    // High end of price
    private Double range;
    private Double cheapestPrice;
    private String cheapestName;
    public Eggs() {
        low = 1.22;
        high = 5.50;
        range = high - low;
    }
    public Double randomVal(){
        return (Math.random() * range) + (low <= high ? low : high);
    }

    public void populateInventory(){
        list.put("Hill Country", randomVal());
        list.put("Sunups", randomVal());
        list.put("Eggland", randomVal());
        list.put("Farmhouse", randomVal());
        list.put("NestFresh", randomVal());
        list.put("Central Market", randomVal());
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
