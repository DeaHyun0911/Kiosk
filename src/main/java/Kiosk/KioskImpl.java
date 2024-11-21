package Kiosk;

public class KioskImpl implements Kiosk {

    @Override
    public void start() {
        System.out.println("주문을 시작합니다.");
    }

    @Override
    public void nextMenu() {

    }

    @Override
    public void prevMenu() {

    }

    @Override
    public void exit(int menuNumber) {
        System.out.println("주문을 종료합니다.");
    }
}
