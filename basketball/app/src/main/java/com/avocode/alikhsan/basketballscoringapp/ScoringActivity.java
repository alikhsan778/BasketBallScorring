package com.avocode.alikhsan.basketballscoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScoringActivity extends AppCompatActivity {
    //menyediakan text constant berasal dari nama class
    private static final String TAG = ScoringActivity.class.getSimpleName();
    //Inisialisai id secara public pada semua element view yang tersedia dilayout
    @BindView(R.id.v_team_one)
    View mLineOne;
    @BindView(R.id.v_team_two)
    View mLineTwo;
    @BindView(R.id.tv_score_team_one)
    TextView mScoreTeamOne;
    @BindView(R.id.tv_score_team_two)
    TextView mScoreTeamTwo;
    @BindView(R.id.tv_name_team_one)
    TextView mNameTeamOne;
    @BindView(R.id.tv_name_team_two)
    TextView mNameTeamTwo;
    @BindView(R.id.btn_one_point_team_one)
    Button mOnePointTeamOne;
    @BindView(R.id.btn_one_point_team_two)
    Button mOnePointTeamTwo;
    @BindView(R.id.btn_two_point_team_one)
    Button mTwoPointTeamOne;
    @BindView(R.id.btn_two_point_team_two)
    Button mTwoPointTeamTwo;
    @BindView(R.id.btn_tree_point_team_one)
    Button mTreePointTeamOne;
    @BindView(R.id.btn_tree_point_team_two)
    Button mTreePointTeamTwo;
    @BindView(R.id.btn_reset)
    Button mReset;
    @BindView(R.id.btn_result)
    Button mResult;
    //encapsulasi secara private dengan deklarasi public variable
    private int scoreTeamOne, scoreTeamTwo;
    public int colorTeamA, colorTeamB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);
        //Menghubungkan id pada activity dengan menggunakan Class Butterknife;
        ButterKnife.bind(this);
        //Mendapatkan actionbar default
        getSupportActionBar().setTitle("Basket Ball Scoring");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        resetPoint();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            colorTeamA = bundle.getInt("color_team_one");
            colorTeamB = bundle.getInt("color_team_two");

            mNameTeamOne.setText(bundle.getString("name_team_one"));
            mNameTeamTwo.setText(bundle.getString("name_team_two"));
            switch (bundle.getInt("color_team_one")) {
                case 1:
                    mNameTeamOne.setTextColor(getResources().getColor(R.color.colorRed));
                    mLineOne.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    break;
                case 2:
                    mNameTeamOne.setTextColor(getResources().getColor(R.color.colorHBlue));
                    mLineOne.setBackgroundColor(getResources().getColor(R.color.colorHBlue));
                    break;
                default:
                    mNameTeamOne.setTextColor(getResources().getColor(R.color.colorBlack));
                    mLineOne.setBackgroundColor(getResources().getColor(R.color.colorGren));
                    break;
            }
            switch (bundle.getInt("color_team_two")) {
                case 1:
                    mNameTeamTwo.setTextColor(getResources().getColor(R.color.colorRed));
                    mLineTwo.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    break;
                case 2:
                    mNameTeamTwo.setTextColor(getResources().getColor(R.color.colorHBlue));
                    mLineTwo.setBackgroundColor(getResources().getColor(R.color.colorHBlue));
                    break;
                default:
                    mNameTeamTwo.setTextColor(getResources().getColor(R.color.colorBlack));
                    mLineTwo.setBackgroundColor(getResources().getColor(R.color.colorGren));
                    break;
            }

        } else {
            exceptBundle();
            Log.d(TAG, "No Bundle");
        }

        mTreePointTeamOne.setOnClickListener(actionTreePointTeamOne);
        mTwoPointTeamOne.setOnClickListener(actionTwoPointTeamOne);
        mOnePointTeamOne.setOnClickListener(actionOnePointTeamOne);

        mTreePointTeamTwo.setOnClickListener(actionTreePointTeamTwo);
        mTwoPointTeamTwo.setOnClickListener(actionTwoPointTeamTwo);
        mOnePointTeamTwo.setOnClickListener(actionOnePointTeamTwo);

        mReset.setOnClickListener(actionResetListener);
        mResult.setOnClickListener(actionResultListener);
    }

    private void exceptBundle() {
        mNameTeamOne.setText(getResources().getText(R.string.team_1));
        mNameTeamOne.setTextColor(getResources().getColor(R.color.colorBlack));
        mNameTeamTwo.setText(getResources().getText(R.string.team_2));
        mNameTeamTwo.setTextColor(getResources().getColor(R.color.colorBlack));
    }

    private void pointTeamOne() {
        mLineOne.setVisibility(View.VISIBLE);
        mLineTwo.setVisibility(View.INVISIBLE);
        mScoreTeamOne.setText(String.valueOf(scoreTeamOne));
    }

    private void pointTeamTwo() {
        mLineOne.setVisibility(View.INVISIBLE);
        mLineTwo.setVisibility(View.VISIBLE);
        mScoreTeamTwo.setText(String.valueOf(scoreTeamTwo));
    }

    private void resetPoint() {
        scoreTeamOne = 0;
        scoreTeamTwo = 0;
        mLineOne.setVisibility(View.INVISIBLE);
        mLineTwo.setVisibility(View.INVISIBLE);
        mScoreTeamOne.setText(String.valueOf(scoreTeamOne));
        mScoreTeamTwo.setText(String.valueOf(scoreTeamTwo));

        mOnePointTeamOne.setEnabled(true);
        mTwoPointTeamOne.setEnabled(true);
        mTreePointTeamOne.setEnabled(true);

        mOnePointTeamTwo.setEnabled(true);
        mTwoPointTeamTwo.setEnabled(true);
        mTreePointTeamTwo.setEnabled(true);
    }

    private void limitPoint(String idTeam) {
        if (idTeam.equals("Team 1")) {
            if (scoreTeamOne >= 100) {
                scoreTeamOne = 99;
                mScoreTeamOne.setText(String.valueOf(scoreTeamOne));
                mOnePointTeamOne.setEnabled(false);
                mTwoPointTeamOne.setEnabled(false);
                mTreePointTeamOne.setEnabled(false);
            }
        } else {
            if (scoreTeamTwo >= 100) {
                scoreTeamTwo = 99;
                mScoreTeamTwo.setText(String.valueOf(scoreTeamTwo));
                mOnePointTeamTwo.setEnabled(false);
                mTwoPointTeamTwo.setEnabled(false);
                mTreePointTeamTwo.setEnabled(false);
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    View.OnClickListener actionTreePointTeamOne = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scoreTeamOne += 3;
            pointTeamOne();
            limitPoint("Team 1");
        }
    };

    View.OnClickListener actionTwoPointTeamOne = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scoreTeamOne += 2;
            pointTeamOne();
            limitPoint("Team 1");
        }
    };

    View.OnClickListener actionOnePointTeamOne = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scoreTeamOne += 1;
            pointTeamOne();
            limitPoint("Team 1");
        }
    };

    View.OnClickListener actionTreePointTeamTwo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scoreTeamTwo += 3;
            pointTeamTwo();
            limitPoint("Team 2");
        }
    };

    View.OnClickListener actionTwoPointTeamTwo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scoreTeamTwo += 2;
            pointTeamTwo();
            limitPoint("Team 2");
        }
    };

    View.OnClickListener actionOnePointTeamTwo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            scoreTeamTwo += 1;
            pointTeamTwo();
            limitPoint("Team 2");
        }
    };

    View.OnClickListener actionResetListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetPoint();
        }
    };

    View.OnClickListener actionResultListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String theWinner = null;
            int scoreWinner = 0,colorWinner = 0;
            boolean checked = true;
            if (scoreTeamOne == 99 && scoreTeamTwo == 99) {
                if (mLineOne.getVisibility() == View.VISIBLE) {
                    theWinner = mNameTeamTwo.getText().toString() ;
                    scoreWinner = scoreTeamTwo;
                    colorWinner = colorTeamB;
                } else {
                    theWinner =  mNameTeamOne.getText().toString() ;
                    scoreWinner = scoreTeamOne;
                    colorWinner = colorTeamA;
                }

            } else {
                if (scoreTeamOne > scoreTeamTwo) {
                    theWinner =  mNameTeamOne.getText().toString() ;
                    scoreWinner = scoreTeamOne;
                    colorWinner = colorTeamA;
                } else if (scoreTeamTwo > scoreTeamOne) {
                    theWinner = mNameTeamTwo.getText().toString() ;
                    scoreWinner = scoreTeamTwo;
                    colorWinner = colorTeamB;
                } else {
                    Toast.makeText(ScoringActivity.this, "Oops Isn't playing now", Toast.LENGTH_SHORT).show();
                    checked = false;
                }
            }
            if (checked){
                Bundle bundle = new Bundle();
                bundle.putString("name_winner",theWinner);
                bundle.putInt("score_winner",scoreWinner);
                bundle.putInt("color_winner",colorWinner);
                Intent intent = new Intent(ScoringActivity.this,ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }

        }
    };
}
