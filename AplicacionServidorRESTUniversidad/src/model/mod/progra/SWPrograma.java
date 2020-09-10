package model.mod.progra;

import estruct.ProgramaAcademico;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import model.ServicioPrograma;

@Path("progra")
public class SWPrograma 
{
    ServicioPrograma ser = new ServicioPrograma();
    public SWPrograma() {
        super();
    }

    @GET
    @Produces("application/json")
    @Path("listarProgramas")
    public ArrayList listarProgramas()
    {   
      return ser.listarProgramas();
    }

    @GET
    @Produces("application/json")
    @Path("getProgramas")
    public ArrayList<ProgramaAcademico> getPrograma() 
    {
       return ser.getPrograma();
    }
}
