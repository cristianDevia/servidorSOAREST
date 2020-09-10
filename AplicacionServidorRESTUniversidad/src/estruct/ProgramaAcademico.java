package estruct;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProgramaAcademico {
    
    public ProgramaAcademico() {
        super();
    }
    
     private String nombre;
     private String codigo;
     private String registroCalificado;
     private int duracionMeses;
     private String modalidad;

     public ProgramaAcademico(String nombre, String codigo, String registroCalificado, int duracionMeses, String modalidad) {
         this.nombre = nombre;
         this.codigo = codigo;
         this.registroCalificado = registroCalificado;
         this.duracionMeses = duracionMeses;
         this.modalidad = modalidad;
     }

     public String getNombre() {
         return nombre;
     }

     public void setNombre(String nombre) {
         this.nombre = nombre;
     }

     public String getCodigo() {
         return codigo;
     }

     public void setCodigo(String codigo) {
         this.codigo = codigo;
     }

     public String getRegistroCalificado() {
         return registroCalificado;
     }

     public void setRegistroCalificado(String registroCalificado) {
         this.registroCalificado = registroCalificado;
     }

     public int getDuracionMeses() {
         return duracionMeses;
     }

     public void setDuracionMeses(int duracionMeses) {
         this.duracionMeses = duracionMeses;
     }

     public String getModalidad() {
         return modalidad;
     }

     public void setModalidad(String modalidad) {
         this.modalidad = modalidad;
     }
     
  
      public String insert()
      {
        System.out.println("INSERT INTO PROGRAMAS VALUES("+codigo+",'"+nombre+",'"+registroCalificado+",'"+duracionMeses+",'"+modalidad+"',");
        return "INSERT INTO PROGRAMAS VALUES("+codigo+",'"+nombre+",'"+registroCalificado+",'"+duracionMeses+",'"+modalidad+"',";
      }
     
     public String update(){
   
         return "UPDATE PROGRAMAS SET CODIGO="+ codigo+","
                 + "NOMBRE='"+nombre+"',"
                 + "REGISTRO='"+registroCalificado+"',"
                 +"DURACION="+duracionMeses+","
                 + "MODALIDAD='"+modalidad+"',";
     }
     
     public String select(){
         return "SELECT * FROM PROGRAMAS WHERE CODIGO="+codigo; 
     }
     
     public String delete(){
         return "DELETE FROM PROGRAMAS WHERE CODIGO="+codigo; 
     }

     
    
}
