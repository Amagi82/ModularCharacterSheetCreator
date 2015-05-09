package amagi82.modularcharactersheetcreator;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialmenu.MaterialMenuDrawable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.listeners.OnFabClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextOnlyModule;


public class MainActivity extends AppCompatActivity implements OnFabClickedListener, OnItemClickedListener {

    private FrameLayout container;
    private MaterialMenuDrawable materialMenu;
    private Toolbar toolbar;
    private FragmentManager fm;
    private NewCharacterFragment newCharacterFragment;
    public static ArrayList<GameCharacter> gameCharacterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(null, "onCreate called");
        Log.i("gameCharacterList = ", gameCharacterList.toString());

        loadGameCharacters();

        Log.i("gameCharacterList = ", gameCharacterList.toString());

        if(MainActivity.gameCharacterList.size() == 0) {
            Log.i(null,"Data created");
            ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "Gangrel"));
            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "Brujah"));
            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "Tremere"));
            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "Ventrue"));
            MainActivity.gameCharacterList = gameCharacters;

            TextOnlyModule module1 = new TextOnlyModule();
            module1.setText("Test text 1");
            TextOnlyModule module2 = new TextOnlyModule();
            module2.setText("Jurassic World comes out next month");

            for(GameCharacter character : gameCharacterList){
                character.getModuleList().add(module1);
                character.getModuleList().add(module2);
            }
        }

        //Add toolbar
        fm = getSupportFragmentManager();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.characters));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle your drawable state here
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                toolbar.setNavigationIcon(null);
                materialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        materialMenu.setNeverDrawTouch(true);

        //Set MainFragment
        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        container = new FrameLayout(this);
        container.setId(R.id.container_id);
        fm.beginTransaction().replace(container.getId(), new MainFragment()).commit();
        fragmentContainer.addView(container);

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                if (fm.getBackStackEntryCount() == 0) {
                    toolbar.setNavigationIcon(null);
                    materialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(newCharacterFragment != null && newCharacterFragment.isVisible()){
            new MaterialDialog.Builder(this).title("Cancel").content("Are you sure you want to discard this character?")
                    .positiveText("KEEP EDITING").negativeText("DISCARD").callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        }
            }).show();
        }else{
            super.onBackPressed();
        }
    }

    //Load characters from save file
    @SuppressWarnings("unchecked")
    private void loadGameCharacters() {
        FileInputStream fis = null;
        ObjectInputStream oi = null;
        try {
            fis = openFileInput("Characters");
            oi = new ObjectInputStream(fis);
            gameCharacterList = (ArrayList<GameCharacter>) oi.readObject();
        } catch (IOException e) {
            Log.e("InternalStorage", e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
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
    }

    @Override
    protected void onStop() {
        super.onStop();

        //Save characters to internal memory
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = openFileOutput("Characters", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(gameCharacterList);
            oos.flush();
            Log.i(null, "Characters saved");
        } catch (IOException e) {
            Log.e("InternalStorage", e.getMessage());
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
    }

    @Override
    public void onCharacterAdded() {
        newCharacterFragment = new NewCharacterFragment();
        fm.beginTransaction().replace(container.getId(), newCharacterFragment).addToBackStack(null).commit();
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.X, false);
    }

    @Override
    public void onModuleAdded() {

    }

    @Override
    public void onModuleClicked(ArrayList<Module> module, int position) {

    }

    @Override
    public void onCharacterClicked(int position) {
        CharacterSheetFragment fragment = new CharacterSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("character", position);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW, false);
    }
}
