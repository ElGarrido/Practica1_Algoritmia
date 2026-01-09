package practica1algoritmia;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionListener;
// Importamos tu lógica
import practica1algoritmia.GestionarColegio;
import practica1algoritmia.Asignaturas.*;
import practica1algoritmia.Cursos.*;
import practica1algoritmia.Cursos.FP.FP;
import practica1algoritmia.Cursos.Bachiller.Bachiller;
import practica1algoritmia.Estudiante;
import practica1algoritmia.Listas.*;
import practica1algoritmia.Interfaces.*;
import practica1algoritmia.Nodo;

public class VentanaPrincipal extends JFrame {

    private JPanel panelContenido;
    private JPanel panelMenu;

    private final Color COLOR_FONDO_APP = new Color(236, 240, 241); // Gris claro
    private final Color COLOR_MENU = new Color(44, 62, 80);         // Azul oscuro
    private final Color COLOR_HEADER = new Color(52, 73, 94);       // Azul grisáceo

    // Colores para las tarjetas
    private final Color COLOR_CARD_ESTUDIANTE = new Color(255, 255, 255);
    private final Color COLOR_BORDER_ESTUDIANTE = new Color(52, 152, 219); // Azul 

    private final Color COLOR_CARD_CURSO = new Color(255, 255, 255);
    private final Color COLOR_BORDER_CURSO = new Color(230, 126, 34);      // Naranja

    private final Color COLOR_CARD_ASIG = new Color(255, 255, 255);
    private final Color COLOR_BORDER_ASIG = new Color(46, 204, 113);       // Verde

    public VentanaPrincipal() {
        // Configuración Ventana
        setTitle("Sistema de Gestión Escolar");
        setSize(1200, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Superior
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.setBackground(COLOR_HEADER);
        JLabel title = new JLabel("PRÁCTICA 1 - GESTIÓN ESCOLAR");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);
        add(header, BorderLayout.NORTH);

        // Panel Central 
        panelContenido = new JPanel();

        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBackground(COLOR_FONDO_APP);
        panelContenido.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor


        JScrollPane scroll = new JScrollPane(panelContenido);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16); 
        add(scroll, BorderLayout.CENTER);

        // Menú Lateral (Botones)
        panelMenu = new JPanel();
        panelMenu.setBackground(COLOR_MENU);
        panelMenu.setLayout(new GridLayout(11, 1, 0, 5));
        panelMenu.setPreferredSize(new Dimension(260, 0));
        panelMenu.setBorder(new EmptyBorder(10, 10, 10, 10));

        crearMenuBotones();
        add(panelMenu, BorderLayout.WEST);


        mostrarMensajeBienvenida();
    }

    //  MENÚ 
    private void crearMenuBotones() {
        agregarBoton("Alta Curso", e -> opcionAltaCurso());
        agregarBoton("Alta Asignatura", e -> opcionAltaAsignatura());
        agregarBoton("Alta Estudiante", e -> opcionAltaEstudiante());
        agregarBoton(" Matricular", e -> opcionMatricular());

        agregarBoton("Baja Curso", e -> opcionBajaCurso());
        agregarBoton("Baja Asignatura", e -> opcionBajaAsignatura());

        agregarBoton("Listar por Curso", e -> opcionListarAsignaturasCurso());
        agregarBoton("Listar por Asignatura", e -> opcionListarCursoDeAsignatura());
        agregarBoton("Listar por Alumno", e -> opcionListarAsignaturasEstudiante());

        JButton btnSalir = new JButton("Salir");
        estilizarBoton(btnSalir);
        btnSalir.setBackground(new Color(192, 57, 43));
        btnSalir.addActionListener(e -> System.exit(0));
        panelMenu.add(btnSalir);
    }

    private void agregarBoton(String texto, ActionListener accion) {
        JButton btn = new JButton(texto);
        estilizarBoton(btn);
        btn.addActionListener(accion);
        panelMenu.add(btn);
    }

    private void estilizarBoton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(52, 73, 94));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(5, 15, 5, 5));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //   TARJETAS VISUALES
    private void limpiarContenido(String tituloSeccion) {
        panelContenido.removeAll();

        JLabel lblTitulo = new JLabel(tituloSeccion);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(Color.DARK_GRAY);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelContenido.add(lblTitulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 15))); 

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // TARJETA DE ESTUDIANTE
    private void agregarTarjetaEstudiante(Estudiante est) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD_ESTUDIANTE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80)); 
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, COLOR_BORDER_ESTUDIANTE), 
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblNombre = new JLabel(est.getNombre());
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblDni = new JLabel("DNI: " + est.getDni());
        lblDni.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lblDni.setForeground(Color.GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(lblNombre);
        infoPanel.add(lblDni);

        card.add(infoPanel, BorderLayout.CENTER);

        panelContenido.add(card);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10))); // Separación entre tarjetas
    }

    // TARJETA DE CURSO
    private void agregarTarjetaCurso(Curso c) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD_CURSO);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, COLOR_BORDER_CURSO), 
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblNombre = new JLabel(c.getNombre() + " (" + c.getIdentificador() + ")");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JLabel lblTipo = new JLabel(c.getDescripcionTipo());
        lblTipo.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblTipo.setForeground(Color.DARK_GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(lblNombre);
        infoPanel.add(lblTipo);

        card.add(infoPanel, BorderLayout.CENTER);

        panelContenido.add(card);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    // TARJETA DE ASIGNATURA
    private void agregarTarjetaAsignatura(Asignatura asig, String listaAlumnos) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD_ASIG);

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 5, 0, 0, COLOR_BORDER_ASIG), 
                new EmptyBorder(10, 15, 10, 15)
        ));

        JLabel lblNombre = new JLabel(asig.getNombre() + " [" + asig.getIdentificador() + "]");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 15));

        JLabel lblDetalle = new JLabel(asig.getDescripcionExtra());
        lblDetalle.setForeground(new Color(100, 100, 100));

        JTextArea txtAlumnos = new JTextArea("Alumnos: " + listaAlumnos);
        txtAlumnos.setLineWrap(true);
        txtAlumnos.setWrapStyleWord(true);
        txtAlumnos.setEditable(false);
        txtAlumnos.setOpaque(false);
        txtAlumnos.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        txtAlumnos.setForeground(new Color(50, 50, 50));

        JPanel infoPanel = new JPanel(new GridLayout(2, 1)); // Nombre y detalle arriba
        infoPanel.setOpaque(false);
        infoPanel.add(lblNombre);
        infoPanel.add(lblDetalle);

        card.add(infoPanel, BorderLayout.NORTH);
        card.add(txtAlumnos, BorderLayout.CENTER);

        panelContenido.add(card);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    // BOTONES
    
    // ALTA CURSO
    private void opcionAltaCurso() {

        String[] tipos = {"FP", "Bachiller"};
        int tipoSel = JOptionPane.showOptionDialog(this, "Seleccione Tipo:", "Alta Curso",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
        if (tipoSel == -1) {
            return;
        }

        JTextField txtCod = new JTextField();
        JTextField txtNom = new JTextField();
        Object[] form = {"Código:", txtCod, "Nombre:", txtNom};
        if (JOptionPane.showConfirmDialog(this, form, "Datos", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) {
            return;
        }

        String res = "";
        if (tipoSel == 0) { // FP
            String[] esps = {"MECANICA", "ELECTRONICA", "INFORMATICA"};
            String esp = (String) JOptionPane.showInputDialog(this, "Especialidad:", "FP", JOptionPane.QUESTION_MESSAGE, null, esps, esps[0]);
            if (esp != null) {
                res = GestionarColegio.altaCursoFP(txtCod.getText(), txtNom.getText(), FP.Especialidad.valueOf(esp));
            }
        } else { // Bach
            String[] nivs = {"PRIMERO", "SEGUNDO"};
            String niv = (String) JOptionPane.showInputDialog(this, "Nivel:", "Bachiller", JOptionPane.QUESTION_MESSAGE, null, nivs, nivs[0]);
            if (niv != null) {
                res = GestionarColegio.altaCursoBach(txtCod.getText(), txtNom.getText(), Bachiller.Nivel.valueOf(niv));
            }
        }
        if (!res.isEmpty()) {
            mostrarNotificacion(res);
        }
    }

    // ALTA ASIGNATURA
    private void opcionAltaAsignatura() {
        JTextField txtIdCurso = new JTextField();
        JTextField txtCod = new JTextField();
        JTextField txtNom = new JTextField();
        Object[] form = {"ID Curso Padre:", txtIdCurso, "ID Asignatura:", txtCod, "Nombre:", txtNom};

        if (JOptionPane.showConfirmDialog(this, form, "Alta Asignatura", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String[] opts = {"Obligatoria", "Optativa"};
            int tipo = JOptionPane.showOptionDialog(this, "Tipo:", "Tipo", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opts, opts[0]);

            String res = "";
            if (tipo == 0) {
                String creds = JOptionPane.showInputDialog("Créditos:");
                if (creds != null) {
                    try {
                        res = GestionarColegio.altaAsignaturaObligatoria(txtIdCurso.getText(), txtCod.getText(), txtNom.getText(), Integer.parseInt(creds));
                    } catch (Exception e) {
                        res = "Error: Datos inválidos";
                    }
                }
            } else if (tipo == 1) {
                String[] perfs = {"TEORICO", "PRACTICO"};
                int p = JOptionPane.showOptionDialog(this, "Perfil:", "Perfil", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, perfs, perfs[0]);
                if (p != -1) {
                    Optativa.Perfil perfil = (p == 0) ? Optativa.Perfil.TEORICO : Optativa.Perfil.PRACTICO;
                    res = GestionarColegio.altaAsignaturaOptativa(txtIdCurso.getText(), txtCod.getText(), txtNom.getText(), perfil);
                }
            }
            if (!res.isEmpty()) {
                mostrarNotificacion(res);
            }
        }
    }

    // ALTA ESTUDIANTE
    private void opcionAltaEstudiante() {
        JTextField txtNom = new JTextField();
        JTextField txtDNI = new JTextField();
        Object[] form = {"Nombre:", txtNom, "DNI:", txtDNI};
        if (JOptionPane.showConfirmDialog(this, form, "Alta Estudiante", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String res = GestionarColegio.altaEstudiante(txtNom.getText(), txtDNI.getText());
            mostrarNotificacion(res);
        }
    }

    // MATRICULAR
    private void opcionMatricular() {
        JTextField txtDNI = new JTextField();
        JTextField txtCod = new JTextField();
        Object[] form = {"DNI Estudiante:", txtDNI, "Cód. Asignatura:", txtCod};
        if (JOptionPane.showConfirmDialog(this, form, "Matricular", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            String res = GestionarColegio.matricular(txtDNI.getText(), txtCod.getText());
            mostrarNotificacion(res);
        }
    }

    // BAJAS
    private void opcionBajaCurso() {
        String cod = JOptionPane.showInputDialog("Código del Curso a eliminar:");
        if (cod != null) {
            if (JOptionPane.showConfirmDialog(this, "¿Seguro?") == JOptionPane.YES_OPTION) {
                String log = GestionarColegio.bajaCursoCascada(cod);
                mostrarNotificacion(log);
            }
        }
    }

    private void opcionBajaAsignatura() {
        String cod = JOptionPane.showInputDialog("Código de Asignatura a eliminar:");
        if (cod != null) {
            if (JOptionPane.showConfirmDialog(this, "¿Seguro?") == JOptionPane.YES_OPTION) {
                String log = GestionarColegio.bajaAsignaturaCascada(cod);
                mostrarNotificacion(log);
            }
        }
    }

    // LISTADOS VISUALES 
    
    //LISTAR ASIGNATURAS DE UN CURSO
    private void opcionListarAsignaturasCurso() {
        String cod = JOptionPane.showInputDialog("Código del Curso:");
        if (cod == null) {
            return;
        }

        Curso c = (Curso) GestionarColegio.catalogoCursos.buscar(cod);
        if (c == null) {
            mostrarNotificacion("Curso no encontrado");
            return;
        }

        limpiarContenido("Asignaturas del curso: " + c.getNombre());

        ListaAsignatura lista = c.asignaturasDelCurso;
        boolean vacio = true;

        for (Asignatura a : lista.asignaturas) {
            if (a != null) {
                vacio = false;

                StringBuilder sb = new StringBuilder();
                Nodo n = a.alumnosMatriculados.getPrimero();
                while (n != null) {
                    sb.append(((Interface_Elemento) n.getInfo())).append(", ");
                    n = n.getSeg();
                }
                String strAlumnos = sb.length() > 0 ? sb.toString() : "(Ninguno)";

                agregarTarjetaAsignatura(a, strAlumnos);
            }
        }
        if (vacio) {
            mostrarAvisoVacio();
        }
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // INFO ASIGNATURA
    private void opcionListarCursoDeAsignatura() {
        String cod = JOptionPane.showInputDialog("Código de Asignatura:");
        if (cod == null) {
            return;
        }
        Asignatura a = (Asignatura) GestionarColegio.catalogoAsignaturas.buscar(cod);
        if (a == null) {
            mostrarNotificacion("Asignatura no encontrada");
            return;
        }

        limpiarContenido("Información Detallada");

        StringBuilder sb = new StringBuilder();
        Nodo n = a.alumnosMatriculados.getPrimero();
        while (n != null) {
            sb.append("• ").append(n.getInfo().toString()).append("\n"); // Nombre alumno
            n = n.getSeg();
        }

        agregarTarjetaAsignatura(a, sb.length() > 0 ? "\n" + sb.toString() : "Ninguno");
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    // INFO ALUMNO
    private void opcionListarAsignaturasEstudiante() {
        String dni = JOptionPane.showInputDialog("DNI Estudiante:");
        if (dni == null) {
            return;
        }
        Estudiante est = (Estudiante) GestionarColegio.catalogoEstudiantes.buscar(dni);
        if (est == null) {
            mostrarNotificacion("Estudiante no encontrado");
            return;
        }

        limpiarContenido("Expediente de: " + est.getNombre());
        agregarTarjetaEstudiante(est); // Mostramos su ficha primero

        JLabel lblMatricula = new JLabel("Matrículas Activas:");
        lblMatricula.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblMatricula.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(lblMatricula);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 10)));

        Nodo actual = est.asignaturasCursadas.getPrimero();
        boolean vacio = true;
        while (actual != null) {
            vacio = false;
            ReferenciaAsignatura ref = (ReferenciaAsignatura) actual.getInfo();
            Asignatura asig = ref.getAsignatura();
            // Reutilizamos la tarjeta de asignatura visualmente
            agregarTarjetaAsignatura(asig, "Cursando actualmente");
            actual = actual.getSeg();
        }
        if (vacio) {
            mostrarAvisoVacio();
        }
        panelContenido.revalidate();
        panelContenido.repaint();
    }


    private void mostrarNotificacion(String msg) {
        limpiarContenido("Resultado de la operación");
        JTextArea lbl = new JTextArea(msg);
        lbl.setEditable(false);
        lbl.setLineWrap(true);
        lbl.setWrapStyleWord(true);
        lbl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        lbl.setFont(new Font("Consolas", Font.PLAIN, 14));
        panelContenido.add(lbl);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void mostrarMensajeBienvenida() {
        limpiarContenido(" ");


        JLabel lbl = new JLabel("<html><body><center><h2>Bienvenido</h2>");

        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel panelCentrador = new JPanel(new GridBagLayout());
        panelCentrador.setBackground(panelContenido.getBackground());
        // Añadimos el label a este panel. Se irá al centro exacto.
        panelCentrador.add(lbl);

        panelCentrador.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        panelContenido.add(panelCentrador);

        panelContenido.revalidate();
        panelContenido.repaint();
    }

    private void mostrarAvisoVacio() {
        JLabel lbl = new JLabel("(No hay datos para mostrar)");
        lbl.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lbl.setForeground(Color.GRAY);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(lbl);
    }

    public static void main(String[] args) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
