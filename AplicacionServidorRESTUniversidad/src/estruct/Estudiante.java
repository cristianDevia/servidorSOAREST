package estruct;

import java.io.Serializable;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Estudiante {
    
        private String nombre,codigo,correo,genero;
        private int cedula,celular;
        private Date fechaNacimiento;
        
        
        public Estudiante() {
            super();
        }
    


        public Estudiante(String nombre, int cedula, String codigo, String correo, int celular, Date fechaNacimiento, String genero) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.correo = correo;
            this.cedula = cedula;
            this.celular = celular;
            this.fechaNacimiento = fechaNacimiento;
            this.genero = genero;
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

        public String getCorreo() {
            return correo;
        }

        public void setCorreo(String correo) {
            this.correo = correo;
        }

        public int getCedula() {
            return cedula;
        }

        public void setCedula(int cedula) {
            this.cedula = cedula;
        }

        public int getCelular() {
            return celular;
        }

        public void setCelular(int celular) {
            this.celular = celular;
        }

     

        public Date getFechaNacimiento() {
            return fechaNacimiento;
        }

        public void setFechaNacimiento(Date fechaNacimiento) {
            this.fechaNacimiento = fechaNacimiento;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }
     
         public String insert()
         {
           
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
             
                String fecha = formatter.format(fechaNacimiento);
                
                System.out.println("INSERT INTO ESTUDIANTES VALUES('"+nombre+"',"+cedula+",'"+codigo+"','"+correo+"',"+celular+",'"+fecha+"','"+genero+"')");
                return "INSERT INTO ESTUDIANTES VALUES('"+nombre+"',"+cedula+",'"+codigo+"','"+correo+"',"+celular+",'"+fecha+"','"+genero + "')";      
        }
        
        public String update(){
        
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
            return "UPDATE ESTUDIANTES SET CODIGO="+ codigo+","
                    + "NOMBRE='"+nombre+"',"
                     + "CEDULA='"+cedula+"',"
                     + "CELULAR='"+celular+"',"
                     + "CORREO='"+correo+"',"
                    +"GENERO='"+genero+"',"
                    + "FECHANACIMIENTO='"+formatter.format(fechaNacimiento)+"' WHERE CODIGO="+codigo;
        }
        
        public String select(){
            return "SELECT * FROM ESTUDIANTES WHERE CODIGO="+ codigo; 
        }
        
        public String delete(){
            return "DELETE FROM ESTUDIANTES WHERE CODIGO="+codigo; 
        }
        
        
}
