
import java.util.Calendar;
import java.util.Date;

public class BlockChainnig {

    public final static char[] letrasEN = {'a', 'e', 'i', 'o', 'u', 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};

    private static String solucion = null;

    private String solucionHexa;

    private String algoritmo;

    private int ceros;
    
    private String cerosString;

    private String textoInicial;

    private long tiempoTotal; //tiempo en ms

    public BlockChainnig(String pAlgoritmo, String pTextoInicial, int pCeros){
        algoritmo = pAlgoritmo;
        ceros = pCeros;
        textoInicial = pTextoInicial;
        cerosString = numCerosToString();
    }

    public void correr(){
        Date inicio = Calendar.getInstance().getTime();

        buscarV(textoInicial, 26, 7);

        Date fin = Calendar.getInstance().getTime();

        tiempoTotal = fin.getTime() - inicio.getTime();
    }

    public String numCerosToString(){
        
        int cerosHexa = ceros/4;
        String aux = "";
        for (int i = 0; i < cerosHexa; i++) {
            aux+="0";
        }
        return aux;

    }


    public void buscarV(String prefijo, int n, int k){

        if(k==0) return;

        for (int i = 0; i < n && solucion == null; i++) {
            String nuevoPrefijo = prefijo + letrasEN[i];

            verificar(nuevoPrefijo);

            buscarV(nuevoPrefijo, n, k - 1);
        }

    }

    public void verificar(String mensaje){

		byte[] mensajeBytes = mensaje.getBytes();
		String hexaActual = null;

        hexaActual = DigestAlg.toHexa(DigestAlg.getDigest(algoritmo, mensajeBytes));

		if(hexaActual.startsWith(cerosString)){
            solucionHexa = hexaActual;
            solucion = mensaje;
		}

	}

    public String darSolucionCalculada(){
        return solucion;
    }

    public String darV(){
        return solucion.replace(textoInicial, "");
    }

    public String darHexa(){
        return solucionHexa;
    }

    public long darTiempoEnMs(){
        return tiempoTotal;
    }
}
