package service;

public interface UserRegistration {

    public void execute (Long chatId);

    public static UserVoteBlockClass of(){
        return new UserVoteBlockClass();
    }
}
