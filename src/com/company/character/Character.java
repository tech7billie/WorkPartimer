package com.company.character;

// 게임에 등장하는 캐릭터들(유저, 손님)의 가장 기본이 되는 속성을 담은 클래스.
// 이 클래스를 상속받는 클래스가 존재한다.

// public : 다른 패키지에서도 객체를 생성할 수 있음
// default : class 앞에 접근제한자가 없는 형태. 동일한 패키지에서만 객체를 생성할 수 있음.
public class Character
{
    // protected : 상속받은 클래스에서 접근 가능
    protected static int hp = 10;    // 현재 캐릭터의 체력
    protected static int setHp = 10; // 세팅된 캐릭터의 체력 : 아이템을 통해 변경 가능

    public static int getHp()
    {
        return hp;
    }

    public static void setHp(int hp)
    {
        Character.hp = hp;
    }

    public static int getSetHp()
    {
        return setHp;
    }

    public static void setSetHp(int setHp)
    {
        Character.setHp = setHp;
    }

    // 유저의 경우 체력이 떨어지면 숙련도가 낮아져 미니게임의 난이도가 높아진다.
    // 손님의 경우 체력이 떨어지면 가게를 나간다.(테이블 비움) ??
    // 특별 손님의 경우
}
