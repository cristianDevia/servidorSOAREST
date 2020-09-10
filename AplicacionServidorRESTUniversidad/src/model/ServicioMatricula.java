package model;

import DB.ConexionBD;

import estruct.Estudiante;
import estruct.Matricula;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ServicioMatricula 
{
    private ArrayList<Matricula> matriculas = new ArrayList<Matricula>();
    ConexionBD conexion;
    public ServicioMatricula() 
    {
        super();
        conexion = new ConexionBD();
        if(matriculas == null) {
            cargarDatosMatricula();
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
    public void anadirMatricula(Matricula matricula)
    {
      conexion.executeUpdateStatement(matricula.insert());
      matriculas.add(matricula);
      System.out.println("matricula agregada: " + matricula);
    }
    
    public void eliminarPorNumeroMatricula(String pCodigo)
    {
        if(matriculas.isEmpty()) {
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
    public void actualizarMatricula(int numeroMatricula,int numCreditos,Date fechaIngreso,double valor,double  ppa)
     {
        
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
    
     
    public ArrayList<Matricula> listarMatriculas()
    {   
        ResultSet results = conexion.executeQueryStatement("SELECT NUMERO_MATRICULA, TO_DATE(FECHA_MATRICULA, 'DD-MM-YYYY') \"FECHA_MATRICULA\", VALOR , NUMERO_CREDITOS, PPA, ESTUDIANTESCEDULA, ESTUDIANTESCODIGO, PROGRAMAACADEMICOCODIGO FROM MATRICULAS");

        try {
            while (results.next()) {

                int numeroMatricula = Integer.parseInt(results.getString("NUMERO_MATRICULA"));
                int numCreditos = Integer.parseInt(results.getString("NUMERO_CREDITOS"));
                double valor = Double.parseDouble(results.getString("VALOR"));
                double ppa = Double.parseDouble(results.getString("PPA"));
                int cedula = Integer.parseInt(results.getString("ESTUDIANTESCODIGO"));
                String codigo = results.getString("ESTUDIANTESCEDULA");
                String programa = results.getString("PROGRAMAACADEMICOCODIGO");
                SimpleDateFormat formatter = new SimpleDateFormat("DD-MM-YYYY");  
                    
                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = formatter.parse(results.getString("FECHA_MATRICULA"));
                } catch (ParseException e) {
                }

                Matricula d = new Matricula(numeroMatricula, fechaNacimiento, valor, numCreditos,  ppa, cedula, codigo, programa);
                matriculas.add(d);
            }
        } catch (SQLException e) {
        }

        return matriculas;
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
    
    public Matricula[] getMatriculas() {
        
        if(matriculas.isEmpty()) {
            cargarDatosMatricula();
        }
        
        Matricula arrayMatriculas[] = new Matricula[matriculas.size()];
        
        for(int i = 0; i< matriculas.size(); i++) {
            Matricula temp = matriculas.get(i);
            arrayMatriculas[i] = temp;
        }
        
        return arrayMatriculas;
    }
    
}
