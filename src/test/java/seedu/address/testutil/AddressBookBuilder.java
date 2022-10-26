package seedu.address.testutil;

import seedu.address.model.Database;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code Database ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private Database addressBook;

    public AddressBookBuilder() {
        addressBook = new Database();
    }

    public AddressBookBuilder(Database addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code Database} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public Database build() {
        return addressBook;
    }
}
