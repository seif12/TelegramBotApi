
package com.swordsoft.telegram.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Update {

    @SerializedName("update_id")
    @Expose
    private Integer updateId;
    @SerializedName("message")
    @Expose
    private Message message;
    @SerializedName("edited_message")
    @Expose
    private Message editedMessage;
    @SerializedName("channel_post")
    @Expose
    private Message channelPost;
    @SerializedName("edited_channel_post")
    @Expose
    private Message editedChannelPost;


    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

	public Message getEditedMessage() {
		return editedMessage;
	}

	public void setEditedMessage(Message editedMessage) {
		this.editedMessage = editedMessage;
	}

	public Message getChannelPost() {
		return channelPost;
	}

	public void setChannelPost(Message channelPost) {
		this.channelPost = channelPost;
	}

	public Message getEditedChannelPost() {
		return editedChannelPost;
	}

	public void setEditedChannelPost(Message editedChannelPost) {
		this.editedChannelPost = editedChannelPost;
	}

    
}
