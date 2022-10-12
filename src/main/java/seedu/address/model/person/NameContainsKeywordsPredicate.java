package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return
            keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getName().fullName, keyword))
            ||
            keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().getValue(), keyword))
            ||
            keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getClazz().className, keyword))
            ||
            keywords.stream()
                    .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().value, keyword))
            ||
            keywords.stream()
                    .anyMatch(keyword -> arrayListContainsWord(person.getSubject().getAllSubjectsTaken(), keyword))
            ||
            keywords.stream()
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
