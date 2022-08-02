package outputUsuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Respuestas {

	public static void muestraArrayDouble(double[] nombreArray, int dimension) {
		for (int i=0; i<dimension; i++) {
			System.out.println("Pos "+(i+1)+"= "+nombreArray[i]);
		}//fin del for
	}// fin de la funcion muestraArrayDouble
	
	public static void muestraArrayEntero(int[] nombreArray, int dimension) {
		for (int i=0; i<dimension; i++) {
			System.out.println("Pos "+(i+1)+"= "+nombreArray[i]);
		}//fin del for
	}// fin de la funcion muestraArrayEntero
	
	public static boolean confirmacion() throws IOException {
		boolean confirma=false;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Esta acción no se podrá deshacer. ¿Confirmas eliminar? (y ->Sí)");
		String respuesta=br.readLine();
		if (respuesta.equals("Y") || respuesta.equals("y")) {
			confirma = true;
		}//fin del if de pregunta confirmación borrado
		return confirma;
	}// fin de funcion confirmacion
	
	public static void sumaArrayDouble(double [] nombreArray) {
		double suma=0;
		for (double pos : nombreArray) {
			suma +=pos;
		}//fin del for 
		System.out.println("El sumatorio de los valores del array es: "+suma);
	}//fin de funcion sumaArrayDouble
	
	public static void sumaArrayEntero(int [] nombreArray) {
		int suma=0;
		for (int pos : nombreArray) {
			suma +=pos;
		}//fin del for 
		System.out.println("El sumatorio de los valores del array es: "+suma);
	}//fin de funcion sumaArrayDouble
	
	public static void mediaArrayDouble(double [] nombreArray) {
		double suma=0;
		for (double pos : nombreArray) {
			suma +=pos;
		}//fin del for 
		System.out.println("La media de los valores del array es: "+suma/(nombreArray.length));
	}//fin de funcion sumaArrayDouble
	
	public static void mediaArrayEntero(int [] nombreArray) {
		int suma=0;
		for (int pos : nombreArray) {
			suma +=pos;
		}//fin del for 
		System.out.println("La media de los valores del array es: "+suma/(nombreArray.length));
	}//fin de funcion sumaArrayDouble
	
	public static void maxArrayDouble(double [] nombreArray) {
		int pos=0;
		int posmax=pos;
		double maximo = nombreArray[pos];
		for (pos=0;pos<nombreArray.length;pos++) {
			if (maximo<nombreArray[pos]) {
				maximo=nombreArray[pos];
				posmax=(pos+1);
			}//fin del if
		}// fin del for
		System.out.println("El valor máximo del array es: "+maximo+", en la posición: "+posmax);
	}// fin de la funcion maxArrayDouble

	public static void maxArrayEntero(int [] nombreArray) {
		int pos=0;
		int posmax=pos;
		int maximo = nombreArray[pos];
		for (pos=0;pos<nombreArray.length;pos++) {
			if (maximo<nombreArray[pos]) {
				maximo=nombreArray[pos];
				posmax=(pos+1);
			}//fin del if
		}// fin del for
		System.out.println("El valor máximo del array es: "+maximo+", en la posición: "+posmax);
	}// fin de la funcion maxArrayEntero
	
	public static void minArrayDouble(double [] nombreArray) {
		int pos=0;
		int posmin=pos;
		double minimo = nombreArray[pos];
		for (pos=0;pos<nombreArray.length;pos++) {
			if (minimo>nombreArray[pos]) {
				minimo=nombreArray[pos];
				posmin=(pos+1);
			}//fin del if
		}// fin del for
		System.out.println("El valor máximo del array es: "+minimo+", en la posición: "+posmin);
	}// fin de la funcion minArrayDouble
	
	public static void minArrayEntero(int [] nombreArray) {
		int pos=0;
		int posmin=pos;
		int minimo = nombreArray[pos];
		for (pos=0;pos<nombreArray.length;pos++) {
			if (minimo>nombreArray[pos]) {
				minimo=nombreArray[pos];
				posmin=(pos+1);
			}//fin del if
		}// fin del for
		System.out.println("El valor máximo del array es: "+minimo+", en la posición: "+posmin);
	}// fin de la funcion minArrayDouble
	
	public static void ordenaArrayDouble(double [] nombreArray) {
		double[] ordenado = new double[nombreArray.length];
	// copio los valores del array original al nuevo array y le aplico el método sort para ordenarlo
		for (int i=0; i<nombreArray.length; i++) {
			ordenado[i]=nombreArray[i];
		}// fin del for que copia los valores del array original
		Arrays.sort(ordenado);
	// mostramos el array ordenadors una vez rellenado de datos
		System.out.println("\nEl array ordenador de menor a mayor es el siguiente...");
		for (int i=0; i<ordenado.length; i++) {
				System.out.println("Valor "+i+" del array: "+ordenado[i]);
		}//fin de for muestra array		
	}//fin funcion ordenaArrayDouble
	
}// fin de la class Respuestas
