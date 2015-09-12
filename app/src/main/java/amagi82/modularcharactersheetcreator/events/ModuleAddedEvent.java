package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.core_models.modules.Module;

public class ModuleAddedEvent {
    public final Module module;

    public ModuleAddedEvent(Module module) {
        this.module = module;
    }
}
