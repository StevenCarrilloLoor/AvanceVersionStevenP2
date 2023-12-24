public class Presupuesto {
    private String nombrePresupuesto;
    private float valorPresupuesto;
    private int anio;
    private  int id;

    public Presupuesto(String nombrePresupuesto, float valorPresupuesto, int anio, int id) {
        this.nombrePresupuesto = nombrePresupuesto;
        this.valorPresupuesto = valorPresupuesto;
        this.anio = anio;
        this.id = id;

    }



    public String getNombrePresupuesto() {
        return nombrePresupuesto;
    }

    public void setNombrePresupuesto(String nombrePresupuesto) {
        this.nombrePresupuesto = nombrePresupuesto;
    }

    public float getValorPresupuesto() {
        return valorPresupuesto;
    }

    public void setValorPresupuesto(float valorPresupuesto) {
        this.valorPresupuesto = valorPresupuesto;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Presupuesto: "+ id + " \n" +
                "Nombre del presupuesto: " + nombrePresupuesto + " " + " Valor anual: "+valorPresupuesto + " "+"año: "+anio;
    }
}
