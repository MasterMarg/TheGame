package com.company;

import com.company.Battle.Battle;

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
        if (battle.getRedSquad().hasAliveUnits()) {
            switch (battle.getRedSquad().toString()) {
                case "Красный" -> System.out.println(ANSI_BOLD_RED + "Победил красный отряд" + ANSI_RESET);
                case "Синий" -> System.out.println(ANSI_BOLD_BLUE + "Победил синий отряд" + ANSI_RESET);
                default -> System.out.println("Победил " + battle.getRedSquad().toString() + " отряд. (Первый)");
            }
        } else {
            switch (battle.getBlueSquad().toString()) {
                case "Красный" -> System.out.println(ANSI_BOLD_RED + "Победил красный отряд" + ANSI_RESET);
                case "Синий" -> System.out.println(ANSI_BOLD_BLUE + "Победил синий отряд" + ANSI_RESET);
                default -> System.out.println("Победил " + battle.getRedSquad().toString() + " отряд. (Второй)");
            }
        }
        System.out.println(ANSI_BOLD + "Битва продолжалась " + battle.getDateHelper().getFormattedDiff() + ANSI_RESET);
    }
}
