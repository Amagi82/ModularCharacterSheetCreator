package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.widgets.RoundedStatBar;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ModuleHealthViewHolder extends ModuleViewHolder {

    @Bind(R.id.tvDamageLevel) public TextView tvDamageLevel;
    @Bind(R.id.tvPenalty) public TextView tvPenalty;
    @Bind(R.id.statBar) public RoundedStatBar statBar;

    public ModuleHealthViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}