package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.watson.model.Model;
import seedu.watson.model.student.Student;


/**
 * Lists all persons in the watson book to the user.
 * This starts a while loop that allows the use to input grades for each student,
 * for a specified Subject and assessment.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Allows for editing of grades by subject and assessment.\n"
            + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "Updating Grades...";

    private final String subject;
    private final String assessmentString;


    /**
     * Creates a GradeCommand to edit the grades of the specified {@code Subject}
     *
     * @param subject        the Subject to edit grades for
     * @param assessmentString the name of the assessment to edit grades for
     */
    public GradeCommand(String subject, String assessmentString) {
        this.subject = subject.toUpperCase();
        this.assessmentString = assessmentString;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(person ->
            person.getSubjectHandler().getSubjectsTaken().toString().contains(subject));

        List<Student> studentList = new ArrayList<>(model.getFilteredPersonList());

        return new CommandResult(MESSAGE_SUCCESS, true, studentList, assessmentString);
    }

}
