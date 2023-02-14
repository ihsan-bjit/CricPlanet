package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.fixture.FixtureIncludeTeamsVenue
import com.ihsan.cricplanet.utils.Utils
import com.squareup.picasso.Picasso

class MatchAdapter(private val matchList: List<FixtureIncludeTeamsVenue>) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    //private val viewModel: CricViewModel = CricViewModel(application = Application())
    class MatchViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding) {
        val localTeam: TextView = binding.findViewById(R.id.local_team_name)
        val visitorTeam: TextView = binding.findViewById(R.id.visitor_team_name)
        val localTeamImage: ImageView = itemView.findViewById(R.id.local_team_image)
        val visitorTeamImage: ImageView = itemView.findViewById(R.id.visitor_team_image)
        val matchName: TextView = itemView.findViewById(R.id.fixture_name)
        val status: TextView = itemView.findViewById(R.id.fixture_status)
        val noteOrVenue: TextView = itemView.findViewById(R.id.fixture_note_venue)
        val upcomingDate:TextView = itemView.findViewById(R.id.fixture_date)
        val upcomingTime:TextView=itemView.findViewById(R.id.fixture_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        Log.d("teamAdapter", "onCreateViewHolder: ${matchList.size}")
        return MatchViewHolder(root)
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged", "ResourceAsColor")
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matchList[position]
        Log.d("teamAdapter", "BindViewHolder: ${matchList.size}")
        holder.localTeam.text = match.localteam!!.name
        holder.visitorTeam.text = match.visitorteam!!.name
        holder.matchName.text = match.type
        holder.localTeamImage.setImageResource(R.drawable.ic_image)
        holder.visitorTeamImage.setImageResource(R.drawable.ic_image)

        if(match.live==true){
            holder.status.text = "LIVE ON GOING"
        }
        if (match.status == "NS") {
            if(match.live==true){
                holder.status.text = "LIVE ON GOING"
                holder.upcomingDate.text= ""
                holder.upcomingTime.text=""
            }else{
                holder.status.text = "UPCOMING"
                holder.status.setBackgroundColor(R.drawable.gradient_status_background)
                val dateTimeList=Utils().dateFormat(match.starting_at!!)
                holder.upcomingDate.text=dateTimeList[0]
                holder.upcomingTime.text=dateTimeList[1]
            }
            holder.status.setBackgroundColor(R.drawable.gradient_status_background)
            if (match.venue?.name==null || match.venue.city ==null)
            {
                holder.noteOrVenue.text="Not Decided Yet"
            }
            else{
                holder.noteOrVenue.text = "${match.venue.name} • ${match.venue.city}"
            }

        } else {
            holder.status.setBackgroundColor(R.color.colorPrimary)
            holder.status.text = match.status
            holder.noteOrVenue.text = match.note
            holder.upcomingDate.text=""
        }

        if (!TextUtils.isEmpty(match.localteam?.image_path)) {
            Picasso.get().load(match.localteam?.image_path).fit()
                .placeholder(R.drawable.progress_animation).into(holder.localTeamImage)
        } else {
            holder.localTeamImage.setImageResource(R.drawable.ic_image)
        }
        if (!TextUtils.isEmpty(match.visitorteam?.image_path)) {
            Picasso.get().load(match.visitorteam?.image_path).fit()
                .placeholder(R.drawable.progress_animation).into(holder.visitorTeamImage)
        } else {
            holder.localTeamImage.setImageResource(R.drawable.ic_image)
        }
    }

    private fun showStyledSnackbar(view: View, text: String) {
        val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#3F51B5"))
        val textView =
            snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        snackbar.show()
    }
}