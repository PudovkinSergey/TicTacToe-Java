public class AppData {

    private static String currPlayer="Игрок";
    public static boolean gameOver;

    public static final String PLAYER ="Игрок";
    public static final String COMP ="Компьютер";
    private static final String PLAYER_VALUE = "X";
    private static final String COMP_VALUE = "O";

    public static String getCurrPlayer(){
        if (currPlayer==null) setDefaultSettings();
        return currPlayer;
    }

    public static void setDefaultSettings(){
        currPlayer = PLAYER;
        gameOver = false;
    }

    // Получаем текущего игрока
    public static String getPlayerValue(){
        switch (currPlayer) {

            case PLAYER:
                return PLAYER_VALUE;

            case COMP:
                return COMP_VALUE;

            default:
                setDefaultSettings();
                return getPlayerValue();
        }
    }

    // Переключаем текущего игрока
    public static void switchCurrPlayer(){
        switch (currPlayer){

            case PLAYER:
                currPlayer=COMP;
                break;

            case COMP:
                currPlayer=COMP;

            default:
                setDefaultSettings();
                break;
        }
    }
}
