package com.ihsan.cricplanet.utils

class Constant {
    companion object {
        // Permission Request Code
        const val internetPermissionAccessCode = 100
        const val smsRequestPermissionAccessCode = 1000

        //const val API_KEY = "81gSXinE4LtZQ7LiZhwEjYbIvpDElRRlUw4dUEEgxlRgHLVMrIB963vfbYms"
        const val API_KEY = "KFAaNiXJsDvbDRtIo40z40GS9OAiBPGhiDdogpjFvERYgCoFCokcVAqE446G"
        const val api_token = "api_token"
        const val countries = "countries"
        const val continents = "continents"
        const val include = "include"
        const val sort = "sort"
        const val live = "live"
        const val fixtures = "fixtures"

        //Fixture Filter
        const val filterByStatus = "filter[status]"
        const val filterBySeason = "filter[season_id]"
        const val filterByLeague = "filter[league_id]"
        const val filterByLocalTeam = "filter[localteam_id]"
        const val filterByVisitorTeam = "filter[visitorteam_id]"
        const val filterByReferee = "filter[referee_id]"
        const val filterByRound = "filter[round]"
        const val filterByStage = "filter[stage_id]"
        const val filterByType = "filter[type]"
        const val filterByVenue = "filter[venue_id]"
        const val filterDate = "filter[starts_between]"

        //Fixture Include
        /*"message":
        {
            localteam
    visitorteam
    scoreboards
    scoreboards.team
    runs
    runs.team
    balls//time 20s call single player .5s
    balls.team
    balls.batsman, balls.bowler, balls.batsmanout, balls.catchstump,
    balls.score, balls.batsmanone, balls.batsmantwo

    stage
    batting
    batting.result

    balls.runoutby, batting.team, batting.batsman, batting.bowler,
    batting.catchstump, batting.batsmanout, batting.runoutby, bowling

    tosswon
    localteam.fixtures.localteam

    bowling.team, season, league, referee, firstumpire, secondumpire,
    tvumpire, manofmatch, manofseries

    winnerteam, lineup, venue, venue.country, odds, odds.bookmaker, odds.market

    bowling.bowler, localteam.fixtures, localteam.fixtures.visitorteam,
    visitorteam.fixtures, visitorteam.fixtures.localteam, visitorteam.fixtures.visitorteam,
    localteam.country, visitorteam.country
        }*/

        const val teams = "teams"
        const val league = "leagues"
        const val teamWithSquad = "teams/:TEAM_ID/squad/:SEASON_ID?include=career"
    }

}