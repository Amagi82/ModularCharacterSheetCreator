package amagi82.modularcharactersheetcreator.models;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.models.modules.Module;

public class Sheet {

    private String title = "";
    private int numColumns = 3;
    private List<Module> modules = new ArrayList<>();

    public Sheet() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }
}
