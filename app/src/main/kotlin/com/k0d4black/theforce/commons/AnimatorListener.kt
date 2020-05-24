package com.k0d4black.theforce.commons

import android.animation.Animator

class AnimatorListener(
    private val onEnd: ((Animator) -> (Unit))? = null
) : Animator.AnimatorListener {

    override fun onAnimationEnd(animator: Animator) {
        onEnd?.invoke(animator)
    }

    override fun onAnimationRepeat(animator: Animator) {
        //no-op
    }

    override fun onAnimationCancel(animator: Animator) {
        //no-op
    }

    override fun onAnimationStart(animator: Animator) {
        //no-op
    }

}