package Store;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        store.welcome();

        Scanner scanner = store.getScanner();
        boolean ordering = true;
        while (ordering) {
            store.mainOrder(); // 메인 메뉴 주문
            store.selectSet(); // 세트 메뉴 여부 선택
            store.drinkOrder(); // 음료 주문
            store.selectQuantity(); // 해당 상품 수량 선택

            System.out.print("추가 주문하시겠습니까? (Y / N): ");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("n")) {
                ordering = false;
                System.out.println("주문을 종료합니다.");
                System.out.println("--------------------------------------------------------------------------");
            } else if (answer.equalsIgnoreCase("y")) {
                System.out.println("장바구니에 담겼습니다.");
                System.out.println("--------------------------------------------------------------------------");
            } else {
                System.out.print("다시 입력해주세요. (Y / N) : ");
            }
        }
        store.pay();
        store.closeScanner();
        store.goodbye();
    }
}