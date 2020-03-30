/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import konquest.backed.objetos.Jugadores;
import konquest.backed.objetos.Planetas;

/**
 *
 * @author bryan
 */
public class ManejadorIA {
    
    public Planetas buscarElMasDebil(Jugadores boot, List<Planetas> listaPlanetas) {
        List<Planetas> lista;
        if (listaPlanetas.size() <= 1) {
            return  listaPlanetas.get(0);
        } 
        lista = seleccionarPlanetas(listaPlanetas, boot);
        listaSeleccionadaProduccion(lista);
        return regresarSeleccion(lista);
    }
    
    public Planetas buscarElMasFuerte(Jugadores boot, List<Planetas> listaPlanetas) {
        List<Planetas> lista;
        if (listaPlanetas.size() <= 1) {
            return  listaPlanetas.get(listaPlanetas.size() - 1);
        } 
        lista = seleccionarPlanetas(listaPlanetas, boot);
        return lista.get(0);
    }
    
    private List<Planetas> seleccionarPlanetas(List<Planetas> lista, Jugadores boot) {
        List<Planetas> regresar = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getJugador() != null) {
                if (!lista.get(i).getJugador().getNombre().equals(boot.getNombre())) {
                    regresar.add(lista.get(i));
                }
            } else {
                regresar.add(lista.get(i));
            }
        }
        return  regresar;
    }
    
    private void ordenar(List<Planetas> lista, int caso) {
        switch (caso) {
            case 1:
                Collections.sort(lista, (Object c1, Object c2) -> new Integer(((Planetas)c1).getProduccion()).compareTo(((Planetas)c2).getProduccion()));
                break;
            case 2:
                Collections.sort(lista, (Object c1, Object c2) -> new Integer(((Planetas)c1).getNaves()).compareTo(((Planetas)c2).getNaves()));
                break;
            default:
                throw new AssertionError();
        }
    }
    
    private List<Planetas> listaSeleccionadaProduccion(List<Planetas> lista) {
        List<Planetas> listaOrdenada = new ArrayList<>();
        ordenar(lista, 1);
        for (int i = 0; i < lista.size(); i++) {
            if ((i == 0) || (i == 1) ) {
                listaOrdenada.add(lista.get(i));
            }
        }
        return listaOrdenada;
    }
    
    private Planetas regresarSeleccion(List<Planetas> listaOrdenada) {
        ordenar(listaOrdenada, 2);
        return listaOrdenada.get(0);
    }
}
