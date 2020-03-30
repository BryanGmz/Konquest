/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import konquest.backed.manejadores.*;
import konquest.backed.objetos.*;


/**
 *
 * @author bryan
 */
public class Principal extends javax.swing.JFrame implements Observer {
    private final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Mapas KONQUEST (.json)", "json");
    private final FileNameExtensionFilter extensionFilterPartida = new FileNameExtensionFilter("Partida KONQUEST (.cpk)", "cpk");
    private final FileNameExtensionFilter extensionFilterReplay = new FileNameExtensionFilter("Partida REPLAY (.rply)", "rply");
    private final ManejadorGUI manejadorGUI = new ManejadorGUI();
    private final ManejadorIA manejadorIA = new ManejadorIA();
    private final ManejadorEscrituraArchivo mea = new ManejadorEscrituraArchivo(new EstructuraKonquest());
    private final ManejadorReplay manejadorReplay = new ManejadorReplay(mea);
    private final ManejadorOnline manejadorOnline = new ManejadorOnline(mea);
    private final FondoPanel fondoPanel = new FondoPanel();
    private String accion;
    private String opcion;
    private Planetas primerPlaneta;
    private Planetas segundoPlaneta;
    private Planetas[][] planets;
    private Tamaño tmño;
    private Mapa mapa;
    private Jugadores jugadorEnTurno;
    private Replay replay;
    private JButton matriz[][];
    private java.util.List<Jugadores> listaJugadores;
    private java.util.List<Planetas> todosLosPlanetas;
    private java.util.List<Ataque> listaAtaqueTurno;
    private int turno;
    private int cantidadPlanetas;
    private int finalizacion;
    private int indiceArchivoReplay;
    private final DialogoErrores dialogoErrores = new DialogoErrores(this, true);
    private DialogListaDeAtaques dialogListaDeAtaques;
    private DialogListaPlanetas dialogListaPlanetas;
    private DialogCrearYEditarMapa dialogCrearYEditarMapa;
    private boolean mapaCiego;
    private boolean grabando;
    private int puertoServidor;//Cambiar
    private int puertoCliente;
    private boolean online;
    
    /**
     * Creates new form Principal
    */
    public Principal() {
        initComponents();
        this.todosLosPlanetas = new ArrayList<>();
        this.listaJugadores = new ArrayList<>();
        this.setContentPane(fondoPanel);
        this.panelJuego.setVisible(false);
        this.setLocationRelativeTo(null);
        this.btnSiguiente.setVisible(false);
        this.btnAnterior.setVisible(false);
        this.lblNumeroReplay.setText("");
        agregarConfiguracion();
    }
    
    private void agregarConfiguracion() {
        Thread thread = new Thread(dialogoErrores);
        thread.start();
    }
    
    private void actualizarServidor() {
        Servidor servidor = new Servidor(puertoServidor);
        servidor.addObserver(this);
        Thread hiloServidor = new Thread(servidor);
        hiloServidor.start();
    }
    
    //Get's y Set's
    
    public ManejadorGUI getManejadorGUI() {
        return manejadorGUI;
    }
    
    public String getAccion() {
        return accion;
    }

    public Planetas getPrimerPlaneta() {
        return primerPlaneta;
    }

    public void setPrimerPlaneta(Planetas primerPlaneta) {
        this.primerPlaneta = primerPlaneta;
    }

    public Planetas getSegundoPlaneta() {
        return segundoPlaneta;
    }

    public void setSegundoPlaneta(Planetas segundoPlaneta) {
        this.segundoPlaneta = segundoPlaneta;
    }

    public JLabel getLblSeleccionadoPrimero() {
        return lblSeleccionadoPrimero;
    }

    public void setLblSeleccionadoPrimero(JLabel lblSeleccionadoPrimero) {
        this.lblSeleccionadoPrimero = lblSeleccionadoPrimero;
    }

    public JLabel getLblSeleccionadoSegundo() {
        return lblSeleccionadoSegundo;
    }

    public void setLblSeleccionadoSegundo(JLabel lblSeleccionadoSegundo) {
        this.lblSeleccionadoSegundo = lblSeleccionadoSegundo;
    }

    public Jugadores getJugadorEnTurno() {
        return jugadorEnTurno;
    }

    public void setJugadorEnTurno(Jugadores jugadorEnTurno) {
        this.jugadorEnTurno = jugadorEnTurno;
        this.listaAtaqueTurno = this.jugadorEnTurno.getListaAtaques();
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public java.util.List<Jugadores> getListaJugadores() {
        return listaJugadores;
    }

    public void setListaJugadores(java.util.List<Jugadores> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public java.util.List<Ataque> getListaAtaqueTurno() {
        return listaAtaqueTurno;
    }

    public void setListaAtaqueTurno(java.util.List<Ataque> listaAtaqueTurno) {
        this.listaAtaqueTurno = listaAtaqueTurno;
    }

    public JButton[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(JButton[][] matriz) {
        this.matriz = matriz;
    }

    public Planetas[][] getPlanets() {
        return planets;
    }

    public void setPlanets(Planetas[][] planets) {
        this.planets = planets;
    }

    public Tamaño getTmño() {
        return tmño;
    }

    public void setTmño(Tamaño tmño) {
        this.tmño = tmño;
    }

    public int getCantidadPlanetas() {
        return cantidadPlanetas;
    }

    public void setCantidadPlanetas(int cantidadPlanetas) {
        this.cantidadPlanetas = cantidadPlanetas;
    }

    public java.util.List<Planetas> getTodosLosPlanetas() {
        return todosLosPlanetas;
    }

    public boolean isMapaCiego() {
        return mapaCiego;
    }

    public void setMapaCiego(boolean mapaCiego) {
        this.mapaCiego = mapaCiego;
    }

    public int getFinalizacion() {
        return finalizacion;
    }

    public void setFinalizacion(int finalizacion) {
        this.finalizacion = finalizacion;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Replay getReplay() {
        return replay;
    }

    public void setReplay(Replay replay) {
        this.replay = replay;
    }

    public JTextArea getTxtMensajes() {
        return txtMensajes;
    }

    public void setTxtMensajes(JTextArea txtMensajes) {
        this.txtMensajes = txtMensajes;
    }

    public int getIndiceArchivoReplay() {
        return indiceArchivoReplay;
    }

    public void setIndiceArchivoReplay(int indiceArchivoReplay) {
        this.indiceArchivoReplay = indiceArchivoReplay;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        panelJuego = new FondoPanel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonFin = new javax.swing.JButton();
        buttonMedirDistancia = new javax.swing.JButton();
        buttonFinTurno = new javax.swing.JButton();
        buttonConsulta = new javax.swing.JButton();
        splitPane = new javax.swing.JSplitPane();
        panelMatriz = new javax.swing.JPanel();
        scrollPaneMatriz = new javax.swing.JScrollPane();
        panelContenedorLabel = new javax.swing.JPanel();
        panelSeleccionar = new javax.swing.JPanel();
        lblTextoInf = new javax.swing.JLabel();
        lblSeleccionadoPrimero = new javax.swing.JLabel();
        lblSeleccionadoSegundo = new javax.swing.JLabel();
        btnMedir = new javax.swing.JButton();
        btnListaAtaques = new javax.swing.JButton();
        lblNavesEnviar = new javax.swing.JLabel();
        txtNaves = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        btnNuevoAtaque = new javax.swing.JButton();
        btnCambiarOrigen = new javax.swing.JButton();
        lblPlanetas = new javax.swing.JLabel();
        btnListaPlanetas = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        lblNumeroReplay = new javax.swing.JLabel();
        btnReplay = new javax.swing.JButton();
        scrollPaneTXT = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JTextArea();
        lblTurno = new javax.swing.JLabel();
        lblMensajes = new javax.swing.JLabel();
        btnRealizarAtaque = new javax.swing.JButton();
        lblNumeroTurno = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        menuJuego = new javax.swing.JMenu();
        menuCargar = new javax.swing.JMenu();
        menuGuardar = new javax.swing.JMenu();
        menuNuevo = new javax.swing.JMenu();
        menuMapa = new javax.swing.JMenu();
        menuEditarMapa = new javax.swing.JMenu();
        menuCrearMapa = new javax.swing.JMenu();
        menuReplay = new javax.swing.JMenu();
        menuCargarReplay = new javax.swing.JMenu();
        menuGrabar = new javax.swing.JMenu();
        menuOnline = new javax.swing.JMenu();
        menuConectar = new javax.swing.JMenu();
        menuEnviarMapa = new javax.swing.JMenu();
        menuCreditos = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("jMenu9");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Konquest");

        buttonFin.setText("Χ Fin de Partida");
        buttonFin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinActionPerformed(evt);
            }
        });

        buttonMedirDistancia.setText("► Medir Distacia");
        buttonMedirDistancia.setToolTipText("Hola\nComo \nEsta\n"); // NOI18N
        buttonMedirDistancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMedirDistanciaActionPerformed(evt);
            }
        });

        buttonFinTurno.setText("√ Fin de Turno");
        buttonFinTurno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonFinTurnoActionPerformed(evt);
            }
        });

        buttonConsulta.setText("Ж Consulta Flota");
        buttonConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConsultaActionPerformed(evt);
            }
        });

        splitPane.setBackground(new java.awt.Color(43, 41, 43));
        splitPane.setDividerLocation(200);

        panelContenedorLabel.setBackground(java.awt.Color.darkGray);
        panelContenedorLabel.setForeground(java.awt.Color.darkGray);
        panelContenedorLabel.setMinimumSize(new java.awt.Dimension(375, 350));
        panelContenedorLabel.setLayout(new java.awt.GridLayout(1, 0));
        scrollPaneMatriz.setViewportView(panelContenedorLabel);

        javax.swing.GroupLayout panelMatrizLayout = new javax.swing.GroupLayout(panelMatriz);
        panelMatriz.setLayout(panelMatrizLayout);
        panelMatrizLayout.setHorizontalGroup(
            panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
        );
        panelMatrizLayout.setVerticalGroup(
            panelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPaneMatriz, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
        );

        splitPane.setRightComponent(panelMatriz);

        panelSeleccionar.setBackground(java.awt.Color.darkGray);
        panelSeleccionar.setMaximumSize(new java.awt.Dimension(400, 400));
        panelSeleccionar.setMinimumSize(new java.awt.Dimension(155, 100));
        panelSeleccionar.setPreferredSize(new java.awt.Dimension(155, 291));

        lblTextoInf.setForeground(new java.awt.Color(255, 255, 255));

        lblSeleccionadoPrimero.setForeground(new java.awt.Color(255, 255, 255));
        lblSeleccionadoPrimero.setText("Origen:");

        lblSeleccionadoSegundo.setForeground(new java.awt.Color(255, 255, 255));
        lblSeleccionadoSegundo.setText("Final:");

        btnMedir.setText("Medir");
        btnMedir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMedirActionPerformed(evt);
            }
        });

        btnListaAtaques.setText("Lista de Ataques");
        btnListaAtaques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaAtaquesActionPerformed(evt);
            }
        });

        lblNavesEnviar.setForeground(new java.awt.Color(255, 255, 255));
        lblNavesEnviar.setText("Naves (Enviar):");

        txtNaves.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNavesKeyTyped(evt);
            }
        });

        btnAñadir.setText("Añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        btnNuevoAtaque.setText("Nuevo");
        btnNuevoAtaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoAtaqueActionPerformed(evt);
            }
        });

        btnCambiarOrigen.setText("Cambiar Origen");
        btnCambiarOrigen.setBorder(javax.swing.BorderFactory.createEtchedBorder(null, new java.awt.Color(0, 0, 0)));
        btnCambiarOrigen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnCambiarOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarOrigenActionPerformed(evt);
            }
        });

        lblPlanetas.setForeground(new java.awt.Color(255, 255, 255));
        lblPlanetas.setText("Planetas:");

        btnListaPlanetas.setText("Lista De Planetas");
        btnListaPlanetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaPlanetasActionPerformed(evt);
            }
        });

        btnSiguiente.setText(">");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        lblNumeroReplay.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroReplay.setText(" ");

        btnReplay.setText("Fin Replay");
        btnReplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSeleccionarLayout = new javax.swing.GroupLayout(panelSeleccionar);
        panelSeleccionar.setLayout(panelSeleccionarLayout);
        panelSeleccionarLayout.setHorizontalGroup(
            panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTextoInf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSeleccionadoPrimero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSeleccionadoSegundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSeleccionarLayout.createSequentialGroup()
                        .addGroup(panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblNumeroReplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnListaPlanetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnMedir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnListaAtaques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelSeleccionarLayout.createSequentialGroup()
                                .addComponent(lblNavesEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNaves))
                            .addGroup(panelSeleccionarLayout.createSequentialGroup()
                                .addComponent(btnAñadir, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNuevoAtaque, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                            .addComponent(btnCambiarOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPlanetas)
                            .addGroup(panelSeleccionarLayout.createSequentialGroup()
                                .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        panelSeleccionarLayout.setVerticalGroup(
            panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTextoInf, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(lblPlanetas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeleccionadoPrimero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCambiarOrigen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSeleccionadoSegundo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNavesEnviar)
                    .addComponent(txtNaves, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAñadir)
                    .addComponent(btnNuevoAtaque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMedir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListaAtaques)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnListaPlanetas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSeleccionarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguiente)
                    .addComponent(btnAnterior))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReplay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumeroReplay)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        splitPane.setLeftComponent(panelSeleccionar);

        txtMensajes.setEditable(false);
        txtMensajes.setBackground(java.awt.Color.darkGray);
        txtMensajes.setColumns(20);
        txtMensajes.setForeground(new java.awt.Color(255, 255, 255));
        txtMensajes.setRows(5);
        scrollPaneTXT.setViewportView(txtMensajes);

        lblTurno.setForeground(new java.awt.Color(255, 255, 255));
        lblTurno.setText("Turno:           ");

        lblMensajes.setForeground(new java.awt.Color(255, 255, 255));
        lblMensajes.setText("Mensajes:");

        btnRealizarAtaque.setText("Ώ Realizar Ataque");
        btnRealizarAtaque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRealizarAtaqueActionPerformed(evt);
            }
        });

        lblNumeroTurno.setForeground(new java.awt.Color(255, 255, 255));
        lblNumeroTurno.setText("#Turno: ");

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJuegoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollPaneTXT)
                    .addComponent(splitPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelJuegoLayout.createSequentialGroup()
                        .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelJuegoLayout.createSequentialGroup()
                                .addComponent(buttonFin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonMedirDistancia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonConsulta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRealizarAtaque)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonFinTurno))
                            .addGroup(panelJuegoLayout.createSequentialGroup()
                                .addComponent(lblMensajes)
                                .addGap(53, 53, 53)
                                .addComponent(lblTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblNumeroTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelJuegoLayout.createSequentialGroup()
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonFin)
                    .addComponent(buttonMedirDistancia)
                    .addComponent(buttonConsulta)
                    .addComponent(buttonFinTurno)
                    .addComponent(btnRealizarAtaque))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitPane)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMensajes)
                    .addComponent(lblTurno)
                    .addComponent(lblNumeroTurno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollPaneTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuJuego.setText("Juego");
        menuJuego.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuJuegoMouseClicked(evt);
            }
        });

        menuCargar.setText("Cargar");
        menuCargar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCargarMouseClicked(evt);
            }
        });
        menuJuego.add(menuCargar);

        menuGuardar.setText("Guargar");
        menuGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuGuardarMouseClicked(evt);
            }
        });
        menuJuego.add(menuGuardar);

        menuNuevo.setText("Nuevo");
        menuNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuNuevoMouseClicked(evt);
            }
        });
        menuJuego.add(menuNuevo);

        menu.add(menuJuego);

        menuMapa.setText("Mapa");
        menuMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuMapaMouseClicked(evt);
            }
        });

        menuEditarMapa.setText("Editar");
        menuEditarMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEditarMapaMouseClicked(evt);
            }
        });
        menuMapa.add(menuEditarMapa);

        menuCrearMapa.setText("Crear");
        menuCrearMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCrearMapaMouseClicked(evt);
            }
        });
        menuMapa.add(menuCrearMapa);

        menu.add(menuMapa);

        menuReplay.setText("Raplay");

        menuCargarReplay.setText("Cargar");
        menuCargarReplay.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCargarReplayMouseClicked(evt);
            }
        });
        menuReplay.add(menuCargarReplay);

        menuGrabar.setText("Grabar");
        menuGrabar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuGrabarMouseClicked(evt);
            }
        });
        menuReplay.add(menuGrabar);

        menu.add(menuReplay);

        menuOnline.setText("Online");

        menuConectar.setText("Conectar");
        menuConectar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuConectarMouseClicked(evt);
            }
        });
        menuOnline.add(menuConectar);

        menuEnviarMapa.setText("Enviar Mapa");
        menuEnviarMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuEnviarMapaMouseClicked(evt);
            }
        });
        menuOnline.add(menuEnviarMapa);

        menu.add(menuOnline);

        menuCreditos.setText("Acerca de...");
        menuCreditos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuCreditosMouseClicked(evt);
            }
        });
        menu.add(menuCreditos);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelJuego, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void menuNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuNuevoMouseClicked
        // TODO add your handling code here:
        online = false;
        this.todosLosPlanetas = new ArrayList<>();
        try {
            remover();
            inhabilitar(false);
            manejadorGUI.leer(extensionFilter, this, panelContenedorLabel, scrollPaneMatriz, this, false, dialogoErrores);
            agregarPanel();
        } catch (Exception e) {
            this.panelJuego.removeAll();
            this.fondoPanel.removeAll();
            this.fondoPanel.updateUI();
            this.panelJuego.updateUI();
            System.out.println("Excepcion: " + e.toString());
            System.out.println("Excepcion Localizad Message: " + e.getLocalizedMessage());
            System.out.println("Excepcion Message: " + e.getMessage());
            dialogoErrores.setVisible(true);
        }
    }//GEN-LAST:event_menuNuevoMouseClicked

    private void buttonFinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinActionPerformed
        // TODO add your handling code here:
        if (online) {
            online = false;
        }
        ganadorPorFinalizacion(false);
        this.panelJuego.removeAll();
        this.fondoPanel.removeAll();
        this.fondoPanel.updateUI();
        this.panelJuego.updateUI();
    }//GEN-LAST:event_buttonFinActionPerformed

    private void buttonMedirDistanciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMedirDistanciaActionPerformed
        // TODO add your handling code here:
        accion = "Medir Distancia";
        inhabilitarBotones();
        btnMedir.setVisible(true);
        lblTextoInf.setText(accion);
        limpiarTXT();
    }//GEN-LAST:event_buttonMedirDistanciaActionPerformed

    private void buttonConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConsultaActionPerformed
        // TODO add your handling code here:
        this.accion = "Consula de Flota";
        limpiarTXT();
        inhabilitarBotones();
        btnListaAtaques.setVisible(true);
        btnListaPlanetas.setVisible(true);
        lblTextoInf.setText(accion);
        dialogListaDeAtaques = new DialogListaDeAtaques(this, true, listaAtaqueTurno, this);
        dialogListaDeAtaques.setVisible(true);
    }//GEN-LAST:event_buttonConsultaActionPerformed

    private void buttonFinTurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonFinTurnoActionPerformed
        // TODO add your handling code here:
        this.accion = "Fin Turno";
        System.out.println("Boolean " + online);
        if (online) {
            //Enviar ataques
            enviarAtaque(manejadorOnline.enviarAtaque(jugadorEnTurno));
        }
        limpiarTXT();
        if (online) {
            limpiarTXT();
            lblNumeroTurno.setText("# Turno: " + turno);
            lblTurno.setText("Turno: " + jugadorEnTurno.getNombre());
            habilitarBotones(false);
        } else{
            inhabilitarBotones();
        }
        cambiarDeTurno();
    }//GEN-LAST:event_buttonFinTurnoActionPerformed

    public void cambiarDeTurno(){
        if (grabando) {
            mapa.setJugadorEnTurno(jugadorEnTurno);
            mapa.setAreaText(txtMensajes.getText());
            mapa.setCantidadPlanetas(cantidadPlanetas);
            mapa.setTurnoActual(turno);
            manejadorReplay.guardarArchivosReplay(mapa, listaJugadores, todosLosPlanetas, txtMensajes.getText(), replay);
        }
        try {
            this.setJugadorEnTurno(listaJugadores.get(listaJugadores.indexOf(jugadorEnTurno) + 1));
        } catch (Exception e) {
            System.out.println("turno: " + turno);
            manejadorGUI.relizarAtaques(txtMensajes, turno, listaJugadores, this);
            actualizarTurnos();
            this.setJugadorEnTurno(listaJugadores.get(0));
            manejadorGUI.agregarFlotas(this);
            manejadorGUI.actualizarToolText(this, mapaCiego);
            if (comprobarFinalizacion()) {
                buttonFinTurno.setEnabled(false);
                ganadorPorFinalizacion(true);
            } else {
                this.turno++;
                comprobarGanador();
            }
            txtMensajes.setText(txtMensajes.getText() + "\nTurno: " + turno);
            System.out.println("Truno " + turno);
        }
        if (online) {
            lblNumeroTurno.setText("# Turno: " + turno);
            lblTurno.setText("Turno: " + jugadorEnTurno.getNombre());
        }
        if (buttonFinTurno.isEnabled()) {
            atacar();
        }
    }
    
    private void atacar(){
        limpiarTXT();
        inhabilitarBotones();
        lblNumeroTurno.setText("# Turno: " + turno);
        lblTurno.setText("Turno: " + jugadorEnTurno.getNombre());
        if (!jugadorEnTurno.tipoCadena().equalsIgnoreCase("HUMANO")) {
            if (!comprobarGanador()) {
                if (comprobarHumanos()) {
                    JOptionPane.showMessageDialog(this, "El Boot esta atacando.", "BOOT", JOptionPane.OK_OPTION);
                }   
                ataqueIA();
                this.accion = "Fin Turno";
                limpiarTXT();
                inhabilitarBotones();
                if (listaJugadores.size() != 1) {
                    cambiarDeTurno();
                } else {
                    if (!comprobarGanador()) {
                        cambiarDeTurno();
                    }
                }
            }   
        }
    }
    
    private void guardar() {
        if (grabando) {
            grabando = false;
            menuGrabar.setText("Grabar");
            JOptionPane.showMessageDialog(null, "Finalizando Grabación");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(extensionFilterReplay);
            int opcionFile = chooser.showSaveDialog(this);
            if (opcionFile == JFileChooser.APPROVE_OPTION) {
                replay.setNombre(chooser.getSelectedFile().getName());
                replay.setPath(chooser.getSelectedFile().getPath());
                manejadorReplay.guadarReplay(replay, (chooser.getSelectedFile().getAbsolutePath() + ManejadorEscrituraReplay.EXTENCION_ARCHIVO_REPLAY));
            }
            JOptionPane.showMessageDialog(null, "Guardado En:\n" + (chooser.getSelectedFile().getAbsolutePath() + ManejadorEscrituraReplay.EXTENCION_ARCHIVO_REPLAY));
        }
    }
    
    private void ganadorPorFinalizacion(boolean finalizcion){
        Jugadores jugador = listaJugadores.get(0);
        for (int i = 1; i < listaJugadores.size(); i++) {
            jugador.getListaAtaques().clear();
            if (listaJugadores.get(i).getPlanetas().size() > jugador.getPlanetas().size()) {
                jugador = listaJugadores.get(i);
                jugador.getListaAtaques().clear();
            }
        }
        if (online) {
            online = false;
        }
        if (finalizcion) {
            JOptionPane.showMessageDialog(this, "Se a llegado al limite turnos\nEl ganador es: " + listaJugadores.get(0).getNombre(), "GANADOR", JOptionPane.INFORMATION_MESSAGE);
            guardar();
        } else {
            JOptionPane.showMessageDialog(this, "Partida Finalizada\nEl ganador es: " + listaJugadores.get(0).getNombre(), "GANADOR", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private boolean comprobarFinalizacion() {
        return finalizacion != 0 && finalizacion == turno;
    }
    
    private boolean comprobarHumanos() {
        int contador = 0;
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).tipoCadena().equalsIgnoreCase("HUMANO")) {
                contador++;
            }
        } return contador != 0;
    }
    
    private void ataqueIA(){
        if (jugadorEnTurno.tipoCadena().equalsIgnoreCase("DIFICIL")) {
            Ataque ataque;
            int naves;
            for (int i = 0; i < jugadorEnTurno.getPlanetas().size(); i++) {
                primerPlaneta = jugadorEnTurno.getPlanetas().get(i);
                if (primerPlaneta.getNaves() != 0) {
                    double calc = (int)(Math.random()*75);
                    calc += 1;
                    calc = calc / 100;
                    calc *= primerPlaneta.getNaves();
                    naves = (int)calc;
                    segundoPlaneta = manejadorIA.buscarElMasDebil(jugadorEnTurno, todosLosPlanetas);
                    System.out.println("Naves enviar por boot: " + naves);
                    System.out.println("Primer Planeta: " + primerPlaneta.getNombre());
                    System.out.println("Segundo Planeta: " + segundoPlaneta.getNombre());
                    primerPlaneta.setNaves(primerPlaneta.getNaves() - naves);
                    ataque = new Ataque(segundoPlaneta, primerPlaneta,naves, (primerPlaneta.calcularDistancia(segundoPlaneta.getFila(), segundoPlaneta.getColumna())) + turno);
                    jugadorEnTurno.getListaAtaques().add(ataque);
                    manejadorGUI.actualizarToolText(this, mapaCiego);
                    limpiarTXT();
                }
            }
        } else {
            Ataque ataque;
            int naves;
            for (int i = 0; i < jugadorEnTurno.getPlanetas().size(); i++) {
                primerPlaneta = jugadorEnTurno.getPlanetas().get(i);
                if (primerPlaneta.getNaves() != 0) {
                    naves = primerPlaneta.getNaves();
                    segundoPlaneta = manejadorIA.buscarElMasFuerte(jugadorEnTurno, todosLosPlanetas);
                    System.out.println("Naves enviar por boot: " + naves);
                    System.out.println("Primer Planeta: " + primerPlaneta.getNombre());
                    System.out.println("Segundo Planeta: " + segundoPlaneta.getNombre());
                    primerPlaneta.setNaves(primerPlaneta.getNaves() - naves);
                    ataque = new Ataque(segundoPlaneta, primerPlaneta,naves, (primerPlaneta.calcularDistancia(segundoPlaneta.getFila(), segundoPlaneta.getColumna())) + turno);
                    jugadorEnTurno.getListaAtaques().add(ataque);
                    manejadorGUI.actualizarToolText(this, mapaCiego);
                    limpiarTXT();
                }
            }
        }
    }
    
    private boolean comprobarGanador(){
        if (listaJugadores.size() == 1) {
            if (listaJugadores.get(0).getPlanetas().size() >= cantidadPlanetas) {
                listaJugadores.get(0).getListaAtaques().clear();
                if (online) {
                    online = false;
                }
                JOptionPane.showMessageDialog(this, "Felicidades el ganador es: " + listaJugadores.get(0).getNombre(), "GANADOR", JOptionPane.INFORMATION_MESSAGE);
                buttonFinTurno.setEnabled(false);
                guardar();
                return true;
            }
        }
        return false;
    }
    
    private void actualizarTurnos(){
        for (int i = 0; i < listaJugadores.size(); i++) {
            if (listaJugadores.get(i).getPlanetas().isEmpty()) {
                listaJugadores.get(0).getListaAtaques().clear();
                JOptionPane.showMessageDialog(this, "Lo siento el Jugador: " + listaJugadores.get(i).getNombre() + " a Perdido ya no posee  planetas.");
                listaJugadores.remove(listaJugadores.get(i));
            }
        }
    }
    
    private void btnMedirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMedirActionPerformed
        // TODO add your handling code here:
        if (primerPlaneta != null && segundoPlaneta != null) {
            JOptionPane.showMessageDialog(this, "Distancia: " + (primerPlaneta.calcularDistancia(segundoPlaneta.getFila(), segundoPlaneta.getColumna())) + " Años Luz");
        } else {
            JOptionPane.showMessageDialog(this, "Debes de seleccionar los planetas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnMedirActionPerformed

    private void btnRealizarAtaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRealizarAtaqueActionPerformed
        // TODO add your handling code here:
        limpiarTXT();
        lblNavesEnviar.setVisible(true);
        txtNaves.setVisible(true);
        btnMedir.setVisible(false);
        btnListaAtaques.setVisible(true);
        btnAñadir.setVisible(true);
        btnNuevoAtaque.setVisible(true);
        accion = "Realizar Ataques";
        lblTextoInf.setText(accion);
    }//GEN-LAST:event_btnRealizarAtaqueActionPerformed

    private void txtNavesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNavesKeyTyped
        // TODO add your handling code here:
        char value = evt.getKeyChar();
        if (value <'0' || value >'9') evt.consume();
    }//GEN-LAST:event_txtNavesKeyTyped

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        // TODO add your handling code here:
        if (primerPlaneta != null && segundoPlaneta != null) {
            if (!txtNaves.getText().isEmpty() && Integer.parseInt(txtNaves.getText()) > 0) {
                if (primerPlaneta.getNaves() >= Integer.parseInt(txtNaves.getText())) {
                    primerPlaneta.setNaves(primerPlaneta.getNaves() - Integer.parseInt(txtNaves.getText()));
                    Ataque ataque = new Ataque(segundoPlaneta, primerPlaneta, Integer.parseInt(txtNaves.getText()), (primerPlaneta.calcularDistancia(segundoPlaneta.getFila(), segundoPlaneta.getColumna())) + turno);
                    jugadorEnTurno.getListaAtaques().add(ataque);
                    manejadorGUI.actualizarToolText(this, mapaCiego);
                    limpiarTXT();
                    JOptionPane.showMessageDialog(this, "Felicidades el ataque fue enviado con exito!!! \nTiempo estimado de llegada: " + (ataque.getTurnoLlegada() - turno) + " Años Luz \nTurno de llegada: " + ataque.getTurnoLlegada(), "Ataque", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Lo siento no hay naves suficientes.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Lo siento debes de ingresar el numero de naves mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes de Seleccionar los planetas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnCambiarOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarOrigenActionPerformed
        // TODO add your handling code here:
        lblSeleccionadoPrimero.setText("");
        primerPlaneta = null;
    }//GEN-LAST:event_btnCambiarOrigenActionPerformed

    private void btnNuevoAtaqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoAtaqueActionPerformed
        // TODO add your handling code here:
        limpiarTXT();
    }//GEN-LAST:event_btnNuevoAtaqueActionPerformed

    private void btnListaAtaquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaAtaquesActionPerformed
        // TODO add your handling code here:
        dialogListaDeAtaques = new DialogListaDeAtaques(this, true, listaAtaqueTurno, this);
        dialogListaDeAtaques.setVisible(true);
    }//GEN-LAST:event_btnListaAtaquesActionPerformed

    private void btnListaPlanetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaPlanetasActionPerformed
        // TODO add your handling code here:
        dialogListaPlanetas = new DialogListaPlanetas(this, true, jugadorEnTurno.getPlanetas());
        dialogListaPlanetas.setVisible(true);
    }//GEN-LAST:event_btnListaPlanetasActionPerformed

    private void menuEditarMapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEditarMapaMouseClicked
        online = false;
        try {
            manejadorGUI.leer(extensionFilter, this, panelContenedorLabel, scrollPaneMatriz, this, true, dialogoErrores);
        } catch (Exception ex) {
            this.panelJuego.removeAll();
            this.fondoPanel.removeAll();
            this.fondoPanel.updateUI();
            this.panelJuego.updateUI();
            System.out.println("Excepcion: " + ex.toString());
            System.out.println("Excepcion Localizad Message: " + ex.getLocalizedMessage());
            System.out.println("Excepcion Message: " + ex.getMessage());
//            e.printStackTrace();
            dialogoErrores.setVisible(true);
        }
    }//GEN-LAST:event_menuEditarMapaMouseClicked

    private void menuCrearMapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCrearMapaMouseClicked
        // TODO add your handling code here:
        online = false;
        dialogCrearYEditarMapa = new DialogCrearYEditarMapa(this, true, false);
        dialogCrearYEditarMapa.setVisible(true);
    }//GEN-LAST:event_menuCrearMapaMouseClicked

    private void menuGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuGuardarMouseClicked
        try {
            // TODO add your handling code here:
            if (mapa != null) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(extensionFilterPartida);
                int opcion = chooser.showSaveDialog(this);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    mapa.setJugadorEnTurno(jugadorEnTurno);
                    mapa.setAreaText(txtMensajes.getText());
                    mapa.setCantidadPlanetas(cantidadPlanetas);
                    mapa.setTurnoActual(turno);
                    System.out.println(chooser.getSelectedFile().getAbsolutePath());
                    if (chooser.getSelectedFile().getAbsolutePath().endsWith(".cpk")) {
                        mea.escrituraArchivoGuardarPartida(mapa, listaJugadores, todosLosPlanetas, (chooser.getSelectedFile().getAbsolutePath()), true);
                    } else {
                         mea.escrituraArchivoGuardarPartida(mapa, listaJugadores, todosLosPlanetas, (chooser.getSelectedFile().getAbsolutePath() + ".cpk"), true);
                    }
                   
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lo siento debes de iniciar una partida para realizar esta accion.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println("Excepcion: " + ex.toString());
        }
    }//GEN-LAST:event_menuGuardarMouseClicked

    private void menuCargarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCargarMouseClicked
        // TODO add your handling code here:
        online = false;
        this.todosLosPlanetas = new ArrayList<>();
        try {
            remover();
            inhabilitar(false);
            manejadorGUI.leerCargaPartida(extensionFilterPartida, this, panelContenedorLabel, scrollPaneMatriz, this, false, grabando, dialogoErrores);
            agregarPanel();
        } catch (Exception e) {
            this.panelJuego.removeAll();
            this.fondoPanel.removeAll();
            this.fondoPanel.updateUI();
            this.panelJuego.updateUI();
            System.out.println("Excepcion: " + e.toString());
            System.out.println("Excepcion Localizad Message: " + e.getLocalizedMessage());
            System.out.println("Excepcion Message: " + e.getMessage());
        }
    }//GEN-LAST:event_menuCargarMouseClicked

    private void menuGrabarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuGrabarMouseClicked
        // TODO add your handling code here:
        if (grabando) {
            grabando = false;
            menuGrabar.setText("Grabar");
            JOptionPane.showMessageDialog(null, "Finalizando Grabación");
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(extensionFilterReplay);
            int opcionFile = chooser.showSaveDialog(this);
            if (opcionFile == JFileChooser.APPROVE_OPTION) {
                replay.setNombre(chooser.getSelectedFile().getName());
                replay.setPath(chooser.getSelectedFile().getPath());
                manejadorReplay.guadarReplay(replay, (chooser.getSelectedFile().getAbsolutePath() + ManejadorEscrituraReplay.EXTENCION_ARCHIVO_REPLAY));
            }
            JOptionPane.showMessageDialog(null, "Guardado En:\n" + (chooser.getSelectedFile().getAbsolutePath() + ManejadorEscrituraReplay.EXTENCION_ARCHIVO_REPLAY));
        } else {
            menuGrabar.setText("Grabando");
            replay = new Replay();
            grabando = true;
        }
    }//GEN-LAST:event_menuGrabarMouseClicked

    private void menuCargarReplayMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCargarReplayMouseClicked
        online = false;
        try {
            dialogoErrores.limpiarTXTArea();
            manejadorReplay.obtenerArchivo(this, extensionFilterReplay, dialogoErrores);
            if (replay != null) {
                if (!replay.getListaArchivosReplay().isEmpty()) {
                    remover();
                    manejadorReplay.obtenerSiguienteCaptura(replay.getListaArchivosReplay().get(0), this, panelContenedorLabel, scrollPaneMatriz, this, false, true, dialogoErrores);
                    agregarPanel();
                    indiceArchivoReplay = 0;
                    this.btnSiguiente.setVisible(true);
                    this.btnAnterior.setVisible(true);
                }
            }
        } catch (Exception ex) {
            this.panelJuego.removeAll();
            this.fondoPanel.removeAll();
            this.fondoPanel.updateUI();
            this.panelJuego.updateUI();
            dialogoErrores.setVisible(true);
            System.out.println("Excepcion: " + ex.toString());
        }
    }//GEN-LAST:event_menuCargarReplayMouseClicked

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (indiceArchivoReplay <= (replay.getListaArchivosReplay().size()-1)) {
            if (indiceArchivoReplay == (replay.getListaArchivosReplay().size()-1)) {
                indiceArchivoReplay = 0;
            } else {
                indiceArchivoReplay++; 
            }
        } else {
            indiceArchivoReplay = replay.getListaArchivosReplay().size()-1;
        }
        if (!replay.getListaArchivosReplay().isEmpty()) {
            remover();
            lblNumeroReplay.setText("Numero de Replay: " + (indiceArchivoReplay + 1));
            manejadorReplay.obtenerSiguienteCaptura(replay.getListaArchivosReplay().get(indiceArchivoReplay), this, panelContenedorLabel, scrollPaneMatriz, this, false, true, dialogoErrores);
            agregarPanel();
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        // TODO add your handling code here:
        if (indiceArchivoReplay >= 0) {
            if (indiceArchivoReplay == 0) {
                indiceArchivoReplay = (replay.getListaArchivosReplay().size()-1);
            } else {
                indiceArchivoReplay--; 
            }
        } else {
            indiceArchivoReplay = 0;
        }
        if (!replay.getListaArchivosReplay().isEmpty()) {
            remover();
            lblNumeroReplay.setText("Numero de Replay: " + (indiceArchivoReplay + 1));
            manejadorReplay.obtenerSiguienteCaptura(replay.getListaArchivosReplay().get(indiceArchivoReplay), this, panelContenedorLabel, scrollPaneMatriz, this, false, true, dialogoErrores);
            agregarPanel();
        }
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnReplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplayActionPerformed
        // TODO add your handling code here:
        online = false;
        inhabilitar(false);
    }//GEN-LAST:event_btnReplayActionPerformed

    private void menuJuegoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuJuegoMouseClicked
        // TODO add your handling code here:
        online = false;
        inhabilitar(false);
    }//GEN-LAST:event_menuJuegoMouseClicked

    private void menuMapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuMapaMouseClicked
        // TODO add your handling code here:
        online = false;
        inhabilitar(false);
    }//GEN-LAST:event_menuMapaMouseClicked

    private void menuCreditosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuCreditosMouseClicked
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, ""
                         + "               Konquest   v1.0                  \n"
                         + "Es un juego que consiste en un juego de\n" +
                           "estrategia por turnos en el que los jugadores pueden conquistar planetas enviando naves con el objetivo\n" +
                           "de construir un gran imperio al conquistar todos los planetas disponibles. Se puede jugar localmente\n" +
                           "contra otros jugadores o contra la computadora, y se puede jugar en línea contra otro jugador.\n"
                         + "Copyright 2020\n"
                         + "Este producto fue creado por:\n"
                         + "(bryanrene-gomezgomez@cunoc.edu.gt) creado en:\n"
                         + "NetBeans IDE 8.2", "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_menuCreditosMouseClicked

    private void menuConectarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuConectarMouseClicked
        // TODO add your handling code here:
        opcion = JOptionPane.showInputDialog(this, "Ingresa la opción para el juego online. \n1. Servidor \n(Cualquier otro número) Cliente.");
        try {
            if (Integer.parseInt(opcion) == 1) {
                puertoCliente = 5000;
                puertoServidor = 6000;
                online = true;
                actualizarServidor();
                JOptionPane.showMessageDialog(this, "Conectado");
            } else {
                puertoCliente = 6000;
                puertoServidor = 5000;
                online = true;
                actualizarServidor();
                JOptionPane.showMessageDialog(this, "Conectado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debes de ingresar un número.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuConectarMouseClicked

    private void menuEnviarMapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuEnviarMapaMouseClicked
        // TODO add your handling code here:
        if (opcion != null && !opcion.isEmpty()) {
            if (mapa != null) {
                if (listaJugadores.size() == 2 && comprobarJugadores()) {
                    online = true;
                    String send = manejadorOnline.sincronizacionMapa(mapa, listaJugadores, todosLosPlanetas);
                    if (!send.isEmpty()) {
                        enviarAtaque(manejadorOnline.sincronizacionMapa(mapa, listaJugadores, todosLosPlanetas));
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "El mapa debe de tener dos jugadores obligatoriamente, y debed de ser del tipo: HUMANO", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes de Cargar un Mapa", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debes de Ingresar la Dirección IP", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuEnviarMapaMouseClicked

    private boolean comprobarJugadores() {
        return listaJugadores.stream().noneMatch((player) -> (!"HUMANO".equals(player.getTipoString())));
    }
    
    private void enviarAtaque(String enviar){
        Cliente cliente = new Cliente(puertoCliente, enviar);
        Thread hiloCliente = new Thread(cliente);
        hiloCliente.start();
    }
    
    private void inhabilitar(boolean bandera){
        this.btnSiguiente.setVisible(false);
        this.btnAnterior.setVisible(false);
        this.btnReplay.setVisible(false);
        this.lblNumeroReplay.setText("");
    }
    
    private void remover(){
        this.todosLosPlanetas = new ArrayList<>();
        this.fondoPanel.removeAll();
        this.panelJuego.removeAll();
        initComponents();
        limpiarTXT();
        inhabilitarBotones();
    }
        
    private void agregarPanel(){
        if (jugadorEnTurno != null) {
            lblTurno.setText("Turno: " + jugadorEnTurno.getNombre());
            lblNumeroTurno.setText("# Turno: " + turno);
            this.panelJuego.setVisible(true);
        }
    }
    
    public void inhabilitarBotonesReplay(){
        buttonFin.setEnabled(false);
        buttonFinTurno.setEnabled(false);
        btnRealizarAtaque.setEnabled(false);
    }
    
    public void habilitarBotones(boolean bandera){
        this.buttonFin.setEnabled(bandera);
        this.buttonFinTurno.setEnabled(bandera);
        this.btnRealizarAtaque.setEnabled(bandera);
        this.btnListaAtaques.setEnabled(bandera);
        this.buttonConsulta.setEnabled(bandera);
    }
    
    private void limpiarTXT() {
        primerPlaneta = null;
        segundoPlaneta = null;
        txtNaves.setText("");
        lblSeleccionadoPrimero.setText("Origen");
        lblSeleccionadoSegundo.setText("Destino");
    }
    
    private void inhabilitarBotones() {
        btnMedir.setVisible(false);
        lblNavesEnviar.setVisible(false);
        txtNaves.setVisible(false);
        btnListaAtaques.setVisible(false);
        btnAñadir.setVisible(false);
        btnNuevoAtaque.setVisible(false);
        btnListaPlanetas.setVisible(true);
    }
    
    @Override
    public void update(Observable o, Object arg) {
        String entrada = (String) arg; 
        System.out.println("Entrada: \n\n" + entrada);
        if (arg != null) {
            try {
                if (manejadorOnline.isAtaque(entrada)) {
                    habilitarBotones(true);
                    cambiarDeTurno();
                    manejadorOnline.obtenerAtaque(entrada, this, panelContenedorLabel, scrollPaneMatriz, this, dialogoErrores);
                } else {
                    remover();
                    inhabilitar(false);
                    manejadorOnline.leerMapaFlujo(entrada, this, panelContenedorLabel, scrollPaneMatriz, this, dialogoErrores);
                    habilitarBotones(false);
                    agregarPanel();
                }
            } catch (Exception e) {
                System.out.println("Excepcion UPDATE: " + e.toString());
                dialogoErrores.setVisible(true);
                e.printStackTrace();
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnCambiarOrigen;
    private javax.swing.JButton btnListaAtaques;
    private javax.swing.JButton btnListaPlanetas;
    private javax.swing.JButton btnMedir;
    private javax.swing.JButton btnNuevoAtaque;
    private javax.swing.JButton btnRealizarAtaque;
    private javax.swing.JButton btnReplay;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton buttonConsulta;
    private javax.swing.JButton buttonFin;
    private javax.swing.JButton buttonFinTurno;
    private javax.swing.JButton buttonMedirDistancia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblMensajes;
    private javax.swing.JLabel lblNavesEnviar;
    private javax.swing.JLabel lblNumeroReplay;
    private javax.swing.JLabel lblNumeroTurno;
    private javax.swing.JLabel lblPlanetas;
    private javax.swing.JLabel lblSeleccionadoPrimero;
    private javax.swing.JLabel lblSeleccionadoSegundo;
    private javax.swing.JLabel lblTextoInf;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenu menuCargar;
    private javax.swing.JMenu menuCargarReplay;
    private javax.swing.JMenu menuConectar;
    private javax.swing.JMenu menuCrearMapa;
    private javax.swing.JMenu menuCreditos;
    private javax.swing.JMenu menuEditarMapa;
    private javax.swing.JMenu menuEnviarMapa;
    private javax.swing.JMenu menuGrabar;
    private javax.swing.JMenu menuGuardar;
    private javax.swing.JMenu menuJuego;
    private javax.swing.JMenu menuMapa;
    private javax.swing.JMenu menuNuevo;
    private javax.swing.JMenu menuOnline;
    private javax.swing.JMenu menuReplay;
    private javax.swing.JPanel panelContenedorLabel;
    private javax.swing.JPanel panelJuego;
    private javax.swing.JPanel panelMatriz;
    private javax.swing.JPanel panelSeleccionar;
    private javax.swing.JScrollPane scrollPaneMatriz;
    private javax.swing.JScrollPane scrollPaneTXT;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JTextArea txtMensajes;
    private javax.swing.JTextField txtNaves;
    // End of variables declaration//GEN-END:variables

    class FondoPanel extends JPanel {
        private Image image;
        
        @Override
        public void paint(Graphics g) {
            image = new ImageIcon(getClass().getResource("/konquest/gui/imagenes/fondo.jpg")).getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
}
