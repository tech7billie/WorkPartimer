package com.company.run;

import com.company.action.*;
import com.company.data.Cafe;
import com.company.data.User;
import com.company.thread.*;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

// 게임의 메인 흐름을 담은 클래스(최초실행, 평일과 주말)
public class GameRun
{
    // 최초 실행하는 메소드
    public void initialRun()
    {
        // BGM 재생
        Sound sound = new Sound("startBGM.mp3", true);
        sound.start();

        // 타이틀 출력
        PrintAction printAction = new PrintAction();
        printAction.printTitle();

        boolean check;   // 반복 여부를 체크하기 위한 변수
        String userName;        // 유저이름을 저장할 변수

        do {
            System.out.print(" 이름을 입력해주세요(한글만 가능) : ");
            Scanner sc = new Scanner(System.in);
            userName = sc.nextLine();                                   // 유저이름을 입력받고
            userName = userName.replaceAll(" ", "");    // 공백을 제거하고
            check = Pattern.matches("^[가-힣]*$", userName);       // 정규표현식을 이용해서 한글인지 확인한다. 한글인 경우 true 반환

        }while (!check); // 입력받은 이름이 한글이 아니면 반복

        User.setName(userName);         // 입력받은 유저이름 속성에 넣기


        String selectStr;       // 사용자가 입력한 값을 담을 변수
        int select = 0;         // selectStr 변수를 형변환해서 담을 변수 


        // 스캐너 객체 생성
        Scanner sc = new Scanner(System.in);

        while (check)            // 올바른 선택지를 선택할 때까지 반복한다.
        {
            //test
            //Thread.sleep(10000);  // 메인쓰레드가 10초 멈춘다.

            System.out.println("========================================================================");
            System.out.println(" 1.시작하기  2.이름 재설정 ");
            System.out.println("------------------------------------------------------------------------");
            System.out.print(" 선택 : ");
            selectStr = sc.nextLine();

            // 입력받은 값이 숫자인지 확인
            try
            {
                // 자료형 변경한 뒤(String → int) int형에 담는다.
                // int형으로 변경되지 않는다면 NumberFormatException 발생
                select = Integer.parseInt(selectStr);
                check = false;

                if (select < 1 || select > 2)// 주어진 값 이외의 수를 선택한 경우
                {
                    System.out.println("========================================================================");
                    System.out.println(" 올바른 값을 입력해주세요.");
                    check = true;
                }

            } catch (NumberFormatException e) // NumberFormatException 발생한다면
            {
                System.out.println("========================================================================");
                System.out.println(" 올바른 값을 입력해주세요.");
                check = true;   // check 에 true 담아서 다시 반복
            }

        }


        final int START = 1;                // 게임 시작
        final int RENAME = 2;               // 이름 재설정

        while (true)
        {
            switch (select)              // 유저의 선택값에 따라 분기 처리
            {
                case START:                            // 1. 시작하기를 선택한 경우
                    GameRun gameRun = new GameRun();
                    gameRun.intro();                  // 게임 시작하는 메소드 호출
                    break;

                case RENAME:                         // 2. 이름 재설정 선택한 경우
                    sound.finish();                  //    노래쓰레드 멈추고
                    initialRun();                    //    초기실행 메소드 호출
                    break;
            }
        }

    }// end initialRun()

    // 게임 시작 메소드
    public void intro()
    {

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();


        // 인트로 출력
        PrintAction printAction = new PrintAction();
        printAction.printIntro();

        introSelect();
    }

    // 인트로 출력 후 선택지 제공하는 메소드
    public void introSelect()
    {
        String selectStr;       // 사용자의 선택값을 담을 변수
        int select = 0;         // selectStr 변수의 값을 int 로 형변환해서 담을 변수
        boolean check = true;   // 반복여부 체크할 변수

        // 올바른 선택지를 선택할 때까지 반복
        while(check) // check 는 위에서 입력받은 유저이름이 한글인걸 확인했기 때문에 true 인 상황이다.
        {
            System.out.println("========================================================================");
            System.out.println(" 1.카페 열기  2.공개된 엔딩 보기  3.끝내기  ");
            System.out.println("------------------------------------------------------------------------");
            System.out.print(" 선택 : ");
            Scanner sc = new Scanner(System.in);
            selectStr = sc.nextLine();

            // 입력받은 값이 숫자인지 확인
            try
            {
                // 자료형 변경한 뒤(String → int) int형에 담는다.
                // int 형으로 변경되지 않는다면 NumberFormatException 발생
                select = Integer.parseInt(selectStr);
                check = false;

            }
            catch (NumberFormatException e) // NumberFormatException 발생한다면
            {
                check = true;   // check 에 true 담아서 다시 반복
                // result = 0; 으로 초기화된 상태이므로  하단 if문 내부까지 실행하고 반복된다.
            }

            if(select < 1 || select > 3 )// 주어진 값 이외의 수를 선택한 경우
            {
                System.out.println("========================================================================");
                System.out.println(" 올바른 값을 입력해주세요.");
                check = true;
            }
        }


        final int OPEN = 1;             // 1. 카페 열기
        final int OPEN_ENDINGS = 2;     // 2. 인트로 다시 보기
        final int EXIT = 3;             // 3. 끝내기

        GameRun gameRun = new GameRun(); // Cafe 객체 생성

        while(true)
        {
            switch(select)              // 유저의 선택값에 따라 분기 처리
            {
                case OPEN:              // 1. 카페 열기를 선택한 경우D
                    gameRun.open();     // 게임 시작하는 메소드 호출
                    break;

                case OPEN_ENDINGS:      // 2. 공개된 엔딩보기를 선택한 경우
                    Ending ending = new Ending();
                    ending.openEndings();           // 공개된 엔딩 확인 메소드 호출
                    break;

                case EXIT :             // 3. 끝내기를 선택한 경우
                    System.exit(0); // 프로그램 종료
                    break;
            }
        }

    } //end start()

    // 카페 오픈 메소드(하루 단위, 평일): 몇주차 며칠인지 보여줌
    public void open()
    {

        String[] days = {"월", "화", "수", "목","금","토"};   // 요일 배열. 토요일은 평일과 주말을 구분하기 위해 존재한다.
        String day = days[User.getWorkingDays() % 6];       // 요일 = 일한일수%6
        // 일한 일수는 0에서부터 시작한다. 하루가 지날 때마다 일한일수가 1씩 증가한다.
        // 0%6 == 0 일 때 월
        // 1%6 == 1 일 때 화
        // 2%6 == 2 일 때 수
        // 3%6 == 3 일 때 목
        // 4%6 == 4 일 때 금
        // 5%6 == 5 일 때 토

        int week = (User.getWorkingDays() / 6) + 1;
        // 첫 주(월~토)에 연산결과가 0이 되므로 '1주차' 부터 시작하기 위해서 1을 더해준다.

        try
        {
            if(!day.equals("토"))    // 평일이라면
            {
                User.setWorkingDays(User.getWorkingDays() + 1);  // 지금까지 일한 일수에 하루를 더해준다.

                Cafe.setTodayCustomerNum(0);                // 하루 방문자 수 0으로 초기화

                Cafe.setChairNum(Cafe.getSetChairNum());          // 현재 카페의 의자 수를 세팅된 값으로 초기화
                Cafe.setCupNum(Cafe.getSetCupNum());              // 현재 유리컵 수를 세팅된 값으로 초기화
                Cafe.setMugNum(Cafe.getSetMugNum());              // 현재 머그잔 수를 세팅된 값으로 초기화

                System.out.println();
                System.out.println();
                System.out.println();
                Thread.sleep(500);
                System.out.println("                         ╔═══━━━─────────━━━═══╗                         ");
                System.out.printf("=========================     %d주차     %s요일    =========================\n", week, day);
                System.out.println("                         ╚═══━━━─────────━━━═══╝                         ");
                System.out.println();
                Thread.sleep(500);
                System.out.println("                         ✨ 카페를 열었습니다 ✨ ");
                System.out.println();


                work(); // 아르바이트하는 메소드 호출

                // test
                selectWork();   // 선택지

            }
            else                // 토요일이라면
            {
                Thread.sleep(500);
                System.out.println();
                System.out.println("                         ✨ 주말이 되었습니다 ✨ ");
                System.out.println();

                weekendInfo();  // 해당 주의 아르바이트 결과를 정산하는 메소드 호출

            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }// end start()


    // 아르바이트하는 메소드
    public void work()
    {
        // 손님수 표현하기 위한 배열, 최대 4주차에서 게임이 끝나기 때문에 최대 숙련도는 5이다.
        // 하루에 방문하는 최대 손님수는 숙련도와 동일하므로 다섯까지 존재
        String[] nums = {"첫", "두", "세", "네", "다섯"};

        try
        {
            Thread.sleep(1000);

            System.out.println("------------------------------------------------------------------------");
            System.out.println("                        " + nums[Cafe.getTodayCustomerNum()] + "번째 손님이 등장했습니다.");
            System.out.println("------------------------------------------------------------------------");

            // 손님 유형 랜덤으로 등장시키기.
            Random rd = new Random();               // 랜덤클래스 객체 생성
            int randomNum = rd.nextInt(10) + 1; // 1 ~ 10 사이의 랜덤값을 생성해서 변수에 담는다.

            if (randomNum <= 6)                       // 랜덤값이 1 ~ 6 인 경우
            {
                // 일반 손님이 등장하는 메소드 호출
                CustomerAction customerAction = new CustomerAction();
                customerAction.comeCustomer();


            } else if (randomNum == 7 || randomNum == 8|| randomNum ==9 )     // 랜덤값이 7,8,9인 경우
            {
                // 특별 손님이 등장하는 메소드 호출
                SpecialCustomerAction specialCustomerAction = new SpecialCustomerAction();
                specialCustomerAction.comeSpecialCustomer();

            } else
            {
                // 비밀 손님이 등장하는 메소드 호출
                SecretCustomerAction secretCustomerAction = new SecretCustomerAction();
                secretCustomerAction.comeSecretCustomer();
            }


            // 랜덤으로 불청객 등장시키기
            randomNum = rd.nextInt(10)+1;   // 1~10 사이의 랜덤수

            if(randomNum <= 2)   // 1 ~2인 경우
            {
                BattleAction battleAction = new BattleAction();
                battleAction.comeBug();
            }

            selectWork();                          // 선택지 고르는 메소드 호출(1. 계속하기 2. 마감하기 3.아이템 사용)

        } catch (Exception e)
        {
           System.out.println(e.toString());
        }

    }// end work()

    // 한 주문을 처리한 뒤 등장하는 선택지 메소드
    public void selectWork()
    {
        boolean check;      // 아르바이트 반복 여부 체크하는 변수
        int result = 0;     // 주어진 값 외의 수를 입력했는지 비교할 때 사용할 변수

        final int KEEP = 1; // 손님 계속 받는 선택지
        final int STOP = 2; // 손님 그만받는 선택지
        final int ITEM = 3; // 아이템 사용하는 선택지

        do {

            System.out.println(" 다음 손님을 받을까요? ");
            System.out.println(" 1. 계속하기 2. 마감하기 3.아이템 사용");
            System.out.println("------------------------------------------------------------------------");

            System.out.print(" 선택 : ");
            Scanner sc = new Scanner(System.in);
            String resultStr = sc.nextLine();   // 유저가 입력한 값을 resultStr 변수에 담는다.

            // 입력받은 값이 숫자 형태인지 확인
            try
            {
                // 입력받은 문자열의 공백을 제거하고
                // 자료형 변경한 뒤(String → int) int 형 변수에 담는다.
                result = Integer.parseInt(resultStr.replace(" ", ""));
                check = false;
                // int 형으로 변경되면 check 에 false 를 담고 반복문을 빠져나간다.
                // int형으로 변경되지 않는다면 NumberFormatException 발생한다.
            }
            catch (NumberFormatException e) // NumberFormatException 발생한다면
            {
                check = true;   // check 에 true 담아서 다시 반복
                                // result = 0; 으로 초기화된 상태이므로 아래 if 문을 실행하고 반복된다.
            }

            if(result < 1 || result >3)// 주어진 값 이외의 수를 선택한 경우
            {
                System.out.println("========================================================================");
                System.out.println(" 올바른 값을 입력해주세요.");
                check = true;   // 반복한다.
            }
            else if(result == KEEP && Cafe.getTodayCustomerNum() == User.getSkillLevel()) // 계속하기를 선택했는데 숙련도와 방문한 손님 수가 같으면
            {
                System.out.println("========================================================================");
                System.out.printf(" %s님의 숙련도가 낮아 더이상 손님을 받을 수 없습니다. \n", User.getName());
                result = 2;     // 손님을 그만 받는 선택지를 택하고
                check = false;  // 반복문을 빠져나간다.
            }

        }while (check);

        while(true)
        {
            switch(result)  // 위에서 선택한 값에 따라서 해당하는 메소드를 호출한다
            {
                case KEEP :                 // 계속 아르바이트한다.

                    work();
                    break;

                case STOP:                  // 다음날로 시간이 흐른다.
                    System.out.println();
                    System.out.println();
                    //System.out.println(" ☾ ⋆*･ﾟ ⋆*･ﾟ ⋆. ･ﾟ. ⋆ * ･ﾟ. ⋆⋆ *･ﾟ⋆*･ﾟ ⋆ . ･ﾟ .⋆*･ﾟ .⋆ ⋆*･ﾟ ⋆*･ﾟ ⋆･ﾟ⋆ *･ﾟ ⋆･ﾟ");

                    PrintAction printAction = new PrintAction();
                    printAction.printAfterDay();

                    System.out.println();
                    System.out.println();
                    open();                // 하루 시작하는 메소드 호출
                    break;

                case ITEM :                 // 보유한 아이템을 보여주는 메소드 호출
                    UserAction userAction = new UserAction();
                    userAction.myItem();
                    break;
            }
        }
    }

    // 주말 정산 메소드
    public void weekendInfo()
    {

        int week = (User.getWorkingDays() /6) + 1;
        // 토요일이 될 때 주차를 계산하면(일한 일수/요일배열 길이) 한 주 적게 나오기 때문에 1을 더해준다.
        // 첫번째 토요일 : 5/6 == 0
        // 두번째 토요일 : 11/6 == 1

        // 코인 제공 조건 만족하면
        // 조건 : 이번주 성공횟수 > 이번주 실패횟수

        try{

            if(User.getWeekSuccessNum() > User.getWeekFailNum())
            {
                // 전재산 = 현재 전재산 + 제공받는 급여코인(숙련도 * 2)
                User.setProperty(User.getProperty() + User.getSkillLevel() * 2);

                Thread.sleep(500);
                System.out.printf("                        ✨ %d코인을 획득했습니다 ✨\n", User.getSkillLevel() * 2);

            }
            else    // 조건 만족하지 못하면
            {
                Thread.sleep(500);
                System.out.println("                        코인을 획득하지 못했습니다! ");

            }
            System.out.println();

            // 숙련도 증가 조건 만족하면
            // 조건 : 숙련도 * 4 <= 누적 음료 제조 성공 횟수
            if(User.getSkillLevel()*4 <= User.getTotalSuccessNum())
            {
                User.setSkillLevel(User.getSkillLevel()+1);     // 숙련도 1 증가

                Thread.sleep(500);
                System.out.println("                      ✨ 숙련도가 1 증가했습니다 ✨");

            }
            else    // 조건 만족하지 못하면
            {
                Thread.sleep(500);
                System.out.println("                         숙련도에 변동이 없습니다! ");
            }
            System.out.println();

            Thread.sleep(500);


        }catch (Exception e){
            System.out.println(e.toString());
        }


        System.out.println("                         ╔═══━━━─────────━━━═══╗                         ");
        System.out.printf("=========================       %d주차 정산       =========================\n", week);
        System.out.println("                         ╚═══━━━─────────━━━═══╝                         ");
        System.out.println();
        System.out.printf(" 이번주 상대한 손님 수 : %d \n", Cafe.getWeekCustomerNum());
        System.out.printf(" 이번주 음료제조에 성공한 횟수 : %d \n", User.getWeekSuccessNum());
        System.out.printf(" 이번주 음료제조에 실패한 횟수 : %d \n", User.getWeekFailNum());
        System.out.println();
        System.out.println("------------------------------------------------------------------------");
        System.out.println();
        System.out.printf(" 총 상대한 손님 수 : %d \n", Cafe.getTotalCustomerNum());
        System.out.printf(" 총 음료제조에 성공한 횟수 : %d \n", User.getTotalSuccessNum());
        System.out.printf(" 총 음료제조에 실패한 횟수 : %d \n", User.getTotalFailNum());
        System.out.println();
        System.out.println("------------------------------------------------------------------------");
        System.out.println();
        System.out.printf(" 보유한 코인 : %d\n", User.getProperty());
        System.out.println();

        // 정산 출력 후
        Cafe.setWeekCustomerNum(0);                // 주 방문자수 0으로 초기화
        User.setWeekSuccessNum(0);                 // 주 음료제조 성공횟수 0으로 초기화
        User.setWeekFailNum(0);                    // 주 음료제조 실패횟수 0으로 초기화

        // 엔딩 주석처리


        // 엔딩 호출
        Ending ending = new Ending();                   // 엔딩객체 생성

        if(week >= 3)  // 3주차 이상이고
        {
            if(Cafe.getTotalCustomerNum() >= 30 && User.getProperty() >= 10)// 총 방문자 수가 30명 이상이고 코인 10개 이상일 때
            {
                ending.bossEnding();                    // 사장 엔딩 호출
            }

            else if(SecretCustomerAction.getSecretCustomerCnt() >= 6) // 비밀 손님 방문 횟수가 6회 이상이면
            {
                ending.scoutEnding();                        // 이직 엔딩 호출
            }
        }

        if(week == 4)                                  // 4주차이고 앞선 조건에 부합하지 않으면
        {
            ending.partimerEnding();                        // 알바 엔딩 호출
        }


        weekend(); // 주말 선택지 메소드 호출(1. 정보 확인  2.상점가기  3.회복하기  4. 주말 지나가기)
    }

    // 주말 선택지  메소드(정보 확인 가능, 상점 이용 가능, 아이템 사용 가능)
    public void weekend()
    {
        boolean check = true;   // 반복여부 체크하는 변수
        String resultStr;       // 사용자의 선택값을 담을 변수(1. 정보 확인  2.상점가기 3.모은 엔딩 확인)
        int result = 0;         // resultStr 를 int 형으로 변환한 사용자의 선택값을 담을 변수

        final int INFO = 1;     //1. 정보 확인 - 1.내 정보 확인 2.카페 정보 확인 3.이전 화면
        final int SHOP = 2;     //2. 상점가기 - 1.영구 아이템  2.소모 아이템  3.이전 화면
        final int RECOVERY = 3; //3. 회복하기 - 10초뒤 체력, 인내력  회복
        final int SKIP = 4;     //4. 주말 지나가기

        while(check)
        {
            System.out.println("========================================================================");
            System.out.println(" 1. 정보 확인  2.상점가기  3.회복하기 4.주말 지나가기");
            System.out.println("------------------------------------------------------------------------");
            System.out.print(" 선택 : ");
            Scanner sc = new Scanner(System.in);
            resultStr = sc.nextLine();

            // 입력받은 값이 숫자인지 확인
            try
            {
                // 자료형 변경한 뒤(String → int) int 형 변수에 담는다.
                result = Integer.parseInt(resultStr);
                check = false;
                // int 형으로 변경되면 check 에 false 담아서 반복문 빠져나간다.
                // int 형으로 변경되지 않는다면 NumberFormatException 발생
            }
            catch (NumberFormatException e) // NumberFormatException 발생한다면
            {
                check = true;   // check 에 true 담아서 다시 반복
                // result = 0; 으로 초기화된 상태이므로  하단 if문 내부까지 실행하고 반복된다.
            }

            if(result < 1 || result > 4 )// 주어진 값 이외의 수를 선택한 경우
            {
                System.out.println("========================================================================");
                System.out.println(" 올바른 값을 입력해주세요.");
                check = true;
            }

        }

        System.out.println();

        switch(result)                          // 사용자의 선택값에 따라
        {
            case  INFO:
                InfoAction infoAction = new InfoAction();
                infoAction.infoSelect();         // 1. 정보 확인 메소드 호출
                break;

            case  SHOP:
                ItemAction itemAction = new ItemAction();
                itemAction.selectItemType();       // 2. 상점 가기 메소드 호출
                break;

            case RECOVERY:                          // 3.회복하기 (3초마다 체력, 인내력 1씩 회복)
                Recovery recovery = new Recovery();

                if (User.getHp() == User.getSetHp() && User.getFeeling() == User.getSetFeeling())
                {
                    System.out.println(" 체력과 인내력을 회복하지 않아도 됩니다 ! ");
                    //recovery.interrupt();
                    recovery.finish();
                } else
                {
                    try
                    {
                        recovery.start();
                        recovery.join();

                    } catch (Exception e){
                        System.out.println(e.toString());
                    }
                }

                weekend();                      // 이전으로

            case SKIP :                        // 4. 주말 지나가기 : 다음날 카페 시작하는 메소드 호출

                try{
                    Sound sound = new Sound("weekInfo.mp3",false);
                    sound.start();

                    System.out.println();
                    System.out.println();
                    System.out.println();

                    Thread.sleep(100);
                    System.out.println("。　　　　　　　　　　　　　　　　　ﾟ　　　.　　　　　　　　　　　　　　.");
                    Thread.sleep(100);
                    System.out.println(",　　　　　　　　　.　 .　　　　　　　　.");
                    Thread.sleep(100);
                    System.out.println("　　　　　。　　　　　　　　　　　　　　　　　　　ﾟ　　　　　　　　　。");
                    Thread.sleep(100);
                    System.out.println("　　.　　　　　　　　.　　　　　.　　　　　　　　　　。　　.　");
                    Thread.sleep(100);
                    System.out.println("　.　　　　　　　　              평일이 되었습니다 ㅤㅤㅤㅤㅤㅤ    　 。　　.");
                    Thread.sleep(100);
                    System.out.println("　 　　　　　　。　　　　　　　　　　　　　　　　　ﾟ　　　.　　　　　　　　　　　　　　.");
                    Thread.sleep(100);
                    System.out.println(",　　　　　　　　　.　 .　　　　　　　　.");
                    Thread.sleep(100);
                    System.out.println("　　　　　。　　　　　　　　　　　　　　　　　　　ﾟ　　　　　　　　　。");
                    Thread.sleep(100);
                    System.out.println("　　.　　　　　　　　.　　　　　.　　　　　　　　　　。　　.");
                    Thread.sleep(100);

                    sound.finish();

                }catch (Exception e)
                {
                    System.out.println(e.toString());
                }

                User.setWorkingDays(User.getWorkingDays() + 1);
                // 주말이 지나도록 하루를 더한다. 토 → 월로 요일 변경

                open(); // 카페 열기

                break;
        }
    }

    // 엔딩
    public void finalEnding()
    {
        boolean check =true;    // 반복여부 체크하기 위한 변수
        String resultStr;
        int result = 0;

        /*System.out.println("　.　　　　　　　　　　　　　　                   ㅤㅤㅤㅤㅤㅤㅤㅤㅤ 　 。　　.");
        System.out.println("　 　　　　　　。　　　　　　　　- End - 　　ﾟ　　　.　　　　　　　　　　　　　　.");
        System.out.println(",　　　　　　　　　.　 .　　　　　　　　.");
        System.out.println("　　　　　。　　　　　　　　　　　　　　　　　　　ﾟ　　　　　　　　　。");
        System.out.println("　　.　　　　　　　　.　　　　　.　　　　　　　　　　。　　.");*/


        while(check) // check 는 위에서 입력받은 유저이름이 한글인걸 확인했기 때문에 true 인 상황이다.
        {
            System.out.println("========================================================================");
            System.out.println(" 1.끝내기  2.다시 시작하기 ");
            System.out.println("------------------------------------------------------------------------");
            System.out.print(" 선택 : ");
            Scanner sc = new Scanner(System.in);
            resultStr = sc.nextLine();
            System.out.println("========================================================================");

            // 입력받은 값이 숫자인지 확인
            try
            {
                // 입력받은 값의 공백을 제거하고
                // 자료형 변경한 뒤(String → int) int형에 담는다.
                result = Integer.parseInt(resultStr.replace(" ",""));
                check = false;
                // int 형으로 변경되면 check 에 false 담아서 반복문 빠져나간다.
                // int형으로 변경되지 않는다면 NumberFormatException 발생
            }
            catch (NumberFormatException e) // NumberFormatException 발생한다면
            {
                check = true;   // check 에 true 담아서 다시 반복
                // result = 0; 으로 초기화된 상태이므로  하단 if문 내부까지 실행하고 반복된다.
            }

            if(result < 1 || result > 2 )// 주어진 값 이외의 수를 선택한 경우
            {
                System.out.println("========================================================================");
                System.out.println(" 올바른 값을 입력해주세요.");
                check = true;
            }

        }


        final int STOP = 1;     // 프로그램 종료하는 선택지
        final int RESTART = 2;  // 다시 시작하는 선택지
        while(true)
        {
            switch(result)
            {
                case STOP : // 프로그램 종료
                    System.exit(0);
                    break;

                case RESTART: // 다시 시작하기
                    UserAction userAction = new UserAction();   // 유저 기능 객체 생성
                    userAction.reset();                         // 기존 값 초기화하는 메소드 호출
                    GameRun gameRun = new GameRun();            // 게임 실행 객체 생성
                    gameRun.intro();                            // 최초 실행 메소드 호츨
                    break;
            }
        }

    }
}
