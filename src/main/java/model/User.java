package model;

import lombok.Data;

import java.util.Map;

@Data
public class User implements ModelElementId<Long> {
    private Long id;
    private String name;
    private int currentWhiskeyBottle;
    private Map<WhiskeyBottle, Integer> resultOfVote;

    public User(Long chatId) {
        this.id = chatId;
        this.currentWhiskeyBottle = 0;
    }

}
