package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.FixtureIncludeForCard
import com.ihsan.cricplanet.utils.MyApplication
import com.ihsan.cricplanet.utils.Utils
import com.squareup.picasso.Picasso

class LiveMatchSliderAdapter (private val context: Context, private var liveMatchList: List<FixtureIncludeForCard>) : PagerAdapter() {
    override fun getCount(): Int {
        return liveMatchList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View =  (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
            R.layout.match_item, null)
        val matchName: TextView = itemView.findViewById(R.id.fixture_name)
        val matchRound: TextView = itemView.findViewById(R.id.match_round)
        val localTeamName: TextView = itemView.findViewById(R.id.local_team_name)
        val visitorTeamName: TextView = itemView.findViewById(R.id.visitor_team_name)
        val localTeamImage: ImageView = itemView.findViewById(R.id.local_team_image)
        val visitorTeamImage: ImageView = itemView.findViewById(R.id.visitor_team_image)
        val status: TextView = itemView.findViewById(R.id.fixture_status)
        val noteOrVenue: TextView = itemView.findViewById(R.id.fixture_note_venue)
        val upcomingDate: TextView = itemView.findViewById(R.id.fixture_date)
        val upcomingTime: TextView = itemView.findViewById(R.id.fixture_time)

        val match=liveMatchList[position]

        val dateTimeList = Utils().dateFormat(match.starting_at!!)
        Log.d("teamAdapter", "BindViewHolder: ${liveMatchList.size}")
        matchName.text = "${match.league?.name} • ${match.type}"
        matchRound.text=match.round
        localTeamName.text = match.localteam!!.name
        visitorTeamName.text = match.visitorteam!!.name
        localTeamImage.setImageResource(R.drawable.ic_image)
        visitorTeamImage.setImageResource(R.drawable.ic_image)
        upcomingDate.text = dateTimeList[0]
        upcomingTime.text = dateTimeList[1]

        if (!TextUtils.isEmpty(match.localteam.image_path)) {
            Picasso.get().load(match.localteam.image_path).fit()
                .placeholder(R.drawable.progress_animation).into(localTeamImage)
        } else {
            localTeamImage.setImageResource(R.drawable.ic_image)
        }
        if (!TextUtils.isEmpty(match.visitorteam.image_path)) {
            Picasso.get().load(match.visitorteam.image_path).fit()
                .placeholder(R.drawable.progress_animation).into(visitorTeamImage)
        } else {
            localTeamImage.setImageResource(R.drawable.ic_image)
        }

        if (match.live == true) {
            Log.d("cricMatchAdapter", "onBindViewHolderLive: $match")
            status.text = "• LIVE"
            status.setBackgroundColor(
                ContextCompat.getColor(
                    MyApplication.instance, R.color.md_red_400
                )
            )
        }


        if (match.venue?.name == null || match.venue.city == null) {
            "Not Decided Yet".also { noteOrVenue.text = it }
        } else {
            if (match.venue.country?.name != null) {
                noteOrVenue.text = "${match.venue.name} • ${match.venue.city} • ${match.venue.country?.name}"
            }
            noteOrVenue.text = "${match.venue.name} • ${match.venue.city}"
        }

        val vp = container as ViewPager
        vp.addView(itemView, 0)
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}