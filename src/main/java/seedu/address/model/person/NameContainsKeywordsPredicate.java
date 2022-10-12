package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLAZZ;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSONALITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;
    private String keywordsSeparatedBySpaces;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
        this.keywordsSeparatedBySpaces = " " + String.join(" ", keywords);
    }

    @Override
    public boolean test(Person person) {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(keywordsSeparatedBySpaces, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                                       PREFIX_ADDRESS, PREFIX_CLAZZ, PREFIX_PERSONALITY, PREFIX_ATTENDANCE,
                                       PREFIX_SUBJECT, PREFIX_GRADE, PREFIX_TAG);

        List<String> names = argMultimap.getAllValues(PREFIX_NAME);
        List<String> phones = argMultimap.getAllValues(PREFIX_PHONE);
        List<String> emails = argMultimap.getAllValues(PREFIX_EMAIL);
        List<String> addresses = argMultimap.getAllValues(PREFIX_ADDRESS);
        List<String> clazzes = argMultimap.getAllValues(PREFIX_CLAZZ);
        List<String> personalities = argMultimap.getAllValues(PREFIX_PERSONALITY);
        //List<String> attendances = argMultimap.getAllValues(PREFIX_ATTENDANCE);
        List<String> subjects = argMultimap.getAllValues(PREFIX_SUBJECT);
        //List<String> grades = argMultimap.getAllValues(PREFIX_GRADE);
        //Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return
            names.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword))
            ||
            phones.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().getValue(), keyword))
            ||
            clazzes.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getClazz().className, keyword))
            ||
            addresses.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword))
            ||
            subjects.stream()
                    .anyMatch(keyword -> arrayListContainsWord(person.getSubject().getAllSubjectsTaken(), keyword))
            ||
            personalities.stream()
                    .anyMatch(keyword -> arrayListContainsWord(person.getPersonality().getArrayOfPersonalities(), keyword));
    }

    private boolean arrayListContainsWord(ArrayList<String> arrayList, String keyword) {
        for (String string: arrayList) {
            if (string.equals(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
