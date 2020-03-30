/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konquest.backed.manejadores;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bryan
 */
public class ManejadorNombrePlanetas {
    
    private String letra(int caso){
        switch (caso) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            case 8:
                return "H";
            case 9:
                return "I";
            case 10:
                return "J";
            case 11:
                return "K";
            case 12:
                return "L";
            case 13:
                return "M";
            case 14:
                return "N";
            case 15:
                return "O";
            case 16:
                return "P";
            case 17:
                return "Q";
            case 18:
                return "R";
            case 19:
                return "S";
            case 20:
                return "T";
            case 21:
                return "U";
            case 22:
                return "V";
            case 23:
                return "W";
            case 24:
                return "X";
            case 25:
                return "Y";
            case 26:
                return "Z";
            default:
                throw new AssertionError();
        }
    }
    
    public List<String> listaNombres(int cantidad) {
        List<String> listaNombres = new ArrayList<>();
        int contador_1 = 1; 
        int contador_2 = 1;
        int contador_3 = 1;
        int contador = 1;
        while ((cantidad + 1) != contador) {
            if (contador <= 26) {
                listaNombres.add(letra(contador));
            } else {
                if (contador <= 702) {
                    String letraAgregar = letra(contador_1) + letra(contador_2);
                    listaNombres.add(letraAgregar);
                    if (contador_2 == 26) {
                        contador_2 = 1;
                        contador_1++;
                    } else {
                        contador_2++;
                    }
                } else {
                    if (contador == 703) {
                        contador_1 = 1;
                        contador_2 = 1;
                    }
                    if (contador <= 18279) {
                        String letraAgregar = letra(contador_1) + letra(contador_2) + letra(contador_3);
                        listaNombres.add(letraAgregar);
                        if (contador_3 == 26) {
                            if (contador_2 == 26) {
                                contador_2 = 1;
                                contador_1++;
                            } else {
                                contador_2++;
                            }
                            contador_3 = 1;
                        } else {
                            contador_3++;
                        }
                    }
                }
            }
            contador++;
        }
        return listaNombres;
    } 
}
