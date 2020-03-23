/**
 * JUEGO CRUCIGRAMA
 * 
 * PROGRAMACION INTERACTIVA
 * 
 * DOCENTE: PAOLA RODRIGUEZ
 *  
 * @author Andres Felipe Galvis Perez 1842504
 * @author Lina Marcela Guamanga Meneses 1730223
 * @version 3.5 16/03/2020
 * 
 */
package Crucigrama;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class CruciControlador.
 * Clase que llama a los metodos de la clase CruciSerializacion para extraer desde un archivo plano las palabras y las pistas.
 * Convierte cada palabra en un ArrayList de objetos CruciCasillas y les asigna una serie de coordenadas para despues acomodar
 * las casillas en la vista.
 */
public class CruciControlador {
	
	
	/** The palabras. */
	List<List<CruciCasillas>> palabras;
	
	/** The pistas. */
	List<String> pistas = new ArrayList<String>();
	
	/** The manejador archivos. */
	private CruciSerializacion manejadorArchivos;
	
	/**
	 * Trancribir pistas.
	 * Transcribe un arreglo de String de manejadorArchivos a un arreglos de String en esta clase
	 * para un manejo mas sencillo de pistas.
	 */
	public void trancribirPistas() {
		
		for (int x = 0; x<manejadorArchivos.getPistas().size();x++) {
			
			pistas.add(manejadorArchivos.getPistas().get(x));	
		}
	}
	
	
	/**
	 * Transcribir.
	 * Metodo que trae desde "manejadorArchivos" el ArrayList palabras(que contiene String) y separa las palabras en 
	 * casillas
	 */
	public void transcribir() 
	{
		palabras = new ArrayList<List<CruciCasillas>>();
		manejadorArchivos = new CruciSerializacion();
		int contador = 0;
		
		manejadorArchivos.leer("src/Archivos/crucigrama.txt");
		
		for(int x = 0;x<manejadorArchivos.getPalabras().size();x++){
			
			contador++;
			
			if (contador == 4) {
			
				List<CruciCasillas> generico = new ArrayList<CruciCasillas>();
				
				for(int y = 0; y < manejadorArchivos.getPalabras().get(x).length();y++)
				{
						generico.add(new CruciCasillas(manejadorArchivos.getPalabras().get(x).charAt(y)));
						
						
						if (y == (manejadorArchivos.getPalabras().get(x).length() - 1)) 
						{
							palabras.add(generico);
							
						}
						
				}
				contador = 0;
			}
			
		}
			
	}
	
	/**
	 * Dar coordenadas iniciales.
	 * Extrae las coordenadas de las letras iniciales de cada palabra 
	 * y se las asigna.
	 */
	
	
	public void darCoordenadasIniciales() 
	{	
		int coordenadasX = 0;
		int coordenadasY = 1;
		int orientacion = 2;
		
		for (int x = 0; x<palabras.size();x++) 
		{
			palabras.get(x).get(0).setX(Integer.parseInt(manejadorArchivos.getPalabras().get(coordenadasX)));
			palabras.get(x).get(0).setY(Integer.parseInt(manejadorArchivos.getPalabras().get(coordenadasY)));
			palabras.get(x).get(0).setOrientacion(manejadorArchivos.getPalabras().get(orientacion));
			
			 coordenadasX = coordenadasX +4;
			 coordenadasY = coordenadasY + 4;
			 orientacion = orientacion + 4;
		}
	}	
	
	/**
	 * Dar coordenadas.
	 * Usando las coordenadas de la primera letra de cada palabra como referencia asigna el resto de coordenadas
	 * dependiendo de la orientacion.
	 * 
	 */
	public void darCoordenadas() {
		
		for(int x = 0; x<palabras.size(); x++) {
			
			if (palabras.get(x).get(0).getOrientacion().equals("V")) {
				int coordenadaX = palabras.get(x).get(0).getX();
				int coordenadaY = palabras.get(x).get(0).getY();
				
				for(int y = 1; y < palabras.get(x).size(); y++) 
				{
					coordenadaY++;
					palabras.get(x).get(y).setX(coordenadaX);
					palabras.get(x).get(y).setY(coordenadaY);
					
				}
				
			}
			
			if (palabras.get(x).get(0).getOrientacion().equals("H") ) {
				
				int coordenadaX = palabras.get(x).get(0).getX();
				int coordenadaY = palabras.get(x).get(0).getY();
				
				for(int y = 1; y < palabras.get(x).size(); y++) 
				{
					coordenadaX++;
					palabras.get(x).get(y).setX(coordenadaX);
					palabras.get(x).get(y).setY(coordenadaY);
					
				}
			}
		}
	}
	
	
	
	/**
	 * Ganar.
	 *Metodo que me indica si todos los estados de las casillas del arreglo palabras son true, si es asi, devolver true
	 *de lo contrario devuelve false.
	 * @return true, if successful
	 */
	public boolean ganar(){
		
		boolean estadoVictoria = false;
		
		for(int x = 0; x < palabras.size(); x++) 
		{
			for(int y = 0; y < palabras.get(x).size(); y++) 
			{
				if (palabras.get(x).get(y).getEstado() == false) 
				{
					//System.out.print("estoy aqui");
					x = palabras.size()-1;
					y = palabras.get(x).size() - 1;
					
				}else if (x+1 == palabras.size() && y+1 == palabras.get(x).size() && palabras.get(x).get(y).getEstado() == true) {
					
					estadoVictoria = true;
				}
			}
		}
		return estadoVictoria;
	}
	
	
	/**
	 * Guardar.
	 * Metodo que me permite guardar el ArrayList de palabras usando el objeto "manejadorArchivos".
	 */
	public void guardar() {
		
		manejadorArchivos.guardar(palabras);
		
	}
	
	/**
	 * Cargar.
	 *Metodo que retorna la lista de palabras, usando el manejador de archivos.
	 * @return the list
	 */
	public List<List<CruciCasillas>> cargar(){
		
		List<List<CruciCasillas>> palabras = new ArrayList<List<CruciCasillas>>(manejadorArchivos.cargar());
		
		return palabras;
	}
	

}
