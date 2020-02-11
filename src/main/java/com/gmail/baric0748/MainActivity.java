package com.gmail.baric0748;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    private ImageView card2;
    private ImageView card3;

    private TextView playerCount;
    private TextView playerCoin;

    private ImageView wishList;

    //for favorite card view
    private ImageView favImg;
    private TextView favName;
    private TextView favRace;
    private TextView favCost;
    private TextView favAtk;
    private TextView favDef;

    //menu buttons
    private ImageView menuHome;
    private ImageView menuDeck;
    private ImageView menuQuest;
    private ImageView menuMis;
    private ImageView menuOptions;

    //players exp
    private ProgressBar expBar;
    private TextView expGrowth;
    private TextView userLevel;


    private player p = player.getPlayerInstance();
    private cards c = cards.getCardInstance();

    private Random r = new Random();
    private Random r1 = new Random();
    private Random r2 = new Random();

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttons
        btn = findViewById(R.id.btn);

        //imageView
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        wishList = findViewById(R.id.wishList);

        //text
        playerCount = findViewById(R.id.hCardCount);
        playerCoin = findViewById(R.id.hCoins);
        userLevel = findViewById(R.id.userLevel);

        //for favorite card view
        favImg = findViewById(R.id.favImg);
        favName = findViewById(R.id.favName);
        favRace = findViewById(R.id.favRace);
        favCost = findViewById(R.id.favCost);
        favAtk = findViewById(R.id.favAtk);
        favDef = findViewById(R.id.favDef);

        //menu
        menuHome = findViewById(R.id.menuHome);
        menuDeck = findViewById(R.id.menuDeck);
        menuQuest = findViewById(R.id.menuQuest);
        menuMis = findViewById(R.id.menuMis);
        menuOptions = findViewById(R.id.menuOptions);

        //players expBar
        expBar = findViewById(R.id.expBar);
        expGrowth = findViewById(R.id.expGrowth);
        //setting players coin
        playerCoin.setText("Coins:" + p.getCoins());
        playerCount.setText(""+p.sizeOf());
        userLevel.setText("" + p.getLevel());

        //when the player exp hits max exp will level up the user and bump up the progress
        //bar max number
        expBar.setMax(p.getMaxExp4Lvl());
        expBar.setProgress(p.getExp());
        expGrowth.setText(p.getExp() + "/" + expBar.getMax());


        //setting array list of cards
        ArrayList<cardObject> card = new ArrayList<>();
        card.add(new cardObject(1, "Faux Mage", R.drawable.faux_mage, 12, 1180, 1070, false, 50));
        card.add(new cardObject(1, "Wolf Rider", R.drawable.wolf_rider, 12, 1320, 930, false, 50));
        card.add(new cardObject(1, "Mysterian Trainer Isaac", R.drawable.mysterian_trainer_isaac, 12, 1380, 870, false, 50));
        card.add(new cardObject(1, "Wight Hunter", R.drawable.wight_hunter, 12, 900, 1340, false, 50));
        card.add(new cardObject(2, "Hamsa", R.drawable.hamsa, 12, 1160, 1090, false, 50));
        card.add(new cardObject(2, "Ivory Dragon", R.drawable.ivory_dragon, 12, 1160, 1080, false, 50));
        card.add(new cardObject(3, "Nephthys", R.drawable.nephthys, 37, 4760, 5340, false, 1000));
        card.add(new cardObject(3, "Lilith", R.drawable.lilith, 7, 980, 460, false, 50));
        card.add(new cardObject(3, "Lesser Daemon", R.drawable.lesser_daemon, 11, 1050, 1030, false, 50));
        card.add(new cardObject(1, "Ninja Trainee", R.drawable.ninja_trainee, 11, 1050, 1030, false, 50));
        card.add(new cardObject(3, "Fire Lizard", R.drawable.fire_lizard, 12, 1080, 1170, false, 50));
        card.add(new cardObject(3, "Magic Pot", R.drawable.magic_pot, 12, 920, 1330, false, 50));
        card.add(new cardObject(3, "Fungie", R.drawable.fungie, 12, 1110, 1140, false, 50));

        //set cards to card object
        c.setOneStar(card);

        //null check for top random cards////////////////////////////////////
        if(!p.getPlayerCards().isEmpty()) {

            int two = r1.nextInt(p.sizeOf());
            int three = r2.nextInt(p.sizeOf());

            card2.setImageResource(p.getPlayerCards().get(two).getTexture());
            card3.setImageResource(p.getPlayerCards().get(three).getTexture());

        }
        //////////////////////////////////////////////////////////////////////

        //image view null checking////////////////////////////////////////////////
        if(p.getWishList() != null)
        {
            wishList.setImageResource(p.getWishList().getTexture());
        }

        if(p.getFavoriteCard() != null)
        {
            //place everything that has to do with favorite card
            favImg.setImageResource(p.getFavoriteCard().getTexture());
            favName.setText(p.getFavoriteCard().getTitle());

            switch (p.getFavoriteCard().getRace())
            {
                case 1:
                    favRace.setText("Human");
                    break;
                case 2:
                    favRace.setText("God");
                    break;
                case 3:
                    favRace.setText("Demon");
                    break;
            }


            //setting favorite card attribute to text view
            favCost.setText("Cost: " + p.getFavoriteCard().getPowerCost());
            favAtk.setText("ATK: " + p.getFavoriteCard().getAttack());
            favDef.setText("DEF: " + p.getFavoriteCard().getDefence());
        }
        ////////////////////////////////////////////////////////////////////////////


        //button onClick listeners
        btn.setOnClickListener((v)->
        {
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.btn));
            Intent intent = new Intent(getApplicationContext(), store.class);
            startActivity(intent);
        });


        //menu onClick//////////////////////////////////////////////////////////////
        menuHome.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuHome));
        });

        menuDeck.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuDeck));
            Intent intent = new Intent(this, ViewDeck.class);
            startActivity(intent);
        });

        menuQuest.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuQuest));
            Intent intent = new Intent(this, questing.class);
            startActivity(intent);
        });

        menuMis.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuMis));
        });

        menuOptions.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuOptions));
        });
        ////////////////////////////////////////////////////////////////////////////

    }//end onCreate
}
