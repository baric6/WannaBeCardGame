package com.gmail.baric0748;

import java.util.ArrayList;

public class player
{
    private ArrayList<cardObject> playerCards = new ArrayList<>();
    private ArrayList<cardObject> playerDeck = new ArrayList<>();
    private cardObject FavoriteCard;
    private cardObject wishList;
    private int coins = 999;
    private int questCount = 0;

    private int exp;
    private int expMax;
    private int level = 1;

    //singleton class
    /////////////////////////////////////////////
    private static player instance;
    private player()
    {

    }
    public static player getPlayerInstance()
    {
        if(instance == null)
        {
            instance = new player();
        }
        return instance;
    }

    //for wish list
    public void setWishList(cardObject wishList) {
        this.wishList = wishList;
    }

    public cardObject getWishList() {
        return wishList;
    }

    //for players coins
    public void setCoins(int coins) {
        this.coins += coins;
    }

    public void removeCoins(int coins)
    {
        this.coins -= coins;
    }

    public int getCoins() {
        if(coins == 0)
        {
            return 0;
        }
        else
        {
            return coins;
        }

    }
    //////////////////////////////////


    //for favorite card
    public void setFavoriteCard(cardObject favoriteCard) {
        FavoriteCard = favoriteCard;
    }

    public cardObject getFavoriteCard() {
        return FavoriteCard;
    }

    //////////////////////////////////////////////


    //player cards
    public void setSinglePlayerCards(cardObject c)
    {
        playerCards.add(c);
    }

    public ArrayList<cardObject> getPlayerCards() {
        return playerCards;
    }

    public int sizeOf()
    {

        if(playerCards.isEmpty())
        {
            return 0;
        }
        else
        {
            return playerCards.size();
        }
    }

    public void setPlayerCards(ArrayList<cardObject> playerCards) {
        this.playerCards = playerCards;
    }


    ///////////////////////////////////////////////////////////
    //player deck
    public void setSinglePlayerDeck(cardObject c)
    {
        playerDeck.add(c);
    }


    public ArrayList<cardObject> getPlayerDeck() {
        return playerDeck;
    }

    public int sizeOfDeck()
    {

        return playerDeck.size();
    }

    public void setPlayerDeck(ArrayList<cardObject> playerDeck) {
        this.playerDeck = playerDeck;
    }

    public void removeCard(cardObject c)
    {
        playerDeck.remove(c);
    }

    public void clearDeck()
    {
        playerDeck.clear();
    }

    //deck attack
    public int deckTotalAttack()
    {
        if(playerDeck.size() == 0)
        {
            return 0;
        }
        else
        {
            int sum = 0;

            for(int i = 0; i <  playerDeck.size(); i++)
            {
               sum += playerDeck.get(i).getAttack();
            }

            return sum;
        }
    }

    //deck defence
    public int deckTotalDefence()
    {
        if(playerDeck.size() == 0)
        {
            return 0;
        }
        else
        {
            int sum = 0;

            for(int i = 0; i <  playerDeck.size(); i++)
            {
                sum += playerDeck.get(i).getDefence();
            }

            return sum;
        }
    }

    //deck powerCost
    public int deckCost()
    {
        if(playerDeck.size() == 0)
        {
            return 0;
        }

        int sum = 0;

        for(int i = 0; i <  playerDeck.size(); i++)
        {
            sum += playerDeck.get(i).getPowerCost();
        }

        return sum;
    }

    //for what quest number the user is on
    public void setQuestCount(int questCount) {
        this.questCount += questCount;
    }

    public int getQuestCount() {
        return questCount;
    }

    //the amount of exp the user has gained since starting the game
    public void setExp(int exp) {
        this.exp += exp;
    }

    public int getExp() {
        return exp;
    }

    //for what level the player is
    public void setLevel(int level) {
        this.level += level;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxExp4Lvl()
    {
        expMax = getLevel() * 60;
        //when player has no exp sets the progress bar to 50 exp for level 2
        if(getExp() >= expMax)
        {
            setLevel(+1);
            exp = 0;
            return expMax;
        }
        return this.expMax;
    }
}
