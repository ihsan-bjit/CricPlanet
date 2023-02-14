package com.ihsan.cricplanet.model.responseapi

import com.ihsan.cricplanet.model.fixture.Fixture

data class ResponseFixture(
    val `data`: List<Fixture>,
    val links: Links,
    val meta: Meta
)