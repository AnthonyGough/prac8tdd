import com.example.addressbook.model.Contact;
import com.example.addressbook.model.ContactManager;
import com.example.addressbook.model.SqliteContactDAO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactManagerTest {

    private ContactManager contactManager;
    private static final String EMPTY_STRING = new String("");
    private static final String NULL_STRING = null;
    private Contact[] contacts = {
            new Contact("John", "Smith", "john.smith@abc.com", "4454844847" ),
            new Contact("Albert", "Wiseman", "wise@wiseworks.com", "67832682"),
            new Contact("Amy", "Lutz", "amy@ppoiu.com", "2232324"),
            new Contact("Timothy", "Ward", "amy@ppoiu.com", "2232324"),
            new Contact("Lucy", "Skydiver", "amy@ppoiu.com", "2232324"),
            new Contact("Amy", "Lutz", "cindy@cindy.com", "875454545455")
    };

    private static final String MATCHING_FIRSTNAME_AMY = "AMY";

    @BeforeAll
    public void setup() {
        contactManager = new ContactManager(new SqliteContactDAO());
        for (Contact contact : contacts) {
            contactManager.addContact(contact);
        }
    }

    @Test
    public void testSearchByFirstnameCorrectNumberNormalCase() {
        List<Contact> matchingContacts = contactManager.searchContacts(contacts[0].getFirstName());
        assertEquals(1, matchingContacts.size());
    }

    @Test
    public void testSearchByFirstnameCorrectNumberLowerCase() {
        List<Contact> matchingContacts = contactManager.searchContacts(contacts[0].getFirstName().toLowerCase());
        assertEquals(1, matchingContacts.size());
    }

    @Test
    public void testSearchByFirstnameCorrectNumberUpperCase() {
        List<Contact> matchingContacts = contactManager.searchContacts(contacts[0].getFirstName().toUpperCase());
        assertEquals(1, matchingContacts.size());
    }

    @Test
    public void testReturnAllContactsWithEmptySearchQuery() {
        List<Contact> matchingContacts = contactManager.searchContacts(EMPTY_STRING);
        assertEquals(contacts.length, matchingContacts.size());
    }

    @Test
    public void testReturnAllContactsWithNullSearchQuery() {
        List<Contact> matchingContacts = contactManager.searchContacts(NULL_STRING);
        assertEquals(contacts.length, matchingContacts.size());
    }


    @Test
    public void testSearchByLastnameCorrectNumber() {
        List<Contact> matchingContacts = contactManager.searchContacts(contacts[0].getLastName());
        assertEquals(1, matchingContacts.size());
    }

    @Test
    public void testSearchByPhoneCorrectNumber() {
        List<Contact> matchingContacts = contactManager.searchContacts(contacts[0].getPhone());
        assertEquals(1, matchingContacts.size());
    }

    @Test
    public void testSearchByEmailCorrectNumber() {
        List<Contact> matchingContacts = contactManager.searchContacts(contacts[0].getEmail());
        assertEquals(1, matchingContacts.size());
    }

    @Test
    public void testSearchByFirstnameMultipleMatchingContacts() {
        List<Contact> matchingContacts = contactManager.searchContacts(MATCHING_FIRSTNAME_AMY);
        assertEquals(2, matchingContacts.size());
    }


}
