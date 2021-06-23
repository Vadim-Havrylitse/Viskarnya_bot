package service;

import controller.TelegramApi;

public interface UserRegistration {

    public void execute (Long chatId, String text, TelegramApi controller);

    public static UserRegistrationClass of(){
        return new UserRegistrationClass();
    }
}
