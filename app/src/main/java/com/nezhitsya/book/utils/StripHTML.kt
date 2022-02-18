package com.nezhitsya.book.utils

import android.text.Html
import android.os.Build

class StripHTML {
    companion object {

        fun stripHtmlTag(str: String): String {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                return Html.fromHtml(str).toString()
            }
            return Html.fromHtml(str,Html.FROM_HTML_MODE_LEGACY).toString()
        }

    }
}