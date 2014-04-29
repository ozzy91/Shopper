package com.ostfeld.shopper.app.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.ostfeld.shopper.app.R;

public class ListItemView extends RelativeLayout {

    private ImageButton btnDelete;
    private ImageButton btnEdit;

    private AnimatorSet fadeIn;
    private AnimatorSet fadeOut;
    private Runnable fadeOutRunnable;

    private boolean isButtonsVisible;

    public ListItemView(Context context) {
        super(context);
        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ListItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        View.inflate(context, R.layout.itm_shopping_list, this);

        btnDelete = (ImageButton) findViewById(R.id.btn_delete);
        btnEdit = (ImageButton) findViewById(R.id.btn_edit);

        ObjectAnimator animDeleteIn = ObjectAnimator.ofFloat(btnDelete, "alpha", 1f);
        ObjectAnimator animEditIn = ObjectAnimator.ofFloat(btnEdit, "alpha", 1f);
        fadeIn = new AnimatorSet();
        fadeIn.playTogether(animDeleteIn, animEditIn);

        ObjectAnimator animDeleteOut = ObjectAnimator.ofFloat(btnDelete, "alpha",1f, 0f);
        ObjectAnimator animEditOut = ObjectAnimator.ofFloat(btnEdit, "alpha",1f, 0f);
        fadeOut = new AnimatorSet();
        fadeOut.playTogether(animDeleteOut, animEditOut);
        fadeOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                btnEdit.setVisibility(View.INVISIBLE);
                btnDelete.setVisibility(View.INVISIBLE);
            }
        });

        fadeOutRunnable = new Runnable() {
            @Override
            public void run() {
                fadeOut.start();
                isButtonsVisible = false;
            }
        };
    }

    public void toggleButtonsVisibility() {
        if (isButtonsVisible) {
            removeCallbacks(fadeOutRunnable);
            fadeOut.start();
            isButtonsVisible = false;
        } else {
            btnEdit.setVisibility(View.VISIBLE);
            btnDelete.setVisibility(View.VISIBLE);
            fadeIn.start();
            isButtonsVisible = true;
            postDelayed(fadeOutRunnable, 3000);
        }
    }


}
