package com.example.brush;

public class Messages {

    public String date, time, type, message, from;

    public Messages(){

    }

    public Messages(String date, String time, String type, String message, String from) {

        this.date = date;
        this.time = time;
        this.type = type;
        this.message = message;
        this.from = from;
    }

    public String getdate() {

        return date;
    }

    public void setdate(String date) {

        this.date = date;
    }

    public String gettime() {

        return time;
    }

    public void settime(String time) {

        this.time = time;
    }

    public String gettype() {

        return type;
    }

    public void settype(String type) {

        this.type = type;
    }

    public String getmessage() {

        return message;
    }

    public void setmessage(String message) {

        this.message = message;
    }

    public String getfrom() {

        return from;
    }

    public void setfrom(String from) {

        this.from = from;
    }
}


/*
public class Messages {

    public String Date, Time, Type, Message, From;

    public Messages(){

    }

    public Messages(String Date, String Time, String Type, String Message, String From) {

        this.Date = Date;
        this.Time = Time;
        this.Type = Type;
        this.Message = Message;
        this.From = From;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String From) {
        this.From = From;
    }
}

*/