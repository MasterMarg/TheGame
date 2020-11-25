package com.company.Battle;

import com.company.Battle.Squads.Squad;
import com.company.Battle.Squads.Units.Classes.Mage;
import com.company.Battle.Squads.Units.Unit;

public class Battle {
    public static final String ANSI_BOLD_YELLOW = "\u001B[33;1m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE_BOLD = "\u001B[30;1m";
    public static final String ANSI_GREEN_BOLD = "\u001B[32;1m";
    public static final String ANSI_VIOLET = "\u001B[35m";
    public static final String ANSI_CYAN_BOLD = "\u001B[36;1m";
    public static final String ANSI_BOLD = "\u001B[0;1m";

    private final DateHelper dateHelper;
    private final Squad redSquad;
    private final Squad blueSquad;

    public Battle() {
        this.dateHelper = new DateHelper();
        this.redSquad = new Squad("Красный");
        this.blueSquad = new Squad("Синий");
    }

    public String[] makeAttack(Squad squad1, Squad squad2) {
        String[] attackLog = new String[4];
        attackLog[0] = this.dateHelper.getDate();
        Unit unit1 = squad1.getRandomUnit();
        Unit unit2 = squad2.getRandomUnit();
        attackLog[1] = unit1.toString() + ANSI_BOLD + unit1.getUnitVitalityCard() + ANSI_RESET + " атакует " +
                unit2.toString() + ANSI_BOLD + unit2.getUnitVitalityCard() + ANSI_RESET;
        int[] attackData = unit1.attack();
        int attack = attackData[0];
        int vitality = unit2.getCurrentVitality();
        int damage = unit2.takeDamage(unit1, attack);
        String attackString = "" + attack;
        if (attackData[1] == 1) attackString = ANSI_BOLD_YELLOW + attackString + ANSI_RESET;
        else attackString = ANSI_WHITE_BOLD + attackString + ANSI_RESET;
        if (unit1 instanceof Mage) {
            attackLog[2] = unit1.toString() + " наносит " + attackString + ANSI_CYAN_BOLD + "(-" + (attack - damage)
                    + ")" + ANSI_RESET + reformString(attack) + " урона.";
        } else {
            attackLog[2] = unit1.toString() + " наносит " + attackString + ANSI_GREEN_BOLD + "(-" + (attack - damage)
                    + ")" + ANSI_RESET + reformString(attack) + " урона.";
        }
        if (attackData[1] == 1)
            attackLog[2] = attackLog[2] + ANSI_BOLD_YELLOW + " Критический удар!" + ANSI_RESET;
        if (unit2.isAlive())
            attackLog[3] = unit2.toString() + " теряет " + damage + reformString(damage) + " здоровья.\n";
        else attackLog[3] = unit2.toString() + " теряет " + vitality + reformString(vitality) + " здоровья.\n";
        if (!unit2.isAlive()) attackLog[3] = attackLog[3] + ANSI_VIOLET + "Боец убит.\n" + ANSI_RESET;
        if (squad2.hasAliveUnits()) this.dateHelper.skipTime();
        return attackLog;
    }

    public String reformString(int input) {
        String output;
        if (input % 100 > 20 || input % 100 < 10) {
            switch (input % 10) {
                case 1 -> output = " единицу";
                case 2, 3, 4 -> output = " единицы";
                default -> output = " единиц";
            }
        } else output = " единиц";
        return output;
    }

    public Squad getRedSquad() {
        return this.redSquad;
    }

    public Squad getBlueSquad() {
        return this.blueSquad;
    }

    public DateHelper getDateHelper() {
        return this.dateHelper;
    }
}
