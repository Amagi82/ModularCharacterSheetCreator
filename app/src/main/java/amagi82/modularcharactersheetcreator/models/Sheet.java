package amagi82.modularcharactersheetcreator.models;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.models.modules.Module;

@JsonObject
public class Sheet {

    @JsonField private String title = "";
    @JsonField private int numColumns = 3;
    @JsonField private List<Module> modules = new ArrayList<>();

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
