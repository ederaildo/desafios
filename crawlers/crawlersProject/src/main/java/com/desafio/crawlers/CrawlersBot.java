package com.desafio.crawlers;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class CrawlersBot extends TelegramLongPollingBot {
 
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() 
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                sendMessage(message); 
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBotUsername() {
    	return "Ederaildo_Bot";
    }

    @Override
    public String getBotToken() {
    	return "472628283:AAGeawEPr6OkUYBWKrJoc-Y6UV7OU9fq0GQ";
    }
}