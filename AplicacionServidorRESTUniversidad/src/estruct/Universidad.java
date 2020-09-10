package estruct;

import javax.xml.bind.annotation.XmlRootElement;


public class Universidad {
    
    public Universidad() {
        super();
    }
    
    private static Universidad universidad;
     
     
     private String nit;
     private String nombre;

     
     private Universidad(String nit, String nombre) {
         this.nit = nit;
         this.nombre = nombre;
     }

     public static Universidad getUniversidad(){
         if(universidad == null){
             return new Universidad("", "Unibague");
         }else{
             return universidad;
         }
           
     }
    
     public String getNit() {
         return nit;
     }

     public void setNit(String nit) {
         this.nit = nit;
     }

     public String getNombre() {
         return nombre;
     }

     public void setNombre(String nombre) {
         this.nombre = nombre;
     }
     
   
}
