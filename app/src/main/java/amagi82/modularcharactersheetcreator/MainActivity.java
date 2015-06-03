package amagi82.modularcharactersheetcreator;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.PathInterpolator;

import com.squareup.otto.Subscribe;

import amagi82.modularcharactersheetcreator.events.CreateCharacterEvent;
import amagi82.modularcharactersheetcreator.utils.BusProvider;


public class MainActivity extends AppCompatActivity {


    //    implements OnFabClickedListener, OnItemClickedListener , OnItemLongClickedListener,
//    OnGameCharacterChangedListener, View.OnClickListener, FragmentManager.OnBackStackChangedListener
//
//    public static ArrayList<GameCharacter> gameCharacterList = new ArrayList<>();
//    private OnBackPressedListener backListener;
//    private boolean isHomeScreen = true;
//    private CoordinatorLayout container;
    private FragmentManager fm = getSupportFragmentManager();
//    private Toolbar toolbar;
//    private MaterialMenuDrawable materialMenu;
//    private Menu menu;
//    private LinearLayoutManager layoutManager;
//    private MainRecyclerViewAdapter recyclerViewAdapter;
//    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new MainFragment();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Add transition info to new fragment
            //fragment.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
            fragment.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
        }
        fm.beginTransaction().add(R.id.container, fragment).commit();


//        //Add toolbar
//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        collapsingToolbar.setTitle(getString(R.string.characters));
//        materialMenu = new MaterialMenuDrawable(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
//        toolbar.setNavigationOnClickListener(this);
//
//        //Container holds recycler view and is replaced with fragments
//        container = (CoordinatorLayout) findViewById(R.id.container);
//        container.setId(R.id.container_id);
//
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.setHasFixedSize(true); //Improves performance if changes in content never change layout size
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerViewAdapter = new MainRecyclerViewAdapter(this, gameCharacterList);
//        recyclerView.setAdapter(recyclerViewAdapter);
//
//        //Set up the Floating Action Button
//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(this);

//          fm.addOnBackStackChangedListener(this);

//        //After orientation change, restore state of fab, main recyclerview, and toolbar.
//        if(savedInstanceState != null) {
//            isHomeScreen = savedInstanceState.getBoolean("isHomeScreen");
//            if(!isHomeScreen){
////                fab.hide();
//                recyclerView.setVisibility(View.GONE);
//                toolbar.setNavigationIcon(materialMenu);
//                materialMenu.setIconState(savedInstanceState.getBoolean("isMaterialMenuX", true)?
//                        MaterialMenuDrawable.IconState.X : MaterialMenuDrawable.IconState.ARROW);
//            }
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register ourselves so that we can provide the initial value.
        BusProvider.getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Always unregister when an object no longer should be on the bus.
        BusProvider.getBus().unregister(this);
    }

    @Subscribe
    public void onCreateCharacter(CreateCharacterEvent event) {
        final Fragment fragment = new CreateCharacterFragment();
        final FloatingActionButton fab = event.fab;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        final int width = 2*size.x / fab.getWidth();

        Log.i(null, "width == "+width);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            fragment.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.fade));
//        }
        int moveX =fab.getWidth()*2;
        int moveY =fab.getHeight()*2;

        fab.animate()
                .translationX(moveX)
                .translationY(moveY)
                .setInterpolator(new PathInterpolator(1, 0))
                .setDuration(800)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        fab.setImageResource(0);
                        fab.animate().scaleX(width).scaleY(width).setDuration(2000).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                Log.i(null, "width after == "+fab.getWidth());
                                fm.beginTransaction()
                                        .replace(R.id.container, fragment)
                                        .addToBackStack("transaction")
                                        .commit();
                            }
                        });
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //this.menu = menu; //Get a menu reference so we can change it elsewhere
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action item clicks
        switch (item.getItemId()) {
            case R.id.action_delete:
//                //Save the characters and positions temporarily in case the user wants to undo the delete
//                final ArrayList<GameCharacter> storedCharacters = new ArrayList<>();
//                final SparseBooleanArray storedPositions = selectedItems.clone();
//                for(int i = 0; i<storedPositions.size(); i++) storedCharacters.add(gameCharacterList.get(storedPositions.keyAt(i)));
//
//                //Delete the characters
//                for (int i = selectedItems.size() - 1; i >= 0; i--) removeCharacter(selectedItems.keyAt(i));
//
//

//                //Allow the user to undo delete
//                SnackbarManager.show(
//                        Snackbar.with(getApplicationContext())
//                                .text(getString(R.string.deleted_count, storedPositions.size())) // text to display
//                                .actionLabel(getString(R.string.undo))
//                                .actionColor(getResources().getColor(R.color.accent))
//                                .actionListener(new ActionClickListener() {
//                                    @Override
//                                    public void onActionClicked(Snackbar snackbar) {
//                                        //User clicked UNDO, so add the stored characters back in their original positions
//                                        for (int i = 0; i < storedPositions.size(); i++) addCharacter(storedPositions.keyAt(i), storedCharacters.get(i));
//                                    }
//                                }).eventListener(new SnackbarEventListener(fab)), this); //Hide the floating action button while Snackbar present
                break;
            case R.id.action_edit:
//                CreateCharacterFragment fragment = new CreateCharacterFragment();
//                Bundle bundle = new Bundle();
//                bundle.putInt("character", selectedItems.keyAt(0));
//                bundle.putBoolean("edit mode", true);
//                fragment.setArguments(bundle);
//                backListener = fragment;
//                attachFragment(fragment, MaterialMenuDrawable.IconState.ARROW);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void attachFragment(Fragment fragment, MaterialMenuDrawable.IconState iconState) {
//
//        Pair<View, String> p1 = Pair.create((View) toolbar, "toolbar");
//        Pair<View, String> p2 = Pair.create((View) fab, "fab");
//        @SuppressWarnings("unchecked") ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1, p2);
//        fragment.setArguments(options.toBundle());
//        fm.beginTransaction().replace(R.id.fragment, fragment).addToBackStack(null).commit();
//
//        toolbar.setNavigationIcon(materialMenu);
//        materialMenu.animateIconState(iconState);
//        isHomeScreen = false;
//    }

//    private void addCharacter(int position, GameCharacter gameCharacter){
//        gameCharacterList.add(position, gameCharacter);
//        recyclerViewAdapter.notifyItemInserted(position);
//        layoutManager.scrollToPosition(position);
//    }
//
//    private void updateCharacter(int position, GameCharacter gameCharacter){
//        gameCharacterList.set(position, gameCharacter);
//        recyclerViewAdapter.notifyItemChanged(position);
//        layoutManager.scrollToPosition(position);
//    }
//
//    private void removeCharacter(int position){
//        layoutManager.scrollToPosition(position);
//        gameCharacterList.remove(position);
//        recyclerViewAdapter.notifyItemRemoved(position);
//    }

    /*
        Load and save game characters. If the load succeeds, it saves that file as a backup, and if it fails, it loads the backup.
     */
//    @SuppressWarnings("unchecked")
//    private void loadGameCharacters(String filename) {
//        long start = System.currentTimeMillis();
//        FileInputStream fis = null;
//        ObjectInputStream oi = null;
//        try {
//            fis = openFileInput(filename);
//            oi = new ObjectInputStream(fis);
//            gameCharacterList = (ArrayList<GameCharacter>) oi.readObject();
//            Log.i(null, filename + " save loaded successfully");
//            Log.i(null, filename + " contains " + fis.getChannel().size() + " bytes");
//
//            //Save characters to backup file if we successfully loaded the primary file
//            if(filename.equals("Characters")) saveGameCharacters("Backup");
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//            if(filename.equals("Characters")){
//                Log.i(null, "Main save file corrupted. Attempting to load backup save");
//                loadGameCharacters("Backup");
//            }else Log.i(null, "Both primary and backup game saves corrupted");
//        } finally {
//            try {
//                if (fis != null) fis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (oi != null) oi.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        Log.i(null, "loadGameCharacters took " + (System.currentTimeMillis() - start) + "ms");
//    }
//
//    //Populate list if empty - remove for production version
//    private void addExampleCharacters() {
//        if(gameCharacterList.size() == 0) {
//            Log.i(null, "Data created");
//            ArrayList<GameCharacter> gameCharacters = new ArrayList<>();
//            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
//            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
//            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
//            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
//            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
//            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
//            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
//            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
//            gameCharacters.add(new GameCharacter("Thomas Anstis", "Vampire", "", "Gangrel"));
//            gameCharacters.add(new GameCharacter("Tom Lytton", "Vampire", "", "Brujah"));
//            gameCharacters.add(new GameCharacter("Georgia Johnson", "Vampire", "", "Tremere"));
//            gameCharacters.add(new GameCharacter("Augustus von Rabenholtz", "Vampire", "", "Ventrue"));
//            gameCharacterList = gameCharacters;
//
//            TextModule module1 = new TextModule();
//            module1.setText("Test text 1");
//            TextModule module2 = new TextModule();
//            module2.setText("Jurassic World comes out next month");
//
//            for(GameCharacter character : gameCharacterList){
//                character.getModuleList().add(module1);
//                character.getModuleList().add(module2);
//            }
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        //Save characters to default file
//        saveGameCharacters("Characters");
//    }
//
//    private void saveGameCharacters(final String filename) {
//        Runnable r = new Runnable() {
//            @Override
//            public void run() {
//                long start = System.currentTimeMillis();
//                //Save characters to internal memory
//                FileOutputStream fos = null;
//                ObjectOutputStream oos = null;
//                try {
//                    fos = openFileOutput(filename, Context.MODE_PRIVATE);
//                    oos = new ObjectOutputStream(fos);
//                    oos.writeObject(gameCharacterList);
//                    oos.flush();
//                    Log.i(null, filename + " saved");
//                } catch (IOException e) {
//                    Log.i(null, "Failed to save " + filename);
//                    e.printStackTrace();
//                } finally {
//                    try{
//                        if(fos != null) fos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    try{
//                        if(oos != null) oos.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.i(null, "saveGameCharacters took " + (System.currentTimeMillis() - start) + "ms");
//            }
//        };
//        Thread thread = new Thread(r);
//        thread.start();
//    }
//
//    //Save state for orientation change
//    @Override
//    public void onSaveInstanceState(Bundle savedInstanceState) {
//        savedInstanceState.putBoolean("isHomeScreen", isHomeScreen);
//        savedInstanceState.putBoolean("isMaterialMenuX", materialMenu.getIconState() == MaterialMenuDrawable.IconState.X);
//        super.onSaveInstanceState(savedInstanceState);
//    }

//    /*
//        Handle user clicks and interface methods
//     */
//    @Override
//    public void OnGameCharacterAdded(GameCharacter character) {
//        addCharacter(0, character);
//    }
//
//    @Override
//    public void OnGameCharacterUpdated(int position, GameCharacter character) {
//        updateCharacter(position, character);
//    }
//
//    @Override
//    public void OnGameCharacterDeleted(final int position) {
//        final GameCharacter gameCharacter = gameCharacterList.get(position);
//        removeCharacter(position);
//    }
//
//    @Override
//    public void onAddModule() {
//    }
//
//    @Override
//    public void onModuleClicked(ArrayList<Module> module, int position) {
//    }
//
//    @Override
//    public void onCharacterClicked(int position) {
//        CharacterSheetFragment fragment = new CharacterSheetFragment();
//        Bundle bundle = new Bundle();
//        bundle.putInt("character", position);
//        fragment.setArguments(bundle);
//        attachFragment(fragment, MaterialMenuDrawable.IconState.ARROW);
//    }
//
//    @Override
//    public void onCharacterLongClicked(int position) {
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.fab) {
//            //Floating action button clicked - add new character
//            CreateCharacterFragment fragment = new CreateCharacterFragment();
//            backListener = fragment;
//            attachFragment(fragment, MaterialMenuDrawable.IconState.ARROW);
//        }else {
//            //Up navigation clicked
//            backListener.onBackPressed();
//            if (fm.getBackStackEntryCount() > 0) fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//        }
//    }
//
//    @Override
//    public void onBackPressed() {
//        backListener.onBackPressed();
//        super.onBackPressed();
//
//    }
//
//    @Override
//    public void onBackStackChanged() {
//        if (fm.getBackStackEntryCount() == 0) {
//            isHomeScreen = true;
//        }
//    }
}
