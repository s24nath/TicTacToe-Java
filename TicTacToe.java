/**
 * Created by Sutriptim Nath on 12th; August; 2020
 */

import java.util.Scanner;
public class TicTacToe {
    private char board[][];
    private char playerMark;
    private String playerName;
    private boolean markerIsPlaced;

    Scanner sc = new Scanner(System.in);

    public TicTacToe() {
        board = new char[3][3];
    }

    public void newGame(){
        int c = '1';
        for(int row = 0; row <= 2; row++) {
            for(int col = 0; col <= 2; col++) {
                board[row][col] = (char)c;
                c++;
            }
        }
    }

    public void display() {
        System.out.println("-------------------");
        for(int row = 0; row <= 2; row++) {
            System.out.print("|  ");
            for(int col = 0; col <= 2; col++) {
                System.out.print(Character.toUpperCase(board[row][col]) + "  |  ");
            }
            System.out.println();
        }
        System.out.println("-------------------");
    }

    private boolean isFull() {
        int c ='1';
        for(int row = 0; row <= 2; row++) {
            for(int col = 0; col <= 2; col++) {
                if(board[row][col] == ((char)c))
                    return false;
                c++;
            }
        }
        return true;
    }

    private boolean wins() {
        if(checkColWins() || checkRowWins() || checkDiagWins()) {
            return true;
        }
        else return false;
    }

    private boolean checkColWins() {
        for(int col = 0; col < 3; col++) {
            if (checkRowColDiag(board[0][col], board[1][col], board[2][col]) == true) {
                return true;
            }
        }
        return false;
    }

    private boolean drawMatch() {
        return (isFull() && !wins());
    }

    private boolean checkRowWins() {
        for(int row = 0; row < 3; row++) {
            if (checkRowColDiag(board[row][0], board[row][1], board[row][2]) == true)
                return true;
        }
        return false;
    }

    private boolean checkDiagWins() {
        if(checkRowColDiag(board[0][0], board[1][1],board[2][2]) == true)
            return true;
        else if(checkRowColDiag(board[0][2], board[1][1],board[2][0]) == true)
            return true;
        else return false;
    }

    private boolean checkRowColDiag(char mark1, char mark2, char mark3) {
         return ((mark1 == mark2) && (mark2 == mark3) && (mark1 == mark3));
    }

    private void setMark(char search) {
        markerIsPlaced = false;
        for(int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                if((char)board[i][j] == search) {
                    board[i][j] = playerMark;
                    markerIsPlaced = true;
                    return;
                }
            }
        }
        System.out.println();
        System.out.println("Marker is already used here");
        System.out.println("Choose an Empty Space");
        display();
    }

    private void changeMark() {
        if(playerMark == 'x') {
            playerMark = 'o';
        }
        else
            playerMark = 'x';
    }

    private void changeName(String player1, String player2) {
        if(playerName == player1)
            playerName = player2;
        else
            playerName = player1;
    }

    public void playGame() {
        newGame();
        System.out.print("Enter Name (Player 1) ->  ");
        String player1 = sc.nextLine();
        System.out.print("Enter Name (Player 2) ->  ");
        String player2 = sc.nextLine();
        System.out.println(player1 + " Choose your marker ' x ' or ' o ' ");
        playerMark = sc.next().charAt(0);
        playerName = player1;
        char c;
        while(!wins() && !isFull()) {
            display();
            do {
                System.out.println("\"" +Character.toUpperCase(playerMark) + "\"   " + playerName + "'s Turn ");
                System.out.print("Place your mark (1-9) ->  "  );
                c = sc.next().charAt(0);
                setMark(c);
            } while(!markerIsPlaced);
            changeMark();
            changeName(player1,player2);
        }
        if(drawMatch()) {
            display();
            sc.nextLine();
            System.out.println("Draw Match");
        }
        else {
            display();
            changeMark();
            changeName(player1,player2);
            sc.nextLine();
            System.out.println("\"" + Character.toUpperCase(playerMark) + "\"  " + playerName + "  Wins the Game ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicTacToe ttt = new TicTacToe();
        System.out.println();
        System.out.println();
        System.out.println("-----------------  TicTacToe  -----------------");
        System.out.println("Main Menu    : - ");
        System.out.println();
        System.out.println("1 . Play Game");
        System.out.println("2 . Exit");
        System.out.print("-> ");
        byte choice1 = sc.nextByte();
        switch(choice1) {
            case 1 :
                ttt.playGame();
                System.out.println("-------------- Game Over --------------");
                System.out.println("Main Menu    : - ");
                System.out.println("1 . Restart Game");
                System.out.println("2 . Exit");
                System.out.print("-> ");
                byte choice2 = sc.nextByte();
                switch(choice2) {
                    case 1 : ttt.playGame();
                    case 2 : System.exit(0);
                    default : System.out.println("Invalid Input");
                }
            case 2 : System.exit(0);

            default : System.out.println("Invalid Input");
        }

    }
}
