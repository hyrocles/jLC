import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.net.URISyntaxException;

import org.xml.sax.SAXException;

public class Config{
    
    private String s_balancerURI;
    
    
    public int load(String s_filePath){
        System.out.println("jjL> load Config");
        if(loadLocalInformation(new File(getPath()+s_filePath))!=0){
            System.out.println("jjL> BalancerURI: "+this.s_balancerURI);
        }
        return 0;
    }
    
    public String getPath(){
        try{
            return Config.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }catch(URISyntaxException e){
            return ""; 
        }
    }
    
    public void setLocalInformation(String balancerURI){
        this.s_balancerURI = balancerURI;
    }
    
    public int loadLocalInformation(File obj_file){
        String s_balancer = null;
        
        try{
            XMLDriver obj_xml = new XMLDriver();
            obj_xml.load(obj_file);
            s_balancer = obj_xml.getBalancer();
        }catch(IOException  e){
            return 0;
        }
        
        if(s_balancer != null){
            setLocalInformation(s_balancer);
            return 1;
        }else{
            return 0;
        }
    }
    
    public int setOnlineInformatio(){
        
        return 0;
    }    
}
