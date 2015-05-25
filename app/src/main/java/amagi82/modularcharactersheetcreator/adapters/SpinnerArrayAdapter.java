package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

/*
    A modified version of ArrayAdapter which uses the first item in the string array as a prompt, but does not display it in the dropdown list
 */

public class SpinnerArrayAdapter<T> extends ArrayAdapter<CharSequence>{

    private Context context;
    private LayoutInflater inflater;
    private static CharSequence prompt; //First item of string array
    private static int spinnerItemLayout = R.layout.spinner_item; //Layout used for selected item, not the dropdown
    private boolean isPromptDisplayed = true;

    private SpinnerArrayAdapter(Context context, int resource, List<CharSequence> objects) {
        super(context, resource, objects);
        this.context = context;
        inflater = LayoutInflater.from(context);
        super.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public static SpinnerArrayAdapter<CharSequence> createFromResource(Context context, int arrayId){
        List<CharSequence> objects = new ArrayList<>(Arrays.asList(context.getResources().getTextArray(arrayId)));
        prompt = objects.get(0);
        objects.remove(0);
        return new SpinnerArrayAdapter<>(context, spinnerItemLayout, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // This provides the View for the Selected Item in the Spinner, not the dropdown (unless dropdownView is not set).
        // getView is called before iterating through the list, and not called again until an item is selected, so use this to display the prompt
        if (isPromptDisplayed) {
            View view = inflater.inflate(spinnerItemLayout, parent, false);
            TextView text = (TextView) view;
            text.setText(prompt);
            text.setTextColor(context.getResources().getColor(R.color.textMaterialSecondary)); //text should be grey before selection
            isPromptDisplayed = false;
            return view;
        }
        return super.getView(position, convertView, parent);
    }
}
