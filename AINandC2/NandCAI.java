package com.ZakHolmes.AINandC2;//pretty much done, decide weather do an ai or not

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class NandCAI {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){
        Draw dr = new Draw();
        dr.setVisible(true);
        char[][] grid = {{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '},{'-','+','-','+','-'},{' ','|',' ','|',' '}};//rows then cols
        boolean player = true; //true is x
        char win;
        String nextCoord;
        while (true) {
            if (player) {
                printGrid(grid);
                System.out.print("Enter the next area (e.g tr for top right)");
                nextCoord = scanner.next();
                grid = updateGrid(grid, nextCoord, player);
                win = winCheck(grid);
                player = !player;
                if (win != ' ')
                    break;
            }else{
                grid = aiTurn(grid,player);
                player = !player;
                win = winCheck(grid);
                if (win != ' ')
                    break;
            }
        }
        char winner = winCheck(grid);
        if (winner != '-'){
            System.out.println(winner + " wins!!");
        }else{
            System.out.println("Its a tie!!");
        }

        System.out.print("Play again");
        String again  = scanner.next();
        if (again.toLowerCase().charAt(0) == 'y')
            main(null);
    }

    public static char[][] aiTurn(char[][] grid,boolean ai){
        //for now ai is always O which is the minimiser
        int bestScore = Integer.MAX_VALUE;
        char aiTurn = 'O';
        int[] bestMove = new int[2];//stores the coords for the best move
        for (int i = 0; i <=4 ; i+=2) {
            for (int j = 0; j <=4; j+=2) {
                if (grid[i][j] == ' '){
                    grid[i][j] = aiTurn;
                    int score = minimax(grid,true,0);
                    grid[i][j] = ' ';
                    if (score < bestScore){
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;

                    }

                }
            }
        }
        grid[bestMove[0]][bestMove[1]] = aiTurn;
        return grid;
    }

    public static int evalPos(char[][] grid) {
        char win = winCheck(grid);
        if (win == 'X') {
            return 1;
        }else if(win == 'O'){
            return -1;
        }else{
            return 0;
        }
    }


    public static int minimax(char[][] grid, boolean isMax,int depth){
        if (winCheck(grid) != ' '){
            return evalPos(grid);
        }
        char aiTurn = 'O';
        char plTurn = 'X';
        int bestScore;
        if (isMax) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i <= 4; i += 2) {
                for (int j = 0; j <= 4; j += 2) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = plTurn;
                        int score = minimax(grid,false,depth+1);
                        bestScore = Math.max(bestScore,score);
                        grid[i][j] = ' ';

                    }
                }
            }
            return bestScore;
        }else{
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i <= 4; i += 2) {
                for (int j = 0; j <= 4; j += 2) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = aiTurn;
                        bestScore = Math.min(bestScore,minimax(grid,true,depth+1));
                        grid[i][j] = ' ';

                    }
                }
            }
            return bestScore;
        }
    }

    public static char[][] updateGrid(char[][] grid, String nextCoord,boolean player) {
        nextCoord = nextCoord.toLowerCase();
        char turn = (player) ? 'X' : 'O';
        while (true) {
            if (nextCoord.length() != 2) {
                System.out.println("please enter a valid co-ordinate");
                nextCoord = scanner.next();
            }

            switch (nextCoord) {
                case "tl":
                    grid[0][0] = turn;
                    return grid;
                case "tm":
                    grid[0][2] = turn;
                    return grid;
                case "tr":
                    grid[0][4] = turn;
                    return grid;
                case "ml":
                    grid[2][0] = turn;
                    return grid;
                case "mm":
                    grid[2][2] = turn;
                    return grid;
                case "mr":
                    grid[2][4] = turn;
                    return grid;
                case "bl":
                    grid[4][0] = turn;
                    return grid;
                case "bm":
                    grid[4][2] = turn;
                    return grid;
                case "br":
                    grid[4][4] = turn;
                    return grid;
                default:
                    System.out.println("please enter a valid coord ");
                    nextCoord = scanner.next();
            }
        }
    }

    public static void printGrid(char[][] grid){
        for (char[] row : grid){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static char winCheck(char[][] grid){
        for (int i = 0;i <= 4;i+=2) {//first check vertical
            char first = grid[0][i];
            if (first != ' ') {
                if (first == grid[2][i] && first == grid[4][i])
                    return first;
            }
        }
        for (int i = 0; i<=4;i+=2){//second check horizontal
            char first = grid[i][0];
            if (first != ' '){
                if (first == grid[i][2] && first == grid[i][4])
                    return first;
            }
        }
        if (grid[0][0] != ' '){
            char first = grid[0][0];
            if (first == grid[2][2] && first == grid[4][4])
                return first;

        }
        if (grid[4][0] != ' '){
            char first = grid[4][0];
            if (first == grid[2][2] && first == grid[0][4])
                return first;
        }
        if (isTie(grid)){
            return '-';
        }

        return ' ';
    }

    public static boolean isTie(char [][] grid) {
        for (int i = 0; i <= 4; i += 2) {
            for (int j = 0; j <= 4; j += 2) {
                if (grid[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
