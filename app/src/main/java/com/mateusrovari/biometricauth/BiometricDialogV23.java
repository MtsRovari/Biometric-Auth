package com.mateusrovari.biometricauth;

import android.animation.Animator;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class BiometricDialogV23 extends BottomSheetDialog implements View.OnClickListener {

    private Context context;

    private Button btnCancel;
    private ImageView imgLogo;
    private LottieAnimationView lottieAnimationView;
    private TextView itemTitle, itemDescription, itemSubtitle, itemStatus;

    private BiometricCallback biometricCallback;

    public BiometricDialogV23(@NonNull Context context) {
        super(context, R.style.BottomSheetDialogTheme);
        this.context = context.getApplicationContext();
        setDialogView();
    }

    public BiometricDialogV23(@NonNull Context context, BiometricCallback biometricCallback) {
        super(context, R.style.BottomSheetDialogTheme);
        this.context = context.getApplicationContext();
        this.biometricCallback = biometricCallback;
        setDialogView();
    }

    public BiometricDialogV23(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected BiometricDialogV23(@NonNull Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void setDialogView() {
        View bottomSheetView = getLayoutInflater().inflate(R.layout.view_bottom_sheet, null);
        setContentView(bottomSheetView);

        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);

        imgLogo = findViewById(R.id.img_logo);
        itemTitle = findViewById(R.id.item_title);
        itemStatus = findViewById(R.id.item_status);
        itemSubtitle = findViewById(R.id.item_subtitle);
        itemDescription = findViewById(R.id.item_description);
        lottieAnimationView = findViewById(R.id.loader);

        updateLogo();
    }

    public void setTitle(String title) {
        itemTitle.setText(title);
    }

    public void updateStatus(String status) {
        itemStatus.setText(status);
    }

    public void initFingerAnimation(boolean isSuccess) {
        if (isSuccess) {
            lottieAnimationView.setAnimation(R.raw.finger_animation);
            lottieAnimationView.playAnimation();
            lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            lottieAnimationView.setAnimation(R.raw.wrong_finger_animation);
            lottieAnimationView.playAnimation();
        }
    }

    public void setSubtitle(String subtitle) {
        itemSubtitle.setText(subtitle);
    }

    public void setDescription(String description) {
        itemDescription.setText(description);
    }

    public void setButtonText(String negativeButtonText) {
        btnCancel.setText(negativeButtonText);
    }

    private void updateLogo() {
        try {
            Drawable drawable = getContext().getPackageManager().getApplicationIcon(context.getPackageName());
            imgLogo.setImageDrawable(drawable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View view) {
        dismiss();
        biometricCallback.onAuthenticationCancelled();
    }
}
