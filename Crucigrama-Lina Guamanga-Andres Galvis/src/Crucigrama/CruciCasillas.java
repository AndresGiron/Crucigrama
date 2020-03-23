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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class CruciCasillas.
 * La clase CruciCasillas representa un JtextField que tiene como valor interno letra,
 * las letras de las palabras del crucigrama
 */
public class CruciCasillas extends JTextField implements Serializable{

	/** The estado. */
	private boolean estado = false;
	
	/** The letra. */
	private char letra;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The orientacion. */
	private String orientacion;
	
	
	/**
	 * Gets the x.
	 *Devuelve la coordenada en X de la letra.
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *Te permite modificar la cooordenada en X de la letra.
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *Devuelve la coordenada en Y de la letra.
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *Te permite modificar la cooordenada en Y de la letra.
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Instantiates a new cruci casillas.
	 *El constructor de CruciCasillas le da unos valores predeterminados
	 *a las propiedades visuales del objeto.
	 * @param letrita the letrita
	 */
	CruciCasillas(char letrita){
		
		letra = letrita;
		this.setPreferredSize(new Dimension(35,35));
		this.setFont(new Font("ComicSans", 0, 12));
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setEditable(true);
		this.setBackground(Color.white);	
	}
	
	/**
	 * Gets the letra.
	 *Devuelve la letra
	 * @return the letra
	 */
	public char getLetra() 
	{
		return letra;
	}

	/**
	 * Gets the orientacion.
	 *Devuelve la orientacion "H" o "V" segun sea el caso.
	 * @return the orientacion
	 */
	public String getOrientacion() {
		return orientacion;
	}

	/**
	 * Sets the orientacion.
	 *Te permite modificar la orientacion de la letra.
	 * @param orientacion the new orientacion
	 */
	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}

	/**
	 * Gets the estado.
	 *Devuelve el estado de la letra, true para cuando lo que hay en el campo del JtextField
	 *coincide con el valor de letra y false cuando no coincide
	 * @return the estado
	 */
	public boolean getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *Te permite modificar el valor del estado 
	 * @param estado the new estado
	 */
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
