package org.example.strategies;

import org.example.models.Board;
import org.example.models.Move;

public interface WinningStrategy {
    boolean winner(Move move, Board board);
}
