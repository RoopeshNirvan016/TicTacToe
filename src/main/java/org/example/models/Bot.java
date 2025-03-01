package org.example.models;

import org.example.factory.BotPlayingStrategyFactory;
import org.example.models.enums.BotDifficultLevel;
import org.example.models.enums.PlayerType;
import org.example.strategies.winningstrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, PlayerType playerType, BotDifficultLevel botDifficultyLevel) {
        super(name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotStrategy(botDifficultyLevel);
    }

    @Override
    public Move makeMove(Board board) {
        Move move =  botPlayingStrategy.botMove(board);
        move.setPlayer(this);
        return move;
    }
}
