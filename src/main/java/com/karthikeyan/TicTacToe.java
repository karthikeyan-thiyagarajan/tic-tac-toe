package com.karthikeyan;

import java.util.*;

public class TicTacToe {

    static List<Integer> playerPos = new ArrayList<>();
    static List<Integer> cpuPos = new ArrayList<>();
    static Scanner scanner;

    public static void main(String[] args) {

        scanner = new Scanner(System.in);
        char[][] board = {{' ', '|', ' ', '|', ' '}, {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'}, {' ', '|', ' ', '|', ' '}};
        System.out.println("(ctrl + c) to exit");
        prrintBoard(board);

        while (true) {
            System.out.print("Enter the Placements (1-9) : ");

            int playerPosInt = scanner.nextInt();
            playerPosInt = checkAvailability(scanner, playerPosInt, true);
            posPlacement(board, playerPosInt, "player");

            int cpuPosInt = new Random().nextInt(9) + 1;
            cpuPosInt = checkAvailability(scanner, cpuPosInt, false);
            posPlacement(board, cpuPosInt, "cpu");

            prrintBoard(board);
            String decision = winningConditions();
            if (!decision.isEmpty()) {
                System.out.println("decision = " + decision);
                break;
            }
        }
    }

    private static int checkAvailability(Scanner scanner, int posInt, boolean isPlayer) {
        int resultPositionInt;
        while (true) {

            if (posInt < 1 || posInt > 9) {
                System.err.println("Please enter the Valid Number (1-9) ");
                posInt = scanner.nextInt();
            } else if (cpuPos.contains(posInt) || playerPos.contains(posInt)) {
                if (isPlayer) {
                    System.err.println("Positions already taken Please select another");
                    posInt = scanner.nextInt();
                } else {
                    posInt = new Random().nextInt(9) + 1;
                }
            } else {
                if (isPlayer) {
                    playerPos.add(posInt);
                } else {
                    cpuPos.add(posInt);
                }
                resultPositionInt = posInt;
                break;
            }
        }
        return resultPositionInt;
    }

    private static void posPlacement(char[][] board, int pos, String user) {
        char symbol = ' ';
        if (user.equals("player")) symbol = 'X';
        else if (user.equals("cpu")) symbol = 'O';
        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + pos);
        }
    }

    private static void prrintBoard(char[][] board) {
        for (char[] chars : board) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    private static String winningConditions() {
        String decision = "";
        List<List<Integer>> winnings = new ArrayList<>();
        winnings.add(Arrays.asList(1, 2, 3));
        winnings.add(Arrays.asList(4, 5, 6));
        winnings.add(Arrays.asList(7, 8, 9));
        winnings.add(Arrays.asList(1, 4, 7));
        winnings.add(Arrays.asList(2, 5, 8));
        winnings.add(Arrays.asList(3, 6, 9));
        winnings.add(Arrays.asList(1, 5, 9));
        winnings.add(Arrays.asList(3, 5, 7));
        for (List<Integer> winning : winnings) {
            if (playerPos.containsAll(winning)) {
                decision = "Congratulations Champ !!! You won....";
            } else if (cpuPos.containsAll(winning)) {
                decision = ("Sorry You Lose :(");
            } else if (playerPos.size() + cpuPos.size() > 9) {
                decision = ("Match Tied");
            }
        }
        return decision;
    }
}

