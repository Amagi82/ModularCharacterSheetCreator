package amagi82.modularcharactersheetcreator.models.game_systems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class NWoDMummy extends GameSystem {

    public enum NWoDMummyDecree {
        BULLHEADED(R.string.bull_headed, R.string.url_nwod_mummy_decree_bull_headed),
        FALCONHEADED(R.string.falcon_headed, R.string.url_nwod_mummy_decree_falcon_headed),
        JACKALHEADED(R.string.jackal_headed, R.string.url_nwod_mummy_decree_jackal_headed),
        LIONHEADED(R.string.lion_headed, R.string.url_nwod_mummy_decree_lion_headed),
        SERPENTHEADED(R.string.serpent_headed, R.string.url_nwod_mummy_decree_serpent_headed);

        private int name;
        private int url;

        NWoDMummyDecree(int name, int url) {
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

    public enum NWoDMummyGuild {
        MAAKEP(R.string.maa_kep, R.string.url_nwod_mummy_guild_maa_kep),
        MESENNEBU(R.string.mesen_nebu, R.string.url_nwod_mummy_guild_mesen_nebu),
        SESHAHEBSU(R.string.sesha_hebsu, R.string.url_nwod_mummy_guild_sesha_hebsu),
        SUMENENT(R.string.su_menent, R.string.url_nwod_mummy_guild_su_menent),
        TEFAABHI(R.string.tef_aabhi, R.string.url_nwod_mummy_guild_tef_aabhi);

        private int name;
        private int url;

        NWoDMummyGuild(int name, int url) {
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

    private NWoDMummyDecree decree;
    private NWoDMummyGuild guild;

    public NWoDMummy() {
        super(System.NWODMUMMY);
    }

    public NWoDMummyDecree getDecree() {
        return decree;
    }

    public void setDecree(NWoDMummyDecree decree) {
        setLeft(decree);
        this.decree = decree;
    }

    public NWoDMummyGuild getGuild() {
        return guild;
    }

    public void setGuild(NWoDMummyGuild guild) {
        setRight(guild);
        this.guild = guild;
    }

    public List<NWoDMummyDecree> getListDecree() {
        List<NWoDMummyDecree> list = new ArrayList<>();
        Collections.addAll(list, NWoDMummyDecree.values());
        return list;
    }

    public List<NWoDMummyGuild> getListGuild() {
        List<NWoDMummyGuild> list = new ArrayList<>();
        Collections.addAll(list, NWoDMummyGuild.values());
        return list;
    }
}
