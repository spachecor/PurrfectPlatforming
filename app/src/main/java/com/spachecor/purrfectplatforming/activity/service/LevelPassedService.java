package com.spachecor.purrfectplatforming.activity.service;

import android.content.Context;
import android.content.SharedPreferences;

public class LevelPassedService {
    private static final String VICTORY_LEVELS_PREFERENCES_NAME;
    static {
        VICTORY_LEVELS_PREFERENCES_NAME = "victory-levels";
    }
    public static void resetLevels(Context context){
        SharedPreferences preferences = context.getSharedPreferences(LevelPassedService.VICTORY_LEVELS_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("level0", false);
        editor.putBoolean("level1", false);
        editor.putBoolean("level2", false);
        editor.putBoolean("level3", false);
        editor.putBoolean("level4", false);
        editor.commit();
    }
    public static Boolean isLevelPassed(Context context, Integer level){
        SharedPreferences preferences = context.getSharedPreferences(LevelPassedService.VICTORY_LEVELS_PREFERENCES_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean("level"+level, false);
    }
    public static void setLevelPassed(Context context, Integer level){
        SharedPreferences preferences = context.getSharedPreferences(LevelPassedService.VICTORY_LEVELS_PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("level"+level, true);
        editor.commit();
    }
}
