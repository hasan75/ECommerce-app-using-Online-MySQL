package com.hasan.onlineshopping.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hasan.onlineshopping.Holder.RecyclerViewHolder;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.StartingScreen.MainActivity;
import com.hasan.onlineshopping.details.ItemDetailActivity;
import com.hasan.onlineshopping.details.ItemDetailSuggestionActivity;
import com.hasan.onlineshopping.mapping.FreqItemsMap;
import com.hasan.onlineshopping.mapping.ImageResourceMap;
import com.hasan.onlineshopping.mapping.ShopIdMap;
import com.hasan.onlineshopping.resource.ImageResource;
import com.hasan.onlineshopping.resource.ItemResource;
import com.srx.widget.PullToLoadView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Created by hasan on 11/19/17.
 */

public class ImagesResourceFragment extends Fragment {

    private static MainActivity mActivity;
    private JsonArrayRequest jsonArrayRequest ;
    private RequestQueue requestQueue ;
    private List<Integer>offerPros;

    private ArrayList<Integer> imgResources;
    private ArrayList<String> itemNames;
    private ArrayList<String> itemPrices;
    private int tabPosition;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        offerPros = new ArrayList<>();

        imgResources = new ArrayList<>();
        itemNames = new ArrayList<>();
        itemPrices = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view,container,false);
        /*View view =  inflater.inflate(R.layout.recycler_view,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);*/
        /*View view = inflater.inflate(R.layout.offer_view, container, false);
        RecyclerView firstOfferRecyclerView = view.findViewById(R.id.first_offer_recycler_view);
        RecyclerView secondOfferRecyclerView = view.findViewById(R.id.second_offer_recycler_view);
        Button first_offer_btn = view.findViewById(R.id.first_offer_button);
        Button second_offer_btn = view.findViewById(R.id.second_offer_button);
        TextView first_offer_details = view.findViewById(R.id.first_offer_details);
        TextView second_offer_details = view.findViewById(R.id.second_offer_details);*/


        if(ImagesResourceFragment.this.getArguments().getInt("item_type") == 1){
            imgResources = ImageResource.getLaptops();
            itemNames = ItemResource.getLaptopNames();
            itemPrices = ItemResource.getLaptopPrices();
            tabPosition = 0;
        }
        else if(ImagesResourceFragment.this.getArguments().getInt("item_type") == 2){
            imgResources = ImageResource.getMobiles();
            itemNames = ItemResource.getMobileNames();
            itemPrices = ItemResource.getMobilePrices();
            tabPosition = 1;
        }
        else if(ImagesResourceFragment.this.getArguments().getInt("item_type") == 3){
            imgResources = ImageResource.getCameras();
            itemNames = ItemResource.getCameraNames();
            itemPrices = ItemResource.getCameraPrices();
            tabPosition = 2;
        }
        else if(ImagesResourceFragment.this.getArguments().getInt("item_type") == 4){
            imgResources = ImageResource.getTablets();
            itemNames = ItemResource.getTabletNames();
            itemPrices = ItemResource.getTabletPrices();
            tabPosition = 3;
        }
        //xtra
        /*else if(ImagesResourceFragment.this.getArguments().getInt("item_type") == 5){

            ArrayList<FreqItemsMap> temp = ShopIdMap.getShopIdMap().getFreqItemsMaps();

            for (int i = 0; i < temp.get(0).freqItems.size(); i++){
                imgResources.add(ImageResource.getImageRes(temp.get(0).freqItems.get(i)));
                itemNames.add(ItemResource.getItemName(temp.get(0).freqItems.get(i)));
                itemPrices.add(ItemResource.getItemPrice(temp.get(0).freqItems.get(i)));
           }


            //tabPosition = 4;
        }*/

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(new RecyclerViewImagesAdapter(imgResources, itemNames, itemPrices));

        return recyclerView;
        //return view;

    }


    public class RecyclerViewImagesAdapter extends RecyclerView.Adapter<RecyclerViewHolder>{

        //int [] imgResources;
        private ArrayList<Integer> imgResources;
        private ArrayList<String> itemNames;
        private ArrayList<String> itemPrices;

        public RecyclerViewImagesAdapter(ArrayList<Integer> a, ArrayList<String> s1, ArrayList<String> s2){
            imgResources = a;
            itemNames = s1;
            itemPrices = s2;
        }

        @Override
        public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);

            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
            //Toast.makeText(mActivity, String.valueOf(imgResources.get(position)),Toast.LENGTH_LONG).show();
            holder.itemName.setText(itemNames.get(position));
            holder.itemPrice.setText(itemPrices.get(position));
            holder.cardview_image.setImageResource(imgResources.get(position));

            holder.cardview_items.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mActivity, ItemDetailSuggestionActivity.class);
                    intent.putExtra("IMAGE_RES", imgResources.get(position));
                    intent.putExtra("IMAGE_POS", position);
                    intent.putExtra("TAB_POS", tabPosition);
                    mActivity.startActivity(intent);
                    //Toast.makeText(mActivity,"Cardview Clicked",Toast.LENGTH_SHORT).show();
                }
            });

            holder.cardview_favourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageResource.addToFavouriteList(imgResources.get(position));
                    holder.cardview_favourites.setImageResource(R.mipmap.ic_favorite_black_18dp);
                    notifyDataSetChanged();
                    Toast.makeText(mActivity,"Item added to wishlist.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return imgResources.size();
        }
    }




}
