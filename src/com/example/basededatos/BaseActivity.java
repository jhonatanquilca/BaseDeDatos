package com.example.basededatos;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;


public class BaseActivity extends Activity implements OnClickListener {
	contactosController contacto;
	EditText nombre;
	EditText email;
	Button nuevoContacto;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);

		contacto = new contactosController(this);

		nombre = (EditText) findViewById(R.id.EditTextNombre);
		email = (EditText) findViewById(R.id.EditTextEmail);
		nuevoContacto = (Button) findViewById(R.id.btnNuevoContacto);
		nuevoContacto.setOnClickListener(this);
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.base, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// Toast.makeText(this, "me presionaste", Toast.LENGTH_LONG).show();
		switch (v.getId()) {
		case R.id.btnNuevoContacto:

			String nombreText = nombre.getText().toString();
			String emailText = email.getText().toString();
			contacto.actionCreate(nombreText, emailText);
			nombre.setText("");
			email.setText("");
			break;
		}

	}
}
