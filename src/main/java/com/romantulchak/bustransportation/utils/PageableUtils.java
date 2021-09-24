package com.romantulchak.bustransportation.utils;

public class PageableUtils {

    private PageableUtils(){}

    public static int getCorrectPage(int page){
        if(page == 0){
            return page;
        }
        return page - 1;
    }
}
