package com.cun.gymoflife;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends Activity {

    Button guardar;
    EditText nombre;
    EditText usuario;
    EditText correo;
    EditText edad;
    EditText peso;
    EditText estatura;
    EditText clave;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, "5k3UQhmd8Ok59nxWMD3hcNSjPbtbeMEauFYBTa9Y", "i8tGZUNGDkJPCZ05kOSGqqCcEKnXzvt8HlBEwCZs");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

            guardar =(Button) findViewById(R.id.registro_guardar);
            nombre  =(EditText) findViewById(R.id.registro_nombre);
            usuario =(EditText) findViewById(R.id.registro_usuario);
            correo  =(EditText) findViewById(R.id.registro_correo);
            edad    =(EditText) findViewById(R.id.registro_edad);
            estatura=(EditText) findViewById(R.id.registro_estatura);
            peso    =(EditText) findViewById(R.id.registro_peso);
            clave   =(EditText) findViewById(R.id.registro_password);

            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String parse_usuario = usuario.getText().toString();
                    String parse_correo  = correo.getText().toString();
                    String parse_clave   = clave.getText().toString();

                    if(parse_usuario.equals("")){
                        Toast.makeText(context, "Todos los campos son requeridos", Toast.LENGTH_SHORT).show();
                    }else{
                        ParseUser user = new ParseUser();
                        user.setUsername(parse_usuario);
                        user.setPassword(parse_clave);
                        user.setEmail(parse_correo);
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    Toast.makeText(context,"Registro exitoso.",Toast.LENGTH_LONG).show();
                                } else {
                                    Log.d("Action", e.toString());
                                    Toast.makeText(context, "Registro no exitoso. Verifique la existencia del correo o usuario..", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });

    }
}
