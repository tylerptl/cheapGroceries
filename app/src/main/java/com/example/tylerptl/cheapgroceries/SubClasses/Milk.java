package com.example.tylerptl.cheapgroceries.SubClasses;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Tyler on 5/3/2018.
 */

public class Milk {
    private HashMap<String, Double> list = new HashMap<String, Double>();
    private Double low; // lower end of price
    private Double high;    // High end of price
    private Double range;
    private Double cheapestPrice;
    private String cheapestName;
    public Milk() {
        low = 2.25;
        high = 4.25;
        range = high - low;
    }
    public Double randomVal(){
        return (Math.random() * range) + (low <= high ? low : high);
    }

    public void populateInventory(){
        list.put("Hill Country", randomVal());
        list.put("100 Grand", randomVal());
        list.put("8th Continent", randomVal());
        list.put("Almond Joy", randomVal());
        list.put("H-E-B", randomVal());
        list.put("Alprose", randomVal());
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
