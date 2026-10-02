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


        list.add(contact1);
        list.add(contact2);
    }
}
