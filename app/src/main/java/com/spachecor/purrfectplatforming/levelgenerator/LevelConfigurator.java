package com.spachecor.purrfectplatforming.levelgenerator;

import android.annotation.SuppressLint;
import android.content.Context;

import com.spachecor.purrfectplatforming.gameobject.Scenery;
import com.spachecor.purrfectplatforming.gameobject.Trophy;
import com.spachecor.purrfectplatforming.gameobject.character.Enemy;
import com.spachecor.purrfectplatforming.gameobject.character.Gamer;
import com.spachecor.purrfectplatforming.gameobject.platform.Platform;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase LevelConfigurator que se encarga de configurar un nivel desde un json de niveles
 * @see Level
 * @author Selene
 * @version 1.0
 */
public class LevelConfigurator {
    private static final String LEVEL_FILE;
    static {
        LEVEL_FILE = "levels.json";
    }

    /**
     * Funcion que toma un level de un fichero de almacenamiento JSON
     * @param context El contexto de la Activity que lo llame
     * @param numberLevel El nivel del level
     * @return El level correspondiente al nivel introducido(puede dar null si no encuentra el nivel)
     */
    public static Level getLevel(Context context, Integer numberLevel){
        return LevelConfigurator.loadLevelFromJson(context, numberLevel, LevelConfigurator.loadJSONFromAsset(context));
    }

    /**
     * Funcion que devuelve el JSON que guarda los niveles convertido en String para su procesamiento
     * @param context El contexto de la Activity que llamó a la funcion para obtener el level
     * @return El JSON que contiene los niveles convertido en String
     */
    private static String loadJSONFromAsset(Context context){
        String json = null;
        try{
            InputStream is = context.getAssets().open(LevelConfigurator.LEVEL_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return json;
    }

    /**
     * Funcion que crea el objeto Level a partir del String que representa el JSON que contiene los niveles
     * @param context El contexto de la Activity que ha solicitado el Level
     * @param numberLevel El nivel del Level
     * @param jsonString El String que representa al JSON de los niveles
     * @return El nivel ya formado
     */
    @SuppressLint("DiscouragedApi")
    private static Level loadLevelFromJson(Context context, Integer numberLevel, String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray levelsArray = jsonObject.getJSONArray("levels");
            //cargamos el nivel
            JSONObject levelJson = levelsArray.getJSONObject(numberLevel);
            //cargamos el escenario
            JSONObject sceneryJson = levelJson.getJSONObject("scenery");
            int backgroundGravity = sceneryJson.getInt("gravity");
            String backgroundName = sceneryJson.getString("background");
            int backgroundResId = context.getResources().getIdentifier(backgroundName, "drawable", context.getPackageName());
            Scenery scenery = new Scenery(backgroundGravity, backgroundResId);
            //cargamos el jugador
            JSONObject gamerJson = levelJson.getJSONObject("gamer");
            JSONObject spritesGamer = gamerJson.getJSONObject("sprites");
            int[] gamerSprites = new int[6];
            for(int i = 0;i<gamerSprites.length; i++){
                gamerSprites[i] = context.getResources().getIdentifier(spritesGamer.getString("sprite"+i), "drawable", context.getPackageName());
                System.out.println("Sprite gamer nº"+i+", valor: "+spritesGamer.getString("sprite"+i));
            }
            Gamer gamer = new Gamer(
                    context,
                    gamerJson.getInt("positionX"),
                    gamerJson.getInt("positionY"),
                    gamerJson.getInt("width"),
                    gamerJson.getInt("height"),
                    gamerSprites,
                    gamerJson.getInt("jumpForce"),
                    gamerJson.getInt("walkingVelocity")
            );
            //cargamos las plataformas
            List<Platform> platforms = new ArrayList<>();
            JSONArray platformsArray = levelJson.getJSONArray("platforms");
            for (int i = 0; i < platformsArray.length(); i++) {
                JSONObject platformJson = platformsArray.getJSONObject(i);
                String platformSpriteName = platformJson.getString("sprite");
                int[] platformSpriteResId = new int[]{
                        context.getResources().getIdentifier(platformSpriteName, "drawable", context.getPackageName())
                };
                Platform.PlatformType type = Platform.PlatformType.valueOf(platformJson.getString("type"));
                platforms.add(new Platform(
                        context,
                        platformJson.getInt("positionX"),
                        platformJson.getInt("positionY"),
                        platformJson.getInt("width"),
                        platformJson.getInt("height"),
                        platformSpriteResId,
                        type
                ));
            }
            //cargamos los enemigos
            List<Enemy> enemies = new ArrayList<>();
            JSONArray enemiesArray = levelJson.getJSONArray("enemies");
            for (int i = 0; i < enemiesArray.length(); i++) {
                JSONObject enemyJson = enemiesArray.getJSONObject(i);
                String enemySpriteName = enemyJson.getString("sprite");
                int[] enemySpriteResId = new int[]{
                        context.getResources().getIdentifier(enemySpriteName, "drawable", context.getPackageName())
                };
                enemies.add(new Enemy(
                        context,
                        enemyJson.getInt("positionX"),
                        enemyJson.getInt("positionY"),
                        enemyJson.getInt("width"),
                        enemyJson.getInt("height"),
                        enemySpriteResId,
                        enemyJson.getInt("walkingVelocity"),
                        enemyJson.getBoolean("mobile"),
                        enemyJson.getInt("minRangeOfMotion"),
                        enemyJson.getInt("maxRangeOfMotion")
                ));
            }
            //cargamos el trofeo
            JSONObject trophyJson = levelJson.getJSONObject("trophy");
            String trophySpriteName = trophyJson.getString("sprite");
            int[] trophySpriteResId = new int[]{
                    context.getResources().getIdentifier(trophySpriteName, "drawable", context.getPackageName())
            };
            Trophy trophy = new Trophy(
                    context,
                    trophyJson.getInt("positionX"),
                    trophyJson.getInt("positionY"),
                    trophyJson.getInt("width"),
                    trophyJson.getInt("height"),
                    trophySpriteResId
            );
            //retornamos el nivel
            return new Level(numberLevel, scenery, gamer, platforms, enemies, trophy);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
