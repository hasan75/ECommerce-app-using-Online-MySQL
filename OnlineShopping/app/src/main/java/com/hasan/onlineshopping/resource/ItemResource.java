package com.hasan.onlineshopping.resource;

/**
 * Created by Nayeem Hasan on 22-Jan-18.
 */
import java.util.ArrayList;


public class ItemResource {
    private static String[] itemnames = {"","Lenevo ThinkPad","Lenevo V110","Lenevo Legion","HP Pavilion",
            "HP Probook","HP Notebook","Apple Macbook","Apple Macbook","Apple Macbook","Samsung Notebook",
            "Samsung Notebook","Apple Macbook","Symphony Helio S25","Symphony Helio S20","Symphony Xplorer P6",
            "Xiaomi Redmi 4","Symphony Helio S19","Xiaomi Mi5","Huawei GR5","Huawei Y5","Huawei P10","Xiaomi Redmi Note 4",
            "Xiaomi Redmi Note 2","Sony Xperia T3","Sony Xperia X","Nikon COOLPIX B500","Nikon 1 J5","Nikon COOLPIX P510",
            "Samsung NX100", "Samsung NX300","Samsung WB1100F","Canon EOS Rebel T7i","Canon Powershot SX730",
            "Samsung SR17","Samsung NX300",  "Samsung Galaxy GC100","Acer Iconia W700P","Lenovo TAB4 TB",
            "ASUS EP121-1A010M","Envizen V8041Q", "Samsung i5-2467M" ,"Cube iPlay 8","Samsung i5-246",
            "Samsung Galaxy P5100","Apple iPad",  "Samsung Galaxy GC100","Acer Iconia W700P","Lenovo TAB4 TB",
            "ASUS EP121-1A010M","Samsung i5-2467M","Samsung i5-2467M","Samsung i5-2467M"};

    private static String[]itemprices = {"","BDT 55000","BDT 75000","BDT 37000","BDT 60000","BDT 52000",
            "BDT 40000","BDT 125000","BDT 150000","BDT 200000","BDT 46500",
            "BDT 49000","BDT 110000","BDT 12000","BDT 11050","BDT 9000","BDT 21000","BDT 12000",
            "BDT 22050","BDT 21000","BDT 12000","BDT 25000",
            "BDT 25000","BDT 22100","BDT 18000","BDT 20000","BDT 21200","BDT 32000",
            "BDT 22100","BDT 22133","BDT 28102","BDT 40000","BDT 36000","BDT 42000","BDT 21000","BDT 32000","BDT 42010",
            "BDT 50000","BDT 21100","BDT 49204","BDT 92020","BDT 93290","BDT 12300","BDT 21000","BDT 22000","BDT 23000",
                "BDT 42010", "BDT 50000","BDT 21100","BDT 49204","BDT 93290","BDT 49204","BDT 49204","BDT 49204"
            };

    private static String[]itemdesc= {"",
            "15.6 inch\n5th gen",
            "14 inch\n5th gen",
            "15.6 inch\n5th gen",
            "15.6 inch\n5th gen"
            ,"15.6 inch\n5th gen",
            "14 inch\n7th gen",
            "15.6 inch\n5th gen",
            "14 inch\n5th gen",
            "15.6 inch\n5th gen",
            "15.6 inch\n5th gen",
            "15.6 inch\n7th gen",
            "15.6 inch\n5th gen",
            "5MP Camera\n16GB Built-in Memory",
            "5MP Camera\n8GB Built-in Memory",
            "8MP Camera\n8GB Built-in Memory",
            "5MP Camera\n8GB Built-in Memory",
            "5MP Camera\n16GB Built-in Memory",
            "13MP Camera\n8GB Built-in Memory",
            "5MP Camera\n8GB Built-in Memory",
            "13MP Camera\n32GB Built-in Memory",
            "5MP Camera\n8GB Built-in Memory",
            "13MP Camera\n32GB Built-in Memory",
            "5MP Camera\n8GB Built-in Memory",
            "5MP Camera\n8GB Built-in Memory",
            "5MP Camera\n32GB Built-in Memory",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "16MP Image Sensor",
            "1GB RAM\n16GB Memory",
            "1GB RAM\n16GB Memory",
            "1GB RAM\n16GB Memory",
            "1GB RAM\n8GB Memory",
            "1GB RAM\n16GB Memory",
            "2GB RAM\n16GB Memory",
            "1GB RAM\n16GB Memory",
            "2GB RAM\n8GB Memory",
            "2GB RAM\n32GB Memory",
            "16MP Image Sensor",
            "1GB RAM\n32GB Memory",
            "2GB RAM\n32GB Memory",
            "2GB RAM\n32GB Memory",
            "2GB RAM\n32GB Memory",
            "2GB RAM\n8GB Memory",
            "2GB RAM\n8GB Memory",
            "2GB RAM\n8GB Memory"};

    public static String getItemName(int proId){
        return itemnames[proId];
    }

    public static ArrayList<String> getLaptopNames(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 1; i <= 12; i++) {
            arr.add(itemnames[i]);

        }

        return arr;
    }

    public static ArrayList<String> getMobileNames(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 13; i <= 25; i++) {
            arr.add(itemnames[i]);

        }

        return arr;
    }
    public static ArrayList<String> getCameraNames(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 26; i <= 36; i++) {
            arr.add(itemnames[i]);

        }
        arr.add(itemnames[46]);

        return arr;
    }
    public static ArrayList<String> getTabletNames(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 37; i <= 45; i++) {
            arr.add(itemnames[i]);

        }
        for (int i = 47; i <= 51; i++){
            arr.add(itemnames[i]);
        }

        return arr;
    }

    public static String getItemPrice(int proId){
        return itemprices[proId];
    }

    public static ArrayList<String> getLaptopPrices(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 1; i <= 12; i++) {
            arr.add(itemprices[i]);

        }

        return arr;
    }

    public static ArrayList<String> getMobilePrices(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 13; i <= 25; i++) {
            arr.add(itemprices[i]);

        }

        return arr;
    }
    public static ArrayList<String> getCameraPrices(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 26; i <= 36; i++) {
            arr.add(itemprices[i]);

        }
        arr.add(itemprices[46]);

        return arr;
    }
    public static ArrayList<String> getTabletPrices(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 37; i <= 45; i++) {
            arr.add(itemprices[i]);

        }
        for (int i = 47; i <= 51; i++){
            arr.add(itemprices[i]);
        }

        return arr;
    }

    public static String getItemDesc(int proId){
        return itemdesc[proId];
    }

    public static ArrayList<String> getLaptopDescs(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 1; i <= 12; i++) {
            arr.add(itemdesc[i]);

        }

        return arr;
    }

    public static ArrayList<String> getMobileDescs(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 13; i <= 25; i++) {
            arr.add(itemdesc[i]);

        }

        return arr;
    }
    public static ArrayList<String> getCameraDescs(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 26; i <= 36; i++) {
            arr.add(itemdesc[i]);

        }
        arr.add(itemdesc[46]);

        return arr;
    }
    public static ArrayList<String> getTabletDescs(){
        ArrayList<String> arr = new ArrayList<>();
        int j = 0;
        for(int i = 37; i <= 45; i++) {
            arr.add(itemdesc[i]);

        }
        for (int i = 47; i <= 51; i++){
            arr.add(itemdesc[i]);
        }

        return arr;
    }

}


