package com.dreamgyf.android.utils.ui.image.bitmap

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur

var renderScript: RenderScript? = null

fun Bitmap.blur(context: Context, radius: Float): Bitmap {
    if (renderScript == null) {
        renderScript = RenderScript.create(context.applicationContext)
    }

    val input = Allocation.createFromBitmap(renderScript, this)
    val output = Allocation.createTyped(renderScript, input.type)
    val script = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
    script.setRadius(radius)
    script.setInput(input)
    script.forEach(output)
    output.copyTo(this)
    return this
}