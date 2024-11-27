package Kiosk.discount;

public enum Grade {
    NATIONAL_MERIT("국가유공자", 10),
    SOLDIER("군인", 5),
    STUDENT("학생", 3),
    PUBLIC("일반", 0),
    GOT("신", 100);

    public final String title;
    public final int percent;

    Grade(String title, int percent) {
        this.title = title;
        this.percent = percent;
    }
}
