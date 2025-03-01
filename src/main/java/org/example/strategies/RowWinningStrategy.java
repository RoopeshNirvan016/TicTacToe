package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;
import org.example.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy{

    private Map<Integer, Map<Symbol,Integer>> rowMap = new HashMap<>();

//    private List<Map<Symbol,Integer>> rowMpa = new ArrayList<>();


    @Override
    public boolean winner(Move move, Board board) {
        int rowNumber =  move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        if(!rowMap.containsKey(rowNumber)){
            rowMap.put(rowNumber,new HashMap<>());
        }

        Map<Symbol,Integer> currRowMap = rowMap.get(rowNumber);

        if(!currRowMap.containsKey(symbol)){
            currRowMap.put(symbol,0);
        }

        currRowMap.put(symbol,currRowMap.get(symbol)+1);

        return currRowMap.get(symbol) == board.getSize();
    }
}
