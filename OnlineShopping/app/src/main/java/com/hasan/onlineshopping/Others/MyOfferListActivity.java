package com.hasan.onlineshopping.Others;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hasan.onlineshopping.Holder.OfferViewHolder;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.StartingScreen.MainActivity;
import com.hasan.onlineshopping.details.ItemDetailSuggestionActivity;
import com.hasan.onlineshopping.mapping.FreqItemsMap;
import com.hasan.onlineshopping.mapping.ShopIdMap;
import com.hasan.onlineshopping.resource.ImageResource;
import com.hasan.onlineshopping.resource.ItemResource;

import java.util.ArrayList;

/**
 * Created by Hasan on 1/22/2018.
 */

public class MyOfferListActivity extends AppCompatActivity{
    private static Context context;
    private RecyclerView firstOfferRecyclerView;
    private RecyclerView secondOfferRecyclerView;
    private Button first_offer_btn;
    private Button second_offer_btn;
    private TextView first_offer_details;
    private TextView second_offer_details;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_view);
        context = MyOfferListActivity.this;



        firstOfferRecyclerView = findViewById(R.id.first_offer_recycler_view);
        secondOfferRecyclerView = findViewById(R.id.second_offer_recycler_view);
        first_offer_btn = findViewById(R.id.first_offer_button);
        second_offer_btn = findViewById(R.id.second_offer_button);
        first_offer_details = findViewById(R.id.first_offer_details);
        second_offer_details = findViewById(R.id.second_offer_details);


        ArrayList<FreqItemsMap> temp = ShopIdMap.getShopIdMap().getFreqItemsMaps();
        ArrayList<String>offerItemNames1 = new ArrayList<>();
        ArrayList<String>offerItemNames2 = new ArrayList<>();
        final ArrayList<Integer>offerImgResources1 = new ArrayList<>();
        final ArrayList<Integer>offerImgResources2 = new ArrayList<>();

        for (int i = 0; i < temp.get(0).freqItems.size(); i++){
            offerImgResources1.add(ImageResource.getImageRes(temp.get(0).freqItems.get(i)));
            offerItemNames1.add(ItemResource.getItemName(temp.get(0).freqItems.get(i)));

        }
        Log.v("size : " ,Integer.toString(temp.get(1).freqItems.size()));
        for (int i = 0; i < temp.get(1).freqItems.size(); i++){
            offerImgResources2.add(ImageResource.getImageRes(temp.get(1).freqItems.get(i)));
            offerItemNames2.add(ItemResource.getItemName(temp.get(1).freqItems.get(i)));

        }

        if(offerImgResources1.size() > 0){
            first_offer_details.setText("10% discount on these pack");
        }
        else{
            first_offer_details.setText("");
        }
        if(offerImgResources2.size() > 0){
            second_offer_details.setText("10% discount on these pack");
        }
        else{
            second_offer_details.setText("");
        }

        first_offer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< offerImgResources1.size(); i++){
                    boolean isok = ImageResource.addToCartList(offerImgResources1.get(i));
                    if(isok) MainActivity.howManyCartItems++;
                }
                Toast.makeText(MyOfferListActivity.this,"Pack added to the cart",Toast.LENGTH_LONG).show();
            }
        });

        second_offer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i< offerImgResources2.size(); i++){
                    boolean is = ImageResource.addToCartList(offerImgResources2.get(i));
                    if(is) MainActivity.howManyCartItems++;
                }
                Toast.makeText(MyOfferListActivity.this,"Pack added to the cart",Toast.LENGTH_LONG).show();
            }
        });

        StaggeredGridLayoutManager staggeredGridLayoutManager1 = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        StaggeredGridLayoutManager staggeredGridLayoutManager2 = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        firstOfferRecyclerView.setLayoutManager(staggeredGridLayoutManager1);
        secondOfferRecyclerView.setLayoutManager(staggeredGridLayoutManager2);
        firstOfferRecyclerView.setAdapter(new OfferListRecyclerViewAdapter(offerImgResources1,offerItemNames1));
        secondOfferRecyclerView.setAdapter(new OfferListRecyclerViewAdapter(offerImgResources2,offerItemNames2));

    }

    public static class OfferListRecyclerViewAdapter extends RecyclerView.Adapter<OfferViewHolder>{

        private ArrayList<Integer> offerImgResource;
        private ArrayList<String> offerItemNames;

        public OfferListRecyclerViewAdapter(ArrayList<Integer>a,ArrayList<String>b){
            offerImgResource = a;
            offerItemNames = b;

        }

        @Override
        public void onViewRecycled(OfferViewHolder holder) {
            if(holder.offerItemImage.getController() != null){
                holder.offerItemImage.getController().onDetach();
            }
            if(holder.offerItemImage.getTopLevelDrawable() != null){
                holder.offerItemImage.getTopLevelDrawable().setCallback(null);
            }


        }

        @Override
        public OfferViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_card_view,parent,false);
            return new OfferViewHolder(view);
        }

        @Override
        public void onBindViewHolder(OfferViewHolder holder, final int position) {
            holder.offerItemImage.setImageResource(offerImgResource.get(position));
            holder.offerItemName.setText(offerItemNames.get(position));
            holder.offerItemsLayour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context , ItemDetailSuggestionActivity.class);
                    intent.putExtra("IMAGE_RES", offerImgResource.get(position));
                    intent.putExtra("IMAGE_POS", position);
                    context.startActivity(intent);
                }
            });




        }

        @Override
        public int getItemCount() {
            return offerImgResource.size();
        }
    }
}
