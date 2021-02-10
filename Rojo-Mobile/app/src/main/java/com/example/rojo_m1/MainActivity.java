package com.example.rojo_m1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Modele.User;

public class MainActivity extends AppCompatActivity {

    TextInputEditText edtPhone, edtPassword;
    TextInputLayout edtPhone1, edtPassword1;
    Button btnSignIn, btnSignUp;
    ImageView image;
    TextView logo, slogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtPhone = (TextInputEditText)findViewById(R.id.edtPhone);
        edtPassword = (TextInputEditText)findViewById(R.id.edtPassword);

        edtPhone1 = findViewById(R.id.edtPhone1);
        edtPassword1 = findViewById(R.id.edtPassword1);

        image = findViewById(R.id.logoImage);
        logo = findViewById(R.id.logoText);
        slogan = findViewById(R.id.logoSlogan);

        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        btnSignUp = (Button)findViewById(R.id.btnSignUp);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please waiting ...");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()) {
                            mDialog.dismiss();
                            User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(edtPassword.getText().toString())) {
                               // Toast.makeText(MainActivity.this, "Sign in successfully !", Toast.LENGTH_SHORT).show();
                                Intent home = new Intent(MainActivity.this, home.class);
                                home.putExtra("KEY", edtPhone.getText().toString());
                                startActivity(home);
                            } else {
                                Toast.makeText(MainActivity.this, "Sign in failed !", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(MainActivity.this, "user does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUp = new Intent(MainActivity.this, SignUp.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View,String>(image, "logo_image");
                pairs[1] = new Pair<View,String>(logo, "logo_text");
                pairs[2] = new Pair<View,String>(slogan, "logo_text1");
                pairs[3] = new Pair<View,String>(edtPhone1, "phone_tran");
                pairs[4] = new Pair<View,String>(edtPassword1, "pass_tran");
                pairs[5] = new Pair<View,String>(btnSignIn, "signup_but");
                pairs[6] = new Pair<View,String>(btnSignUp, "signup_tran");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options= ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(signUp, options.toBundle());
                }
            }
        });

    }
}