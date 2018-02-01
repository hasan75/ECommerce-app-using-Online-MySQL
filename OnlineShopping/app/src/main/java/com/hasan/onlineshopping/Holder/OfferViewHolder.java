package com.hasan.onlineshopping.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hasan.onlineshopping.R;

/**
 * Created by Hasan on 1/22/2018.
 */

public class OfferViewHolder extends RecyclerView.ViewHolder{
    public final View mView;
    public final TextView offerItemName;
    public final SimpleDraweeView offerItemImage;
    public final LinearLayout offerItemsLayour;

    public OfferViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        offerItemName = itemView.findViewById(R.id.offer_item_name);
        offerItemImage = itemView.findViewById(R.id.offer_item_image);
        offerItemsLayour = itemView.findViewById(R.id.offer_cardview_items);
    }
}


