package model;

import DB.ConexionBD;

import estruct.ProgramaAcademico;

import java.sql.ResultSet;

import java.util.ArrayList;

public class ServicioPrograma {
    public ServicioPrograma() {
        
        super();
        cargarDatosPrograma();
    }
    private static ArrayList<ProgramaAcademico> programa = new ArrayList();
    ConexionBD conexion;
    
    public void cargarDatosPrograma(){

    try{  
        //ResultSet results = conexion.executeQueryStatement("SELECT NUMERO_MATRICULA, TO_DATE(FECHA_MATRICULA, 'DD-MM-YYYY') \"FECHA_MATRICULA\", NUMERO_CREDITOS, PPA, ESTUDIANTECEDULA, ESTUDIANTECODIGO, PROGRAMAACADEMICOCODIGO FROM MATRICULAS"); 
          ResultSet results = conexion.executeQueryStatement("SELECT NOMBRE, CODIGO, REGISTRO_CALIFICADO, DURACION_MESES, MODALIDAD FROM PROGRAMAACADEMICO");
        if(results != null){
        
            while (results.next()) {              

                String nombre = results.getString("NOMBRE");
                String codigo = results.getString("CODIGO");
                String registro = results.getString("REGISTRO_CALIFICADO");
                int duracion = Integer.parseInt(results.getString("DURACION_MESES"));
                String modalidad = results.getString("MODALIDAD");
               
                //ESTE ES UN ERROR  

                ProgramaAcademico d = new ProgramaAcademico(nombre, codigo, registro, duracion, modalidad);
                programa.add(d);
            }
        
        }
        }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    }
    
    public ArrayList listarProgramas()
    {   
       if(programa.isEmpty())
       {
           cargarDatosPrograma();
       }
       return programa;
    }
    
    public ArrayList<ProgramaAcademico> getPrograma() 
    {
       return programa;
    }
}
