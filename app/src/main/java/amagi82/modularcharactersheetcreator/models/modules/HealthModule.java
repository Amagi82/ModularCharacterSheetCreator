package amagi82.modularcharactersheetcreator.models.modules;

import java.util.List;

import amagi82.modularcharactersheetcreator.R;

public class HealthModule extends Module {

    private List<Stat> healthLevels;
    private int damageBashing;
    private int damageLethal;
    private int damageAgg;

    public HealthModule() {
        super(Type.HEALTH, R.layout.module_health);
    }

    public List<Stat> getHealthLevels() {
        return healthLevels;
    }

    public void setHealthLevels(List<Stat> healthLevels) {
        this.healthLevels = healthLevels;
    }

    public int getDamageBashing() {
        return damageBashing;
    }

    public void setDamageBashing(int damageBashing) {
        this.damageBashing = damageBashing;
        reviewDamage();
    }

    public int getDamageLethal() {
        return damageLethal;
    }

    public void setDamageLethal(int damageLethal) {
        this.damageLethal = damageLethal;
        reviewDamage();
    }

    public int getDamageAgg() {
        return damageAgg;
    }

    public void setDamageAgg(int damageAgg) {
        this.damageAgg = damageAgg;
        reviewDamage();
    }

    public void addDamageBashing() {
        damageBashing++;
        reviewDamage();
    }

    public void addDamageLethal() {
        damageLethal++;
        reviewDamage();
    }

    public void addDamageAgg() {
        damageAgg++;
        reviewDamage();
    }

    public boolean healDamage() {
        if (damageBashing > 1) damageBashing--;
        else if (damageLethal > 1) damageLethal--;
        else if (damageBashing > 1) damageBashing--;
        else return false;
        return true;
    }

    private void reviewDamage() {
        int max = healthLevels.size();
        if (damageAgg + damageBashing + damageLethal > max) {
            if (damageAgg + damageLethal > max) {
                if (damageAgg > max) {
                    damageAgg = max;
                    damageLethal = 0;
                } else damageLethal = max - damageAgg;
                damageBashing = 0;
            } else damageBashing = max - damageAgg - damageLethal;
        }
    }
}
