package com.gmail.baric0748;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class ViewDeck extends AppCompatActivity {

    private RecyclerView recyclerView;
    private recyclerAdapter recyclerAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private Button deleteDec;

    private ImageView deck1;
    private ImageView deck2;
    private ImageView deck3;
    private ImageView deck4;
    private ImageView deck5;

    private TextView playerCoin;
    private TextView deckSum;

    //menu buttons
    private ImageView menuHome;
    private ImageView menuDeck;
    private ImageView menuQuest;
    private ImageView menuMis;
    private ImageView menuOptions;

    private player p = player.getPlayerInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_deck);

        recyclerView = findViewById(R.id.recyclerView);
        deleteDec = findViewById(R.id.deleteDec);

        deck1 = findViewById(R.id.deck1);
        deck2 = findViewById(R.id.deck2);
        deck3 = findViewById(R.id.deck3);
        deck4 = findViewById(R.id.deck4);
        deck5 = findViewById(R.id.deck5);

        //menu
        menuHome = findViewById(R.id.menuHome);
        menuDeck = findViewById(R.id.menuDeck);
        menuQuest = findViewById(R.id.menuQuest);
        menuMis = findViewById(R.id.menuMis);
        menuOptions = findViewById(R.id.menuOptions);

        playerCoin = findViewById(R.id.playerCoin);
        deckSum = findViewById(R.id.deckSum);

        playerCoin.setText("Coins: " + p.getCoins());

        //for recyclerView layout
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerAdapter = new recyclerAdapter(p.getPlayerCards());
        recyclerView.setLayoutManager(layoutManager);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(recyclerAdapter);
        //////////////////////////////////////////////////////////////////////////////////////////////////

        //when the player deck is filled with at least one entery will populate the the deckImageView
        //for players card and save it no matter what screen they go to
        if(p.getPlayerDeck().size() == 1)
        {
            deck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
        }
        else if(p.getPlayerDeck().size() == 2)
        {
            deck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            deck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
        }
        else if(p.getPlayerDeck().size() == 3)
        {
            deck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            deck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
            deck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
        }
        else if(p.getPlayerDeck().size() == 4)
        {
            deck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            deck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
            deck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
            deck4.setImageResource(p.getPlayerDeck().get(3).getTexture());
        }
        else if(p.getPlayerDeck().size() == 5)
        {
            deck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
            deck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
            deck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
            deck4.setImageResource(p.getPlayerDeck().get(3).getTexture());
            deck5.setImageResource(p.getPlayerDeck().get(4).getTexture());
        }
        //////////////////////////////////////////////////////////////////

        //when the activity is started will populate the textView for deckCost, deckAttack,
        //deckDefence
        //TODO add deck defence textView to this activity
        deckSum.setText("Deck Power:" + p.deckTotalAttack());

        //this is the button that deletes the
        deleteDec.setOnClickListener((v)->{

            YoYo.with(Techniques.RubberBand).playOn(findViewById(R.id.deleteDec));
            p.clearDeck();

            deck1.setImageResource(R.drawable.card_back);
            deck2.setImageResource(R.drawable.card_back);
            deck3.setImageResource(R.drawable.card_back);
            deck4.setImageResource(R.drawable.card_back);
            deck5.setImageResource(R.drawable.card_back);
            deckSum.setText("Deck Power:" + p.deckTotalAttack());
        });

        recyclerAdapter.setOnItemClickListener(new recyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int pos) {
                Toast.makeText(getApplicationContext(), "card: " + pos + " -" + p.getPlayerCards().get(pos).getTitle() + "-", Toast.LENGTH_LONG).show();
                dialogAlert(pos);
            }

            @Override
            public void onSetFavorite(int pos) {



                if (p.getPlayerDeck().size() < 5 && !p.getPlayerDeck().contains(p.getPlayerCards().get(pos))) {

                    //when the button is on do this
                    p.getPlayerCards().get(pos).setFavorite(true);
                    p.setSinglePlayerDeck(p.getPlayerCards().get(pos));

                    if (p.getPlayerDeck().size() == 1) {
                        deck1.setImageResource(p.getPlayerDeck().get(0).getTexture());
                    } else if (p.getPlayerDeck().size() == 2) {
                        deck2.setImageResource(p.getPlayerDeck().get(1).getTexture());
                    } else if (p.getPlayerDeck().size() == 3) {
                        deck3.setImageResource(p.getPlayerDeck().get(2).getTexture());
                    } else if (p.getPlayerDeck().size() == 4) {
                        deck4.setImageResource(p.getPlayerDeck().get(3).getTexture());
                    } else if (p.getPlayerDeck().size() == 5) {
                        deck5.setImageResource(p.getPlayerDeck().get(4).getTexture());
                    }

                    deckSum.setText("Deck Power:" + p.deckTotalAttack());
                } else {
                    Toast.makeText(getApplicationContext(), "you can only set 5 cards and one of each type", Toast.LENGTH_LONG).show();

                }
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


    }//end on create

    //everything for dialog popup to view card details
    public void dialogAlert(int pos)
    {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.card_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //ImageView card1 = dialogView.findViewById(R.id.card1);
        TextView dialog_title = dialogView.findViewById(R.id.dialog_title);
        TextView race = dialogView.findViewById(R.id.race);
        TextView powerCost = dialogView.findViewById(R.id.powerCost);
        TextView atk = dialogView.findViewById(R.id.atk);
        TextView def = dialogView.findViewById(R.id.def);

        ImageView dialog_image = dialogView.findViewById(R.id.dialog_image);

        Button setFavoriteClick = dialogView.findViewById(R.id.setFavorite);
        Button saleCard = dialogView.findViewById(R.id.saleCard);
        Button back = dialogView.findViewById(R.id.back);
        Button wishList = dialogView.findViewById(R.id.wish);

        dialog_title.setText(p.getPlayerCards().get(pos).getTitle());
        dialog_image.setImageResource(p.getPlayerCards().get(pos).getTexture());
        powerCost.setText("Power Cost: " + p.getPlayerCards().get(pos).getPowerCost());
        atk.setText("Attack: " + p.getPlayerCards().get(pos).getAttack());
        def.setText("Defence: " + p.getPlayerCards().get(pos).getDefence());
        saleCard.setText("Sale for: " + p.getPlayerCards().get(pos).getCardSalePrice() + " Coins");

        switch (p.getPlayerCards().get(pos).getRace())
        {
            case 1:
                race.setText("Human");
                break;
            case 2:
                race.setText("God");
                break;
            case 3:
                race.setText("Demon");
                break;
        }

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        setFavoriteClick.setOnClickListener((v)->{
            p.setFavoriteCard(p.getPlayerCards().get(pos));

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        //when a card is sold
        saleCard.setOnClickListener((v)->{
            p.setCoins(p.getPlayerCards().get(pos).getCardSalePrice());
            p.removeCard(p.getPlayerCards().get(pos));
            recyclerAdapter.removeAt(pos);


            Intent intent = new Intent(this, ViewDeck.class);
            startActivity(intent);
        });

        //closes dialog
        back.setOnClickListener((v)->{
            Intent intent = new Intent(this, ViewDeck.class);
            startActivity(intent);
        });


        wishList.setOnClickListener((v)->{
            p.setWishList(p.getPlayerCards().get(pos));

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        alertDialog.show();
    }


    //when the back button is pressed it will bring you to the main activity and
    //save the favorite to the new activity good work around to save the favorite card
    //to the player object not sure if this will work in all cases
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}//end class
