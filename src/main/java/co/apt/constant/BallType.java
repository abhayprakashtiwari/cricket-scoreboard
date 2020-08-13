package co.apt.constant;

public enum BallType {

    SINGLE("1"), DOUBLE("2"), TRIPLE("3"), FOUR("4"), SIX("6"), WIDE("Wd"), WICKET("W"), NOBALL("Nb"), SIMPLE("X");

    private String ball;

    private BallType(String ball){
        this.ball = ball;
    }

    public String getBall() {
        return ball;
    }

    public static BallType fromString(String val){
        switch (val){
            case "1": return SINGLE;
            case "2": return DOUBLE;
            case "3": return TRIPLE;
            case "4": return FOUR;
            case "6": return SIX;
            case "Wd": return WIDE;
            case "W": return WICKET;
            case "Nb": return NOBALL;
            default: return SIMPLE;
        }

    }
}
