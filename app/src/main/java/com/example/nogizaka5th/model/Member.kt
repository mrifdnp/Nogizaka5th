package com.example.nogizaka5th.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(
    var name : String ?="",
    var city : String?="",
    var photo: Int=0,
    var birthday: String?="",
):Parcelable   {
    fun getFormattedBirthplace(): String {
    return "Birthplace: ${city ?: "-"}"
}
}