package com.jlu.jtee;
import com.jlu.jtee.domain.Option;
import org.junit.Test;

import java.util.ArrayList;

public class Map {

    @Test
    public void testMap(){
        ArrayList<String> places = new ArrayList<String>();
            ArrayList<Option> places2 = new ArrayList<>();

        places.add("A,B");

        Option a =new Option("44",places);
        Option b = new Option("34",places);
        places2.add(a);
        places2.add(b);
        System.out.println(places2.toString());
    }
}
