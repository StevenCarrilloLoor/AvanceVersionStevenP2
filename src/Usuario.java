public class Usuario {
    private String nombre;
    private String apellido;
    private int edad;
    private String rol;
    private String correo;
    private String usuario;
    private String contrasena;

    public Usuario(String nombre, String apellido, int edad, String rol, String correo, String usuario, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.rol = rol;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Usuario: \n" + nombre+ " " + apellido+
                        "  Edad:\n" + edad + " "+
                        "  rol:\n" + rol+ " "+
                        "  Correo:\n" + correo+ " "+
                        "  Usuario:\n" +usuario+" "+
                        "  contrasena:\n"+contrasena;
    }

    public String recuperarUsuario(){
        return "\n=========================================="+
                "\nUsuario: " + usuario +
                "\nContraseña: " + contrasena ;
    }
}
