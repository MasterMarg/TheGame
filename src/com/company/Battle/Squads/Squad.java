package com.company.Battle.Squads;

import com.company.Battle.Squads.Units.*;
import com.company.Battle.Squads.Units.Classes.Archer;
import com.company.Battle.Squads.Units.Classes.Mage;
import com.company.Battle.Squads.Units.Classes.Warrior;

public class Squad implements Cloneable {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Unit[] units;
    private String name;

    public Squad(String name) {
        this.name = analyzeSquadName(name, name);
        this.units = new Unit[(int) (Math.random() * 21) + 40];
        for (int index = 0; index < this.units.length; index++) {
            switch ((int) (Math.random() * 3)) {
                case 0 -> {
                    this.units[index] = new Warrior(Race.values()[(int) (Math.random() * Race.values().length)]);
                    this.units[index].setClassName(analyzeSquadName(name, this.units[index].getClassName()));
                    this.units[index].setSquadName(this.name);
                }
                case 1 -> {
                    this.units[index] = new Archer(Race.values()[(int) (Math.random() * Race.values().length)]);
                    this.units[index].setClassName(analyzeSquadName(name, this.units[index].getClassName()));
                    this.units[index].setSquadName(this.name);
                }
                case 2 -> {
                    this.units[index] = new Mage(Race.values()[(int) (Math.random() * Race.values().length)]);
                    this.units[index].setClassName(analyzeSquadName(name, this.units[index].getClassName()));
                    this.units[index].setSquadName(this.name);
                }
            }
        }
    }

    @Override
    public Squad clone() throws CloneNotSupportedException {
        Squad squad = (Squad) super.clone();
        if (this.name.equals(ANSI_RED + "Красный" + ANSI_RESET)
                || this.name.equals(ANSI_BLUE + "Синий" + ANSI_RESET)) {
            StringBuilder stringBuilder = new StringBuilder(squad.name);
            stringBuilder.delete(0, 5);
            squad.name = "Другой " + stringBuilder;
        } else squad.name = "Другой " + squad.name;
        squad.units = new Unit[this.units.length];
        int index = 0;
        for (Unit unit : this.units) {
            if (unit instanceof Warrior) {
                squad.units[index] = ((Warrior) unit).clone();
                squad.units[index].setSquadName(squad.name);
                index = index + 1;
            }
            if (unit instanceof Archer) {
                squad.units[index] = (((Archer) unit).clone());
                squad.units[index].setSquadName(squad.name);
                index = index + 1;
            }
            if (unit instanceof Mage) {
                squad.units[index] = ((Mage) unit).clone();
                squad.units[index].setSquadName(squad.name);
                index = index + 1;
            }
        }
        return squad;
    }

    public Unit getRandomUnit() {
        int index;
        do index = (int) (Math.random() * this.units.length);
        while (!this.units[index].isAlive());
        return this.units[index];
    }

    public boolean hasAliveUnits() {
        boolean hasAliveUnits = false;
        for (Unit unit : this.units) {
            if (unit.isAlive()) hasAliveUnits = true;
        }
        return hasAliveUnits;
    }

    public String analyzeSquadName(String squadName, String name) {
        switch (squadName) {
            case "Красный" -> name = ANSI_RED + name + ANSI_RESET;
            case "Синий" -> name = ANSI_BLUE + name + ANSI_RESET;
        }
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
