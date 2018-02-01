package com.hasan.onlineshopping.Holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hasan.onlineshopping.R;

/**
 * Created by hasan on 11/19/17.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView itemName;
    public final TextView itemPrice;
    public final SimpleDraweeView cardview_image;
    public final LinearLayout cardview_items;
    public final ImageView cardview_favourites;

    public RecyclerViewHolder(View view){
        super(view);
        mView = view;
        itemName = view.findViewById(R.id.item_name);
        itemPrice = view.findViewById(R.id.item_price);
        cardview_image = view.findViewById(R.id.cardview_image);
        cardview_items = view.findViewById(R.id.cardview_items);
        cardview_favourites = view.findViewById(R.id.cardview_favourites);

    }
}
