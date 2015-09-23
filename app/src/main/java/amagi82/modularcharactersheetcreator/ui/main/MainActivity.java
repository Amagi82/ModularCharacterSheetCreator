package amagi82.modularcharactersheetcreator.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.colintmiller.simplenosql.NoSQL;
import com.colintmiller.simplenosql.NoSQLEntity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.databinding.ActivityMainBinding;
import amagi82.modularcharactersheetcreator.entities.characters.GameCharacter;
import amagi82.modularcharactersheetcreator.entities.characters.Sheet;
import amagi82.modularcharactersheetcreator.entities.characters.Splat;
import amagi82.modularcharactersheetcreator.entities.games.CMage;
import amagi82.modularcharactersheetcreator.entities.games.CVampire;
import amagi82.modularcharactersheetcreator.entities.games.CWerewolf;
import amagi82.modularcharactersheetcreator.ui.base.BaseActivity;
import amagi82.modularcharactersheetcreator.ui.edit.EditActivity;
import amagi82.modularcharactersheetcreator.ui.xtras.utils.Template;

public class MainActivity extends BaseActivity {
    public static final String BUCKET = "bucket";
    public static final int NONE = -1;
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

        viewModel = new MainViewModel();
        viewModel.addAll(generateSampleCharacters());

        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainViewModel(viewModel);
        binding.toolbar.setLogo(R.drawable.title_onyx);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        binding.recyclerView.setOnClickListener(new View.OnClickListener() {
//            @Override public void onClick(View v) {
//
//            }
//        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditActivity.class));
            }
        });

        updateList();
        Log.i(null, "Main Activity created");
    }

    private void updateList() {
        Intent intent = getIntent();
        if(intent.getStringExtra(UPDATE_TYPE) == null) return;
        GameCharacter character = intent.getParcelableExtra(CHARACTER);
        switch (intent.getStringExtra(UPDATE_TYPE)) {
            case ADD:
                viewModel.add(character);
                break;
            case REMOVE:
                viewModel.remove(character);
                break;
            case UPDATE:
                viewModel.update(character);
                break;
        }
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
            Log.i(null, "Creating template for "+character.name());
            Template template = new Template(this, character);
            Sheet sheet = template.createDefaultSheet();
            List<Sheet> sheets = new ArrayList<>();
            sheets.add(sheet);
            character = character.toBuilder().sheets(sheets).build();
            Log.i(null, character.name() + " contains " + character.getGameSystem().toString());
        }
        //for (GameCharacter character : characters) saveCharacter(character);

        return characters;
    }

    private void saveCharacter(GameCharacter character) {
        NoSQLEntity<GameCharacter> entity = new NoSQLEntity<>(BUCKET, character.entityId());
        entity.setData(character);
        NoSQL.with(this).using(GameCharacter.class).save(entity);
    }

//    deleteCharacter
//    NoSQL.with(this).using(GameCharacter.class).bucketId(BUCKET).entityId(event.character.entityId()).delete();

    @StringDef({ADD, REMOVE, UPDATE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface UpdateCharacters {}

    public static final String ADD = "add";
    public static final String REMOVE = "remove";
    public static final String UPDATE = "update";
    public static final String CHARACTER = "character";
    public static final String UPDATE_TYPE = "update type";
}
