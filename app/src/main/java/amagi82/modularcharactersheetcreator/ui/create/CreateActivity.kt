package amagi82.modularcharactersheetcreator.ui.create

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.databinding.CreateActivityBinding
import amagi82.modularcharactersheetcreator.extras.*
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.models.Sheet
import amagi82.modularcharactersheetcreator.models.games.templates.Template
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity
import amagi82.modularcharactersheetcreator.ui.create._events.*
import amagi82.modularcharactersheetcreator.ui.crop.CropActivity
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.MenuItem
import android.view.View
import com.squareup.otto.Subscribe
import java.util.*

/*
    Screen used to create or modify the core of a GameCharacter. Choose game gameId, character axis(commonly referred to as "splats", name, and photo.
    Character sheet settings are not handled here (see Sheet).

    Navigation:
    This screen can be reached from either MainActivity (for new characters) or from SheetActivity (for modifying existing characters).
    Exiting this screen takes you back to the MainActivity.
 */
class CreateActivity : BaseActivity() {
    private val binding by lazy { DataBindingUtil.setContentView<CreateActivityBinding>(this, R.layout.create_activity) }
    private var createViewModel: CreateViewModel = CreateViewModel(GameCharacter())
    internal var character: GameCharacter = GameCharacter()
    internal var backstack: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.toolbar.navigationIcon = icon(R.drawable.ic_close_24dp, Color.WHITE)
        binding.toolbar.inflateMenu(R.menu.menu_create)
        binding.toolbar.setNavigationOnClickListener { end(RESULT_CANCELED) }

        character = intent.getParcelableExtra<GameCharacter>(CHARACTER) ?: GameCharacter()

        createViewModel = CreateViewModel(character)
        binding.setCreateViewModel(createViewModel)
        //If an update to a page > 0 is handled immediately, the adapter doesn't get set up.
        Handler().postDelayed({ createViewModel.update(character) }, 10)
    }

    @Subscribe fun gameSelected(event: GameSelectedEvent) {
        //character = character.withGame(event.gameId)
        update()
    }

    @Subscribe fun axisUpdated(event: AxisUpdateEvent) {
        createViewModel.update(character, event.splatId)
        binding.appbar.setExpanded(true)
    }

    @Subscribe fun axisSelected(event: AxisSelectedEvent) {
        if (createViewModel.page.get() == LEFT)
        //character = character.withLeft(event.splatId)
        else {
            val system = character.gameSystem()
            if (system?.checkLeft() == true) {
                val leftId = system?.updateLeft(character.leftId, event.splatId)
                //if (leftId != character.leftId()) character = character.withLeft(leftId)
            }
            //character = character.withRight(event.splatId)
        }
        update()
    }

    @Subscribe fun nameChanged(event: NameChangedEvent) {
        //character = character.withName(event.name)
        createViewModel.update(character)
    }

    @Subscribe fun keyboardVisible(event: KeyboardVisibleEvent) {
        createViewModel.softKeyboardVisible()
    }

    @Subscribe fun resetItem(event: ResetSelectionEvent) {
        goBack(event.toPage)
    }

    override fun onBackPressed() {
        if (backstack > 0)
            goBack(backstack - 1)
        else {
            setResult(Activity.RESULT_CANCELED)
            super.onBackPressed()
        }
    }

    private fun goBack(toPage: Int) {
        //character = character.removeProgress(toPage)
        update()
    }

    private fun update() {
        createViewModel.update(character)
        binding.appbar.setExpanded(true)
        //backstack = character.getProgress()
    }

    fun onActionPhoto(view: View) {
        if (character.image == null)
            getCroppedImage()
        else {
            AlertDialog.Builder(this).setItems(R.array.portrait_choices) { dialog, which ->
                if (which == 0) {
                    //Remove the image and use the default icon
                    //character = character.withImage(null, null)
                    createViewModel.update(character)
                } else
                    getCroppedImage()
            }.show()
        }
    }

    private fun getCroppedImage() {
        startActivityForResult(Intent(this@CreateActivity, CropActivity::class.java).putExtra(CHARACTER, character), REQ_DEFAULT)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) return
        character = data.getParcelableExtra<GameCharacter>(CHARACTER)
        createViewModel.update(character)
    }

    fun onFabClicked(view: View) {
        if (character.sheets.size == 0) {
            val defaultSheet = Template.create(resources, character)
            val sheets = ArrayList<Sheet>(1)
            //sheets.add(defaultSheet)
            //character = character.withSheets(sheets)
        }
        end(RESULT_OK, Intent().putExtra(CHARACTER, character))
    }

    fun onActionDelete(item: MenuItem) {
        end(RESULT_DELETED, Intent().putExtra(CHARACTER, character))
    }
}
