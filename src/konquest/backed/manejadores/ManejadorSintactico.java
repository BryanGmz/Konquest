/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.util.ArrayList;
import java.util.List;
import konquest.backed.objetos.*;
import konquest.gui.DialogoErrores;

/**
 *
 * @author bryan
 */
public class ManejadorSintactico {
    private DialogoErrores errores;
    
    public void setErrores(DialogoErrores errores) {
        this.errores = errores;
    }
    
    private void erroresTXT(String s, int left, int right){
        try {
            errores.agregarErroresTXTSintaxis("Error Sintaxis Token No Reconocido: << " + s + " >>"
                    + " Linea # " + (right + 1) + " Columna # " + (left + 1) + " ");
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            e.printStackTrace();
        }
    }
    
    private void agregarSalida(String s){
        errores.agregarAnalisisTXT(s);
    }
    
    /* Metodos de Nueva Partida */
    
    public AtributtosMapa agregarObjetosMapa(Object objeto, Object agregar, int l, int r, int attributo) {
        if (objeto != null) {
            AtributtosMapa atributtosMapa = (AtributtosMapa)objeto;
            switch (attributo) {
                case 1:
                    if (atributtosMapa.getiD() == null) {
                        atributtosMapa.setiD(agregar.toString());
                    } else {
                        erroresTXT(" ID <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 2:
                    if (atributtosMapa.getTamaño() == null) {
                        atributtosMapa.setTamaño((Tamaño)agregar);
                    } else {
                        erroresTXT(" Tamaño <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 3:
                    if (!atributtosMapa.iscAlAzar()) {
                        atributtosMapa.setAlAzar((boolean)agregar);
                        atributtosMapa.setcAlAzar(true);
                    } else {
                        erroresTXT(" AlAzar <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 4:
                    if (!atributtosMapa.iscPlanetasNeutrales()) {
                        atributtosMapa.setPlanetasNeutrales(Integer.parseInt(agregar.toString()));
                        atributtosMapa.setcPlanetasNeutrales(true);
                    } else {
                        erroresTXT(" PlanetasNeutrales <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 5:
                    if (!atributtosMapa.iscMapaCiego()) {
                        atributtosMapa.setMapaCiego((boolean)agregar);
                        atributtosMapa.setcMapaCiego(true);
                    } else {
                        erroresTXT( " MapaCiego <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 6:
                    if (!atributtosMapa.iscAcumular()) {
                        atributtosMapa.setAcumular((boolean)agregar);
                        atributtosMapa.setcAcumular(true);
                    } else {
                        erroresTXT(" Acumular <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 7:
                    if (atributtosMapa.getNeutrales() == null) {
                        atributtosMapa.setNeutrales((Neutrales)agregar);
                    } else {
                        erroresTXT(" Neutrales <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 8:
                    if (!atributtosMapa.iscFinalizacion()) {
                        atributtosMapa.setFinalizacion(Integer.parseInt(agregar.toString()));
                        atributtosMapa.setcFinalizacion(true);
                    } else {
                        erroresTXT(" Finalizacion <- Repetido ", l, r);
                        return null;
                    }
                    break;
                default:
                    return null;
            } agregarSalida(atributtosMapa.inf());
            return atributtosMapa;
        } else {
            return null;
        }
    }
    
    public AtributtosMapa comprobarLosAtributos(Object comprobar, int l, int r){
        if (comprobar != null) {
            AtributtosMapa atributtosMapa = (AtributtosMapa)comprobar;
            if (atributtosMapa.getiD() != null) {
                if (atributtosMapa.getTamaño() != null) {
                    if (atributtosMapa.getNeutrales() != null) {
                        if (atributtosMapa.isAlAzar()) {
                            if (atributtosMapa.getPlanetasNeutrales() != 0) {
                                agregarSalida(atributtosMapa.inf());
                                return atributtosMapa;
                            } else { erroresTXT(" } <- Token esperado -> PlanetasNeutrales ", l, r);}
                        } else {
                            agregarSalida(atributtosMapa.inf());
                            return atributtosMapa;
                        } 
                    } else { erroresTXT(" } <- Token esperado -> NEUTRALES ", l, r);}
                } else { erroresTXT(" } <- Token esperado -> Tamaño ", l, r);}
            } else { erroresTXT(" } <- Token esperado -> ID ", l, r);}
        } return null;
    }
    
    public AtributtosMapa agregarUnAtributto(Object agregar, int attributo) {
        AtributtosMapa atributtosMapa = new AtributtosMapa();
        switch (attributo) {
            case 1:
                atributtosMapa.setiD(agregar.toString());
                break;
            case 2:
                atributtosMapa.setTamaño((Tamaño)agregar);
                break;
            case 3:
                atributtosMapa.setAlAzar((boolean)agregar);
                atributtosMapa.setcAlAzar(true);
                break;
            case 4:
                atributtosMapa.setPlanetasNeutrales(Integer.parseInt(agregar.toString()));
                atributtosMapa.setcPlanetasNeutrales(true);
                break;
            case 5:
                atributtosMapa.setMapaCiego((boolean)agregar);
                atributtosMapa.setcMapaCiego(true);
                break;
            case 6:
                atributtosMapa.setAcumular((boolean)agregar);
                atributtosMapa.setcAcumular(true);
                break;
            case 7:
                atributtosMapa.setNeutrales((Neutrales)agregar);
                break;
            case 8:
                atributtosMapa.setFinalizacion(Integer.parseInt(agregar.toString()));
                atributtosMapa.setcFinalizacion(true);
                break;
            default:
                System.out.println("Error agregando");
        } agregarSalida(atributtosMapa.inf());
        return atributtosMapa;
    }
    
    public Planetas agregarUnAtributoPlanetas(Object objetoAgregar, int caso) {
        Planetas planetas = new Planetas();
        switch (caso) {
            case 1://Nombres
                planetas.setNombre(objetoAgregar.toString());
                break;
            case 2://Porcentaje
                planetas.setPorcentajeMuertes((double)objetoAgregar);
                break;
            case 3://Naves
                planetas.setNaves((int)objetoAgregar);
                planetas.setNavesExiste(true);
                break;
            case 4://Produccion
                planetas.setCantidadProducida(Integer.parseInt(objetoAgregar.toString()));
                planetas.setCantidadProducidaExiste(true);
                break;
            case 5://Fila
                planetas.setFila(Integer.parseInt(objetoAgregar.toString()));
                planetas.setFilaExiste(true);
                break;
            case 6://Columna
                planetas.setColumna(Integer.parseInt(objetoAgregar.toString()));
                planetas.setColumnaExiste(true);
                break;
            case 7://Cantidad Producida
                planetas.setCantidadProducida(Integer.parseInt(objetoAgregar.toString()));
                planetas.setCantidadProducidaExiste(true);
                break;
            default:
                throw new AssertionError();
        }
        agregarSalida(planetas.inf());
        return planetas;
    }
    
    public Planetas agregarDosAtributos(Object objetoAgregar1, Object objetoAgregar2, int casoDeclaraciones) {
        Planetas planetas = new Planetas();
        switch (casoDeclaraciones) {
            case 1://Declaracion Nombre Naves
                planetas.setNombre(objetoAgregar1.toString());
                planetas.setNaves((int)objetoAgregar2);
                break;
            case 2://Declaracion Nombre Produccion
                planetas.setNombre(objetoAgregar1.toString());
                planetas.setProduccion((int)objetoAgregar2);
                break;
            case 3://Declaracion Nombre Porcentaje
                planetas.setNombre(objetoAgregar1.toString());
                planetas.setPorcentajeMuertes((double)objetoAgregar2);
                break;
            case 4://Declaracion Naves Produccion
                planetas.setNaves((int)objetoAgregar1);
                planetas.setProduccion((int)objetoAgregar2);
                break;
            case 5://Declaracion Naves Porcentaje
                planetas.setNaves((int)objetoAgregar1);
                planetas.setPorcentajeMuertes((double)objetoAgregar2);
                break;
            case 6://Declaracion Produccion Porcentaje
                planetas.setProduccion((int)objetoAgregar1);
                planetas.setPorcentajeMuertes((double)objetoAgregar2);
                break;
            default:
                return null;
        }
        agregarSalida(planetas.inf());
        return planetas;
    } 

    public Neutrales agregarNeutrales(Object mostrarNaves, Object mostrarEstaditicas, Object produccion) {
        return new Neutrales((boolean)mostrarNaves, (boolean)mostrarEstaditicas, Integer.parseInt(produccion.toString()));
    }
    
    public Tamaño agregarTamaño(Object filas, Object columnas) {
        return new Tamaño(Integer.parseInt(filas.toString()), Integer.parseInt(columnas.toString()));
    }
    
    public EstructuraKonquest agregarDosAtributosKonquest(Object objetoAgregar1, Object objetoAgregar2, int casoDeclaraciones) {
        EstructuraKonquest estructuraKonquest = new EstructuraKonquest();
        switch (casoDeclaraciones) {
            case 1://Declaracion Planetas Neutrales - Jugadores
                estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(((List<Planetas>)objetoAgregar1), true));
                estructuraKonquest.setJugadores((List<Jugadores>)objetoAgregar2);
                break;
            case 2://Declaracion Planetas - Jugadores
                estructuraKonquest.setEspacioPlanetas(new Espacio(((List<Planetas>)objetoAgregar1), false));
                estructuraKonquest.setJugadores((List<Jugadores>)objetoAgregar2);
                break;
            case 3://Declaracion Planetas Neutrales - Planetas
                estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(((List<Planetas>)objetoAgregar1), true));
                estructuraKonquest.setEspacioPlanetas(new Espacio(((List<Planetas>)objetoAgregar2), false));
                break;
            case 4://Declaracion Mapa - Jugadores
                estructuraKonquest.setMapa((Mapa)objetoAgregar1);
                estructuraKonquest.setJugadores((List<Jugadores>)objetoAgregar2);
                break;
            case 5://Declaracion Planetas Neutrales - Mapa
                estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(((List<Planetas>)objetoAgregar1), true));
                estructuraKonquest.setMapa((Mapa)objetoAgregar2);
                break;
            case 6://Declaracion Planetas - Mapa
                estructuraKonquest.setEspacioPlanetas(new Espacio(((List<Planetas>)objetoAgregar1), false));
                estructuraKonquest.setMapa((Mapa)objetoAgregar2);
                break;
            default:
                return null;
        }
        agregarSalida(estructuraKonquest.inf());
        return estructuraKonquest;
    } 

    public EstructuraKonquest agregarAtributosKonquest(Object objetoAgregar, Object planeta, int caso) {
        EstructuraKonquest estructuraKonquest = (EstructuraKonquest)planeta;
        switch (caso) {
            case 1://Agregar Planetas
                estructuraKonquest.setEspacioPlanetas(new Espacio(((List<Planetas>)objetoAgregar), false));
                break;
            case 2://Agregar Planetas Neutrales
                estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(((List<Planetas>)objetoAgregar), true));
                break;
            case 3://Agregar Jugadores
                estructuraKonquest.setJugadores((List<Jugadores>)objetoAgregar);
                break;
            case 4://Agregar Mapa
                estructuraKonquest.setMapa((Mapa)objetoAgregar);
                break;
            default:
                return null;
        }
        agregarSalida(estructuraKonquest.inf());
        return estructuraKonquest;
    }
    
    public EstructuraKonquest comprobarStruct(Object objeto, int l, int r) {
        if (((EstructuraKonquest)objeto).getJugadores() != null) {
            if (((EstructuraKonquest)objeto).getMapa()!= null) {
                if (((EstructuraKonquest)objeto).getEspacioPlanetas()!= null) {
                    if (((EstructuraKonquest)objeto).getEspacioPlanetasNeutrales()!= null) {
                        comprobarProducciones(((EstructuraKonquest)objeto).getEspacioPlanetasNeutrales(), ((EstructuraKonquest)objeto).getMapa().getAtributtosMapa().getNeutrales());
                        agregarAcumular(((EstructuraKonquest)objeto).getMapa(), ((EstructuraKonquest)objeto).getEspacioPlanetas(), ((EstructuraKonquest)objeto).getEspacioPlanetasNeutrales());
                        agregarSalida(((EstructuraKonquest)objeto).inf());
                        return (EstructuraKonquest)objeto;
                    } else { erroresTXT(" } <- Token esperado -> PLANETAS_NEUTRALES ", l, r);}
                } else { erroresTXT(" } <- Token esperado -> PLANETAS ", l, r);} 
            } else { erroresTXT(" } <- Token esperado -> MAPA ", l, r);} 
        } else { erroresTXT(" } <- Token esperado -> JUGADORES ", l, r);}
        return null;
    }
    
    public Mapa mapaComprobar(Object objeto, int l, int r){
        if (comprobarLosAtributos(((Mapa)objeto).getAtributtosMapa(), l, r) != null) {
            agregarSalida(((Mapa)objeto).inf()); 
            return (Mapa)objeto;
        } else {
            erroresTXT("}", l, r);
            return null;
        }
    }
    
    private void comprobarProducciones(Espacio espacioNeutrales, Neutrales neutrales) {
        for (int i = 0; i < espacioNeutrales.getListaPlanetas().size(); i++) {
            if (espacioNeutrales.getListaPlanetas().get(i).getProduccion() == 0) {
                espacioNeutrales.getListaPlanetas().get(i).setProduccion(neutrales.getProduccion());
            }
        }
    }
    
    private void agregarAcumular(Mapa mapa, Espacio espacioPlanetas, Espacio espacioPlanetasNeutrales) {
        if (mapa.getAtributtosMapa().isAcumular() == true) {
            comprobarAcumular(espacioPlanetas);
            comprobarAcumular(espacioPlanetasNeutrales);
        }
    }
    
    private void comprobarAcumular(Espacio espacio) {
        for (int i = 0; i < espacio.getListaPlanetas().size(); i++) {
            espacio.getListaPlanetas().get(i).setProduccion(1);
        }
    } 
    
    /* Metodos de Cargar Partida */
    
    public Mapa agregarObjetosMapaPartida(Object objeto, Object agregar, int l, int r, int attributo) {
        if (objeto != null) {
            Mapa mapa = (Mapa)objeto;
            AtributtosMapa atributtosMapa = mapa.getAtributtosMapa();
            Jugadores jugadores = new Jugadores();
            switch (attributo) {
                case 1:
                    if (atributtosMapa.getTamaño() == null) {
                        atributtosMapa.setTamaño((Tamaño)agregar);
                    } else {
                        erroresTXT(" Tamaño <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 2:
                    if (!atributtosMapa.iscMapaCiego()) {
                        atributtosMapa.setMapaCiego((boolean)agregar);
                        atributtosMapa.setcMapaCiego(true);
                    } else {
                        erroresTXT(" MapaCiego <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 3:
                    if (!atributtosMapa.iscFinalizacion()) {
                        atributtosMapa.setFinalizacion(Integer.parseInt(agregar.toString()));
                        atributtosMapa.setcFinalizacion(true);
                    } else {
                        erroresTXT(" Finalizacion <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 4:
                    if (mapa.getCantidadPlanetas() == 0) {
                        mapa.setCantidadPlanetas(Integer.parseInt(agregar.toString()));
                    } else {
                        erroresTXT(" CantidadPlanetas <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 5:
                    if (mapa.getTurnoActual() == 0) {
                        mapa.setTurnoActual(Integer.parseInt(agregar.toString()));
                    } else {
                        erroresTXT(" TurnoActual <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 6:
                    if (mapa.getJugadorEnTurno() == null) {
                        jugadores.setNombre(agregar.toString());
                        mapa.setJugadorEnTurno(jugadores);
                    } else {
                        erroresTXT(" Jugador <- Repetido ", l, r);
                        return null;
                    }
                    break;
                default:
                    return null;
            }
            agregarSalida(mapa.inf());
            return mapa;
        } else {
            return null;
        }
    }
    
    public Mapa comprobarLosAtributosMapa(Object comprobar, int l, int r){
        if (comprobar != null) {
            Mapa mapa = (Mapa)comprobar; 
            AtributtosMapa atributtosMapa = mapa.getAtributtosMapa();
            if (atributtosMapa.getTamaño() != null) {
                if (mapa.getCantidadPlanetas() != 0) {
                    if (mapa.getTurnoActual() != 0) {
                        if (mapa.getJugadorEnTurno() != null) {
                            agregarSalida(mapa.inf());
                            return mapa;
                        } else { erroresTXT(" } <- Token esperado -> Jugador ", l, r);} 
                    } else { erroresTXT(" } <- Token esperado -> TurnoActual ", l, r);}
                } else { erroresTXT(" } <- Token esperado -> CantidadPlanetas ", l, r);}
            } else { erroresTXT(" } <- Token esperado -> Tamaño ", l, r);}
        } else { erroresTXT(" } ", l, r);}
        return null;
    }

    public Mapa agregarUnAtributoMapa(Object agregar, int attributo) {
        Mapa mapa = new Mapa();
        AtributtosMapa atributtosMapa = new AtributtosMapa();
        Jugadores jugador = new Jugadores();
        switch (attributo) {
            case 1:
                atributtosMapa.setTamaño((Tamaño)agregar);
                break;
            case 2:
                atributtosMapa.setMapaCiego((boolean)agregar);
                atributtosMapa.setcMapaCiego(true);
                break;
            case 3:
                atributtosMapa.setFinalizacion(Integer.parseInt(agregar.toString()));
                break;
            case 4:
                mapa.setCantidadPlanetas(Integer.parseInt(agregar.toString()));
                break;
            case 5:
                mapa.setTurnoActual(Integer.parseInt(agregar.toString()));
                break;
            case 6:
                jugador.setNombre(agregar.toString());
                mapa.setJugadorEnTurno(jugador);
                break;
            default:
                System.out.println("Error agregando");
        }
        mapa.setAtributtosMapa(atributtosMapa);
        agregarSalida(mapa.inf());
        return mapa;
    }
    
    public EstructuraKonquest agregarObjetosEstructura(Object objeto, Object agregar, int l, int r, int attributo) {
        if (objeto != null) {
            EstructuraKonquest estructuraKonquest = (EstructuraKonquest)objeto;
            switch (attributo) {
                case 1:
                    if (estructuraKonquest.getEspacioPlanetas()== null) {
                        if (agregar != null) {
                            estructuraKonquest.setEspacioPlanetas(new Espacio(((List<Planetas>)agregar), false));
                        }
                    } else {
                        erroresTXT(" PLANETAS -> Repetido ", l, r);
                        return null;
                    }
                    break;
                case 2:
                    if (estructuraKonquest.getEspacioPlanetasNeutrales() == null) {
                        if (agregar != null) {
                            estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(((List<Planetas>)agregar), true));
                        }
                    } else {
                        erroresTXT(" PLANETAS_NEUTRALES -> Repetido ", l, r);
                        return null;
                    }
                    break;
                case 3:
                    if (estructuraKonquest.getJugadores() == null) {
                        if (agregar != null) {
                            estructuraKonquest.setJugadores((List<Jugadores>)agregar);
                        }
                    } else {
                        erroresTXT(" JUGADORES -> Repetido ", l, r);
                        return null;
                    }
                    break;
                case 4:
                    if (estructuraKonquest.getMapa() == null) {
                        if (agregar != null) {
                            estructuraKonquest.setMapa((Mapa)agregar);
                        }
                    } else {
                        erroresTXT(" MAPA -> Repetido ", l, r);
                        return null;
                    }
                    break;
                case 5:
                    if (estructuraKonquest.getAtaques() == null) {
                        if (agregar != null) {
                            estructuraKonquest.setAtaques((List<Ataque>)agregar);
                        }
                    } else {
                        erroresTXT(" ATAQUE -> Repetido ", l, r);
                        return null;
                    }
                    break;
                default:
                    return null;
            }
            agregarSalida(estructuraKonquest.inf());
            return estructuraKonquest;
        } else {
            return null;
        }
    }
    
    public EstructuraKonquest agregarUnAtributoKonquest(Object objetoAgregar, int caso) {
        EstructuraKonquest estructuraKonquest = new EstructuraKonquest();
        switch (caso) {
            case 1://Agregar Planetas
                if (objetoAgregar != null) {
                    estructuraKonquest.setEspacioPlanetas(new Espacio(((List<Planetas>)objetoAgregar), false));
                }
                break;
            case 2://Agregar Planetas Neutrales
                if (objetoAgregar != null) {
                    estructuraKonquest.setEspacioPlanetasNeutrales(new Espacio(((List<Planetas>)objetoAgregar), true));
                }
                break;
            case 3://Agregar Jugadores
                if (objetoAgregar != null) {
                    estructuraKonquest.setJugadores((List<Jugadores>)objetoAgregar);
                }
                break;
            case 4://Agregar Mapa
                if (objetoAgregar != null) {
                    estructuraKonquest.setMapa((Mapa)objetoAgregar);
                }
                break;
            case 5://Agregar Ataques
                if (objetoAgregar != null) {
                    estructuraKonquest.setAtaques((List<Ataque>)objetoAgregar);
                }
                break;
            default:
                return null;
        }
        agregarSalida(estructuraKonquest.inf());
        return estructuraKonquest;
    }
    
    public EstructuraKonquest comprobarStructCargar(Object objeto, int l, int r) {
        if (objeto != null) {
            if (((EstructuraKonquest)objeto).getJugadores() != null) {
                if (((EstructuraKonquest)objeto).getMapa()!= null) {
                    if (((EstructuraKonquest)objeto).getEspacioPlanetas()!= null) {
                        if (((EstructuraKonquest)objeto).getEspacioPlanetasNeutrales()!= null) {
                            if (((EstructuraKonquest)objeto).getAtaques() != null) {
                                agregarSalida(((EstructuraKonquest)objeto).inf());
                                return (EstructuraKonquest)objeto;
                            } else { erroresTXT(" } <- Token esperado -> ATAQUES ", l, r);}
                        } else { erroresTXT(" } <- Token esperado -> PLANETAS_NEUTRALES ", l, r);}
                    } else { erroresTXT(" } <- Token esperado -> PLANETAS ", l, r);} 
                } else { erroresTXT(" } <- Token esperado -> MAPA ", l, r);}
            } else { erroresTXT(" } <- Token esperado -> JUGADORES ", l, r);}
        } else { erroresTXT(" } ", l, r);}
        return null;
    }
    
    public Planetas agregarObjetosPlanetas(Object objeto, Object agregar, int l, int r, int attributo) {/* Cargar */
        if (objeto != null) {
            Planetas planetas = (Planetas)objeto;
            switch (attributo) {
                case 1:
                    if (planetas.getNombre() == null) {
                        planetas.setNombre(agregar.toString());
                    } else {
                        erroresTXT(" Nombre <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 2:
                    if (planetas.getNaves() == 0) {
                        planetas.setNaves(Integer.parseInt(agregar.toString()));
                        planetas.setNavesExiste(true);
                    } else {
                        erroresTXT(" Naves <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 3:
                    if (planetas.getProduccion() == 0) {
                        planetas.setProduccion(Integer.parseInt(agregar.toString()));
                    } else {
                        erroresTXT(" Produccion <- Produccion ", l, r);
                        return null;
                    }
                    break;
                case 4:
                    if (planetas.getPorcentajeMuertes() == 0) {
                        planetas.setPorcentajeMuertes(Double.parseDouble(agregar.toString()));
                    } else {
                        erroresTXT(" PorcentajeMuertes <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 5:
                    if (!planetas.isFilaExiste()) {
                        planetas.setFila(Integer.parseInt(agregar.toString()));
                        planetas.setFilaExiste(true);
                    } else {
                        erroresTXT(" Fila <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 6:
                    if (!planetas.isColumnaExiste()) {
                        planetas.setColumna(Integer.parseInt(agregar.toString()));
                        planetas.setColumnaExiste(true);
                    } else {
                        erroresTXT(" Columna <- Repetido ", l, r);
                        return null;
                    }
                    break;
                case 7:
                    if (!planetas.isCantidadProducidaExiste()) {
                        planetas.setCantidadProducida(Integer.parseInt(agregar.toString()));
                        planetas.setCantidadProducidaExiste(true);
                    } else {
                        erroresTXT(" CantidadProducida <- Repetido ", l, r);
                        return null;
                    }
                    break;
                default:
                    return null;
            }
            agregarSalida(planetas.infCarga());
            return planetas;
        } else {
            return null;
        }
    }

    public Planetas comprobarPlanetas(Object comprobar, int l , int r){
        if (comprobar != null) {
            Planetas planeta = (Planetas)comprobar;
            if (planeta.getNombre() != null) {
                if (planeta.isNavesExiste()) {
                    if (planeta.getProduccion() != 0) {
                        if (planeta.getPorcentajeMuertes() != 0.0) {
                            if (planeta.isFilaExiste()) {
                                if (planeta.isColumnaExiste()) {
                                    if (planeta.isCantidadProducidaExiste()) {
                                        agregarSalida(planeta.infCarga());
                                        return planeta;
                                    } else { erroresTXT(" } <- Token esperado -> CantiidadProducida ", l, r);}
                                } else { erroresTXT(" } <- Token esperado -> Columna ", l, r);}
                            } else { erroresTXT(" } <- Token esperado -> Fila ", l, r);}
                        } else { erroresTXT(" } <- Token esperado -> PorcentajeMuertes ", l, r);}
                    } else { erroresTXT(" } <- Token esperado -> Produccion ", l, r);}
                } else { erroresTXT(" } <- Token esperado -> Nave ", l, r);}
            } else { erroresTXT(" } <- Token esperado -> Nombre ", l, r);} 
        } else { erroresTXT(" } ", l, r);}
        return null;
    } 
    
    public Ataque agregarObjetosAtaque(Object objeto, Object agregar, int l, int r, int attributo) {
        if (objeto != null) {
            Ataque ataque = (Ataque)objeto;
            Planetas planetas = new Planetas();
            Jugadores jugadores = new Jugadores();
            switch (attributo) {
                case 1:
                    if (ataque.getPlanetaDestino() == null) {
                        planetas.setNombre(agregar.toString());
                        ataque.setPlanetaDestino(planetas);
                    } else {
                        erroresTXT("PlanetaDestino <- Repetido", l, r);
                        return null;
                    }
                    break;
                case 2:
                    if (ataque.getPlanetaOrigen() == null) {
                        planetas.setNombre(agregar.toString());
                        ataque.setPlanetaOrigen(planetas);
                    } else {
                        erroresTXT("PlanetaOrigen <- Repetido", l, r);
                        return null;
                    }
                    break;
                case 3:
                    if (ataque.getJugador() == null) {
                        jugadores.setNombre(agregar.toString());
                        ataque.setJugador(jugadores);
                    } else {
                        erroresTXT("Jugador <- Repetido", l, r);
                        return null;
                    }
                    break;
                case 4:
                    if (ataque.getPorcentajeMuertes() == 0) {
                        ataque.setPorcentajeMuertes(Double.parseDouble(agregar.toString()));
                    } else {
                        erroresTXT("PorcentajeMuertes <- Repetido", l, r);
                        return null;
                    }
                    break;
                case 5:
                    if (ataque.getNumeroDeNaves() == 0) {
                        ataque.setNumeroDeNaves(Integer.parseInt(agregar.toString()));
                    } else {
                        erroresTXT("NumeroNaves <- Repetido", l, r);
                        return null;
                    }
                    break;
                case 6:
                    if (ataque.getTurnoLlegada() == 0) {
                        ataque.setTurnoLlegada(Integer.parseInt(agregar.toString()));
                    } else {
                        erroresTXT("TurnoLlegada <- Repetido", l, r);
                        return null;
                    }
                    break;
                default:
                    return null;
            }
            agregarSalida(ataque.inf());
            return ataque;
        } else {
            return null;
        }
    }
    
    public Ataque comprobarLosAtributosAtaque(Object comprobar, int l, int r){
        if (comprobar != null) {
            Ataque ataque = (Ataque)comprobar;
            if (ataque.getPlanetaDestino() != null) {
                if (ataque.getPlanetaOrigen() != null) {
                    if (ataque.getJugador() != null) {
                        if (ataque.getPorcentajeMuertes() != 0) {
                            if (ataque.getTurnoLlegada() != 0) {
                                agregarSalida(ataque.inf());
                                return ataque;
                            } else { erroresTXT(" } <- Token esperado -> Turno ", l, r);}
                        } else { erroresTXT(" } <- Token esperado -> PorcentajeMuertes ", l, r);}
                    } else { erroresTXT(" } <- Token esperado -> Jugador ", l, r);}
                } else { erroresTXT(" } <- Token esperado -> PlanetaOrigen ", l, r);}
            } else { erroresTXT(" } <- Token esperado -> PlanetaDestino ", l, r);}
        } else { erroresTXT("}", l, r);}
        return null;
    }
    
    public Ataque agregarUnAtributoAtaques(Object agregar, int attributo) {
        Ataque ataque = new Ataque();
        Planetas planetas = new Planetas();
        Jugadores jugador = new Jugadores();
        switch (attributo) {
            case 1:
                planetas.setNombre(agregar.toString());
                ataque.setPlanetaDestino(planetas);
                break;
            case 2:
                planetas.setNombre(agregar.toString());
                ataque.setPlanetaOrigen(planetas);
                break;
            case 3:
                jugador.setNombre(agregar.toString());
                ataque.setJugador(jugador);
                break;
            case 4:
                ataque.setPorcentajeMuertes(Double.parseDouble(agregar.toString()));
                break;
            case 5:
                ataque.setNumeroDeNaves(Integer.parseInt(agregar.toString()));
                break;
            case 6:
                ataque.setTurnoLlegada(Integer.parseInt(agregar.toString()));
                break;
            default:
                System.out.println("Error agregando");
        }
        agregarSalida(ataque.inf());
        return ataque;
    }
    
    /* Metodos para ambos */
    
    public Planetas agregarAtributos(Object objetoAgregar, Object planeta, int caso) {//Para cargar y estructura konquest
        Planetas planetas = (Planetas)planeta;
        switch (caso) {
            case 1://Agregar Naves
                planetas.setNaves((int)objetoAgregar);
                planetas.setNavesExiste(true);
                break;
            case 2://Agregar Produccion
                planetas.setProduccion((int)objetoAgregar);
                break;
            case 3://Agregar Porcentaje
                planetas.setPorcentajeMuertes((double)objetoAgregar);
                break;
            case 4://Agregar Nombre
                planetas.setNombre(objetoAgregar.toString());
                break;
            default:
                return null;
        }
        agregarSalida(planetas.inf());
        return planetas;
    }
    
    public List<Planetas> listadoPlanetasN(Object lista, Object agregar){
        List<Planetas> listaAgregar = new ArrayList<>();
        if (lista != null) {
            if (agregar != null) {
                if (lista instanceof Planetas) {
                    List<Planetas> listado = new ArrayList<>();
                    listado.add((Planetas)lista);
                    listado.add((Planetas)agregar);
                    return listado;
                } else {
                    ((List<Planetas>)lista).add((Planetas)agregar);
                    return (List<Planetas>)lista;
                }
            } else {
                if (lista instanceof Planetas) {
                    listaAgregar.add((Planetas)lista);
                } else {
                    return (List<Planetas>)lista;
                }
            }
        } else {
            if (agregar != null) {
                listaAgregar.add((Planetas)agregar);
            } else {
                return null;
            }
        } return listaAgregar;
    }
    
    public List<Planetas> listadoPlanetas(Object lista, Object agregar, int l, int r){
        List<Planetas> listaAgregar = new ArrayList<>();
        agregar = comprobarPlanetas(agregar, l, r);
        if (lista != null) {
            if (agregar != null) {
                if (lista instanceof Planetas) {
                    List<Planetas> listado = new ArrayList<>();
                    listado.add((Planetas)lista);
                    listado.add((Planetas)agregar);
                    return listado;
                } else {
                    ((List<Planetas>)lista).add((Planetas)agregar);
                    return (List<Planetas>)lista;
                }
            } else {
                if (lista instanceof Planetas) {
                    listaAgregar.add((Planetas)lista);
                } else {
                    return (List<Planetas>)lista;
                }
            }
        } else {
            if (agregar != null) {
                listaAgregar.add((Planetas)agregar);
            } else {
                return null;
            }
        } return listaAgregar;
    }
    
    public List<Ataque> listadoAtaques(Object lista, Object agregar, int l, int r){
        List<Ataque> listaAgregar = new ArrayList<>();
        agregar = comprobarLosAtributosAtaque(agregar, l, r);
        if (lista != null) {
            if (agregar != null) {
                if (lista instanceof Ataque) {
                    List<Ataque> listado = new ArrayList<>();
                    listado.add((Ataque)lista);
                    listado.add((Ataque)agregar);
                    return listado;
                } else {
                    ((List<Ataque>)lista).add((Ataque)agregar);
                    return (List<Ataque>)lista;
                }
            } else {
                if (lista instanceof Ataque) {
                    listaAgregar.add((Ataque)lista);
                } else {
                    return (List<Ataque>)lista;
                }
            }
        } else {
            if (agregar != null) {
                listaAgregar.add((Ataque)agregar);
            } else {
                return null;
            }
        } return listaAgregar;
        
    }
    
    public List<Jugadores> listadoJugadores(Object lista, Object agregar, int l, int r){
        List<Jugadores> listaAgregar = new ArrayList<>();
        agregar = comprobarJugadores(agregar, l, r);
        if (lista != null) {
            if (agregar != null) {
                if (lista instanceof Jugadores) {
                    List<Jugadores> listado = new ArrayList<>();
                    listado.add((Jugadores)lista);
                    listado.add((Jugadores)agregar);
                    return listado;
                } else {
                    ((List<Jugadores>)lista).add((Jugadores)agregar);
                    return (List<Jugadores>)lista;
                }
            } else {
                if (lista instanceof Jugadores) {
                    listaAgregar.add((Jugadores)lista);
                } else {
                    return (List<Jugadores>)lista;
                }
            }
        } else {
            if (agregar != null) {
                listaAgregar.add((Jugadores)agregar);
            } else {
                return null;
            }
        } 
        return listaAgregar;
    }
    
    public List<String> listadoPlanetasJugador(Object lista, Object agregar){
        if (lista instanceof String) {
            List<String> listado = new ArrayList<>();
            listado.add(((String)lista).replaceAll("\"", ""));
            listado.add(((String)agregar).replaceAll("\"", ""));
            return listado;
        } else {
            ((List<String>)lista).add(((String)agregar).replaceAll("\"", ""));
            return (List<String>)lista;
        }
    }
    
    public Jugadores agregarAtributosJugador(Object nombre, Object planetas, Object tipo){
        if (planetas instanceof String) {
            List<String> listaPlanetas = new ArrayList<>();
            listaPlanetas.add((String)planetas);
            return new Jugadores(nombre.toString(), tipo.toString(), listaPlanetas);
        } else {
            return new Jugadores(nombre.toString(), tipo.toString(), (List<String>)planetas);
        }
    }
    
    public Jugadores comprobarJugadores(Object comprobar, int l, int r) {
        if (comprobar != null) {
            Jugadores jugador = (Jugadores) comprobar;
            if (jugador.getNombre() != null && jugador.tipoCadena() != null && !jugador.getPlanetasCadena().isEmpty()) {
                agregarSalida(jugador.inf());
                return jugador;
            }
        } erroresTXT("}", l, r);
        return null;
    }
    
    public List<Planetas> listaPlanetasKonquest(Object objeto) {
        if (objeto != null) {
            if (objeto instanceof Planetas) {
                List<Planetas> lista = new ArrayList<>();
                lista.add((Planetas)objeto);
                return lista;
            } else {
                return ((List<Planetas>)objeto);
            }
        } else {
            return null;
        }
    }
    
    public List<Jugadores> listaJugadoresKonquest(Object objeto) {
        if (objeto != null) {
            if (objeto instanceof Jugadores) {
                List<Jugadores> lista = new ArrayList<>();
                lista.add((Jugadores)objeto);
                return lista;
            } else {
                return (List<Jugadores>)objeto;
            }
        } else {
            return null;
        }
    }
    
    public List<Ataque> listaAtaqueKonquest(Object objeto) {
        if (objeto != null) {
            if (objeto instanceof Ataque) {
                List<Ataque> lista = new ArrayList<>();
                lista.add((Ataque)objeto);
                return lista;
            } else {
                return (List<Ataque>)objeto;
            }
        } else {
            return null;
        }
    }
}
