/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;

/**
 *
 * @author moren
 */
public class Habitacionmodelo {
    
    private Habitacionmodelo() {
    }
    
    public static Habitacionmodelo getInstance() {
        return habitacionHolder.INSTANCE;
    }
    
    private static class habitacionHolder {

        private static final Habitacionmodelo INSTANCE = new Habitacionmodelo();
    }
}
