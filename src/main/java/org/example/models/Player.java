package org.example.models;

import org.example.models.enums.PlayerType;

import java.util.Scanner;

public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private static Scanner scanner = new Scanner(System.in);

    public Move makeMove(Board board){
        System.out.println("please enter the row number: ");
        int r = scanner.nextInt();

        System.out.println("please enter the col number: ");
        int c = scanner.nextInt();

        return new Move(this,new Cell(r,c));
    }


    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public void printPlayer(){
        System.out.println("here is the winner: "+ this.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
