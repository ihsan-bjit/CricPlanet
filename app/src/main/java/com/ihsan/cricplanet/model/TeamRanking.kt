package com.ihsan.cricplanet.model

data class TeamRanking(
    val gender: String,
    val points: Any,
    val position: Any,
    val rating: Any,
    val resource: String,
    val team: List<Team>,
    val type: String,
    val updated_at: String
)