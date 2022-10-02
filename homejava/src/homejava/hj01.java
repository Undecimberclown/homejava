package homejava;
// 로또
// 1. 숫자를 뽑아
// 1-1. 수동과 자동, 그리고 연속으로 비교
// 2. 키보드와의 숫자를 비교
// 3. 중복 상태를 확인 후
// 4. 등수 발표
import java.util.*;

public class hj01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        final int MAX = 45;
        final int MIN = 1;
        final int LOTTO_SIZE = 5;
        final int LOTTO_ONESIZE = 6;
        int[][] arr = new int[LOTTO_SIZE][LOTTO_ONESIZE];
        int[] lottoArr = new int[LOTTO_ONESIZE];
        int[] computerNUM = new int[LOTTO_ONESIZE];
        int count = 0;
        while(true){
            System.out.println("1. 수동 2. 자동, 3. 결과발표 0. 종료");
            System.out.print("> ");
            int userNum = scanner.nextInt();

            while(userNum  < 0 || userNum > 3){
                System.out.println("잘못된 값을 입력하셨습니다.");
                System.out.println("1. 수동 2. 자동, 3. 결과발표");
                System.out.print("> ");
                userNum = scanner.nextInt();
            }
            if(userNum == 1){
                if(count < LOTTO_SIZE) {


                    lottoArr = userChoice(lottoArr, scanner, MAX, MIN);
                    System.out.println();
                    check(lottoArr, scanner, MAX, MIN);
                    System.out.println();
                    sort(lottoArr);
                    for (int i = 0; i < lottoArr.length; i++) {
                        arr[count][i] = lottoArr[i];
                        System.out.print(arr[count][i] + "   ");
                    }
                    System.out.println();
                    count++;
                } else{
                    System.out.println("최대량 입니다.");
                }

            }else if(userNum == 2){
                if(count < LOTTO_SIZE) {
                    lottoArr = autoSet(lottoArr, random, MAX, MIN);
                    sort(lottoArr);
                    for (int i = 0; i < lottoArr.length; i++) {
                        arr[count][i] = lottoArr[i];
                        System.out.print(arr[count][i] + "   ");
                    }
                    System.out.println();
                    count++;
                } else{
                    System.out.println("최대량 입니다.");
                }

            }else if(userNum == 3){
                autoSet(computerNUM, random, MAX, MIN);
                sort(computerNUM);
                String result = "";
                String com = "";
                for(int i = 0; i < LOTTO_SIZE; i++){
                    for(int j = 0; j < LOTTO_ONESIZE; j++){
                        result += arr[i][j] + "   ";

                    }
                    int coge = compare(arr, i, computerNUM);
                    result += String.format("[%d]개 중복", coge);
                    result += "\n";
                }
                for(int i = 0; i < LOTTO_ONESIZE; i++){
                    com += computerNUM[i] + "   ";
                }
                System.out.printf("=================================================\n" +
                        "%s\n" +
                        "=================================================\n" +
                        "%s\n" +
                        "=================================================\n"
                        , result, com);
                for(int i = 0; i < LOTTO_SIZE; i++){
                    for(int j = 0; j < LOTTO_ONESIZE; j++){
                        arr[i][j] = 0;
                        lottoArr[j] = 0;
                        computerNUM[j] = 0;
                    }
                }
                count = 0;


            }else if(userNum == 0){
                System.out.println("이용해주셔서 감사합니다.");
                break;
            }
        }
    }
    public static int[] userChoice(int[] arr,Scanner scanner, int MAX, int MIN){
        for(int i =0; i < arr.length; i++){
            System.out.printf("[%d]번 번호를 입력해주세요\n", i+1);
            System.out.print("> ");
            arr[i] = scanner.nextInt();
            size(arr[i], MAX, MIN, scanner, i);

        }
        return arr;
    }
    public static int[] check(int[] arr, Scanner scanner, int MAX, int MIN){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(i != j && arr[i] == arr[j]){
                    System.out.printf("[%d]번째 숫자가 중복입니다. 다시 입력해주세요\n", i+1);
                    System.out.print("> ");
                    arr[i] = scanner.nextInt();
                    arr[i] = size(arr[i], MAX, MIN, scanner, i);


                }
            }

        }
        return arr;
    }
    public static int size(int num, int MAX, int MIN,Scanner scanner, int i){
        while(num > MAX || num < MIN){
            System.out.println("잘못된 수치입니다.");
            System.out.printf("[%d]번 번호를 입력해주세요\n", i+1);
            System.out.print("> ");
            num = scanner.nextInt();
        }
        return num;
    }
    public static int[] sort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i+1] = temp;
                i = -1;
            }
        }
        return arr;
    }
    public static int[] autoSet(int[] arr, Random random, int MAX, int MIN){
        for(int i = 0; i < arr.length; i++){
            arr[i] = random.nextInt(MAX) + MIN;

        }
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(i != j && arr[i] == arr[j]){
                    arr[i] = random.nextInt(MAX) + MIN;
                }
            }
        }
        return arr;
    }
    public static int compare(int[][] arr, int one, int[] comNUM){
        int count = 0;
        for(int i = 0; i < comNUM.length; i++){
            for(int j = 0; j < comNUM.length; j++) {
                if (arr[one][i] == comNUM[j]) {
                    count++;
                }
            }

        }
        return count;
    }


}
