package com.example.volumecontrol.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class VolumeControl(context: Context?, attributeSet: AttributeSet?): View(context, attributeSet) {

    val paint = Paint()
    val paint2 = Paint()
    var canvasRotation = rotation
    var toast = Toast(context)
    var volumeLevel = 0

    private var xStart: Float = 0f
    private var yStart: Float = 0f
    private var xDiff: Float = 0f
    private var yDiff: Float = 0f

    init {

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
                volumeLevel = (rotation/360*100).toInt()
                toast = Toast.makeText(context, "%$volumeLevel", Toast.LENGTH_SHORT)
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