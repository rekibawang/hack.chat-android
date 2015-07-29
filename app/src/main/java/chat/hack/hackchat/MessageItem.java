package chat.hack.hackchat;

import android.widget.TextView;

/**
 * Created by Rudra on 12-07-2015.
 */
public class MessageItem {
    String message;
    String sender;
    String time;
    String cls;
    String trip; // stores the tripcode of the user
    String myNick; // myNick is used to highlight messages with @myNick

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public String getTime() {
        return time;
    }

    public String getCls() {
        return cls;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setCls(String cls) {
        this.cls = cls;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public void setMyNick(String myNick) {
        this.myNick = myNick;
    }

    public String getMyNick() {
        return myNick;
    }


}
