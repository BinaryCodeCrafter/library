package com.hamada.library;

import com.hamada.library.domain.BookEntity;

public final class testData {


    private testData(){}

    public static BookEntity testBookEntity(){

        return new BookEntity(
                1L, "The Robots Are Coming" , "Ali Ahmed" , "08332654" ,
                2003 , "Horror");
    }
}
