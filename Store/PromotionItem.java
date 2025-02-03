package Store;

public class PromotionItem extends MainItem {
    private double promotionRate = 0.1;

    PromotionItem(String name, int price, double promotionRate) {
        super(name, price);
        this.promotionRate = promotionRate;
    }

    PromotionItem(String name, int price) {
        super(name, price);
    }

    public double getPromotionRate() {
        return promotionRate;
    }

    public void displayMenu() {
        System.out.println(String.format("Main Menu %2d. %s / %,d won (해당 버거 %d %% 할인 쿠폰 지급)", id, name, price, (int)(promotionRate*100)));
    }


}
