package com.swordsoft.telegram.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {
	 @SerializedName("chat_id")
	 @Expose
	 private Integer chatId;
	 @SerializedName("text")
	 @Expose
	 private String text;
	 @SerializedName("parse_mode")
	 @Expose
	 private String parseMode;
	 @SerializedName("disable_web_page_preview")
	 @Expose
	 private Boolean disableWebPagePreview;
	 @SerializedName("disable_notification")
	 @Expose
	 private Boolean disableNotification;
	 @SerializedName("reply_to_message_id")
	 @Expose
	 private Integer replyToMessageId;
	public Integer getChatId() {
		return chatId;
	}
	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParseMode() {
		return parseMode;
	}
	public void setParseMode(String parseMode) {
		this.parseMode = parseMode;
	}
	public Boolean getDisableWebPagePreview() {
		return disableWebPagePreview;
	}
	public void setDisableWebPagePreview(Boolean disableWebPagePreview) {
		this.disableWebPagePreview = disableWebPagePreview;
	}
	public Boolean getDisableNotification() {
		return disableNotification;
	}
	public void setDisableNotification(Boolean disableNotification) {
		this.disableNotification = disableNotification;
	}
	public Integer getReplyToMessageId() {
		return replyToMessageId;
	}
	public void setReplyToMessageId(Integer replyToMessageId) {
		this.replyToMessageId = replyToMessageId;
	}
	 
	 
}
