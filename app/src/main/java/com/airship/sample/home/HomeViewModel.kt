/* Copyright Airship and Contributors */
package com.airship.sample.home

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.urbanairship.UAirship
import com.urbanairship.messagecenter.MessageCenter

/**
 * View model for the HomeScreen.
 */
internal open class HomeViewModel() : ViewModel() {

    open var channelId = UAirship.shared().channel.channelIdFlow
    open var unreadMessageCount = MessageCenter.shared().inbox.getUnreadMessagesFlow()

    internal open fun copyToClipboard(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        clipboard.setPrimaryClip(ClipData.newPlainText("Channel ID", channelId.value))

        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}
