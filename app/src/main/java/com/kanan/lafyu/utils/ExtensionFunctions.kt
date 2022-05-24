package com.kanan.lafyu.utils

import android.content.Context


fun Float.dpToPx(context: Context) = this * context.resources.displayMetrics.density