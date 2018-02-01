package com.hasan.onlineshopping.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hasan.onlineshopping.R;

/**
 * Created by hasan on 11/25/17.
 */

public class CartListRecyclerViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final SimpleDraweeView my_cart_itemview_simpleDraweeview;
    public final LinearLayout my_cart_list_recycleview_layout;
    public final LinearLayout my_cart_itemview_desc;
    public final Button my_cart_list_action_remove;
    public final Button my_cart_list_action_pay;
    public final Button my_cart_list_action_details;
    public final TextView my_cart_list_item_name;
    public final TextView my_cart_list_item_price;

    public CartListRecyclerViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        my_cart_list_recycleview_layout = itemView.findViewById(R.id.my_cart_list_recycleview_layout);
        my_cart_itemview_simpleDraweeview = itemView.findViewById(R.id.my_cart_itemview_simpleDraweeview);
        my_cart_itemview_desc = itemView.findViewById(R.id.my_cart_itemview_desc);
        my_cart_list_action_remove = itemView.findViewById(R.id.my_cart_list_action_remove);
        my_cart_list_action_pay = itemView.findViewById(R.id.my_cart_list_action_pay);
        my_cart_list_action_details = itemView.findViewById(R.id.my_cart_list_action_details);
        my_cart_list_item_name = itemView.findViewById(R.id.my_cart_itemview_name);
        my_cart_list_item_price = itemView.findViewById(R.id.my_cart_itemview_price);
    }
}
