package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy
{

  private  Map<Symbol,Integer> leftDiagonalMap = new HashMap<>();
  private  Map<Symbol,Integer> rightDiagonalMap = new HashMap<>();


    @Override
    public boolean winner(Move move, Board board) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        //update the left diagonal
        if(row == col){

            if(!leftDiagonalMap.containsKey(symbol)){
                leftDiagonalMap.put(symbol,0);
            }
            leftDiagonalMap.put(symbol,leftDiagonalMap.get(symbol)+1);

            return leftDiagonalMap.get(symbol) == board.getSize();
        }

        //update the right diagonal
        if(row+col == board.getSize()-1){
            if(!rightDiagonalMap.containsKey(symbol)){
                rightDiagonalMap.put(symbol,0);
            }
            rightDiagonalMap.put(symbol,rightDiagonalMap.get(symbol)+1);

            return rightDiagonalMap.get(symbol) == board.getSize();
        }

        return false;
    }
}
