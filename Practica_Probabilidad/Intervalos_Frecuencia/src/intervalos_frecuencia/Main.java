/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package intervalos_frecuencia;

/**
 *
 * @author juan vallejos b
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath = "C:\\zeno_sama.txt"; 
        ArrayList<Integer> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                numeros.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("No hay ningun archivo: " + e.getMessage());
            return;
        }

        // maximo valor
        int maximo = Collections.max(numeros);
         
        //minimo valor
        int minimo = Collections.min(numeros);
        
        //rango
        int rango = maximo - minimo;
        
        //numero de clases
        System.out.println("Ingresa el numero de clases:");
        int k = scanner.nextInt();
        int clases = 0;
        clases = k;
        
        
        // amplitud
        int amplitud = (int) Math.ceil((double) rango / clases);
            
        System.out.println("Valor maximo: " + maximo);
        System.out.println("Valor minimo: " + minimo);
        System.out.println("Rango: " + rango);
        System.out.println("Numero de clases (K): " + clases);
        System.out.println("Tamanio del intervalo (Amplitud): " + amplitud);
        System.out.println("==============================================================================");
        System.out.println("                  TABLA DE INTERVALOS DE FRECUENCIA");
        System.out.println("==============================================================================");
        
        // límites de cada clase
        int[] limitesInferiores = new int[clases];
        int[] limitesSuperiores = new int[clases];
        int[] valoresXi = new int[clases];
        int[] frecuenciasNi = new int[clases];
        int[] frecuenciasNiAcumuladas = new int[clases];
        double[] porcentajesHi = new double[clases];
        double[] porcentajesHiAcumulados = new double[clases];

        for (int i = 0; i < clases; i++) {
            limitesInferiores[i] = minimo + (i * amplitud);
            limitesSuperiores[i] = minimo + ((i + 1) * amplitud);
            valoresXi[i] = (limitesInferiores[i] + limitesSuperiores[i]) / 2;

            // Calcular ni
            for (int numero : numeros) {
                if (numero >= limitesInferiores[i] && numero < limitesSuperiores[i]) {
                    frecuenciasNi[i]++;
                }
            }

            // Calcular acumulacion Ni
            if (i == 0) {
                frecuenciasNiAcumuladas[i] = frecuenciasNi[i];
            } else {
                frecuenciasNiAcumuladas[i] = frecuenciasNiAcumuladas[i - 1] + frecuenciasNi[i];
            }

            // Calcular porcentaje hi
            porcentajesHi[i] = (double) frecuenciasNi[i] / numeros.size() * 100;

            // Calcular porcentaje acumulado Hi
            if (i == 0) {
                porcentajesHiAcumulados[i] = porcentajesHi[i];
            } else {
                porcentajesHiAcumulados[i] = porcentajesHiAcumulados[i - 1] + porcentajesHi[i];
            }
        }

        // Imprimir la tabla de frecuencias
        System.out.println("Nro Clases\tLi-1 - Li    \t Xi  \t ni  \t Ni   \t  hi  \t\t   Hi");
       
        int totalNi = 0;
        for (int i = 0; i < clases; i++) {
            totalNi += frecuenciasNi[i];
            System.out.printf("%d\t\t[%d, %d) \t %d\t %d\t %d\t %.2f%%\t\t %.2f%%\n",
                    i + 1, limitesInferiores[i], limitesSuperiores[i], valoresXi[i], frecuenciasNi[i],
                    frecuenciasNiAcumuladas[i], porcentajesHi[i], porcentajesHiAcumulados[i]);
        }
        
        System.out.println("Total\t\t\t\t\t" + totalNi);
    }
}



