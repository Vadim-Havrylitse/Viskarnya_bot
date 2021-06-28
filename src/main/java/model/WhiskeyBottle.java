package model;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.InputFile;


@Data
public class WhiskeyBottle implements ModelElementId<String> {

    private String nameBottle;
    private InputFile icon;
    private String description;


    @Override
    public String getId() {
        return nameBottle;
    }
}
