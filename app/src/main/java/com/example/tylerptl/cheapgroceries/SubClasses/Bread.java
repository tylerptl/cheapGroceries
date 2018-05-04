package com.example.tylerptl.cheapgroceries.SubClasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tyler on 5/3/2018.
 */

public class Bread {
    private HashMap<String, Double> list = new HashMap<String, Double>();
    private Double low; // lower end of price
    private Double high;    // High end of price
    private Double range;
    private Double cheapestPrice;
    private String cheapestName;


    public Bread() {
        low = 2.50;
        high = 5.50;
        range = high - low;
    }

    public Double randomVal(){
        return (Math.random() * range) + (low <= high ? low : high);
    }

    public void populateInventory(){
        list.put("Wonder Bread", randomVal());
        list.put("Nature's Own", randomVal());
        list.put("Martin's Famous", randomVal());
        list.put("Bond Bread", randomVal());
        list.put("Warburtons", randomVal());
        list.put("Pepperidge Farm", randomVal());
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
