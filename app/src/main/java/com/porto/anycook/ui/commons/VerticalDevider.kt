package com.porto.anycook.ui.commons

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class VerticalDivider(
    context: Context, spaceDp: Int
) : RecyclerView.ItemDecoration() {

    private var space: Int

    init {
        val metrics = context.resources.displayMetrics
        space = (spaceDp * metrics.density).roundToInt()
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = space

        if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
            outRect.bottom = space
    }
}
