package com.company.Battle.Squads.Units.Classes;

import com.company.Battle.Squads.Units.Race;
import com.company.Battle.Squads.Units.Unit;

public class Mage implements Unit, Cloneable {
    public static final String ANSI_BOLD = "\u001B[0;1m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final static double strengthModifier = 0.7;
    private final static double dexterityModifier = 0.9;
    private final static double intelligenceModifier = 1.4;
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

    public Mage(Race race) {
        this.race = race;
        this.strength = (int) (race.strength * strengthModifier);
        this.dexterity = (int) (race.dexterity * dexterityModifier);
        this.intelligence = (int) (race.intelligence * intelligenceModifier);
        this.vitality = (int) (race.strength * strengthModifier * 10);
        this.currentVitality = this.vitality;
        this.defense = (int) (race.dexterity * dexterityModifier * 2);
        this.resistance = (int) (race.intelligence * intelligenceModifier * 2.5);
        switch (race) {
            case WOOD_ELF -> this.className = "Друид";
            case HIGH_ELF -> this.className = "Волшебник";
            case DWARF -> this.className = "Рунный жрец";
            case ORC -> this.className = "Шаман племени";
            default -> this.className = "Маг";
        }
    }

    @Override
    public String toString() {
        return this.squadName + " " + this.className;
    }

    @Override
    public Mage clone() throws CloneNotSupportedException {
        Mage mage = (Mage) super.clone();
        switch (mage.race) {
            case WOOD_ELF -> mage.className = "Друид";
            case HIGH_ELF -> mage.className = "Волшебник";
            case DWARF -> mage.className = "Рунный жрец";
            case ORC -> mage.className = "Шаман племени";
            default -> mage.className = "Маг";
        }
        return mage;
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
        if ((Math.random() * 100) < (5 * this.race.intelligence * intelligenceModifier / 10)) {
            output[0] = ((int) (this.race.intelligence * intelligenceModifier * 10));
            output[1] = 1;
        } else output[0] = ((int) (this.race.intelligence * intelligenceModifier * 5));
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
