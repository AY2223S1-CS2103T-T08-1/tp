package seedu.address.ui;

import java.util.List;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.subject.Assessment;
import seedu.address.model.person.subject.Subject;

/**
 * Controller for a help page
 */
public class GradeWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(GradeWindow.class);
    private static final String FXML = "GradeWindow.fxml";
    private String assessmentString;
    private int index = 0;
    private List<Person> personList;

    @FXML
    private Button submitButton;

    @FXML
    private Label assessmentName;

    @FXML
    private Label assessmentWeightage;

    @FXML
    private Label studentClass;

    @FXML
    private Label studentName;

    @FXML
    private Label assessmentSubject;

    @FXML
    private Label assessmentTotalScore;

    @FXML
    private TextField enteredScore;



    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public GradeWindow(Stage root) {
        super(FXML, root);
        enteredScore.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    enterGradeForStudent();
                }
            }
        });
    }



    /**
     * Creates a new HelpWindow.
     */
    public GradeWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show(List<Person> personList, String assessmentString) {
        logger.fine("Showing the grading page.");
        this.personList = personList;
        this.assessmentString = assessmentString;
        getRoot().show();
        getRoot().centerOnScreen();
        updateUI();

    }

    /**
     * updates UI Labels to the next person to be updated
     */
    public void updateUI() {
        if (index > personList.size()) {
            getRoot().hide();
            return;
        }
        //parse assesment string
        String[] parsedString = assessmentString.split("_");
        String subject = parsedString[0].trim();
        String name = parsedString[1].trim();
        String totalScore = parsedString[2].trim();
        String weightage = parsedString[3].trim();
        Person currentPerson = personList.get(index);
        assessmentSubject.setText("Subject: " + subject);
        assessmentName.setText("Assessment: " + name);
        assessmentWeightage.setText("Weightage: " + weightage);
        assessmentTotalScore.setText("Total Score: " + totalScore);
        studentName.setText("Student Name: " + currentPerson.getName().toString());
        studentClass.setText("Student Class: " + currentPerson.getStudentClass());
        return;
    }

    /**
     * Updates Grades of current student in focus
     * @param mark score received for the assignment
     */
    public void updateGradesForCurrentStudent(String mark) {
        String[] parsedString = assessmentString.split("_");
        String subjectName = parsedString[0].trim();
        String name = parsedString[1].trim();

        double score = Double.parseDouble(mark);
        double totalScore = Double.parseDouble(parsedString[2].trim());
        double weightage = Double.parseDouble(parsedString[3].trim());
        double difficulty = Double.parseDouble(parsedString[4].trim());
        Assessment newAssesment = new Assessment(name, weightage, score, totalScore, difficulty);
        Person currentPerson = personList.get(index);
        Subject subject = currentPerson.getSubjectHandler().getSubject(subjectName);
        subject.updateGradeAssessment(newAssesment);
    }
    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Method to handle when a grade is entered for a student
     */
    @FXML
    public void enterGradeForStudent() {
        updateGradesForCurrentStudent(enteredScore.getText());
        enteredScore.clear();
        index += 1;
        updateUI();
    }

}
