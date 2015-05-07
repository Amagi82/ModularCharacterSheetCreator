package amagi82.modularcharactersheetcreator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.listeners.OnCharacterAddedListener;
import amagi82.modularcharactersheetcreator.listeners.OnCharacterClickedListener;
import amagi82.modularcharactersheetcreator.listeners.OnModuleAddedListener;
import amagi82.modularcharactersheetcreator.listeners.OnModuleClickedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import amagi82.modularcharactersheetcreator.models.modules.Module;


public class MainActivity extends AppCompatActivity implements OnCharacterAddedListener, OnCharacterClickedListener, OnModuleAddedListener, OnModuleClickedListener {

    FrameLayout container;
    public static ArrayList<GameCharacter> gameCharacterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.characters));

        //Set MainFragment
        FrameLayout fragmentContainer = (FrameLayout) findViewById(R.id.container);
        container = new FrameLayout(this);
        container.setId(R.id.container_id);
        getSupportFragmentManager().beginTransaction().replace(container.getId(), new MainFragment()).commit();
        fragmentContainer.addView(container);

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
    public void onCharacterAdded() {
//        CharacterFragment fragment = new CharacterFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction().replace(MainActivity.container.getId(), fragment).addToBackStack(null).commit();
    }

    @Override
    public void onModuleAdded() {

    }

    @Override
    public void onModuleClicked(ArrayList<? extends Module> module, int position) {

    }

    @Override
    public void onCharacterClicked(int position) {
        CharacterSheetFragment fragment = new CharacterSheetFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("character", position);
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(container.getId(), fragment).addToBackStack(null).commit();
    }
}
