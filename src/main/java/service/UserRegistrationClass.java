package service;

import controller.TelegramApi;
import lombok.Data;
import model.User;
import repository.BaseRepository;
import repository.BaseRepositoryFactory;

@Data
public class UserRegistrationClass implements UserRegistration{

    private final BaseRepository<Long, User> usersRepository;

    public UserRegistrationClass() {
        this.usersRepository = BaseRepositoryFactory.of(User.class);
    }

    @Override
    public void execute(Long chatId, String text, TelegramApi controller) {
        if (!usersRepository.existsById(chatId)){
            usersRepository.save(new User(chatId));
            controller.sendNewMessage(chatId, "Hellooooooooooo User. Write your name!");
            return;
        }
        final User user = usersRepository.getOne(chatId);


    }
}
