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

    private int intValidated(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("\u001B[31m숫자만 입력 해주세요!\u001B[0m");
        }
    }

    private boolean optionValidated(int result, List<Integer> options) {
        try {
            return options.contains(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int getInput(String message, int options) {
        while (true) {
                String input = scannerInput(message);
                int result = intValidated(input);
                if(!optionValidated(result, createList(options))) {
                    System.out.println("\u001B[31m메뉴 번호를 확인해주세요!\u001B[0m");
                    System.out.println();
                    continue;
                }
                return result;
        }
    }

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

    public String stringInput(String message) {
        while (true) {
            try {
                return scannerInput(message);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 메뉴길이에 따른 배열 반환
    public static List<Integer> createList(int menuLength) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < menuLength; i++) {
            list.add(i);
        }
        return list;
    }

}
