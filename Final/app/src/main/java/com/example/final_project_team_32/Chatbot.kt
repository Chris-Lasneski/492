package com.example.final_project_team_32


class Chatbot(chatAdapter: ChatAdapter, contactItem: ContactItem) {
    private var chatDapter: ChatAdapter = chatAdapter
    private var history: List<ChatItem> = chatAdapter.chatList
    private lateinit var lastMessage: ChatItem
    private var name: String = contactItem.name
    private var contct: ContactItem = contactItem
    private var songName: String = contactItem.song

    fun addAnswer(botAnswer: String) {
        history = history + ChatItem(botAnswer, name, "")
        chatDapter.addChatItem(ChatItem(botAnswer, name, ""))
    }

    fun addReaction(botAnswer: String) {
        if (lastMessage.reaction == "" && lastMessage.owner != name) {
            lastMessage.reaction = "\t\t❤️"
        } else {
            lastMessage.reaction = ""
        }
        val response = ChatItem(botAnswer, name, "")
        history = history + ChatItem(botAnswer, name, lastMessage.reaction)
        chatDapter.addChatItem(response)
    }

    fun chat() {
        if (history.isEmpty()) {
            history = history + ChatItem("MESSAGES WILL VANISH AFTER LEAVING", "SYSTEM", "")
            chatDapter.addChatItem(ChatItem("MESSAGES WILL VANISH AFTER LEAVING", "SYSTEM", ""))
            return
        }
        lastMessage = history.last()
        if (lastMessage.message == "?" && lastMessage.owner == "You") {
            addAnswer("I literally have no idea what you're talking about")
            return
        }
        if (lastMessage.message == "!" && lastMessage.owner == "You") {
            addAnswer("WOAH!!! Thats so poggers dude!")
            return
        }
        if (lastMessage.message.contains("where", true) && lastMessage.message.contains("you", true) && lastMessage.owner == "You") {
            addAnswer("https://www.google.com/maps/place/@" + contct.lat + "," + contct.lon + ",12z")
            return
        }
        if (lastMessage.message.contains("song", true) && lastMessage.message.contains("you", true) && lastMessage.owner == "You") {
            addAnswer("https://www.youtube.com/watch?v=djV11Xbc914")
            return
        }
        if (lastMessage.message.contains("crazy", true) && lastMessage.owner == "You") {
            addAnswer("WHO ARE YOU CALLING CRAZY?!!!")
            return
        }
        if (lastMessage.message.contains("hey", true) && lastMessage.owner == "You") {
            addAnswer("Hey")
            return
        }
        if (lastMessage.message.contains("yep", true) && lastMessage.owner == "You") {
            addAnswer("Awesome")
            return
        }
        if (lastMessage.message.contains("yup", true) && lastMessage.owner == "You") {
            addAnswer("Awesome")
            return
        }
        if (lastMessage.message.contains("My", true) && lastMessage.message.contains("name", true) && lastMessage.owner == "You") {
            addAnswer("You are " + chatDapter.username + " right?")
            return
        }
        if (lastMessage.message.contains("Your", true) && lastMessage.message.contains("name", true) && lastMessage.owner == "You") {
            addAnswer("My name is " + name)
            return
        }
        if (lastMessage.message.contains("You", true) && lastMessage.message.contains("Who", true) && lastMessage.owner == "You") {
            addAnswer("My name is " + name)
            return
        }
        if (lastMessage.message.contains("I", true) && lastMessage.message.contains("Who", true) && lastMessage.owner == "You") {
            addAnswer("You are " + chatDapter.username + " right?")
            return
        }
        if (lastMessage.message.contains("time", true) && lastMessage.owner == "You") {
            addAnswer("How does 4:42 AM sound?")
            return
        }
        if (lastMessage.message.contains("sounds", true) && lastMessage.owner == "You") {
            addReaction("Perfect, see ya then ;)")
            return
        }
        if (lastMessage.message.contains("okay", true) && lastMessage.owner == "You") {
            addReaction("Okay")
            return
        }
        if (lastMessage.message.contains("oh", true) && lastMessage.owner == "You") {
            addReaction("Oh...")
            return
        }
        if ((lastMessage.message.contains("AM", true) || lastMessage.message.contains("PM", true))
            && lastMessage.message.contains(":", true) && lastMessage.owner == "You") {
            addAnswer("Yeah, that time works better for me too!")
            return
        }
    }
    fun alt_chat() {
        if (history.isEmpty()) {
            history = history + ChatItem("MESSAGES WILL VANISH AFTER LEAVING", "SYSTEM", "")
            chatDapter.addChatItem(ChatItem("MESSAGES WILL VANISH AFTER LEAVING", "SYSTEM", ""))
            return
        }
        lastMessage = history.last()
        if (lastMessage.message == "?" && lastMessage.owner == "You") {
            addAnswer("¯\\_(ツ)_/¯")
            return
        }
        if (lastMessage.message == "!" && lastMessage.owner == "You") {
            addAnswer("~!~")
            return
        }
        if (lastMessage.message.contains("where", true) && lastMessage.message.contains("you", true) && lastMessage.owner == "You") {
            addAnswer("https://www.google.com/maps/place/@" + contct.lat + "," + contct.lon + ",12z")
            return
        }
        if (lastMessage.message.contains("song", true) && lastMessage.message.contains("you", true) && lastMessage.owner == "You") {
            addAnswer("https://www.youtube.com/watch?v=upRhsDUIDSE")
            return
        }
        if (lastMessage.message.contains("wild", true) && lastMessage.owner == "You") {
            addAnswer("Can't wait to hit the slopes")
            return
        }
        if (lastMessage.message.contains("yeppers", true) && lastMessage.owner == "You") {
            addAnswer("Awesome ( ͡° ͜ʖ ͡°)")
            return
        }
        if (lastMessage.message.contains("awesome", true) && lastMessage.owner == "You") {
            addAnswer("Awesome.")
            return
        }
        if (lastMessage.message.contains("My", true) && lastMessage.message.contains("name", true) && lastMessage.owner == "You") {
            addAnswer("You are " + chatDapter.username + " right?")
            return
        }
        if (lastMessage.message.contains("Your", true) && lastMessage.message.contains("name", true) && lastMessage.owner == "You") {
            addAnswer("My name is " + name)
            return
        }
        if (lastMessage.message.contains("You", true) && lastMessage.message.contains("Who", true) && lastMessage.owner == "You") {
            addAnswer("My name is " + name)
            return
        }
        if (lastMessage.message.contains("I", true) && lastMessage.message.contains("Who", true) && lastMessage.owner == "You") {
            addAnswer("You are " + chatDapter.username + " right?")
            return
        }
        if (lastMessage.message.contains("time", true) && lastMessage.owner == "You") {
            addAnswer("How does 3:64 PM sound?")
            return
        }
        if (lastMessage.message.contains("sounds", true) && lastMessage.owner == "You") {
            addReaction("Thats awesome, can\'t wait (w/rizz)")
            return
        }
        if (lastMessage.message.contains("okay", true) && lastMessage.owner == "You") {
            addAnswer("Okay (w/stease)")
            return
        }
        if (lastMessage.message.contains("uh", true) && lastMessage.owner == "You") {
            addAnswer("What?")
            return
        }
        if (lastMessage.message.contains("hey", true) && lastMessage.owner == "You") {
            addAnswer("Hey (w/rizz)")
            return
        }
        if ((lastMessage.message.contains("AM", true) || lastMessage.message.contains("PM", true))
            && lastMessage.message.contains(":", true) && lastMessage.owner == "You") {
            addAnswer("Yeah, that works!")
            return
        }
    }
}