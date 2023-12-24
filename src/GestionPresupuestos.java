import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestionPresupuestos {
    public List<Presupuesto> presupuestos;

    Map<String, Float> categorias = new HashMap<>();
    public GestionPresupuestos() {
        presupuestos = new ArrayList<Presupuesto>();
        // Presupuesto para la compra de combustible
        presupuestos.add(new Presupuesto("Compra de Combustible", 150000f, 2022, 1));
        presupuestos.add(new Presupuesto("Compra de Combustible", 140000f, 2021,2));
        presupuestos.add(new Presupuesto("Compra de Combustible", 130000f, 2020,3));

        // Presupuesto para mantenimiento de equipos y maquinaria
        presupuestos.add(new Presupuesto("Mantenimiento de Equipos", 30000f, 2022,4));
        presupuestos.add(new Presupuesto("Mantenimiento de Equipos", 20000f, 2021,5));
        presupuestos.add(new Presupuesto("Mantenimiento de Equipos", 10000f, 2020,6));

        // Presupuesto para salarios de los empleados
        presupuestos.add(new Presupuesto("Salarios", 120000f, 2022,7));
        presupuestos.add(new Presupuesto("Salarios", 110000f, 2021,8));
        presupuestos.add(new Presupuesto("Salarios", 100000f, 2020,9));

        // Presupuesto para publicidad y marketing
        presupuestos.add(new Presupuesto("Publicidad y Marketing", 20000f, 2022,10));
        presupuestos.add(new Presupuesto("Publicidad y Marketing", 10000f, 2021,11));
        presupuestos.add(new Presupuesto("Publicidad y Marketing", 11000f, 2020,12));

        // Presupuesto para seguros y licencias
        presupuestos.add(new Presupuesto("Seguros y Licencias", 25000f, 2022,13));
        presupuestos.add(new Presupuesto("Seguros y Licencias", 24000f, 2021,14));
        presupuestos.add(new Presupuesto("Seguros y Licencias", 23000f, 2020,15));

        // Presupuesto para mejoras y renovaciones
        presupuestos.add(new Presupuesto("Mejoras y Renovaciones", 50000f, 2022,16));
        presupuestos.add(new Presupuesto("Mejoras y Renovaciones", 49000f, 2021,17));
        presupuestos.add(new Presupuesto("Mejoras y Renovaciones", 48000f, 2020,18));

        // Presupuesto para gastos administrativos y de oficina
        presupuestos.add(new Presupuesto("Gastos Administrativos", 15000f, 2022,19));
        presupuestos.add(new Presupuesto("Gastos Administrativos", 16000f, 2021,20));
        presupuestos.add(new Presupuesto("Gastos Administrativos", 14000f, 2020,21));

        // Presupuesto para emergencias o imprevistos
        presupuestos.add(new Presupuesto("Fondo de Emergencia", 10000f, 2022,22));
        presupuestos.add(new Presupuesto("Fondo de Emergencia", 9000f, 2021,23));
        presupuestos.add(new Presupuesto("Fondo de Emergencia", 8000f, 2020,24));

        // Presupuesto para servicios y utilidades (electricidad, agua, internet)
        presupuestos.add(new Presupuesto("Servicios y Utilidades", 40000f, 2022,25));
        presupuestos.add(new Presupuesto("Servicios y Utilidades", 38000f, 2021,26));
        presupuestos.add(new Presupuesto("Servicios y Utilidades", 36000f, 2020,27));

        // Presupuesto para formación y capacitación de empleados
        presupuestos.add(new Presupuesto("Capacitación de Empleados", 10000f, 2022,28));
        presupuestos.add(new Presupuesto("Capacitación de Empleados", 9000f, 2021,29));
        presupuestos.add(new Presupuesto("Capacitación de Empleados", 7000f, 2020,30));
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public boolean esPresupuestoUnico(String nombrePresupuesto) {
        for (Presupuesto p : presupuestos) {
            if (p.getNombrePresupuesto().equals(nombrePresupuesto)) {
                return false; // Se encontró un paquete con el mismo código.
            }
        }
        return true; // No se encontró ningún paquete con ese código, es único.
    }

    public void agregarPresupuestoSiEsUnico(Presupuesto nuevoPresupuesto) throws Exception {
        if (esPresupuestoUnico(nuevoPresupuesto.getNombrePresupuesto())) {
            presupuestos.add(nuevoPresupuesto);
        } else {
            throw new Exception("Ya existe un Presupuesto con el nombre: " + nuevoPresupuesto.getNombrePresupuesto());
        }

    }

    public boolean eliminarPresupuestoPorNombre(String nombrePresupuesto) {
        for (int i = 0; i < presupuestos.size(); i++) {
            if (presupuestos.get(i).getNombrePresupuesto().equals(nombrePresupuesto)) {
                presupuestos.remove(i);
                return true; // Presupuesto encontrado y eliminado
            }
        }
        return false; // No se encontró un presupuesto con ese nombre
    }

    public boolean actualizar(Presupuesto dato) {

        for (Presupuesto p : presupuestos) {
            if (p.getId()==dato.getId()) {
                p.setId(dato.getId());
                p.setNombrePresupuesto(dato.getNombrePresupuesto());
                p.setValorPresupuesto(dato.getValorPresupuesto());
                p.setAnio(dato.getAnio());
                return true;
            }
        }
        return false;
    }

    private Float calcularPromedioRecursivo(String nombrePresupuesto, int indice, float suma, int conteo) {
        if (indice == presupuestos.size()) {
            // Caso base: hemos revisado todos los presupuestos
            return conteo > 0 ? suma / conteo : null; // Retorna el promedio o null si no hay coincidencias
        }

        Presupuesto p = presupuestos.get(indice);
        if (p.getNombrePresupuesto().equals(nombrePresupuesto)) {
            // Si el nombre coincide, acumula el valor y aumenta el conteo
            suma += p.getValorPresupuesto();
            conteo++;
        }
        // Llamada recursiva al siguiente presupuesto
        return calcularPromedioRecursivo(nombrePresupuesto, indice + 1, suma, conteo);
    }

    public Float calcularPromedioPorNombre(String nombrePresupuesto) {
        // Inicia la recursión desde el primer presupuesto
        return calcularPromedioRecursivo(nombrePresupuesto, 0, 0, 0);
    }

    public float calcularPromedioGeneral(){
        float general;
        return general=(calcularPromedioPorNombre("Capacitación de Empleados")+
                calcularPromedioPorNombre("Compra de Combustible")+
                calcularPromedioPorNombre("Mantenimiento de Equipos")+
                calcularPromedioPorNombre("Fondo de Emergencia")+
                calcularPromedioPorNombre("Gastos Administrativos")+
                calcularPromedioPorNombre("Mejoras y Renovaciones")+
                calcularPromedioPorNombre("Seguros y Licencias")+
                calcularPromedioPorNombre("Publicidad y Marketing")+
                calcularPromedioPorNombre("Salarios")+
                calcularPromedioPorNombre("Mantenimiento de Equipos"));
    }

    public boolean actualizarPre(Presupuesto dato) {

        for (Presupuesto p : presupuestos) {
            if (p.getId()==dato.getId()) {
                p.setNombrePresupuesto(dato.getNombrePresupuesto());
                p.setValorPresupuesto(dato.getValorPresupuesto());
                p.setAnio(dato.getAnio());
                p.setId(dato.getId());
                return true;
            }
        }
        return false;
    }

}











