package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeams

data class ResponseFixtureIncludeTeams(
    val `data`: List<FixtureIncludeTeams>,
    val links: Links,
    val meta: Meta
)