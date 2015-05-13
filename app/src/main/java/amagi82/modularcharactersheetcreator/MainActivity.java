package amagi82.modularcharactersheetcreator;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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

import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.adapters.MainRecyclerViewAdapter;
import amagi82.modularcharactersheetcreator.listeners.OnFabClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterAddedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemLongClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;


public class MainActivity extends AppCompatActivity implements OnFabClickedListener, OnItemClickedListener , OnItemLongClickedListener, OnGameCharacterAddedListener{

    public static ArrayList<GameCharacter> gameCharacterList = new ArrayList<>();
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private FrameLayout container;
    private FragmentManager fm = getSupportFragmentManager();
    private Toolbar toolbar;
    private MaterialMenuDrawable materialMenu;
    private NewCharacterFragment newCharacterFragment;
    private Menu menu;
    private RecyclerView recyclerView;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadGameCharacters();
        addExampleCharacters();

        //Add toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.characters));
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        materialMenu.setNeverDrawTouch(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Up navigation
                if (fm.getBackStackEntryCount() > 0) fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        //Container holds recycler view and is replaced with fragments
        container = (FrameLayout) findViewById(R.id.container);
        container.setId(R.id.container_id);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true); //Improves performance if changes in content never change layout size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new MainRecyclerViewAdapter(this);
        //recyclerViewAdapter.setHasStableIds(true);
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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Floating action button clicked - add new character
                newCharacterFragment = new NewCharacterFragment();
                recyclerView.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
                fm.beginTransaction().replace(container.getId(), newCharacterFragment).addToBackStack(null).commit();
                toolbar.setNavigationIcon(materialMenu);
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.X, false);
            }
        });

        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fm.getBackStackEntryCount() == 0) {
                    recyclerView.setVisibility(View.VISIBLE);
                    resetDefaultMenu();
                }
            }
        });
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
                for (int position = selectedItems.size()-1; position >= 0; position--) {
                    gameCharacterList.remove(position);
                    recyclerViewAdapter.notifyItemRemoved(position);
                }
                resetDefaultMenu();
                break;
            case R.id.action_edit:
                newCharacterFragment = new NewCharacterFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("character", getSelectedItems().iterator().next());
                bundle.putBoolean("edit mode", true);
                newCharacterFragment.setArguments(bundle);

                resetDefaultMenu();
                recyclerView.setVisibility(View.GONE);

                fm.beginTransaction().replace(container.getId(), newCharacterFragment).addToBackStack(null).commit();
                toolbar.setNavigationIcon(materialMenu);
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.X, false);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //
        if(newCharacterFragment != null && newCharacterFragment.isVisible()) {
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
        }else if(selectedItems.size() > 0){
            resetDefaultMenu();
        }else {
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

    //Populate list if empty - remove for production version
    private void addExampleCharacters() {
        if(gameCharacterList.size() == 0) {
            Log.i(null, "Data created");
            ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "Gangrel"));
            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "Brujah"));
            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "Tremere"));
            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "Ventrue"));
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
        resetDefaultMenu();
        recyclerView.setVisibility(View.GONE);

        CharacterSheetFragment fragment = new CharacterSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("character", position);
        fragment.setArguments(bundle);
        fm.beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW, false);
    }

    @Override
    public void onCharacterLongClicked(int position) {
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.ARROW, false);
        fab.setVisibility(View.GONE);
        toolbar.setBackgroundColor(getResources().getColor(R.color.grey_500));
        if(Build.VERSION.SDK_INT >= 21) getWindow().setStatusBarColor(getResources().getColor(R.color.grey_700));

        toggleSelection(position);
    }

    private void resetDefaultMenu(){
        menu.clear();
        getMenuInflater().inflate(R.menu.menu_main, menu);
        toolbar.setNavigationIcon(null);
        toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
        if(Build.VERSION.SDK_INT >= 21) getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
        selectedItems.clear();
        materialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
        fab.setVisibility(View.VISIBLE);
        setTitle(getString(R.string.characters));
    }

    private void selectedItemsChanged() {
        int count = selectedItems.size();
        setTitle(getString(R.string.selected_count, count));
        menu.clear();
        getMenuInflater().inflate(count == 0 ? R.menu.menu_main : count == 1 ? R.menu.menu_main_longclick_single : R.menu.menu_main_longclick_multiple, menu);
        if(count == 0) resetDefaultMenu();
    }
    public void toggleSelection(int position) {
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position);
        }
        else {
            selectedItems.put(position, true);
        }
        recyclerViewAdapter.notifyItemChanged(position);
        selectedItemsChanged();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }
}
