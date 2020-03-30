/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import konquest.backed.manejadores.ManejadorEscrituraArchivo;
import konquest.backed.objetos.*;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author bryan
 */
public class DialogCrearYEditarMapa extends javax.swing.JDialog {
    private Jugadores jugador;
    private Jugadores jugadorApuntador;
    private Planetas planeta;
    private Planetas planetaApuntador;
    private Mapa mapa; 
    private List<Planetas> listaPlanetasRelleno;
    private List<Jugadores> listaJugadoresRelleno;
    private List<Planetas> listaPlanetasJugadores;
    private List<Planetas> listaPlanetasNuetrales;
    private List<Jugadores> listaJugadores;
    private ObservableList<Planetas> observableListPlanetas;
    private ObservableList<Jugadores> observableListJugadores;
    private boolean editar;
    private ManejadorEscrituraArchivo manejadorEscrituraArchivo;
    
    /**
     * Constructor para crear mapas
     * @param parent
     * @param modal
     * @param editar
     */
    public DialogCrearYEditarMapa(java.awt.Frame parent, boolean modal, boolean editar) {
        super(parent, modal);
        this.listaJugadoresRelleno = new ArrayList<>();
        this.listaPlanetasRelleno = new ArrayList<>();
        this.listaPlanetasNuetrales = new ArrayList<>();
        this.listaPlanetasJugadores = new ArrayList<>();
        this.listaJugadores = new ArrayList<>();
        this.observableListJugadores = ObservableCollections.observableList(this.listaJugadoresRelleno);
        this.observableListPlanetas = ObservableCollections.observableList(this.listaPlanetasRelleno);
        this.editar = editar;
        initComponents();
        this.btnEditar.setVisible(false);
        this.setLocationRelativeTo(null);
        
    }

    /* Constructor para editar mapas */
    public DialogCrearYEditarMapa(java.awt.Frame parent, boolean modal, boolean editar, List<Planetas> listaPlanetasJugadores, List<Planetas> listaPlanetasNeutrales, List<Jugadores> listaJugadores, Mapa mapa, String pathSeleccionado) {
        super(parent, modal);
        this.listaJugadoresRelleno = new ArrayList<>();
        this.listaPlanetasRelleno = new ArrayList<>();
        this.observableListJugadores = ObservableCollections.observableList(this.listaJugadoresRelleno);
        this.observableListPlanetas = ObservableCollections.observableList(this.listaPlanetasRelleno);
        this.listaPlanetasJugadores = listaPlanetasJugadores;
        this.listaPlanetasNuetrales = listaPlanetasNeutrales;
        this.listaJugadores = listaJugadores;
        this.mapa = mapa;
        this.editar = editar;
        initComponents();
        this.lblUbicacion.setText(pathSeleccionado);
        agregarDatosKonquest();
        this.btnGuardar.setVisible(false);
        this.btnUbicacion.setVisible(false);
        this.setLocationRelativeTo(null);
    }

    public Jugadores getJugador() {
        return jugador;
    }

    public void setJugador(Jugadores jugador) throws CloneNotSupportedException {
        limpiarCampoJugadores();
        Jugadores anterior = jugador;
        this.jugadorApuntador = jugador;
        if (jugador != null) {
            System.out.println("Paso");
            this.jugador = jugador.clone();
            txtNombreJugador.setText(this.jugador.getNombre());
            if (!checkBoxNeutral.isSelected()) {
                txtJugadorSeleccionado.setText(this.jugador.getNombre());
            }
            comboBoxTipo.setSelectedItem(this.jugador.tipoCadena());
        }
        firePropertyChange("Jugador Seleccionado", anterior, this.jugador);
        actualizarListaJugadores(listaJugadores);
    }

    public Planetas getPlaneta() {
        return planeta;
    }

    public void setPlaneta(Planetas planeta) throws CloneNotSupportedException {
        limpiarCamposPlanetas();
        Planetas anterior = planeta; 
        this.planetaApuntador = planeta;
        if (planeta != null) {
            this.planeta = planeta.clone();
            txtNombrePlaneta.setText(this.planeta.getNombre());
            if (this.planeta.getProduccion() != 0) {
                txtProduccionPlaneta.setText(Integer.toString(this.planeta.getProduccion()));
            }
            txtNavesPlaneta.setText(Integer.toString(this.planeta.getNaves()));
            String porcentaje = Double.toString(this.planeta.getPorcentajeMuertes()).substring(2);
            txtPorcentajePlaneta.setText(porcentaje);
            if (this.planeta.getJugador() != null) {
                txtJugadorSeleccionado.setText(this.planeta.getJugador().getNombre());
                actualizarListaPlanetas(listaPlanetasJugadores);
            } else {
                checkBoxNeutral.setSelected(true);
                actualizarListaPlanetas(listaPlanetasNuetrales);
            }
        } firePropertyChange("Planeta Selccionado", anterior, this.planetaApuntador);
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public List<Planetas> getListaPlanetasRelleno() {
        return listaPlanetasRelleno;
    }

    public void setListaPlanetasRelleno(List<Planetas> listaPlanetasRelleno) {
        this.listaPlanetasRelleno = listaPlanetasRelleno;
    }

    public List<Jugadores> getListaJugadoresRelleno() {
        return listaJugadoresRelleno;
    }

    public void setListaJugadoresRelleno(List<Jugadores> listaJugadoresRelleno) {
        this.listaJugadoresRelleno = listaJugadoresRelleno;
    }

    public List<Planetas> getListaPlanetasJugadores() {
        return listaPlanetasJugadores;
    }

    public void setListaPlanetasJugadores(List<Planetas> listaPlanetasJugadores) {
        this.listaPlanetasJugadores = listaPlanetasJugadores;
    }

    public List<Planetas> getListaPlanetasNuetrales() {
        return listaPlanetasNuetrales;
    }

    public void setListaPlanetasNuetrales(List<Planetas> listaPlanetasNuetrales) {
        this.listaPlanetasNuetrales = listaPlanetasNuetrales;
    }

    public List<Jugadores> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(List<Jugadores> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public ObservableList<Planetas> getObservableListPlanetas() {
        return observableListPlanetas;
    }

    public void setObservableListPlanetas(ObservableList<Planetas> observableListPlanetas) {
        this.observableListPlanetas = observableListPlanetas;
    }

    public ObservableList<Jugadores> getObservableListJugadores() {
        return observableListJugadores;
    }

    public void setObservableListJugadores(ObservableList<Jugadores> observableListJugadores) {
        this.observableListJugadores = observableListJugadores;
    }

    public boolean isEditar() {
        return editar;
    }

    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        panelMapa = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblTmaño = new javax.swing.JLabel();
        lblFila = new javax.swing.JLabel();
        lblColumna = new javax.swing.JLabel();
        lblPlanetasNeutrales = new javax.swing.JLabel();
        lblColumna3 = new javax.swing.JLabel();
        checkMostraNaves = new javax.swing.JCheckBox();
        checkMostraEstadisticas = new javax.swing.JCheckBox();
        lblProduccion = new javax.swing.JLabel();
        lblOpciones = new javax.swing.JLabel();
        checkMapaCiego = new javax.swing.JCheckBox();
        checkAcumular = new javax.swing.JCheckBox();
        txtNombre = new javax.swing.JTextField();
        txtFila = new javax.swing.JTextField();
        txtColumna = new javax.swing.JTextField();
        txtProduccion = new javax.swing.JTextField();
        txtPlanetasNeutrales = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        checkBoxAlAzar = new javax.swing.JCheckBox();
        lblPlanetasNeutrales1 = new javax.swing.JLabel();
        txtFinalizacion = new javax.swing.JTextField();
        btnUbicacion = new javax.swing.JButton();
        lblUbicacion = new javax.swing.JLabel();
        panelJugadores = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaJugadores = new javax.swing.JTable();
        lblAgregar = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblNombreJugador = new javax.swing.JLabel();
        txtNombreJugador = new javax.swing.JTextField();
        comboBoxTipo = new javax.swing.JComboBox<>();
        btnEditarJugador = new javax.swing.JButton();
        btnAgregarJugador = new javax.swing.JButton();
        btnEliminarJugador = new javax.swing.JButton();
        btnEliminarJugador2 = new javax.swing.JButton();
        btnLimpiarJugadoes = new javax.swing.JButton();
        panelPlanetas = new javax.swing.JPanel();
        lblNombrePlaneta = new javax.swing.JLabel();
        lblNaves = new javax.swing.JLabel();
        lblProduccionPlaneta = new javax.swing.JLabel();
        lblPorcentaje = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPlanetasJugadores = new javax.swing.JTable();
        btnAgregarPlaneta = new javax.swing.JButton();
        btnEliminarPlaneta = new javax.swing.JButton();
        btnEditarPlaneta = new javax.swing.JButton();
        txtNombrePlaneta = new javax.swing.JTextField();
        txtProduccionPlaneta = new javax.swing.JTextField();
        txtNavesPlaneta = new javax.swing.JTextField();
        txtPorcentajePlaneta = new javax.swing.JTextField();
        lblJugador = new javax.swing.JLabel();
        txtJugadorSeleccionado = new javax.swing.JTextField();
        btnNeutrales = new javax.swing.JButton();
        btnPlanetas = new javax.swing.JButton();
        checkBoxNeutral = new javax.swing.JCheckBox();
        btnLimpiarPlanetas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar - Crear");
        setPreferredSize(new java.awt.Dimension(1010, 575));

        panelMapa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Mapa", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        lblNombre.setText("Nombre*:");

        lblTmaño.setText("Tamaño*:");

        lblFila.setText("Fila*:");

        lblColumna.setText("Columna*:");

        lblPlanetasNeutrales.setText("Planetas Neutrales*:");

        lblColumna3.setText("NEUTRALES:");

        checkMostraNaves.setText("Mostrar Naves");

        checkMostraEstadisticas.setText("Mostrar Estadisticas");

        lblProduccion.setText("Produccion*:");

        lblOpciones.setText("OPCIONES:");

        checkMapaCiego.setText("Mapa Ciego");
        checkMapaCiego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMapaCiegoActionPerformed(evt);
            }
        });

        checkAcumular.setText("Acumular");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtFila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilaKeyTyped(evt);
            }
        });

        txtColumna.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtColumnaKeyTyped(evt);
            }
        });

        txtProduccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProduccionKeyTyped(evt);
            }
        });

        txtPlanetasNeutrales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlanetasNeutralesKeyTyped(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        checkBoxAlAzar.setText("Al Azar");

        lblPlanetasNeutrales1.setText("Finalización:");

        txtFinalizacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFinalizacionKeyTyped(evt);
            }
        });

        btnUbicacion.setText("Ubicación");
        btnUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbicacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMapaLayout = new javax.swing.GroupLayout(panelMapa);
        panelMapa.setLayout(panelMapaLayout);
        panelMapaLayout.setHorizontalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMapaLayout.createSequentialGroup()
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkMostraEstadisticas)
                            .addComponent(checkMostraNaves)))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre))
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFila)
                                    .addComponent(lblTmaño))
                                .addGap(51, 51, 51)
                                .addComponent(txtFila))
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(lblColumna)
                                .addGap(15, 15, 15)
                                .addComponent(txtColumna))
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addComponent(lblColumna3)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addComponent(lblPlanetasNeutrales1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addComponent(lblPlanetasNeutrales)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPlanetasNeutrales, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(lblProduccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMapaLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(checkBoxAlAzar)
                                .addGap(47, 47, 47)))
                        .addComponent(txtProduccion))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelMapaLayout.createSequentialGroup()
                                .addComponent(lblOpciones)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(checkMapaCiego)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(checkAcumular))
                    .addGroup(panelMapaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMapaLayout.setVerticalGroup(
            panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMapaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTmaño)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFila)
                    .addComponent(txtFila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColumna)
                    .addComponent(txtColumna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblColumna3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkMostraNaves)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkMostraEstadisticas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProduccion)
                    .addComponent(txtProduccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBoxAlAzar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPlanetasNeutrales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlanetasNeutrales))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFinalizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPlanetasNeutrales1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblOpciones)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkMapaCiego)
                    .addComponent(checkAcumular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUbicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelJugadores.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Jugadores", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListJugadores}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaJugadores);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tipoString}"));
        columnBinding.setColumnName("Tipo");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${jugador}"), tablaJugadores, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(tablaJugadores);

        lblAgregar.setText("DATOS:");

        lblTipo.setText("Tipo*:");

        lblNombreJugador.setText("Nombre*:");

        txtNombreJugador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreJugadorKeyTyped(evt);
            }
        });

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HUMANO", "FACIL", "DIFICIL" }));

        btnEditarJugador.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnEditarJugador.setText("Editar");
        btnEditarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarJugadorActionPerformed(evt);
            }
        });

        btnAgregarJugador.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnAgregarJugador.setText("Agregar");
        btnAgregarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarJugadorActionPerformed(evt);
            }
        });

        btnEliminarJugador.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnEliminarJugador.setText("Eliminar");
        btnEliminarJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarJugadorActionPerformed(evt);
            }
        });

        btnEliminarJugador2.setText("Ver Planetas");
        btnEliminarJugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarJugador2ActionPerformed(evt);
            }
        });

        btnLimpiarJugadoes.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        btnLimpiarJugadoes.setText("Limpiar");
        btnLimpiarJugadoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarJugadoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJugadoresLayout = new javax.swing.GroupLayout(panelJugadores);
        panelJugadores.setLayout(panelJugadoresLayout);
        panelJugadoresLayout.setHorizontalGroup(
            panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJugadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelJugadoresLayout.createSequentialGroup()
                        .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreJugador)
                            .addComponent(lblAgregar)
                            .addComponent(lblTipo))
                        .addGap(16, 16, 16)
                        .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelJugadoresLayout.createSequentialGroup()
                                .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarJugador2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(txtNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelJugadoresLayout.createSequentialGroup()
                        .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJugadoresLayout.createSequentialGroup()
                                .addComponent(btnAgregarJugador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditarJugador)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiarJugadoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminarJugador)))
                        .addContainerGap())))
        );
        panelJugadoresLayout.setVerticalGroup(
            panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelJugadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreJugador)
                    .addComponent(txtNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminarJugador2)
                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarJugador)
                    .addComponent(btnEditarJugador)
                    .addComponent(btnEliminarJugador)
                    .addComponent(btnLimpiarJugadoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelPlanetas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Planetas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        lblNombrePlaneta.setText("Nombre*:");

        lblNaves.setText("Naves*:");

        lblProduccionPlaneta.setText("Producción*: ");

        lblPorcentaje.setText("%*:   0 .");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${observableListPlanetas}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, tablaPlanetasJugadores);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${naves}"));
        columnBinding.setColumnName("Naves");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${porcentajeMuertes}"));
        columnBinding.setColumnName("%");
        columnBinding.setColumnClass(Double.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${produccion}"));
        columnBinding.setColumnName("Produccion");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${jugador.nombre}"));
        columnBinding.setColumnName("Jugador");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${planeta}"), tablaPlanetasJugadores, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane3.setViewportView(tablaPlanetasJugadores);

        btnAgregarPlaneta.setText("Agregar");
        btnAgregarPlaneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPlanetaActionPerformed(evt);
            }
        });

        btnEliminarPlaneta.setText("Eliminar");
        btnEliminarPlaneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarPlanetaActionPerformed(evt);
            }
        });

        btnEditarPlaneta.setText("Editar");
        btnEditarPlaneta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPlanetaActionPerformed(evt);
            }
        });

        txtNombrePlaneta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombrePlanetaKeyTyped(evt);
            }
        });

        txtProduccionPlaneta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProduccionPlanetaKeyTyped(evt);
            }
        });

        txtNavesPlaneta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNavesPlanetaKeyTyped(evt);
            }
        });

        txtPorcentajePlaneta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPorcentajePlanetaKeyTyped(evt);
            }
        });

        lblJugador.setText("Jugador*:");

        txtJugadorSeleccionado.setEditable(false);

        btnNeutrales.setText("Mostrar Neutrales");
        btnNeutrales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNeutralesActionPerformed(evt);
            }
        });

        btnPlanetas.setText("Mostrar Planetas");
        btnPlanetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlanetasActionPerformed(evt);
            }
        });

        checkBoxNeutral.setText("NEUTRAL");

        btnLimpiarPlanetas.setText("Limpiar");
        btnLimpiarPlanetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarPlanetasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelPlanetasLayout = new javax.swing.GroupLayout(panelPlanetas);
        panelPlanetas.setLayout(panelPlanetasLayout);
        panelPlanetasLayout.setHorizontalGroup(
            panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlanetasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(panelPlanetasLayout.createSequentialGroup()
                        .addComponent(btnAgregarPlaneta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditarPlaneta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiarPlanetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarPlaneta))
                    .addGroup(panelPlanetasLayout.createSequentialGroup()
                        .addComponent(lblNaves)
                        .addGap(13, 13, 13)
                        .addComponent(txtNavesPlaneta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPorcentaje)
                        .addGap(4, 4, 4)
                        .addComponent(txtPorcentajePlaneta))
                    .addGroup(panelPlanetasLayout.createSequentialGroup()
                        .addComponent(lblNombrePlaneta)
                        .addGap(1, 1, 1)
                        .addComponent(txtNombrePlaneta, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblProduccionPlaneta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProduccionPlaneta))
                    .addGroup(panelPlanetasLayout.createSequentialGroup()
                        .addComponent(btnNeutrales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlanetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelPlanetasLayout.createSequentialGroup()
                        .addComponent(lblJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJugadorSeleccionado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkBoxNeutral, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelPlanetasLayout.setVerticalGroup(
            panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlanetasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarPlaneta)
                    .addComponent(btnEditarPlaneta)
                    .addComponent(btnEliminarPlaneta)
                    .addComponent(btnLimpiarPlanetas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombrePlaneta)
                    .addComponent(lblProduccionPlaneta)
                    .addComponent(txtNombrePlaneta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtProduccionPlaneta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNaves)
                    .addComponent(lblPorcentaje)
                    .addComponent(txtPorcentajePlaneta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNavesPlaneta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJugadorSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblJugador)
                    .addComponent(checkBoxNeutral))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelPlanetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNeutrales)
                    .addComponent(btnPlanetas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelMapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPlanetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelJugadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelPlanetas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelJugadores, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMapa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFilaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilaKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtFilaKeyTyped

    private void txtColumnaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtColumnaKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtColumnaKeyTyped

    private void txtProduccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProduccionKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtProduccionKeyTyped

    private void txtPlanetasNeutralesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlanetasNeutralesKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtPlanetasNeutralesKeyTyped

    private void txtProduccionPlanetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProduccionPlanetaKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtProduccionPlanetaKeyTyped

    private void txtPorcentajePlanetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPorcentajePlanetaKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtPorcentajePlanetaKeyTyped

    private void txtNavesPlanetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNavesPlanetaKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtNavesPlanetaKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (txtNombre.getText().length() == 0) {
            if (!(Character.isLetter(value) || value == '$' || value == '_')) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            if (!(Character.isLetter(value) || Character.isDigit(value) || value == '-' || value == '$' || value == '_')) {
                evt.consume();
                getToolkit().beep();
            }
        }
        
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombrePlanetaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePlanetaKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if ((txtNombrePlaneta.getText().length() >= 3) || !(Character.isLetter(value))) {
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_txtNombrePlanetaKeyTyped

    private void txtNombreJugadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreJugadorKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (txtNombreJugador.getText().length() == 0) {
            if (!(Character.isLetter(value))) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            if ((txtNombreJugador.getText().length() >= 10) || !(Character.isLetter(value) || Character.isDigit(value))) {
                evt.consume();
                getToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtNombreJugadorKeyTyped

    private void btnAgregarPlanetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPlanetaActionPerformed
        // TODO add your handling code here:
        if (comprobarCampoVacioPlanetas()) {
            Planetas agregar;
            if (checkBoxNeutral.isSelected()) {
                if (!buscarExistenciaPlanetas(txtNombrePlaneta.getText())) {
                    agregar = new Planetas();
                    agregar.setNombre(txtNombrePlaneta.getText());
                    agregar.setNaves(Integer.parseInt(txtNavesPlaneta.getText()));
                    agregar.setPorcentajeMuertes(Double.parseDouble("0."+txtPorcentajePlaneta.getText()));
                    if (!txtProduccionPlaneta.getText().isEmpty()) {
                        agregar.setProduccion(Integer.parseInt(txtProduccionPlaneta.getText()));
                    }
                    listaPlanetasNuetrales.add(agregar);
                    actualizarListaPlanetas(listaPlanetasNuetrales);
                    JOptionPane.showMessageDialog(null, "Planeta Agregado");
                    limpiarCamposPlanetas();
                } else {
                    mensajesError("Existe un planeta con el mismo nombre, vuelve a verificar.\nLa entrada es sensible a mayúsculas y minusculas.");
                }
            } else {
                if (!buscarExistenciaPlanetas(txtNombrePlaneta.getText())) {
                    agregar = new Planetas(txtNombrePlaneta.getText(), Integer.parseInt(txtNavesPlaneta.getText()), Integer.parseInt(txtProduccionPlaneta.getText()), Double.parseDouble("0."+txtPorcentajePlaneta.getText()));
                    agregar.setJugador(jugadorApuntador);
                    listaPlanetasJugadores.add(agregar);
                    jugador.getPlanetas().add(agregar);
                    actualizarListaPlanetas(listaPlanetasJugadores);
                    JOptionPane.showMessageDialog(null, "Planeta Agregado");
                    limpiarCamposPlanetas();
                } else {
                    mensajesError("Existe un planeta con el mismo nombre, vuelve a verificar.\nLa entrada es sensible a mayúsculas y minusculas.");
                }
            }
        } else {
            mostrarAdvertencia("<Planetas>");
        }
    }//GEN-LAST:event_btnAgregarPlanetaActionPerformed

    private void btnAgregarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarJugadorActionPerformed
        // TODO add your handling code here:
        if (comprobarCampoVacioJugadores()) {
            listaJugadores.add(new Jugadores(txtNombreJugador.getText(), comboBoxTipo.getSelectedItem().toString(), new ArrayList<>()));
            actualizarListaJugadores(listaJugadores);
            JOptionPane.showMessageDialog(null, "Jugador Agregado");
            limpiarCampoJugadores();
        } else {
            mostrarAdvertencia("<Jugadores>");
        }
    }//GEN-LAST:event_btnAgregarJugadorActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if (comprobarCampoVacioMapa()) {
            if (comprobarJugadoresYPlanetas()) {
                EstructuraKonquest estructuraKonquest;
                Mapa mapaEstructura;
                AtributtosMapa atributtosMapa = new AtributtosMapa();
                atributtosMapa.setiD(txtNombre.getText());
                atributtosMapa.setTamaño(new Tamaño(Integer.parseInt(txtFila.getText()), Integer.parseInt(txtColumna.getText())));
                atributtosMapa.setNeutrales(new Neutrales(checkMostraNaves.isSelected(), checkMostraEstadisticas.isSelected(), Integer.parseInt(txtProduccion.getText())));
                atributtosMapa.setMapaCiego(checkMapaCiego.isSelected());
                atributtosMapa.setAcumular(checkMapaCiego.isSelected());
                atributtosMapa.setAlAzar(checkBoxAlAzar.isSelected());
                if (!txtFinalizacion.getText().isEmpty()) {
                    atributtosMapa.setFinalizacion(Integer.parseInt(txtFinalizacion.getText()));
                }
                if (checkBoxAlAzar.isSelected()) {
                    atributtosMapa.setPlanetasNeutrales(Integer.parseInt(txtPlanetasNeutrales.getText()));
                }
                mapaEstructura = new Mapa(atributtosMapa);
                estructuraKonquest = new EstructuraKonquest();
                estructuraKonquest.setMapa(mapaEstructura);
                estructuraKonquest.setEspacioPlanetas(new Espacio(listaPlanetasJugadores, false));
                estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(listaPlanetasNuetrales, true));
                estructuraKonquest.setJugadores(listaJugadores);
                if ((atributtosMapa.getTamaño().getFilas()*atributtosMapa.getTamaño().getColumnas()) > (listaPlanetasJugadores.size() + listaPlanetasNuetrales.size())) {
                    if (!lblUbicacion.getText().isEmpty()) {
                        try {
                            manejadorEscrituraArchivo = new ManejadorEscrituraArchivo(estructuraKonquest);
                            manejadorEscrituraArchivo.escribirArchivoSalida(lblUbicacion.getText() + "/" + mapaEstructura.getAtributtosMapa().getiD() + ".json");
                            JOptionPane.showMessageDialog(this, "Creado con el nombre: " + mapaEstructura.getAtributtosMapa().getiD()
                            + "\nUbicación: " + lblUbicacion.getText() + "/" + mapaEstructura.getAtributtosMapa().getiD() + ".json");
                            this.setVisible(false);
                        } catch (IOException ex) {
                            mensajesError("No se a encontrado la ubicación.");
                        }
                    } else {
                        mensajesError("Debes de seleccionar una ubicación");
                    }
                    
                } else {
                    mensajesError("Lo siento el tamaño del mapa debe de ser mayor al número de planetas.");
                }
                //ManejadorEscritura
            } else {
                JOptionPane.showMessageDialog(null, "Los Jugadores deben de tener al menos un planeta.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            mostrarAdvertencia("<Mapa>");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtFinalizacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFinalizacionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFinalizacionKeyTyped
    
    private void btnEliminarJugador2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarJugador2ActionPerformed
        // TODO add your handling code here:
        if (jugador != null) {
            JOptionPane.showMessageDialog(null, "Planetas: \n" + infPlanetasJugador(), "Planetas del Jugador", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Debes de Seleccionar un jugador.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarJugador2ActionPerformed

    private void btnPlanetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlanetasActionPerformed
        // TODO add your handling code here:
        actualizarListaPlanetas(listaPlanetasJugadores);
    }//GEN-LAST:event_btnPlanetasActionPerformed

    private void btnNeutralesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNeutralesActionPerformed
        // TODO add your handling code here:
        actualizarListaPlanetas(listaPlanetasNuetrales);
    }//GEN-LAST:event_btnNeutralesActionPerformed

    private void btnLimpiarJugadoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarJugadoesActionPerformed
        // TODO add your handling code here:
        limpiarCampoJugadores();
        jugador = null;
    }//GEN-LAST:event_btnLimpiarJugadoesActionPerformed

    private void btnLimpiarPlanetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarPlanetasActionPerformed
        // TODO add your handling code here:
        limpiarCamposPlanetas();
    }//GEN-LAST:event_btnLimpiarPlanetasActionPerformed

    private void btnEditarPlanetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPlanetaActionPerformed
        // TODO add your handling code here:
        if (comprobarCampoVacioPlanetas()) {
            if (checkBoxNeutral.isSelected()) {
                if(planeta.getNombre().equalsIgnoreCase(txtNombrePlaneta.getText())){
                    planetaApuntador.setNaves(Integer.parseInt(txtNavesPlaneta.getText()));
                    planetaApuntador.setPorcentajeMuertes(Double.parseDouble("0."+txtPorcentajePlaneta.getText()));
                    if (!txtProduccionPlaneta.getText().isEmpty()) {
                        planetaApuntador.setProduccion(Integer.parseInt(txtProduccionPlaneta.getText()));
                    } 
                    if (listaPlanetasJugadores.contains(planetaApuntador)) {
                        planetaApuntador.getJugador().getPlanetas().remove(planetaApuntador);
                        planetaApuntador.setJugador(null);
                        listaPlanetasNuetrales.add(planetaApuntador);
                        listaPlanetasJugadores.remove(planetaApuntador);
                    } 
                    actualizarListaPlanetas(listaPlanetasNuetrales);
                    JOptionPane.showMessageDialog(null, "Modificado");
                    limpiarCamposPlanetas();
                } else {
                    if (!buscarExistenciaPlanetas(txtNombrePlaneta.getText())) {
                        planetaApuntador.setNombre(txtNombrePlaneta.getText());
                        planetaApuntador.setNaves(Integer.parseInt(txtNavesPlaneta.getText()));
                        planetaApuntador.setPorcentajeMuertes(Double.parseDouble("0."+txtPorcentajePlaneta.getText()));
                        if (!txtProduccionPlaneta.getText().isEmpty()) {
                            planetaApuntador.setProduccion(Integer.parseInt(txtProduccionPlaneta.getText()));
                        } 
                        if (listaPlanetasJugadores.contains(planetaApuntador)) {
                            planetaApuntador.getJugador().getPlanetas().remove(planetaApuntador);
                            planetaApuntador.setJugador(null);
                            listaPlanetasNuetrales.add(planetaApuntador);
                            listaPlanetasJugadores.remove(planetaApuntador);
                        } 
                        actualizarListaPlanetas(listaPlanetasNuetrales);
                        JOptionPane.showMessageDialog(null, "Modificado");
                        limpiarCamposPlanetas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Existe un planeta con el mismo nombre, vuelve a verificar.\nLa entrada es sensible a mayúsculas y minusculas.");
                    }
                }
            } else {
                if (planeta.getNombre().equalsIgnoreCase(txtNombrePlaneta.getText())) {
                    planetaApuntador.setNaves(Integer.parseInt(txtNavesPlaneta.getText()));
                    planetaApuntador.setPorcentajeMuertes(Double.parseDouble("0."+txtPorcentajePlaneta.getText()));
                    planetaApuntador.setProduccion(Integer.parseInt(txtProduccionPlaneta.getText()));
                    planetaApuntador.setJugador(jugadorApuntador);
                    planetaApuntador.getJugador().getPlanetas().add(planetaApuntador);
                    if (listaPlanetasNuetrales.contains(planetaApuntador)) {
                        listaPlanetasJugadores.add(planetaApuntador);
                        listaPlanetasNuetrales.remove(planetaApuntador);
                    } 
                    actualizarListaPlanetas(listaPlanetasJugadores);
                    JOptionPane.showMessageDialog(null, "Modificado");
                    limpiarCamposPlanetas();
                } else {
                    if (!buscarExistenciaPlanetas(txtNombrePlaneta.getText())) {
                        planetaApuntador.setNombre(txtNombrePlaneta.getText());
                        planetaApuntador.setNaves(Integer.parseInt(txtNavesPlaneta.getText()));
                        planetaApuntador.setPorcentajeMuertes(Double.parseDouble("0."+txtPorcentajePlaneta.getText()));
                        planetaApuntador.setProduccion(Integer.parseInt(txtProduccionPlaneta.getText()));
                        planetaApuntador.setJugador(jugadorApuntador);
                        planetaApuntador.getJugador().getPlanetas().add(planetaApuntador);
                        if (listaPlanetasNuetrales.contains(planetaApuntador)) {
                            listaPlanetasJugadores.add(planetaApuntador);
                            listaPlanetasNuetrales.remove(planetaApuntador);
                        } 
                        actualizarListaPlanetas(listaPlanetasJugadores);
                        JOptionPane.showMessageDialog(null, "Modificado");
                        limpiarCamposPlanetas();
                    } else {
                        JOptionPane.showMessageDialog(null, "Existe un planeta con el mismo nombre, vuelve a verificar.\nLa entrada es sensible a mayúsculas y minusculas.");
                    }
                }
                
            }
        } else {
            mostrarAdvertencia("<Planeta>");
        }
        
    }//GEN-LAST:event_btnEditarPlanetaActionPerformed

    private void btnEditarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarJugadorActionPerformed
        // TODO add your handling code here:
        if (comprobarCampoVacioJugadores()) {
            if (jugador.getNombre().equalsIgnoreCase(txtNombreJugador.getText())) {
                jugadorApuntador.setTipo(comboBoxTipo.getSelectedItem().toString());
                actualizarListaJugadores(listaJugadores);
                JOptionPane.showMessageDialog(null, "Modificado");
                limpiarCampoJugadores();
            } else {
                if (!buscarExistenciaJugadores(txtNombreJugador.getText())) {
                    jugadorApuntador.setNombre(txtNombreJugador.getText());
                    jugadorApuntador.setTipo(comboBoxTipo.getSelectedItem().toString());
                    actualizarListaJugadores(listaJugadores);
                    JOptionPane.showMessageDialog(null, "Modificado");
                    limpiarCampoJugadores();
                } else {
                    JOptionPane.showMessageDialog(null, "Existe un jugador con el mismo nombre, vuelve a verificar.\nLa entrada es sensible a mayúsculas y minusculas.");
                }
            }
        } else {
            mostrarAdvertencia("<Jugadores>");
        }
    }//GEN-LAST:event_btnEditarJugadorActionPerformed

    private void btnEliminarPlanetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarPlanetaActionPerformed
        // TODO add your handling code here:
        if (planeta != null) {
            if (checkBoxNeutral.isSelected()) {
                if (listaPlanetasNuetrales.remove(planetaApuntador)) {
                    JOptionPane.showMessageDialog(this, "Eliminado");
                    limpiarCamposPlanetas();
                } else {
                    mensajesError("Error eliminando vuelve a intentar más tarde.");
                }
                actualizarListaPlanetas(listaPlanetasNuetrales);
            } else {
                eliminarPlanetaJugador(planeta.getJugador(), planetaApuntador);
                if (listaPlanetasJugadores.remove(planetaApuntador)) {
                    JOptionPane.showMessageDialog(this, "Eliminado");
                    limpiarCamposPlanetas();
                } else {
                    mensajesError("Error eliminando vuelve a intentar más tarde.");
                }
                actualizarListaPlanetas(listaPlanetasJugadores);
            }
        } else {
            mensajesError("Debes de seleccionar un planeta.");
        }
    }//GEN-LAST:event_btnEliminarPlanetaActionPerformed

    private void btnEliminarJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarJugadorActionPerformed
        // TODO add your handling code here:
        if (jugador != null) {
            if (jugador.getPlanetas().isEmpty()) {
                if (listaJugadores.remove(jugadorApuntador)) {
                    JOptionPane.showMessageDialog(this, "Eliminado");
                } else {
                    mensajesError("Error eliminando vuelve a intentar más tarde.");
                }
            } else {
                mensajesError("Lo siento debes de elminar los planetas que tiene este jugador o cambiar a neutrales.");
            }
        } else {
            mensajesError("Debes de seleccionar un jugador.");
        }
    }//GEN-LAST:event_btnEliminarJugadorActionPerformed

    private void checkMapaCiegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMapaCiegoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkMapaCiegoActionPerformed

    private void btnUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbicacionActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
        int val = chooser.showSaveDialog(this);
        if (val == JFileChooser.APPROVE_OPTION) {
           lblUbicacion.setText(chooser.getSelectedFile().getAbsolutePath()); 
        }
    }//GEN-LAST:event_btnUbicacionActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        if (comprobarCampoVacioMapa()) {
            if (comprobarJugadoresYPlanetas()) {
                EstructuraKonquest estructuraKonquest;
                Mapa mapaEstructura;
                AtributtosMapa atributtosMapa = new AtributtosMapa();
                atributtosMapa.setiD(txtNombre.getText());
                atributtosMapa.setTamaño(new Tamaño(Integer.parseInt(txtFila.getText()), Integer.parseInt(txtColumna.getText())));
                atributtosMapa.setNeutrales(new Neutrales(checkMostraNaves.isSelected(), checkMostraEstadisticas.isSelected(), Integer.parseInt(txtProduccion.getText())));
                atributtosMapa.setMapaCiego(checkMapaCiego.isSelected());
                atributtosMapa.setAcumular(checkMapaCiego.isSelected());
                atributtosMapa.setAlAzar(checkBoxAlAzar.isSelected());
                if (!txtFinalizacion.getText().isEmpty()) {
                    atributtosMapa.setFinalizacion(Integer.parseInt(txtFinalizacion.getText()));
                }
                if (checkBoxAlAzar.isSelected()) {
                    atributtosMapa.setPlanetasNeutrales(Integer.parseInt(txtPlanetasNeutrales.getText()));
                }
                mapaEstructura = new Mapa(atributtosMapa);
                estructuraKonquest = new EstructuraKonquest();
                estructuraKonquest.setMapa(mapaEstructura);
                estructuraKonquest.setEspacioPlanetas(new Espacio(listaPlanetasJugadores, false));
                estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(listaPlanetasNuetrales, true));
                estructuraKonquest.setJugadores(listaJugadores);
                if ((atributtosMapa.getTamaño().getFilas()*atributtosMapa.getTamaño().getColumnas()) > (listaPlanetasJugadores.size() + listaPlanetasNuetrales.size())) {
                    if (!lblUbicacion.getText().isEmpty()) {
                        try {
                            manejadorEscrituraArchivo = new ManejadorEscrituraArchivo(estructuraKonquest);
                            manejadorEscrituraArchivo.escribirArchivoSalida(lblUbicacion.getText());
                            JOptionPane.showMessageDialog(this, "Editado: " 
                            + "\nUbicación: " + lblUbicacion.getText());
                            this.setVisible(false);
                        } catch (IOException ex) {
                            mensajesError("No se a encontrado la ubicación.");
                        }
                    } else {
                        btnUbicacion.setVisible(true);
                        mensajesError("Debes de seleccionar una ubicación");
                    }
                    
                } else {
                    mensajesError("Lo siento el tamaño del mapa debe de ser mayor al número de planetas.");
                }
                //ManejadorEscritura
            } else {
                JOptionPane.showMessageDialog(null, "Los Jugadores deben de tener al menos un planeta.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            mostrarAdvertencia("<Mapa>");
        }
    }//GEN-LAST:event_btnEditarActionPerformed
    
    private void mensajesError(String mensajeError) {
        JOptionPane.showMessageDialog(null, mensajeError, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    private void eliminarPlanetaJugador(Jugadores jugadorEliminar, Planetas planeta) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getNombre().equalsIgnoreCase(jugadorEliminar.getNombre())) {
                listaJugadores.get(i).getPlanetas().remove(planeta);
            }
        }
    }
    
    private void agregarDatosKonquest() {
        txtNombre.setText(mapa.getAtributtosMapa().getiD());
        txtFila.setText(Integer.toString(mapa.getAtributtosMapa().getTamaño().getFilas()));
        txtColumna.setText(Integer.toString(mapa.getAtributtosMapa().getTamaño().getColumnas()));
        checkMostraEstadisticas.setSelected(mapa.getAtributtosMapa().getNeutrales().isMostrarEstadisticas());
        checkMostraNaves.setSelected(mapa.getAtributtosMapa().getNeutrales().isMostrarNaves());
        txtProduccion.setText(Integer.toString(mapa.getAtributtosMapa().getNeutrales().getProduccion()));
        checkBoxAlAzar.setSelected(mapa.getAtributtosMapa().isAlAzar());
        if (mapa.getAtributtosMapa().isAlAzar()) {
            txtPlanetasNeutrales.setText(Integer.toString(mapa.getAtributtosMapa().getPlanetasNeutrales()));
        }
        checkMapaCiego.setSelected(mapa.getAtributtosMapa().isMapaCiego());
        checkAcumular.setSelected(mapa.getAtributtosMapa().isAcumular());
        if (mapa.getAtributtosMapa().getFinalizacion() != 0) {
            txtFinalizacion.setText(Integer.toString(mapa.getAtributtosMapa().getFinalizacion()));
        }
        actualizarListaJugadores(listaJugadores);
        actualizarListaPlanetas(listaPlanetasJugadores);
    }
    
    private void actualizarListaPlanetas(List<Planetas> lista) {
        this.observableListPlanetas.clear();
        this.observableListPlanetas.addAll(lista);
    }
    
    private void actualizarListaJugadores(List<Jugadores> lista) {
        this.observableListJugadores.clear();
        this.observableListJugadores.addAll(lista);
    }
    
    private void mostrarAdvertencia(String area) {
        JOptionPane.showMessageDialog(null, "Todos los campos del área: " + area + " con * son obligatorios.", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    
    private String infPlanetasJugador(){
        String retornar = "";
        for (int i = 0; i < jugador.getPlanetas().size(); i++) {
            retornar = retornar + jugador.getPlanetas().get(i).getNombre() + "\n";
        }
        return retornar;
    }
    
    private boolean comprobarJugadoresYPlanetas() {
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getPlanetas().isEmpty()) {
                return false;
            }
        } return true;
    }
    
    private boolean comprobarCampoVacioPlanetas() {
        if (checkBoxNeutral.isSelected()) {//Verdadero si no esta vacio
            return !txtNombrePlaneta.getText().isEmpty() && !txtNavesPlaneta.getText().isEmpty() && !txtPorcentajePlaneta.getText().isEmpty();
        } else {
            return !txtNombrePlaneta.getText().isEmpty() && !txtNavesPlaneta.getText().isEmpty() && !txtPorcentajePlaneta.getText().isEmpty() && !txtPorcentajePlaneta.getText().isEmpty() && (jugador != null);
        }
    }
    
    private boolean comprobarCampoVacioJugadores() { //Agregar comprobacion de planetas
        return !txtNombreJugador.getText().isEmpty(); 
    }
    
    private boolean comprobarCampoVacioMapa() {
        if (checkBoxAlAzar.isSelected()) {
            return !txtNombre.getText().isEmpty() && !txtFila.getText().isEmpty() && !txtPlanetasNeutrales.getText().isEmpty() && !txtColumna.getText().isEmpty() && !txtProduccion.getText().isEmpty();
        } else {
            return !txtNombre.getText().isEmpty() && !txtFila.getText().isEmpty() && !txtColumna.getText().isEmpty() && !txtProduccion.getText().isEmpty();
        }
    }
    
    private boolean buscarExistenciaPlanetas(String buscar){//Si existe regresa verdadero
        for (int i = 0; i < listaPlanetasNuetrales.size(); i++) {
            if (listaPlanetasNuetrales.get(i).getNombre().equalsIgnoreCase(buscar)) {
                return true;
            }
        }
        for (int i = 0; i < listaPlanetasJugadores.size(); i++) {
            if (listaPlanetasJugadores.get(i).getNombre().equalsIgnoreCase(buscar)) {
                return true;
            } 
        } 
        return false;
    }
    
    private boolean buscarExistenciaJugadores(String nombre){
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        } return false;
    }
    
    private void limpiarCamposPlanetas() {
        txtNombrePlaneta.setText("");
        txtProduccionPlaneta.setText("");
        txtPorcentajePlaneta.setText("");
        txtJugadorSeleccionado.setText("");
        txtNavesPlaneta.setText("");
        checkBoxNeutral.setSelected(false);
    }
    
    private void limpiarCampoJugadores() {
        txtNombreJugador.setText("");
        comboBoxTipo.setSelectedItem("HUMANO");
    }
    
    private void limpiarCampoMapa() {
        txtNombre.setText("");
        txtColumna.setText("");
        txtFila.setText("");
        txtProduccion.setText("");
        txtFinalizacion.setText("");
        checkBoxAlAzar.setSelected(false);
        checkMapaCiego.setSelected(false);
        checkAcumular.setSelected(false);
        checkMostraEstadisticas.setSelected(false);
        checkMostraNaves.setSelected(false);
        listaJugadores.clear();
        listaPlanetasJugadores.clear();
        listaPlanetasNuetrales.clear();
        limpiarCampoJugadores();
        limpiarCamposPlanetas();
        actualizarListaJugadores(listaJugadores);
        actualizarListaPlanetas(listaPlanetasJugadores);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarJugador;
    private javax.swing.JButton btnAgregarPlaneta;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditarJugador;
    private javax.swing.JButton btnEditarPlaneta;
    private javax.swing.JButton btnEliminarJugador;
    private javax.swing.JButton btnEliminarJugador2;
    private javax.swing.JButton btnEliminarPlaneta;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiarJugadoes;
    private javax.swing.JButton btnLimpiarPlanetas;
    private javax.swing.JButton btnNeutrales;
    private javax.swing.JButton btnPlanetas;
    private javax.swing.JButton btnUbicacion;
    private javax.swing.JCheckBox checkAcumular;
    private javax.swing.JCheckBox checkBoxAlAzar;
    private javax.swing.JCheckBox checkBoxNeutral;
    private javax.swing.JCheckBox checkMapaCiego;
    private javax.swing.JCheckBox checkMostraEstadisticas;
    private javax.swing.JCheckBox checkMostraNaves;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAgregar;
    private javax.swing.JLabel lblColumna;
    private javax.swing.JLabel lblColumna3;
    private javax.swing.JLabel lblFila;
    private javax.swing.JLabel lblJugador;
    private javax.swing.JLabel lblNaves;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreJugador;
    private javax.swing.JLabel lblNombrePlaneta;
    private javax.swing.JLabel lblOpciones;
    private javax.swing.JLabel lblPlanetasNeutrales;
    private javax.swing.JLabel lblPlanetasNeutrales1;
    private javax.swing.JLabel lblPorcentaje;
    private javax.swing.JLabel lblProduccion;
    private javax.swing.JLabel lblProduccionPlaneta;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTmaño;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JPanel panelJugadores;
    private javax.swing.JPanel panelMapa;
    private javax.swing.JPanel panelPlanetas;
    private javax.swing.JTable tablaJugadores;
    private javax.swing.JTable tablaPlanetasJugadores;
    private javax.swing.JTextField txtColumna;
    private javax.swing.JTextField txtFila;
    private javax.swing.JTextField txtFinalizacion;
    private javax.swing.JTextField txtJugadorSeleccionado;
    private javax.swing.JTextField txtNavesPlaneta;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreJugador;
    private javax.swing.JTextField txtNombrePlaneta;
    private javax.swing.JTextField txtPlanetasNeutrales;
    private javax.swing.JTextField txtPorcentajePlaneta;
    private javax.swing.JTextField txtProduccion;
    private javax.swing.JTextField txtProduccionPlaneta;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
