package com.example.hppavilionabbas.kaioprimegym;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Inicio extends Activity {

    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private TextView[] mDots;
    private SliderAdapter sliderAdapter;
    private Button mBackBtn;
    private Button mNextBtn;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        mSlideViewPager = (ViewPager) findViewById(R.id.slideWiewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);

        mNextBtn = (Button) findViewById(R.id.nextBtn);
        mBackBtn = (Button) findViewById(R.id.prevBtn);

        sliderAdapter = new SliderAdapter(this);

        mSlideViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);

        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage+1);
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mSlideViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }

    public void addDotsIndicator(int position)
    {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();

        for(int i = 0; i < mDots.length ; i++ ) {
                mDots[i] = new TextView(this);
                mDots[i].setText(Html.fromHtml("&#8226;"));
                mDots[i].setTextSize(35);
                mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
                mDotLayout.addView(mDots[i]);
        }

        if(mDots.length > 0)
        {
            mDots[position].setTextColor(getResources().getColor(R.color.colorRed));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1)
        {

        }

        @Override
        public void onPageSelected(int i)
        {
                addDotsIndicator(i);
                mCurrentPage = i;

                if(i == 0)
                {
                    mNextBtn.setEnabled(true);
                    mBackBtn.setEnabled(false);
                    mBackBtn.setVisibility(View.INVISIBLE);

                    mNextBtn.setText("Siguiente");
                    mBackBtn.setText("");
                }
                else if ( i == mDots.length -1)
                {
                    mNextBtn.setEnabled(true);
                    mBackBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);

                    mNextBtn.setText("Siguiente");
                    mBackBtn.setText("Atrás");
                }

                else
                {
                    mNextBtn.setEnabled(true);
                    mBackBtn.setEnabled(true);
                    mBackBtn.setVisibility(View.VISIBLE);

                    mNextBtn.setText("Siguiente");
                    mBackBtn.setText("Atrás");
                }
        }

        @Override
        public void onPageScrollStateChanged(int i)
        {

        }
    };

}
