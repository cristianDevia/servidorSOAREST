package estruct;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Matricula {
    
    public Matricula() {
        super();
    }
    
    private int numeroMatricula,numCreditos,cedulaEstudiante;
    private Date fechaMatricula;
    private double valor,ppa;
    private String codigoEstudiante, programa;

    public Matricula(int numeroMatricula, Date fechaMatricula, double valor, int numCreditos, double ppa, int cedulaEstudiante, String codigoEstudiante, String programa) {
        this.numeroMatricula = numeroMatricula;
        this.numCreditos = numCreditos;
        this.fechaMatricula = fechaMatricula;
        this.valor = valor;
        this.ppa = ppa;
        this.cedulaEstudiante = cedulaEstudiante;
        this.codigoEstudiante = codigoEstudiante;
        this.programa = programa;
    }

    public int getCedulaEstudiante() {
        return cedulaEstudiante;
    }

    public void setCedulaEstudiante(int cedulaEstudiante) {
        this.cedulaEstudiante = cedulaEstudiante;
    }

    public String getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    
    

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public int getNumCreditos() {
        return numCreditos;
    }

    public void setNumCreditos(int numCreditos) {
        this.numCreditos = numCreditos;
    }

    public Date getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Date fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getPpa() {
        return ppa;
    }

    public void setPpa(double ppa) {
        this.ppa = ppa;
    }
    
 
         public String insert()
     {
       
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
         
            String fecha = formatter.format(fechaMatricula);
            
            System.out.println("INSERT INTO MATRICULAS VALUES("+numeroMatricula+",'" + fecha+"'"+","+valor+","+numCreditos+"," + ppa + "," + cedulaEstudiante + ",'"+ codigoEstudiante +"','" + programa +"'" +")");
            return "INSERT INTO MATRICULAS VALUES("+numeroMatricula+",'"+fecha+"',"+valor+","+numCreditos+"," + ppa + "," + cedulaEstudiante + ",'"+ codigoEstudiante +"','" + programa +"'"+")";
    
    }
    
    public String update(){
    
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
        return "UPDATE MATRICULAS SET NUMERO_MATRICULA="+ numeroMatricula+","
                + "NUMERO_CREDITOS="+numCreditos+","
                 + "VALOR="+valor+","
                 + "PPA="+ppa+","
                 + "FECHA_MATRICULA='"+formatter.format(fechaMatricula)+"' WHERE NUMERO_MATRICULA="+numeroMatricula;
                

               
    }
    
    
    public String select(){
        return "SELECT * FROM MATRICULAS WHERE NUMERO_MATRICULA="+numeroMatricula; 
    }
    
    public String delete(){
        return "DELETE FROM MATRICULAS WHERE NUMERO_MATRICULA="+numeroMatricula; 
    }
    
    
}
