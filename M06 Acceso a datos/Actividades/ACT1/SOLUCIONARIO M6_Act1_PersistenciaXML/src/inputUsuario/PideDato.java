package inputUsuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PideDato {
	
// funcion para pedir numero Entero
	public static int pideEntero(String pregunta, int minimo, int maximo) {
		int numdado=minimo;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean numeroNOK=true;
		while (numeroNOK) {
			System.out.println("Introduce el numero entre "+minimo+" y "+maximo+" " + pregunta + "\n");
			try {
				numdado = Integer.parseInt(br.readLine());
				if (numdado>=minimo && numdado<=maximo) {
//					System.out.println("Has asignado el valor " + numdado+" "+ pregunta+"\n");
				numeroNOK=false;
				}else {
				System.out.println("El numero debe ser entre "+minimo+" y "+maximo);	
				}
			} catch (NumberFormatException e) {
				System.out.println("Has introducido un valor no numérico\n");
			} catch (IOException e) {
				System.out.println("Se ha producido un error");
			} //fin del try
		}//fin del while interno
		return numdado;
	}// fin funcion pideEnteno
	
// funcion para pedir numero Decimal
	public static double pideDouble(String pregunta, double minimo, double maximo) {
		double numdado=minimo;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean numeroNOK=true;
		while (numeroNOK) {
			System.out.println("Introduce el numero entre "+minimo+" y "+maximo+" " + pregunta + "\n");
			try {
				numdado = Double.parseDouble(br.readLine());
				if (numdado>=minimo && numdado<=maximo) {
//					System.out.println("Has asignado el valor " + numdado+" "+ pregunta+"\n");
				numeroNOK=false;
				}else {
				System.out.println("El numero debe ser entre "+minimo+" y "+maximo);	
				}
			} catch (NumberFormatException e) {
				System.out.println("Has introducido un valor no numérico\n");
			} catch (IOException e) {
				System.out.println("Se ha producido un error");
			} //fin del try
		}//fin del while interno
		return numdado;
	}// fin funcion pideDouble
		
// funcion para pedir cadena de Texto	
	public static String pideTexto(String pregunta, String stpasado) {
		String stdado=stpasado;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean textoNOK=true;
		while (textoNOK) {
			System.out.println("Introduce el texto " + pregunta + "\n");
			try {
			    stdado = br.readLine();
			    if (stdado.equals("")) {
				stdado=stpasado;
			    }
//			    System.out.println("Has asignado el texto '" + stdado + "' solicitado\n");
			    textoNOK=false;
			} catch (NumberFormatException e) {
				System.out.println("Has introducido un valor numérico\n");
			} catch (IOException e) {
				System.out.println("Se ha producido un error");
			} //fin del try
		}//fin del while interno
		return stdado;
	}// fin funcion pideTexto
	
// funcion para dimensionar un array
	public static int dimensionaArray(int max) {
		int dimension = PideDato.pideEntero("para dimensionar \n"
			+ "la cantidad de valores que contendrá el array",0,max);
		return dimension;
	}// fin función dimensionaArray

	
}// fin de la class pideDato
