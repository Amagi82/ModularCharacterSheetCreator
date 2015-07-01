package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;
import amagi82.modularcharactersheetcreator.models.Choice;

public class NMummy extends Onyx {

    public enum Decree {
        BULLHEADED(R.string.bull_headed, R.string.url_nwod_mummy_decree_bull_headed),
        FALCONHEADED(R.string.falcon_headed, R.string.url_nwod_mummy_decree_falcon_headed),
        JACKALHEADED(R.string.jackal_headed, R.string.url_nwod_mummy_decree_jackal_headed),
        LIONHEADED(R.string.lion_headed, R.string.url_nwod_mummy_decree_lion_headed),
        SERPENTHEADED(R.string.serpent_headed, R.string.url_nwod_mummy_decree_serpent_headed);

        private int name;
        private int url;

        Decree(int name, int url) {
            this.name = name;
            this.url = url;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }
    }

    public enum Guild {
        MAAKEP(R.string.maa_kep, R.string.url_nwod_mummy_guild_maa_kep),
        MESENNEBU(R.string.mesen_nebu, R.string.url_nwod_mummy_guild_mesen_nebu),
        SESHAHEBSU(R.string.sesha_hebsu, R.string.url_nwod_mummy_guild_sesha_hebsu),
        SUMENENT(R.string.su_menent, R.string.url_nwod_mummy_guild_su_menent),
        TEFAABHI(R.string.tef_aabhi, R.string.url_nwod_mummy_guild_tef_aabhi);

        private int name;
        private int url;

        Guild(int name, int url) {
            this.name = name;
            this.url = url;
        }

        public int getName() {
            return name;
        }

        public int getUrl() {
            return url;
        }
    }

    private Decree decree;
    private Guild guild;
    private Choice choiceLeft;
    private Choice choiceRight;
    private List<Choice> list = new ArrayList<>();

    public NMummy() {
    }

    public NMummy(String decreeName, String guildName) {
        this(Decree.valueOf(decreeName), Guild.valueOf(guildName));
    }

    public NMummy(Decree decree, Guild guild) {
        this.decree = decree;
        this.guild = guild;
        choiceLeft = getChoice(decree);
        choiceRight = getChoice(guild);
    }

    private Choice getChoice(Decree decree){
        return new Choice(decree.name(), decree.getName(), Game.System.NMUMMY.getUrlBase(), decree.getUrl());
    }

    private Choice getChoice(Guild guild){
        return new Choice(guild.name(), guild.getName(), Game.System.NMUMMY.getUrlBase(), guild.getUrl());
    }

    @Override public String getSystemName() {
        return Game.System.NMUMMY.name();
    }

    @Override public int getArchetype() {
        return decree.getName();
    }

    @Override public Choice getLeft() {
        return choiceLeft;
    }

    @Override public Choice getRight() {
        return choiceRight;
    }

    @Override public List<Choice> getList(String eName) {
        list.clear();
        if (eName == null) {
            for (Decree decree : Decree.values()){
                list.add(getChoice(decree));
            }
            return list;
        }
        if(decree == null) {
            decree = Decree.valueOf(eName);
            choiceLeft = getChoice(decree);

            for (Guild guild : Guild.values()) {
                list.add(getChoice(guild));
            }
            return list;
        }
        guild = Guild.valueOf(eName);
        choiceRight = getChoice(guild);
        return null;
    }
}
