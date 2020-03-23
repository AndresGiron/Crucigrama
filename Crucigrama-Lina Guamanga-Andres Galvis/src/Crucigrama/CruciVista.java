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

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;


// TODO: Auto-generated Javadoc
/**
 * The Class CruciVista.
 * Clase que corresponde a la vista del programa
 */
public class CruciVista extends JFrame {
	
	/** The juego. */
	private JPanel juego = new JPanel();;
	
	/** The pistas texto. */
	private JTextArea pistasTexto = new JTextArea();
	
	/** The crucigrama. */
	private Panel crucigrama = new Panel();
	
	/** The control. */
	private CruciControlador control = new CruciControlador();
	
	/** The listener. */
	private Listener listener = new Listener();
	
	/** The respuesta. */
	private String respuesta = "";
	
	/**
	 * Instantiates a new CruciVista.
	 * Constructor de la ventana
	 */
	public CruciVista()
	{
		iniciarVista();

		this.setTitle("Crucigrama");
		this.pack();
		//this.setSize(520, 700);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}

	/**
	 * Iniciar vista.
	 * Llama a la funcion acomodar que realiza la configuracion del panel juego y 
	 * posteriormente agrega el panel al container.
	 */
	public void iniciarVista() 
	{		
		Container container = this.getContentPane();
		acomodar();
		container.add(juego);	
	}
	
	/**
	 * Acomodar.
	 * Llama a los metodos de control para construir el ArrayList de palabras,
	 * asignarles coordenadas y agragarlos al panel crucigrama en CENTER con respecto al panel juego,
	 * tambien agrega las pistas a un JtextArea ubicado en SOUTH 
	 * 
	 */
	public void acomodar() {
		
		//METODOS DE CONTROL
		control.transcribir();
		control.trancribirPistas();
		control.darCoordenadasIniciales();
		control.darCoordenadas();
		
		//VARIABLES LOCALES
		String texto = "";
		
		//J-COMPONENTES
		juego.setLayout(new BorderLayout());
		
		//BLOQUE DE COLOCAR PISTAS
		for(int x = 0; x < control.pistas.size();x++){
			
			texto = texto+ " "+control.pistas.get(x) +" \n";
			
		}
		pistasTexto.setText(texto);
		pistasTexto.setFont(new Font("ComicSans", 0, 14));
		pistasTexto.setEditable(false);
		pistasTexto.setBorder(null);
		
		juego.add(pistasTexto, BorderLayout.SOUTH);
		
		//BLOQUE DE CRUCIGRAMA
		
		String partida[]= {"Nueva partida", "Cargar partida"};
		ImageIcon icono = new ImageIcon("src/imagenes/icono.png");
		respuesta = (String)JOptionPane.showInputDialog
				(null, "Seleccione Partida","Opciones",JOptionPane.DEFAULT_OPTION,icono,partida,partida[0]);
		
		if (respuesta.equals("Cargar partida")) {
			
			
			crucigrama.setLayout(new GridBagLayout());
			
			List<List<CruciCasillas>> palabritas = new ArrayList<List<CruciCasillas>>(control.cargar());
			control.palabras = palabritas;
			
		}
		
		crucigrama.setLayout(new GridBagLayout());
		crucigrama.setBackground(Color.GRAY);
		GridBagConstraints constraints = new GridBagConstraints();
		
		int coorX;
		int coorY;
		
		for(int x = 0; x < control.palabras.size(); x++) 
		{
			for(int y = 0; y < control.palabras.get(x).size();y++) 
			{
				
				coorX=control.palabras.get(x).get(y).getX();
				coorY=control.palabras.get(x).get(y).getY();
				
				constraints.gridx = coorX;
				constraints.gridy = coorY;
				constraints.gridwidth = 1;
				constraints.gridheight = 1;
				constraints.fill = GridBagConstraints.BOTH;
				
				control.palabras.get(x).get(y).addActionListener(listener);
				
				if (y==0) {
					
					for(int z = 0; z < control.palabras.size(); z++) 
					{
						for(int w = 0; w < control.palabras.get(z).size();w++) 
						{
							if ( x != coorX && control.palabras.get(z).get(w).getX() == control.palabras.get(x).get(y).getX() && control.palabras.get(z).get(w).getY() == control.palabras.get(x).get(y).getY()) {
								
								Border bordegenerico = BorderFactory.createTitledBorder((x+1)+"");
								
								control.palabras.get(x).get(y).setBorder(bordegenerico);
								control.palabras.get(z).get(w).setBorder(bordegenerico);
								
							}
							
						}
					}
						
				}
				
				crucigrama.add (control.palabras.get(x).get(y), constraints);
			}
		}
		
		juego.add(crucigrama, BorderLayout.CENTER);
		
	}
	
	/**
	 * Reset.
	 * Resetea el programa
	 */
	public void reset(){
		
		CruciVista vista = new CruciVista();
		
	}
	
	
	/**
	 * The Class Listener.
	 * La clase escucha, detecta cuando se le pone una letra a un objeto Crucicasillas(que hereda de TextFiedl)
	 * y procede a compararlo con el valor interno, tambien detecta cuando todas las letras tienen sus correspondientes palabras,
	 * cuando esto ocurre el juego acaba y te pregunta si quieres reiniciar.
	 */
	private class Listener implements ActionListener
	{
		
		/** The coor X. */
		int coorX;
		
		/** The coor Y. */
		int coorY;
		
		/**
		 * Action performed.
		 *
		 * @param evento the evento
		 */
		public void actionPerformed(ActionEvent evento) {
			
			
			for(int x = 0; x < control.palabras.size(); x++) 
			{
				for(int y = 0; y < control.palabras.get(x).size();y++) 
				{
		
					if (evento.getSource() == control.palabras.get(x).get(y)) {
						
						coorX = x;
						coorY = y;
					}
					
				}
			}
			
			if (control.palabras.get(coorX).get(coorY).getText().length() > 1) 
			{
				control.palabras.get(coorX).get(coorY).setText("");
			}
			
			if ((control.palabras.get(coorX).get(coorY).getLetra()+"").equals((control.palabras.get(coorX).get(coorY)).getText())){
				
				control.palabras.get(coorX).get(coorY).removeActionListener(listener);
				control.palabras.get(coorX).get(coorY).setEditable(false);
				control.palabras.get(coorX).get(coorY).setEstado(true);
				control.palabras.get(coorX).get(coorY).setBorder(null);
				
				for(int x = 0; x < control.palabras.size(); x++) 
				{
					for(int y = 0; y < control.palabras.get(x).size();y++) 
					{
						if ( x != coorX && control.palabras.get(x).get(y).getX() == control.palabras.get(coorX).get(coorY).getX() && control.palabras.get(x).get(y).getY() == control.palabras.get(coorX).get(coorY).getY()) {
							
							control.palabras.get(x).get(y).setEstado(true);
							control.palabras.get(x).get(y).setEnabled(false);
							control.palabras.get(x).get(y).setOpaque(false);
							control.palabras.get(coorX).get(coorY).setEstado(true);;
							
						}
						
					}
				}
				
				control.guardar();
				
			}
					
			if (control.ganar() == true) {
				
				System.gc();
				dispose();
				
				ImageIcon icono2 = new ImageIcon("src/imagenes/icono.png");
				int respuesta2 = JOptionPane.showConfirmDialog(juego, "Ganaste, quieres jugar otra partida", "Ganaste", 1,1,icono2);
						
				
				if (respuesta2 == 0) {
					
					reset();
					
				}else {
					
					System.exit(0);	
				}
				
			}
			
		}

	}
	
}
