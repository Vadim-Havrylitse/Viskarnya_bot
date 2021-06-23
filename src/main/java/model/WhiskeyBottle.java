package model;

import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;

@Data
public class WhiskeyBottle implements ModelElementId<String> {

    private String nameBottle;
    private String description;

    @SneakyThrows
    public WhiskeyBottle (URL url, String description){
        this.description = description;
    }

    @Override
    public String getId() {
        return nameBottle;
    }
}
