import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;

import org.w3c.dom.Document;

public class Config{
    
    private Document xml_localInformation;
    
    private void setXml_localInformation(Document localInformation){
    	this.xml_localInformation = localInformation;
    }
    
    private String getPath(){
        try{
            return Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }catch(URISyntaxException e){
            return ""; 
        }
    }
    
    private boolean loadLocalInformation(File obj_file){
        Document xml_tempInformation = null;
        
        try{
            XMLDriver obj_XMLDriver = new XMLDriver();
            xml_tempInformation = obj_XMLDriver.loadFromFile(obj_file);
            if(xml_tempInformation != null){
            	setXml_localInformation(xml_tempInformation);
                return true;
            }
        }catch(IOException  e){
            return false;
        }
		return false;
    }
    
    private boolean loadOnlineInformation(String balancerURI){
        if(xml_localInformation != null){
            // new Client
            // connect Balancer
            // auth. Methode
            // getRouting
        }
        return false;
    }
    
    public boolean load(String s_filePath){
        if(loadLocalInformation(new File(getPath()+s_filePath))){
            loadOnlineInformation("");
            return true;
        }else{
            System.out.println("%> cant load local Information");
            return false;
        }
    }
    
    public String getNodeType(){
    	return "node";
    }
}
