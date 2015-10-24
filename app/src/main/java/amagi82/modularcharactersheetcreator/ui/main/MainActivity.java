package amagi82.modularcharactersheetcreator.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityMainBinding;
import amagi82.modularcharactersheetcreator.models.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.models.characters.Sheet;
import amagi82.modularcharactersheetcreator.models.characters.Splat;
import amagi82.modularcharactersheetcreator.models.games.CMage;
import amagi82.modularcharactersheetcreator.models.games.CVampire;
import amagi82.modularcharactersheetcreator.models.games.CWerewolf;
import amagi82.modularcharactersheetcreator.models.templates.Template;
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;

/*
    Main screen the user launches into. Contains a list of characters the user has created.

    Navigation:
    Floating Action Button navigates to Edit screen, where you can create a new character.
    Clicking on a character takes you to the Sheet screen.
 */
public class MainActivity extends BaseActivity {
    private MainViewModel viewModel;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(null, "Main Activity onCreate");
        //NoSQL.with(this).using(GameCharacter.class).bucketId("bucket").delete();
//        if (savedInstanceState != null) {
//            try {
//                if(savedInstanceState.getString(CURRENT_CHARACTER) != null) {
//                    currentCharacter = LoganSquare.parse(savedInstanceState.getString(CURRENT_CHARACTER), GameCharacter.class);
//                }
//                characters = LoganSquare.parseList(savedInstanceState.getString(CHARACTERS), GameCharacter.class);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else
        //loadSavedCharacters();

        viewModel = new MainViewModel(generateSampleCharacters());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(viewModel);
        binding.toolbar.setLogo(R.drawable.title_onyx);
    }

    private void loadSavedCharacters() {
        //characters = new ArrayList<>();
//        NoSQL.with(this).withDeserializer(logan).using(GameCharacter.class).bucketId(BUCKET).orderBy(new DataComparator<GameCharacter>() {
//            @Override public int compare(NoSQLEntity<GameCharacter> o1, NoSQLEntity<GameCharacter> o2) {
//                return o1.getData().getTimeStamp() > o2.getData().getTimeStamp() ? -1 : o1.getData().getTimeStamp() < o2.getData().getTimeStamp() ? 1 : 0;
//            }
//        }).retrieve(new RetrievalCallback<GameCharacter>() {
//            @Override public void retrievedResults(List<NoSQLEntity<GameCharacter>> entities) {
//                Log.i(null, "Found " + entities.size() + " sample characters");
//                if (entities.size() == 0) {
//                    Log.i(null, "generating sample characters");
//                    generateSampleCharacters();
//                } else for (NoSQLEntity<GameCharacter> entity : entities) characters.add(entity.getData());
//
//                fm.beginTransaction().add(R.id.container, new MainFragment()).commit();
//            }
//        });
    }

    private List<GameCharacter> generateSampleCharacters() {
        Log.i(null, "Creating data");
        List<GameCharacter> characters = new ArrayList<>();
        characters.add(GameCharacter.create("Thomas Anstis", new CVampire(), Splat.create(R.string.gangrel, R.string.url_cwod_vampire_clan_gangrel), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Tom Lytton", new CVampire(), Splat.create(R.string.brujah_antitribu, R.string.url_cwod_vampire_antitribu_brujah), Splat.create(R.string.anarchs, R.string.url_cwod_vampire_sect_anarchs)));
        characters.add(GameCharacter.create("Georgia Johnson", new CVampire(), Splat.create(R.string.tremere, R.string.url_cwod_vampire_clan_tremere), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Augustus von Rabenholtz", new CVampire(), Splat.create(R.string.ventrue, R.string.url_cwod_vampire_clan_ventrue), Splat.create(R.string.camarilla, R.string.url_cwod_vampire_sect_camarilla)));
        characters.add(GameCharacter.create("Dr. Von Natsi", new CMage(), Splat.create(R.string.traditions, R.string.url_cwod_mage_faction_traditions), Splat.create(R.string.scions_of_ether, R.string.url_cwod_mage_tradition_scions_of_ether)));
        characters.add(GameCharacter.create("Stormwalker", new CWerewolf(), Splat.create(R.string.glass_walkers, R.string.url_cwod_werewolf_tribe_glass_walkers), Splat.create(R.string.ahroun, R.string.url_cwod_werewolf_auspice_ahroun)));

        for (GameCharacter character : characters) {
            Log.i(null, "Creating template for " + character.name());
            Sheet defaultSheet;
            if(character.sheets().size() == 0) {
                defaultSheet = Template.create(getResources(), character);
                List<Sheet> sheets = new ArrayList<>(1);
                sheets.add(defaultSheet);
                character = character.toBuilder().sheets(sheets).build();
            }
            Log.i(null, character.name() + " contains " + character.getGameSystem().toString());
        }
        //for (GameCharacter character : characters) saveCharacter(character);

        return characters;
    }

    private void saveCharacter(GameCharacter character) {
//        NoSQLEntity<GameCharacter> entity = new NoSQLEntity<>(BUCKET, character.entityId());
//        entity.setData(character);
//        NoSQL.with(this).using(GameCharacter.class).save(entity);
    }

//    deleteCharacter
//    NoSQL.with(this).using(GameCharacter.class).bucketId(BUCKET).entityId(event.character.entityId()).delete();


    @Override protected void onActivityResult(@ReqCode int requestCode, @ResultCode int resultCode, Intent data) {
        Log.i("MainActivity", "onActivityResult resultCode = " + resultCode);
        if (resultCode == RESULT_CANCELED) return;

        GameCharacter character = data.getParcelableExtra(CHARACTER);
        int position = data.getIntExtra(POSITION, -1);
        Log.i("MainActivity", "onActivityResult: position: " + position);
        Log.i("MainActivity", "onActivityResult: character returned: " + character);

        if (resultCode == RESULT_OK && requestCode == ADD) viewModel.add(character);
        else if (resultCode == RESULT_OK && requestCode == MODIFY && position >= 0) viewModel.update(character, position);
        else if (resultCode == RESULT_DELETED && position >= 0) viewModel.remove(position);

        //TODO: make sure characters are modified in the database
    }

    public void onClickAddCharacter(View view) {
        startActivityForResult(new Intent(MainActivity.this, EditActivity.class), ADD);
    }

    @IntDef({ADD, MODIFY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ReqCode {
    }

    @IntDef({RESULT_CANCELED, RESULT_OK, RESULT_DELETED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
    }

    public static final int ADD = 1;
    public static final int MODIFY = 2;
    public static final int RESULT_DELETED = 3;

    public static final String CHARACTER = "CHARACTER";
    public static final String POSITION = "POSITION";
}
