package com.rzsahu.demoanimation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import android.view.animation.ScaleAnimation
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val image: ImageView = findViewById(R.id.imageView)

        findViewById<Button>(R.id.button).setOnClickListener {
            /**
             * Pivot determines the anchor,
             * from where the animation will scale up or start rotating
             */
            image.pivotX = 0.0f
            image.pivotY = 0.0f

            /**
             * There are many types of animation
             * 1. Scale animation
             * 2. Translate animation
             * 3. Alpha animation
             * 4. Rotational animation
             * Animation components
             * 1. Duration (in ms): animation play time
             * 2. Interpolator : Basically a path, how animation will play,
             *      e.x.: Linear, Sinusoidal, or any path
             * 3. startTime : Defines the delay time to start playing an animation
             * 4. RepeatMode: Restart[play animation from begin]
             *                Reverse[play animation start to end and then end to start]
             * 5. RepeatCount: Int value or Infinite
             * 6. PivotX, PivotY: Decide anchor pos for view
             */


            val scaleAnimator: ValueAnimator? = ValueAnimator.ofFloat(
                0.0f,
                2.0f
            ).also {
                it.duration = 2000
                it.interpolator = PathInterpolator(
                    0.12f,
                    1.05f,
                    .98f,
                    -0.82f
                )
                it.addUpdateListener { anim ->
                    image.scaleX = anim.animatedFraction * 5
                    image.scaleY = anim.animatedFraction * 5
                }
            }

            val alphaAnimator: ValueAnimator? = ValueAnimator.ofFloat(
                0.0f,
                10.0f
            ).also {
                it.duration = 4000
                it.interpolator = LinearInterpolator()
                it.addUpdateListener { anim ->
                    image.background.alpha = (anim.animatedFraction * 255).toInt()
                }
            }

            val rotationAnimator: ValueAnimator? = ValueAnimator.ofFloat(
                0.0f,
                1.0f
            ).also {
                it.duration = 2000
                it.interpolator = LinearInterpolator()
                it.addUpdateListener { anim ->
                    image.rotation = anim.animatedFraction * 3600
                }
                it.startDelay = 5000 // this rotation animation will start after 2sec
            }

            val animator: AnimatorSet = AnimatorSet()
            animator.playTogether(scaleAnimator, alphaAnimator)
            animator.start()
        }
    }
}