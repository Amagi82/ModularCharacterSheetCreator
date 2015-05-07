package amagi82.modularcharactersheetcreator.listeners;


import java.util.ArrayList;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public interface OnItemClickedListener {
    void onCharacterClicked(int position);
    void onModuleClicked(ArrayList<? extends Module> module, int position);
}