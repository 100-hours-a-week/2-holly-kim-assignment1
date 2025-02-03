package Store;

public abstract class Item {
    String name;
    int price;
    int id;

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Item() {
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
