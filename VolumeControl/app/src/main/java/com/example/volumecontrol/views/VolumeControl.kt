package com.example.volumecontrol.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.example.volumecontrol.R

class VolumeControl(context: Context?, attributeSet: AttributeSet?): View(context, attributeSet) {

    val paint = Paint(ANTI_ALIAS_FLAG)
    val paint2 = Paint(ANTI_ALIAS_FLAG)
    var canvasRotation = rotation
    var volumeLevel = 0
        private set

    private var xStart: Float = 0f
    private var xDiff: Float = 0f

    init {
        val typedArray = context?.obtainStyledAttributes(attributeSet, R.styleable.VolumeControl)
        val layoutHeight = typedArray?.getInt(R.styleable.VolumeControl_layout_height, LinearLayout.LayoutParams.WRAP_CONTENT)
        val layoutWidth = typedArray?.getInt(R.styleable.VolumeControl_layout_width, LinearLayout.LayoutParams.WRAP_CONTENT)
        if (layoutHeight != null && layoutWidth != null){
            this.layoutParams = ViewGroup.LayoutParams(layoutWidth, layoutHeight)
        }
        typedArray?.recycle()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                xStart = event.x
            }
            MotionEvent.ACTION_MOVE -> {
                xDiff = event.x - xStart
                    rotation = canvasRotation + xDiff/10
            }
            MotionEvent.ACTION_UP -> {
                if (rotation <= -45f){
                    volumeLevel = (rotation/360*100 + 25).toInt()
                }
                if (rotation in -45f..-1f){
                    volumeLevel = (rotation/360*100 + 50).toInt()
                }
                if (rotation in 0f..45f){
                    volumeLevel = (rotation/360*100 + 50).toInt()
                }
                if (rotation in 46f..90f){
                    volumeLevel = (rotation/360*100 + 75).toInt()
                }

                var toast = Toast.makeText(context, "Volume $volumeLevel%", Toast.LENGTH_SHORT)
                toast.show()
                canvasRotation = rotation
            }
        }
        return true
    }


    override fun onDraw(canvas: Canvas?) {

        paint.setColor(Color.CYAN)
        paint2.setColor(Color.BLUE)

        canvas?.drawCircle(width/2f, height/2f, 350f, paint)
        canvas?.drawCircle(width/2f, height/3f, 25f, paint2)

        super.onDraw(canvas)
    }
}