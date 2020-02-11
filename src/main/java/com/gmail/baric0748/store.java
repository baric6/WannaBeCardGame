package com.gmail.baric0748;
/*
* this class is for the store with connection to the activity_store.xml
* everything that has to do with the store goes in here
* */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class store extends AppCompatActivity {

    private TextView genericCard;
    private ImageView genericImage;
    private Button btnOK;
    private Button homeward;

    private player p = player.getPlayerInstance();
    private cards c = cards.getCardInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        genericCard = findViewById(R.id.genericCard);
        genericImage = findViewById(R.id.genericImage);
        btnOK = findViewById(R.id.btnOK);
        homeward = findViewById(R.id.homeward);

        //ramdomly display card to the user
        Random r = new Random();
        int ran = r.nextInt(13);
        genericCard.setText(c.getOneStar().get(ran).getTitle());
        genericImage.setImageResource(c.getOneStar().get(ran).getTexture());
        btnOK.setText("Get card for: '" + c.getOneStar().get(ran).getCardSalePrice() + " Coins'");


        //compares the players coins to the card price if players coins are bigger then the amount
        //lets you get the card else wont let you
        //removes the price of card from the players balance
        //sets the card to the players card list
            btnOK.setOnClickListener((v) -> {
                if(p.getCoins() >= c.getOneStar().get(ran).getCardSalePrice()) {

                    p.removeCoins(c.getOneStar().get(ran).getCardSalePrice());
                    p.setSinglePlayerCards(c.getOneStar().get(ran));
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Not enough coins", Toast.LENGTH_LONG).show();
                }
            });



            //goes to mainActivity
            homeward.setOnClickListener((v)->{
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            });
    }
}
