package es.studium.practicaT2;

public class Ciclista extends Thread {
	
	int dorsal;
	String nombre;
	String equipo;
	double kilometros;
	int stamina;
	
	public Ciclista (String linea, int puesto) {
		String [] datos = linea.split(", ");
		dorsal = Integer.parseInt(datos[0]);
		nombre = datos[1];
		equipo = datos[2];
		stamina = obtenerStamina(puesto);
		kilometros = 0;
		
	}
	
	public void run () {
		
		double avance;
		
		for (int i = 0; i < 20; i++) {
			
			avance = ((Math.random() * 5) + 1) * ((double) stamina / 12) * 10;
			kilometros += avance;
			System.out.println("Corredor " + dorsal + " ha avanzado " + String.format("%.2f", avance) + " kilómetros y en total lleva " + String.format("%.2f", kilometros) + ".");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	private int obtenerStamina(int puesto) {
		switch (puesto) {
		case 1:
			stamina = 10;
			break;
		case 2:
			stamina = 8;
			break;
		case 3:
			stamina = 7;
			break;
		case 4:
			stamina = 6;
			break;
		case 5:
			stamina = 5;
			break;
		case 6:
			stamina = 4;
			break;
		case 7:
			stamina = 3;
			break;
		case 8:
			stamina = 1;
			break;
		}
		return stamina;
	}
	
	public double getKilometros() {
		return kilometros;
	}
	
}
