package Store;

public class MainItem extends Item{
    public static int mainCnt;

    public MainItem(String name, int price){
        super(name, price);
        this.id=++mainCnt;
    }

    public void displayMenu(){
        System.out.println(String.format("Main Menu %2d. %s / %,d won ", id, name, price));
    }
}
