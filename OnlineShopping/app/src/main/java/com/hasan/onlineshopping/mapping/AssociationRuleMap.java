package com.hasan.onlineshopping.mapping;

import java.util.ArrayList;

/**
 * Created by Nayeem Hasan on 22-Jan-18.
 */

public class AssociationRuleMap {
    public ArrayList<Integer> ant;
    public ArrayList<Integer> con;
    public Double confidence;

    public AssociationRuleMap(){
        ant = new ArrayList<>();
        con = new ArrayList<>();
    }

    public String getStr(){
        String s  = String.valueOf(ant.size())+String.valueOf(con.size())+String.valueOf(confidence);
        return s;
    }
}
