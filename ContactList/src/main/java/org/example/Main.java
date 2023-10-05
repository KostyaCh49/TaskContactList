package org.example;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Contact[] contacts = new Contact[100];
    static int amountOfContacts = 0;
    static int currentContact = 0;
    static boolean accessToFunctions = false;

    public static void main(String[] args) {
        int choice = 0;
        while (true) {
            System.out.println("""
                    Options:
                    1. Add Contact
                    2. List Contacts
                    3. Edit Contact
                    4. Delete Contact
                    5. Quit
                    Enter your choice:\s""");

            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addContact();
                case 2 -> listContacts();
                case 3 -> editContact();
                case 4 -> deleteContact();
                case 5 -> System.exit(0);
                default -> System.out.println("You need to choose 1-5");
            }
        }
    }

    public static void addContact() {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter surname: ");
        String surname = scanner.next();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.next();

        if (amountOfContacts <= contacts.length) {
            Contact newContact = new Contact(name, surname, phoneNumber);
            contacts[currentContact] = newContact;
            amountOfContacts++;
            currentContact++;
            System.out.println("Contact created successfully.");
        } else {
            System.out.println("Contacts limit: 100. You can't create more contacts");
        }
    }

    public static void deleteContact() {
        listContacts();

        if (accessToFunctions) {
            System.out.println("Enter contact numbering: ");
            int index = scanner.nextInt();

            index--;
            if (contacts[index] == null) {
                System.out.println("No contact at this index.");
            } else {
                System.out.println("Contact delete successfully.");
                contacts[index] = null;
                contacts[index] = contacts[currentContact - 1];
                contacts[currentContact - 1] = null;
                amountOfContacts--;
                currentContact--;
            }
        }
    }

    public static void editContact() {
        listContacts();

        if (accessToFunctions) {
            System.out.println("Enter contact numbering: ");
            int index = scanner.nextInt();

            index--;
            if (contacts[index] == null) {
                System.out.println("No contact at this index.");
            } else {
                System.out.println(contacts[index].getName());
                System.out.println(contacts[index].getSurname());
                System.out.println(contacts[index].getPhoneNumber());

                System.out.println("What do you want to change? Name - 1, surname - 2, phone number - 3. Enter the number(1-3).");
                int choice = 0;
                do {
                    choice = scanner.nextInt();
                } while (choice < 1 || choice > 3);

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter a new name: ");
                        contacts[index].setName(scanner.next());
                    }
                    case 2 -> {
                        System.out.println("Enter a new surname: ");
                        contacts[index].setSurname(scanner.next());
                    }
                    case 3 -> {
                        System.out.println("Enter a new phone number: ");
                        contacts[index].setPhoneNumber(scanner.next());
                    }
                }
            }
        }
    }

    public static void listContacts() {
        if (amountOfContacts == 0) {
            System.out.println("No contacts. Add at least one contact.");
        } else {
            accessToFunctions = true;
            for (int i = 0; i < amountOfContacts; i++) {
                System.out.println(i + 1 + ":");
                System.out.println(contacts[i].getName());
                System.out.println(contacts[i].getSurname());
                System.out.println(contacts[i].getPhoneNumber() + "\n");
            }
        }
    }
}