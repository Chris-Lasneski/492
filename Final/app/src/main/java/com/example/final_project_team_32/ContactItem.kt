package com.example.final_project_team_32

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactItem(val name: String,
                  val lastMessage: String,
                  val pfp: String,
                  val lat: Double,
                  val lon: Double,
                  val song: String) : java.io.Serializable