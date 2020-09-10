package model;

import DB.ConexionBD;

import estruct.Estudiante;
import estruct.Matricula;
import estruct.ProgramaAcademico;
import estruct.Universidad;

import java.rmi.RemoteException;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class ServicioUniversidad {
    
    /*public ServicioUniversidad() {
        super();
    }*/
    
     
    private static ArrayList<Matricula> matriculas = new ArrayList();
    private static ArrayList<Estudiante> estudiantes = new ArrayList();
    private static ArrayList<ProgramaAcademico> programa = new ArrayList();
    
     Universidad uni = Universidad.getUniversidad();
     ConexionBD conexion;
     
     public void ServicioUniversidad()
     {
         conexion = new ConexionBD();
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
     public void cargarDatosMatricula(){

     try{  
         ResultSet results = conexion.executeQueryStatement("SELECT NUMERO_MATRICULA, TO_DATE(FECHA_MATRICULA, 'DD-MM-YYYY') \"FECHA_MATRICULA\", VALOR , NUMERO_CREDITOS, PPA, ESTUDIANTESCEDULA, ESTUDIANTESCODIGO, PROGRAMAACADEMICOCODIGO FROM MATRICULAS"); 
       
         if(results != null){
         
             while (results.next()) {              

                 int numeroMatricula = Integer.parseInt(results.getString("NUMERO_MATRICULA"));
                 int numCreditos = Integer.parseInt(results.getString("NUMERO_CREDITOS"));
                 double valor = Double.parseDouble(results.getString("VALOR"));
                 double ppa = Double.parseDouble(results.getString("PPA"));
                 int cedula = Integer.parseInt(results.getString("ESTUDIANTESCODIGO"));
                 String codigo = results.getString("ESTUDIANTESCEDULA");
                 String programa = results.getString("PROGRAMAACADEMICOCODIGO");
                 
                     //ESTE ES UN ERROR  
                 System.out.println(results.getString("FECHA_MATRICULA")); 
                 
                     
                 SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");  
                     
                 Date fechaNacimiento = formatter.parse(results.getString("FECHA_MATRICULA"));

                 Matricula d = new Matricula(numeroMatricula, fechaNacimiento, valor, numCreditos,  ppa, cedula, codigo, programa);
                 matriculas.add(d);
             }
         
         }
         }
     catch(Exception e)
     {
       e.printStackTrace();
     }
     }
    
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
     
      public void anadirMatricula(Matricula matricula)
     {
        conexion.executeUpdateStatement(matricula.insert());
        matriculas.add(matricula);      
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
     
     
      public void eliminarPorNumeroMatricula(String pCodigo)
     {
         if(matriculas.isEmpty())
         {
             cargarDatosMatricula();
         }  

        for(int i=0; i< matriculas.size();i++)
      {
          Matricula actual = (Matricula) matriculas.get(i);
          if(String.valueOf(actual.getNumeroMatricula()).equals(pCodigo))
          {
                 conexion.executeUpdateStatement(actual.delete());
                 matriculas.remove(actual);
                 System.out.println("Matricula eliminada: " + actual.getNumeroMatricula());
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
     
    
    public void actualizarMatricula(int numeroMatricula,int numCreditos,Date fechaIngreso,double valor,double  ppa)
     {
          if(matriculas.isEmpty())
         {
             cargarDatosMatricula();
         }
         
       for(int i=0; i< matriculas.size();i++)
      {
           Matricula actual = (Matricula) matriculas.get(i);
          if(actual.getNumeroMatricula()== numeroMatricula)
           {
                 actual.setNumCreditos(numCreditos);
                 actual.setFechaMatricula(fechaIngreso);
                 actual.setValor(valor);
                 actual.setPpa(ppa);
                 conexion.executeUpdateStatement(actual.update());
                 System.out.println("Matricula Actualizada: " + actual.getCedulaEstudiante());
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
      
     public ArrayList listarMatriculas()
     {   
        if(matriculas.isEmpty())
        {
            cargarDatosMatricula();
        }
        return matriculas;
     }
     
     public ArrayList listarProgramas()
     {   
        if(programa.isEmpty())
        {
            cargarDatosPrograma();
        }
        return programa;
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
      
      
      public Matricula buscarMatricula( String codigo )
      {
            Matricula matricula = null;
             
            ResultSet results = conexion.executeQueryStatement("SELECT NUMERO_MATRICULA, TO_DATE(FECHA_MATRICULA, 'DD-MM-YYYY') \"FECHA_MATRICULA\", VALOR , NUMERO_CREDITOS, PPA, ESTUDIANTESCEDULA, ESTUDIANTESCODIGO, PROGRAMAACADEMICOCODIGO FROM MATRICULAS"); 
       
            if(results != null)
            {
                try {
                    
                    while(results.next())
                    {
                         String resNumero = results.getString("NUMERO_MATRICULA");
                         
                         if(resNumero.equals(codigo))
                         {
                            int numeroMatricula = Integer.parseInt(results.getString("NUMERO_MATRICULA"));
                           int numCreditos = Integer.parseInt(results.getString("NUMERO_CREDITOS"));
                           double valor = Double.parseDouble(results.getString("VALOR"));
                           double ppa = Double.parseDouble(results.getString("PPA"));
                           String codigoEstu = results.getString("ESTUDIANTESCODIGO");
                           int cedulaEstu = Integer.parseInt(results.getString("ESTUDIANTESCEDULA"));
                           String programa = results.getString("PROGRAMAACADEMICOCODIGO");
                             
                             //SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");               
                             //Date fechaNacimiento = formatter.parse(results.getString("FECHA_NACIMIENTO"));
                             String y = results.getString("FECHA_MATRICULA");
                             
                             Date x = new SimpleDateFormat("yyyy-MM-dd").parse(y);
                                                             
                             Matricula d = new Matricula(numeroMatricula, x, valor, numCreditos, ppa, cedulaEstu, codigoEstu, programa);
                             matricula = d;
                             //estudiantes.add(d);
                             //estu = d.getNombre() + " \n " + String.valueOf(d.getCedula()) + " \n " + String.valueOf(d.getCelular()) + " \n " + d.getCorreo() + " \n " + y + " \n " + d.getGenero();
                             System.out.println("la matricula es: " + matricula.getNumeroMatricula());
                             
                         }
                    }
                }catch(Exception e)
                 {
                    e.printStackTrace();
                 }
            }
            
            return matricula;
      }
    
    /*public boolean existeCorreo(String pCorreo)
     {
          boolean centinela = false;
          for(int i =0; i<estudiantes.size() && centinela == false;i++ )            
         {
             Estudiante actual = (Estudiante)estudiantes.get(i);
             
             if(actual.getCorreo().equals(pCorreo))
             {
                 centinela = true;
             }
         }
          
           return centinela;
      }*/
     
      
       /*public boolean existeCelular(int pCelular)
       {
          boolean centinela = false;
           for(int i =0; i<estudiantes.size() && centinela == false;i++ )            
          {
             Estudiante actual = (Estudiante)estudiantes.get(i);
             
             if(actual.getCelular() == pCelular)
             {
                 centinela = true;
             }
         }
          
          return centinela;
       }*/
        
   
     /* public boolean usuarioExiste( String pCodigo )
     {
         boolean centinela = false;
         
         for(int i = 0; i < estudiantes.size() && !centinela; i++)
         {
             Estudiante estu = (Estudiante) estudiantes.get(i);
             String auxCod = estu.getCodigo();
             
             if(pCodigo.equalsIgnoreCase(auxCod))
             {
                 centinela = true;
                 
             }
         }
         
         return centinela;
     }*/
      
    /* public boolean existeCedula( int pCedula )
     {
         boolean centinela = false;
         
         for(int i = 0; i < estudiantes.size() && !centinela; i++)
         {
             Estudiante estu = (Estudiante) estudiantes.get(i);
             int auxCod = estu.getCedula();
             
             if(pCedula == auxCod)
             {
                 centinela = true;
                 
             }
         }
         
         return centinela;
    }*/
    
      
        public ArrayList<Estudiante> getEstudiantes() {
            return estudiantes;
        }
   
        public ArrayList<Matricula> getMatriculas() {
           return matriculas;
        }
        
    public ArrayList<ProgramaAcademico> getPrograma() {
       return programa;
    }
}
