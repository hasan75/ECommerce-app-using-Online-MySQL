package com.hasan.onlineshopping.resource;

import com.hasan.onlineshopping.R;
import com.hasan.onlineshopping.mapping.ImageResourceMap;

import java.util.ArrayList;

/**
 * Created by hasan on 11/19/17.
 */

public class ImageResource {
    private static ArrayList<Integer>favouritesList = new ArrayList<>();
    private static ArrayList<Integer>cartList = new ArrayList<>();
   /* private static int [] productArray = {-1,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,
            R.drawable.p9,R.drawable.p10,R.drawable.p11,R.drawable.p12,R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16,R.drawable.p17,
            R.drawable.p18,R.drawable.p19,R.drawable.p20,R.drawable.p21,R.drawable.p22,R.drawable.p23,R.drawable.p24,R.drawable.p25};

*/
    private static int [] productArray = {0,R.drawable.p1,R.drawable.p2,R.drawable.p3,R.drawable.p4,R.drawable.p5,R.drawable.p6,R.drawable.p7,R.drawable.p8,
            R.drawable.p9,R.drawable.p10,R.drawable.p11,R.drawable.p12,R.drawable.p13,R.drawable.p14,R.drawable.p15,R.drawable.p16,R.drawable.p17,
            R.drawable.p18,R.drawable.p19,R.drawable.p20,R.drawable.p21,R.drawable.p22,R.drawable.p23,R.drawable.p24,R.drawable.p25,
            R.drawable.p26,R.drawable.p27,R.drawable.p28,R.drawable.p29,R.drawable.p30,R.drawable.p31,R.drawable.p32,R.drawable.p33,
            R.drawable.p34,R.drawable.p35,R.drawable.p36,R.drawable.p37,R.drawable.p38,R.drawable.p39,R.drawable.p40,R.drawable.p41,
            R.drawable.p42,R.drawable.p43,R.drawable.p44,R.drawable.p45,R.drawable.p46,R.drawable.p47,R.drawable.p48,
            R.drawable.p49,R.drawable.p50,R.drawable.p51};

    public static int getImageRes(int proId){
        return productArray[proId];
    }

    public static ArrayList<Integer>  getLaptops(){
        ArrayList<Integer> arr = new ArrayList<>();
        int j = 0;
        for(int i = 1; i <= 12; i++) {
            arr.add(productArray[i]);
            ImageResourceMap.getImageResourceMap().putImageResource(productArray[i],i);
        }

        return arr;
    }

    /*public static int [] getElectronics(){
        int [] arr = {R.drawable.electronics_apple_1,R.drawable.electronics_hp_1,
        R.drawable.electronics_huawei,R.drawable.electronics_man_handwatch,
        R.drawable.electronics_sony,R.drawable.electronics_woman_handwatch};
        return arr;
    }*/

    public static ArrayList<Integer> getMobiles(){
        ArrayList<Integer> arr = new ArrayList<>();
        //int [] arr = new int[10];
        int j = 0;
        for(int i = 13; i <= 25; i++) {
            arr.add(productArray[i]);
            ImageResourceMap.getImageResourceMap().putImageResource(productArray[i],i);
        }
        return arr;
    }

    /*public static int [] getJuwelery(){
        int [] arr = {R.drawable.juwelery_1,R.drawable.juwelery_2,R.drawable.juwelery_3,
        R.drawable.juwelery_4,R.drawable.juwelery_5,R.drawable.juwelery_6};
        return arr;
    }*/

    public static ArrayList<Integer>  getCameras(){
        ArrayList<Integer> arr = new ArrayList<>();
        int j = 0;
        for(int i = 26; i <= 36; i++) {
            arr.add(productArray[i]);
            ImageResourceMap.getImageResourceMap().putImageResource(productArray[i],i);
        }
        arr.add(productArray[46]);
        ImageResourceMap.getImageResourceMap().putImageResource(productArray[46],46);
        return arr;
    }



    /*public static int [] getBooks(){
        int [] arr = {R.drawable.offer_book_1,R.drawable.books_allthelightwecannotsee,
        R.drawable.books_dont_i_know_u,R.drawable.books_wolfbordercover};
        return arr;
    }*/

    public static ArrayList<Integer>  getTablets(){
        ArrayList<Integer> arr = new ArrayList<>();
        int j = 0;
        for(int i = 37; i <= 45; i++) {
            arr.add(productArray[i]);
            ImageResourceMap.getImageResourceMap().putImageResource(productArray[i],i);
        }
        for(int i = 47; i <= 51; i++) {
            arr.add(productArray[i]);
            ImageResourceMap.getImageResourceMap().putImageResource(productArray[i],i);
        }

        return arr;
    }

    public static void addToFavouriteList(int id){
        favouritesList.add(id);
    }

    public static ArrayList<Integer> getFavouriteList(){
        return favouritesList;
    }

    public static void removeFromFavouriteList(int pos){
        favouritesList.remove(pos);
    }

    public static boolean addToCartList(int id){
        if(cartList.contains(id) == false) {cartList.add(id);return true;}
        else return false;
    }

    public static ArrayList<Integer> getCartList(){
        return cartList;
    }

    public static void removeFromCartList(int pos){
        cartList.remove(pos);
    }


}
