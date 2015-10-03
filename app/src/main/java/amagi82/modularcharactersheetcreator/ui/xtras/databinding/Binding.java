package amagi82.modularcharactersheetcreator.ui.xtras.databinding;

public interface Binding<T> {
    int getLayoutRes(T model);
    int getBindingVariable(T model);
}