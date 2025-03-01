package org.example.factory;

import org.example.models.enums.BotDifficultLevel;
import org.example.strategies.winningstrategy.BotPlayingStrategy;
import org.example.strategies.winningstrategy.DifficultBotPlayingStrategy;
import org.example.strategies.winningstrategy.EasyBotPlayingStrategy;
import org.example.strategies.winningstrategy.MediumBotPlayingStrategy;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotStrategy(BotDifficultLevel botDifficultLevel){
        if(botDifficultLevel.equals(BotDifficultLevel.EASY)){
            return new EasyBotPlayingStrategy();
        }else if(botDifficultLevel.equals(BotDifficultLevel.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }else if(botDifficultLevel.equals(BotDifficultLevel.HARD)){
            return new DifficultBotPlayingStrategy();
        }
        return null;
    }
}
