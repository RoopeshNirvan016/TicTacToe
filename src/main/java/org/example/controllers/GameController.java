package org.example.controllers;

import org.example.models.Game;
import org.example.models.Player;
import org.example.models.enums.GameState;

import java.util.List;

public class GameController {

    private static GameController instance = null;

   public static GameController getGameController(){
        if(instance == null){
            instance = new GameController();
        }
        return instance;
    }

    /***
     * Start a new game
     * It should create a new game and should return the game object
     * @return
     */
    public Game startGame(int dimensionOfBoard, List<Player> playerList){
         //call the game builder to start the game
        return Game.getBuilder().setDimension(dimensionOfBoard).setPlayerList(playerList).build();

    }

    public void makeMove(Game game){
        //before you make the move how the game is in progress then only we can make the move
        game.makeMove();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }

    public void displayBoardMethod(Game game){
        game.printBoard();
    }
}
