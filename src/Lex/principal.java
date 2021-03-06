/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Lex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author necro
 */
public class principal {
   public static void main(String args[]) throws Exception{
       String path = "src/lex/Lexer.flex";
       String cup="src/lex/Cup.cup";
       generarLexer(path);
       generarCup(cup);
   }    
   
   public static void generarLexer(String path){
       File file = new File(path);
       jflex.Main.generate(file);
   }
   public static void generarCup(String cup) throws IOException, Exception{        
        String[] archivoCup={"-parser","Cup",cup};
        java_cup.Main.main(archivoCup);
        boolean RedirigidoCup = redirigir("Cup.java");
        boolean RedirigidoSym= redirigir("sym.java");
        if(RedirigidoCup && RedirigidoSym){
            System.exit(0);
        }else System.err.println("Falló generar Cup");
     }
     
     public static boolean redirigir(String file) {
        boolean exito = false;
        File archivo = new File(file);
        if (archivo.exists()) {
            Path currentRelativePath = Paths.get("");
            String miLocacion = currentRelativePath.toAbsolutePath().toString()
                    + File.separator + "src" + File.separator
                    + "lex" + File.separator + archivo.getName();
            File archivoAnterior = new File(miLocacion);
            archivoAnterior.delete();
            
            if (archivo.renameTo(new File(miLocacion))) {
                exito = true;
            }else System.err.println("Fallo renombrar");
        }else System.err.println("Fallo redirigir");
        return exito;
    }
}
