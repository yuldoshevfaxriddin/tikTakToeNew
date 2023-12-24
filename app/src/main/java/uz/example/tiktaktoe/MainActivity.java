package uz.example.tiktaktoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean playerOneActive ;
    private TextView playerOne,playerTwo,playerStatus;
    private Button[] buttons = new Button[9];
    private Button reset,playagain,change_bg;
    private int playerOneScoreCount,playerTwoScoreCount;
    int rounds ,change_bg_count;
    private RelativeLayout main_activity;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions = {
            {0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}
    };
//
// 0 1 2
// 3 4 5
// 6 7 8
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerOne = findViewById(R.id.text_score1);
        playerTwo = findViewById(R.id.text_score2);
        playerStatus = findViewById(R.id.textStatus);
        reset = findViewById(R.id.btn_reset);
        playagain = findViewById(R.id.btn_play_again);

        change_bg = findViewById(R.id.change_bg);
        main_activity = findViewById(R.id.mainActivity);

        buttons[0] = findViewById(R.id.btn0);
        buttons[1] = findViewById(R.id.btn1);
        buttons[2] = findViewById(R.id.btn2);
        buttons[3] = findViewById(R.id.btn3);
        buttons[4] = findViewById(R.id.btn4);
        buttons[5] = findViewById(R.id.btn5);
        buttons[6] = findViewById(R.id.btn6);
        buttons[7] = findViewById(R.id.btn7);
        buttons[8] = findViewById(R.id.btn8);

        for(int i=0 ;i<buttons.length;i++){
            buttons[i].setOnClickListener(this);
        }
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        playerOneActive = true;
        rounds = 0;
        change_bg_count = 1 ;

        change_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            switch(change_bg_count){
                case 0: main_activity.setBackgroundResource(R.drawable.img);
                    break;
                case 1: main_activity.setBackgroundResource(R.drawable.img_1);
                    break;
                case 2: main_activity.setBackgroundResource(R.drawable.images1);
                    break;
                case 3: main_activity.setBackgroundResource(R.drawable.images2);
                    break;
                case 4: main_activity.setBackgroundResource(R.drawable.image3);
                    break;
                case 5: main_activity.setBackgroundResource(R.drawable.img_2);
                    break;
                case 6: main_activity.setBackgroundResource(R.drawable.image5);
                    break;
                case 7: main_activity.setBackgroundResource(R.drawable.image6);
                    break;
                case 8: main_activity.setBackgroundResource(R.drawable.img_3);
                    break;
                case 9: main_activity.setBackgroundResource(R.drawable.image8);
                    break;
            }
                change_bg_count ++;
                change_bg_count = change_bg_count % 10;

            }
        });

    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return ;
        }
        else if(checkWinner()){
            return ;
        }
        String buttonId = view.getResources().getResourceEntryName(view.getId());
        int gameStatePointer = Integer.parseInt(buttonId.substring(buttonId.length()-1,buttonId.length()));

        if(playerOneActive){
            ((Button)view).setText("X");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 0;
        }
        else {

            ((Button)view).setText("O");
            ((Button)view).setTextColor(Color.parseColor("#ffc34a"));
            gameState[gameStatePointer] = 1;
        }
        rounds ++;
        if(checkWinner()){
            if(playerOneActive){
                playerOneScoreCount++;
                updatePlayerScore();
                playerStatus.setText(R.string.oneWinner);
            }
            else{
                playerTwoScoreCount++;
                updatePlayerScore();
                playerStatus.setText(R.string.twoWinner);
            }
        }
        else if(rounds==9){
            playerStatus.setText(R.string.noWinner);
        }
        else{
            playerOneActive = !playerOneActive;
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                updatePlayerScore();
            }
        });
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();

            }
        });



    }

    private boolean checkWinner() {
        boolean winnerResults = false;
        for(int[] winningPositions:winningPositions){
            if(gameState[winningPositions[0]] == gameState[winningPositions[1]]  &&
                    gameState[winningPositions[1]] == gameState[winningPositions[2]] && gameState[winningPositions[0]]!=2){
                winnerResults = true;
            }

        }
        return winnerResults;
    }

    private void playAgain() {
        rounds = 0;
        playerOneActive = true;
        for(int i=0 ;i< buttons.length;i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
        playerStatus.setText(R.string.status);
    }

    private void updatePlayerScore() {
        playerOne.setText(Integer.toString(playerOneScoreCount));
        playerTwo.setText(Integer.toString(playerTwoScoreCount));

    }
}