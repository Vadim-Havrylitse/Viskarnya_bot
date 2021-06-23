package model;

import lombok.Data;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

@Data
public class WhiskeyBottle {

    private Long id;
    private Image photo;
    private String description;

    @SneakyThrows
    public WhiskeyBottle (URL url, String description){
        this.photo = ImageIO.read(url);
        this.description = description;
    }
}
