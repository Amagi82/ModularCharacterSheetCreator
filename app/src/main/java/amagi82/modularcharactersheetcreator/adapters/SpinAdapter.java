package amagi82.modularcharactersheetcreator.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;

/**
 * Decorator Adapter to allow a Spinner to show a 'Nothing Selected...' initially
 * displayed instead of the first choice in the Adapter.
 */
public class SpinAdapter implements SpinnerAdapter, ListAdapter {

    protected static final int EXTRA = 1;
    protected SpinnerAdapter adapter;
    protected Context context;
    protected int nothingSelectedLayout;
    protected int nothingSelectedDropdownLayout;
    protected LayoutInflater layoutInflater;
    private String hint;

    /**
     * Use this constructor to have NO 'Select One...' item, instead use the standard prompt or nothing at all.
     * @param spinnerAdapter wrapped Adapter.
     * you want text grayed out like a prompt...
     */
    public SpinAdapter(SpinnerAdapter spinnerAdapter, String hint, Context context) {
        this(spinnerAdapter, hint, -1, context);
    }

    /**
     * Use this constructor to Define your 'Select One...' layout as the first row in the returned choices.
     * If you do this, you probably don't want a prompt on your spinner or it'll have two 'Select' rows.
     * @param spinnerAdapter wrapped Adapter. Should probably return false for isEnabled(0)
     * @param nothingSelectedDropdownLayout layout for your 'Select an Item...' in the dropdown.
     */
    public SpinAdapter(SpinnerAdapter spinnerAdapter, String hint, int nothingSelectedDropdownLayout, Context context) {
        this.adapter = spinnerAdapter;
        this.context = context;
        this.hint = hint;
        this.nothingSelectedDropdownLayout = nothingSelectedDropdownLayout;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        // This provides the View for the Selected Item in the Spinner, not the dropdown (unless dropdownView is not set).
        if (position == 0) {
            View v = layoutInflater.inflate(R.layout.spinner_item, parent, false);
            TextView tvSpinnerItem = (TextView) v.findViewById(R.id.tvSpinnerItem);
            tvSpinnerItem.setText(hint);
            tvSpinnerItem.setTextColor(android.R.attr.editTextColor);
            tvSpinnerItem.setTextAppearance(context, R.style.TextAppearance_AppCompat_Medium_Inverse);
            return v;
        }
        return adapter.getView(position - EXTRA, null, parent); // Could re-use the convertView if possible.
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        // Android BUG! http://code.google.com/p/android/issues/detail?id=17128 - Spinner does not support multiple view types
        if (position == 0) {
            return nothingSelectedDropdownLayout == -1 ? new View(context) : getNothingSelectedDropdownView(parent);
        }
        // Could re-use the convertView if possible, use setTag...
        return adapter.getDropDownView(position - EXTRA, null, parent);
    }

    /**
     * Override this to do something dynamic... For example, "Pick your favorite of these 37".
     */
    protected View getNothingSelectedDropdownView(ViewGroup parent) {
        return layoutInflater.inflate(nothingSelectedDropdownLayout, parent, false);
    }

    @Override
    public int getCount() {
        int count = adapter.getCount();
        return count == 0 ? 0 : count + EXTRA;
    }

    @Override
    public Object getItem(int position) {
        return position == 0 ? null : adapter.getItem(position - EXTRA);
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position >= EXTRA ? adapter.getItemId(position - EXTRA) : position - EXTRA;
    }

    @Override
    public boolean hasStableIds() {
        return adapter.hasStableIds();
    }

    @Override
    public boolean isEmpty() {
        return adapter.isEmpty();
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        adapter.registerDataSetObserver(observer);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {
        adapter.unregisterDataSetObserver(observer);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0; // Don't allow the 'nothing selected' item to be picked.
    }
}