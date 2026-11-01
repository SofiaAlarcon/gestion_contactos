package com.alarcon063.gestioncontactos.data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.alarcon063.gestioncontactos.domain.Contact;
import com.alarcon063.gestioncontactos.domain.Gender;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class ContactsRepository {
    private static final String LOG_TAG = ContactsRepository.class.getSimpleName();
    private final DatabaseHelper helper;


    public ContactsRepository(Context context) {
        helper = new DatabaseHelper(context);
    }

    public long insert(Contact contact) {
        long id = 0;
        try {
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("phone", contact.getPhone());
            values.put("lastName", contact.getLastName());
            values.put("address", contact.getAddress());
            values.put("gender", contact.getGender().name());

            id = db.insert(helper.TABLE_CONTACTOS, null, values);

            db.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error insertando contacto: " + e);
        }
        return id;
    }

    public int update(Long id, Contact contact) {
        int rows = 0;
        try {
            SQLiteDatabase db = helper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name", contact.getName());
            values.put("phone", contact.getPhone());
            values.put("lastName", contact.getLastName());
            values.put("address", contact.getAddress());
            values.put("gender", contact.getGender().name());

            rows = db.update(
                    helper.TABLE_CONTACTOS,
                    values,
                    "_id=?",
                    new String[]{String.valueOf(id) }
            );

            db.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error actualizando contacto: " + e);
        }

        return rows;
    }

    public List<Contact> findAll()  {
        List<Contact> list = new ArrayList<>();

        try {
            SQLiteDatabase db = helper.getReadableDatabase();

            Cursor cursor = db.query(
                    helper.TABLE_CONTACTOS,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow("lastName"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                Gender gender = Gender.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("gender")));

                Contact contact = new Contact(name, lastName, phone, address, gender);
                contact.setId(id);
                list.add(contact);
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error obteniendo contactos: ", e);
        }

        return list;
    }

    public Contact getById (long id) {
        Contact result = null;

        try {
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.query(
                    helper.TABLE_CONTACTOS,
                    null,
                    "_id=?",
                    new String[]{String.valueOf(id)},
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                long _id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow("lastName"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                Gender gender = Gender.valueOf(cursor.getString(cursor.getColumnIndexOrThrow("gender")));

                result = new Contact(name, lastName, phone, address, gender);
                result.setId(_id);
            }
            cursor.close();
            db.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error obteniendo contacto: " + e);
        }

        return result;
    }

    public int delete(long id) {
        int rows = 0;
        try {
            SQLiteDatabase db = helper.getWritableDatabase();
            rows = db.delete(
                    helper.TABLE_CONTACTOS,
                    "_id=?",
                    new String[]{String.valueOf(id)}
            );
            db.close();
        } catch (Exception e) {
            Log.e(LOG_TAG, "Error borrando contacto: " + e);
        }
        return rows;
    }
}

