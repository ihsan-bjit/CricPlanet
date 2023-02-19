package com.ihsan.cricplanet.model.fixture

import com.ihsan.cricplanet.model.League
import com.ihsan.cricplanet.model.Season
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.model.Venue
import com.ihsan.cricplanet.model.fixture.referee.RefereeX
import com.ihsan.cricplanet.model.fixture.referee.Umpire
import com.ihsan.cricplanet.model.fixture.result.manofseries.ManOfSeries
import com.ihsan.cricplanet.model.fixture.result.manofthematch.ManOfMatch
import com.ihsan.cricplanet.model.fixture.result.teamwontoss.TossWon
import com.ihsan.cricplanet.model.fixture.result.winnerteam.WinnerTeamX
import com.ihsan.cricplanet.model.fixture.scoreboard.Scoreboard
import com.ihsan.cricplanet.model.fixture.scoreboard.lineup.Lineup
import com.ihsan.cricplanet.model.fixture.scoreboard.run.Run

data class FixtureByIdWithDetails(
    val draw_noresult: Any?,
    val elected: String?,
    val first_umpire_id: Int?,
    val firstumpire: Umpire?,
    val follow_on: Boolean?,
    val id: Int,
    val last_period: Any?,
    val league: League?,
    val league_id: Int?,
    val lineup: List<Lineup>?,
    val live: Boolean?,
    val localteam: Team?,
    val localteam_dl_data: TeamDlData?,
    val localteam_id: Int?,
    val man_of_match_id: Int?,
    val man_of_series_id: Any?,
    val manofmatch: ManOfMatch?,
    val manofseries: ManOfSeries?,
    val note: String?,
    val referee: RefereeX?,
    val referee_id: Int?,
    val resource: String?,
    val round: String?,
    val rpc_overs: Any?,
    val rpc_target: Any?,
    val runs: List<Run>?,
    val scoreboards: List<Scoreboard>?,
    val season: Season?,
    val season_id: Int?,
    val second_umpire_id: Int?,
    val secondumpire: Umpire?,
    val stage_id: Int?,
    val starting_at: String?,
    val status: String?,
    val super_over: Boolean?,
    val toss_won_team_id: Int?,
    val tosswon: TossWon?,
    val total_overs_played: Int?,
    val tv_umpire_id: Int?,
    val tvumpire: Umpire?,
    val type: String?,
    val venue: Venue?,
    val venue_id: Int?,
    val visitorteam: Team?,
    val visitorteam_dl_data: TeamDlData?,
    val visitorteam_id: Int?,
    val weather_report: List<Any>?,
    val winner_team_id: Int?,
    val winnerteam: WinnerTeamX?
) {
    constructor() : this(
        null,
        null,
        null,
        null,
        null,
        0,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    )
}