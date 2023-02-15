package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.fixture.FixtureIncludeForDetails

data class ResponseFixtureIncludeTeams(
    val `data`: List<FixtureIncludeForDetails>,
    val links: Links,
    val meta: Meta
)