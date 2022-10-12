package com.dreamgyf.android.utils.ui.image.drawable

import android.graphics.*
import android.graphics.drawable.Drawable

class RoundRectDrawable(private val bitmap: Bitmap, private var radius: Float = 0f) : Drawable() {

    private val rectF = RectF(0f, 0f, bitmap.width.toFloat(), bitmap.height.toFloat())

    private val path = Path().apply {
        addRoundRect(rectF, radius, radius, Path.Direction.CW)
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun draw(canvas: Canvas) {
        canvas.clipPath(path)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getOutline(outline: Outline) {
        outline.setRoundRect(bounds, radius)
    }

    fun setRadius(radius: Float) {
        this.radius = radius
        path.reset()
        path.addRoundRect(rectF, this.radius, this.radius, Path.Direction.CW)
    }
}