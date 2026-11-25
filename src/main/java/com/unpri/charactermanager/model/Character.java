
package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    // Constructor 1
    public Character(String name, CharacterType type) {
        setName(name);
        setType(type);
        setLevel(1);
    }

    // Constructor 2
    public Character(String name, CharacterType type, int level) {
        setName(name);
        setType(type);
        setLevel(level);
    }

    // Constructor 3
    public Character(String name, CharacterType type, int level, int mana) {
        setName(name);
        setType(type);
        setLevel(level);
        setMana(mana);
    }

    // Menghitung HP dan Attack
    private void calculateStats() {
        hitPoints = 100 + (level * 20);
        attack = 10 + (level * 5);
    }

    // Menghitung Mana otomatis berdasarkan tipe
    private void calculateMana() {
        switch (type) {
            case WARRIOR:
                mana = 30;
                break;
            case MAGE:
                mana = 100;
                break;
            case ARCHER:
                mana = 50;
                break;
            case HEALER:
                mana = 80;
                break;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        if (name.trim().length() > 20) {
            throw new IllegalArgumentException("Nama maksimal 20 karakter.");
        }
        this.name = name.trim();
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException("Tipe karakter tidak boleh kosong.");
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("Level MAGE maksimal 60.");
        }

        this.type = type;
        calculateMana();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException("Level harus antara 1 sampai 99.");
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException("Level MAGE maksimal 60.");
        }

        this.level = level;
        calculateStats();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttack() {
        return attack;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException("Mana tidak boleh negatif.");
        }
        this.mana = mana;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hitPoints=" + hitPoints +
                ", attack=" + attack +
                ", mana=" + mana +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Character)) return false;

        Character other = (Character) obj;
        return name.equalsIgnoreCase(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }
}
