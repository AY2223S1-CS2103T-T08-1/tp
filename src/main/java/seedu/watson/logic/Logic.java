package seedu.watson.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.watson.commons.core.GuiSettings;
import seedu.watson.logic.commands.CommandResult;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.logic.parser.exceptions.ParseException;
import seedu.watson.model.ReadOnlyAddressBook;
import seedu.watson.model.person.Person;
import seedu.watson.model.Model;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' watson book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
