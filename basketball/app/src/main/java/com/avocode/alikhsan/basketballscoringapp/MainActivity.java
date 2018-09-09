package com.avocode.alikhsan.basketballscoringapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //Inisialisai id secara public pada semua element view yang tersedia dilayout
    @BindView(R.id.ed_team_one)
    EditText mTeamOne;
    @BindView(R.id.ed_team_two)
    EditText mTeamTwo;
    @BindView(R.id.rb_one_red)
    RadioButton mOneRed;
    @BindView(R.id.rb_one_blue)
    RadioButton mOneBlue;
    @BindView(R.id.rb_two_red)
    RadioButton mTwoRed;
    @BindView(R.id.rb_two_blue)
    RadioButton mTwoBlue;
    @BindView(R.id.btn_go)
    Button mGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Mendapatkan actionbar default
        getSupportActionBar().setTitle("Team Entry");

        //Menghubungkan id pada activity dengan menggunakan Class Butterknife;
        ButterKnife.bind(this);

        mOneRed.setChecked(true);
        mTwoBlue.setChecked(true);
        //menggunakan event click pada button go
        mGo.setOnClickListener(actionGoListener);
    }

    View.OnClickListener actionGoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Boolean validColorTeam;
            validColorTeam = true;
            int colorPictOne, colorPictTwo;
            String mNameTeamOne, mNameTeamTwo;
            mNameTeamOne = mTeamOne.getText().toString();
            mNameTeamTwo = mTeamTwo.getText().toString();
            if (mNameTeamOne.isEmpty()) {
                mTeamOne.setError("Name Team One Must Be Write");
                mTeamOne.requestFocus();
            }
            if (mNameTeamTwo.isEmpty()) {
                mTeamTwo.setError("Name Team Two Must Be Write");
                mTeamTwo.requestFocus();
            }

            if (!mNameTeamOne.isEmpty() && !mNameTeamTwo.isEmpty()) {

                if (mOneRed.isChecked()) {
                    colorPictOne = 1;
                } else {
                    colorPictOne = 2;
                }
                if (mTwoRed.isChecked()) {
                    colorPictTwo = 1;
                } else {
                    colorPictTwo = 2;
                }
                if (mOneRed.isChecked() && mTwoRed.isChecked()) {
                    validColorTeam = false;
                }
                if (mOneBlue.isChecked() && mTwoBlue.isChecked()) {
                    validColorTeam = false;
                }
                if (validColorTeam) {
                    Bundle bundle = new Bundle();
                    bundle.putString("name_team_one", mNameTeamOne);
                    bundle.putString("name_team_two", mNameTeamTwo);
                    bundle.putInt("color_team_one", colorPictOne);
                    bundle.putInt("color_team_two", colorPictTwo);
                    Intent intent = new Intent(MainActivity.this, ScoringActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Color Team Not Be Same", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };
}
