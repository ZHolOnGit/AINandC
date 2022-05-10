package com.ZakHolmes.AINandC2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.ZakHolmes.AINandC2.GUIAI.aiTurn;
import static com.ZakHolmes.AINandC2.GUIAI.winCheck;

public class Draw extends JFrame {

    public Cell[][] cells;

    public Draw(){
        setSize(300,300);
        setLocationRelativeTo(null);
        addComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        Draw dr = new Draw();
        dr.setVisible(true);
    }
    private void addComponents(){
        GridLayout gridLayout = new GridLayout(3,3);
        this.setLayout(gridLayout);
        Cell[][] cells = new Cell[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3 ; j++) {
                JButton button = setUpElement();
                Cell tempEl = new Cell(button,i,j);
                cells[i][j] = tempEl;
                button.addActionListener(e -> onClick(tempEl));

            }
        }
        this.cells = cells;
    }

    private void endGame(){
        char win = winCheck(cells);
        System.out.println(win);
        if (win == 'X' || win == 'O'){
            JOptionPane.showMessageDialog(this,win + " Is the winner");
            System.exit(0);
        }else if (win == '-'){
            JOptionPane.showMessageDialog(this,"Tie!");
            System.exit(0);
        }
    }

    private void onClick(Cell tempEl){
        if (tempEl.getValue()==' '){
            tempEl.setValue('X');
            tempEl.jButton.setText("X");
            endGame();
        }
        int[] aiArr = aiTurn(this.cells);
        this.cells[aiArr[0]][aiArr[1]].setValue('O');
        this.cells[aiArr[0]][aiArr[1]].jButton.setText("O");
        endGame();
    }

    private JButton setUpElement() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setBorder(new LineBorder(Color.BLACK));
        JButton button = new JButton("");
        button.setFont(new Font("Arial",Font.PLAIN,40));
        button.setPreferredSize(new Dimension(100, 100));
        button.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);
        button.setContentAreaFilled(true);
        button.setBorderPainted(false);
        button.setOpaque(true);
        panel.add(button);

        return button;
    }

}
