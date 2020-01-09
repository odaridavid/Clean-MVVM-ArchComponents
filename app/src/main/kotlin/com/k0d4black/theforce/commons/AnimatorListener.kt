package com.k0d4black.theforce.commons

import android.animation.Animator

class AnimatorListener(
    private val onStart: ((Animator) -> (Unit))? = null,
    private val onRepeat: ((Animator) -> (Unit))? = null,
    private val onEnd: ((Animator) -> (Unit))? = null,
    private val onCancel: ((Animator) -> (Unit))? = null
) :
    Animator.AnimatorListener {
    override fun onAnimationRepeat(animator: Animator) {
        onRepeat?.invoke(animator)
    }

    override fun onAnimationEnd(animator: Animator) {
        onEnd?.invoke(animator)
    }

    override fun onAnimationCancel(animator: Animator) {
        onCancel?.invoke(animator)
    }

    override fun onAnimationStart(animator: Animator) {
        onStart?.invoke(animator)
    }

}