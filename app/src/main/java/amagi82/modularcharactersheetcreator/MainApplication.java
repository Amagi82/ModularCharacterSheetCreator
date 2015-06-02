package amagi82.modularcharactersheetcreator;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;

public class MainApplication extends Application{

    private static MainApplication instance;
    private static List<GameCharacter> gameCharacterList;

    public static MainApplication getInstance(){
        return instance;
    }

    public static Context getAppContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        gameCharacterList = new ArrayList<>();
        loadGameCharacters("Characters");
        addExampleCharacters();
    }

    public synchronized static List<GameCharacter> getGameCharacters() {
        if (gameCharacterList == null) gameCharacterList = new ArrayList<>();
        return gameCharacterList;
    }

    /*
        Load and save game characters. If the load succeeds, it saves that file as a backup, and if it fails, it loads the backup.
     */
    @SuppressWarnings("unchecked")
    private void loadGameCharacters(String filename) {
        long start = System.currentTimeMillis();
        FileInputStream fis = null;
        ObjectInputStream oi = null;
        try {
            fis = openFileInput(filename);
            oi = new ObjectInputStream(fis);
            gameCharacterList = (List<GameCharacter>) oi.readObject();
            Log.i(null, filename + " save loaded successfully");
            Log.i(null, filename + " contains " + fis.getChannel().size() + " bytes");

            //Save characters to backup file if we successfully loaded the primary file
            if(filename.equals("Characters")) saveGameCharacters("Backup");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            if(filename.equals("Characters")){
                Log.i(null, "Main save file corrupted. Attempting to load backup save");
                loadGameCharacters("Backup");
            }else Log.i(null, "Both primary and backup game saves corrupted");
        } finally {
            try {
                if (fis != null) fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (oi != null) oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i(null, "loadGameCharacters took " + (System.currentTimeMillis() - start) + "ms");
    }

    //Populate list if empty - remove for production version
    private void addExampleCharacters() {
        if(gameCharacterList.size() == 0) {
            Log.i(null, "Data created");
            List<GameCharacter> gameCharacters = new ArrayList<>();
            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
            gameCharacterList = gameCharacters;

            TextModule module1 = new TextModule();
            module1.setText("Test text 1");
            TextModule module2 = new TextModule();
            module2.setText("Jurassic World comes out next month");

            for(GameCharacter character : gameCharacterList){
                character.getModuleList().add(module1);
                character.getModuleList().add(module2);
            }
        }
    }

    private void saveGameCharacters(final String filename) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
                //Save characters to internal memory
                FileOutputStream fos = null;
                ObjectOutputStream oos = null;
                try {
                    fos = openFileOutput(filename, Context.MODE_PRIVATE);
                    oos = new ObjectOutputStream(fos);
                    oos.writeObject(gameCharacterList);
                    oos.flush();
                    Log.i(null, filename + " saved");
                } catch (IOException e) {
                    Log.i(null, "Failed to save " + filename);
                    e.printStackTrace();
                } finally {
                    try{
                        if(fos != null) fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try{
                        if(oos != null) oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Log.i(null, "saveGameCharacters took " + (System.currentTimeMillis() - start) + "ms");
            }
        };
        Thread thread = new Thread(r);
        thread.start();
    }
}
