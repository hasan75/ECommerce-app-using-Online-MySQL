package com.hasan.onlineshopping.mapping;

import com.hasan.onlineshopping.resource.ImageResource;

import java.util.HashMap;

/**
 * Created by hasan on 1/18/18.
 */

public class ImageResourceMap {

    private static ImageResourceMap imageResourceMap;
    private static HashMap<Integer,Integer>map = new HashMap<>();
    public static ImageResourceMap getImageResourceMap(){
        if(imageResourceMap == null){
            imageResourceMap = new ImageResourceMap();
        }
        return imageResourceMap;
    }

    public static void putImageResource(int imgResource,int pro_id){
        map.put(imgResource,pro_id);
    }

    public static int getImgResource(int imgResource){
        return map.get(imgResource);
    }
}
