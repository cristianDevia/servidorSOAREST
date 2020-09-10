package mod.estu;

import DB.ConexionBD;

import estruct.Estudiante;


import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import model.ServicioEstudiante;

@Path("mod.estu")
public class SWEstudiante
{
    ServicioEstudiante ser = new ServicioEstudiante();
    
    public SWEstudiante() {
        super();
        
    }
  


    @POST
    @Path("anadirEstudiante")
    public void anadirEstudiante(Estudiante estu)
    {
        ser.anadirEstudiante(estu);
     
        
    }

    @POST
    @Consumes("application/json")
    @Path("anadirEstudianteParametros")
    public void anadirEstudianteParametros(@QueryParam("nombre") String nombre, @QueryParam("cedula") int cedula,
                                           @QueryParam("codigo") String codigo, @QueryParam("correo") String correo,
                                           @QueryParam("celular") int celular,
                                           @QueryParam("fechaNacimiento") Date fechaNacimiento,
                                           @QueryParam("genero") String genero)
    {
        Estudiante estu = new Estudiante(nombre,cedula,codigo,correo,celular,fechaNacimiento,genero);
        ser.anadirEstudiante(estu);
     
        
    }


    @DELETE
    @Consumes("application/json")
    @Path("eliminarPorCodigoEstudiante")
    public void eliminarPorCodigoEstudiante(@QueryParam("pCodigo") String pCodigo)
    { 
            ser.eliminarPorCodigoEstudiante(pCodigo);
     }

    @PUT
    @Consumes("application/json")
    @Path("actualizarEstudiantePorCodigo")
    public void actualizarEstudiantePorCodigo(@QueryParam("nombre") String nombre, @QueryParam("cedula") int cedula,
                                              @QueryParam("codigo") String codigo, @QueryParam("correo") String correo,
                                              @QueryParam("celular") int celular,
                                              @QueryParam("fechaNacimiento") Date fechaNacimiento,
                                              @QueryParam("genero") String genero)
     {
            ser.actualizarEstudiantePorCodigo(nombre, cedula, codigo, correo, celular, fechaNacimiento, genero);
     }

    @GET
    @Produces("application/json")
    @Path("listarEstudiantes")
    public ArrayList listarEstudiantes()
    {
     
      return ser.listarEstudiantes();
    }

    @GET
    @Produces("application/json")
    @Path("buscarEstudiantePorCodigo")
    public Estudiante buscarEstudiantePorCodigo(@QueryParam("codigo") String codigo)
    { 
         Estudiante estu = ser.buscarEstudiantePorCodigo(codigo);
          
          return estu;
    }

    @GET
    @Produces("application/json")
    @Path("getEstudiantes")
    public ArrayList<Estudiante> getEstudiantes() {
        return ser.getEstudiantes();
    }
}
