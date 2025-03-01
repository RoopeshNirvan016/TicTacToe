package org.example.models;

import org.example.models.enums.CellState;

import java.util.ArrayList;
import java.util.List;


public class Board {
   private int size;
   private List<List<Cell>> cells;

    public Board(int dimension) {
        this.size = dimension;
        this.cells = new ArrayList<>();

        for(int i=0; i<dimension; i++) {
              cells.add(new ArrayList<>());
              for(int j=0; j<dimension; j++) {
                  cells.get(i).add(new Cell(i,j));
              }
        }
    }

    public void displayBoard() {
        for(int i=0; i<cells.size(); i++){
             for(int j=0; j< cells.get(i).size();j++){
                 if(cells.get(i).get(j).getCellState() == CellState.EMPTY){
                     System.out.print("|  |");
                 }else {
                     System.out.println("| "+cells.get(i).get(j).getPlayer().getSymbol().getValue()+" |");
                 }
             }
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }
}
