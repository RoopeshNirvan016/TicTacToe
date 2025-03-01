package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    Map<Integer,Map<Symbol,Integer>> colMap = new HashMap<>();

    @Override
    public boolean winner(Move move, Board board) {
        int colNumber = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!colMap.containsKey(colNumber)){
            colMap.put(colNumber,new HashMap<>());
        }

        Map<Symbol,Integer> currColNumber = colMap.get(colNumber);

        if(!currColNumber.containsKey(symbol)){
            currColNumber.put(symbol,0);
        }

        currColNumber.put(symbol,currColNumber.get(symbol)+1);

        return currColNumber.get(symbol)==board.getSize();
    }
}
