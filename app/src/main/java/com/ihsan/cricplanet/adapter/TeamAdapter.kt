package com.ihsan.cricplanet.adapter

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.ihsan.cricplanet.R
import com.ihsan.cricplanet.model.Team
import com.ihsan.cricplanet.viewmodel.CricViewModel
import com.squareup.picasso.Picasso

class TeamAdapter(
    private val teamList: ArrayList<Team>
) : RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {
    //private val viewModel: CricViewModel = CricViewModel(application = Application())

    class TeamViewHolder(private val binding: View) : RecyclerView.ViewHolder(binding){
        val teamName: TextView =binding.findViewById(R.id.team)
        val image: ImageView =itemView.findViewById(R.id.image)
        val description: TextView =itemView.findViewById(R.id.description)
        val source: TextView =itemView.findViewById(R.id.source)
        val btnFavourite: ImageButton =itemView.findViewById(R.id.favourite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val root= LayoutInflater.from(parent.context).inflate(R.layout.team_item,parent,false)
        Log.d("teamAdapter", "onCreateViewHolder: ${teamList.size}")
        return TeamViewHolder(root)
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val team=teamList[position]
        Log.d("teamAdapter", "BindViewHolder: ${teamList.size}")
        holder.teamName.text=team.name
        /*holder.description.text="Id: ${team.id}${team.resource} updated at:${team.updated_at}"

        holder.source.text=team.national_team.toString()

        if(!TextUtils.isEmpty(team.image_path))
        {
            Picasso.get().load(team.image_path).fit().centerCrop().placeholder(R.drawable.progress_animation).into(holder.image)
        }
        else{
            holder.image.setImageResource(R.drawable.ic_image)
        }
        holder.itemView.setOnClickListener{
            //showStyledSnackbar(it, team.name)
            //notifyDataSetChanged()
        }*/
    }

    fun showStyledSnackbar(view: View, text: String) {
        val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#3F51B5"))
        val textView = snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.setTextColor(Color.WHITE)
        textView.textSize = 20f
        textView.setTypeface(null, Typeface.BOLD)
        snackbar.show()
    }
}