package com.hasan.onlineshopping.StartingScreen;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.details.ItemDetailSuggestionActivity;
import com.hasan.onlineshopping.mapping.AssociationRuleMap;
import com.hasan.onlineshopping.mapping.FreqItemsMap;
import com.hasan.onlineshopping.mapping.ShopIdMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Hasan on 1/20/2018.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText shopId,password;
    private Button login;

    ShopIdMap shopIdMap;

    private ArrayList<FreqItemsMap> freqItemsMapsTemp;
    private ArrayList<AssociationRuleMap> associationRulesTemp;

    Boolean validOutlet;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        shopId = findViewById(R.id.shop_id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);

        freqItemsMapsTemp = new ArrayList<>();
        associationRulesTemp = new ArrayList<>();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shop_id = shopId.getText().toString();
                String pass = password.getText().toString();

                if(shopId.getText().toString().equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity.this,"Enter valid shop id or password",Toast.LENGTH_SHORT).show();
                }
                else{
                    shopIdMap = ShopIdMap.getShopIdMap();
                    shopIdMap.setShopId(Integer.parseInt(shop_id));

                    validOutlet = true;
                    QueryTask queryTask = new QueryTask();
                    queryTask.execute(shop_id);
                    //makeQuery(shop_id);
                    //Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                    //startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    private void makeQuery(String outlet){
        //273797
         JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "http://192.168.43.94:1234/OnlineShop/db_manager.php?id="+outlet,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {


                            JSONObject jsonObject = response.getJSONObject(0);


                            Integer temp = jsonObject.getInt("outlet_id");
                            //Toast.makeText(LoginActivity.this, "IT is" + jsonObject.toString(), Toast.LENGTH_LONG).show();
                            String ascRulesTemp = jsonObject.getString("asc_rule");
                            JSONArray ascRulesArr = new JSONArray(ascRulesTemp);

                            String freqItemsTemp = jsonObject.getString("freq_items");
                            JSONArray freqItemsArr = new JSONArray(freqItemsTemp);

                            JSONArray antArr, conArr;
                            Double conf;

                            for (int i = 0; i < ascRulesArr.length(); i++){
                                JSONObject ascRule = (JSONObject) ascRulesArr.get(i);
                                AssociationRuleMap associationRule = new AssociationRuleMap();

                                antArr = ascRule.getJSONArray("ant");
                                conArr = ascRule.getJSONArray("con");
                                conf = ascRule.getDouble("conf");
                                for (int j = 0; j < antArr.length(); j++)
                                    associationRule.ant.add(antArr.getInt(j));

                                for (int j = 0; j < conArr.length(); j++)
                                    associationRule.con.add(conArr.getInt(j));

                                associationRule.confidence = conf;

                                if (associationRule.ant.size() == 1)
                                    associationRulesTemp.add(associationRule);
                                //Toast.makeText(ItemDetailSuggestionActivity.this,associationRule.getStr()+" it is",Toast.LENGTH_LONG).show();
                            }

                            Collections.sort(associationRulesTemp, new Comparator<AssociationRuleMap>() {
                                @Override
                                public int compare(AssociationRuleMap t1, AssociationRuleMap t2) {
                                    return Double.compare(t1.confidence, t2.confidence);
                                }
                            });
                            Collections.reverse(associationRulesTemp);
                            shopIdMap.setAssociationRuleMaps(associationRulesTemp);

                            JSONArray freqArr;
                            Double sup;
                            for (int i = 0; i < freqItemsArr.length(); i++){
                                JSONObject freqItem = (JSONObject) freqItemsArr.get(i);
                                FreqItemsMap freqItemsMap = new FreqItemsMap();

                                freqArr = freqItem.getJSONArray("freq_item");
                                sup = freqItem.getDouble("sup");
                                for (int j = 0; j < freqArr.length(); j++)
                                    freqItemsMap.freqItems.add(freqArr.getInt(j));
                                freqItemsMap.support = sup;

                                if (freqItemsMap.freqItems.size() > 1 && freqItemsMap.freqItems.size() <= 3)
                                    freqItemsMapsTemp.add(freqItemsMap);
                            }
                            Collections.sort(freqItemsMapsTemp, new Comparator<FreqItemsMap>() {
                                @Override
                                public int compare(FreqItemsMap t1, FreqItemsMap t2) {
                                    return Double.compare(t1.support, t2.support);
                                }
                            });
                            Collections.reverse(freqItemsMapsTemp);

                            shopIdMap.setFreqItemsMaps(freqItemsMapsTemp);

                            Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

//
                        } catch (JSONException e) {
                            //e.printStackTrace();

                            Toast.makeText(LoginActivity.this,"Invalid Shop Id",Toast.LENGTH_SHORT).show();
                        }
                        //progressBar.setVisibility(View.GONE);
                        //JSON_PARSE_DATA_AFTER_WEBCALL(response);
                        //if(isRest == false) hasLoadedAll = true;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(LoginActivity.this,"Connection Problem",Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

        requestQueue.add(jsonArrayRequest);
    }

    private class QueryTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... strings) {
            String temp = strings[0];
            makeQuery(temp);
            return null;
        }


//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//            if (validOutlet) {
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        }
    }
}
