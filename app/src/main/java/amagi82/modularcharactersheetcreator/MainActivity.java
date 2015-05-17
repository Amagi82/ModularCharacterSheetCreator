package amagi82.modularcharactersheetcreator;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;
import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.adapters.MainRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.listeners.OnFabClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterAddedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemLongClickedListener;
import amagi82.modularcharactersheetcreator.listeners.SnackbarEventListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;


public class MainActivity extends AppCompatActivity implements OnFabClickedListener, OnItemClickedListener , OnItemLongClickedListener,
        OnGameCharacterAddedListener, View.OnClickListener, FragmentManager.OnBackStackChangedListener{

    public static ArrayList<GameCharacter> gameCharacterList = new ArrayList<>();
    public static SparseBooleanArray selectedItems = new SparseBooleanArray();
    private FrameLayout container;
    private FragmentManager fm = getSupportFragmentManager();
    private Toolbar toolbar;
    private MaterialMenuDrawable materialMenu;
    private Menu menu;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(gameCharacterList.size() == 0) loadGameCharacters("Characters");
        addExampleCharacters();

        //Add toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.characters));
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        toolbar.setNavigationOnClickListener(this);

        //Container holds recycler view and is replaced with fragments
        container = (FrameLayout) findViewById(R.id.container);
        container.setId(R.id.container_id);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true); //Improves performance if changes in content never change layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new MainRecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        //Set up the Floating Action Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_person_add_white_24dp);
        fab.attachToRecyclerView(recyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
            }

            @Override
            public void onScrollUp() {
            }
        });
        fab.setOnClickListener(this);

        fm.addOnBackStackChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu; //Get a menu reference so we can change it elsewhere
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action item clicks
        switch(item.getItemId()){
            case R.id.action_settings:
                break;
            case R.id.action_delete:
                //Save the characters and positions temporarily in case the user wants to undo the delete
                final ArrayList<GameCharacter> storedCharacters = new ArrayList<>();
                final SparseBooleanArray storedPositions = selectedItems.clone();
                for(int i = 0; i<storedPositions.size(); i++) storedCharacters.add(gameCharacterList.get(storedPositions.keyAt(i)));

                //Delete the characters
                for (int i = selectedItems.size() - 1; i >= 0; i--) {
                    gameCharacterList.remove(selectedItems.keyAt(i));
                    recyclerViewAdapter.notifyItemRemoved(selectedItems.keyAt(i));
                }
                selectedItems.clear();
                resetDefaultMenu();

                //Allow the user to undo delete
                SnackbarManager.show(
                        Snackbar.with(getApplicationContext())
                                .text(getString(R.string.deleted_count, storedPositions.size())) // text to display
                                .actionLabel(getString(R.string.undo))
                                .actionColor(getResources().getColor(R.color.accent))
                                .actionListener(new ActionClickListener() {
                                    @Override
                                    public void onActionClicked(Snackbar snackbar) {
                                        //User clicked UNDO, so add the stored characters back in their original positions
                                        for (int i = 0; i < storedPositions.size(); i++) {
                                            gameCharacterList.add(storedPositions.keyAt(i), storedCharacters.get(i));
                                            recyclerViewAdapter.notifyItemInserted(storedPositions.keyAt(i));
                                        }
                                    }
                                }).eventListener(new SnackbarEventListener(fab)), this); //Hide the floating action button while Snackbar present
                break;
            case R.id.action_edit:
                NewCharacterFragment fragment = new NewCharacterFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("character", selectedItems.keyAt(0));
                bundle.putBoolean("edit mode", true);
                fragment.setArguments(bundle);
                attachFragment(fragment, MaterialMenuDrawable.IconState.X);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attachFragment(Fragment fragment, MaterialMenuDrawable.IconState iconState) {
        resetDefaultMenu();
        fab.hide();
        recyclerView.setVisibility(View.GONE);
        fm.beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(iconState);
    }


    /*
        Handle item selection
     */
    private void resetDefaultMenu(){
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        toolbar.setNavigationIcon(null);
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        if(Build.VERSION.SDK_INT >= 21) getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
        if(selectedItems.size()>0){
            for(int i=gameCharacterList.size()-1; i>= 0; i--){
                if(selectedItems.get(i)) {
                    selectedItems.delete(i);
                    recyclerViewAdapter.notifyItemChanged(i);
                }
            }
        }
        materialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
        fab.show();

        setTitle(getString(R.string.characters));
    }

    private void selectedItemsChanged() {
        int count = selectedItems.size();
        setTitle(getString(R.string.selected_count, count));
        menu.clear();
        getMenuInflater().inflate(count == 0 ? R.menu.menu_main : count == 1 ? R.menu.menu_main_longclick_single : R.menu.menu_main_longclick_multiple, menu);
        if(count == 0) resetDefaultMenu();
    }

    private void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        }
        else {
            selectedItems.put(position, true);
        }
        recyclerViewAdapter.notifyItemChanged(position);
        selectedItemsChanged();
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
            gameCharacterList = (ArrayList<GameCharacter>) oi.readObject();
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
            ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
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

    @Override
    protected void onStop() {
        super.onStop();
        //Save characters to default file
        saveGameCharacters("Characters");
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

    /*
        Handle user clicks and interface methods
     */
    @Override
    public void OnGameCharacterAdded(GameCharacter character) {
        gameCharacterList.add(0, character);
        recyclerViewAdapter.notifyItemInserted(0);
    }

    @Override
    public void OnGameCharacterUpdated(int position, GameCharacter character) {
        gameCharacterList.set(position, character);
        recyclerViewAdapter.notifyItemChanged(position);
    }

    @Override
    public void onAddModule() {
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
        attachFragment(fragment, MaterialMenuDrawable.IconState.ARROW);
    }

    @Override
    public void onCharacterLongClicked(int position) {
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW);
        fab.hide();
        toolbar.setBackgroundColor(getResources().getColor(R.color.grey_600));
        if(Build.VERSION.SDK_INT >= 21) getWindow().setStatusBarColor(getResources().getColor(R.color.grey_700));

        toggleSelection(position);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            //Floating action button clicked - add new character
            attachFragment(new NewCharacterFragment(), MaterialMenuDrawable.IconState.X);
        }else {
            //Up navigation clicked
            if (fm.getBackStackEntryCount() > 0) fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            resetDefaultMenu();
        }
    }

    @Override
    public void onBackPressed() {
        if(selectedItems.size() > 0){
            resetDefaultMenu();
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public void onBackStackChanged() {
        if (fm.getBackStackEntryCount() == 0) {
            recyclerView.setVisibility(View.VISIBLE);
            resetDefaultMenu();
        }
    }
}
