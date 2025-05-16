package com.example.final_project_team_32

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageButton
import androidx.core.view.size
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.media.MediaPlayer
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.content.res.AppCompatResources


const val EXTRA_CONTACT = "com.example.final_project_team_32.CONTACTITEM"
const val EXTRA_MESSAGE = "com.example.final_project_team_32.CONTACTITEM"
const val EXTRA_THING = "com.example.final_project_team_32.CONTACTITEM"

class Chat_Box : AppCompatActivity() {
    private lateinit var sendMessage: ImageButton
    private lateinit var enterMessage: EditText

    private lateinit var chatHistory: RecyclerView
    private lateinit var chatAdapter: ChatAdapter
    private var person: String? = null
    private var firstMessage: String? = null
    private var contact: ContactItem? = null
    private var songName: String? = null
    private var songMP: String? = null
    private var isPlaying = false
    private var mediaPlayer: MediaPlayer? = null
    private var musicIcon: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_box)

        sendMessage = findViewById(R.id.bt_send_message)
        enterMessage = findViewById(R.id.et_enter_message)

        chatHistory = findViewById<RecyclerView>(R.id.rv_chat_history)

        chatAdapter = ChatAdapter(::onChatClick)

        chatHistory.layoutManager = LinearLayoutManager(this)
        //chatHistory.setHasFixedSize(true)
        chatHistory.adapter = chatAdapter



        if (intent != null && intent.hasExtra(EXTRA_THING)) {
            contact = intent.getSerializableExtra(EXTRA_THING) as ContactItem
        }

//        if (intent != null && intent.hasExtra(EXTRA_CONTACT)) {
//            person = intent.getSerializableExtra(EXTRA_CONTACT) as String
//            title = person
//        }
//
//        if (intent != null && intent.hasExtra(EXTRA_MESSAGE)) {
//            firstMessage = intent.getSerializableExtra(EXTRA_MESSAGE) as String
//        }
        person = contact?.name
        firstMessage = contact?.lastMessage
        songName = contact?.song
        // can be ignored for now until figure out how to use in R search path
        //songMP = contact?.song + ".mp3"

        // couldn't figure out how to use varibles for R.raw. path so doing this for now
        if(person == "Greebo the Beeper") {
            mediaPlayer = MediaPlayer.create(this, R.raw.it_just_works)
        }
        else if(person == "David Duchovny") {
            mediaPlayer = MediaPlayer.create(this, R.raw.never_gonna_give_you_up)
        }
        else if(person == "Danny DeVito") {
            mediaPlayer = MediaPlayer.create(this, R.raw.diggy_diggy_hole)
        }
        else if(person == "Dennis Reynold") {
            mediaPlayer = MediaPlayer.create(this, R.raw.count_to_three)
        }
        else if(person == "Bruce Wayne") {
            mediaPlayer = MediaPlayer.create(this, R.raw.fortunate_son)
        }
        title = person


        var chatbot = Chatbot(chatAdapter, contact!!)
        if (person == "David Duchovny" || person == "Dennis Reynolds") {
            chatbot.alt_chat()
        } else {
            chatbot.chat()
        }
        chatAdapter.addChatItem(ChatItem(firstMessage!!, person!!, ""))

        sendMessage.setOnClickListener {
            val chatitem = ChatItem(enterMessage.text.toString(), "You", "")
            chatAdapter.addChatItem(chatitem)
            chatbot = Chatbot(chatAdapter, contact!!)
            if (person == "David Duchovny" || person == "Dennis Reynolds") {
                chatbot.alt_chat()
            } else {
                chatbot.chat()
            }
            chatHistory.scrollToPosition(chatAdapter.itemCount - 1)
            enterMessage.getText().clear()
        }


        chatHistory.scrollToPosition(chatAdapter.itemCount - 1)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        this.musicIcon = menu.findItem(R.id.sound_button)

        menuInflater.inflate(R.menu.activity_chat_box, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(!mediaPlayer!!.isPlaying) {
            title = songName
            musicIcon?.isChecked = true
            Log.d("", musicIcon.toString())
            musicIcon?.icon = AppCompatResources.getDrawable(this, android.R.drawable.ic_media_pause)
            Log.d("", musicIcon.toString())
            playSound()
        }
        else if(mediaPlayer!!.isPlaying) {

            title = person
            musicIcon?.isChecked = false
            Log.d("", musicIcon?.icon.toString())
            musicIcon?.icon = AppCompatResources.getDrawable(this, android.R.drawable.ic_media_play)
            Log.d("", musicIcon?.icon.toString())
            pauseSound()
        }

//        if(mediaPlayer!!.isPlaying) {
//            title = songName
//        }
//        else {
//            title = person
//        }

//        if(mediaPlayer!!.isPlaying) {
//            musicIcon?.isChecked = true
//            musicIcon?.icon = AppCompatResources.getDrawable(this, android.R.drawable.ic_media_pause)
//        }
//        else {
//            musicIcon?.isChecked = false
//            musicIcon?.icon = AppCompatResources.getDrawable(this, android.R.drawable.ic_media_play)
//        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        var prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val name: String? = prefs.getString(getString(R.string.your_name), "")

        chatAdapter.updateName(name!!)
    }

    private fun onChatClick(chatItem: ChatItem) {
        if (chatItem.reaction == "") {
            chatItem.reaction = "❤️"
        } else {
            chatItem.reaction = ""
        }

        if (chatItem.message.contains("http", false)) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(chatItem.message));
            startActivity(browserIntent);
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, DMs::class.java).apply {
            putExtra(EXTRA_THING, contact)
        }

        //should stop sound on back press
        if(mediaPlayer != null) {
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null
        }

        startActivity(intent)
        super.onBackPressed();
    }

    //play music
    fun playSound() {
        if(mediaPlayer?.isPlaying == false) {
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start()
        }
    }

    // pause music
    fun pauseSound() {
        if(mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
        }
    }

    // Destroys mediaPlayer instance on app closure
    override fun onStop() {
        super.onStop()
        if(mediaPlayer != null) {
            mediaPlayer!!.release()
            mediaPlayer = null
        }
    }
}