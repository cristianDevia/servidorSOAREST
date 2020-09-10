package model;

import DB.ConexionBD;

import estruct.Estudiante;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;


public class ServicioEstudiante {
    
    ConexionBD conexion;
    private ArrayList<Estudiante> estudiantes = new ArrayList(); 
    
    public ServicioEstudiante() {
        super();
        conexion = new ConexionBD();
        if(estudiantes == null)
        {
        cargarDatosEstudiantes();
        }
        
    }
    
    
    public void cargarDatosEstudiantes(){

    try{     
        ResultSet results = conexion.executeQueryStatement("SELECT NOMBRE, CEDULA, CODIGO, CORREO, CELULAR, TO_DATE(FECHANACIMIENTO, 'DD-MM-YYYY') \"FECHANACIMIENTO\", GENERO FROM ESTUDIANTES"); 
      
        if(results != null){
        
            while (results.next()) {              

                String codigo = results.getString("CODIGO");
                String nombre = results.getString("NOMBRE");
                int cedula = Integer.parseInt(results.getString("CEDULA"));
                String genero = results.getString("GENERO");
                int celular = Integer.parseInt(results.getString("CELULAR"));
                String correo = results.getString("CORREO");
               
                //ESTE ES UN ERROR  
                System.out.println(results.getString("FECHANACIMIENTO")); 
         
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
                    
                Date fechaNacimiento = formatter.parse(results.getString("FECHANACIMIENTO"));

                Estudiante d = new Estudiante(nombre,cedula, codigo, correo, celular, fechaNacimiento, genero );
                estudiantes.add(d);
                
            }
        
        }
        }
    catch(Exception e)
    {
      e.printStackTrace();
    }
    }
    
    public void anadirEstudiante(Estudiante estu)
    {
     
         //conexion.executeProcedure(nuevo.getCodigo());
      
         if(conexion.executeUpdateStatement(estu.insert()))
         {
             //conexion.executeUpdateStatement(nuevo.insert());
             estudiantes.add(estu);  
         }
         else
         {
             System.out.println("Error en agregar estudiante ");
         }
    }
    
    public void eliminarPorCodigoEstudiante(String pCodigo)
    {
        if(estudiantes.isEmpty())
        {
            cargarDatosEstudiantes();
        }     
       for(int i=0; i< estudiantes.size();i++)
     {
        Estudiante actual = (Estudiante) estudiantes.get(i);
           if(actual.getCodigo().equals(pCodigo))
         {
                conexion.executeUpdateStatement(actual.delete());
                estudiantes.remove(actual);
                System.out.println("Estudiante eliminado " + actual.getNombre());
         }
         }
     }
    
    public void actualizarEstudiantePorCodigo(String nombre, int cedula, String codigo, String correo, int celular, Date fechaNacimiento, String genero)
     {
         if(estudiantes.isEmpty())
         {
             cargarDatosEstudiantes();
         }
       for(int i=0; i< estudiantes.size();i++)
      {
          Estudiante actual = (Estudiante) estudiantes.get(i);
          if(actual.getCodigo().equals(codigo))
           {
                 actual.setCedula(cedula);
                 actual.setNombre(nombre);
                 actual.setCorreo(correo);
                 actual.setCelular(celular);
                 actual.setFechaNacimiento(fechaNacimiento);
                 actual.setGenero(genero);
                 
                 conexion.executeUpdateStatement(actual.update());
              }
          }
     }
    
    public ArrayList listarEstudiantes()
    {
      if(estudiantes.isEmpty())
      {
          cargarDatosEstudiantes();
      }
      return estudiantes;
    }
    
    public Estudiante buscarEstudiantePorCodigo(String codigo)
    { 
          Estudiante estu = null;
           
          ResultSet results = conexion.executeQueryStatement("SELECT NOMBRE, CEDULA, CODIGO, CORREO, CELULAR, FECHANACIMIENTO, GENERO FROM ESTUDIANTES"); 
     
          if(results != null)
          {
              try {
                  
                  while(results.next())
                  {
                       String resCodigo = results.getString("CODIGO");
                       
                       if(resCodigo.equals(codigo))
                       {
                           String nombre = results.getString("NOMBRE");
                           int cedula = Integer.parseInt(results.getString("CEDULA"));
                           String genero = results.getString("GENERO");
                           int celular = Integer.parseInt(results.getString("CELULAR"));
                           String correo = results.getString("CORREO");
                           
                           //SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");               
                           //Date fechaNacimiento = formatter.parse(results.getString("FECHA_NACIMIENTO"));
                           String y = results.getString("FECHANACIMIENTO");
                           
                           Date x = new SimpleDateFormat("yyyy-MM-dd").parse(y);
                                                           
                           Estudiante d = new Estudiante(nombre,cedula, codigo, correo, celular, x, genero );
                           estu = d;
                           //estudiantes.add(d);
                           //estu = d.getNombre() + " \n " + String.valueOf(d.getCedula()) + " \n " + String.valueOf(d.getCelular()) + " \n " + d.getCorreo() + " \n " + y + " \n " + d.getGenero();
                           System.out.println("estudiante:" + estu.getCodigo());
                           
                       }
                  }
              }catch(Exception e)
               {
                  e.printStackTrace();
               }
          }
          
          return estu;
    }
    public ArrayList<Estudiante> getEstudiantes() {
              
        ResultSet results = conexion.executeQueryStatement("SELECT NOMBRE, CEDULA, CODIGO, CORREO, CELULAR, FECHANACIMIENTO, GENERO FROM ESTUDIANTES");

        try {
            while (results.next()) {

                String resCodigo = results.getString("CODIGO");
                String nombre = results.getString("NOMBRE");
                int cedula = Integer.parseInt(results.getString("CEDULA"));
                String genero = results.getString("GENERO");
                int celular = Integer.parseInt(results.getString("CELULAR"));
                String correo = results.getString("CORREO");
                String y = results.getString("FECHANACIMIENTO");

                Date x = null;
                try 
                {
                    x = new SimpleDateFormat("yyyy-MM-dd").parse(y);
                }
                catch (ParseException e)
                {
                }

                Estudiante d = new Estudiante(nombre, cedula, resCodigo, correo, celular, x, genero);
                estudiantes.add(d);

            }
        } catch (SQLException e) {
        }
    

            return estudiantes;
    }
}
