package com.application.ttm.controller;

import com.application.ttm.model.MessageStore;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>@Author tangtaiming</p>
 * <p>@Date 2018-12-28</p>
 * <p>@Version 1.0</p>
 **/
public class HelloWorldAction extends ActionSupport {

    private MessageStore messageStore;

    @Override
    public String execute() {
        System.out.println("Hello world!");
        messageStore = new MessageStore();
        return SUCCESS;
    }

    public String login() {
        return SUCCESS;
    }

    public MessageStore getMessageStore() {
        return messageStore;
    }
}
