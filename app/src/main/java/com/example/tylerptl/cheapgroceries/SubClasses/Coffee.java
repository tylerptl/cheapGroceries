package com.example.tylerptl.cheapgroceries.SubClasses;

import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Tyler on 5/3/2018.
 */

public class Coffee {
    private HashMap<String, Double> list = new HashMap<String, Double>();
    private Double low; // lower end of price
    private Double high;    // High end of price
    private Double range;
    private Double cheapestPrice;
    private String cheapestName;
    public Coffee() {
        low = 2.25;
        high = 7.50;
        range = high - low;
    }
    public Double randomVal(){
        return (Math.random() * range) + (low <= high ? low : high);
    }

    public void populateInventory(){
        list.put("Maxwell House", randomVal());
        list.put("Folgers", randomVal());
        list.put("Counter Culture", randomVal());
        list.put("La Colombe", randomVal());
        list.put("Evans Brothers", randomVal());
        list.put("Intelligentsia Black Cat", randomVal());
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
