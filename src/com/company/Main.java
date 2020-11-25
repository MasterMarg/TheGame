package com.company;

import com.company.Battle.Battle;
import com.company.Battle.Squads.Squad;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[0;1m";
    public static final String ANSI_BOLD_RED = "\u001B[31;1m";
    public static final String ANSI_BOLD_BLUE = "\u001B[34;1m";

    public static void main(String[] args) {
        Battle battle = new Battle();
        System.out.println();
        System.out.println(ANSI_BOLD + battle.getDateHelper().getFormattedStartDate() +
                "\nБитва началась!\n" + ANSI_RESET);
        while (battle.getRedSquad().hasAliveUnits() && battle.getBlueSquad().hasAliveUnits()) {
            for (String string : battle.makeAttack(battle.getRedSquad(), battle.getBlueSquad()))
                System.out.println(string);
            if (battle.getBlueSquad().hasAliveUnits()) {
                for (String string : battle.makeAttack(battle.getBlueSquad(), battle.getRedSquad())) {
                    System.out.println(string);
                }
            }
        }
        if (battle.getRedSquad().hasAliveUnits()) System.out.println(getResult(battle.getRedSquad()));
        else System.out.println(getResult(battle.getBlueSquad()));
        System.out.println(ANSI_BOLD + "Битва продолжалась " + battle.getDateHelper().getFormattedDiff() + ANSI_RESET);
    }

    public static String getResult(Squad squad) {
        String result;
        switch (squad.toString()) {
            case "Красный" -> result = ANSI_BOLD_RED + "Победил красный отряд" + ANSI_RESET;
            case "Синий" -> result = ANSI_BOLD_BLUE + "Победил синий отряд" + ANSI_RESET;
            default -> result = "Победил " + squad.toString() + " отряд. (Первый)";
        }
        return result;
    }
}
