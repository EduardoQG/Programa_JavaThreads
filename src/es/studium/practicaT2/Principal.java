package es.studium.practicaT2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Principal {

	public static void main(String[] args) {
		
		ArrayList<Ciclista> ciclistas = new ArrayList<>();
		
		int countUAE = 1, countJumbo = 1, countMovistar = 1, countCofidis = 1, countGroupama = 1, countAstana = 1; 
		int puesto = 0;
		
		// Crear ciclistas con los datos del fichero.
		try {
            File archivo = new File("ciclistas.txt");
            FileReader lectorArchivo = new FileReader(archivo);
            BufferedReader lectorLineas = new BufferedReader(lectorArchivo);
            String linea;
            while ((linea = lectorLineas.readLine()) != null) {
                
            	String equipo = linea.split(", ")[2];
            	
            	if(equipo.equals("UAE Emirates")) {
            		puesto = countUAE;
            		countUAE++;
            	} else if (equipo.equals("Jumbo-Visma")) {
            		puesto = countJumbo;
            		countJumbo++;
            	} else if (equipo.equals("Movistar")) {
            		puesto = countMovistar;
            		countMovistar++;
            	} else if (equipo.equals("Cofidis")) {
            		puesto = countCofidis;
            		countCofidis++;
            	} else if (equipo.equals("Groupama-FDJ")) {
            		puesto = countGroupama;
            		countGroupama++;
            	} else if (equipo.equals("Astana")) {
            		puesto = countAstana;
            		countAstana++;
            	} 
            	
            	Ciclista c = new Ciclista(linea, puesto);
                ciclistas.add(c);
            }
            lectorLineas.close();
            lectorArchivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		// Iniciar hilos.
		for (Ciclista ciclista: ciclistas) {
			ciclista.start();
		}
		
		// El hilo principal debe esperar a que acaben los hilos de los ciclistas.
		for (Ciclista ciclista: ciclistas) {
			try {
	            ciclista.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
		}
		
        // Ordenar el ArrayList.
		Collections.sort(ciclistas, Comparator.comparingDouble(Ciclista::getKilometros).reversed());
		 
		// Borrar el texto del archivo y reescribirlo con el ranking nuevo.
		try {
			 File archivoPrueba = new File ("ciclistas.txt");
			 BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoPrueba));
			 escritor.write("");
			 
			 int contador = 1;
			 System.out.println("Clasificación final:");
			 for (Ciclista ciclista: ciclistas) {
				 escritor.write(contador + ", " + ciclista.nombre + ", " + ciclista.equipo);
		         escritor.newLine();
				 System.out.println(contador + ": " + ciclista.dorsal + "-            " + ciclista.nombre + " " + String.format("%.2f", ciclista.kilometros) + " " + " kilómetros.");
				 contador++;
			 }
			 
			 escritor.close();
			 
		} catch (IOException e) {
	            e.printStackTrace();
	    }
		 
	}

}
