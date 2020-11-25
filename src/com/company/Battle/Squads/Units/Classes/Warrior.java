package com.company.Battle.Squads.Units.Classes;

import com.company.Battle.Squads.Units.Race;
import com.company.Battle.Squads.Units.Unit;

public class Warrior implements Unit, Cloneable {
    public static final String ANSI_BOLD = "\u001B[0;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final static double strengthModifier = 1.3;
    private final static double dexterityModifier = 1;
    private final static double intelligenceModifier = 0.7;
    private final int strength;
    private final int dexterity;
    private final int intelligence;
    private int currentVitality;
    private final int defense;
    private final int resistance;
    private String squadName;
    private String className;
    private final Race race;
    private final int vitality;

    public Warrior(Race race) {
        this.race = race;
        this.strength = (int) (race.strength * strengthModifier);
        this.dexterity = (int) (race.dexterity * dexterityModifier);
        this.intelligence = (int) (race.intelligence * intelligenceModifier);
        this.vitality = (int) (race.strength * strengthModifier * 10);
        this.currentVitality = this.vitality;
        this.defense = (int) (race.dexterity * dexterityModifier * 2);
        this.resistance = (int) (race.intelligence * intelligenceModifier * 2.5);
        switch (race) {
            case WOOD_ELF -> this.className = "Танцующий с клинками";
            case HIGH_ELF -> this.className = "Мастер клинка";
            case DWARF -> this.className = "Мастер топора";
            case ORC -> this.className = "Берсерк";
            default -> this.className = "Воин";
        }
    }

    @Override
    public String toString() {
        return this.squadName + " " + this.className;
    }

    @Override
    public Warrior clone() throws CloneNotSupportedException {
        Warrior warrior = (Warrior) super.clone();
        switch (warrior.race) {
            case WOOD_ELF -> warrior.className = "Танцующий с клинками";
            case HIGH_ELF -> warrior.className = "Мастер клинка";
            case DWARF -> warrior.className = "Мастер топора";
            case ORC -> warrior.className = "Берсерк";
            default -> warrior.className = "Воин";
        }
        return warrior;
    }

    public void setClassName(String name) {
        this.className = name;
    }

    public String getClassName() {
        return this.className;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDexterity() {
        return this.dexterity;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public int getCurrentVitality() {
        return this.currentVitality;
    }

    public int getVitality() {
        return this.vitality;
    }

    public String getUnitVitalityCard() {
        return ANSI_BOLD + " (" + this.getCurrentVitality() + "/" + this.getVitality() + ") " + ANSI_RESET;
    }

    public int[] attack() {
        int[] output = new int[2];
        if ((Math.random() * 100) < (5 * this.race.dexterity * dexterityModifier / 10)) {
            output[0] = ((int) (this.race.strength * strengthModifier * 8));
            output[1] = 1;
        } else output[0] = ((int) (this.race.strength * strengthModifier * 4));
        return output;
    }

    public int takeDamage(Unit unit, int damage) {
        if (unit instanceof Mage) {
            if (damage > this.resistance) {
                this.currentVitality = this.currentVitality - (damage - this.resistance);
                return damage - this.resistance;
            } else return 0;
        } else {
            if (damage > this.defense) {
                this.currentVitality = this.currentVitality - (damage - this.defense);
                return damage - this.defense;
            } else return 0;
        }
    }

    public boolean isAlive() {
        return this.currentVitality > 0;
    }

    public void setSquadName(String name) {
        this.squadName = name;
    }
}
