package com.avocode.alikhsan.basketballscoringapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    //Inisialisai id secara public pada semua element view yang tersedia dilayout
    @BindView(R.id.iv_crown)
    ImageView mCrown;
    @BindView(R.id.name_team_winner)
    TextView mNameTeamWinner;
    @BindView(R.id.tv_score_team_winner)
    TextView mScoreTeamWinner;
    @BindView(R.id.ll_winner)
    LinearLayout mWiner;

    //Inisialisai class animasi secara public
    Animation nAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //Menghubungkan id pada activity dengan menggunakan Class Butterknife;
        ButterKnife.bind(this);

        //Mendapatkan actionbar default
        getSupportActionBar().setTitle("Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Mendeklarasikan load/masukkan dengan parameter nama activity, file resource animasi
        nAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_crown);

        //Mendeklarasikan penggunaan animasi pada image
        mCrown.startAnimation(nAnimation);

        //Local variable bundle dengan parsing data berasal dari intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mNameTeamWinner.setText(bundle.getString("name_winner"));
            mScoreTeamWinner.setText(String.valueOf(bundle.getInt("score_winner")));
            mScoreTeamWinner.startAnimation(nAnimation);

            switch (bundle.getInt("color_winner")) {
                case 1:
                    mWiner.setBackground(getResources().getDrawable(R.drawable.custom_layout_winner_red));
                    break;
                case 2:
                    mWiner.setBackground(getResources().getDrawable(R.drawable.custom_layout_winner_blue));
                    break;
                default:
                    mWiner.setBackground(getResources().getDrawable(R.drawable.custom_layout_winner_blue));
                    break;
            }
            mWiner.startAnimation(nAnimation);
        }
    }
    //menempelkan (inflate) file resourse menu pada option menu actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_share, menu);
        return true;

    }

    //memberikan event pada id di setiap menu dalam menu item yang tersedia
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.mn_share) {
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto: " + "Share Score"));
            i.putExtra(Intent.EXTRA_SUBJECT, "Score" + mNameTeamWinner.getText().toString());
            i.putExtra(Intent.EXTRA_TEXT, mText());
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        } else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private String mText(){
        String text = "";
        text = "Yeay, Team " +mNameTeamWinner.getText().toString()+
                " won a score of "+ mScoreTeamWinner.getText().toString()+". " +
                "\nSpread this info to other friends to enliven this victory";
        return  text;
    }
}
