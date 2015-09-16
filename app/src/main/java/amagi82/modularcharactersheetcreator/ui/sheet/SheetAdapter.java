package amagi82.modularcharactersheetcreator.ui.sheet;


public class SheetAdapter {
//        extends RecyclerView.Adapter<ModuleViewHolder> {
//
//    private Resources res;
//    private List<Module> modules;
//    private Module.Type types[] = Module.Type.values();
//
//    public SheetAdapter(Resources res, List<Module> modules) {
//        this.res = res;
//        this.modules = modules;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return modules.get(position).getType().ordinal();
//    }
//
//    // Create new views (invoked by the layout manager). Type from getItemViewType above.
//    @Override
//    public ModuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        switch (types[viewType]) {
//            case HEADER:
//                return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_header, parent, false));
//            case HEALTH:
//                return new ModuleHealthViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_health, parent, false));
//            case STATBLOCK:
//                return new ModuleBlockViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_block, parent, false));
//            case STATUS:
//                return new ModuleStatusViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_status, parent, false));
//            case TITLETEXTBLOCK:
//                return new ModuleBlockViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_block, parent, false));
//            case BLOODPOOL:
//                return new ModuleBloodPoolViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_bloodpool, parent, false));
//            default:
//                return new ModuleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.module_text, parent, false));
//        }
//    }
//
//    // Replace the contents of a view (invoked by the layout manager)
//    @Override
//    public void onBindViewHolder(ModuleViewHolder vh, final int position) {
//        if (vh.tvTitle != null) vh.tvTitle.setText(modules.get(position).getTitle());
//        if (vh.tvText != null) vh.tvText.setText(modules.get(position).getText());
//
//        switch (types[vh.getItemViewType()]) {
//            case BLOODPOOL:
//                bind((ModuleBloodPoolViewHolder) vh, (BloodPoolModule) modules.get(position));
//                break;
//            case HEALTH:
//                bind((ModuleHealthViewHolder) vh, (HealthModule) modules.get(position));
//                break;
//            case STATBLOCK:
//                bind((ModuleBlockViewHolder) vh, (StatBlockModule) modules.get(position));
//                break;
//            case STATUS:
//                bind((ModuleStatusViewHolder) vh, (StatusModule) modules.get(position));
//                break;
//            case TITLETEXTBLOCK:
//                bind((ModuleBlockViewHolder) vh, (TitleTextBlockModule) modules.get(position));
//                break;
//        }
//    }
//
//    private void bind(ModuleBloodPoolViewHolder vh, BloodPoolModule module) {
//        vh.tvBloodPerTurn.setText(res.getString(R.string.blood_per_turn) + " " + module.getBloodPerTurn());
//        vh.circleProgress.setMax(module.getBloodMax());
//        vh.circleProgress.setProgress(module.getBloodCurrent());
//    }
//
//    private void bind(ModuleHealthViewHolder vh, HealthModule module) {
//        if (module.isDamaged()) vh.tvDamageLevel.setText(module.getCurrentHealth().getCategory());
//        vh.tvPenalty.setText(res.getQuantityString(R.plurals.dice_penalty, module.getCurrentHealth().getValue()));
//        vh.statBar.setNumStars(module.getHealthLevels().size());
////        vh.statBar.setHealthAgg(module.getDamageAgg());
////        vh.statBar.setHealthLethal(module.getDamageLethal());
////        vh.statBar.setHealthBashing(module.getDamageBashing());
//    }
//
//    private void bind(ModuleBlockViewHolder vh, StatBlockModule module) {
//        vh.statBarBlock.setStats(module.getStats());
//        int max = 5;
//        for(Stat stat : module.getStats()) max = Math.max(max, stat.getValueMax());
//        vh.statBarBlock.setMax(max);
//    }
//
//    private void bind(ModuleStatusViewHolder vh, StatusModule module) {
//        vh.statBar.setNumStars(module.getNumStars());
//        vh.statBar.setRatingMax(module.getValueMax());
//        vh.statBar.setRating(module.getValue());
//    }
//
//    private void bind(ModuleBlockViewHolder vh, TitleTextBlockModule module) {
////        for (int i = 0; i<module.getStats().size(); i++) {
////            View v = LayoutInflater.from(vh.linearLayout.getContext()).inflate(R.layout.row, vh.linearLayout, true);
////            v.setId(i);
////            TextView tvCategory = (TextView) v.findViewById(R.id.tvCategory);
////            CustomView customView = (CustomView) v.findViewById(R.id.customView);
////            tvCategory.setText(stat.getCategory());
////            customView.setRating(stat.getRating());
////        }
////        for (Stat stat : module.getStats()) {
////            View v = LayoutInflater.from(vh.linearLayout.getContext()).inflate(R.layout.row_boldtext_text, vh.linearLayout, true);
////            TextView tvCategory = (TextView) v.findViewById(R.id.tvCategory);
////            TextView tvText = (TextView) v.findViewById(R.id.tvText);
////            tvCategory.setText(stat.getCategory());
////            tvText.setText(stat.getSpecialty());
////
//////            RowViewHolder rowViewHolder = createChildViewHolder(vh.linearLayout);
//////            bindChild(rowViewHolder, stat);
////        }
//    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return modules.size();
//    }
}