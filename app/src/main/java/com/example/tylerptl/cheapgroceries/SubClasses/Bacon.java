package com.example.tylerptl.cheapgroceries.SubClasses;


import java.util.Collections;
import java.util.HashMap;


/**
 * Created by Tyler on 5/3/2018.
 */

public class Bacon {
    private HashMap<String, Double> list = new HashMap<String, Double>();
    private Double low; // lower end of price
    private Double high;    // High end of price
    private Double range;
    private Double cheapestPrice;
    private String cheapestName;

    public Bacon() {
        low = 3.00;
        high = 8.00;
        range = (high-low);
        populateInventory();

    }

    public Double randomVal(){
        return (Math.random() * range) + (low <= high ? low : high);
    }

    public void populateInventory(){
        list.put("Oscar Mayer", randomVal());
        list.put("Wright Brand", randomVal());
        list.put("Hormel", randomVal());
        list.put("Smithfield", randomVal());
        list.put("Farmland Foods", randomVal());
        list.put("Black Label", randomVal());
        cheapestPrice = (Double) Collections.min(list.values());

    }

    public Double getCheapestPrice(){
      return cheapestPrice;
    }

    public String getCheapestName(){
        cheapestName="";
        Double lowestNum = Double.MAX_VALUE;
        for(String key : list.keySet()){
            Double valueTest = list.get(key);
            if(valueTest < lowestNum){
                cheapestName = key;

           }
       }
       return cheapestName;
    }

}
