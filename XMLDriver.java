import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLDriver{
    
    private Document obj_doc;
    
    public boolean load(File obj_file)throws IOException{
        try{
            DocumentBuilderFactory obj_dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder obj_db = obj_dbf.newDocumentBuilder();
            try{
                this.obj_doc = obj_db.parse(obj_file);
            }catch(SAXException e){
                 return false;
            }
            this.obj_doc.getDocumentElement().normalize();
        }catch(ParserConfigurationException e){
            return false;
        }
        
        return true;
    }
    
    public String getBalancer(){
        if(obj_doc != null){
            return obj_doc.getElementsByTagName("balancer").item(0).getTextContent().trim();
        }else{
            return null;
        }
    }
}
