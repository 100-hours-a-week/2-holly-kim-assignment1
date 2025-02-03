# assignment1 by holly.kim

1번째 과제

### 실행방법
Main.java 실행  

### 클래스 다이어그램 
<img width="480" alt="스크린샷 2025-02-03 오후 9 46 54" src="https://github.com/user-attachments/assets/7ca1b0f2-a3ed-4684-ab97-0c9590adc2e5" />


<img width="344" alt="스크린샷 2025-02-03 오후 9 47 12" src="https://github.com/user-attachments/assets/2023a40e-8be7-4ee8-817a-4f98d9c329b4" />

<img width="321" alt="스크린샷 2025-02-03 오후 9 47 05" src="https://github.com/user-attachments/assets/d4041fce-f331-470b-bbb2-b1793bb53ee1" />


### 서비스 설명

햄버거 가게 McDonald’s 주문 서비스를 구현했습니다.

메인메뉴 선택-세트메뉴 여부 선택-(세트메뉴 추가 상품 선택) - 음료메뉴 선택 - 수량 선택 으로 이루어져있습니다.

상품 선택 후 계속 주문을 할지 선택하여 다른 상품도 주문할 수 있습니다.

### 구현 설명

Item←MainItem←PromotionItem 으로 2차 상속을 구현했습니다.

Scanner를 여러 곳에서 사용하면 생기는 문제를 방지하기 위해 getScanner()를 구현했습니다. 

메인메뉴 목록(모든 메인메뉴), 추가메뉴 목록, 음료 목록에서 각각 번호를 매기기 위해 static int cnt를 선언했습니다. 

세트메뉴 추가상품메뉴와 음료메뉴는 둘 다 AdditionalItem객체이므로 static int 변수인 cnt 값을 0으로 리셋하기 위해 resetCount() 함수를 구현했습니다.
