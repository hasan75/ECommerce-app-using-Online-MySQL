package com.hasan.onlineshopping.StartingScreen;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by hasan on 11/18/17.
 */

public class FrescoActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);

    }
}
