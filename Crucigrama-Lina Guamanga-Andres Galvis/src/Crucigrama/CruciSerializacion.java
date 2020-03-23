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

import java.io.*;
import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class CruciSerializacion.
 * Clase que me permite manejar archivos.txt, serializar y deserializar objetos
 */
public class CruciSerializacion implements Serializable { 
	
	/** The palabras. */
	private List<String> palabras = new ArrayList<String>();
	
	/** The pistas. */
	private List<String> pistas = new ArrayList<String>();
    
    /** The file input. */
    private FileInputStream fileInput;
    
    /** The file output. */
    private FileOutputStream fileOutput;
    
    /** The object input. */
    private ObjectInputStream objectInput;
    
    /** The object output. */
    private ObjectOutputStream objectOutput;

	/**
	 * Gets the palabras.
	 *Metodo que retorna la lista de palabras.
	 * @return the palabras
	 */
	public List<String> getPalabras() {
		return palabras;
	}

	/**
	 * Gets the pistas.
	 *Metodo que retorna la lista de pistas.
	 * @return the pistas
	 */
	public List<String> getPistas() {
		return pistas;
	}

	/**
	 * Leer.
	 *Metodo que obtiene de un archivo de texto plano las coordenadas, la orientacion,
	 *las palabras y las pistas. 
	 * @param archivoEntrante 
	 */
	public void leer(String archivoEntrante) {
		
		String lineas;
		FileReader fileReader;
		BufferedReader bufferedReader = null;
		
		try {
			
			fileReader = new FileReader(archivoEntrante);
			bufferedReader = new BufferedReader(fileReader);
			
			while( (lineas=bufferedReader.readLine()).equals("-") == false) {
				
				palabras.add(lineas);
			}
			
			while( (lineas=bufferedReader.readLine())!=null) {
				
				pistas.add(lineas);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	/**
	 * Cargar.
	 *Metodo para deserializar el archivo "guardado" para obtener
	 *el objeto que constituye la lista de palabras, que a su vez son listas de casillas
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<List<CruciCasillas>> cargar() {
		
		List<List<CruciCasillas>> palabras = null;
		   
		   try {
			fileInput = new FileInputStream("src/Archivos/guardado");
			objectInput = new ObjectInputStream(fileInput);
			palabras = (List<List<CruciCasillas>>) objectInput.readObject();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				objectInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		   return palabras;		
	}
	
	/**
	 * Guardar.
	 *Metodo que guarda el arreglo donde estan las palabras(palabras tambien es un arreglo)
	 *y dentro de las palabras estan las casillas.
	 * @param palabrasEntrante 
	 */
	public void guardar(List<List<CruciCasillas>> palabrasEntrante) {
		
    	try {
			fileOutput = new FileOutputStream("src/Archivos/guardado");
			objectOutput = new ObjectOutputStream(fileOutput);
			
			objectOutput.writeObject(palabrasEntrante);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				objectOutput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}
