package com.iesfernandowirtz.loteria;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	// Atributos:
	TextView textViewResultado;
	TextView textViewReintegro;
	Loteria objLoteria;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Establecer identificadores:
		textViewResultado = (TextView) findViewById(R.id.tvResultado);
		textViewReintegro = (TextView) findViewById(R.id.tvReintegro);
		SharedPreferences prefe = getSharedPreferences("datos", MODE_PRIVATE);
		textViewResultado.setText(prefe.getString("resultado", ""));
		textViewReintegro.setText(prefe.getString("reintegro", ""));
	}

	public void onButtonClickGenerar(View v) {

		objLoteria = new Loteria();
		String resultado = objLoteria.mostrarTabla();
		String reintegro = objLoteria.mostrarReintegro();
		
		textViewResultado.setText(resultado);
		textViewReintegro.setText(reintegro);
		
		SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
		Editor editor=preferencias.edit();
		editor.putString("resultado", resultado);
		editor.putString("reintegro", reintegro);
		editor.commit();  

	}

}
