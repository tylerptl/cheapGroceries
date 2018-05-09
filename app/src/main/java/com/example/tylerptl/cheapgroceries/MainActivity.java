package com.example.tylerptl.cheapgroceries;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    EditText textInput;
    Button butSave, butClear;
    ArrayList<String> list; // This ArrayList will store all the search terms inputted by the user
    Button calc;    // This button will run the methods pertaining to gathering prices and displaying them
    TextView walmartPrice, samsPrice, hebPrice, walmartName, samsName, hebName;
    DecimalFormat df;
    storeInventory samsInventory, walmartInventory, hebInventory;
    ArrayList<String> availableItems;   // This will be used to verify if the store has that type of foodstuff
    Spinner spinTest;
    String[] cartText;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        textInput = (EditText) findViewById(R.id.userInput);
        butSave = (Button) findViewById(R.id.butSave);
        butClear = (Button) findViewById(R.id.butClear);
        calc = (Button) findViewById(R.id.butCalc);
        walmartPrice = (TextView) findViewById(R.id.walmartPrice);
        samsPrice = (TextView) findViewById(R.id.samsPrice);
        hebPrice = (TextView) findViewById(R.id.hebPrice);
        walmartName = (TextView) findViewById(R.id.walmartName);
        samsName = (TextView) findViewById(R.id.samsName);
        hebName = (TextView) findViewById(R.id.hebName);
        df = new DecimalFormat("#.##");
        df.setMinimumFractionDigits(2);

        storeInventory baseStore = new storeInventory();
        availableItems = new ArrayList<>();
        availableItems = baseStore.getItemTypes();

        spinTest = (Spinner) findViewById(R.id.spinner1);
        cartText = new String[1];
        cartText[0] = "Cart";

        walmartName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!list.isEmpty()) {
                    dropDown(walmartInventory.getShoppingCart());
                }
            }
        });
        samsName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!list.isEmpty()) {
                    dropDown(samsInventory.getShoppingCart());
                }
            }
        });
        hebName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!list.isEmpty()) {
                    dropDown(hebInventory.getShoppingCart());
                }
            }
        });

        butClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearList();

            }
        });

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

        ArrayAdapter<String> defaultWalmart = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cartText);  // This adapter will display 'Cart' in the dropdown when no store is selected
        spinTest.setAdapter(defaultWalmart);

    }

    /**
     * This method controls our dropdown. It creates a String[] with a size equal to how many search terms the user inputted. From there it fetches the HashMap representing the selected store's shopping cart
     * and displays it accordingly
     * @param cart This is the HashMap shopping cart that is passed when a user selects a store above the price display
     */
    public void dropDown(HashMap cart){
        String[] itemsAndPrices = new String[list.size()];
        int num = 0;
        for(Object key: cart.keySet()){
            itemsAndPrices[num] = "("+ list.get(num)+ ") " +key + ":\t" + ("$") +df.format(cart.get(key));
            num++;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                itemsAndPrices);
        spinTest.setAdapter(adapter);

    }

    /**
     * This method is called when a user hits the save button. If an item is not supported by the program then an error pops up asking the user to enter a valid search. Otherwise the program continues and adds the
     * search to the ArrayList list and clears the text.
     */

    public void saveSearch(){
        String str = textInput.getText().toString();
        if(!availableItems.contains(str.toLowerCase())){
            textInput.setError("No stores carry this item type - re enter input");
            return;
        }
        list.add(str);
        textInput.getText().clear();
        textInput.setText(null);
        textInput.setError(null);

    }

    /**
     * This method will clear out all elements inputted by the user in to the search. It will also reset the dropdown to display 'Cart' and reset the colors of all price displays to default black.
     */
    public void clearList(){
        ArrayAdapter<String> defaultWalmart = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, cartText);
        list.clear();
        setTexttoDefault();
        walmartPrice.setText(null);
        samsPrice.setText(null);
        hebPrice.setText(null);
        spinTest.setAdapter(defaultWalmart);
    }

    /**
     * This method is the actual code used to remove any green text from the price displays and reset all to default black.
     */

    public void setTexttoDefault(){
        samsPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.defaultTextColor));
        walmartPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.defaultTextColor));
        hebPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.defaultTextColor));
    }

    /**
     * This method is called once the user selects 'Calculate'. Within the method, 3 storeInventory classes are created and populated. From there the program sets the price display text as well as
     * changes the color of the cheapest cart to green.
     */
    public void generateCarts(){
        setTexttoDefault();

        samsInventory = new storeInventory(list);
        samsInventory.searchInventory();
        Double samsTotal = samsInventory.getTotalPrice();
        samsTotal = Double.valueOf(df.format(samsTotal));
        samsPrice.setText("$" + samsTotal.toString());

        walmartInventory = new storeInventory(list);
        walmartInventory.searchInventory();
        Double walmartTotal = walmartInventory.getTotalPrice();
        walmartTotal = Double.valueOf(df.format(walmartTotal));
        walmartPrice.setText("$" + walmartTotal.toString());

        hebInventory = new storeInventory(list);
        hebInventory.searchInventory();
        Double hebTotal = hebInventory.getTotalPrice();
        hebTotal = Double.valueOf(df.format(hebTotal));
        hebPrice.setText("$" + hebTotal.toString());

        if(samsTotal <= walmartTotal && samsTotal <= hebTotal){
            samsPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.cheapestCart));
        }else if(walmartTotal <= samsTotal && walmartTotal <= hebTotal){
            walmartPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.cheapestCart));
        }else{
            hebPrice.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.cheapestCart));
        }

        //dropDown();
     }

}




