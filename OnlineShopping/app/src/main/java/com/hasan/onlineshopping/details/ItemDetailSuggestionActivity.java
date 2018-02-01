package com.hasan.onlineshopping.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hasan.onlineshopping.Holder.RecyclerViewHolder;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.StartingScreen.MainActivity;
import com.hasan.onlineshopping.mapping.AssociationRuleMap;
import com.hasan.onlineshopping.mapping.ImageResourceMap;
import com.hasan.onlineshopping.mapping.ShopIdMap;
import com.hasan.onlineshopping.resource.ImageResource;
import com.hasan.onlineshopping.resource.ItemResource;
import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hasan on 1/18/18.
 */

public class ItemDetailSuggestionActivity extends AppCompatActivity {
    private static Context context;
    private int imgPos;
    private int imgRes;
    private PullToLoadView pullToLoadView;
    private RecyclerViewImageAdapter adapter;
    private boolean isLoading = false;
    private boolean hasLoadedAll = false;
    private JsonArrayRequest jsonArrayRequest ;
    private RequestQueue requestQueue ;
    private TextView itemDetailName,itemDetailPrice,itemDetailDesc;

    private int tabPosition;


    private int pro_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail_suggestion);
        
        context = ItemDetailSuggestionActivity.this;

        SimpleDraweeView draweeView = findViewById(R.id.details_image);
        //TextView addToCart = findViewById(R.id.action_add_to_cart);
        Button addToCart = findViewById(R.id.action_add_to_cart_button);
        itemDetailName = findViewById(R.id.item_detail_name);
        itemDetailPrice = findViewById(R.id.item_detail_price);
        itemDetailDesc = findViewById(R.id.item_detail_desc);

        imgRes = getIntent().getIntExtra("IMAGE_RES",0);
        imgPos = getIntent().getIntExtra("IMAGE_POS",0);
        tabPosition = getIntent().getIntExtra("TAB_POS",0);

        pro_id = ImageResourceMap.getImageResourceMap().getImgResource(imgRes);
        //Toast.makeText(ItemDetailSuggestionActivity.this,"got pro_id " + pro_id,Toast.LENGTH_SHORT).show();



        draweeView.setImageResource(imgRes);
        itemDetailName.setText(ItemResource.getItemName(pro_id));
        itemDetailPrice.setText(ItemResource.getItemPrice(pro_id));
        itemDetailDesc.setText(ItemResource.getItemDesc(pro_id));
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean is = ImageResource.addToCartList(imgRes);
                if(is){
                    MainActivity.howManyCartItems++;
                    Toast.makeText(ItemDetailSuggestionActivity.this,"Item added to cart.",Toast.LENGTH_SHORT).show();
                }

            }
        });
        //xtra

        pullToLoadView=  findViewById(R.id.pullToLoadView);
        RecyclerView recyclerView=pullToLoadView.getRecyclerView();
        RecyclerView.LayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new RecyclerViewImageAdapter(new ArrayList<Integer>(),new ArrayList<String>(),new ArrayList<String>());
        recyclerView.setAdapter(adapter);

        initialize();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ItemDetailSuggestionActivity.this,MainActivity.class);
        intent.putExtra("PREV_TAB",tabPosition);
        startActivity(intent);
        finish();
    }

    public void initialize() {

        ArrayList<AssociationRuleMap> temp = ShopIdMap.getShopIdMap().getAssociationRuleMaps();

        for (int i = 0; i < temp.size(); i++) {
            AssociationRuleMap tempRule = temp.get(i);
            if (tempRule.ant.get(0) == pro_id) {
                adapter.add(tempRule.con.get(0));

            }
        }

//        if (associationRules.get(i).ant.contains(pro_id)) {
//            Toast.makeText(ItemDetailSuggestionActivity.this,String.valueOf(associationRules.get(i).ant.get(0))+" "+
//                    String.valueOf(pro_id)+" here it is",Toast.LENGTH_LONG).show();
//
//
//            for (int j = 0; j < associationRules.get(i).con.size(); j++)
//                adapter.add(associationRules.get(i).con.get(j));
//        }


    }


    private class RecyclerViewImageAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        ArrayList<Integer>imgResources;
        ArrayList<String>sug_item_names;
        ArrayList<String>sug_item_prices;

        public RecyclerViewImageAdapter(ArrayList<Integer>a,ArrayList<String>b,ArrayList<String>c){
            imgResources = a;
            sug_item_names = b;
            sug_item_prices = c;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            //holder.cardview_image.setImageResource(imgResources[position]);
            Log.v("position : ",Integer.toString(position));
            Log.v("imgResouce : " , Integer.toString(ImageResource.getImageRes(imgResources.get(position))));
            holder.cardview_image.setImageResource(ImageResource.getImageRes(imgResources.get(position)));
            holder.itemName.setText(sug_item_names.get(position));
            holder.itemPrice.setText(sug_item_prices.get(position));
            holder.cardview_items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = getIntent();
                    finish();
                    intent.putExtra("IMAGE_RES", ImageResource.getImageRes(imgResources.get(position)));
                    intent.putExtra("IMAGE_POS", position);

                    startActivity(intent);

//                    Intent intent = new Intent(context, ItemDetailSuggestionActivity.class);
//                    ///intent.putExtra("IMAGE_RES", imgResources[position]);
//                    intent.putExtra("IMAGE_RES", ImageResource.getImageRes(imgResources.get(position)));
//                    intent.putExtra("IMAGE_POS", position);
//                    context.startActivity(intent);
                    //Toast.makeText(mActivity,"Cardview Clicked",Toast.LENGTH_SHORT).show();
                }
            });

            holder.cardview_favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //ImageResource.addToFavouriteList(imgResources[position]);
                    ImageResource.addToFavouriteList(ImageResource.getImageRes(imgResources.get(position)));
                    holder.cardview_favourites.setImageResource(R.mipmap.ic_favorite_black_18dp);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Item added to wishlist.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return imgResources.size();
        }

        public void add(int s) {
            Log.v("add : ",Integer.toString(s));
            if (!imgResources.contains(s)) {
                imgResources.add(s);
                sug_item_names.add(ItemResource.getItemName(s));
                sug_item_prices.add(ItemResource.getItemPrice(s));
                notifyDataSetChanged();
            }
        }

        public int getLength(){
            return imgResources.size();
        }

        /*
        CLEAR DATA FROM ADAPTER
         */
        public void clear() {
            imgResources.clear();
            notifyDataSetChanged();
        }

    }
}
