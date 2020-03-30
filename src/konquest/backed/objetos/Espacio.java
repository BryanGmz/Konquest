/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.objetos;

import java.util.List;

/**
 *
 * @author bryan
 */
public class Espacio {
    private List<Planetas> listaPlanetas;
    private boolean neutral;

    public Espacio() {}

    public Espacio(List<Planetas> listaPlanetas, boolean neutral) {
        this.listaPlanetas = listaPlanetas;
        this.neutral = neutral;
    }

    public List<Planetas> getListaPlanetas() {
        return listaPlanetas;
    }

    public void setListaPlanetas(List<Planetas> listaPlanetas) {
        this.listaPlanetas = listaPlanetas;
    }

    public boolean isNeutral() {
        return neutral;
    }

    public void setNeutral(boolean neutral) {
        this.neutral = neutral;
    }
}
