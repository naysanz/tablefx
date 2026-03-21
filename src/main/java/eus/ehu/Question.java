package eus.ehu;

public class Question {
    Integer id;
    String text;

    public Question(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Question(){
        
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String toString() {
        return id + ": " + text;
    }
}
