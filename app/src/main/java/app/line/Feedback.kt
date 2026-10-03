package app.line

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator

object Feedback {
    private val tone by lazy { ToneGenerator(AudioManager.STREAM_SYSTEM, 35) }

    fun messageSent(context: Context) = play(context, "message_sound", ToneGenerator.TONE_PROP_ACK)

    fun interfaceClick(context: Context) = play(context, "interface_sound", ToneGenerator.TONE_PROP_BEEP2)

    private fun play(context: Context, preference: String, toneType: Int) {
        if (!context.getSharedPreferences("line-ui", Context.MODE_PRIVATE).getBoolean(preference, true)) return
        runCatching { tone.startTone(toneType, 90) }
    }
}
