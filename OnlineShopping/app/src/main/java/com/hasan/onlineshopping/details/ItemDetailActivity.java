package com.hasan.onlineshopping.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.StartingScreen.MainActivity;
import com.hasan.onlineshopping.resource.ImageResource;

/**
 * Created by hasan on 11/23/17.
 */

public class ItemDetailActivity extends AppCompatActivity {
    int imgPos;
    int imgRes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.item_details);
        SimpleDraweeView draweeView = findViewById(R.id.details_image);
        TextView addToCart = findViewById(R.id.action_add_to_cart);
        TextView shopNow = findViewById(R.id.action_shop_now);

        imgRes = getIntent().getIntExtra("IMAGE_RES",0);
        imgPos = getIntent().getIntExtra("IMAGE_POS",0);

        //draweeView.setImageResource(imgRes);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageResource.addToCartList(imgRes);
                MainActivity.howManyCartItems++;

                Toast.makeText(ItemDetailActivity.this,"Item added to cart.",Toast.LENGTH_SHORT).show();

            }
        });

        shopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ItemDetailActivity.this,"Item added to order",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
