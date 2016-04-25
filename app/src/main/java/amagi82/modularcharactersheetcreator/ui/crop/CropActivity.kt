package amagi82.modularcharactersheetcreator.ui.crop

import amagi82.modularcharactersheetcreator.extras.*
import amagi82.modularcharactersheetcreator.models.GameCharacter
import amagi82.modularcharactersheetcreator.ui._base.BaseActivity
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.graphics.Palette
import org.jetbrains.anko.onClick
import org.jetbrains.anko.setContentView
import java.io.IOException

/*
    This screen takes an image from the gallery and allows the user to crop it.
 */
class CropActivity : BaseActivity() {
    private val character by lazy { intent.getParcelableExtra<GameCharacter>(CHARACTER) }
    private val v = CropView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        v.setContentView(this)

        if (character == null) {
            log("ERROR: character is null")
            finish()
        }
        if (!hasPermission(WRITE_EXTERNAL_STORAGE)) requestPermission(0, WRITE_EXTERNAL_STORAGE)
        else selectImageFromGallery()

        v.fab.onClick {
            val croppedImage = v.cropImageView.croppedBitmap
            val uri = Uri.parse(MediaStore.Images.Media.insertImage(contentResolver, croppedImage, "OnyxPathCharacterPortrait", "Portrait used by your character"))

            val palette = Palette.from(croppedImage).generate()
            val swatch = palette.darkVibrantSwatch ?: palette.vibrantSwatch ?: palette.swatches.sortedBy { it.population }.lastOrNull()

            setResult(Activity.RESULT_OK, Intent().putExtra(CHARACTER, character.copy(
                    image = GameCharacter.CharacterImage(uri.toString(), croppedImage.height, croppedImage.width),
                    colorScheme = if (swatch != null) GameCharacter.ColorScheme(swatch.rgb, swatch.bodyTextColor, swatch.titleTextColor) else null)))
            finish()
        }
    }

    private fun selectImageFromGallery() {
        val galleryIntent = Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT)
        if (galleryIntent.resolveActivity(packageManager) != null) startActivityForResult(galleryIntent, REQ_DEFAULT)
        else {
            log("ERROR: no gallery found")
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (hasPermission(WRITE_EXTERNAL_STORAGE)) selectImageFromGallery()
        else {
            log("failed to get permission")
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK || data == null) log("Failed to get picture with resultCode: $resultCode and data: $data")
        else try {
            v.cropImageView.imageBitmap = scaleBitmapToScreen(data.data)
            return
        } catch (e: IOException) {
            e.printStackTrace()
        }
        finish()
    }

    private fun scaleBitmapToScreen(uri: Uri): Bitmap? {
        //Scale image down to available space to save memory
        try {
            var inputStream = contentResolver.openInputStream(uri)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true //First pass gets image dimensions without loading into memory
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream?.close()
            val imageHeight = options.outHeight
            val imageWidth = options.outWidth
            val targetHeight = screenHeight()
            val targetWidth = screenWidth()
            var inSampleSize = 1 //Scale factor
            if (imageHeight > targetHeight || imageWidth > targetWidth) {
                val halfHeight = imageHeight / 2
                val halfWidth = imageWidth / 2
                // Calculate the largest inSampleSize value that is a power of 2 and keeps both height and width larger than the requested height and width.
                while (halfHeight / inSampleSize >= targetHeight && halfWidth / inSampleSize >= targetWidth) {
                    inSampleSize *= 2
                }
            }
            options.inSampleSize = inSampleSize
            options.inJustDecodeBounds = false
            inputStream = contentResolver.openInputStream(uri) //Cannot use same stream
            val bitmap = BitmapFactory.decodeStream(inputStream, null, options) //Scale image to inSampleSize
            inputStream?.close()
            return bitmap
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}
