package com.company.Battle.Squads.Units;

public interface Unit {

    int[] attack();

    int takeDamage(Unit unit, int damage);

    boolean isAlive();

    void setSquadName(String name);

    String getClassName();

    void setClassName(String name);

    int getStrength();

    int getDexterity();

    int getIntelligence();

    int getCurrentVitality();

    int getVitality();

    String getUnitVitalityCard();
}
