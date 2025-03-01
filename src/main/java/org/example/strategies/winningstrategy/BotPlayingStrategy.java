package org.example.strategies.winningstrategy;

import org.example.models.Board;
import org.example.models.Move;

public interface BotPlayingStrategy {
   Move botMove(Board board);
}
