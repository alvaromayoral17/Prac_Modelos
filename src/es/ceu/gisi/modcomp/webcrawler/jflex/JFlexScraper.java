package es.ceu.gisi.modcomp.webcrawler.jflex;

import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Tipo;
import es.ceu.gisi.modcomp.webcrawler.jflex.lexico.Token;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Esta clase encapsula toda la lógica de interacción con el parser HTML.
 *
 * @author Sergio Saugar García <sergio.saugargarcia@ceu.es>
 */
public class JFlexScraper {
       
    ArrayList<String> enlacesA = new ArrayList();
    ArrayList<String> enlacesIMG = new ArrayList();   
    Stack<String> etiquetasAbiertas = new Stack();
    
    HTMLParser analizador;
    boolean malBalanceado = false;
    
    public JFlexScraper(File fichero) throws FileNotFoundException, IOException {
        Reader reader = new BufferedReader(new FileReader(fichero));
        analizador = new HTMLParser(reader);
    Token tk = analizador.nextToken();
        
        int estado = 0;
        
        boolean etiquetaA = false;
        boolean valorEsHREF = false;
        boolean etiquetaIMG = false;
        boolean valorEsSRC = false;
        
        while (tk != null){
        
            switch (estado){
                case 0:
                    //Sin haber leido una etiqueta de open 
                    if(tk.getTipo() == Tipo.OPEN){
                    estado = 1;
                    }
                    break;
                case 1:
                    //Habiendo leido una etiqueta de open
                    if(tk.getTipo() == Tipo.PALABRA){
                    estado = 2;
                    etiquetasAbiertas.push(tk.getValor().toLowerCase());
                    if(tk.getValor().equalsIgnoreCase("a")){
                    etiquetaA = true;
                    }
                    else if (tk.getValor().equalsIgnoreCase("img")){
                        etiquetaIMG = true;
                    }
                            
                    } else if (tk.getTipo() == Tipo.SLASH){
                    estado = 6;
                    }
                    break;
                case 2: 
                    //Leemos si es un atributo-valor o el fin de una etiqueta
                    if(tk.getTipo() == Tipo.PALABRA){
                    estado = 3;
                    if(etiquetaA){
                    if(tk.getValor().equalsIgnoreCase("href")){
                    valorEsHREF = true;
                        }
                    } else if (etiquetaIMG){
                        //tk.getValor.equalsIgnoreCase("src");
                        valorEsSRC = true;
                        
                    }
                }
                //Por completar
                    break;
                case 3:
                    if (tk.getTipo()== Tipo.IGUAL){
                    estado = 4;
                    break;
                    }
                case 4:
                    //Valor de un atributo
                    if (tk.getTipo == Tipo.VALOR){
                    if(valorEsHREF){
                    enlacesA.add(tk.getValor());
                        }
                    else if (valorEsSRC){
                    enlacesIMG.add(tk.getValor());
                    }
                    }    
                    break;
                case 6:
                    if(tk.getTipo == Tipo.PALABRA){
                        if(tk.getValor().equalsIgnoreCase(etiquetasAbiertas.peek())){
                                etiquetasAbiertas.pop();
                        }else{
                            malBalanceado = true;
                        }
                    }
                    
            }
            tk = analizador.nextToken();
        }
    }
    }

    // Esta clase debe contener tu automata programado...
    public ArrayList<String> obtenerHiperenlaces() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public ArrayList<String> obtenerHiperenlacesImagenes() {
        // Habrá que programarlo..
        return new ArrayList<String>();
    }

    public boolean esDocumentoHTMLBienBalanceado() {
        // Habrá que programarlo..
        return true;
        //return !(malBanlanceado && !etiquetasAbiertas.empty());
    }
}
