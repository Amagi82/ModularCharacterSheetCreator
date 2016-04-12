package amagi82.modularcharactersheetcreator.ui.main

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.databinding.MainActivityBinding
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.games.Game
import amagi82.modularcharactersheetcreator.models.games.templates.Template
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity
import amagi82.modularcharactersheetcreator.ui.create.CreateActivity
import amagi82.modularcharactersheetcreator.ui.sheet.SheetActivity
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import auto.parcelgson.gson.AutoParcelGsonTypeAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.squareup.otto.Subscribe
import java.util.*

/*
    Main screen the user launches into. Contains a list of characters the user has created.

    Navigation:
    Floating Action Button navigates to Create screen, where you can create a new character.
    Clicking on a character takes you to the Sheet screen.
 */
class MainActivity : BaseActivity() {
    private var viewModel: MainViewModel? = null
    private var binding: MainActivityBinding? = null
    private var gson: Gson? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        gson = GsonBuilder().registerTypeAdapterFactory(AutoParcelGsonTypeAdapterFactory()).create()
        viewModel = MainViewModel()
        loadSavedCharacters()

        binding?.mainViewModel = viewModel
        //binding.toolbar.setLogo(R.drawable.title_onyx);
    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({ binding?.fab?.show() }, 400)
    }

    override fun onPause() {
        super.onPause()
        binding?.fab?.hide()
    }

    private fun loadSavedCharacters() {
        Log.i("MainActivity", "Shared Preferences contains: " + saved.all.toString())
        var characters: List<GameCharacter>? = ArrayList()
        if (saved.contains(BaseActivity.LIST)) {
            val listType = object : TypeToken<List<GameCharacter>>() {

            }.type
            characters = gson!!.fromJson<List<GameCharacter>>(saved.getString(BaseActivity.LIST, null), listType)
        }
        if (characters == null || characters.size == 0) characters = generateSampleCharacters()
        viewModel?.addAll(characters)
    }

    //Temporary sample data for testing purposes. Eventually add a splash screen to direct the user to create a new character if the list is empty
    private fun generateSampleCharacters(): List<GameCharacter> {
        Log.i("MainActivity", "Creating data")
        val characters = ArrayList<GameCharacter>()
        characters.add(GameCharacter("Thomas Anstis", Game.CVAMPIRE, 104, 1001))
        characters.add(GameCharacter("Tom Lytton", Game.CVAMPIRE, 102, 1003))
        characters.add(GameCharacter("Georgia Johnson", Game.CVAMPIRE, 121, 1001))
        characters.add(GameCharacter("Augustus von Rabenholtz", Game.CVAMPIRE, 123, 1001))
        characters.add(GameCharacter("Dr. Von Natsi", Game.CMAGE, 1, 107))
        characters.add(GameCharacter("Stormwalker", Game.CWEREWOLF, 8, 101))

        for (i in characters.indices) {
            val defaultSheet = Template.create(resources, characters[i]) ?: return emptyList()
            val sheets = ArrayList<Sheet>(1)
            sheets.add(defaultSheet)
            //characters[i] = characters[i].withSheets(sheets)
        }
        putSaved(BaseActivity.LIST, gson!!.toJson(characters))

        return characters
    }

    private fun saveCharacters() {
        putSaved(BaseActivity.LIST, gson!!.toJson(viewModel!!.characters))
    }

    override fun onActivityResult(@ReqCode requestCode: Int, @ResultCode resultCode: Int, data: Intent) {
        Log.i("MainActivity", "onActivityResult resultCode = " + resultCode)
        if (resultCode == Activity.RESULT_CANCELED) return

        val character = data.getParcelableExtra<GameCharacter>(BaseActivity.CHARACTER)
        Log.i("MainActivity", "onActivityResult: character returned: " + character)

        if (resultCode == BaseActivity.RESULT_EDIT) {
            startActivityForResult(Intent(this@MainActivity, CreateActivity::class.java).putExtra(BaseActivity.CHARACTER, character), BaseActivity.MODIFY)
        } else if (resultCode == Activity.RESULT_OK && requestCode == BaseActivity.ADD) {
            viewModel!!.add(character)
            saveCharacters()
        } else if (resultCode == Activity.RESULT_OK && requestCode == BaseActivity.MODIFY) {
            viewModel!!.update(character)
            saveCharacters()
        } else if (resultCode == BaseActivity.RESULT_DELETED) {
            viewModel!!.remove(character)
            saveCharacters()
        }
    }

    @Subscribe fun onItemClicked(event: CharacterClickedEvent) {
        startActivityForResult(Intent(this@MainActivity, SheetActivity::class.java).putExtra(BaseActivity.CHARACTER, event.character), BaseActivity.DEFAULT)
    }

    fun onClickAddCharacter(view: View) {
        startActivityForResult(Intent(this@MainActivity, CreateActivity::class.java), BaseActivity.ADD)
    }
}