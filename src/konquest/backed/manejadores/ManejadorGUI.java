/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import konquest.backed.analizador.Lexico;
import konquest.backed.analizador.Sintax;
import konquest.backed.analizador.partida.LexerGuardarPartida;
import konquest.backed.analizador.partida.SintaxGuardarPartida;
import konquest.backed.objetos.Ataque;
import konquest.backed.objetos.Espacio;
import konquest.backed.objetos.EstructuraKonquest;
import konquest.backed.objetos.Jugadores;
import konquest.backed.objetos.Planetas;
import konquest.backed.objetos.Tamaño;
import konquest.gui.DialogCrearYEditarMapa;
import konquest.gui.DialogoErrores;
import konquest.gui.Principal;

/**
 *
 * @author bryan
 */
public class ManejadorGUI {
    private JButton matriz[][];
    private Planetas[][] planets;
    private Tamaño tmño;
    private final ManejadorNombrePlanetas manejadorNombrePlanetas = new ManejadorNombrePlanetas();
    
    public void agregarToolText(int i, int j, boolean mapaCiego){
        ImageIcon imagen;
        ImageIcon imagenAgregar;
        if (planets[i][j] != null) {//Agregar Condicional Ver Stats
            if (mapaCiego) {
                matriz[i][j].setToolTipText("Nombre: " + planets[i][j].getNombre() + "   Naves: " + planets[i][j].getNaves() 
                        + "   Produccion: " + planets[i][j].getProduccion() + "   % de Muertes: " + planets[i][j].getPorcentajeMuertes() 
                + "  Can. Producida: " + planets[i][j].getCantidadProducida()); 
            }
            if (planets[i][j].getJugador() != null) {
                matriz[i][j].setText(planets[i][j].getNombre() + " > " + planets[i][j].getProduccion());
                imagen = new ImageIcon(getClass().getResource("/konquest/gui/imagenes/jugadores/" + (numerosAleatorios(16) + 1) +".jpg"));
            } else {
                matriz[i][j].setText(planets[i][j].getNombre());
                imagen = new ImageIcon(getClass().getResource("/konquest/gui/imagenes/neutrales/" + (numerosAleatorios(27) + 1) +".jpg"));
            }
        } else {
            imagen = new ImageIcon(getClass().getResource("/konquest/gui/imagenes/0.jpg"));
        }
        imagenAgregar = new ImageIcon(imagen.getImage().getScaledInstance(matriz[i][j].getWidth(), matriz[i][j].getHeight(), Image.SCALE_DEFAULT));
        matriz[i][j].setIcon(imagenAgregar);
    }
    
    public void agregarTxt(Planetas planeta, Principal principal){
        ImageIcon imagen;
        ImageIcon imagenAgregar;
        principal.getMatriz()[planeta.getFila()][planeta.getColumna()].setSize(50, 50);
        principal.getMatriz()[planeta.getFila()][planeta.getColumna()].setText(planeta.getNombre() + " > " + planeta.getProduccion());
        imagen = new ImageIcon(getClass().getResource("/konquest/gui/imagenes/jugadores/" + (numerosAleatorios(16) + 1) +".jpg"));
        imagenAgregar = new ImageIcon(imagen.getImage().getScaledInstance(principal.getMatriz()[planeta.getFila()][planeta.getColumna()].getWidth(), principal.getMatriz()[planeta.getFila()][planeta.getColumna()].getHeight(), Image.SCALE_DEFAULT));
        principal.getMatriz()[planeta.getFila()][planeta.getColumna()].setIcon(imagenAgregar);
    }
    
    private java.util.List<Planetas> agregarPlanetas(Planetas planetas[][], Tamaño t){
        java.util.List<Planetas> lista = new ArrayList<>();
        for (int i = 0; i < t.getFilas(); i++) {
            for (int j = 0; j < t.getColumnas(); j++) {
                if (planetas[i][j] != null) {
                    Planetas p = planetas[i][j];
                    p.setFila(i);
                    p.setColumna(j);
                    lista.add(p);
                }
            }
        }
        return lista;
    }
    
    public void construirDialogoEditar(EstructuraKonquest estructuraKonquest, JFrame frame, String path) {
        if (estructuraKonquest != null) {
            agregarPlanetasAJugador(estructuraKonquest);
            DialogCrearYEditarMapa dialog = new DialogCrearYEditarMapa(frame, true, true, estructuraKonquest.getEspacioPlanetas().getListaPlanetas(), estructuraKonquest.getEspacioPlanetasNeutrales().getListaPlanetas(), estructuraKonquest.getJugadores(), estructuraKonquest.getMapa(), path);
            dialog.setVisible(true);
        }
    }
    
    public void construir(EstructuraKonquest estructuraKonquest, JPanel panelPricipal, JFrame frame, JScrollPane panelScroll, Principal principal) {
        Planetas [][] planetas;
        if (estructuraKonquest != null) {
            Tamaño tamaño = estructuraKonquest.getMapa().getAtributtosMapa().getTamaño();
            planetas = new Planetas[tamaño.getFilas()][tamaño.getColumnas()];
            if (estructuraKonquest.getMapa().getAtributtosMapa().isAlAzar()) {
                comprobarAlAzar(tamaño, estructuraKonquest, estructuraKonquest.getEspacioPlanetas(), estructuraKonquest.getEspacioPlanetasNeutrales());
                agregarPlanetasAJugadorAlAzar(estructuraKonquest.getEspacioPlanetas().getListaPlanetas(), estructuraKonquest);
            } else {
                agregarPlanetasAJugador(estructuraKonquest);
//                construirEspacio(tamaño, principal, estructuraKonquest.getEspacioPlanetasNeutrales(), estructuraKonquest.getEspacioPlanetas(), planetas);
            }
            construirEspacio(tamaño, principal, estructuraKonquest.getEspacioPlanetasNeutrales(), estructuraKonquest.getEspacioPlanetas(), planetas);
            principal.setMapa(estructuraKonquest.getMapa());
            principal.setListaJugadores(estructuraKonquest.getJugadores());
            principal.setJugadorEnTurno(jugadorInicial(estructuraKonquest.getJugadores()));
            principal.setTurno(1);
            principal.setCantidadPlanetas(estructuraKonquest.getEspacioPlanetas().getListaPlanetas().size() + estructuraKonquest.getEspacioPlanetasNeutrales().getListaPlanetas().size());
            principal.getTodosLosPlanetas().clear();
            principal.getTodosLosPlanetas().addAll(agregarPlanetas(planetas, tamaño));
            principal.setFinalizacion(estructuraKonquest.getMapa().getAtributtosMapa().getFinalizacion());
            principal.setMapaCiego(estructuraKonquest.getMapa().getAtributtosMapa().isMapaCiego());
            
            agregarTablero(panelPricipal, frame,  tamaño.getFilas(), tamaño.getColumnas(), panelScroll, principal, planetas);
        }
    }
    
    public void construirCargarPartida(EstructuraKonquest estructuraKonquest, JPanel panelPricipal, JFrame frame, JScrollPane panelScroll, Principal principal, boolean replay) {
        Planetas [][] planetas;
        if (estructuraKonquest != null) {
            Tamaño tamaño = estructuraKonquest.getMapa().getAtributtosMapa().getTamaño();
            planetas = new Planetas[tamaño.getFilas()][tamaño.getColumnas()];
            
            agregarPlanetasAJugador(estructuraKonquest);
            agregarPlanetasALosAtaques(estructuraKonquest);
            agregarAtaquesAJugador(estructuraKonquest);
            
            construirEspacioCargar(tamaño, principal, estructuraKonquest.getEspacioPlanetasNeutrales(), estructuraKonquest.getEspacioPlanetas(), planetas);
            principal.setMapa(estructuraKonquest.getMapa());
            principal.setListaJugadores(estructuraKonquest.getJugadores());
            
            principal.setJugadorEnTurno(obtenerJugadorIncial(estructuraKonquest));
            principal.setTurno(estructuraKonquest.getMapa().getTurnoActual());
            if (replay) {
                principal.inhabilitarBotonesReplay();
            }
            principal.setCantidadPlanetas(estructuraKonquest.getEspacioPlanetas().getListaPlanetas().size() + estructuraKonquest.getEspacioPlanetasNeutrales().getListaPlanetas().size());
            principal.getTodosLosPlanetas().clear();
            principal.getTodosLosPlanetas().addAll(agregarPlanetas(planetas, tamaño));
            principal.setFinalizacion(estructuraKonquest.getMapa().getAtributtosMapa().getFinalizacion());
            principal.setMapaCiego(estructuraKonquest.getMapa().getAtributtosMapa().isMapaCiego());
            
            agregarTablero(panelPricipal, frame,  tamaño.getFilas(), tamaño.getColumnas(), panelScroll, principal, planetas);
        }
    }
    
    private Jugadores obtenerJugadorIncial(EstructuraKonquest estructuraKonquest) {
        for (int i = 0; i < estructuraKonquest.getJugadores().size(); i++) {
            if (estructuraKonquest.getJugadores().get(i).getNombre().equalsIgnoreCase(estructuraKonquest.getMapa().getJugadorEnTurno().getNombre())) {
                return estructuraKonquest.getJugadores().get(i);
            }
        } return estructuraKonquest.getJugadores().get(0);
    }
    
    private void comprobarAlAzar(Tamaño tamaño, EstructuraKonquest konquest, Espacio espacioPlanetas, Espacio espacioNeutrales) {
        java.util.List<Planetas> listaPlanetas = new ArrayList<>();
        java.util.List<Planetas> listaPlanetasJugadores = new ArrayList<>();
        java.util.List<Planetas> listaPlanetasNeutrales = new ArrayList<>();
        java.util.List<String> nombresDePlanetas;
        int numeroPlanetas = konquest.getMapa().getAtributtosMapa().getPlanetasNeutrales();
        if (!((tamaño.getFilas()*tamaño.getColumnas()) >= numeroPlanetas)) {
            numeroPlanetas = tamaño.getFilas()*tamaño.getColumnas();
        } System.out.println("Numero de Planetas: " + numeroPlanetas);
        nombresDePlanetas = manejadorNombrePlanetas.listaNombres(numeroPlanetas);
        Planetas p;
        
        for (int i = 0; i < numeroPlanetas; i++) {
            p = new Planetas(nombresDePlanetas.get(i), ((int)(Math.random() * 23) + 2), ((int)(Math.random() * 18) + 2), ((Math.random() * 0.9) + 0.1));
            listaPlanetas.add(p);
        }
        for (int i = 0; i < konquest.getJugadores().size(); i++) {
            System.out.println("C J A " + i);
            listaPlanetasJugadores.add(listaPlanetas.get(i));
        }
        for (int i = (konquest.getJugadores().size()); i < listaPlanetas.size(); i++) {
            System.out.println("C J N " + i);
            listaPlanetasNeutrales.add(listaPlanetas.get(i));
        }
        espacioPlanetas.setListaPlanetas(listaPlanetasJugadores);
        espacioPlanetas.setNeutral(false);
        espacioNeutrales.setListaPlanetas(listaPlanetasNeutrales);
        espacioNeutrales.setNeutral(true);
    }
    
    private void agregarPlanetasAJugadorAlAzar(java.util.List<Planetas> lista, EstructuraKonquest estructuraKonquest) {
        for (int i = 0; i < estructuraKonquest.getJugadores().size(); i++) {
            System.out.println("A P A " + i);
            estructuraKonquest.getJugadores().get(i).getPlanetas().add(lista.get(i));
            lista.get(i).setJugador(estructuraKonquest.getJugadores().get(i));
        }
    }
    
    public void agregarPlanetasAJugador(EstructuraKonquest estructuraKonquest){
        Jugadores jugador;
        for (int i = 0; i < estructuraKonquest.getJugadores().size(); i++) {
            jugador = estructuraKonquest.getJugadores().get(i);
            for (int j = 0; j < jugador.getPlanetasCadena().size(); j++) {
                for (int k = 0; k < estructuraKonquest.getEspacioPlanetas().getListaPlanetas().size(); k++) {
                    Planetas planeta = estructuraKonquest.getEspacioPlanetas().getListaPlanetas().get(k);
                    if (jugador.getPlanetasCadena().get(j).equalsIgnoreCase(planeta.getNombre())) {
                        jugador.getPlanetas().add(planeta);
                        planeta.setJugador(jugador);
                    }
                }
            }   
        }
    }
    
    /* Metodo de cargar partida */
    public void agregarAtaquesAJugador(EstructuraKonquest estructuraKonquest){
        Jugadores jugador;
        java.util.List<Ataque> listaDeAtq = estructuraKonquest.getAtaques();
        for (int i = 0; i < listaDeAtq.size(); i++) {
            Ataque ataque = listaDeAtq.get(i);
            for (int j = 0; j < estructuraKonquest.getJugadores().size(); j++) {
                jugador = estructuraKonquest.getJugadores().get(j);
                if (ataque.getJugador().getNombre().equalsIgnoreCase(jugador.getNombre())) {
                    ataque.setJugador(jugador);
                    jugador.getListaAtaques().add(ataque);
                }
            }   
        }
    }
    
    /* Metodo de cargar partida */
    public void agregarPlanetasALosAtaques(EstructuraKonquest estructuraKonquest){
        agregarPlanetasAAtaque(estructuraKonquest.getAtaques(), estructuraKonquest.getEspacioPlanetas().getListaPlanetas());
        agregarPlanetasAAtaque(estructuraKonquest.getAtaques(), estructuraKonquest.getEspacioPlanetasNeutrales().getListaPlanetas());
    }
    
    /* Metodo de cargar partida */
    public void agregarPlanetasAAtaque(java.util.List<Ataque> listaAtaques, java.util.List<Planetas> listaPlanetas){
        Planetas planetas;
        for (int i = 0; i < listaAtaques.size(); i++) {
            Ataque ataque = listaAtaques.get(i);
            for (int j = 0; j < listaPlanetas.size(); j++) {
                planetas = listaPlanetas.get(j);
                if (ataque.getPlanetaDestino().getNombre().equalsIgnoreCase(planetas.getNombre())) {
                    ataque.setPlanetaDestino(planetas);
                } else if (ataque.getPlanetaOrigen().getNombre().equalsIgnoreCase(planetas.getNombre())) {
                    ataque.setPlanetaOrigen(planetas);
                }
            }   
        }
    }
    
    
    public void construirEspacio(Tamaño tamaño, Principal principal, Espacio espacioNeutrales, Espacio espacioJugadores, Planetas [][] planetas) {
        construirComprobacion(espacioJugadores, tamaño, planetas);
        construirComprobacion(espacioNeutrales, tamaño, planetas);
    }
    
    public void construirEspacioCargar(Tamaño tamaño, Principal principal, Espacio espacioNeutrales, Espacio espacioJugadores, Planetas [][] planetas) {
        construirComprobacionCarga(espacioJugadores, tamaño, planetas);
        construirComprobacionCarga(espacioNeutrales, tamaño, planetas);
    }
    
    public void actualizarToolText(Principal principal, boolean mostar) {
        if (mostar) {
            for (int i = 0; i < principal.getTmño().getFilas(); i++) {
                for (int j = 0; j < principal.getTmño().getColumnas(); j++) {
                    if (principal.getPlanets()[i][j] != null) {
                        principal.getMatriz()[i][j].setToolTipText("Nombre: " + principal.getPlanets()[i][j].getNombre() + "   Naves: " + principal.getPlanets()[i][j].getNaves() + "   Produccion: " + principal.getPlanets()[i][j].getProduccion() 
                                + "   % de Muertes: " + principal.getPlanets()[i][j].getPorcentajeMuertes() + "   Cant. Producida: " + principal.getPlanets()[i][j].getCantidadProducida());
                    }
                }
            }
        }
    }
    
    public void agregarFlotas(Principal principal){
        for (int i = 0; i < principal.getTmño().getFilas(); i++) {
            for (int j = 0; j < principal.getTmño().getColumnas(); j++) {
                if (principal.getPlanets()[i][j] != null) {
                    principal.getPlanets()[i][j].setCantidadProducida(principal.getPlanets()[i][j].getCantidadProducida() + principal.getPlanets()[i][j].getProduccion());
                    principal.getPlanets()[i][j].setNaves(principal.getPlanets()[i][j].getNaves() + principal.getPlanets()[i][j].getProduccion());
                }
            }
        }
    }
    
    public void comprobarAtaques(java.util.List<Ataque> lista, JTextArea textArea, int turno, Principal principal){
        for (int i = 0; i < lista.size(); i++) {
            System.out.println("Ataque: " + lista.get(i).getPlanetaOrigen().getNombre());
            System.out.println("T llegada " + lista.get(i).getTurnoLlegada());
            System.out.println("T " + turno);
            if (lista.get(i).getTurnoLlegada() <= turno) {
                if (lista.get(i).comprobarAtaque()) {
                    textArea.setText(textArea.getText() 
                            + "\nEl Planeta: " + lista.get(i).getPlanetaDestino().getNombre() 
                            + " -> Fue conquistado  por el Jugador: " + lista.get(i).getPlanetaOrigen().getJugador().getNombre());
                    agregarTxt(lista.get(i).getPlanetaDestino(), principal);
                    lista.get(i).recompensa();
                } else {
                    textArea.setText(textArea.getText()
                            + "\nEl Planeta: " + lista.get(i).getPlanetaDestino().getNombre() + " -> Se ha defendido al ataque del Jugador: " + lista.get(i).getPlanetaOrigen().getJugador().getNombre());
                }
                lista.remove(lista.get(i));
            }
        }
    }
    
    public void relizarAtaques(JTextArea textArea, int turno, java.util.List<Jugadores> listaJugadores, Principal principal) {
        for (int i = 0; i < listaJugadores.size(); i++) {
            System.out.println("Lista Jugadores: " + listaJugadores.get(i).getNombre());
            comprobarAtaques(listaJugadores.get(i).getListaAtaques(), textArea, turno, principal);
        }
    }
    
    public void construirComprobacionCarga(Espacio espacio, Tamaño tamaño, Planetas[][] planetas) {
        for (int i = 0; i < espacio.getListaPlanetas().size(); i++) {
            agregarPosicionPlaneta(planetas, espacio.getListaPlanetas().get(i), espacio.getListaPlanetas().get(i).getFila(), espacio.getListaPlanetas().get(i).getColumna());
        }
    }
    
    public void construirComprobacion(Espacio espacio, Tamaño tamaño, Planetas[][] planetas) {
        int valorFila;
        int valorColumna;
        for (int i = 0; i < espacio.getListaPlanetas().size(); i++) {
            valorFila = numerosAleatorios(tamaño.getFilas()-1);
            valorColumna = numerosAleatorios(tamaño.getColumnas()-1);
            comprobarPosicionesEnemigos(valorFila, valorColumna, tamaño.getFilas(), tamaño.getColumnas(), planetas, espacio.getListaPlanetas().get(i));
        }
    }
    
    private void agregarPosicionPlaneta(Planetas planetas [][], Planetas planetaAgregar, int valorX, int valorY){
        planetas[valorX][valorY] = planetaAgregar;
    }
    
    public void comprobarPosicionesEnemigos(int mComprobar, int nComprobar, int mInicial, int nInicial, Planetas planetas [][], Planetas planetaAgregar) {
        int valorX = mComprobar;
        int valorY = nComprobar;
        boolean comprobraEnemigo = false;
        while (comprobraEnemigo == false) {
            if (planetas[valorX][valorY] == null) {
                comprobraEnemigo = true;
                agregarPosicionPlaneta(planetas, planetaAgregar, valorX, valorY);
//                planetas[valorX][valorY] = planetaAgregar;
            } else {
                valorX = numerosAleatorios(mInicial-1);
                valorY = numerosAleatorios(nInicial-1);
            }
        }
    }
    
    private int numerosAleatorios(int inetervalo) {
        return ((int)(Math.random()*inetervalo));
    }
    
    private Jugadores jugadorInicial(java.util.List<Jugadores> lista){
        return lista.get(0);
    }
    
    public String leer(FileNameExtensionFilter extensionFilter, JFrame component, JPanel panelPrincipal, JScrollPane scrollPane, Principal principal, boolean editar, DialogoErrores errores) throws FileNotFoundException, IOException, Exception{
        String path = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(extensionFilter);
        int opcion = chooser.showOpenDialog(component);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String docIde = "";
            File archivo = chooser.getSelectedFile();
            path = archivo.getAbsolutePath();
            FileInputStream entrada;
            entrada = new FileInputStream(archivo);
            int e;
            while ((e=entrada.read()) != -1) {
                char caracter = (char) e;
                docIde += caracter;
            }
            entrada.close();
            errores.limpiarTXTArea();
            Lexico lexico = new Lexico(new StringReader(docIde));
            lexico.setErrores(errores);
            Sintax s = new Sintax(lexico, component, panelPrincipal, scrollPane, principal, editar, path, errores);
            s.parse();
            errores.setVisible(true);
        }
        return path;
    }
    
    public String leerCargaPartida(FileNameExtensionFilter extensionFilter, JFrame component, JPanel panelPrincipal, JScrollPane scrollPane, Principal principal, boolean editar, boolean replay, DialogoErrores errores) throws FileNotFoundException, IOException, Exception{
        String path = "";
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(extensionFilter);
        int opcion = chooser.showOpenDialog(component);
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String docIde = "";
            File archivo = chooser.getSelectedFile();
            path = archivo.getAbsolutePath();
            FileInputStream entrada;
            entrada = new FileInputStream(archivo);
            int e;
            while ((e=entrada.read()) != -1) {
                char caracter = (char) e;
                docIde += caracter;
            }
            entrada.close();
            if (!errores.isVisible()) {
                errores.limpiarTXTArea();
                errores.setVisible(false);
            }
            LexerGuardarPartida lexico = new LexerGuardarPartida(new StringReader(docIde));
            lexico.setErrores(errores);
            SintaxGuardarPartida s = new SintaxGuardarPartida(lexico, component, panelPrincipal, scrollPane, principal, editar, path, replay, errores);
            s.parse();
            errores.setVisible(true);
        }
        return path;
    }
    
    public void agregarTablero(JPanel panel, JFrame frame, int filas, int columnas, JScrollPane panelScroll, Principal principal, Planetas planeta[][]) {
        panel.setLayout(new java.awt.GridLayout(filas, columnas));
        matriz = new JButton[filas][columnas];
        tmño = new Tamaño(filas, columnas);
        planets = planeta;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j] = new JButton();
                matriz[i][j].setSize(50, 50);
                agregarToolText(i, j, principal.isMapaCiego());
                matriz[i][j].setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
                matriz[i][j].setBackground(new Color(0, 2, 31, 255));
                matriz[i][j].setForeground(Color.WHITE);
                matriz[i][j].setVisible(true);
                matriz[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        for (int k = 0; k < filas; k++) {
                            for (int l = 0; l < columnas; l++) {
                                if(e.getSource() == matriz[k][l]){
                                    if (e.getButton() == MouseEvent.BUTTON1) {
                                        System.out.println(k + ", " + l);
                                        System.out.println("Acción: " + principal.getAccion());
                                        if (principal.getAccion() != null) {
                                            if ( principal.getAccion().equalsIgnoreCase("Medir Distancia") || principal.getAccion().equalsIgnoreCase("Realizar Ataques")) {
                                                if (planeta[k][l] != null) {
                                                    if (principal.getPrimerPlaneta() == null) {
                                                        if (principal.getAccion().equalsIgnoreCase("Medir Distancia")) {
                                                            principal.setPrimerPlaneta(planeta[k][l]);
                                                            principal.getPrimerPlaneta().setFila(k); principal.getPrimerPlaneta().setColumna(l);
                                                            principal.getLblSeleccionadoPrimero().setText("Origen -> " + planeta[k][l].getNombre());
                                                        } else{
                                                            if (planeta[k][l].getJugador() != null && principal.getAccion().equalsIgnoreCase("Realizar Ataques") && planeta[k][l].getJugador().getNombre().equals(principal.getJugadorEnTurno().getNombre())) {
                                                                principal.setPrimerPlaneta(planeta[k][l]);
                                                                principal.getPrimerPlaneta().setFila(k); principal.getPrimerPlaneta().setColumna(l);
                                                                principal.getLblSeleccionadoPrimero().setText("Origen -> " + planeta[k][l].getNombre());
                                                            } else {
                                                                JOptionPane.showMessageDialog(null, "Lo siento solo debes puedes seleccionar tus planetas.", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                                                            }
                                                        }
                                                    } else {
                                                        if (principal.getAccion().equalsIgnoreCase("Medir Distancia")) {
                                                            principal.setSegundoPlaneta(planeta[k][l]);
                                                            principal.getSegundoPlaneta().setFila(k); principal.getSegundoPlaneta().setColumna(l);
                                                            principal.getLblSeleccionadoSegundo().setText("Destino -> " + planeta[k][l].getNombre());
                                                        } else {
                                                            if (principal.getAccion().equalsIgnoreCase("Realizar Ataques")) {
                                                                if (planeta[k][l].getJugador() != null) {
                                                                    if (!(planeta[k][l].getJugador().getNombre().equals(principal.getJugadorEnTurno().getNombre()))) {
                                                                        principal.setSegundoPlaneta(planeta[k][l]);
                                                                        principal.getSegundoPlaneta().setFila(k); principal.getSegundoPlaneta().setColumna(l);
                                                                        principal.getLblSeleccionadoSegundo().setText("Destino -> " + planeta[k][l].getNombre());
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Lo siento no puedes enviar ataques a tus propios planetas.", "ERROR", JOptionPane.ERROR_MESSAGE);
                                                                    }
                                                                } else {
                                                                    principal.setSegundoPlaneta(planeta[k][l]);
                                                                    principal.getSegundoPlaneta().setFila(k); principal.getSegundoPlaneta().setColumna(l);
                                                                    principal.getLblSeleccionadoSegundo().setText("Destino -> " + planeta[k][l].getNombre());
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                panel.add(matriz[i][j]);
                panel.updateUI();
            }
        }
        principal.setPlanets(planets);
        principal.setMatriz(matriz);
        principal.setTmño(tmño);
    }
}
