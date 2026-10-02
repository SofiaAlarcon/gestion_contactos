package com.alarcon063.gestioncontactos.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alarcon063.gestioncontactos.data.ContactsRepository;
import com.alarcon063.gestioncontactos.databinding.ActivityContactsBinding;
import com.alarcon063.gestioncontactos.domain.Contact;

public class ContactsActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    private static final String LOG_TAG = ContactsActivity.class.getSimpleName();
    private ActivityContactsBinding binding;
    private ContactManager contactManager;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityContactsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        recyclerView = binding.contactsRecyclerView;
        contactManager = getContactManager();
        recyclerView.setAdapter(contactManager);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SearchView searchView = binding.searchView;
        searchView.setOnQueryTextListener(this);
    }

    private ContactManager getContactManager() {
        if(contactManager == null) {
            contactManager = new ContactManager(this, ContactsRepository.getList());
        }
        return contactManager;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        getContactManager().buscar(s);
        return false;
    }

    public void onClickAddButton(View view) {
        Intent i = new Intent(getApplicationContext(), FormActivity.class);
        Contact contact = null;
        i.putExtra("contact", contact);
        i.putExtra("title", "Agregar");
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume - refresh contact list");
        getContactManager().contactsList = ContactsRepository.getList();
        recyclerView.setAdapter(getContactManager());
        binding.searchView.onActionViewCollapsed();
    }
}