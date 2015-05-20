package amagi82.modularcharactersheetcreator;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import amagi82.modularcharactersheetcreator.listeners.OnGameCharacterChangedListener;
import amagi82.modularcharactersheetcreator.models.GameCharacter;
import de.hdodenhof.circleimageview.CircleImageView;

public class NewCharacterFragment extends Fragment implements View.OnClickListener {

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_FILE = 2;
    private Uri imageCaptureUri;
    private GameCharacter gameCharacter;
    private OnGameCharacterChangedListener listener;
    private MaterialDialog dialog;
    private CircleImageView iconCharacter;
    private ImageView iconRace;
    private ImageView iconClass;
    private EditText etName;
    private EditText etGameSystem;
    private EditText etRace;
    private EditText etClass;
    private CropImageView cropper;
    private int idRace;
    private int idClass;
    private int characterPosition;
    private int circleImageSize;
    private boolean isEditMode = false;
    private boolean hasCustomCharacterIcon = false;
    private boolean deletingCharacter = false;

    public NewCharacterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Check if we're editing a character or creating a new one
        Bundle arguments = getArguments();
        if(arguments != null) {
            isEditMode = getArguments().getBoolean("edit mode");
            characterPosition = getArguments().getInt("character");
        }
        getActivity().setTitle(getResources().getString(isEditMode? R.string.edit_character : R.string.new_character));

        View rootView = inflater.inflate(isEditMode? R.layout.fragment_edit_character : R.layout.fragment_new_character, container, false);
        setHasOptionsMenu(true);

        listener = (OnGameCharacterChangedListener) getActivity();

        iconCharacter = (CircleImageView) rootView.findViewById(R.id.iconCharacter);
        ImageView iconGameSystem = (ImageView) rootView.findViewById(R.id.iconGameSystem);
        iconRace = (ImageView) rootView.findViewById(R.id.iconRace);
        iconClass = (ImageView) rootView.findViewById(R.id.iconClass);
        ImageView iconTemplate = (ImageView) rootView.findViewById(R.id.iconTemplate);
        etName = (EditText) rootView.findViewById(R.id.etName);
        etGameSystem = (EditText) rootView.findViewById(R.id.etGameSystem);
        etRace = (EditText) rootView.findViewById(R.id.etRace);
        etClass = (EditText) rootView.findViewById(R.id.etClass);
        EditText etTemplate = (EditText) rootView.findViewById(R.id.etTemplate);
        circleImageSize = (int) getResources().getDimension(R.dimen.circle_icon_size);

        if(isEditMode){
            gameCharacter = MainActivity.gameCharacterList.get(characterPosition);
            iconCharacter.setImageBitmap(gameCharacter.getCharacterIcon());
            etName.setText(gameCharacter.getCharacterName());
            etGameSystem.setText(gameCharacter.getGameSystem());
            etRace.setText(gameCharacter.getCharacterRace());
            etClass.setText(gameCharacter.getCharacterClass());
            hasCustomCharacterIcon = gameCharacter.hasCustomCharacterIcon();

            Button bDelete = (Button) rootView.findViewById(R.id.bDelete);
            bDelete.setOnClickListener(this);
        }else {
            gameCharacter = new GameCharacter();
            if(!hasCustomCharacterIcon) iconCharacter.setImageBitmap(createDefaultIcon());
        }

        iconCharacter.setOnClickListener(this);
        iconGameSystem.setOnClickListener(this);
        iconRace.setOnClickListener(this);
        iconClass.setOnClickListener(this);
        iconTemplate.setOnClickListener(this);
        etTemplate.setOnClickListener(this);

        //Make a new icon for the first letter of the character's name.
        etName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!hasCustomCharacterIcon) iconCharacter.setImageBitmap(createDefaultIcon());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(isEditMode ? R.menu.menu_edit_character : R.menu.menu_new_character, menu);
        if(!isEditMode){
            menu.findItem(R.id.action_add).getActionView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveCharacter();
                    listener.OnGameCharacterAdded(gameCharacter);
                    getFragmentManager().popBackStack();

                    //Dismiss keyboard
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(etName.getWindowToken(), 0);
                }
            });
        }
    }

    private void saveCharacter() {
        gameCharacter.setCharacterName(etName.getText().toString());
        gameCharacter.setGameSystem(etGameSystem.getText().toString());
        gameCharacter.setCharacterRace(etRace.getText().toString());
        gameCharacter.setCharacterClass(etClass.getText().toString());
        gameCharacter.setCharacterIcon(((BitmapDrawable) iconCharacter.getDrawable()).getBitmap());
        gameCharacter.setHasCustomCharacterIcon(hasCustomCharacterIcon);
    }

    //On edit mode, we save changes when the user hits the back button
    @Override
    public void onDestroyView() {
        if (!deletingCharacter && isEditMode) {
            saveCharacter();
            listener.OnGameCharacterUpdated(characterPosition, gameCharacter);
        }
        super.onDestroyView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_discard) {
            isEditMode = false;
            getFragmentManager().popBackStack();
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap createDefaultIcon(){
        int textColor = getResources().getColor(R.color.white);
        int backgroundColor = getResources().getColor(R.color.primary);
        String firstLetter = etName.getText().length() > 0? etName.getText().toString().substring(0, 1) : "";

        TextPaint textPaint = new TextPaint();
        textPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setLinearText(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(getResources().getDimension(R.dimen.text_size_circle_icon));

        Rect rect = new Rect();
        rect.set(0, 0, circleImageSize, circleImageSize);

        int xPos = rect.centerX();
        int yPos = (int) (rect.centerY() - (textPaint.descent() + textPaint.ascent()) * .5);

        Bitmap bitmap = Bitmap.createBitmap(circleImageSize, circleImageSize, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(backgroundColor);
        canvas.drawText(firstLetter, xPos, yPos, textPaint);

        return bitmap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iconCharacter: //Select an image for a custom icon
                dialog = new MaterialDialog.Builder(getActivity()).items(R.array.icon_choices)
                        .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                        switch (position){
                            case 0:
                                //Get image from camera. Check to make sure device is equipped with a camera
                                if(getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                                    Intent intentTakePhoto 	 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    imageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                            "tmp_avatar_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
                                    intentTakePhoto.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageCaptureUri);
                                    try {
                                        intentTakePhoto.putExtra("return-data", true);
                                        startActivityForResult(intentTakePhoto, PICK_FROM_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                }else{
                                    Toast.makeText(getActivity(), "No camera detected on your device", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case 1:
                                //Get image from gallery
                                Intent intentFromGallery = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intentFromGallery, "Complete action using"), PICK_FROM_FILE);
                                break;
                            case 2:
                                //Just use the default icon
                                iconCharacter.setImageBitmap(createDefaultIcon());
                                hasCustomCharacterIcon = false;
                                break;
                        }
                    }
                }).show();
                break;
            case R.id.iconGameSystem:
                chooseGameSystem(R.array.game_systems, etGameSystem);
                break;
            case R.id.iconRace:
                chooseArchetype(idRace, etRace);
                break;
            case R.id.iconClass:
                chooseArchetype(idClass, etClass);
                break;
            case R.id.iconTemplate:
                Log.i(null, "Offer template if available");
                break;
            case R.id.etTemplate:
                Log.i(null, "Offer template if available");
                break;
            case R.id.bDelete:
                listener.OnGameCharacterDeleted(characterPosition);
                deletingCharacter = true;
                getFragmentManager().popBackStack();
                break;
            default:
                break;
        }
    }

    //Called when an image is selected from the camera or the gallery, and lets you crop it into an icon
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        if (data != null) imageCaptureUri = data.getData();

        try {
            //Measure the size of the image without loading it into memory
            InputStream input = getActivity().getContentResolver().openInputStream(imageCaptureUri);
            BitmapFactory.Options bitmapInfo = new BitmapFactory.Options();
            bitmapInfo.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(input, null, bitmapInfo);
            input.close();
            if ((bitmapInfo.outWidth == -1) || (bitmapInfo.outHeight == -1)) return;

            //Get screen size, and find ratio of image size to screen size
            WindowManager wm = (WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            wm = null;
            Point size = new Point();
            display.getSize(size);
            double ratio = Math.max(bitmapInfo.outHeight / (size.y*.8), bitmapInfo.outWidth / (size.x*.8));

            //Scale image size down to match screen size
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = (int) ratio;
            bitmapOptions.inPreferredConfig=Bitmap.Config.ARGB_8888;//optional
            input = getActivity().getContentResolver().openInputStream(imageCaptureUri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            input.close();

            dialog = new MaterialDialog.Builder(getActivity())
                    .title(getResources().getString(R.string.crop_image)).customView(R.layout.dialog_crop_image, false)
                    .positiveText(getResources().getString(R.string.ok))
                    .negativeText(getResources().getString(R.string.cancel))
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            Bitmap croppedBitmap = cropper.getCroppedImage();
                            croppedBitmap = Bitmap.createScaledBitmap(croppedBitmap, circleImageSize, circleImageSize, true);
                            iconCharacter.setImageBitmap(croppedBitmap);
                            hasCustomCharacterIcon = true;
                        }
                    })
                    .build();
            cropper = (CropImageView) dialog.findViewById(R.id.cropImageView);
            cropper.setImageBitmap(bitmap);
            dialog.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void chooseGameSystem(final int arrayId, final EditText editText) {
        dialog = new MaterialDialog.Builder(getActivity()).items(arrayId).itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                if (!text.equals(getString(R.string.other))) {
                    if (editText.getText().toString().equals(getString(R.string.dnd))) {
                        editText.setText(editText.getText() + " " + text + " " + getString(R.string.edition));
                        return;
                    } else {
                        editText.setText(text);
                        if (text.equals(getString(R.string.dnd))) chooseGameSystem(R.array.game_systems_dnd, editText);
                        if (text.equals(getString(R.string.world_of_darkness))) chooseGameSystem(R.array.game_systems_wod, editText);
                    }
                }
                gameSystemChanged(text.toString().contains(":") ? -position : position);
            }
        }).show();
    }

    private void chooseArchetype(final int arrayId, final EditText editText) {
        dialog = new MaterialDialog.Builder(getActivity()).items(arrayId).itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View view, int position, CharSequence text) {
                if (!text.equals(getString(R.string.other))) editText.setText(text);
            }
        }).show();
    }

    private void gameSystemChanged(int position) {
        etRace.setHint(getString(R.string.character_race));
        etClass.setHint(getString(R.string.character_class));
        switch (position) {
            case 1:
                iconRace.setVisibility(View.GONE);
                etRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.INVISIBLE);
                etClass.setHint("Profession");
                break;
            case 2:
                idRace = R.array.races_dnd;
                idClass = R.array.classes_dnd;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                break;
            case 3:
                idRace = R.array.races_dungeon_world;
                idClass = R.array.classes_dungeon_world;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                break;
            case 4:
                idRace = R.array.races_earthdawn;
                idClass = R.array.classes_earthdawn;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Discipline");
                break;
            case 5:
                idClass = R.array.classes_feng_shui;
                iconRace.setVisibility(View.GONE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Character species, if not human");
                iconClass.setVisibility(View.VISIBLE);
                etClass.setHint("Archetype");
                break;
            case 6:
                iconClass.setVisibility(View.INVISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Character concept");
                break;
            case 7:
                idRace = R.array.races_pathfinder;
                idClass = R.array.classes_pathfinder;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                break;
            case 8:
                idRace = R.array.races_shadowrun;
                idClass = R.array.classes_shadowrun;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etClass.setHint("Archetype");
                break;
            case -1:
                idClass = R.array.classes_cwod_vampire;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Clan");
                break;
            case -2:
                idRace = R.array.races_cwod_werewolf;
                idClass = R.array.classes_cwod_werewolf;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Breed");
                etClass.setHint("Tribe");
                break;
            case -3:
                idClass = R.array.classes_cwod_mage;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Tradition");
                break;
            case -4:
                idClass = R.array.classes_cwod_wraith;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Faction");
                break;
            case -5:
                idRace = R.array.classes_cwod_changeling;
                idClass = R.array.classes_cwod_changeling;
                iconRace.setVisibility(View.VISIBLE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.VISIBLE);
                etRace.setHint("Court");
                etClass.setHint("Kith");
                break;
            case -6:
                idClass = R.array.classes_nwod_vampire;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Clan");
                break;
            case -7:
                idClass = R.array.classes_nwod_werewolf;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Tribe");
                break;
            case -8:
                idClass = R.array.classes_nwod_mage;
                iconRace.setVisibility(View.GONE);
                iconClass.setVisibility(View.VISIBLE);
                etRace.setVisibility(View.GONE);
                etClass.setHint("Order");
                break;
            default:
                iconClass.setVisibility(View.INVISIBLE);
                etRace.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPause() {
        if (dialog != null) dialog.dismiss();
        super.onPause();
    }
}