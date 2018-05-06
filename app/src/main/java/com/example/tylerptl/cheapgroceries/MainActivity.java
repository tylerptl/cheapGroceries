package com.example.tylerptl.cheapgroceries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText textInput;
    Button butSave;
    ArrayList<String> list;
    // com.example.tylerptl.cheapgroceries.walmartStore walmartStore;
    Button calc;
    TextView walmartPrice, samsPrice, hebPrice;
    DecimalFormat df;
    storeInventory samsInventory, walmartInventory, hebInventory;

    /////////////////////////Expandable List stuff below this line

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, Double> expandableListDetail = new HashMap<>();


    //////////////////////////////////////////////////////////////////


    //Double totalSamsPrice;

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
        //task = new gatherSamsPrices();


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


        ///////////////////////////// Expandable List stuff below this line
//        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
//
//        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
//        expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);
//        expandableListView.setAdapter(expandableListAdapter);
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(getApplicationContext(),
//                        expandableListTitle.get(groupPosition) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v,
//                                        int groupPosition, int childPosition, long id) {
//                Toast.makeText(
//                        getApplicationContext(),
//                        expandableListTitle.get(groupPosition)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT
//                ).show();
//                return false;
//            }
//        });
    }

//////////////////////////////////////////////////////////////////////


//    /**
//     * This works SOME OF THE TIME - other times it just crashes the app do to Asynctask
//     * When the method does work it retrieves the proper value
//     */
//    private class gatherSamsPrices extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... urls) {
//            HttpResponse response = null;
//            HttpGet httpGet = null;
//            HttpClient mHttpClient = null;
//            String s = "";
//            String index = "";
//
//            try {
//                if(mHttpClient == null){
//                    mHttpClient = new DefaultHttpClient();
//                }
//
//
//                httpGet = new HttpGet(urls[0]);
//
//
//                response = mHttpClient.execute(httpGet);
//                s = EntityUtils.toString(response.getEntity(), "UTF-8");
//                s = s.substring(s.indexOf("currencyAmount\":"));
//                s = s.substring(s.indexOf(":"+1), s.indexOf(","));
//                s = s.substring(1, s.length());
//
//
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return s;
//        }
//
//        @Override
//        protected void onPostExecute(String result){
//            totalSamsPrice += Double.parseDouble(result);
//            samsPrice.setText(totalSamsPrice.toString());
//
//        }
//    }

    public void saveSearch(){
        String str = textInput.getText().toString();
        list.add(str);
        textInput.getText().clear();
        textInput.setText(null);
    }

    public void generateCarts(){
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

        ExpandableListDataPump expand = new ExpandableListDataPump(walmartInventory.getShoppingCart(), samsInventory.getShoppingCart(), hebInventory.getShoppingCart());
        }



       // prepareWalmartPrices();
    }
//    public void prepareWalmartPrices() {
//        walmartStore = new walmartStore(list);
//        //samsStore = new samsStore(list);
//        try {
//            walmartStore.createCart();
//            //samsStore.createCart();
//            walmartPrice.setText(walmartStore.getTotalCost().toString());
//            // prepareSamsPrices();
//           // samsPrice.setText(samsStore.getTotalCost().toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    public void prepareSamsPrices(){
//        String samsUrl ="https://www.samsclub.com/sams/search/searchResults.jsp?clubId=8226&limit=48&offset=0&searchTerm=";
//        String samsEndTag = "&selectedFilter=club&sortKey=relevance&sortOrder=1&viewMode=grid";
//        String url;
//        StringBuilder urlCreate = new StringBuilder();
//        for(String str : list){
//            url ="";
//            urlCreate.append(samsUrl).append(str).append(samsEndTag);
//            url = urlCreate.toString();
//            url = url.replaceAll(" ", "%20");
//            task.execute(url);
//
//        }
//    }

