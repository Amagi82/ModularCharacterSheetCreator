package amagi82.modularcharactersheetcreator.events;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public class ModuleClickedEvent {
    public final Module module;

    public ModuleClickedEvent(Module module) {
        this.module = module;
    }
}
