import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ventana {
    private JTabbedPane JPANEL;
    private JPanel panel1;
    private JPanel principal;
    private JTextField tstNombre;
    private JTextField txtApellido;
    private JTextField txtCorreo;
    private JSpinner spEdad;
    private JButton btnAgregar;
    private JList lUsuarios;
    private JTextField txtActNombre;
    private JTextField txtActApellido;
    private JTextField txtActCorreo;
    private JTextField txtActEdad;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnPresupuesto;
    private JTextField txtPresupuesto;
    private JTabbedPane tabbedPane1;
    private JButton btnInicio;
    private JTextField txtContrasena;
    private JTextField txtUsuariose;
    private JButton btnRecuperar;
    private JComboBox cboRoles;
    private JTextField txtUsuario;
    private JTextField txtContrasenaUsuario;
    private JComboBox cboActualizarRol;
    private JTextField txtUsuarioActualizar;
    private JTextField txtContrasenaActualizar;
    private JTextField txtCorreoRecuperacion;
    private JButton btnRecuperacionCredenciales;
    private JLabel labelRecuperar;
    private JButton btnCancelar;
    private JButton btnPresupuestoAnualGeneral;
    private JComboBox cboPresupuestoIndividualAnual;
    private JTextArea textArea1;
    private JButton btnVerPromedioIndividualGeneral;
    private JLabel labelPresupuestoIndividualGneral;
    private JLabel labelPresupuestoGeneral;
    private JList listTodosAnos;
    private JButton btnJlistTodoAnos;
    private JComboBox cboGestioonTiposPresupuesto;
    private JComboBox cboAniosPresupuesto;
    private JTextField txtGestionPrsupuesto;
    private JButton btnActualizarGestionPresuuesto;
    private JTextField txtId;

    DefaultListModel<Usuario> dlm1 = new DefaultListModel<Usuario>();
    DefaultListModel<Presupuesto> dlm2 = new DefaultListModel<Presupuesto>();

    GestionUsuario listaUsuario = new GestionUsuario();
    DistribucionPresupuesto distribucionPresupuesto = new DistribucionPresupuesto();
    GestionPresupuestos listaPresupuesto = new GestionPresupuestos();
    Map<String, Float> categorias = new HashMap<>();

    private void llenarJlist() {
        List<Usuario> listado = listaUsuario.getUsuarios();
        DefaultListModel<Usuario> dlm = new DefaultListModel<>();
        for(Usuario j : listado){
            dlm.addElement(j); // Asegúrate de agregar objetos de tipo Usuario, no Strings.
        }
        lUsuarios.setModel(dlm);
    }

    private void llenarJlistPre() {
        List<Presupuesto> listado = listaPresupuesto.getPresupuestos();
        DefaultListModel<Presupuesto> dlm = new DefaultListModel<>();
        for(Presupuesto p : listado){
            dlm.addElement(p); // Asegúrate de agregar objetos de tipo Usuario, no Strings.
        }
        listTodosAnos.setModel(dlm);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana");
        frame.setContentPane(new Ventana().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static final double PRESUPUESTO = 1000.0; // Definir el presupuesto

    public void enviarAlertaDesviacionPresupuestaria() {
        try {
            double totalEdades = listaUsuario.getUsuarios().stream().mapToDouble(Usuario::getEdad).sum();

            if (totalEdades > PRESUPUESTO) {
                JOptionPane.showMessageDialog(null, "Alerta: Desviación presupuestaria significativa");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Ventana() {

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = tstNombre.getText();
                String apellido = txtApellido.getText();
                String correo = txtCorreo.getText();
                int edad = Integer.parseInt(spEdad.getValue().toString());
                String rol = cboRoles.getSelectedItem().toString();
                String usuario =txtUsuario.getText();
                String contrasena=txtContrasenaUsuario.getText();
                if(listaUsuario.parametroVacio(nombre)&&listaUsuario.parametroVacio(apellido)&&
                        listaUsuario.parametroVacio(correo)&&listaUsuario.parametroVacio(spEdad.getValue().toString())&&
                        listaUsuario.parametroVacio(rol)&&listaUsuario.parametroVacio(usuario)&&
                        listaUsuario.parametroVacio(contrasena)){
                    if(listaUsuario.validarContrasena(contrasena)){
                        if(listaUsuario.validarCorreo(correo)){
                            if (listaUsuario.validarEdad(edad)){
                                try {
                                    listaUsuario.agregarUsuarioSiEsUnico(new Usuario(nombre, apellido,edad,rol,correo,usuario,contrasena));
                                    JOptionPane.showMessageDialog(null, "nuevo registro");
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, ex.getMessage());
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"LOS MENORES DE EDAD NO PUEDEN REGISTRARSE");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Formato de correo invalido");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Formato de la contrasena invalida");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"ALGUNO DE LOS PARAMETROS ESTA VACIO");
                }

            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarJlist();
            }
        });
        lUsuarios.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (lUsuarios.getSelectedIndex() != -1) {
                    int indice = lUsuarios.getSelectedIndex();
                    Usuario usuario = listaUsuario.getUsuarios().get(indice);
                    txtActNombre.setText("" + usuario.getNombre());
                    txtActApellido.setText(usuario.getApellido());
                    txtActCorreo.setText("" + usuario.getCorreo());
                    txtActEdad.setText("" + usuario.getEdad());
                    txtUsuarioActualizar.setText(""+usuario.getUsuario());
                    txtContrasenaActualizar.setText(""+usuario.getContrasena());
                    cboActualizarRol.setSelectedItem(""+usuario.getRol());
                }
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtActNombre.getText();
                String apellido = txtActApellido.getText();
                String correo = txtActCorreo.getText();
                String rol = cboActualizarRol.getSelectedItem().toString();
                String usuario1 =txtUsuarioActualizar.getText();
                String contrasena=txtContrasenaActualizar.getText();
                int edad = Integer.parseInt(txtActEdad.getText());
                //Validacion-------------------------------------------
                if(listaUsuario.parametroVacio(nombre)&&listaUsuario.parametroVacio(apellido)&&
                        listaUsuario.parametroVacio(correo)&&listaUsuario.parametroVacio(spEdad.getValue().toString())&&
                        listaUsuario.parametroVacio(rol)&&listaUsuario.parametroVacio(usuario1)&&
                        listaUsuario.parametroVacio(contrasena)){
                    if(listaUsuario.validarContrasena(contrasena)){
                        if(listaUsuario.validarCorreo(correo)){
                            if (listaUsuario.validarEdad(edad)){
                                Usuario usuario = new Usuario(nombre, apellido,edad,rol,correo,usuario1,contrasena);
                                try {
                                    if(listaUsuario.validarUsuarioSiEsUnico(usuario)){
                                        if (listaUsuario.actualizar(usuario)) {
                                            JOptionPane.showMessageDialog(null,
                                                    "Elemento actualizado");
                                            llenarJlist();
                                        } else{
                                            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
                                        }
                                    }
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null,ex.getMessage());
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"LOS MENORES DE EDAD NO PUEDEN REGISTRARSE");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Formato de correo invalido");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Formato de la contrasena invalida");
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"ALGUNO DE LOS PARAMETROS ESTA VACIO");
                }
                //----------------------------------------------------
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = lUsuarios.getSelectedIndex();
                if (selectedIndex != -1) {
                    // Obtiene el usuario seleccionado del modelo de la lista
                    Usuario usuarioSeleccionado = ((DefaultListModel<Usuario>) lUsuarios.getModel()).getElementAt(selectedIndex);
                    String correoUsuario = usuarioSeleccionado.getCorreo();

                    try {
                        // Llama a un método para eliminar el usuario de la lista de usuarios basándose en el correo
                        listaUsuario.eliminarUsuarioPorCorreo(correoUsuario);
                        // Eliminar visualmente el usuario de la lista en la GUI
                        ((DefaultListModel<Usuario>) lUsuarios.getModel()).remove(selectedIndex);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un usuario para eliminar");
                }
                llenarJlist(); // Actualizar la lista visual
            }
        });


        btnPresupuesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double nuevoPresupuesto = Double.parseDouble(txtPresupuesto.getText());
                    JOptionPane.showMessageDialog(null, "Presupuesto actualizado a: " + nuevoPresupuesto);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: Ingresa un valor numérico válido para el presupuesto.");
                }
            }
        });
        btnInicio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(listaUsuario.InicioDeSesion(txtUsuariose.getText(),txtContrasena.getText())){
                        JOptionPane.showMessageDialog(null,"INICIO DE SESION EXITOSO");
                        JPANEL.setEnabled(true);
                    }else{
                        JOptionPane.showMessageDialog(null,"ERROR EN INICIO DE SESION");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        });
        btnRecuperar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelRecuperar.setVisible(true);
                txtCorreoRecuperacion.setVisible(true);
                btnRecuperacionCredenciales.setVisible(true);
                btnCancelar.setVisible(true);
            }
        });
        btnRecuperacionCredenciales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = txtCorreoRecuperacion.getText(); //
                Usuario usuarioEncontrado = listaUsuario.buscarUsuarioPorCorreo(correo);
                if (usuarioEncontrado != null) {
                    JOptionPane.showMessageDialog(null, "Usuario encontrado " + usuarioEncontrado.recuperarUsuario());
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario no encontrado con el correo: " + correo);
                }
            }
        });
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelRecuperar.setVisible(false);
                txtCorreoRecuperacion.setVisible(false);
                btnRecuperacionCredenciales.setVisible(false);
                btnCancelar.setVisible(false);
            }
        });
        btnPresupuestoAnualGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelPresupuestoGeneral.setText("" + listaPresupuesto.calcularPromedioGeneral());
            }
        });
        btnVerPromedioIndividualGeneral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePresupuesto= cboPresupuestoIndividualAnual.getSelectedItem().toString();
                Float promedio = listaPresupuesto.calcularPromedioPorNombre(nombrePresupuesto);
                if (promedio != null) {
                    // Mostrar el promedio, por ejemplo, en un JOptionPane
                    labelPresupuestoIndividualGneral.setText(""+promedio);
                } else {
                    // Mensaje si no se encontraron presupuestos con ese nombre
                    JOptionPane.showMessageDialog(null, "No se enxtcontraron presupuestos con el nombre: " + nombrePresupuesto);
                }
            }
        });
        btnPresupuesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Float.parseFloat((txtPresupuesto.getText()))<0){
                    JOptionPane.showMessageDialog(null,"INGRESE UN PROSUPUESTO VALIDO");

                }else{
                    if (Float.parseFloat(txtPresupuesto.getText())<listaPresupuesto.calcularPromedioGeneral()){
                        JOptionPane.showMessageDialog(null,"-----ADVERTENCIA-----\n" +
                                "EL PRESUPUESTO SELECCIONADO ES MENOR AL PRESUPUESTO PROMEDIO HISTORICO, TENGA EN CUENTA ESTO AL MOMENTO DE SELECCIONAR EL PRESUPUESTO");
                    }
                    distribucionPresupuesto.distribuirPresupuestoAnual(Float.parseFloat(txtPresupuesto.getText()));
                    textArea1.setText(distribucionPresupuesto.imprimirEnTextArea());
                }
            }
        });
        btnJlistTodoAnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llenarJlistPre();
            }
        });
        //--------------------------------------------Actualizar presupuesto--------------------------
        btnActualizarGestionPresuuesto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombrePresupuesto = cboGestioonTiposPresupuesto.getSelectedItem().toString();
                float valorPresupuesto = Float.parseFloat(txtGestionPrsupuesto.getText());
                int anio = Integer.parseInt(cboAniosPresupuesto.getSelectedItem().toString());
                int id = Integer.parseInt(txtId.getText());
                Presupuesto presupuesto = new Presupuesto(nombrePresupuesto,valorPresupuesto,anio,id);
                if(listaPresupuesto.actualizarPre(presupuesto)) {
                    JOptionPane.showMessageDialog(null,
                            "Elemento actualizado");
                    llenarJlistPre();
                }
                else
                    JOptionPane.showMessageDialog(null,
                            "No se pudo actualizar");
            }
        });
        listTodosAnos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(listTodosAnos.getSelectedIndex()!=-1){
                    int indice = listTodosAnos.getSelectedIndex();
                    Presupuesto presupuesto = listaPresupuesto.getPresupuestos().get(indice);
                    cboGestioonTiposPresupuesto.setSelectedItem(""+presupuesto.getNombrePresupuesto());
                    cboAniosPresupuesto.setSelectedItem(""+presupuesto.getAnio());
                    txtGestionPrsupuesto.setText(""+presupuesto.getValorPresupuesto());
                    txtId.setText(""+presupuesto.getId());
                }
            }
        });
    }

}