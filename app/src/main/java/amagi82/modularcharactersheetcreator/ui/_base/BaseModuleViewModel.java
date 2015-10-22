package amagi82.modularcharactersheetcreator.ui._base;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public abstract class BaseModuleViewModel {
    public final int type;

    public BaseModuleViewModel(Module module) {
        type = module.type();
        update(module);
    }

    public abstract void update(Module module);
}
