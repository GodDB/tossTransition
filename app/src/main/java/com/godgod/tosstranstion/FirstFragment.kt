package com.godgod.tosstranstion

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup

import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.core.animation.doOnStart
import androidx.core.view.updateLayoutParams
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.godgod.tosstranstion.databinding.FragmentFirstBinding
import com.google.android.material.transition.MaterialContainerTransform

class FirstFragment : BaseFragment<FragmentFirstBinding>(R.layout.fragment_first) {

    companion object {
        const val TAG = "firstFragment"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setupView() {
        binding.nsvContainer.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            Log.d("godgod", "${scrollY}")
            if (scrollY > (binding.partialFrom.y) - (binding.root.height) + (binding.partialFrom.height / 2)) {
                if (binding.partialFrom.visibility != View.VISIBLE) {
                    startCollapseTransition()
                }
            } else {
                if(binding.partialTo.visibility != View.VISIBLE) {
                    startExpandTransition()
                }
            }
        }
    }

    private fun startExpandTransition() {
        ValueAnimator.ofInt(20, 0).apply {
            duration = 300
            addUpdateListener {
                val value = it.animatedValue as Int
                binding.partialTo.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    marginStart = value
                    marginEnd = value
                }
            }
            doOnStart {
                binding.partialFrom.visibility = View.INVISIBLE
                binding.partialTo.visibility = View.VISIBLE
            }
        }.start()
    }

    private fun startCollapseTransition() {
        ValueAnimator.ofInt(0, 20).apply {
            duration = 300
            addUpdateListener {
                val value = it.animatedValue as Int
                binding.partialFrom.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    marginStart = value
                    marginEnd = value
                }
            }
            doOnStart {
                binding.partialTo.visibility = View.INVISIBLE
                binding.partialFrom.visibility = View.VISIBLE
            }
        }.start()
    }
}