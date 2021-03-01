package com.example.coin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText etusuario, etpassword;
    Button btnlogin, btnregistrar;
    String url= "https://coinpurseadministratudinero.000webhostapp.com/login.php";
    String str_usuario, str_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etusuario =findViewById(R.id.etusuario);
        etpassword= findViewById(R.id.etpassword);
        btnlogin=findViewById(R.id.btnlogin);
        btnregistrar=findViewById(R.id.btnregistrar);


    }
    public void MainActivity (View View)
    {
        if(etusuario.getText().toString().equals(""))
        {
            Toast.makeText(this,"Ingresa usuario",Toast.LENGTH_SHORT).show();
        }else if (etpassword.getText().toString().equals(""))
        {
            Toast.makeText(this,"Ingresa contraseña",Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Espere un momento");
            progressDialog.show();
            str_usuario = etusuario.getText().toString().trim();
            str_password = etusuario.getText().toString().trim();



            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if (response.equalsIgnoreCase("Perfecto")) {
                        etusuario.setText("");
                        etpassword.setText("");
                        startActivity(new Intent(getApplicationContext(), menu.class));
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                   // str_password="lacorrales";
                  //  str_password="12345";
                    params.put("correo", str_usuario);
                    params.put("password", str_password);
return params;
                }
            };


            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);



        }
    } public void menu(View view)
    {
        Intent menu=new Intent(this, menu.class);
        Intent envia=new Intent(this, menu.class);
        envia.putExtra("Usuario",str_usuario);
        startActivity(envia);
        startActivity(menu);
    }



}