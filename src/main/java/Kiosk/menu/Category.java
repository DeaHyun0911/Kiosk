package Kiosk.menu;

public enum Category {
    BURGERS,
    DRINKS,
    SIDES;

    public static String[] numberArray() {
        Category[] categories = Category.values();
        String[] strArr = new String[categories.length];

        for(int i = 0; i < categories.length; i++) {
            strArr[i] = String.valueOf(i + 1);
        }

        return strArr;
    }
}

