package amagi82.modularcharactersheetcreator.adapters;


import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.ModuleBlockViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.ModuleBloodPoolViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.ModuleHealthViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.ModuleStatusViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.ModuleViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.RowStatViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.RowViewHolder;
import amagi82.modularcharactersheetcreator.models.modules.BloodPoolModule;
import amagi82.modularcharactersheetcreator.models.modules.HealthModule;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.Stat;
import amagi82.modularcharactersheetcreator.models.modules.StatBlockModule;
import amagi82.modularcharactersheetcreator.models.modules.StatusModule;
import amagi82.modularcharactersheetcreator.models.modules.TitleTextBlockModule;
import amagi82.modularcharactersheetcreator.widgets.RoundedStatBar;

public class SheetAdapter extends RecyclerView.Adapter<ModuleViewHolder> {

    private Resources res;
    private List<Module> modules;
    private Module.Type types[] = Module.Type.values();

    public SheetAdapter(Resources res, List<Module> modules) {
        this.res = res;
        this.modules = modules;
    }

    @Override
    public int getItemViewType(int position) {
        return modules.get(position).getType().ordinal();
    }

    // Create new views (invoked by the layout manager). Type from getItemViewType above.
    @Override
    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (types[viewType]) {
            case HEADER:
                return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_header, parent, false));
            case HEALTH:
                return new ModuleHealthViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_health, parent, false));
            case STATBLOCK:
                return new ModuleBlockViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_block, parent, false));
            case STATUS:
                return new ModuleStatusViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_status, parent, false));
            case TITLETEXTBLOCK:
                return new ModuleBlockViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_block, parent, false));
            case BLOODPOOL:
                return new ModuleBloodPoolViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_bloodpool, parent, false));
            default:
                return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_text, parent, false));
        }
    }

//    private RowViewHolder createChildViewHolder(LinearLayout layout) {
//        return new RowViewHolder(LayoutInflater.from(layout.getContext()).inflate(R.layout.row_boldtext_text, layout, true));
//    }
//
//    private RowStatViewHolder createChildStatViewHolder(LinearLayout layout) {
//        return new RowStatViewHolder(LayoutInflater.from(layout.getContext()).inflate(R.layout.row_stat, layout, true));
//    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ModuleViewHolder vh, final int position) {
        if (vh.tvTitle != null) vh.tvTitle.setText(modules.get(position).getTitle());
        if (vh.tvText != null) vh.tvText.setText(modules.get(position).getText());

        switch (types[vh.getItemViewType()]) {
            case BLOODPOOL:
                bind((ModuleBloodPoolViewHolder) vh, (BloodPoolModule) modules.get(position));
                break;
            case HEALTH:
                bind((ModuleHealthViewHolder) vh, (HealthModule) modules.get(position));
                break;
            case STATBLOCK:
                bind((ModuleBlockViewHolder) vh, (StatBlockModule) modules.get(position));
                break;
            case STATUS:
                bind((ModuleStatusViewHolder) vh, (StatusModule) modules.get(position));
                break;
            case TITLETEXTBLOCK:
                bind((ModuleBlockViewHolder) vh, (TitleTextBlockModule) modules.get(position));
                break;
        }
    }

    private void bind(ModuleBloodPoolViewHolder vh, BloodPoolModule module) {
        vh.tvBloodPerTurn.setText(res.getString(R.string.blood_per_turn) + " " + module.getBloodPerTurn());
        vh.circleProgress.setMax(module.getBloodMax());
        vh.circleProgress.setProgress(module.getBloodCurrent());
    }

    private void bind(ModuleHealthViewHolder vh, HealthModule module) {
        if (module.isDamaged()) vh.tvDamageLevel.setText(module.getCurrentHealth().getCategory());
        vh.tvPenalty.setText(res.getQuantityString(R.plurals.dice_penalty, module.getCurrentHealth().getValue()));
        vh.statBar.setNumStars(module.getHealthLevels().size());
//        vh.statBar.setHealthAgg(module.getDamageAgg());
//        vh.statBar.setHealthLethal(module.getDamageLethal());
//        vh.statBar.setHealthBashing(module.getDamageBashing());
    }

    private void bind(ModuleBlockViewHolder vh, StatBlockModule module) {
        for (int i = 0; i<module.getStats().size(); i++) {
            Stat stat = module.getStats().get(i);
            View v = LayoutInflater.from(vh.linearLayout.getContext()).inflate(R.layout.row_stat, vh.linearLayout, true);
            RoundedStatBar statBar = (RoundedStatBar) v.findViewById(R.id.statBar);
            statBar.setId(i);
            statBar.setTitle(stat.getCategory());
            statBar.setSpecialty(stat.getSpecialty());
            statBar.setNumStars(stat.getNumStars());
            statBar.setRatingMax(stat.getValueMax());
            statBar.setRating(stat.getValueTemporary());
            statBar.setRatingBase(stat.getValue());
            statBar.setRatingMin(stat.getValueMin());
//            RowStatViewHolder rowStatViewHolder = createChildStatViewHolder(vh.linearLayout);
//            bindChild(rowStatViewHolder, stat);
        }
    }

    private void bind(ModuleStatusViewHolder vh, StatusModule module) {
        vh.statBar.setNumStars(module.getNumStars());
        vh.statBar.setRatingMax(module.getValueMax());
        vh.statBar.setRating(module.getValue());
    }

    private void bind(ModuleBlockViewHolder vh, TitleTextBlockModule module) {
        for (Stat stat : module.getStats()) {
            View v = LayoutInflater.from(vh.linearLayout.getContext()).inflate(R.layout.row_boldtext_text, vh.linearLayout, true);
            TextView tvCategory = (TextView) v.findViewById(R.id.tvCategory);
            TextView tvText = (TextView) v.findViewById(R.id.tvText);
            tvCategory.setText(stat.getCategory());
            tvText.setText(stat.getSpecialty());

//            RowViewHolder rowViewHolder = createChildViewHolder(vh.linearLayout);
//            bindChild(rowViewHolder, stat);
        }
    }

    private void bindChild(RowStatViewHolder vh, Stat stat) {
        if (vh.tvCategory != null) {
            vh.tvCategory.setText(stat.getCategory());
            if (vh.tvText != null) {
                vh.tvText.setVisibility(View.VISIBLE);
                vh.tvText.setText(stat.getSpecialty());
            }
        } else {
            vh.statBar.setTitle(stat.getCategory());
            vh.statBar.setSpecialty(stat.getSpecialty());
        }
        vh.statBar.setNumStars(stat.getNumStars());
        vh.statBar.setRatingMax(stat.getValueMax());
        vh.statBar.setRating(stat.getValueTemporary());
        vh.statBar.setRatingBase(stat.getValue());
        vh.statBar.setRatingMin(stat.getValueMin());
    }

    private void bindChild(RowViewHolder vh, Stat stat) {
        vh.tvCategory.setText(stat.getCategory());
        vh.tvText.setText(stat.getSpecialty());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return modules.size();
    }
}