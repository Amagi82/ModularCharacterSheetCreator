package amagi82.modularcharactersheetcreator.ui.crop

import amagi82.modularcharactersheetcreator.R
import amagi82.modularcharactersheetcreator.databinding.CropActivityBinding
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity
import android.app.Activity
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.View
import java.io.IOException

/*
    This screen takes an image from the gallery and allows the user to crop it.
 */
class CropActivity : AppCompatActivity() {
    private val character: GameCharacter by lazy { intent.getParcelableExtra<GameCharacter>(BaseActivity.CHARACTER)  }
    private var binding: CropActivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<CropActivityBinding>(this, R.layout.crop_activity)

        //character = intent.getParcelableExtra<GameCharacter>(BaseActivity.CHARACTER)
//        if (character == null) {
//            Log.i("CropActivity", "ERROR: character is null")
//            finish()
//        }
        val intent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, getString(R.string.complete_action_using)), BaseActivity.DEFAULT)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) {
            finish()
            return
        }
        try {
            binding!!.cropImageView.imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, data.data)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    fun onFabClicked(view: View) {
        val croppedImage = binding!!.cropImageView.croppedBitmap
        val uri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character"))

        val palette = Palette.from(croppedImage).generate()
        var swatch: Palette.Swatch? = null
        if (palette.darkVibrantSwatch != null)
            swatch = palette.darkVibrantSwatch
        else if (palette.vibrantSwatch != null)
            swatch = palette.vibrantSwatch
        else {
            for (s in palette.swatches) {
                if (swatch == null || swatch.population < s.population) swatch = s
            }
        }
//        character = character.copy(
//                GameCharacter.CharacterImage(uri.toString(), croppedImage.height, croppedImage.width),
//                if (swatch == null) null else GameCharacter.ColorScheme(swatch.rgb, swatch.bodyTextColor, swatch.titleTextColor))

        setResult(Activity.RESULT_OK, Intent().putExtra(BaseActivity.CHARACTER, character))
        finish()
    }
}
