package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class Game {

    public enum System {
        CWOD(R.string.cwod, R.drawable.title_wod),
        NWOD(R.string.nwod, R.drawable.title_wod),
        CVAMPIRE(R.string.cwod_vampire, R.drawable.title_vampire_masquerade, R.color.cwod_vampire, R.string.url_cwod_vampire_base, Category.CWOD, R.string.sect, R.string.clan),
        CWEREWOLF(R.string.cwod_werewolf, R.drawable.title_werewolf_apocalypse, R.color.cwod_werewolf, R.string.url_cwod_werewolf_base, Category.CWOD, R.string.fera, R.string.tribe, R.string.auspice),
        CWRAITH(R.string.cwod_wraith, R.drawable.title_wraith_oblivion, R.color.cwod_wraith, R.string.url_cwod_wraith_base, Category.CWOD, R.string.arcanos),
        CMAGE(R.string.cwod_mage, R.drawable.title_mage_ascension, R.color.cwod_mage, R.string.url_cwod_mage_base, Category.CWOD, R.string.tradition),
        NVAMPIRE(R.string.nwod_vampire, R.drawable.title_vampire_requiem, R.color.nwod_vampire, R.string.url_nwod_vampire_base, Category.NWOD, R.string.clan, R.string.bloodline, R.string.covenant),
        NWEREWOLF(R.string.nwod_werewolf, R.drawable.title_werewolf_forsaken, R.color.nwod_werewolf, R.string.url_nwod_werewolf_base, Category.NWOD, R.string.tribe, R.string.auspice),
        NMUMMY(R.string.nwod_mummy, R.drawable.title_mummy_curse, R.color.nwod_mummy, R.string.url_nwod_mummy_base, Category.NWOD, R.string.decree, R.string.guild),
        NDEMON(R.string.nwod_demon, R.drawable.title_demon_descent, R.color.nwod_demon, R.string.url_nwod_demon_base, Category.NWOD, R.string.incarnation, R.string.agenda),
        SCION(R.string.scion, R.drawable.title_scion, R.color.scion),
        TRINITY(R.string.trinity, R.drawable.title_trinity_continuum, R.color.trinity),
        EXALTED(R.string.exalted, R.drawable.title_exalted, R.color.exalted);

        private int name;
        private int drawable;
        private int urlBase;
        private int color;
        private Category category;
        private int[] categoryTitles;

        System(int name, int drawable) {
            this(name, drawable, 0, 0, Category.DEFAULT);
        }

        System(int name, int drawable, int color) {
            this(name, drawable, color, 0, Category.DEFAULT);
        }

        System(int name, int drawable, int color, Category category) {
            this(name, drawable, color, 0, category);
        }

        System(int name, int drawable, int color, int urlBase, Category category) {
            this.name = name;
            this.drawable = drawable;
            this.color = color;
            this.urlBase = urlBase;
            this.category = category;
        }

        System(int name, int drawable, int color, int urlBase, Category category, int... categoryTitles) {
            this(name, drawable, color, urlBase, category);
            this.categoryTitles = categoryTitles;
        }

        public int getName() {
            return name;
        }

        public int getDrawable() {
            return drawable;
        }

        public int getColor() {
            return color;
        }

        public int getUrlBase() {
            return urlBase;
        }

        public Category getCategory() {
            return category;
        }

        public int getCategoryCount() {
            return categoryTitles.length;
        }

        public int[] getCategoryTitles() {
            return categoryTitles;
        }
    }

    private enum Category {
        CWOD, NWOD, DEFAULT;
    }

    private Choice choice;
    private List<Choice> choices = new ArrayList<>();

    public Game() {
    }

    public Game(Onyx game) {
        setChoice(game.getSystemName());
    }

    public Choice getChoice() {
        return choice;
    }

    public List<Choice> getList(String eName) {
        Category category = eName == null ? Category.DEFAULT : eName.equals("CWOD") ? Category.CWOD : eName.equals("NWOD") ? Category.NWOD : null;
        choices.clear();
        if (category == null) return null;
        for (System system : System.values()) {
            if (system.getCategory() == category) choices.add(new Choice(system.name(), system.getName(), system.getDrawable()));
        }
        return choices;
    }

    private void setChoice(String eName) {
        System system = System.valueOf(eName);
        choice = new Choice(eName, system.getName(), system.getDrawable());
    }
}
