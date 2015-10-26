package amagi82.modularcharactersheetcreator.ui._base;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public abstract class BaseModuleViewModel {
    @Module.Type public final int type;
    @Module.SpanCount public final int spanCount;

    public BaseModuleViewModel(Module module) {
        type = module.type();
        spanCount = module.spanCount();
    }

    public abstract void update(Module module);
}
