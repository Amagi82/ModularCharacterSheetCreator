package amagi82.modularcharactersheetcreator.adapters.viewholders;

import android.view.View;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.widgets.RoundedStatBarBlock;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ModuleBlockViewHolder extends ModuleViewHolder {

    @Bind(R.id.statBarBlock) public RoundedStatBarBlock statBarBlock;

    public ModuleBlockViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}