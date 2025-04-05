/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teatromoroventasv2;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author kabes
 */
public class TeatroMoroVentasV2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#0,000");
        int opcionMenu = 0;
        int tEntrada = 0;
        int nEntradas = 0;
        int tTarifa = 0;
        int valorEntrada = 0;
        int edadUsuario = 0;
        // Información para mostrar en pantalla
        String zonas[] = { "VIP", "Platea Baja", "Platea Alta", "Palcos" };
        String tipoTarifa[] = { "Estudiante", "Tercera Edad", "General" };
        // Estudiantes 10% dcto | Tercera edad 15% dcto
        int tarifasGeneral[] = { 30000, 15000, 18000, 13000 };

        // Menú
        for (int i = 0; i < 1;) {
            System.out.println("\n========== Bienvenido al Teatro Moro ==========\n");
            System.out.println("\n==================== Menú ====================\n");
            System.out.println("1. Comprar entrada");
            System.out.println("2. Salir");
            System.out.print("\n> Seleccione una opción: ");
            System.out.println("\n >");
            opcionMenu = sc.nextInt();

            if (opcionMenu == 1) {
                // Muestra mapa y solicita tipo de entrada a usuario
                tEntrada = seleccionaTipoEntrada(sc, zonas, tEntrada);

                // Asignación de tarifas en base a edad
                System.out.println("> Por favor ingrese su edad: ");
                System.out.println("\n >");
                edadUsuario = sc.nextInt();

                tTarifa = defineTarifa(edadUsuario, tTarifa);

                // Solicita el número de entradas
                System.out.print("\n> ¿Cuántas entradas desea comprar? ");
                System.out.println("\n >");
                nEntradas = sc.nextInt();

                valorEntrada = calculaValorEntrada(tarifasGeneral, tEntrada, tTarifa, nEntradas, valorEntrada);

                detalleCompra(zonas, tipoTarifa, tarifasGeneral, tEntrada, tTarifa, nEntradas, valorEntrada, df);

            } else if (opcionMenu == 2) {
                System.out.println("Gracias por visitar el Teatro Moro. ¡Hasta luego!");
                sc.close();
                System.exit(0);
            } else {
                System.out.println("Opción no válida, por favor intente nuevamente.");
            }
        }
    }

    public static int seleccionaTipoEntrada(Scanner sc, String[] zonas, int tEntrada) {
        System.out.println("\n==================== Plano Teatro Moro ====================\n");
        for (int j = 0; j < zonas.length; j++) {// itera en el array de tipos de entrada
            System.out.println((j + 1) + ". [ " + zonas[j] + " ]");
        }
        System.out.println("\n===========================================================");

        System.out.print("\n> Seleccione el tipo de entrada: ");
        tEntrada = sc.nextInt();
        System.out.println(" " + zonas[tEntrada - 1]);

        // Verifica que el tipo de entrada sea correcto
        if (tEntrada == 1 | tEntrada == 2 | tEntrada == 3 | tEntrada == 4) {
            tEntrada = tEntrada - 1; // Ajusta el índice para el array
        } else {
            System.out.println("\n===============================================");
            System.out.println("\nOpción no válida, por favor intente nuevamente.");
            System.out.println("\n===============================================");
        }
        return tEntrada;
    }

    public static int defineTarifa(int edadUsuario, int tTarifa) {
        // Verifica si el usuario es estudiante o tercera edad
        if (edadUsuario < 0) {// Verifica edad negativa
            System.out.println("\nEdad no válida, por favor intente nuevamente.");
            tTarifa = -1;// Tarifa no válida
        } else if (edadUsuario < 18) {
            System.out.println("\nTarifa aplicada: Estudiante | 10% descuento"); // 10% dcto
            tTarifa = 0; // Tarifa Estudiante
        } else if (edadUsuario >= 60) {// 15% dcto
            System.out.println("\nTarifa aplicada: Tercera Edad | 15% descuento"); // 15% dcto
            tTarifa = 1; // Tarifa Tercera Edad
        } else {// Tarifa General
            System.out.println("\nTarifa aplicada: General");
            tTarifa = 2; // Tarifa General
        }
        return tTarifa;
    }

    public static int calculaValorEntrada(int[] tarifasGeneral, int tEntrada, int tTarifa, int nEntradas, int valorEntrada) {
        double descuento = 0.0;
        // Asignación de descuento según tarifa
        if (tTarifa == 0) {
            descuento = 0.90; // 10% dcto
        } else if (tTarifa == 1) {
            descuento = 0.85; // 15% dcto
        } else {
            descuento = 1.0; // Tarifa General
        }

        int intento = 0; 
        
        do {
            var finalEntrada = tarifasGeneral[tEntrada] * descuento;
            valorEntrada = (int)(finalEntrada * nEntradas);
            intento++;
        } while (intento < 1);

        return valorEntrada;
    }

    public static void detalleCompra(String[] zonas, String[] tipoTarifa, int[] tarifasGeneral, int tEntrada,
            int tTarifa, int nEntradas, int valorEntrada, DecimalFormat df) {
        // Muestra el detalle de la compra
        System.out.println("\n==================== Detalle de la compra ====================");
        System.out.println("Tipo de entrada: " + zonas[tEntrada]);
        System.out.println("Número de entradas: " + nEntradas);
        System.out.println("Tarifa: " + tipoTarifa[tTarifa]);
        System.out.println("Valor de la entrada: " + zonas[tEntrada] + " $" + df.format(tarifasGeneral[tEntrada]));
        System.out.println("Descuento Aplicado: $"+ df.format(tarifasGeneral[tEntrada] - valorEntrada));
        System.out.println("Valor total a pagar: $" + df.format(valorEntrada));
        System.out.println("==============================================================");
    }
}
