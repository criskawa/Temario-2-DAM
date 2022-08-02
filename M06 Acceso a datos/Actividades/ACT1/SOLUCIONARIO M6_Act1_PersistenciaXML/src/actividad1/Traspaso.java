package actividad1;

import java.io.*;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import actividad1.Incidencias.*;
import inputUsuario.*;
import outputUsuario.*;

public class Traspaso {

//	DECLARACION variables objeto.
	static Incidencias listIncidencias = new Incidencias();
	static ArrayList<Incidencia> listIncidencia = new ArrayList<Incidencia>();
	static String sep = File.separator;
	static String ruta = System.getProperty("user.dir") + sep + "Incidencias" + sep;
	static File txt = null; // Objeto File que guardara el fichero incidencias.txt
	static FileReader fr = null;
	static BufferedReader br = null;
	static JAXBContext contexto = null;
	static Marshaller marshall = null;
	static File xml = null; // Objeto File que corresponde al fichero incidencias.xml
	static XPath xpath = null; //Objeto Xpath que permite la busqueda por el fichero XMl
	static String laBusqueda="";
	static String loEncontrado="";
	static Document xmlDoc = null;
	static XPathExpression xExpr = null;
	static Object match = null;
	static NodeList nodeList = null;
	

	public static void main(String[] args) {

		Mensajes.bienvenida("Actividad 01 de M06");
		
		leerTXT();
		//escribirXML();
		String nodo = PideDato.pideTexto("para el Nodo en qué buscar (Enter para: 'destino')", "destino");
		String destinatario = PideDato.pideTexto("del valor a buscar (Enter para: 'jramirez')", "jramirez");
		buscarIncidencias(nodo, destinatario);
		
		Mensajes.despedida();;

	} // fin del main

	/* funcion de lectura del fichero incidencas.txt
	 * 1- compruebo si existe
	 * 2- declaro e inicializo una nueva incidencia "unaIncidencia"
	 * 3- 
	 */
	public static void leerTXT() {

		try {
			txt = new File(ruta + "incidencias.txt");

			if (!txt.exists()) {
				System.out.println("Vaya, el fichero 'incidencias.txt' no esta en su ubicacion");
			} else {

				fr = new FileReader(txt);
				br = new BufferedReader(fr);
			
				String hayIncidencia;

				while ((hayIncidencia = br.readLine()) != null) {
					Incidencia unaIncidencia = new Incidencia();
					unaIncidencia.setFecha(hayIncidencia.substring(2, 21));
					String od = hayIncidencia.substring(22); //od-> Origen y Destino
					String[] odSeparado = od.split(" ");
					unaIncidencia.setOrigen(odSeparado[0]);
					unaIncidencia.setDestino(odSeparado[1]);
					unaIncidencia.setDetalle(br.readLine());
					unaIncidencia.setTipo(br.readLine());
					listIncidencia.add(unaIncidencia);
				}
				/*
				 //recorro y muestro la lista de incidencis para comprobar esten agregadas
				 for(Incidencia laIncidencia : listIncidencia) {
					 System.out.println(laIncidencia); 
				 }
				 */
				 listIncidencias.setIncidencias(listIncidencia);
				 
			}// fin del condicional si existe fichero .txt
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// En el finally cerramos el fichero
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}// fin de finally
	}// fin funcion lecturaTXT


	// funcion que genera el XML y guarda los datos de Incidencias en el XML.
	public static void escribirXML() {
    	try {
	        // Creamos el contexto JAXBContext para nuestra clase incidencias
	        contexto = JAXBContext.newInstance(Incidencias.class);
	        // Declaramos el serializador
	        marshall = contexto.createMarshaller();
	        // Fichero que vamos a generar
	        xml = new File(ruta + "incidencias.xml");
	        //Hacemos que escriba los datos de Incidencias con formato XML
	        marshall.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        // Guardamos el XML en el fichero destino
	        marshall.marshal(listIncidencias, xml);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//fin de la funcion escribirXML
	
	//funcion que busca las incidencias de un Origen concreto usando Xpath
	public static void buscarIncidencias(String nodo, String destinatario) {
		
		laBusqueda ="/incidencias/incidencia["+nodo+"='"+destinatario+"']";
		
		try {
			// Abrimos el fichero XML
            xpath = XPathFactory.newInstance().newXPath() ;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            xmlDoc = factory.newDocumentBuilder().parse(new InputSource(new FileInputStream(ruta + "incidencias.xml")));
			xExpr = xpath.compile(laBusqueda);
            match =  xExpr.evaluate(xmlDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) match;
        	
            loEncontrado = "\nEn el fichero XML de Incidencias he encontrado " + nodeList.getLength() + " resultados";
        	loEncontrado = loEncontrado + "\nque coinciden con: " + laBusqueda;
            
        	for (int i = 0; i < nodeList.getLength(); i++) {
                loEncontrado = loEncontrado +"\n\n---"+ nodeList.item(i).getNodeName() +" num: "+(i+1)+"-------------------------"; 
                loEncontrado= loEncontrado + "\n Incidencia de " + nodeList.item(i).getAttributes().getNamedItem("fechahora");
                loEncontrado= loEncontrado + "\n Origen: " + nodeList.item(i).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                loEncontrado= loEncontrado + "\n Destino: " + nodeList.item(i).getChildNodes().item(3).getChildNodes().item(0).getNodeValue();
                loEncontrado= loEncontrado + "\n Detalle: " + nodeList.item(i).getChildNodes().item(5).getChildNodes().item(0).getNodeValue();
                loEncontrado= loEncontrado + "\n Tipo: " + nodeList.item(i).getChildNodes().item(7).getChildNodes().item(0).getNodeValue();
            }
            System.out.println(loEncontrado);
			
		} catch (Exception e) {
            System.out.println(e.getMessage());
        }
		
	} // fin funcion de busqueda con xpath
    
} // fin clase Traspaso
