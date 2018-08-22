package com.cyecize.skatefixers.areas.users.bindingModels;

public class EmailUserBindingModel {

    private String topic;

    private String message;

    public EmailUserBindingModel(){

    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
