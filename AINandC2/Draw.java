package AINandC2;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Draw extends JFrame {

    public Draw(){
        setSize(300,300);
        setLocationRelativeTo(null);
        addComponents();
    }

    private void addComponents(){
        GridLayout gridLayout = new GridLayout(3,3);
        this.setLayout(gridLayout);
        Cell[][] cells = new Cell[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j <3 ; j++) {
                JButton button = setUpElement();
                Cell tempEl = new Cell(button,i,j);
                //button.addActionListener(e -> onClick);

            }
        }
    }

//    private void onClick(AINandC2.Cell tempEl){
//        if (tempEl.getValue()==' '){
//            tempEl.
//        }
//
//        }
//    }

    private JButton setUpElement() {
        JPanel panel = new JPanel();
        this.add(panel);
        panel.setBorder(new LineBorder(Color.BLACK));
        JButton button = new JButton("");
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
