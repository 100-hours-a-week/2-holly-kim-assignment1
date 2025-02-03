package store;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import java.util.Optional;


public class Store {
    private Scanner scanner = new Scanner(System.in);
    private String inputData;
    private TotalPrice payment = new TotalPrice();
    private List<Item> purchasedList = new ArrayList<>();

    private static final List<MainItem> mainMenuList;
    private static final List<AdditionalItem> sideMenuList;
    private static final List<AdditionalItem> drinkList;

    static {
        mainMenuList = List.of(
                new MainItem("빅맥", 4500),
                new MainItem("맥스파이시 상하이 버거", 5000),
                new MainItem("의성마늘버거", 5500)
        );
        sideMenuList = List.of(
                new AdditionalItem("감자튀김 S", 0),
                new AdditionalItem("감자튀김 L", 500)
        );
        AdditionalItem.resetCounter();
        drinkList = List.of(
                new AdditionalItem("콜라", 0),
                new AdditionalItem("사이다", 0),
                new AdditionalItem("바닐라 쉐이크", 500),
                new AdditionalItem("오렌지 주스", 500)
        );
    }

    public void welcome() {
        System.out.println("====================");
        System.out.println("Welcome to McDonald's");
        System.out.println("주문을 도와드리겠습니다.");
        System.out.println("---------------------");
    }


    public void mainOrder() {
        payment.resetCurrentPrice();
        purchasedList = new ArrayList<>();

        System.out.println("❇ 메인 메뉴");
        System.out.println("메뉴를 선택해주세요.\n");
        for (MainItem i : mainMenuList) {
            i.displayMenu();
        }
        selectMenu(mainMenuList);
    }

    public void selectSet() {
        System.out.println("❇ 세트로 하시겠습니까?\n");
        System.out.print("세트 여부 입력(Y / N) : ");
        while (true) {
            String isSet = scanner.nextLine();
            if (isSet.equalsIgnoreCase("Y")) {
                System.out.println("세트 메뉴를 선택하셨습니다.");
                System.out.println("---------------------");
                sideOrderSize();
                break;
            } else if (isSet.equalsIgnoreCase("N")) {
                System.out.println("세트 메뉴를 선택하지 않았습니다. 단품으로 주문되었습니다.");
                System.out.println("------------------------");
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

    public void selectMenu(List<? extends Item> menuList) {
        System.out.print("메뉴 번호 입력: ");
//        Optional<Item> optional=mainMenuList.stream().filter(i->i.getId()==selectedNum).findAny();
//        if(optional.isPresent()){ }
        while (true) {
            try {
                inputData = scanner.nextLine();
                int selectedNum = Integer.parseInt(inputData);

                if (selectedNum > menuList.size() || selectedNum < 0) {
                    System.out.println("잘못된 번호입니다. 다시 선택하세요.");
                    continue;
                }
                Item selectedMenu = menuList.get(selectedNum - 1);
                System.out.printf("%d번 %s 상품을 선택하셨습니다.%n", selectedNum, selectedMenu);
                payment.add(selectedMenu.getPrice());
                purchasedList.add(selectedMenu);
                System.out.println("---------------------");
                break;
            } catch (NumberFormatException e) {
                System.out.print("숫자를 입력하세요.");
            }
        }
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
                    System.out.println("---------------------");
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
        while (true) {
            try {
                System.out.print("해당 상품을 몇 개 주문하시겠습니까? (1 이상의 숫자 입력): ");
                int quantity = Integer.parseInt(scanner.nextLine());
                if (quantity < 1) {
                    System.out.println("1 이상의 숫자를 입력해주세요.");
                    continue;
                }
                payment.multiply(quantity);
                break;
            } catch (NumberFormatException e) {
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
        System.out.println("----------------------------");
    }

    public void closeScanner() {
        scanner.close();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void goodbye() {
        System.out.println("Thank you for visiting McDonald's");
        System.out.println("====================");
    }


}

