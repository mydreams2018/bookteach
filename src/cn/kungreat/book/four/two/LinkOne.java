package cn.kungreat.book.four.two;


public class LinkOne {

    static void one(){
        System.out.println(TwoMain.LINK.get());
        System.out.println(TwoMain.LINKTWO.get());
        LinkTwo.two();
    }
}
