package store;

public class AdditionalItem extends MainItem{
    public static int cnt=0; // shadowing(재할당)
    public AdditionalItem(String name, int price) {
        this.name=name;
        this.price=price;
        this.id=++cnt;
    }
    public static void resetCounter(){
        cnt=0;
    }
    public void displayMenu(){
        System.out.println(String.format("%d. %s (+%,d won)", this.id, name, price));
    }
}
