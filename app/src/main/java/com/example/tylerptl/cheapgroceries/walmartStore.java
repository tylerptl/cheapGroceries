package com.example.tylerptl.cheapgroceries;

import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class walmartStore implements Serializable {
    private HashMap<String, Double> shoppingCart;
    private Double totalCost = 0.00;
    private ArrayList<String> list;
    private Double price;

    public Double getPrice() {
        return price;
    }


    public HashMap<String, Double> getShoppingCart() {
        return shoppingCart;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    walmartStore(ArrayList<String> list){
        this.list = list;

    }

    public void createCart() throws IOException {

        String walmartUrl ="https://www.walmart.com/search/?query=";
        String walmartEndTag = "&cat_id=0";
        shoppingCart = new HashMap();
        String url;
        StringBuilder urlCreate = new StringBuilder();
        BufferedWriter writer;



        // Iterate through each item in the list and add to shopping cart
        for(String str : list) {

            urlCreate.append(walmartUrl).append(str).append(walmartEndTag);
            url = urlCreate.toString();
            url = url.replaceAll(" ", "%20");
            price = getPrice(url);
            totalCost += price;

            //Create HTML file
        }
    }
    public Double getPrice(String webSite){
        String str = "";
        String s="";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpClient httpCLient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(webSite);
        try{


            HttpResponse response;
            response = httpCLient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "windows-1251"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }
            str = sb.toString();

            //Search string for price
            s = str.substring(str.indexOf("offerPrice\":"+1));
            s = s.substring(s.indexOf(":")+1);
            s = s.substring(0, s.indexOf(","));
            price = Double.parseDouble(s);

            // Write to file
            //writeFileOnInternalStorage(MainActivity.this, "test.txt", str);

        } catch (IOException e){
            e.printStackTrace();
           // Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }

        return price;
    }
}
