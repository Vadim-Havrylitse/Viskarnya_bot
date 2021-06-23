package model;

import lombok.Data;

@Data
public class User implements ModelElementId<Long> {
    private Long id;
    private String name;
    private int currentWhiskeyBottle;

    public User(String name) {
        this.name = name;
        this.currentWhiskeyBottle = 0;
    }

}
