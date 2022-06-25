package com.cesor.android.ttbtcastsp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cesor.android.ttbtcastsp.databinding.ItemActorBinding
/****
 * Project: Users SP
 * From: com.cesor.android.userssp
 * Created by: César Castro on 22/06/2022 at 14:35.
 ***/
class ActorAdapter(private val actors: List<Actor>, private val listener: OnClickListener) :
    RecyclerView.Adapter<ActorAdapter.ViewHolder>() {
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val actor = actors[position]
        with(holder){
            val actorPosition = position + 1
            setListener(actor, actorPosition)
            binding.tvOrder.text = actorPosition.toString()
            binding.tvActorName.text = actor.getFullName()
            binding.tvActorRole.text = actor.getActorRole()
            Glide.with(context)
                .load(actor.imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }

    override fun getItemCount(): Int = actors.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val binding = ItemActorBinding.bind(view)
        fun setListener(actor: Actor, position: Int){
            binding.root.setOnClickListener{listener.onClick(actor, position)}
        }
    }
}