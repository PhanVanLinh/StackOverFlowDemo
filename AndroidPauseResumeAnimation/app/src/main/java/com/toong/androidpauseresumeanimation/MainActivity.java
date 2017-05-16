package com.toong.androidpauseresumeanimation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<Animator> mAnimatorList = new ArrayList<>();

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    anim_button(R.id.btn1, 4000);
    anim_button(R.id.btn2, 6000);
}

private void anim_button(int id, long duration)
{
    Button b = (Button) findViewById(id);
    Animator animator = AnimatorInflater.loadAnimator(this, R.animator.fade_out);
    animator.setTarget(b);
    animator.setDuration(duration);
    animator.start();

    mAnimatorList.add(animator);
}

@Override
protected void onPause() {
    super.onPause();
    for (Animator animator: mAnimatorList) {
        animator.pause();
    }
}

@Override
protected void onResume() {
    super.onResume();
    for (Animator animator: mAnimatorList) {
        animator.resume();
    }
}

    public void checkAllAnimatorComplete(){
        for (Animator animator: mAnimatorList) {
        }
    }
}
