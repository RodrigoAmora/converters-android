package br.com.rodrigoamora.converters.extensions

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.graphics.drawable.Icon
import br.com.rodrigoamora.converters.ui.activity.MainActivity

@TargetApi(26)
fun createShortcutInfos(context: Context, shortLabels: Array<String>, disabledMessage: Array<String>, options: Array<String>, icons: Array<Int>) : MutableList<ShortcutInfo> {
    var shortcutInfos = mutableListOf<ShortcutInfo>()

    for (i in shortLabels.indices) {
        val intentRestaurant = Intent(context, MainActivity::class.java)
        intentRestaurant.putExtra("option", options[i])
        intentRestaurant.setAction(Intent.ACTION_VIEW)

        val shortcutInfo = ShortcutInfo.Builder(context.getApplicationContext(), "shortcut" + shortLabels[i])
                .setIntent(intentRestaurant)
                .setShortLabel(shortLabels[i])
                .setLongLabel(shortLabels[i])
                .setDisabledMessage(disabledMessage[i])
                .setIcon(Icon.createWithResource(context, icons[i]))
                .build()

        shortcutInfos.add(shortcutInfo)
    }

    return shortcutInfos
}