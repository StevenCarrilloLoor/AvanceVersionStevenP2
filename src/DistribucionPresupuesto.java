import java.util.HashMap;
import java.util.Map;

public class DistribucionPresupuesto {
    GestionPresupuestos gestion = new GestionPresupuestos();
    private Map<String, Float> categorias = new HashMap<>();
    private GestionPresupuestos listaPresupuesto = new GestionPresupuestos(); // Asume que tienes una clase GestionPresupuestos con los métodos necesarios

    public void distribuirPresupuestoAnual(float presupuestoTotal) {
        categorias.put("Capacitación de Empleados", (gestion.calcularPromedioPorNombre("Capacitación de Empleados")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Servicios y Utilidades", (gestion.calcularPromedioPorNombre("Servicios y Utilidades")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Fondo de Emergencia", (gestion.calcularPromedioPorNombre("Fondo de Emergencia")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Gastos Administrativos", (gestion.calcularPromedioPorNombre("Gastos Administrativos")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Mejoras y Renovaciones", (gestion.calcularPromedioPorNombre("Mejoras y Renovaciones")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Seguros y Licencias", (gestion.calcularPromedioPorNombre("Seguros y Licencias")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Publicidad y Marketing", (gestion.calcularPromedioPorNombre("Publicidad y Marketing")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Salarios", (gestion.calcularPromedioPorNombre("Salarios")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Mantenimiento de Equipos", (gestion.calcularPromedioPorNombre("Mantenimiento de Equipos")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
        categorias.put("Compra de Combustible", (gestion.calcularPromedioPorNombre("Compra de Combustible")/gestion.calcularPromedioGeneral()) * presupuestoTotal);
    }

    public String imprimirEnTextArea() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Float> entry : categorias.entrySet()) {
            String categoria = entry.getKey();
            Float asignacion = entry.getValue();
            result.append(categoria).append(": $").append(asignacion).append("\n");
        }
        return result.toString();
    }

}
