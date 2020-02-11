package com.gmail.baric0748;

import java.util.Objects;

public class cardObject
{
    //race 1 = humaan, 2 = gods, 3 = demons
    private int race;
    private String title;
    private int texture;
    private int powerCost;
    private int attack;
    private int defence;
    private boolean isFavorite;
    private int cardSalePrice;


    cardObject()
    {

    }

    cardObject(int race, String title, int texture, int powerCost, int attack, int defence, boolean isFavorite, int cardSalePrice)
    {
        this.race = race;
        this.texture = texture;
        this.title = title;
        this.powerCost = powerCost;
        this.attack = attack;
        this.defence = defence;
        this.isFavorite = isFavorite;
        this.cardSalePrice = cardSalePrice;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public int getPowerCost() {
        return powerCost;
    }

    public void setPowerCost(int powerCost) {
        this.powerCost = powerCost;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getRace() {
        return race;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public void setTexture(int texture) {
        this.texture = texture;
    }

    public int getTexture() {
        return texture;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getCardSalePrice() {
        return cardSalePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof cardObject)) return false;
        cardObject that = (cardObject) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }


}
