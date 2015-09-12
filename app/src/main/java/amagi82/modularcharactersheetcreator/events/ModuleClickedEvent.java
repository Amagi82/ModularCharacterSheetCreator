package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.core_models.modules.Module;

public class ModuleClickedEvent {
    public final Module module;

    public ModuleClickedEvent(Module module) {
        this.module = module;
    }
}
