package com.example.addressbook.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used for TDD
 */
public class ContactManager {

    private IContactDAO iContactDAO;

    private ArrayList<Contact> contacts  = new ArrayList<Contact>();

    public ContactManager(IContactDAO iContactDAO) {
        this.iContactDAO = iContactDAO;
        }

    public List<Contact> searchContacts(String query) {
        return this.contacts;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

}
