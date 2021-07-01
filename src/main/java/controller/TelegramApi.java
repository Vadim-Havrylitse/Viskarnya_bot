package controller;

import lombok.Data;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserRegistration;
import service.UserRegistrationClass;

import java.io.File;
import java.util.Objects;

import static util.ApplicationProperties.getProperties;

public class TelegramApi extends TelegramLongPollingBot {
    private final UserRegistrationClass userRegistration;


    public TelegramApi() {
        this.userRegistration = UserRegistration.of();
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
        sendPhoto(update.getMessage().getChatId());
    }

    @SneakyThrows
    public void sendNewMessage (Long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        execute(sendMessage);
    }

    @SneakyThrows
    public void sendPhoto (Long chatId){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(chatId.toString());
        sendPhoto.setPhoto(new InputFile(GoogleApi.loadIcon(
                "https://drive.google.com/drive/u/0/folders/1lDfetSdYwVKjUPhZmuTPdGgpjx7ibM6K/IMG_20210301_150129.jpg"),
                "wer2"));
        //sendPhoto.setPhoto(new InputFile(TelegramApi.class.getResourceAsStream("/photo.jpg"), "sdfss"));
        execute(sendPhoto);

    }
}
