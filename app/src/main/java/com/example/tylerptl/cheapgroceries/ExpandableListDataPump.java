package com.example.tylerptl.cheapgroceries;

/**
 * Created by Tyler on 5/4/2018.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ExpandableListDataPump {
    HashMap<String, Double> walmartCart, samsCart, hebCart;


    ExpandableListDataPump(HashMap<String, Double> walmartCart, HashMap<String, Double> samsCart, HashMap<String, Double> hebCart){
        this.walmartCart = walmartCart;
        this.samsCart = samsCart;
        this.hebCart = hebCart;
    }
    public HashMap<String, HashMap<String, Double>> getData() {
        HashMap<String, HashMap<String, Double>> expandableListDetail = new HashMap<String, HashMap<String, Double>>();

        HashMap<String, Double> walmartIterator = new HashMap<>();
        Iterator it = walmartCart.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            walmartIterator.put((String)pair.getKey(), (Double) pair.getValue());
        }


//        walmartCart.put("India");
//        walmartCart.put("Pakistan");
//        walmartCart.put("Australia");
//        walmartCart.put("England");
//        walmartCart.put("South Africa");

        HashMap<String, Double> samsIterator = new HashMap<>();

        it = samsCart.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            samsIterator.put((String)pair.getKey(), (Double) pair.getValue());
        }

        HashMap<String, Double> hebIterator = new HashMap<>();
        it = hebCart.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            hebIterator.put((String)pair.getKey(), (Double) pair.getValue());
        }

        expandableListDetail.put("Walmart", walmartIterator);
        expandableListDetail.put("Sam's Club", samsIterator);
        expandableListDetail.put("HEB", hebIterator);
        return expandableListDetail;
    }
}