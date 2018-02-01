package com.hasan.onlineshopping.Others;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hasan.onlineshopping.Holder.CartListRecyclerViewHolder;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.StartingScreen.LoginActivity;
import com.hasan.onlineshopping.StartingScreen.MainActivity;
import com.hasan.onlineshopping.details.ItemDetailActivity;
import com.hasan.onlineshopping.details.ItemDetailSuggestionActivity;
import com.hasan.onlineshopping.mapping.ImageResourceMap;
import com.hasan.onlineshopping.resource.ImageResource;
import com.hasan.onlineshopping.resource.ItemResource;

import java.util.ArrayList;

/**
 * Created by hasan on 11/25/17.
 */

public class MyCartListActivity extends AppCompatActivity {
    private static Context context;
    private LinearLayout my_cart_list_recycleview_layout;
    private LinearLayout my_cart_list_empty;
    private Button empty_cart_list_action_shop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_cart_list);
        context = MyCartListActivity.this;

        my_cart_list_recycleview_layout = findViewById(R.id.my_cart_list_recycleview_layout);
        my_cart_list_empty = findViewById(R.id.my_cart_list_empty);
        empty_cart_list_action_shop = findViewById(R.id.empty_cart_list_action_shop);
        //////////////////////

        if(MainActivity.howManyCartItems > 0){
            my_cart_list_recycleview_layout.setVisibility(View.VISIBLE);
            my_cart_list_empty.setVisibility(View.GONE);
        }
        else{
            my_cart_list_recycleview_layout.setVisibility(View.GONE);
            my_cart_list_empty.setVisibility(View.VISIBLE);

            empty_cart_list_action_shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }

        /////////////////

        ArrayList<Integer>cartListRes = ImageResource.getCartList();

        RecyclerView recyclerView = findViewById(R.id.my_cart_list_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyCartListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new CartListRecyclerViewAdapter(cartListRes,my_cart_list_empty,my_cart_list_recycleview_layout,empty_cart_list_action_shop));


    }



    public static class CartListRecyclerViewAdapter extends RecyclerView.Adapter<CartListRecyclerViewHolder>{

        private ArrayList<Integer> imgResource;
        private LinearLayout emptyCart,nonEmptyCart;
        private Button shopNow;
        public CartListRecyclerViewAdapter(ArrayList<Integer>res,LinearLayout empty,LinearLayout nonEmpty,Button b){
            imgResource = res;
            emptyCart = empty;
            nonEmptyCart = nonEmpty;
            shopNow = b;
        }

        @Override
        public void onViewRecycled(CartListRecyclerViewHolder holder) {
            if(holder.my_cart_itemview_simpleDraweeview.getController() != null){
                holder.my_cart_itemview_simpleDraweeview.getController().onDetach();
            }
            if(holder.my_cart_itemview_simpleDraweeview.getTopLevelDrawable() != null){
                holder.my_cart_itemview_simpleDraweeview.getTopLevelDrawable().setCallback(null);
            }


        }

        @Override
        public CartListRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_cart_list_item_view,parent,false);
            return new CartListRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CartListRecyclerViewHolder holder, final int position) {
            int pro_id = ImageResourceMap.getImageResourceMap().getImgResource(imgResource.get(position));
            holder.my_cart_itemview_simpleDraweeview.setImageResource(imgResource.get(position));
            holder.my_cart_list_item_name.setText(ItemResource.getItemName(pro_id));
            holder.my_cart_list_item_price.setText(ItemResource.getItemPrice(pro_id));
            holder.my_cart_list_action_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context , ItemDetailSuggestionActivity.class);
                    intent.putExtra("IMAGE_RES", imgResource.get(position));
                    intent.putExtra("IMAGE_POS", position);
                    context.startActivity(intent);
                }
            });
            holder.my_cart_list_action_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageResource.removeFromCartList(position);
                    notifyDataSetChanged();
                    MainActivity.howManyCartItems--;
                    //xtra
                    if(MainActivity.howManyCartItems == 0){
                        nonEmptyCart.setVisibility(View.GONE);
                        emptyCart.setVisibility(View.VISIBLE);
                        shopNow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context,MainActivity.class);
                                intent.putExtra("SHOP_ID",0);
                                context.startActivity(intent);
                            }
                        });
                    }




                    Toast.makeText(context,"This item is removed :/", Toast.LENGTH_SHORT).show();
                }
            });

            holder.my_cart_list_action_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"Pay for it :3", Toast.LENGTH_SHORT).show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return imgResource.size();
        }
    }
}
