package com.alarcon063.gestioncontactos.data;

import com.alarcon063.gestioncontactos.domain.Contact;
import com.alarcon063.gestioncontactos.domain.Gender;

import java.util.ArrayList;
import java.util.List;

public class ContactsRepository {
    private static List<Contact> list;

    public static List<Contact> getList() {
        if(list == null) {
            initContactList();
        }
        return list;
    }

    private static void initContactList(){
        list = new ArrayList<Contact>();
        Contact contact1 = new Contact("Melany", "Perez", "123456", "San lorenzo 321", Gender.FEMALE);
        Contact contact2 = new Contact("Olivia", "Alvarez", "789101", "Rocamora 654", Gender.FEMALE);
        Contact contact3 = new Contact("Simon", "Velez", "121315", "Alem 987", Gender.MALE);
        Contact contact4 = new Contact("Ulises", "Klug", "121315", "Alem 987", Gender.MALE);
        Contact contact5 = new Contact("Sheila", "Casas", "121315", "Alem 987", Gender.MALE);


        list.add(contact1);
        list.add(contact2);
        list.add(contact3);
        list.add(contact4);
        list.add(contact5);
    }
}
