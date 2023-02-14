package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeams
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeamsVenue

data class ResponseFixtureIncludeTeamsVenue(
    val `data`: List<FixtureIncludeTeamsVenue>,
    val links: Links,
    val meta: Meta
)