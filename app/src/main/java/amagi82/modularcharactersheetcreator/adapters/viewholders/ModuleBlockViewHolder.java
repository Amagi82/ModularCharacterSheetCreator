package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.view.View;
import android.widget.LinearLayout;

import amagi82.modularcharactersheetcreator.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ModuleBlockViewHolder extends ModuleViewHolder {

    @Bind(R.id.linearLayout) public LinearLayout linearLayout;

    public ModuleBlockViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}