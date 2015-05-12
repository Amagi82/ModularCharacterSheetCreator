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

import amagi82.modularcharactersheetcreator.listeners.OnFabClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnItemLongClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;


public class MainActivity extends AppCompatActivity implements OnFabClickedListener, OnItemClickedListener , OnItemLongClickedListener{

    private FrameLayout container;
    private MaterialMenuDrawable materialMenu;
    private Toolbar toolbar;
    private FragmentManager fm;
    private NewCharacterFragment newCharacterFragment;
    public static ArrayList<GameCharacter> gameCharacterList = new ArrayList<>();
    private Menu menu;
    private MainRecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    FloatingActionButton fab;

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

            TextModule module1 = new TextModule();
            module1.setText("Test text 1");
            TextModule module2 = new TextModule();
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
                recyclerViewAdapter.clearSelections();
                selectedItemsChanged();
            }
        });
        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
        materialMenu.setNeverDrawTouch(true);


        setTitle(getResources().getString(R.string.characters));

        container = (FrameLayout) findViewById(R.id.container);
        container.setId(R.id.container_id);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter = new MainRecyclerViewAdapter(gameCharacterList, this);
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
                    fab.setVisibility(View.VISIBLE);
                    recyclerViewAdapter.notifyItemInserted(0);  //TODO: make handle set as well as add
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
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.i(null, "MainActivity options selected item "+item.toString());
        switch(item.getItemId()){
            case R.id.action_settings:
                return true; //is return necessary here??
            case R.id.action_delete:
                List<Integer> selectedItemPositions = recyclerViewAdapter.getSelectedItems();
                for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
                    recyclerViewAdapter.removeData(selectedItemPositions.get(i));
                }
                recyclerViewAdapter.clearSelections();
                selectedItemsChanged();
                return true;
            case R.id.action_edit:
                newCharacterFragment = new NewCharacterFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("character", recyclerViewAdapter.getSelectedItems().iterator().next());
                bundle.putBoolean("edit mode", true);
                newCharacterFragment.setArguments(bundle);

                recyclerViewAdapter.clearSelections();
                selectedItemsChanged();
                recyclerView.setVisibility(View.GONE);

                fm.beginTransaction().replace(container.getId(), newCharacterFragment).addToBackStack(null).commit();
                toolbar.setNavigationIcon(materialMenu);
                materialMenu.animateIconState(MaterialMenuDrawable.IconState.X, false);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
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
        }else if(recyclerViewAdapter.getSelectedItemCount() > 0){
            recyclerViewAdapter.clearSelections();
            selectedItemsChanged();
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

    private void toggleSelection(int index) {
        recyclerViewAdapter.toggleSelection(index);
        setTitle(getString(R.string.selected_count, recyclerViewAdapter.getSelectedItemCount()));
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
    public void onAddCharacter() {
        newCharacterFragment = new NewCharacterFragment();
        fm.beginTransaction().replace(container.getId(), newCharacterFragment).addToBackStack(null).commit();
        toolbar.setNavigationIcon(materialMenu);
        materialMenu.animateIconState(MaterialMenuDrawable.IconState.X, false);
    }

    @Override
    public void onAddModule() {
    }

    @Override
    public void onModuleClicked(ArrayList<Module> module, int position) {
    }

    @Override
    public void onCharacterClicked(int position) {
        recyclerViewAdapter.clearSelections();
        selectedItemsChanged();
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

        Log.i("position ", position + " clicked");
        toggleSelection(position);
        selectedItemsChanged();
    }

    private void selectedItemsChanged() {
        int count = recyclerViewAdapter.getSelectedItemCount();
        menu.clear();
        Log.i(null, "selected item count = " + count);
        getMenuInflater().inflate(count ==0? R.menu.menu_main : count ==1? R.menu.menu_main_longclick_single : R.menu.menu_main_longclick_multiple, menu);
        if(count == 0){
            toolbar.setNavigationIcon(null);
            materialMenu.setIconState(MaterialMenuDrawable.IconState.BURGER);
            toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
            if(Build.VERSION.SDK_INT >= 21) getWindow().setStatusBarColor(getResources().getColor(R.color.primary_dark));
            fab.setVisibility(View.VISIBLE);
            setTitle(getString(R.string.characters));
        }
    }
}
