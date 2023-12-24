import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GestionUsuario {
    public List<Usuario> usuarios;

    public GestionUsuario() {
        usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Steven Ramon","Carrillo Loor",20,"ADMINISTRADOR","steven@gmail.com","Steven99","Steven1799"));
        usuarios.add(new Usuario("John", "Doe", 25, "EMPLEADO", "john@example.com", "john_user1", "Password1"));
        usuarios.add(new Usuario("Alice", "Smith", 30, "JEFE", "alice@example.com", "alice_user2", "Password2"));
        usuarios.add(new Usuario("Bob", "Johnson", 28, "ADMINISTRADOR", "bob@example.com", "bob_user3", "Password3"));
        usuarios.add(new Usuario("Eva", "Williams", 35, "EMPLEADO", "eva@example.com", "eva_user4", "Password4"));
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    public boolean InicioDeSesion( String usuario, String contrasena) throws Exception {
        for (Usuario u:usuarios) {
            if ((u.getUsuario().equals(usuario)&&u.getContrasena().equals(contrasena)&&u.getRol().equals("ADMINISTRADOR"))||
                    (u.getUsuario().equals(usuario)&&u.getContrasena().equals(contrasena)&&u.getRol().equals("JEFE"))){
                return true;
            } else if (u.getUsuario().equals(usuario)&&u.getContrasena().equals(contrasena)&&u.getRol().equals("EMPLEADO")) {
                throw new Exception("LOS EMPLEADOS NO PUDE INICIAR COMO ADMINISTRADORES O JEFES");
            }
            }
        return false;
        }


    public String analisisDetalladoDesviacionPresupuestaria() {
        double totalEdades = usuarios.stream().mapToDouble(Usuario::getEdad).sum();

        if (totalEdades > PRESUPUESTO) {
            StringBuilder analisis = new StringBuilder("Análisis detallado de desviación presupuestaria:\n");
            analisis.append("Total de edades: ").append(totalEdades).append("\n");

            // Agrega más detalles o análisis según sea necesario

            return analisis.toString();
        } else {
            return "No hay desviación presupuestaria significativa.";
        }
    }

    private static final double PRESUPUESTO = 1000.0; // Definir el presupuesto

    public void enviarAlertaDesviacionPresupuestaria() {
        double totalEdades = usuarios.stream().mapToDouble(Usuario::getEdad).sum();

        if (totalEdades > PRESUPUESTO) {
            JOptionPane.showMessageDialog(null, "Alerta: Desviación presupuestaria significativa");
        }
    }

    public String generarInformeDesviacionPresupuestaria() {
        return analisisDetalladoDesviacionPresupuestaria();
    }

    public boolean esCorreoUnico(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                return false; // Se encontró un paquete con el mismo código.
            }
        }
        return true; // No se encontró ningún paquete con ese código, es único.
    }
    public boolean esUsuarioUnico(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return false; // Se encontró un paquete con el mismo código.
            }
        }
        return true; // No se encontró ningún paquete con ese código, es único.
    }

    public void agregarUsuarioSiEsUnico(Usuario nuevoUsuario) throws Exception {
        if(validarUsuario(nuevoUsuario.getUsuario())){
            if (esCorreoUnico(nuevoUsuario.getCorreo())&&esUsuarioUnico(nuevoUsuario.getUsuario())) {
                usuarios.add(nuevoUsuario);
            } else {
                throw new Exception("Ya existe el Usuario o correo seleccionado en la base de datos");
            }
        }else{
            throw new Exception("El usuario es invalido");
        }


    }

    //VALIDAR SI EL USUARIO ES UNICO--------------------
    public boolean validarUsuarioSiEsUnico(Usuario nuevoUsuario) throws Exception {
        if(validarUsuario(nuevoUsuario.getUsuario())){
            if (esUsuarioUnico(nuevoUsuario.getUsuario())) {
                return true;
            } else {
                throw new Exception("Ya existe el Usuario");
            }
        }else{
            throw new Exception("El usuario es invalido");
        }
    }

    public Usuario eliminarUsuario() throws Exception {
        if (usuarios.isEmpty()) {
            throw new Exception("No existen Usuarios");
        }
        return usuarios.remove(usuarios.size() - 1);
    }

    public void eliminarUsuarioPorCorreo(String correo) throws Exception {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCorreo().equals(correo)) {
                usuarios.remove(i);
                return;
            }
        }
        throw new Exception("Usuario no encontrado con el correo: " + correo);
    }

    public boolean actualizar(Usuario dato) {

        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(dato.getCorreo())) {
                u.setNombre(dato.getNombre());
                u.setApellido(dato.getApellido());
                u.setRol(dato.getRol());
                u.setCorreo(dato.getCorreo());
                u.setUsuario(dato.getUsuario());
                u.setContrasena(dato.getContrasena());
                u.setEdad(dato.getEdad());
                return true;
            }
        }
        return false;
    }
    public boolean parametroVacio(String parametro){
        if(!parametro.equals("")){
            return true;
        }else{
            return false;
        }
    }
    public boolean validarContrasena(String contrasena) {
        boolean tieneMayuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspacio = false;

        if (contrasena.length() < 8) {
            return false; // La contraseña no tiene al menos 8 caracteres
        }

        for (char caracter : contrasena.toCharArray()) {
            if (Character.isUpperCase(caracter)) {
                tieneMayuscula = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumero = true;
            } else if (Character.isWhitespace(caracter)) {
                tieneEspacio = true;
            }

            if (tieneMayuscula && tieneNumero && !tieneEspacio) {
                return true; // La contraseña cumple con todos los requisitos
            }
        }

        return false; // La contraseña no cumple con los requisitos
    }
    //-------------Validar Usuario--------------
    public boolean validarUsuario(String contrasena) {
        boolean tieneLetras = false;
        boolean tieneNumeros = false;
        boolean tieneEspacio = false;

        // Check if the password has a minimum length of 5 characters
        if (contrasena.length() < 5) {
            return false;
        }

        for (char caracter : contrasena.toCharArray()) {
            if (Character.isWhitespace(caracter)) {
                 tieneEspacio = true; // La contraseña no debe contener espacios
            }
            if (Character.isLetter(caracter)) {
                tieneLetras = true;
            } else if (Character.isDigit(caracter)) {
                tieneNumeros = true;
            }

            // If the password contains both letters and numbers, it meets the requirements
            if (tieneLetras && tieneNumeros && !tieneEspacio) {
                return true;
            }
        }

        return false;
    }

    //------------------------------------------
    public boolean validarCorreo(String correo) {
        // Verifica si el correo contiene al menos un caracter '@' y al menos un caracter '.'
        return correo.contains("@") && correo.contains(".");

    }
    public boolean validarEdad(int edad) {
        return edad >= 18; // Retorna verdadero si la edad es mayor o igual a 18
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                return u; // Usuario encontrado con el correo correspondiente
            }
        }
        return null; // No se encontró un usuario con ese correo
    }

}
