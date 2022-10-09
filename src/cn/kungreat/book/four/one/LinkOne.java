package cn.kungreat.book.four.one;

import java.util.Map;

public class LinkOne {

    static void one(Map map){
        System.out.println(map.get("LINK"));
        LinkTwo.two(map);
    }
}
