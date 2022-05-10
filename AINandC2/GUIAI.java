package com.ZakHolmes.AINandC2;

public class GUIAI {
    public static int[] aiTurn(Cell[][] cells){
        //for now ai is always O which is the minimiser
        int bestScore = Integer.MAX_VALUE;
        char aiTurn = 'O';
        int[] bestMove = new int[2];//stores the coords for the best move
        for (int i = 0; i <=2 ; i++) {
            for (int j = 0; j <=2; j++) {
                if (cells[i][j].getValue() == ' '){
                    cells[i][j].setValue(aiTurn);
                    int score = minimax(cells,true,0);
                    cells[i][j].setValue(' ');
                    if (score < bestScore){
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;

                    }

                }
            }
        }
        return bestMove;
    }

    public static int evalPos(Cell[][] grid) {
        char win = winCheck(grid);
        if (win == 'X') {
            return 1;
        }else if(win == 'O'){
            return -1;
        }else{
            return 0;
        }
    }


    public static int minimax(Cell[][] cells, boolean isMax, int depth){
        if (winCheck(cells) != ' '){
            return evalPos(cells);
        }
        char aiTurn = 'O';
        char plTurn = 'X';
        int bestScore;
        if (isMax) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i <= 2; i ++) {
                for (int j = 0; j <= 2; j ++) {
                    if (cells[i][j].getValue() == ' ') {
                        cells[i][j].setValue(plTurn);
                        int score = minimax(cells,false,depth+1);
                        bestScore = Math.max(bestScore,score);
                        cells[i][j].setValue(' ');

                    }
                }
            }
            return bestScore;
        }else{
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i <= 2; i ++) {
                for (int j = 0; j <= 2; j ++) {
                    if (cells[i][j].getValue() == ' ') {
                        cells[i][j].setValue(aiTurn);
                        bestScore = Math.min(bestScore,minimax(cells,true,depth+1));
                        cells[i][j].setValue(' ');

                    }
                }
            }
            return bestScore;
        }
    }
    public static char winCheck(Cell[][] grid){
        for (int i = 0;i <= 2;i++) {//first check vertical
            char first = grid[0][i].getValue();
            if (first != ' ') {
                if (first == grid[1][i].getValue() && first == grid[2][i].getValue())
                    return first;
            }
        }
        for (int i = 0;i <= 2;i++){//second check horizontal
            char first = grid[i][0].getValue();
            if (first != ' '){
                if (first == grid[i][1].getValue() && first == grid[i][2].getValue())
                    return first;
            }
        }
        if (grid[0][0].getValue() != ' '){
            char first = grid[0][0].getValue();
            if (first == grid[1][1].getValue() && first == grid[2][2].getValue())
                return first;

        }
        if (grid[2][0].getValue() != ' '){
            char first = grid[2][0].getValue();
            if (first == grid[1][1].getValue() && first == grid[0][2].getValue())
                return first;
        }
        if (isTie(grid)){
            return '-';
        }

        return ' ';
    }

    public static boolean isTie(Cell [][] grid) {
        for (int i = 0; i <= 2; i ++) {
            for (int j = 0; j <= 2; j ++) {
                if (grid[i][j].getValue() == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
