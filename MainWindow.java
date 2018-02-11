import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    // Текстовое поле с состоянием игры
    private JLabel topText;
    // Игровое поле
    public GameCell[][]  cells;

    public MainWindow() {
        super("TicTaeToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Controller controller = new Controller(this);

        // Создаём компоновщик
        Box box = Box.createVerticalBox();

       //добавляем отступ
        box.add(Box.createVerticalStrut(10));

        ///Добавляем текст
        topText = new JLabel("Начало игры!", SwingConstants.LEFT);
        topText.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        box.add(topText);

        // Добавляем кнопку рестарта
        JButton restartButton = new JButton("Начать заново");
        restartButton.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        restartButton.addActionListener(controller);
        box.add(restartButton);

        // Добавляем отступ
        box.add(Box.createVerticalStrut(10));

        // Добавляем игровое поле
        JPanel gameField= new JPanel();
        gameField.setLayout(new GridLayout(3, 3, 0, 0));
        cells = new GameCell[3][3];

        for (int i=0; i<cells.length;i++) {
            for (int j=0; j<cells.length;j++) {
                cells[i][j]=new GameCell(i,j);
                cells[i][j].addMouseListener(controller);
                gameField.add(cells[i][j]);
            }
        }

        box.add(gameField);
        setContentPane(box);
        setSize(600, 600);
    }

    // Перезапуск игры
    public void restart(){

       setCellsEnabled(true);

        for (int i=0; i<cells.length;i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j].setText("");
            }
        }
    }

    //Блокировка кнопок
    public void setBlockState() {
        setCellsEnabled(false);
    }

    private void setCellsEnabled(boolean bool){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j].setEnabled(bool);
            }
        }
    }

    public void setWinner(String currPlayer){
        topText.setText(currPlayer.concat(" выиграл!"));
    }

    public void setFullField(){
        topText.setText("Ничья!");
    }

}
