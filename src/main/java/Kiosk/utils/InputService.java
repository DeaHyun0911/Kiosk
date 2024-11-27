package Kiosk.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputService {
    private final Scanner scanner;

    public InputService() {
        this.scanner = new Scanner(System.in);
    }

    // 사용자 입력을 받아옴
    public String scannerInput(String str) {
        System.out.print(str);
        return scanner.nextLine();
    }

    // 받은 문자열 숫자인지 검증
    private int intValidated(String number) {
        try {
            return Integer.parseInt(number); // 숫자 변환 시도
        } catch (NumberFormatException e) {
            throw new NumberFormatException("\u001B[31m숫자만 입력 해주세요!\u001B[0m");
        }
    }

    // 입력한 숫자가 메뉴 범위 안에 있는 지 확인
    private boolean optionValidated(int result, List<Integer> options) {
        return options.contains(result);
    }

    // 입력받은 메뉴 길이를 배열로 변환
    public static List<Integer> createList(int menuLength) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < menuLength; i++) {
            list.add(i);
        }
        System.out.println(list);
        return list;
    }

    // 사용자 입력 메세지와 메뉴 범위를 받아 입력받은 값이 숫자, 메뉴 범위에 포함되는 검증하고 숫자 타입으로 반환
    public int getInput(String message, int options) {
        while (true) {
            try {
                String input = scannerInput(message);
                int result = intValidated(input);
                if (!optionValidated(result, createList(options))) {
                    System.out.println("\u001B[31m메뉴 번호를 확인해주세요!\u001B[0m");
                    System.out.println();
                    continue;
                }
                return result;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }



    // 수량 선택을 위한 메서드
    public int getQuantityInput(String message) {
        while (true) {
            try {
                String input = scannerInput(message);
                if (intValidated(input) != 0) {
                    return intValidated(input);
                } else {
                    throw new NumberFormatException("0 이상의 숫자를 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 일반 문자열 입력 메서드
    public String stringInput(String message) {
        while (true) {
            try {
                return scannerInput(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }



}
