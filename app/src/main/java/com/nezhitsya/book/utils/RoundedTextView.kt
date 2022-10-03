package com.nezhitsya.book.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatTextView

class RoundedTextView : AppCompatTextView {

    private lateinit var mContext: Context
    private lateinit var path: Path

    constructor(@NonNull context: Context) : this(context, null) { init(context) }
    constructor(@NonNull context: Context, @Nullable attrs: AttributeSet?) : this(context, attrs, 0) { init(context) }
    constructor(
        @NonNull context: Context,
        @Nullable attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {
        init(context)
    }

    private fun init(context: Context?) {
        this.mContext = context!!
    }

    override fun draw(canvas: Canvas) {
        path = Path()
        path.addRoundRect(0F, 0F, width.toFloat(), height.toFloat(), 30F, 30F, Path.Direction.CW)
        canvas.clipPath(path)
        super.draw(canvas)
    }
}