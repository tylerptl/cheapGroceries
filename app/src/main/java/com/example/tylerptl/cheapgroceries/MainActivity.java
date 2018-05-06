package com.example.tylerptl.cheapgroceries;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    EditText textInput;
    Button butSave;
    ArrayList<String> list;
    Button calc;
    TextView walmartPrice, samsPrice, hebPrice;
    DecimalFormat df;
    storeInventory samsInventory, walmartInventory, hebInventory;
    ArrayList<String> availableItems;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        textInput = (EditText) findViewById(R.id.userInput);
        butSave = (Button) findViewById(R.id.butSave);
        calc = (Button) findViewById(R.id.butCalc);
        walmartPrice = (TextView) findViewById(R.id.walmartPrice);
        samsPrice = (TextView) findViewById(R.id.samsPrice);
        hebPrice = (TextView) findViewById(R.id.hebPrice);
        df = new DecimalFormat("#.##");
        availableItems = new ArrayList<>();
        availableItems =storeInventory.getItemTypes();
        
        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSearch();

            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateCarts();
            }
        });

    }

    public void saveSearch(){
        String str = textInput.getText().toString();
        if(!availableItems.contains(str)){
            textInput.setError("No stores carry this item type - re enter input");
            return;
        }
        list.add(str);
        textInput.getText().clear();
        textInput.setText(null);
    }

    public void generateCarts(){
        samsPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.defaultTextColor));
        walmartPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.defaultTextColor));
        hebPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.defaultTextColor));

        samsInventory = new storeInventory(list);
        samsInventory.searchInventory();
        Double samsTotal = samsInventory.getTotalPrice();
        samsTotal = Double.valueOf(df.format(samsTotal));
        samsPrice.setText(samsTotal.toString());

        walmartInventory = new storeInventory(list);
        walmartInventory.searchInventory();
        Double walmartTotal = walmartInventory.getTotalPrice();
        walmartTotal = Double.valueOf(df.format(walmartTotal));
        walmartPrice.setText(walmartTotal.toString());

        hebInventory = new storeInventory(list);
        hebInventory.searchInventory();
        Double hebTotal = hebInventory.getTotalPrice();
        hebTotal = Double.valueOf(df.format(hebTotal));
        hebPrice.setText(hebTotal.toString());

        if(samsTotal <= walmartTotal && samsTotal <= hebTotal){
            samsPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.cheapestCart));
        }else if(walmartTotal <= samsTotal && walmartTotal <= hebTotal){
            walmartPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.cheapestCart));
        }else{
            hebPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.cheapestCart));
        }
     }

}




