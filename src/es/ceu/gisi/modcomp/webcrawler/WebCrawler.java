package es.ceu.gisi.modcomp.webcrawler;

import es.ceu.gisi.modcomp.webcrawler.jflex.JFlexScraper;
import es.ceu.gisi.modcomp.webcrawler.jsoup.JsoupScraper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.net.URL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta aplicación contiene el programa principal que ejecuta ambas partes del
 * proyecto de programación.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class WebCrawler {

    public static void main(String[] args) throws IOException {
        JFlexScraper jflex = new JFlexScraper(new File("C:/Modelos/gisi-modcomputacion/test/es/ceu/gisi/modcomp/webcrawler/jflex.html"));
        ArrayList<String> enlacesA = jflex.obtenerHiperenlaces();
        ArrayList<String> enlacesIMG = jflex.obtenerHiperenlacesImagenes();
        System.out.println(enlacesA);
        System.out.println(enlacesIMG);
        
 //2ª PARTE
        JsoupScraper jsoup = new JsoupScraper(new URL());
        // Deberá inicializar JFlexScraper con el fichero HTML a analizar
        // Y creará un fichero con todos los hiperenlaces que encuentre.
        // También deberá indicar, mediante un mensaje en pantalla que
        // el fichero HTML que se ha pasado está bien balanceado.

        // Deberá inicializar JsoupScraper con la DIRECCIÓN HTTP de una página
        // web a analizar. Creará un fichero con todos los hiperenlaces que
        // encuentre en la página web. También obtendrá estadísticas de uso 
        // de las etiquetas HTML más comunes: a, br, div, li, ul, p, span, table, td, tr
            
    }
}
