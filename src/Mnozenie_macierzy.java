import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.File;
import java.lang.NumberFormatException;
import java.util.ArrayList;

public class Mnozenie_macierzy{
	
	static int W, K;
	float[][] macierz = new float[W][K];
	ArrayList<float[][]> macierze = new ArrayList<float[][]>();
	Scanner input = new Scanner(System.in);
	
	//METODA DEKLARUJĄCA MACIERZE I ZAPISUJĄCA JE DO PLIKU
	public Mnozenie_macierzy(int n) throws IOException{
		PrintWriter zapis = new PrintWriter(new FileWriter ("macierz.txt", true));	
		int w = W;
		int k = K;
		boolean niewystarczy = true;
		System.out.println("DEKLARACJA MACIERZY NR " + (n+1) + ": ");
		
		do{
			do{
				if(n==1){
					System.out.println("Ze względu na warunki, które muszą spełniać mnożone macierze przyjęto liczbę wierszy macierzy nr " + (n+1) + " równą liczbie kolumn macierzy nr " + (n) + ": " + K);				
					w = K;
				}else{
					do{
						try{
							System.out.print("Liczba wierszy macierzy " + (n+1) + ": ");				
							w = input.nextInt();
							niewystarczy = false;
						}catch(InputMismatchException e){
							System.out.println("Panie, co Pan! Tylko liczby naturalne! Wpuścić chamstwo na salony! Jeszcze raz!");
							input.next();
						}	
					} while(niewystarczy);
				}
				if(w<=0){
					System.out.println("Panie! Liczba wierszy chyba musi być dodatnia, nie?! Jeszcze raz!");
				}
			}while(w<=0);
			if(w>100){
				System.out.println("Niestetyż, można deklarować maksymalnie macierz 100x100.");
			}
		}while(w>100);
		
		
		niewystarczy = true;
		
		do{
			do{
				do{
					try{
						System.out.print("Liczba kolumn macierzy " + (n+1) + ": ");				
						k = input.nextInt();
						niewystarczy = false;	
					}catch(InputMismatchException e){
						System.out.println("Panie, co Pan! Tylko liczby naturalne! Wpuścić chamstwo na salony! Jeszcze raz!");
						input.next();
					}
					} while(niewystarczy);
				if(k<=0){
					System.out.println("Panie! Liczba wierszy chyba musi być dodatnia, nie?! Jeszcze raz!");
				}
			}while(k<=0);
			if(k>100){
				System.out.println("Niestetyż, można deklarować maksymalnie macierz 100x100.");
			}
		}while(k>100);
		
		
		K = k;
		
		zapis.println("------------------");
		zapis.println("Macierz nr " + (n+1) + ":");
		zapis.println("------------------");
		
		float[][] matrix = new float[w][k];
		
		for (int i = 0; i<matrix.length; i++){
			for (int j = 0; j<matrix[i].length; j++){
				niewystarczy = true;
				do{
					try{
						System.out.print("Wprowadź wartość a" + (i+1) + (j+1) + " (wiersz " + (i+1) + " kolumna " + (j+1) + "): ");
						matrix[i][j] = input.nextFloat();
						niewystarczy = false;
					}catch(InputMismatchException e){
						System.out.println("Panie, co Pan! Tylko liczby! Wpuścić chamstwo na salony! Jeszcze raz! (Separatorem dziesiętnym jest przecinek.)");
						input.next();
					}
					if(matrix[i][j]>3.40282346638528860e+38 || matrix[i][j]<-3.40282346638528860e+38){
						System.out.println("Podana wartość musi mieścić się w przedziale +/- 3.40282346638528860e+38.");
						matrix[i][j] = 0;
						niewystarczy = true;
					}
				} while(niewystarczy);
				zapis.print(matrix[i][j] + " ");
			}
			zapis.println();
		}
		
		zapis.println("");	
		zapis.close();
		macierz = matrix;
	}
	
	//METODA DRUKUJĄCA MACIERZE W PROGRAMIE
	public void wydruk(int n){
		float[][] matrix = macierz;
		System.out.println("Macierz " + (n+1) + " wygląda następująco:");
		for (int i=0; i<matrix.length; i++){
			for (int j=0; j<matrix[i].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}	
	}
	
	//METODA WCZYTUJĄCA MACIERZE DO TABLIC
	public void odczyt() throws FileNotFoundException{
		Scanner plik = new Scanner(new File("macierz.txt"));
		float[][][] macPom = new float[2][100][100];
		String linijka;
		plik.nextLine();
		plik.nextLine();
		for(int n = 0; n<2; n++){
			int i = 0;
			int j = 0;
			int w = 0;
			int k = 0;
			for (int POM = 0; POM<3; POM++){
				plik.nextLine();		
			}
			linijka = plik.nextLine();
			Scanner linia = new Scanner(linijka);
			while(!(linijka.equals("")) == true){
				linia = new Scanner(linijka);
				while(linia.hasNext()){
					try{
						macPom[n][i][j] = Float.parseFloat(linia.next());
						j++;
						k = j;
					}catch(NumberFormatException e){
					}
				}
				j = 0;
				i++;
				linijka = plik.nextLine();
			}									
			w = i;
			i = 0;
			float[][] matrix = new float[w][k];
			for(int a = 0; a < w; a++){
				for (int b = 0; b < k; b++){
					matrix[a][b] = macPom[n][a][b];
				}
			}
			macierze.add(matrix);
			if(n==1){
				System.out.println();
			}
			System.out.println("WCZYTANO MACIERZ NR " + (n+1) + ":");
			for(int a = 0; a < macierze.get(n).length; a++){
				for (int b = 0; b < macierze.get(n)[a].length; b++){
					System.out.print(macierze.get(n)[a][b] + " ");
				}
				System.out.println();
			}
		linia.close();
		}
	plik.close();
	}
	
	//METODA MNOŻĄCA MACIERZE I DOPISUJĄCA MACIERZ WYNIKOWĄ DO PLIKU
	public void mnozenie () throws IOException{
		int w = macierze.get(0).length;
		int k = macierze.get(1)[0].length;
		float[][] wynik = new float[w][k];
		PrintWriter zapis = new PrintWriter(new FileWriter ("macierz.txt", true));
		zapis.println("------------------");
		zapis.println("Macierz wynikowa:");
		zapis.println("------------------");
		System.out.println("MACIERZ WYNIKOWA WYGLĄDA NASTĘPUJĄCO:");
		for(int a=0; a<wynik.length; a++){
			for(int b=0; b<wynik[a].length; b++){
				for(int i=0; i<macierze.get(0)[0].length; i++){
					wynik[a][b] = wynik[a][b] + macierze.get(0)[a][i] * macierze.get(1)[i][b];
				}
			System.out.print(wynik[a][b] + " ");
			zapis.print(wynik[a][b] + " ");
			}
		System.out.println();
		zapis.println();
		}
		zapis.close();
		System.out.println();
		System.out.println("PS Wszystko zapisało się w pliku tekstowym!");
		System.out.println();
		System.out.println("*** КОНЕЦ ***");
	}
	
	public static void main (String[] args) throws IOException{
		
		//czyszczenie pliku txt
		PrintWriter czyscioch = new PrintWriter("macierz.txt");
		czyscioch.println("MNOŻENIE MACIERZY:");
		czyscioch.println();
		czyscioch.close();
		
		//definicja macierzy nr 1
		Mnozenie_macierzy matrix = new Mnozenie_macierzy(0);
		System.out.println();
		
		//wydruk macierzy nr 1
		matrix.wydruk(0);
		System.out.println();
		
		//definicja macierzy nr 2
		matrix = new Mnozenie_macierzy(1);
		System.out.println();
		
		//wydruk macierzy nr 2
		matrix.wydruk(1);
		System.out.println();
		
		//odczyt obu macierzy z pliku
		matrix.odczyt();
		System.out.println();
		
		//mnożenie macierzy
		matrix.mnozenie();
	}
}
