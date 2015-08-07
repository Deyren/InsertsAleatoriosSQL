/*
 * Dale moreno
 */
package creadordeinsertssql.gestiondearchivos;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author pedruben
 */
class ManejadorDeEventosSAX extends DefaultHandler{

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if(qName.equals("creadordeinserts")){   
            System.out.println("Archivo de Schema: "+attributes.getValue(1));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
           for (int i = start; i < start+length; i++) {
               System.out.print(ch[i]);
        }
    }
    

    
    
    
}
