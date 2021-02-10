package com.example.rojo_m1;


import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Modele.User;

public class Profile_Fragment extends Fragment {

    String key;
    TextInputEditText edtName,edtEmail, edtPassword;
    TextView profile_name,profile_description,count;
    home home;
    Button button;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         home = (home)getActivity();
        key = home.getKey();
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        button = (Button) rootView.findViewById(R.id.button_update);
        edtName = (TextInputEditText)rootView.findViewById(R.id.username);
        edtEmail = (TextInputEditText)rootView.findViewById(R.id.email);
        edtPassword = (TextInputEditText)rootView.findViewById(R.id.password);
        profile_name = (TextView)rootView.findViewById(R.id.profile_username);
        profile_description = (TextView)rootView.findViewById(R.id.profile_email);
        count = (TextView)rootView.findViewById(R.id.first_text_card);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              boolean boo =   updateUser(key, edtName.getText().toString(),edtEmail.getText().toString(),edtPassword.getText().toString());
              if(boo){
                  Toast.makeText(home, "Account uptaded successfully", Toast.LENGTH_SHORT).show();
              } else {
                  Toast.makeText(home, "Account non uptaded successfully", Toast.LENGTH_SHORT).show();
              }
            }
        });


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child(key);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = new User(snapshot.child("name").getValue().toString(),snapshot.child("email").getValue().toString(), snapshot.child("password").getValue().toString());
                profile_name.setText(user.getName());
                profile_description.setText(user.getEmail());
                edtName.setText(user.getName());
                edtEmail.setText(user.getEmail());
                edtPassword.setText(user.getPassword());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("uploads");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int countString = new Long(dataSnapshot.getChildrenCount()).intValue();
                count.setText(""+countString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;
    }

    public boolean updateUser(String id, String name, String email, String password)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User").child(id);
        User user = new User(name,email,password);
        databaseReference.setValue(user);



        return true;
    }
}
