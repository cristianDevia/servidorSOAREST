package model;

import DB.ConexionBD;

import estruct.Estudiante;
import estruct.Matricula;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("model")
public class SWMatricula
{
    ServicioMatricula ser = new ServicioMatricula();
    public SWMatricula() {
        super();
    }


    @POST
    @Path("anadirMatricula")
    public void anadirMatricula(Matricula matricula)
    {
        ser.anadirMatricula(matricula);
    }

    @POST
    @Consumes("application/json")
    @Path("anadirMatriculaParametros")
    public void anadirMatriculaParametros(@QueryParam("numeroMatricula") int numeroMatricula,
                                          @QueryParam("fechaMatricula") Date fechaMatricula,
                                          @QueryParam("valor") double valor, @QueryParam("numCreditos") int numCreditos,
                                          @QueryParam("ppa") double ppa,
                                          @QueryParam("cedulaEstudiante") int cedulaEstudiante,
                                          @QueryParam("codigoEstudiante") String codigoEstudiante,
                                          @QueryParam("programa") String programa)
    {
        Matricula mat = new Matricula(numeroMatricula,fechaMatricula,valor,numCreditos,ppa,cedulaEstudiante,codigoEstudiante,programa);
        ser.anadirMatricula(mat);
    }

    @DELETE
    @Consumes("application/json")
    @Path("eliminarPorNumeroMatricula")
    public void eliminarPorNumeroMatricula(@QueryParam("pCodigo") String pCodigo)
    {
        ser.eliminarPorNumeroMatricula(pCodigo);
    }

    @PUT
    @Consumes("application/json")
    @Path("actualizarMatricula")
    public void actualizarMatricula(@QueryParam("numeroMatricula") int numeroMatricula,
                                    @QueryParam("numCreditos") int numCreditos,
                                    @QueryParam("fechaIngreso") Date fechaIngreso, @QueryParam("valor") double valor,
                                    @QueryParam("ppa") double ppa)
     {
        ser.actualizarMatricula(numeroMatricula, numCreditos, fechaIngreso, valor, ppa);
     }


    @GET
    @Produces("application/json")
    @Path("listarMatriculas")
    public ArrayList<Matricula> listarMatriculas()
    {       
       return ser.listarMatriculas();
    }

    @GET
    @Produces("application/json")
    @Path("buscarMatricula")
    public Matricula buscarMatricula(@QueryParam("codigo") String codigo )
    {
        
          return ser.buscarMatricula(codigo);
    }
    
}
