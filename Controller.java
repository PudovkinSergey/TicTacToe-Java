import java.awt.event.*;
import java.util.Random;

public class Controller extends MouseAdapter implements MouseListener, ActionListener{

    //  Окно, которое создало контроллер
    private MainWindow parent;

    public Controller(MainWindow parent){
        this.parent=parent;
    }

    // Отрабатываем кнопку рестарта
    @Override
    public void actionPerformed(ActionEvent event){
        restart();
    }

    // Отрабатываем нажатия кнопок на игровом поле
    @Override
    public void mouseClicked(MouseEvent event){
        GameCell currCell= (GameCell) event.getComponent();

        if (!currCell.isEnabled()) return;

        if (checkCell(currCell)){
            if (AppData.gameOver) return;
            compTurn();
        }
    }

    // Ход компьютера
    private void compTurn(){
        boolean success;
        int x,y;
        GameCell currCell;
        do {
            // Выбираем случайную незаполненную клетку
            x=new Random().nextInt(3);
            y=new Random().nextInt(3);

            currCell = parent.cells[x][y];

            success=checkCell(currCell);
        } while (!success);
    }

    // Рестарт игры
    public void  restart() {
        AppData.setDefaultSettings();
        parent.restart();
    }

    // Проверяем заполненность клетки
    private boolean checkCell(GameCell currCell){

        if (currCell.getText().equals("")) {
            // Пишем Х или О на кнопку
            currCell.setText(AppData.getPlayerValue());

            // Проверяем наличие победителя или заполненного поля
            if (checkForWinner(currCell)) return true;
            if (checkForFullField()) return true;

            AppData.switchCurrPlayer();
            return true;
        }
        return false;
    }

    // Проверяем наличие побидителя
    private boolean checkForWinner(GameCell currCell){

        if (findThreeInARow(currCell)){
            parent.setBlockState();
            parent.setWinner(AppData.getCurrPlayer());
            AppData.gameOver=true;
            return true;
        }
        return false;
    }

    // Проверяем наличие трех одинаковых символов в ряд
    private boolean findThreeInARow(GameCell currCell){
        GameCell[] row = new GameCell[3];

        // Проверяем горизонтальную линии
        for (int i = 0; i < row.length; i++ ){
            row[i]=parent.cells[currCell.cellX][i];
        }
        if (checkRow(row)) return true;

        // Проверяем вертикальную линию
        for (int i = 0; i < row.length; i++ ){
            row[i]=parent.cells[i][currCell.cellY];
        }
        if (checkRow(row)) return true;

        // Проверяем диагональ
        if (currCell.cellX==currCell.cellY) {
            for (int i = 0; i <row.length; i++ ){
                row[i]=parent.cells[i][i];
            }
            if (checkRow(row)) return true;
        }

        // Проверяем вторую диагональ
        if (currCell.cellX==(2-currCell.cellY)) {
            for (int i = 0; i <row.length; i++ ){
                row[i]=parent.cells[i][2-i];
            }
            if (checkRow(row)) return true;
        }
        return false;
    }

    // Проверяем ряд на содержание одинаковых символов
    private boolean checkRow(GameCell[] row){
        if (row==null) return false;

        String playerSymbol= row[0].getText();

        if (playerSymbol.equals("")) return false;

        for (GameCell gameCell :row){
            if (!gameCell.getText().equals(playerSymbol)) return false;
        }
        return true;
    }

    // Проверяем заполненность поля
    private boolean checkForFullField(){
        for (int i =0; i<parent.cells.length;i++)
            for (int j =0; j<parent.cells.length;j++)
                if (parent.cells[i][j].getText().equals("")) return false;
        parent.setBlockState();
        parent.setFullField();
        AppData.gameOver=true;
        return true;
    }
}
