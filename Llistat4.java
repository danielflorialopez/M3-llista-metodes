package cas4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Llistat4 {

	Scanner scan = new Scanner(System.in);

	/**
	 * Es fa una instància de la classe Llistat4 (?) Se separa el programa principal
	 * a un altre mètode anomenat inici()
	 */
	public static void main(String[] args) {

		Llistat4 llista = new Llistat4();
		llista.inici();

	}

	/**
	 * L'estructura del programa està dins d'aquest mètode inici()
	 * 
	 * No té paràmetres No té return
	 * 
	 * Es declara l'array alumnes d'una mida de 25 Es declaren les altres variables:
	 * element fa referència al nom o cognom de l'alumne opcio fa referència a triar
	 * entre "primer" i "darrer" posicio fa referència a la posició de l'array
	 * operacio fa referència al número d'opció del menú num_posicions fa referència
	 * a la distància entre dos elements de dins de l'array
	 * 
	 * S'inicialitza un bucle per a que segueixi iterant mentres el número
	 * d'operacio no correspongui a la de sortir S'usa un switch per a passar el
	 * número d'operacio i triar una opció del menú Es comprova amb altres mètodes
	 * que l'input sigui del tipus desitjat i dins dels paràmetres que es necessiten
	 */
	public void inici() {

		// Scanner scan = new Scanner(System.in);
		String[] alumnes = new String[25];

		String element, opcio;
		int posicio, operacio, num_posicions;

		menu();
		valorsDeProva(alumnes);

		operacio = comprovarOperacio();

		while (operacio != 12) {

			switch (operacio) {

			case 0:

				menu();
				break;

			case 1:

				System.out.println("Has triat: 1.Inserir alumne");
				element = comprovarElement();
				posicio = comprovarPosicio();

				inserir(alumnes, element, posicio);
				break;

			case 2:

				System.out.println("Has triat: 2.Localitzar per nom");
				element = comprovarElement();

				localitzar(alumnes, element);
				break;

			case 3:

				System.out.println("Has triat: 3.Recuperar per posició");
				posicio = comprovarPosicio();

				recuperar(alumnes, posicio);
				break;

			case 4:

				System.out.println("Has triat: 4.Eliminar un alumne");
				posicio = comprovarPosicio();

				eliminar(alumnes, posicio);
				break;

			case 5:

				System.out.println("Has triat: 5.Suprimir tots els alumnes amb el mateix nom");
				element = comprovarElement();
				num_posicions = 0;

				suprimirDada(alumnes, element, num_posicions);
				break;

			case 6:

				System.out.println("Has triat: 6.Buidar llista");
				esborrar(alumnes);
				break;

			case 7:

				System.out.println("Has triat: 7.Primer o darrer");
				opcio = comprovarOpcio();

				primerDarrer(alumnes, opcio);
				break;

			case 8:

				System.out.println("Has triat: 8.Imprimir llista");
				imprimir(alumnes);
				break;

			case 9:

				System.out.println("Has triat: 9.Ordenar");
				ordenar(alumnes);
				break;

			case 10:

				System.out.println("Has triat: 10.Localitzar en ordenada");
				element = comprovarElement();

				localitzarEnOrdenada(alumnes, element);
				break;

			case 11:
				System.out.println("Has triat: 11.Suprimir en ordenada");

				element = comprovarElement();
				num_posicions = 0;

				suprimirEnOrdenada(alumnes, element, num_posicions);
				break;
			}

			operacio = comprovarOperacio();
		}

		scan.close(); // es tanca l'scanner dins d'inici, al final, no al main

	}

	public void menu() {

		System.out.println("Tria un número d'opció: " + "\n0.Menú" + "\n1.Inserir alumne" + "\n2.Localitzar per nom"
				+ "\n3.Recuperar per posició" + "\n4.Eliminar un alumne"
				+ "\n5.Suprimir tots els alumnes amb el mateix nom" + "\n6.Buidar llista" + "\n7.Primer o darrer"
				+ "\n8.Imprimir llista" + "\n9.Ordenar alfabèticament" + "\n10.Localitzar en ordenada"
				+ "\n11.Suprimir en ordenada" + "\n12.Sortir");

	}

	public void inserir(String[] array, String elem, int pos) {

		for (int i = pos; i > 0; i--) {

			if (array[pos - 1] == null) {
				pos -= 1;
			} else {
				break;
			}
		}

		for (int i = array.length - 1; i > pos; i--) {
			array[i] = array[i - 1];
		}
		array[pos] = elem;

		// per a imprimir o fer debug, es pot comentar
		for (int i = 0; i < array.length; i++) {

			if (array[i] != null)
				System.out.println((i + 1) + ". " + array[i]);
		}
	}

	public void localitzar(String[] array, String elem) {

		if (Arrays.asList(array).indexOf(elem) == -1)
			System.out.println("No s'ha trobat cap alumne amb aquest nom.");
		else
			System.out.println("L'alumne està a la posició " + (Arrays.asList(array).indexOf(elem) + 1) + "\n");
	}

	public void recuperar(String[] array, int pos) {

		System.out.println(array[pos]);

	}

	public void eliminar(String[] array, int pos) {

		array[pos] = null;

		// desplaça els elements posteriors una posicio a l'esquerra
		for (int i = pos; i < array.length; i++) {

			if (i == array.length - 1) {
				array[i] = null;
			} else {
				array[i] = array[i + 1];
			}
		}

	}

	public void suprimirDada(String[] array, String elem, int num_pos) {

		for (int i = 0; i < array.length; i++) { // es buiden els elements coincidents

			if (array[i] != null && array[i].equals(elem))
				array[i] = null;
		}

		for (int i = 0; i < array.length; i++) {// es compten les posicions fins el següent not null

			if (array[i] == null) {

				for (int j = i; j < array.length; j++) {

					if (array[j] != null) {

						num_pos = j - i;
						break;

					}
				}

				for (int j = i; j < array.length; j++) {// s'arrosseguen els valors tantes posicions com s'haiguen
														// trobat

					if (j >= array.length - num_pos)
						array[j] = null;
					else
						array[j] = array[j + num_pos];
				}
			}
		}
	}

	public void esborrar(String[] array) {

		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}

	}

	public void primerDarrer(String[] array, String opc) {

		switch (opc) {
		// busca primer element de l'array
		case "primer":

			if (array[0] != null)
				System.out.println(array[0]);
			break;

		// busca l'últim element not null
		case "darrer":
			for (int i = array.length - 1; i >= 0; i--) {

				if (array[i] != null) {

					System.out.println(array[i]);
					break;
				}
			}
			break;
		}

	}

	public void imprimir(String[] array) {

		for (int i = 0; i < array.length; i++) {

			if (array[i] != null)
				System.out.println((i + 1) + ". " + array[i]);

		}
	}

	public void ordenar(String[] array) {

		Arrays.sort(array, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));

	}

	public void localitzarEnOrdenada(String[] array, String elem) {

		Arrays.sort(array, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));

		// System.out.println("L'element inserit està a la posició " +
		// Arrays.asList(array).indexOf(elem));
		if (Arrays.asList(array).indexOf(elem) == -1)
			System.out.println("No s'ha trobat cap alumne amb aquest nom.");
		else
			System.out.println("L'alumne està a la posició " + (Arrays.asList(array).indexOf(elem) + 1) + "\n");
	}

	public void suprimirEnOrdenada(String[] array, String elem, int num_pos) {

		Arrays.sort(array, Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER));

		for (int i = 0; i < array.length; i++) { // es buiden els elements coincidents

			if (array[i] != null && array[i].equals(elem))
				array[i] = null;
		}

		for (int i = 0; i < array.length; i++) {// es compten les posicions fins el següent not null

			if (array[i] == null) {

				for (int j = i; j < array.length; j++) {

					if (array[j] != null) {

						num_pos = j - i;
						break;

					}
				}

				for (int j = i; j < array.length; j++) {// s'arrosseguen els valors tantes posicions com s'haiguen
														// trobat

					if (j >= array.length - num_pos)
						array[j] = null;
					else
						array[j] = array[j + num_pos];
				}
			}
		}
	}

	public int comprovarOperacio() {

		String oper;
		int numOper = -1;
		boolean flag = true;

		// System.out.println("Introdueix el número de l'operació: ");
		// pos = scan2.nextInt();

		while (flag == true || numOper > 12 || numOper < 0) {

			try {

				System.out.println("Introdueix un número d'operació entre el 0 i el 12:");
				oper = scan.next();
				numOper = Integer.parseInt(oper);

				flag = false;

			} catch (NumberFormatException e) {

				System.out.println("No és un número vàlid");
				flag = true;

			}
		}
		// scan.close();

		return numOper;
	}

	public String comprovarElement() {

		String elem;

		System.out.println("Introdueix un alumne: ");
		elem = scan.next();

		while (!elem.matches("[a-zA-Z]+") || elem.length() > 50) {

			System.out.println("Paràmetre invàlid, introdueix un element vàlid: ");
			elem = scan.next();

		}

		// scan2.close();

		return elem;
	}

	public int comprovarPosicio() {

		String pos;
		int posEnter = -1;
		boolean flag = true;

		// System.out.println("Introdueix la posició: ");
		// pos = scan2.nextInt();

		while (flag == true || posEnter > 25 || posEnter < 1) {
			try {
				System.out.println("Introdueix una posició entre l'1 i el 25: ");
				pos = scan.next();
				posEnter = Integer.parseInt(pos);

				flag = false;
			}

			catch (NumberFormatException e) {

				System.out.println("No és un número vàlid.");
				flag = true;
			}
		}

		// scan.close();

		return posEnter - 1;
	}

	public String comprovarOpcio() {

		String opc;

		System.out.println("Introdueix \"primer\" o \"darrer\" per a triar un dels dos elements: ");
		opc = scan.next();

		while (opc.matches("primer") != true && opc.matches("darrer") != true) {

			System.out.println("Paràmetre invàlid. Torna a intentar-ho: ");
			opc = scan.next();

		}

		// scan.close();

		return opc;

	}

	public void valorsDeProva(String[] array) {

		array[0] = "z";
		array[1] = "m";
		array[2] = "b";
		array[3] = "a";

	}
}