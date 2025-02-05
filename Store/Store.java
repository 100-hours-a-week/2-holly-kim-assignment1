package Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import java.util.Optional;


public class Store {
    private final Scanner scanner = new Scanner(System.in);
    private final TotalPrice payment = new TotalPrice();
    private List<Item> purchasedList = new ArrayList<>();
    private double discountRate = 0;

    private static List<MainItem> mainMenuList;
    private static List<PromotionItem> newMenuList;
    private static List<AdditionalItem> sideMenuList;
    private static List<AdditionalItem> drinkList;
    // 모든 메인 메뉴를 포함하는 리스트 생성
    private static List<MainItem> allMainMenuList = new ArrayList<>();

    static {
        mainMenuList = List.of(
                new MainItem("빅맥", 4500),
                new MainItem("맥스파이시 상하이 버거", 5000),
                new MainItem("의성마늘버거", 5500),
                new MainItem("쿼터파운드치즈", 6000),
                new MainItem("최고급홀리버거", 9000)
        );
        newMenuList = List.of(
                new PromotionItem("치즈할라피뇨더블쿼터파운드치즈", 6300),
                new PromotionItem("치즈할라피뇨쿼터파운드치즈", 6500),
                new PromotionItem("독점공개신상버거", 7000, 0.2)
        );
        sideMenuList = List.of(
                new AdditionalItem("감자튀김 S", 0),
                new AdditionalItem("감자튀김 L", 500)
        );
        drinkList = List.of(
                new AdditionalItem("콜라", 0),
                new AdditionalItem("사이다", 0),
                new AdditionalItem("바닐라 쉐이크", 500),
                new AdditionalItem("오렌지 주스", 500)
        );
    }

    public void deleteItem(List<? extends Item> list, int id) {
        list.remove(id);
        setItemId(list);
    }

    public <T extends Item> void addItem(List<T> list, T item) {
        list.add(item);
        setItemId(list);
    }


    public void setItemId(List<? extends Item> list) {
        int id = 1;
        for (Item item : list) {
            item.setId(id++);
        }
    }

    public void welcome() {
        allMainMenuList.addAll(mainMenuList);
        allMainMenuList.addAll(newMenuList);
        setItemId(allMainMenuList);
        setItemId(sideMenuList);
        setItemId(drinkList);

        // 리스트 수정
        AdditionalItem doctorPepper=new AdditionalItem("닥터페퍼",1000);
        addItem(drinkList, doctorPepper);
        // 리스트 삭제, 인덱스 값은 0부터 시작에 주의!
        deleteItem(mainMenuList, 4);
        System.out.println("======================== Welcome to McDonald's ===========================");
        popup();
        System.out.println("주문을 도와드리겠습니다.");
        System.out.println("--------------------------------------------------------------------------");
    }

    public void popup() {
        System.out.println("**************************************************************************");
        System.out.println("현재 진행중인 프로모션 안내드립니다.");
        System.out.println("신메뉴를 기간 한정으로 할인하고 있습니다.\n(단품/세트 모두 해당 버거별 일괄 할인율 적용)");
        System.out.println("신메뉴 목록은 다음과 같습니다.");
        printPromotionBurger();
        System.out.println("**************************************************************************");
    }

    private void printPromotionBurger() {
        for (PromotionItem i : newMenuList) {
            System.out.println(String.format("✯ %s (원가 %,d won) -> 구매 시 해당 단품, 세트 %d %% 할인 쿠폰 증정!",
                    i.getName(),
                    i.getPrice(),
                    (int) (i.getPromotionRate() * 100)));
        }
    }


    public void mainOrder() {
        payment.resetCurrentPrice();
        purchasedList = new ArrayList<>();

        System.out.println("❇ 메인 메뉴");
        System.out.println("메뉴를 선택해주세요.\n");
        for (MainItem i : mainMenuList) {
            i.displayMenu();
        }
        for (PromotionItem i : newMenuList) {
            i.displayMenu();
        }
        // 선택한 버거가 할인 버거인 경우 discountRate 변경
        String burger = selectMenu(allMainMenuList);
        for (PromotionItem i : newMenuList) {
            if (burger.equals(i.getName())) {
                discountRate = i.getPromotionRate();
            }
        }
    }

    public void selectSet() {
        System.out.println("❇ 세트로 하시겠습니까?\n");
        System.out.print("세트 여부 입력(Y / N) : ");
        while (true) {
            String isSet = scanner.nextLine();
            if (isSet.equalsIgnoreCase("Y")) {
                System.out.println("세트 메뉴를 선택하셨습니다.");
                System.out.println("--------------------------------------------------------------------------");
                sideOrderSize();
                break;
            } else if (isSet.equalsIgnoreCase("N")) {
                System.out.println("세트 메뉴를 선택하지 않았습니다. 단품으로 주문되었습니다.");
                System.out.println("--------------------------------------------------------------------------");
                break;
            } else {
                System.out.print("다시 입력해주세요. (Y / N) : ");
            }
        }
    }

    public void sideOrderSize() {
        System.out.println("❇ 세트 사이드 메뉴를 선택해주세요.\n");
        for (AdditionalItem i : sideMenuList) {
            i.displayMenu();
        }
        selectMenu(sideMenuList);
    }

    public void drinkOrder() {
        System.out.println("❇ 음료를 선택해주세요.\n");
        for (AdditionalItem i : drinkList) {
            i.displayMenu();
        }
        selectMenu(drinkList);
    }

    public String selectMenu(List<? extends Item> menuList) {
        System.out.print("메뉴 번호 입력: ");
        while (true) {
            try {
                String inputData = scanner.nextLine();
                int selectedNum = Integer.parseInt(inputData);
                if (selectedNum > menuList.size() || selectedNum < 1) {
                    System.out.println("잘못된 번호입니다. 다시 선택하세요.");
                    continue;
                }

                Item selectedMenu = menuList.get(selectedNum - 1);
                System.out.printf("%d번 %s 상품을 선택하셨습니다.%n", selectedNum, selectedMenu);
                addToCart(selectedMenu);
                System.out.println("--------------------------------------------------------------------------");
                return selectedMenu.getName();
            } catch (NumberFormatException e) {
                System.out.print("숫자를 입력하세요.");
            }
        }
    }

    private void addToCart(Item selectedMenu) {
        payment.add(selectedMenu.getPrice());
        purchasedList.add(selectedMenu);
    }

    public void pay() {
        int remainingAmount = payment.getTotalPrice();
        System.out.printf("지불해야 할 총 금액은 %,d won 입니다.%n", remainingAmount);

        while (remainingAmount > 0) {
            System.out.print("얼마를 지불하시겠습니까? : ");
            try {
                int paymentAmount = Integer.parseInt(scanner.nextLine());
                if (paymentAmount < 0) {
                    System.out.println("금액은 0 이상이어야 합니다. 다시 입력해주세요.");
                    continue;
                } else if (paymentAmount > remainingAmount) {
                    System.out.println("입력한 금액이 지불할 금액보다 큽니다. 다시 입력해주세요.");
                    continue;
                }
                remainingAmount -= paymentAmount;
                if (remainingAmount > 0) {
                    System.out.printf("남은 금액: %,d won%n", remainingAmount);
                } else {
                    System.out.println("결제가 완료되었습니다.");
                    System.out.println("--------------------------------------------------------------------------");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
    }

    public void selectQuantity() {
        System.out.println("현재 해당 상품의 정보를 확인해드리겠습니다.");
        for (Item i : purchasedList) {
            System.out.println(String.format("%s (%,d won)", i, i.getPrice()));
        }
        System.out.println(String.format("현재 보고있는 상품의 총 금액: %,d won", payment.getCurrentPrice()));
        System.out.println("할인 쿠폰은 최종 결제 단계에서 적용됩니다.");
        while (true) {
            try {
                System.out.print("해당 상품을 몇 개 주문하시겠습니까? (1 이상의 숫자 입력): ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    System.out.println("1 이상의 숫자를 입력해주세요.");
                    continue;
                }
                payment.multiply(quantity, discountRate);
                discountRate = 0;
                break;
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    public void closeScanner() {
        scanner.close();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void goodbye() {
        System.out.println("================== Thank you for visiting McDonald's =====================");
    }


}

