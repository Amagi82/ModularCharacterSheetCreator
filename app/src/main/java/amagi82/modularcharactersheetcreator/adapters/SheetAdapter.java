package amagi82.modularcharactersheetcreator.adapters;


import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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
import amagi82.modularcharactersheetcreator.widgets.StatRatingBar;

public class SheetAdapter extends RecyclerView.Adapter<ModuleViewHolder> {

    private Resources res;
    private List<Module> modules;

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
        Module.Type types[] = Module.Type.values();
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

    private RowViewHolder createChildViewHolder(LinearLayout layout){
        return new RowViewHolder(LayoutInflater.from(layout.getContext()).inflate(R.layout.row_boldtext_text, layout, false));
    }
    private RowStatViewHolder createChildViewHolder(LinearLayout layout, int layoutId){
        return new RowStatViewHolder(LayoutInflater.from(layout.getContext()).inflate(layoutId, layout, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ModuleViewHolder vh, final int position) {
        Module module = modules.get(position);
        if (vh.tvTitle != null) vh.tvTitle.setText(module.getTitle());
        if(vh.tvText != null) vh.tvText.setText(module.getText());

        switch (module.getType()) {
            case HEALTH:
                bind((ModuleHealthViewHolder) vh, (HealthModule)module);
                break;
            case STATBLOCK:
                bind((ModuleBlockViewHolder) vh, (StatBlockModule)module);
                break;
            case STATUS:
                bind((ModuleStatusViewHolder) vh, (StatusModule)module);
                break;
            case TITLETEXTBLOCK:
                bind((ModuleBlockViewHolder) vh, (TitleTextBlockModule)module);
                break;
            case BLOODPOOL:
                bind((ModuleBloodPoolViewHolder) vh, (BloodPoolModule)module);
                break;
        }
    }

    private void bind(ModuleHealthViewHolder vh, HealthModule module) {
        vh.tvDamageLevel.setText(module.getCurrentHealth().getCategory());
        vh.tvPenalty.setText(module.getCurrentHealth().getValue());
        vh.statRatingBar.setMaxRating(module.getHealthLevels().size());
        vh.statRatingBar.setHealthAgg(module.getDamageAgg());
        vh.statRatingBar.setHealthLethal(module.getDamageLethal());
        vh.statRatingBar.setHealthBashing(module.getDamageBashing());
    }

    private void bind(ModuleBlockViewHolder vh, StatBlockModule module) {
        for(Stat stat : module.getStats()){
            RowStatViewHolder rowStatViewHolder = createChildViewHolder(vh.linearLayout, module.getRowLayoutId());
            bindChild(rowStatViewHolder, stat);
        }
    }

    private void bind(ModuleStatusViewHolder vh, StatusModule module) {
        vh.statRatingBar.setBarType(module.isCircle()? StatRatingBar.BarType.CIRCLE : StatRatingBar.BarType.SQUARE);
        vh.statRatingBar.setNumStars(module.getNumStars());
        vh.statRatingBar.setMaxRating(module.getValueMax());
        vh.statRatingBar.setRating(module.getValue());
    }

    private void bind(ModuleBlockViewHolder vh, TitleTextBlockModule module) {
        for(Stat stat : module.getStats()){
            RowViewHolder rowViewHolder = createChildViewHolder(vh.linearLayout);
            bindChild(rowViewHolder, stat);
        }
    }

    private void bind(ModuleBloodPoolViewHolder vh, BloodPoolModule module) {
        vh.tvBloodPerTurn.setText(res.getString(R.string.blood_per_turn)+" "+module.getBloodPerTurn());
        vh.circleProgress.setMax(module.getBloodMax());
        vh.circleProgress.setProgress(module.getBloodCurrent());
    }

    private void bindChild(RowStatViewHolder vh, Stat stat){
        vh.tvCategory.setText(stat.getCategory());
        if(vh.tvText != null) vh.tvText.setText(stat.getSpecialty());
        vh.statRatingBar.setNumStars(stat.getValueMax());
        vh.statRatingBar.setRating(stat.getValue());
        vh.statRatingBar.setTempRating(stat.getValueTemporary());
    }

    private void bindChild(RowViewHolder vh, Stat stat){
        vh.tvCategory.setText(stat.getCategory());
        if(vh.tvText != null) vh.tvText.setText(stat.getSpecialty());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return modules.size();
    }


}