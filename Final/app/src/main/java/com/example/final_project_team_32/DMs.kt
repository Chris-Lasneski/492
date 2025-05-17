package com.example.final_project_team_32

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DMs : AppCompatActivity() {
    private lateinit var contactList: RecyclerView
    private lateinit var contactAdapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dms)

        contactAdapter = ContactAdapter(::onContactClick)

        contactList = findViewById(R.id.rv_dm_list)
        contactList.layoutManager = LinearLayoutManager(this)
        contactList.setHasFixedSize(true)
        contactList.adapter = contactAdapter
        title = "DM's"

        var contact1 = ContactItem("Greebo the Beeper",
            "Bio: What's the deal with Airline Food",
            "https://i.pinimg.com/170x/e0/7d/03/e07d03a8261a6648112ddabac92e26ef.jpg",
            44.060644,
            -123.1925899,
            "It Just Works")
        var contact2 = ContactItem("David Duchovny",
            "Bio: I heard Bree Sharp is pretty cool",
            "https://m.media-amazon.com/images/M/MV5BNzBhMjAyZGYtZWQ1Yy00MTQxLWEwNjgtZTczYzJjMjIxZWE2XkEyXkFqcGdeQXVyNjk1MjYyNTA@._V1_FMjpg_UX1000_.jpg",
            37.7576948,
            -122.4726194,
            "Never Gonna Give You Up")
        var contact3 = ContactItem("Danny DeVito",
            "Bio: So anyway, I started blasting",
            "https://upload.wikimedia.org/wikipedia/commons/2/21/Danny_DeVito_by_Gage_Skidmore.jpg",
            40.0451675,
            -75.3566353,
            "Diggy Diggy Hole")
        var contact4 = ContactItem("Dennis Reynolds",
            "Bio: The implication is great",
            "https://media.gq.com/photos/5aaaaca1da6c277bbb5da0d6/master/w_2250,h_3000,c_limit/Glenn-Howerton6447.jpg",
            40.6971494,
            -74.2598655,
            "Count To Three")
        var contact5 = ContactItem("Bruce Wayne",
            "Bio: I am NOT Batman",
            "https://static.dc.com/dc/files/default_images/Char_Profile_Batman_20190116_5c3fc4b40faec2.47318964.jpg",
            41.8333925,
            -88.0121478,
            "Fortunate Son")
        contactAdapter.updateContacts(contact1)
        contactAdapter.updateContacts(contact2)
        contactAdapter.updateContacts(contact3)
        contactAdapter.updateContacts(contact4)
        contactAdapter.updateContacts(contact5)

        if (intent != null && intent.hasExtra(EXTRA_THING)) {
            contact2 = intent.getSerializableExtra(EXTRA_THING) as ContactItem
        }
    }

    override fun onResume() {
        super.onResume()
        var prefs = PreferenceManager.getDefaultSharedPreferences(this)
        var lat: Double = prefs.getString(getString(R.string.your_latitude), null)!!.toDouble()
        var lon: Double = prefs.getString(getString(R.string.your_longitude), null)!!.toDouble()
        contactAdapter.updateSettingsCoords(lat, lon)
    }



        private fun onContactClick(contact: ContactItem) {
        val intent = Intent(this, Chat_Box::class.java).apply {
            putExtra(EXTRA_CONTACT, contact.name)
            putExtra(EXTRA_MESSAGE, contact.lastMessage)
            putExtra(EXTRA_THING, contact)
        }

        startActivity(intent)
    }
}