package eus.ehu;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class TableController {

    @FXML
    private TableColumn<Question, Integer> questionID;

    @FXML
    private TableColumn<Question, String> questionText;

    @FXML
    private TableView<Question> tblQuestions;

    @FXML
    private TextField txtQuestion;

    @FXML
    private Label lblMessage;

    private ObservableList<Question> questions;
    private int nextQuestionId;
    private final PauseTransition messageTimer = new PauseTransition(Duration.seconds(3));

    @FXML
    void addButton() {
        String newText = txtQuestion.getText().trim();
        if (newText.isEmpty()) {
            showTemporaryMessage("The question shouldn't be empty");
            return;
        }

        Question newQuestion = new Question(nextQuestionId++, newText);
        questions.add(newQuestion);
        txtQuestion.clear();
        lblMessage.setVisible(false);
    }

    @FXML
    void removeButton() {
        Question selected = tblQuestions.getSelectionModel().getSelectedItem();
        if (selected != null) {
            questions.remove(selected);
            reorderQuestionIds();
            showTemporaryMessage("Question removed");
        } else {
            showTemporaryMessage("Please select a question to remove");
        }
    }

    @FXML
    void initialize() {
        questionID.setCellValueFactory(new PropertyValueFactory<>("id"));
        questionText.setCellValueFactory(new PropertyValueFactory<>("text"));

        questions = FXCollections.observableArrayList(
                new Question(1, "What is Java?"),
                new Question(2, "What is JavaFX?"),
                new Question(3, "What is a TableView?")
        );
        nextQuestionId = questions.stream()
            .mapToInt(Question::getId)
            .max()
            .orElse(0) + 1;
        tblQuestions.setItems(questions);
        setupQuestionsSelection();
        lblMessage.setVisible(false);
        messageTimer.setOnFinished(event -> lblMessage.setVisible(false));
    }

    private void setupQuestionsSelection(){
        tblQuestions.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Selected question: " + newSelection);
            }
        });

    }

    private void showTemporaryMessage(String message) {
        lblMessage.setText(message);
        lblMessage.setVisible(true);
        messageTimer.stop();
        messageTimer.playFromStart();
    }

    private void reorderQuestionIds() {
        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setId(i + 1);
        }
        nextQuestionId = questions.size() + 1;
        tblQuestions.refresh();
    }

}

