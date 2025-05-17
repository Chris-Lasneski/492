package com.example.final_project_team_32

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChatItem(
    var message: String,
    var owner: String,
    var reaction: String) : java.io.Serializable