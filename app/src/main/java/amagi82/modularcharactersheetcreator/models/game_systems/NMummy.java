package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NMummy extends GameSystem {

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

    public String decreeName;
    public String guildName;

    public NMummy() {
        super(System.NWODMUMMY);
    }

    public Decree getDecree() {
        return Decree.valueOf(decreeName);
    }

    public void setDecree(Decree decree) {
        setLeft(new SubType(decree.getName(), decree.getUrl()));
        setTitle(decree.getName());
        decreeName = decree.name();
    }

    public Guild getGuild() {
        return Guild.valueOf(guildName);
    }

    public void setGuild(Guild guild) {
        setRight(new SubType(guild.getName(), guild.getUrl()));
        guildName = guild.name();
    }

    public List<Decree> getListDecree() {
        List<Decree> list = new ArrayList<>();
        Collections.addAll(list, Decree.values());
        return list;
    }

    public List<Guild> getListGuild() {
        List<Guild> list = new ArrayList<>();
        Collections.addAll(list, Guild.values());
        return list;
    }
}
