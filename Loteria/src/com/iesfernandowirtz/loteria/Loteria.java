package com.iesfernandowirtz.loteria;

import java.util.Arrays;
import java.util.Random;

public class Loteria {

	int tabla[];
	int reintegro;

	public Loteria() {
		tabla = new int[6];
		generarAleatorios();
		generarReintegro();

	}

	// Creamos un método que genera un número aleatorio entre 1 y 49.
	private void generarAleatorios() {
		int aleatorio;
		Random random = new Random(); // Creo un objeto random.
		for (int i = 0; i < tabla.length; i++) {

			aleatorio = random.nextInt(49) + 1;

			while (!comprobarRepetido(aleatorio)) {
				aleatorio = random.nextInt(49) + 1;
			}
			tabla[i] = aleatorio;
		}

		ordenarAleatorios();
	}

	private void ordenarAleatorios() {
		Arrays.sort(tabla);// Los ordena de menos a mayor.
	}

	// Creamos un método que genera un número aleatorio entre 1 y 9.
	private void generarReintegro() {
		Random random = new Random(); // Creo un objeto random.
		reintegro = random.nextInt(9) + 1;
	}

	private boolean comprobarRepetido(int aleatorio) {

		for (int i = 0; i < tabla.length; i++) {
			if (tabla[i] == aleatorio) {
				return false;
			}
		}
		return true;
	}

	public String mostrarTabla() {
		StringBuffer numeros = new StringBuffer();
		for (int i = 0; i < tabla.length; i++) {
			numeros.append("  " + Integer.toString(tabla[i]));
		}
		return numeros.toString();
	}

	public String mostrarReintegro() {
		return Integer.toString(reintegro);
	}

	public int[] getTabla() {
		return tabla;
	}

	public int getReintegro() {
		return reintegro;
	}
	
}
