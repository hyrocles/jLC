
/**
 * ServiceNode.
 * 
 * @author T. Schmalenberg 
 * @version 21.10.2012
 */
public class ServiceNode{
    
    public static void main(String[] args){
        Config obj_config = new Config();
        if(obj_config.load("conf/config.xml")){
            System.out.println("%> no Error");
            //...   
        }
    }
}
