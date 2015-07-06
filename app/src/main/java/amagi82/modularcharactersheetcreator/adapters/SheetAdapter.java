package amagi82.modularcharactersheetcreator.adapters;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.adapters.viewholders.ContainerViewHolder;
import amagi82.modularcharactersheetcreator.adapters.viewholders.TextViewHolder;
import amagi82.modularcharactersheetcreator.models.modules.Module;
import amagi82.modularcharactersheetcreator.models.modules.TextModule;

public class SheetAdapter extends RecyclerView.Adapter<ContainerViewHolder> {

    private ArrayList<Module> modules;

    public SheetAdapter(ArrayList<Module> modules, Activity activity) {
        this.modules = modules;
    }

    @Override
    public int getItemViewType(int position) {
        return modules.get(position).getType().ordinal();
    }

    // Create new views (invoked by the layout manager). Type from getItemViewType above.
    @Override
    public ContainerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Module.Type typeEnums[] = Module.Type.values();
        switch (typeEnums[viewType]) {
            case TEXT:
                return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_text, parent, false));

            default:
                return new TextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_text, parent, false));
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ContainerViewHolder holder, final int position) {

        switch(modules.get(position).getType()){
            case TEXT:
                TextModule module = (TextModule) modules.get(position);
                TextViewHolder hold = (TextViewHolder) holder;
                hold.tvText.setText(module.getText());
                break;

        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return modules.size();
    }


}