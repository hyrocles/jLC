import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Config{
    
    private String s_balancerURI;
    
    public boolean load(String s_filePath){
        if(loadLocalInformation(new File(getPath()+s_filePath))){
            loadOnlineInformation(this.s_balancerURI);
            return true;
        }else{
            System.out.println("%> cant load "+this.s_balancerURI);
            return false;
        }
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
    
    public boolean loadLocalInformation(File obj_file){
        String s_balancer = "";
        
        try{
            XMLDriver obj_xml = new XMLDriver();
            if(obj_xml.load(obj_file)){
                s_balancer = obj_xml.getBalancer();
            }
        }catch(IOException  e){
            return false;
        }
        
        if(s_balancer != ""){
            setLocalInformation(s_balancer);
            return true;
        }else{
            return false;
        }
    }
    
    public boolean loadOnlineInformation(String balancerURI){
        if(balancerURI != ""){
            // new Client
            // connect Balancer
            // auth. Methode
            // getRouting
        }
        return false;
    }
}
