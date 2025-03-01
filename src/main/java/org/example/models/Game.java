package org.example.models;

import org.example.exceptions.InvalidBotCountException;
import org.example.exceptions.InvalidPlayerCountException;
import org.example.models.enums.CellState;
import org.example.models.enums.GameState;
import org.example.models.enums.PlayerType;
import org.example.strategies.ColumnWinningStrategy;
import org.example.strategies.DiagonalWinningStrategy;
import org.example.strategies.RowWinningStrategy;
import org.example.strategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
   private Board board;
   private List<Player> playerList;
   private Player winner;
   private List<Move> moveList;
   private GameState gameState;
   private int nextPlayerIndex;
   private List<WinningStrategy> winningStrategies;


   private Game(int dimension,List<Player> playerList, List<WinningStrategy> winningStrategies){
        this.board = new Board(dimension);
        this.playerList = playerList;
        this.gameState = GameState.IN_PROGRESS;
        this.moveList = new ArrayList<>();
        this.nextPlayerIndex = 0;
        this.winningStrategies =winningStrategies;
   }

   public void printBoard(){
        this.board.displayBoard();
        playerList.forEach(player -> {
           System.out.println("playerName: "+player.getName());
      });
      System.out.println("State of the game:"+ gameState);
   }

   public boolean validateMove(Move move, Board board){
      int r = move.getCell().getRow();
      int c = move.getCell().getCol();
      if(r<0 && r>this.board.getSize() || c<0 && c>this.board.getSize()){
           return  false;
      }
      return board.getCells().get(r).get(c).isEmpty();
   }

   public void makeMove(){
     Player currentPlayer = playerList.get(nextPlayerIndex);
     Move move = currentPlayer.makeMove(board);
     //Game will validate the move
      if(!validateMove(move,board)){
         System.out.println("move is not a valid one");
      }
      int r = move.getCell().getRow();
      int c = move.getCell().getCol();

      Cell cell = board.getCells().get(r).get(c);

      cell.setPlayer(currentPlayer);
      cell.setCellState(CellState.FILLED);
      nextPlayerIndex = (nextPlayerIndex+1) % playerList.size();

      //add the move to the list of moves
      Move finalMove = new Move(currentPlayer,cell);
      this.moveList.add(finalMove);

      if(checkWinner(finalMove,board)){
          this.gameState = GameState.ENDED;
          this.winner = currentPlayer;
      }else if(this.moveList.size() == this.board.getSize() * this.board.getSize()){
          this.gameState = GameState.DRAW;
      }
   }

   private boolean checkWinner(Move move, Board board){
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.winner(move,board)) {
                return true;
            }
        }
        return false;
   }


   public Player getWinner(){
       return winner;
   }

   public Board getBoard() {
      return board;
   }

   public List<Player> getPlayerList() {
      return playerList;
   }

   public List<Move> getMoveList() {
      return moveList;
   }

   public GameState getGameState() {
      return gameState;
   }

   public int getNextPlayerIndex() {
      return nextPlayerIndex;
   }

   public static GameBuilder getBuilder(){
       return new GameBuilder();
   }

   public static class GameBuilder{
      private int dimension;
      private List<Player> playerList;

      private void validateBotCount(){
         // bot count <= 1
         AtomicInteger botCount = new AtomicInteger();
         playerList.stream().forEach(player -> {
           if(player.getPlayerType().equals(PlayerType.BOT))
             botCount.getAndIncrement();
         });

         if(botCount.get()>1)
            throw  new InvalidBotCountException("Bot count should  be less than equal to 1");

      }

      private void validatePlayerCount(){
         // player should be N-1;
         if(playerList.size() != dimension-1)
            throw new InvalidPlayerCountException("Player count should be N-1");
      }

      private void validateUniqueSymbol(){
         for(int i=0; i<playerList.size(); i++) {
            for(int j=i+1; j<playerList.size(); j++){
                  if(playerList.get(i).getSymbol().getValue() == playerList.get(j).getSymbol().getValue())
                     throw new RuntimeException("Symbols should be unique");
            }
         }
      }

      private void validateGame() {
         validateBotCount();
         validatePlayerCount();
         validateUniqueSymbol();
      }


      public Game build(){
         //validations
         validateGame();
         List<WinningStrategy> winningStrategyList = new ArrayList<>();
         winningStrategyList.add(new RowWinningStrategy());
         winningStrategyList.add(new ColumnWinningStrategy());
         winningStrategyList.add(new DiagonalWinningStrategy());
         return new Game(
                 dimension,
                 playerList,winningStrategyList);
      }

      public int getDimension() {
         return dimension;
      }

      public GameBuilder setDimension(int dimension) {
         if(dimension <=2)
            throw  new RuntimeException("dimension shouldn't be less than 2");

         this.dimension = dimension;
         return this;
      }

      public List<Player> getPlayerList() {
         return playerList;
      }

      public GameBuilder setPlayerList(List<Player> playerList) {
         this.playerList = playerList;
         return this;
      }
   }
}
