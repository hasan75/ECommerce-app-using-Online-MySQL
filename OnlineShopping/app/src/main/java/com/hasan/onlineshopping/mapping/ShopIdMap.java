package com.hasan.onlineshopping.mapping;

import java.util.ArrayList;

/**
 * Created by Hasan on 1/20/2018.
 */

public class ShopIdMap {
    private static int shop_id = 399;
    private static ShopIdMap shopIdMap;

    private static ArrayList<FreqItemsMap> freqItemsMaps;
    private static ArrayList<AssociationRuleMap> associationRuleMaps;

    private ShopIdMap(){
        associationRuleMaps = new ArrayList<>();
        freqItemsMaps = new ArrayList<>();
    }
    public static ShopIdMap  getShopIdMap(){
        if(shopIdMap == null){
            shopIdMap = new ShopIdMap();
        }
        return shopIdMap;
    }

    public static void setShopId(int id){
        shop_id = id;
    }

    public static int getShopId(){
        return shop_id;
    }

    public static ArrayList<AssociationRuleMap> getAssociationRuleMaps(){
        return associationRuleMaps;
    }

    public static ArrayList<FreqItemsMap> getFreqItemsMaps() {
        return freqItemsMaps;
    }

    public static void setFreqItemsMaps(ArrayList<FreqItemsMap> freqItemsMaps) {
        ShopIdMap.freqItemsMaps = freqItemsMaps;
    }

    public static void setAssociationRuleMaps(ArrayList<AssociationRuleMap> associationRuleMaps) {
        ShopIdMap.associationRuleMaps = associationRuleMaps;
    }
}
