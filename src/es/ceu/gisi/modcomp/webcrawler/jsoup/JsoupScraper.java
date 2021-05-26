/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.ceu.gisi.modcomp.webcrawler.jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Esta clase encapsula toda la lógica de interacción con el analizador Jsoup.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JsoupScraper {

    public final Document doc;
    String nextline = System.getProperty("line.separator");

    /**
     * Este constructor crea un documento a partir de la URL de la página web a
     * analizar.
     *
     * @param url Una URL que apunte a un documento HTML (p.e.
     *            http://www.servidor.com/index.html)
     * @throws java.io.IOException
     */
    public JsoupScraper(URL url) throws IOException {
        // La variable deberá inicializarse de alguna manera utilizando una URL...
        // De momento, se inicializa a null para que compile...
        doc = null;
    }

    /**
     * Este constructor crea un documento a partir del contenido HTML del String
     * que se pasa como parámetro.
     *
     * @param html Un documento HTML contenido en un String.
     * @throws java.io.IOException
     */
    public JsoupScraper(String html) throws IOException {
        doc = Jsoup.parse(html);
    }

    /**
     * Realiza estadísticas sobre el número de etiquetas de un cierto tipo.
     *
     * @param etiqueta La etiqueta sobre la que se quieren estadísticas
     *
     * @return El número de etiquetas de ese tipo que hay en el documento HTML
     */
    public int numEtiqueta(String etiqueta) {
        // nos cuenta el numero de etiquetas que tenemos en el documento
        // Habrá que programarlo..
        Elements etiquetas = doc.select(etiqueta);
        int n = etiquetas.size();
        return n;
    }

    /**
     * Obtiene todos los hiperenlaces que se encuentran en el documento creado.
     *
     * @return Una lista con todas las URLs de los hiperenlaces
     */
    public List<String> obtenerHiperenlaces() throws IOException {
        // Los hiperenlaces debemos guardarlos en un arrayList
        ArrayList<String> hipenlaces = new ArrayList<>();
        File doc = new File("Jsoup_Link.txt");
        doc.delete();
        File newdoc = new File("Jsoup_Link.txt");
        //nuevo documento donde se escribiran los enlaces
        FileWriter fw = new FileWriter(newdoc, false);
        Elements links;
        links = doc.select("a[href]");
        //Con este bucle for conseguimos introducir los links 
        //en el array y escribirlos en el doc.
        for (Element link : links) {

            hipenlaces.add(link.attr("href"));
            fw.write(link.attr("href") + nextline);
        }
        
        //Borra los elementos nulos del ArrayList
        hipenlaces.removeAll(Arrays.asList(null, ""));

        fw.close();
        
        //devuelve el arraylist
        return hipenlaces;
    }

    /**
     * Obtiene todos los hiperenlaces de las imágenes incrustadas.
     *
     * @return Una lista con todas las URLs de los hiperenlaces
     */
    public List<String> obtenerHiperenlacesImagenes() throws IOException{
        //array donde se guardarán los enlaces img
        ArrayList<String> hipenlacesimg = new ArrayList<String>();
        File doc = new File("Jsoup_IMGLink.txt");
        doc.delete();
        File newdoc = new File("Jsoup_IMG.txt");
        
        FileWriter f2 = new FileWriter(newdoc, false);
        Elements links = doc.select("IMG[src]");
        //mete en el arraylist los links y los escribe en el documento
        for (Element link : links) {

            hipenlacesimg.add(link.attr("src"));
            f2.write(link.attr("src") + nextline);
        }

        //Borra los elementos nulos del ArrayList
        hipenlacesimg.removeAll(Arrays.asList(null, ""));

        f2.close();

        //devuelve el arraylist
        return hipenlacesimg;
    }


    /**
     * Obtiene la imagen a la que hace referencia LA PRIMERA etiqueta IMG que
     * encontramos.
     *
     * @return El nombre (o ruta) de la primera imagen insertada en el documento
     *         HTML.
     */
    public String obtenerContenidoImg() {
        Element elemento = doc.select("IMG").first();
        //obtine lo siguiente a "src"
        String imagen = elemento.attr("src");
        return imagen;
    }
}
