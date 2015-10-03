package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

public class MultiItemBinder<T> implements Binding<T> {

    private final BindingCondition<T>[] bindingConditions;

    @SafeVarargs public MultiItemBinder(BindingCondition<T>... bindingConditions) {
        this.bindingConditions = bindingConditions;
    }

    @Override
    public int getLayoutRes(T model) {
        for (BindingCondition<T> dataBinder : bindingConditions) {
            if (dataBinder.canHandle(model)) return dataBinder.getLayoutRes(model);
        }
        throw new IllegalStateException();
    }

    @Override
    public int getBindingVariable(T model) {
        for (BindingCondition<T> dataBinder : bindingConditions) {
            if (dataBinder.canHandle(model)) return dataBinder.getBindingVariable(model);
        }
        throw new IllegalStateException();
    }
}
