package store;

public class MainItem extends Item{
    public static int cnt;
    int id;

    public MainItem(String name, int price){
        super(name, price);
        this.id=++cnt;
    }

    public MainItem() {
        super();
    }

    public void displayMenu(){
        System.out.println(String.format("Main Menu %2d. %s / %,d won ", id, name, price));
    }
}
