package eus.ehu;

import javafx.scene.image.Image;

public class Question {
    Integer id;
    String text;
    private Image image;
    private String imagePath;

    public Question(Integer id, String text, String imagePath) {
        this.id = id;
        this.text = text;
        this.image = new Image(imagePath);
    }

    public Question(){
        
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Image getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String toString() {
        return id + ": " + text;
    }
}
