package com.example.yoldas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class KayitOl extends AppCompatActivity {

    private EditText editEmail, editSifre;
    private String txtEmail, txtSifre;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        editEmail = (EditText)findViewById(R.id.editTextTextMultiLineKayit);
        editSifre = (EditText)findViewById(R.id.TextSifreKayit2);
        editSifre = (EditText)findViewById(R.id.TextSifreKayit1);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.ButtonKayitiki).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitOl.this, AnaEKRAN.class);
                startActivity(intent);
            }
        });
    }
    public void ButtonKayitiki (View v){
        txtEmail = editEmail.getText().toString();
        txtSifre = editSifre.getText().toString();

        if (!TextUtils.isEmpty(txtEmail) && !TextUtils.isEmpty(txtSifre)) {
            mAuth.createUserWithEmailAndPassword(txtEmail, txtSifre)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                                Toast.makeText(KayitOl.this, "Kayıt İşlemi Başarılı", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(KayitOl.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        } else
            Toast.makeText(this, "Email ve Şifre Boş Bırakılamaz", Toast.LENGTH_SHORT).show();


    }
}