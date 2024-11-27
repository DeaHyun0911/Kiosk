package Kiosk.utils;


import Kiosk.menu.Category;

public class PrintService {

    public final FistMenu fistMenu = new FistMenu();

    public static class FistMenu {
        public void title() {
            System.out.println();
            System.out.println("\033[38;5;214m[ MOM'S TOUCH MENU ]\033[0m");
        }

        public void exit() {
            System.out.println("\u001B[36m0.\u001B[0m EXIT");
        }

        public void addMenu() {
            System.out.print("\u001B[36m4\u001B[0m. ORDERS   \u001B[36m5\u001B[0m. CART    ");
        }

        public void menu(Category[] categories) {
            for(int i = 0; i < categories.length; i++) {
                System.out.print("\u001B[36m" + (i + 1) + "\u001B[0m. " + categories[i] + "    ");
            }
        }
    }

}
