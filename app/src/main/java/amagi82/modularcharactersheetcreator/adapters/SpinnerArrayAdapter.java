package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;

public class SpinnerArrayAdapter<T> extends ArrayAdapter<CharSequence>{

    private Context context;
    private LayoutInflater inflater;

    public SpinnerArrayAdapter(Context context, int resource, CharSequence[] objects) {
        super(context, resource, objects);
        this.context = context;
        inflater = LayoutInflater.from(context);
        super.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public static SpinnerArrayAdapter<CharSequence> createFromResource(Context context, int arrayId){
        return new SpinnerArrayAdapter<>(context, R.layout.spinner_item, context.getResources().getTextArray(arrayId));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // This provides the View for the Selected Item in the Spinner, not the dropdown (unless dropdownView is not set).
        if (position == 0) {
            View v;
            if (convertView == null) {
                v = inflater.inflate(R.layout.spinner_item, parent, false);
            } else {
                v = convertView;
            }
            TextView tvSpinnerItem = (TextView) v.findViewById(R.id.tvSpinnerItem);
            tvSpinnerItem.setText(getItem(position));
            tvSpinnerItem.setTextColor(android.R.attr.editTextColor);
            tvSpinnerItem.setTextAppearance(context, R.style.TextAppearance_AppCompat_Medium_Inverse);
            return v;
        }
        return super.getView(position - 1, convertView, parent);
    }
}
