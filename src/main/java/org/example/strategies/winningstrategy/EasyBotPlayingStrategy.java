package org.example.strategies.winningstrategy;

import org.example.models.Board;
import org.example.models.Cell;
import org.example.models.Move;
import org.example.models.enums.CellState;
import org.example.models.enums.GameState;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move botMove(Board board) {
        //iterate through a board and find the first empty cell on the board
        for(List<Cell> cells: board.getCells()){
            for(Cell cell: cells){
                if(cell.getCellState().equals(CellState.EMPTY)){
                    return new Move(null,cell);
                }
            }
        }
        return null;
    }
}
