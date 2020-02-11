package com.gmail.baric0748;

import java.util.ArrayList;

public class cards
{

    private ArrayList<cardObject> oneStar = new ArrayList<>();

    //singleton class
    /////////////////////////////////////////////
    private static cards instance;
    private cards()
    {

    }
    public static cards getCardInstance()
    {
        if(instance == null)
        {
            instance = new cards();
        }
        return instance;
    }

    public void setOneStar(ArrayList<cardObject> cards) {
        this.oneStar = cards;
    }

    public ArrayList<cardObject> getOneStar() {
        return oneStar;
    }

    public cardObject getSingle(cardObject c)
    {
        return c;
    }
}
