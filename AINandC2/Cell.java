package AINandC2;

import javax.swing.*;

public class Cell {

    private char value = ' ';

    private JButton jButton;

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Cell(JButton jButton, int row, int col) {
        this.jButton = jButton;
        this.row = row;
        this.col = col;
    }

    private int row;

    private int col;


}
