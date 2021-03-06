package com.company.thread;

import com.company.action.BattleAction;
import com.company.data.Bug;
import com.company.data.User;

import java.util.Random;

public class AttackBug extends Thread
{
    private Bug bug;
    private User user;

    public AttackBug(Bug bug, User user)
    {
        this.bug = bug;
        this.user = user;
    }


    @Override
    public void run()
    {
        attackBug();
    }



    // 유저가 벌레를 공격한다.
    public void attackBug()
    {
        while(true)
        {
            try
            {
                Thread.sleep(2000); // 이 이후에 멈출지 체크해야한다.

                if(user.getBattleHp()<=0||bug.getHp()<=0)
                {
                    return;
                   // this.stop();

                }

                Random rd = new Random();
                int randomNum = rd.nextInt(10)+1;

                int damage;


                if(randomNum<=7) // 1~7까지의 경우
                {
                    damage = User.getSkillLevel();      // 유저의 숙련도 만큼 데미지를 입힌다

                    // 불청객의 체력에서 받은 데미지만큼 제외하기
                    bug.setHp(bug.getHp() - damage);

                    Sound sound = new Sound("attack.mp3",false);
                    sound.start();

                    System.out.printf("------------------------------------------------------------------------\n"
                            + "                       %s님이 %s를 공격했습니다!\n"
                            + "------------------------------------------------------------------------\n\n"
                            + " >>> %s가 %d 데미지를 입었습니다 <<<\n"
                            + "==================================\n"
                            + "              %s\n"
                            + "==================================\n"
                            + "          체력    : %d\n"
                            + "          공격력  : %d\n"
                            + "==================================\n"
                            , User.getName(), bug.getName(), bug.getName(), damage,bug.getName(), bug.getHp(),bug.getDamage());

                }
                else
                {
                    damage = User.getSkillLevel()*2;    // 유저의 숙련도의 두배만큼 데미지를 입힌다.

                    // 불청객의 체력에서 받은 데미지만큼 제외하기
                    bug.setHp(bug.getHp() - damage);

                    Sound sound = new Sound("attack.mp3",false);
                    sound.start();

                    System.out.printf("------------------------------------------------------------------------\n"
                            + "                    %s님이 %s를 강하게 공격했습니다!\n"
                            + "------------------------------------------------------------------------\n\n"
                            + " >>> %s가 %d 데미지를 입었습니다 <<<\n"
                            + "==================================\n"
                            + "              %s\n"
                            + "==================================\n"
                            + "          체력    : %d\n"
                            + "          공격력  : %d\n"
                            + "==================================\n"
                            , User.getName(), bug.getName(), bug.getName(), damage, bug.getName(), bug.getHp(),bug.getDamage());
                }

                if(bug.getHp()<=0||user.getBattleHp()<=0)
                {
                    return;
                    //this.stop();

                }


            }
            catch (InterruptedException e){
                return;
            }

        }

    }

    /*
    // 유저가 벌레를 공격한다.
    public void attackBug()
    {
        try
        {
            Random rd = new Random();
            int randomNum = rd.nextInt(10)+1;

            int damage;

            Sound sound = new Sound("attack.mp3",false);
            sound.start();

            if(randomNum<=7) // 1~7까지의 경우
            {
                damage = User.getSkillLevel();      // 유저의 숙련도 만큼 데미지를 입힌다
                System.out.printf("------------------------------------------------------------------------\n"
                        + "                       %s님이 %s를 공격했습니다!\n"
                        + "------------------------------------------------------------------------\n\n"
                        + " >>> %s가 %d 데미지를 입었습니다 <<<\n", User.getName(), bug.getName(), bug.getName(), damage);

            }
            else
            {
                damage = User.getSkillLevel()*2;    // 유저의 숙련도의 두배만큼 데미지를 입힌다.
                System.out.printf("------------------------------------------------------------------------\n"
                        + "                    %s님이 %s를 강하게 공격했습니다!\n"
                        + "------------------------------------------------------------------------\n\n"
                        + " >>> %s가 %d 데미지를 입었습니다 <<<\n", User.getName(), bug.getName(), bug.getName(), damage);

            }

            // 불청객의 체력에서 받은 데미지만큼 제외하기
            bug.setHp(bug.getHp() - damage);

            // test
            //System.out.println("유저가 공격, 벌레 체력 : " + bug.getHp() + " 유저 체력 : " + user.getBattleHp());

            // 불청객 정보 출력
            BattleAction battleAction = new BattleAction();
            battleAction.bugInfo(bug);


        }catch (Exception e){
            System.out.println(e.toString());
        }
    }*/

}
