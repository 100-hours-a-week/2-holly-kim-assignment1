package Store;

public class AdditionalItem extends Item{
    public static int additionalCnt =0;
    public AdditionalItem(String name, int price) {
        this.name=name;
        this.price=price;
        this.id=++additionalCnt;
    }
    public static void resetCounter(){
        additionalCnt =0;
    }
    public void displayMenu(){
        System.out.println(String.format("%d. %s (+%,d won)", this.id, name, price));
    }
}
