package controller;

import lombok.Data;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.UserRegistration;
import service.UserRegistrationClass;

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
        if (update.hasMessage() && update.getMessage().getText().startsWith("/start")){
            userRegistration.execute(update.getMessage().getChatId(), null,this);
        }


    }

    @SneakyThrows
    public void sendNewMessage (Long chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(chatId.toString());
        sendMessage.enableHtml(true);
        execute(sendMessage);
    }
}
