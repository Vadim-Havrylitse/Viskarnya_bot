package service;

public interface UserVoteBlock {

    public void execute (Long chatId);

    public static UserVoteBlockClass of(){
        return new UserVoteBlockClass();
    }
}
