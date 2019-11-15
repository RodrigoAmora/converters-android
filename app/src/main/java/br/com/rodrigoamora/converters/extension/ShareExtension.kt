package br.com.rodrigoamora.converters.extension

import android.content.Context
import android.content.Intent

fun share(context: Context, title: String, text: String) {
    val sharingIntent = Intent(Intent.ACTION_SEND)
    sharingIntent.type = "text/plain"
    sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
    context.startActivity(Intent.createChooser(sharingIntent, title))
}