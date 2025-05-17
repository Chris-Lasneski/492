package com.example.final_project_team_32

import android.graphics.Color
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class ChatAdapter(private val onClick: (ChatItem) -> Unit)
    : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {
    var chatList: List<ChatItem> = listOf()
    var username: String = ""


    fun addChatItem(chatItem: ChatItem) {
        chatList = chatList + chatItem
        notifyDataSetChanged()
    }

    fun addReaction(chatItem: ChatItem) {
        if (chatItem.reaction == "") {
            chatItem.reaction = "\t\t❤️"
        } else {
            chatItem.reaction = ""
        }
        notifyDataSetChanged()
    }

    fun updateName(name: String) {
        username = name
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.chatList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_list_item, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.chatList[position])
    }


    class ViewHolder(itemView: View, val onClick: (ChatItem) -> Unit)
        : RecyclerView.ViewHolder(itemView) {
        private lateinit var currentChat: ChatItem
        private val message: TextView = itemView.findViewById(R.id.tv_message)
        private val name: TextView = itemView.findViewById(R.id.tv_name)
        private val reaction: TextView = itemView.findViewById(R.id.tv_reaction)
        private val chat_card: MaterialCardView = itemView.findViewById(R.id.chat_card)

        init {
            itemView.setOnClickListener {
                currentChat?.let(onClick)
            }
        }

        fun bind(chatItem: ChatItem) {
            currentChat = chatItem
            message.text = chatItem.message
            name.text = chatItem.owner
            reaction.text = chatItem.reaction

            if (name.text == "You") {
                chat_card.setCardBackgroundColor(0x1AA7EC)
                chat_card.setCardBackgroundColor(Color.parseColor("#8A98C6"))
            } else if (name.text == "SYSTEM") {
                chat_card.setCardBackgroundColor(0xFFFFFF)
                chat_card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))
            } else {
                chat_card.setCardBackgroundColor(0xE63B60)
                chat_card.setCardBackgroundColor(Color.parseColor("#92C68A"))
            }
        }
    }
}
