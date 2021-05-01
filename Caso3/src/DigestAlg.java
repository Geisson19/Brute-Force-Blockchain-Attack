import java.security.MessageDigest;

public class DigestAlg {
    

    public static byte[] getDigest(String algoritmo, byte[] buffer){
        try {
            MessageDigest digest = MessageDigest.getInstance(algoritmo);
            digest.update(buffer);
            return digest.digest();      
        } catch (Exception e) {
            return null;
        }
    }

    public static String toHexa( byte[] byteArray){

        String salida = "";

        for (int i = 0; i < byteArray.length; i++) {
            if((byteArray[i] & 0xff) <= 0xf){
                salida += "0";
            }
            salida += Integer.toHexString(byteArray[i] & 0xff).toLowerCase();
        }
        
        return salida;

    }
}
