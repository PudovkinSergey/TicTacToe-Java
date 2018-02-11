import javax.swing.*;

public class GameCell extends JButton {

    // Координаты кнопки на игровом поле
    public int cellX;
    public int cellY;

    public GameCell(int cellX, int cellY){
        this.cellX=cellX;
        this.cellY=cellY;
    }
}
