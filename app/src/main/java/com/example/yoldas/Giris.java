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

public class Giris extends AppCompatActivity {
    private EditText editEmaill, editSifree;
    private String txtEmaill, txtSifree;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        editEmaill = (EditText)findViewById(R.id.kayit_ol_editEmaill);
        editSifree = (EditText)findViewById(R.id.kayit_ol_editSifree);

        mAuth = FirebaseAuth.getInstance();

    }
    public void ButtonKayitiki(View v){
        txtEmaill = editEmaill.getText().toString();
        txtSifree = editSifree.getText().toString();
        if(!TextUtils.isEmpty(txtEmaill) && !TextUtils.isEmpty(txtSifree)) {
            mAuth.createUserWithEmailAndPassword(txtEmaill, txtSifree)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                                Toast.makeText(Giris.this, " Kayit İşlemi Başarılı", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(Giris.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

        }else
            Toast.makeText(this,"Email Ve Şifre Boş Bırakılamaz", Toast.LENGTH_SHORT).show();

    }
}