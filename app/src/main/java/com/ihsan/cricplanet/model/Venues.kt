package com.ihsan.cricplanet.model

data class Venues(
    val capacity: Int,
    val city: String,
    val country_id: Int,
    val floodlight: Boolean,
    val id: Int,
    val image_path: String,
    val name: String,
    val resource: String,
    val updated_at: String
)