package SDTP1;

/**
 * Esta classe implementa todas as funcionalidades de input através do teclado.
 * Referência: classe fornecida pela Professora Paula Prata na Unidade Curricular 
 * de Programação de Dispositivos Móveis no ano letivo 2019/2020.  
 * @author Adriana, Daniel e Fernando
 * @version 1.0
 */
import java.io.*;

public class Ler {
    public static String umaString (){
        String s = "";
        try{
        BufferedReader in = new BufferedReader ( new InputStreamReader (System.in));
        s= in.readLine();
        }
        catch (IOException e){
        System.out.println("Erro ao ler fluxo de entrada.");
        }
    return s;
    }
    
    public static int umInt(){
        while(true){
            try{
                return Integer.valueOf(umaString().trim()).intValue();
            }
            catch(Exception e){
                System.out.println("Não é um inteiro válido!!!");
            }
        }
    }
    
     public static double umDouble(){
        while(true){
            try{
                return Double.valueOf(umaString().trim()).doubleValue();
            }
            catch(Exception e){
                System.out.println("Não é um double válido!!!");
            }
        }
    }
    
    public static float umFloat(){
        while(true){
            try{
                return Float.valueOf(umaString().trim()).floatValue();
            }
            catch(Exception e){
                System.out.println("Não é um float válido!!!");
            }
        }
    }

    public static boolean umBoolean(){
        while(true){
            try{
                return Boolean.valueOf(umaString().trim()).booleanValue();
            }
            catch(Exception e){
                System.out.println("Não é um boolean válido!!!");
            }
        }
    }
    
    public static char umChar(){
        while(true){
            try{
                return umaString().charAt(0);//pega o primeiro caratere 
            }
            catch(Exception e){
                System.out.println("Não é um char válido!!!");
            }
        }
    }
    
     public static byte umByte(){
        while(true){
            try{
                return Byte.valueOf(umaString().trim()).byteValue();
            }
            catch(Exception e){
                System.out.println("Não é um boolean válido!!!");
            }
        }
    }

    public static short umShort(){
        while(true){
            try{
                return Short.valueOf(umaString().trim()).shortValue();
            }
            catch(Exception e){
                System.out.println("Não é um short válido!!!");
            }
        }
    }
    
   public static long umLong(){
        while(true){
            try{
                return Long.valueOf(umaString().trim()).longValue();
            }
            catch(Exception e){
                System.out.println("Não é um boolean válido!!!");
            }
        }
    }

}