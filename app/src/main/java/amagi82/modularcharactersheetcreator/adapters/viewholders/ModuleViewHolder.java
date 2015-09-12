package amagi82.modularcharactersheetcreator.adapters.viewholders;


import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.events.ModuleClickedEvent;
import amagi82.modularcharactersheetcreator.core_models.modules.Module;
import amagi82.modularcharactersheetcreator.utils.Otto;
import butterknife.Bind;
import butterknife.ButterKnife;

public class ModuleViewHolder extends RecyclerView.ViewHolder {

    @Nullable @Bind(R.id.tvText) public TextView tvText;
    @Nullable @Bind(R.id.tvTitle) public TextView tvTitle;
    public Module module;

    public ModuleViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Otto.BUS.getBus().post(new ModuleClickedEvent(module));
            }
        });
    }
}