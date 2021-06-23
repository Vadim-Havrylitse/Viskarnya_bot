package controller;

import lombok.Data;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static util.ApplicationProperties.getProperties;

public class TelegramApi extends TelegramLongPollingBot {
    private static final


    public TelegramApi() {
        this.
    }

    @Override
    public String getBotUsername() {
        return getProperties().getProperty("telegram_bot_username");
    }

    @Override
    public String getBotToken() {
        return getProperties().getProperty("telegram_token");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
//        SendPhoto sendPhoto = new SendPhoto();
//        sendPhoto.setChatId(String.valueOf(update.getMessage().getChatId()));
//        sendPhoto.setPhoto((InputFile)GoogleApi.reed().get(0));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
        sendMessage.setText((String) GoogleApi.reed().get(0));
        sendApiMethod(sendMessage);
    }
}
