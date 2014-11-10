package com.iesfernandowirtz.loteria;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// Atributos:
	TextView textViewResultado;
	TextView textViewReintegro;
	Loteria objLoteria;
	String resultado;
	String reintegro;

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

		generarNumeros();

		mostrarNumeros();

	}

	private void mostrarNumeros() {
		textViewResultado.setText(resultado);
		textViewReintegro.setText(reintegro);
	}

	private void generarNumeros() {
		objLoteria = new Loteria();
		resultado = objLoteria.mostrarTabla();
		reintegro = objLoteria.mostrarReintegro();
	}

	private void guardarDatos(String resultado, String reintegro) {
		SharedPreferences preferencias = getSharedPreferences("datos",
				Context.MODE_PRIVATE);
		Editor editor = preferencias.edit();
		editor.putString("resultado", resultado);
		editor.putString("reintegro", reintegro);
		editor.commit();
	}

	public void onButtonClickGuardar(View v) {
		if (resultado == null || reintegro == null) {
			alertaSimple("Error al guardar",
					"Debe de generar un número para poder guardarlo");

		} else {
			guardarDatos(resultado, reintegro);
			alertaSimple("Guardado correcto",
					"El número se ha guardado correctamente");
		}
	}

	private void alertaSimple(String titulo, String mensaje) {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(titulo);
		builder.setMessage(mensaje);
		builder.setPositiveButton("OK", null);
		builder.create();
		builder.show();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			new AlertDialog.Builder(this)
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setTitle("Salir")
					.setMessage(
							"Estás seguro? Se perderan los cambios no guardados!")
					.setNegativeButton(android.R.string.cancel, null)
					// sin listener
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {// un
								// listener
								// que al
								// pulsar,
								// cierre la
								// aplicacion
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Salir
									MainActivity.this.finish();
								}
							}).show();

			// Si el listener devuelve true, significa que el evento esta
			// procesado, y nadie debe hacer nada mas
			return true;
		}
		// para las demas cosas, se reenvia el evento al listener habitual
		return super.onKeyDown(keyCode, event);
	}

}
