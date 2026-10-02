package com.alarcon063.gestioncontactos.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alarcon063.gestioncontactos.R;
import com.alarcon063.gestioncontactos.data.ContactsRepository;
import com.alarcon063.gestioncontactos.databinding.ActivityFormBinding;
import com.alarcon063.gestioncontactos.domain.Contact;
import com.alarcon063.gestioncontactos.domain.Gender;

public class FormActivity extends AppCompatActivity {

    private ActivityFormBinding binding;
    private ContactManager contactManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageButton doneButton = binding.doneButton;

        String title = getIntent().getStringExtra("title");
        TextView formTitle = binding.formTitle;
        formTitle.setText(title);

        doneButton.setOnClickListener(this::onDoneClick);
    }

    private void onDoneClick(View view) {
        String name = binding.formInputName.getText().toString();
        String phone = binding.formInputPhone.getText().toString();

        // Validar campos requeridos
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(view.getContext(), "ERROR: ingrese datos", Toast.LENGTH_SHORT).show();
            return;
        }

        String lastName = binding.formInputLastName.getText().toString();
        String address = binding.formInputAddress.getText().toString();
        int genderId = binding.radioGroupGender.getCheckedRadioButtonId();

        Gender gender;
        if(genderId == R.id.genderFemale) {
            gender = Gender.FEMALE;
        } else {
            gender = Gender.MALE;
        }

        getContactManager().save(name, lastName, phone, address, gender, view);

        // Eliminar la actividad actual y volver a la anterior (ContactsActivity)
        finish();
    }

    private ContactManager getContactManager() {
        if(contactManager == null) {
            contactManager = new ContactManager(this, ContactsRepository.getList());
        }
        return contactManager;
    }
}