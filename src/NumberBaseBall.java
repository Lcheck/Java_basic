import java.util.Random;
import java.util.Scanner;
public class NumberBaseBall {

    static String ans; //정답
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean done=false;

        while (!done) {
            int count = 0; //카운트
            String userAns=""; //사용자의 정답
            ans=makeAns(); //정답 생성

            while (true) {
                count++;
                if (ans.equals(userAns)) {
                    System.out.println("정답입니다!");
                    System.out.println("1.다시하시겠습니까?");
                    System.out.println("2.종료하시겠습니까?");

                    if (scanner.nextInt(10) == 1) {
                        break;
                    }
                    done=true;
                    break;

                }
                if (count > 10) {
                    System.out.printf("패배하셨습니다. \n정답은 %s 입니다.\n", ans);
                    System.out.println("1.다시하시겠습니까?");
                    System.out.println("2.종료하시겠습니까?");

                    if (scanner.nextInt(10) == 1) {
                        break;
                    }

                    done=true;
                    break;
                }


                try {
                    System.out.println("세자리 숫자를 입력하세요");
                    userAns = scanner.next();
                    System.out.println(judge(userAns) + "입니다.");
                    System.out.printf("%d 회 남으셨습니다.\n\n", 10 - count);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("\n[경고] 3자리의 숫자를 입력해주세요");
                    count--;
                }
            }

        }
    }

    public static String makeAns(){
        Random random = new Random(); //랜덤 객체
        int randomNum;
        random.setSeed(System.currentTimeMillis()); // 랜덤 시드 설정
        StringBuilder ans = new StringBuilder(); // 정답 문자열

        for (int i=0;i<3;i++) { // 자리수가 중복되지 않은 세자리 숫자 설정
            randomNum=random.nextInt(10); //랜덤숫자(1자리)

            if(ans.toString().contains(String.valueOf(randomNum))){ //만일 중복되는 수가 존재하면
                i--; //한번 더 실행

            }else { // 같지 않으면
                ans.append(Integer.toString(randomNum,10));//자릿수 이어 붙이기
            }
        }
        return ans.toString();
    }

    public static String judge(String userAns){
        int strike = 0;
        int ball =0;

        for (int i=0;i<3;i++){
            if (ans.charAt(i)==userAns.charAt(i)){ //위치와 값이 같다면
                strike++;

                continue; //스크라이크인 경우는 볼로 취급하지 않음
            }

            if(ans.contains(String.valueOf(userAns.charAt(i)))) { //각 자리의 숫자가 정답에 포함되어 있는지 체크
                ball++;
            }
        }

        return String.format("%d 스트라이크 %d 볼",strike,ball);

        }



    }
