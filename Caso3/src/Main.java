import java.util.Scanner;

public class Main{

    private static int nAlgoritmo;

    private static String[] algoritmos = {"SHA-256", "SHA-512"};

    private static String algoritmo;

    private static String transaccion;

    private static int numCeros;

    private static BlockChainnig blockChainnig;

    private static void verificarDatos(int nAlgoritmo, String transaccion, int numCeros) throws Exception{

        if(transaccion.length() > 32){
            throw new Exception("El tamaño de la transacción no puede ser mayor a 32 caracteres.");
        }

        if(nAlgoritmo != 1 && nAlgoritmo != 2){
            throw new Exception("Ingrese un valor válido por favor (1 o 2)");
        }

        int[] numCerosPosibles = {20,24,28,32,36};
        boolean bien = false;

        for (int j = 0; j < numCerosPosibles.length && !bien; j++) {
            if(numCeros == numCerosPosibles[j]){
                bien = true;
            }
        }

        if(!bien){
            throw new Exception("Número de ceros inválido, intentelo nuevamente con uno de los siguientes valores: 20,24,28,32,36");
        }
        
    }

    private static void mostrarDatosIngresados(String algoritmo, String transaccion, int numCeros){

        System.out.println("======================================" );
        System.out.println("         - Datos ingresados -         " );
        System.out.println("Algoritmo: " + algoritmo                );
        System.out.println("Datos de la transaccion: " + transaccion);
        System.out.println("Número de ceros: " + numCeros           );
        System.out.println("======================================" );

    }

    private static void entrada() throws Exception{

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el número del algoritmo que quiere usar [1]. para SHA-256 o [2]. para SHA-512)");
        nAlgoritmo = scan.nextInt();
        System.out.println("Ingrese la cadena de datos (máximo 32 caracteres)");
        transaccion = scan.next();
        System.out.println("Ingrese el número de ceros (20,24,28,32 o 36)");
        numCeros = scan.nextInt();
        scan.close();
        
        verificarDatos(nAlgoritmo, transaccion, numCeros);

        algoritmo = algoritmos[nAlgoritmo-1];
        mostrarDatosIngresados(algoritmo, transaccion, numCeros);
        
    }

    public static void salida(String v, int tiempo, String hexa){

        System.out.println("======================================");
        System.out.println("         - Valores Obtenidos -        ");
        System.out.println("Cadena v obtenida: " + v);
        System.out.println("Salida hexa: " + hexa);
        System.out.println("Tiempo de ejecución: " + tiempo + " segundos");
        System.out.println("======================================");

    }

    public static void main(String[] args) throws Exception{
        entrada();
        blockChainnig = new BlockChainnig(algoritmo, transaccion, numCeros);
        System.out.println(" <> Procesando, por favor espere. <>");
        blockChainnig.correr();
        String v = blockChainnig.darV();
        int tiempoSeg = (int) blockChainnig.darTiempoEnMs()/1000;
        String hexa = blockChainnig.darHexa();
        salida(v, tiempoSeg, hexa);
    }

}