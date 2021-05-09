package org.yuno.apps.lurk.screens.settings.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Settings(
        val games: String,
        val streamers: String,
        val title: String,
        val tag: String,
        val languageCode: String
) : Parcelable