package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

public abstract class BindingCondition<T> extends ItemBinder<T> {

    public BindingCondition(int bindingVariable, int layoutId) {
        super(bindingVariable, layoutId);
    }

    public abstract boolean canHandle(T model);

    public boolean canHandle(int layoutId) {
        return this.layoutId == layoutId;
    }
}
