package com.example.final_project_team_32

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.lang.Math.abs
import java.math.RoundingMode
import kotlin.math.pow
import kotlin.math.sqrt

class ContactAdapter(private val onClick: (ContactItem) -> Unit)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    var contactList: List<ContactItem> = listOf()
    var lat: Double = 0.0
    var lon: Double = 0.0
    var alt: Boolean = false

    fun updateContacts(contact: ContactItem) {
        contactList = contactList + contact
        notifyDataSetChanged()
    }

    fun updateAlt(bool: Boolean) {
        alt = bool
        notifyDataSetChanged()
    }

    fun updateSettingsCoords(your_lat: Double, your_lon: Double) {
        lat = your_lat
        lon = your_lon
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun getItemCount() = this.contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.contactList[position], lat, lon)
    }

    class ViewHolder(itemView: View, val onClick: (ContactItem) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private val pfp: ImageView = itemView.findViewById(R.id.iv_pfp)
        private val name: TextView = itemView.findViewById(R.id.tv_contact_name)
        private val message: TextView = itemView.findViewById(R.id.tv_contact_message)
        private val distance: TextView = itemView.findViewById(R.id.tv_contact_distance)

        private lateinit var currentContact: ContactItem

        init {
            itemView.setOnClickListener {
                currentContact?.let(onClick)
            }
        }

        fun bind(contact: ContactItem, your_lat: Double, your_lon: Double) {
            currentContact = contact
            val ctx = itemView.context
            val builderN = StringBuilder()
            if (contact.name.length < 30) {
                name.text = contact.name
            }
            else {
                message.text = builderN.append(contact.lastMessage.take(27)).append("...").toString()
                name.text = builderN.append(contact.lastMessage.take(27)).append("...").toString()
            }
            val builder = StringBuilder()
            if (contact.lastMessage.length < 30) {
                message.text = contact.lastMessage
            }
              else {
                message.text = builder.append(contact.lastMessage.take(27)).append("...").toString()
              }

            Glide.with(ctx)
                .load(contact.pfp)
                .override(200,200)
                .fitCenter()
                .into(pfp)

            var new_lat = (your_lat - contact.lat)
            new_lat.pow(2)
            var new_lon = (your_lon - contact.lat)
            new_lon.pow(2)
            val dist = sqrt(abs(new_lat+new_lon)).toBigDecimal().setScale(2, RoundingMode.FLOOR)
            distance.text = "(" + dist.toString() + " degrees away)"
        }
    }
}
