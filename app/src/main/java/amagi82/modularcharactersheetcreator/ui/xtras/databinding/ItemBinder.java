package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

public interface ItemBinder<T> {
    int getLayoutRes(T model);
    int getBindingVariable(T model);
}