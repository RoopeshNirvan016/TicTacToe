package org.example;

import org.example.controllers.GameController;
import org.example.models.Bot;
import org.example.models.Game;
import org.example.models.Player;
import org.example.models.Symbol;
import org.example.models.enums.BotDifficultLevel;
import org.example.models.enums.GameState;
import org.example.models.enums.PlayerType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Roopesh",new Symbol('X'),PlayerType.HUMAN);
        Player player2 = new Player("Rajesh",new Symbol('Y'),PlayerType.HUMAN);
        Player player3 = new Player("Ramesh",new Symbol('Z'),PlayerType.HUMAN);
        Player player4 = new Player("Karthik",new Symbol('L'),PlayerType.HUMAN);
        Player player5 = new Bot("ComputerBot",new Symbol('R'), PlayerType.BOT,BotDifficultLevel.EASY);

        //Build the game board with the list of players
        Game gameBoard = GameController.getGameController().startGame(3,new ArrayList<>(List.of(player1,player5)));

        //playing game untill the game state is in progress
        while (gameBoard.getGameState() == GameState.IN_PROGRESS){
            GameController.getGameController().displayBoardMethod(gameBoard);

            GameController.getGameController().makeMove(gameBoard);
        }

        GameState actualGameStatus = GameController.getGameController().getGameState(gameBoard);
        System.out.println("Game is ended:");
        System.out.println("Game state: " +actualGameStatus);
        if(actualGameStatus.equals(GameState.ENDED)){
            System.out.println("Game Winner details: ");
            GameController.getGameController().getWinner(gameBoard).printPlayer();
        }
    }
}