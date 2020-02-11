package com.gmail.baric0748;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Random;

public class questing extends AppCompatActivity {

    private player p = player.getPlayerInstance();
    private cards c = cards.getCardInstance();

    //buttons
    private Button getExp;

    //deck view at top of the page
    private ImageView questDeck1;
    private ImageView questDeck2;
    private ImageView questDeck3;
    private ImageView questDeck4;
    private ImageView questDeck5;
    private ImageView questDeck6;

    //data in the deck view next to favorite card
    private TextView questFavName;
    private TextView questTotalCost;
    private TextView questTotalAtk;
    private TextView questTotalDef;

    //quest background scene
    //data fields
    //quest counter
    //exp counter
    private ImageView questBasicScene;
    private int count = 1;

    //menu buttons
    private ImageView menuHome;
    private ImageView menuDeck;
    private ImageView menuQuest;
    private ImageView menuMis;
    private ImageView menuOptions;

    //players exp
    private ProgressBar qProgressBar;
    //private TextView qExp;
    private TextView qLevel;
    private TextView qCardCount;
    private TextView qCoins;

    //for backgrounds
    private ArrayList<Integer> qBackground = new ArrayList<>();
    private Random qBackgoundPicker = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questing);

        //buttons
        getExp = findViewById(R.id.getExp);

        //menu
        menuHome = findViewById(R.id.menuHome);
        menuDeck = findViewById(R.id.menuDeck);
        menuQuest = findViewById(R.id.menuQuest);
        menuMis = findViewById(R.id.menuMis);
        menuOptions = findViewById(R.id.menuOptions);

        //deck View
        questDeck1 = findViewById(R.id.questDeck1);
        questDeck2 = findViewById(R.id.questDeck2);
        questDeck3 = findViewById(R.id.questDeck3);
        questDeck4 = findViewById(R.id.questDeck4);
        questDeck5 = findViewById(R.id.questDeck5);
        questDeck6 = findViewById(R.id.questDeck6);

        //deck data
        questFavName = findViewById(R.id.questFavName);
        questTotalCost = findViewById(R.id.questTotalCost);
        questTotalAtk = findViewById(R.id.questTotalAtk);
        questTotalDef = findViewById(R.id.questTotalDef);

        //players expBar
        qProgressBar = findViewById(R.id.qProgressBar);
        //qExp = findViewById(R.id.qExp);
        qLevel = findViewById(R.id.qLevel);
        qCardCount = findViewById(R.id.qCardCount);
        qCoins = findViewById(R.id.qCoins);

        //setting players coin
        qCoins.setText("Coins:" + p.getCoins());
        qCardCount.setText(""+p.sizeOf());
        qLevel.setText("" + p.getLevel());


        //when the player exp hits max exp will level up the user and bump up the progress
        //bar max number
        qProgressBar.setMax(p.getMaxExp4Lvl());
        qProgressBar.setProgress(p.getExp());
        //qExp.setText(p.getExp() + "/" + qProgressBar.getMax());



        //null pointer checks//////////////////////////////////////////
        if(p.getFavoriteCard() != null) {
            questFavName.setText(p.getFavoriteCard().getTitle() + "'s Team");
        }

        if(!p.getPlayerDeck().isEmpty()) {
            questTotalCost.setText("Cost: " + p.deckCost());
            questTotalAtk.setText("ATK: " + p.deckTotalAttack());
            questTotalDef.setText("Def: " + p.deckTotalDefence());
        }
        //////////////////////////////////////////////////////////////////

        //setting the background images to array
        qBackground.add(R.drawable.quest_walkway_forest);
        qBackground.add(R.drawable.quest_walkway_forest1);
        qBackground.add(R.drawable.quest_walkway_forest2);

        //setting the scene
        questBasicScene = findViewById(R.id.questBasicScene);
        questBasicScene.setImageResource(qBackground.get(qBackgoundPicker.nextInt(qBackground.size())));

        //null checking for deck of cards
        //setting deck of cards///////////////////////////////////////////////////
        if(p.getFavoriteCard() != null)
        {
            questDeck6.setImageResource(p.getFavoriteCard().getTexture());
        }

        if(p.getPlayerDeck().size() == 1)
        {
            questDeck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
        }
        else if(p.getPlayerDeck().size() == 2)
        {
            questDeck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            questDeck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
        }
        else if(p.getPlayerDeck().size() == 3)
        {
            questDeck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            questDeck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
            questDeck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
        }
        else if(p.getPlayerDeck().size() == 4)
        {
            questDeck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            questDeck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
            questDeck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
            questDeck4.setImageResource(p.getPlayerDeck().get(3).getTexture());
        }
        else if(p.getPlayerDeck().size() == 5)
        {
            questDeck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            questDeck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
            questDeck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
            questDeck4.setImageResource(p.getPlayerDeck().get(3).getTexture());
            questDeck5.setImageResource(p.getPlayerDeck().get(4).getTexture());
        }
        ////////////////////////////////////////////////////////////////////////////

        //when the button is clicked opens the questing alert
        //add one to the quest counter
        getExp.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.getExp));

            if(p.getFavoriteCard() != null && p.getPlayerDeck().size() > 0) {
                questingAlert();
                p.setQuestCount(1);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please Make a team to go forth", Toast.LENGTH_LONG).show();
            }
        });


        //menu onClick//////////////////////////////////////////////////////////////
        menuHome.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuHome));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        menuDeck.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuDeck));
            Intent intent = new Intent(this, ViewDeck.class);
            startActivity(intent);
        });

        menuQuest.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuQuest));
        });

        menuMis.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuMis));
        });

        menuOptions.setOnClickListener((v)->{
            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.menuOptions));
        });
        ////////////////////////////////////////////////////////////////////////////

    }//end onCreate

    //when in the questing dialog Alert
    public void questingAlert()
    {
        //random generators
        Random r = new Random();
        int rand1 = r.nextInt(c.getOneStar().size());
        int rand2 = r.nextInt(c.getOneStar().size());
        int rand3 = r.nextInt(c.getOneStar().size());
        int rand4 = r.nextInt(c.getOneStar().size());
        int coinRand = r.nextInt(33);

        //setting up the alert dialog/////////////////////////////////////////////////////////////
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View questView = LayoutInflater.from(this).inflate(R.layout.quest_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(questView);
        AlertDialog alertDialog = builder.create();
        /////////////////////////////////////////////////////////////////////////////////////////

        //questing area elements
        LinearLayout l = questView.findViewById(R.id.linearLayout);
        ImageView enemy1 = questView.findViewById(R.id.enemy1);
        ImageView enemy2 = questView.findViewById(R.id.enemy2);
        ImageView enemy3 = questView.findViewById(R.id.enemy3);
        Button enemyAtk = questView.findViewById(R.id.enemyAtk);

        //rewards area elements
        ImageView questWinCard = questView.findViewById(R.id.questWinCard);
        ImageView questCoinsPic = questView.findViewById(R.id.questCoinsPic);
        TextView questCoinsAmount = questView.findViewById(R.id.questCoinsAmount);

        //when dialog starts make rewards INVISIBLE
        questWinCard.setVisibility(View.INVISIBLE);
        questCoinsPic.setVisibility(View.INVISIBLE);

        //set questing back ground and enemies
        l.setBackgroundResource(qBackground.get(qBackgoundPicker.nextInt(qBackground.size())));
        enemy1.setImageResource(c.getOneStar().get(rand1).getTexture());
        enemy2.setImageResource(c.getOneStar().get(rand2).getTexture());
        enemy3.setImageResource(c.getOneStar().get(rand3).getTexture());


        enemyAtk.setOnClickListener(v->{

            YoYo.with(Techniques.RubberBand).playOn(questView.findViewById(R.id.enemyAtk));

            //when the atk button is hit for the first time it, flashes the screen,
            //shakes the cards
            //sets the image view to INVISIBLE
            //+1 to quest count
            if(count == 1)
            {
                YoYo.with(Techniques.Flash).playOn(questView.findViewById(R.id.linearLayout));
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                enemy1.startAnimation(shake);
                enemy1.setVisibility(View.INVISIBLE);
                count++;
            }
            //when the atk button is hit for the second time it, flashes the screen,
            //shakes the cards
            //sets the image view to INVISIBLE
            //+1 to quest count
            else if(count == 2)
            {
                YoYo.with(Techniques.Flash).playOn(questView.findViewById(R.id.linearLayout));
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                enemy2.startAnimation(shake);
                enemy2.setVisibility(View.INVISIBLE);
                count++;
            }
            //when the atk button is hit for the third time it, flashes the screen,
            //shakes the cards
            //sets the image view to INVISIBLE
            //+1 to quest count
            //makes the reward area VISIBLE
            //sets the winners card to players cards
            //sets winning coins to players coins
            //sets text to the button "get rewards"
            else if(count == 3)
            {
                YoYo.with(Techniques.Flash).playOn(questView.findViewById(R.id.linearLayout));
                Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
                enemy3.startAnimation(shake);
                enemy3.setVisibility(View.INVISIBLE);

                questWinCard.setVisibility(View.VISIBLE);
                questCoinsPic.setVisibility(View.VISIBLE);

                questWinCard.setImageResource(c.getOneStar().get(rand4).getTexture());
                p.setSinglePlayerCards(c.getOneStar().get(rand4));

                questCoinsPic.setImageResource(R.drawable.gold_coins_pic);
                questCoinsAmount.setText("+" + coinRand);
                p.setCoins(coinRand);

                enemyAtk.setText("GET REWARDS");
                count++;
            }
            else if(count == 4)
            {
                count = 0;
                p.setExp(10);
                qLevel.setText("" + p.getLevel());

                Intent intent = new Intent(this, questing.class);
                startActivity(intent);
            }

        });

        //show alert
        alertDialog.show();
    }
}
