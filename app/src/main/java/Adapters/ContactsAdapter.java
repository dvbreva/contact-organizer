package Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.contactsorganizer.R;

import java.util.List;

import Models.Contact;

public class ContactsAdapter extends ArrayAdapter<Contact> {

    private Context context;
    private List<Contact> contacts;


    public ContactsAdapter(Context context, List<Contact> contacts) {
        super(context, R.layout.contact_layout, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.contact_layout, parent, false);

        TextView textViewName = (TextView) view.findViewById(R.id.textViewName);
        textViewName.setText(contacts.get(position).getName());

        TextView textViewPhone = (TextView) view.findViewById(R.id.textViewPhone);
        textViewPhone.setText(contacts.get(position).getPhone());

        TextView textViewDescription = (TextView) view.findViewById(R.id.textViewDescription);
        textViewDescription.setText(contacts.get(position).getDescription());

        TextView textViewCategory = (TextView) view.findViewById(R.id.textViewCategory);
        textViewCategory.setText(contacts.get(position).getCategory());

        return view;
    }
}