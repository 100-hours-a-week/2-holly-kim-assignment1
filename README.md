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

Scanner 객체가 중복 생성되는 것을 막기 위해 getScanner()를 구현했습니다. 


###  변경 사항
2025.02.05



1. static int cnt->동시성 문제/단위테스트 어려움 유발 가능  
static int cnt 변수를 삭제하고 Store.java에서 id를 초기화하는 함수를 작성. 한 번만 실행되도록 welcome()에서 이 함수를 호출.



3. 메뉴 수정 시 id값 관리의 어려움  
메뉴리스트와 id를 인자로 수정하거나 삭제하는 함수를 구현



4. selectMenu()에서 많은 역할을 수행->단일 책임  
메뉴를 선택하는 부분과 금액을 계산하는 부분으로 분리



5. selectMenu()에서 입력값 유효성 검사 조건 변경  
selectedNum<0 -> selectedNum<1
