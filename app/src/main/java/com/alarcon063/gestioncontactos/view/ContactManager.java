package com.alarcon063.gestioncontactos.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alarcon063.gestioncontactos.R;
import com.alarcon063.gestioncontactos.data.ContactsRepository;
import com.alarcon063.gestioncontactos.domain.Contact;
import com.alarcon063.gestioncontactos.domain.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContactManager extends RecyclerView.Adapter<ContactManager.ViewHolder> {
    private LayoutInflater inflater;
    protected List<Contact> contactsList;
    private static final String LOG_TAG = ContactManager.class.getSimpleName();

    public ContactManager(Context context, List<Contact> list) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contactsList = new ArrayList<>();
        this.contactsList.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.item_contact, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contactsList.get(position);
        holder.name.setText(contact.getFullname());
        holder.phone.setText(contact.getPhone());
        holder.contactId = contact.getId();
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }


    public void save(String name, String lastName, String phone, String address, Gender gender, View view) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);
        contact.setGender(gender);
        ContactsRepository.getList().add(contact);

        this.contactsList = new ArrayList<>();
        this.contactsList.addAll(ContactsRepository.getList());

        notifyDataSetChanged();
        Context context = view.getContext();
        Toast.makeText(context, "Contacto guardado", Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView phone;

        private Long contactId;

        public Long getContactId() {
            return contactId;
        }

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_view_nombre_contacto);
            phone = (TextView) itemView.findViewById(R.id.text_view_numero_contacto);
            ImageButton callButton = itemView.findViewById(R.id.callButton);

            callButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    String phone = contactsList.get(getAdapterPosition()).getPhone();
                    intent.setData(Uri.parse("tel:" + phone));
                    context.startActivity(intent);
                }
            });
        }
    }


}
