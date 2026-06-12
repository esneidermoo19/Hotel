/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package controlador;

/**
 *
 * @author moren
 */
public class Habitacioncontrolador {
    
    private Habitacioncontrolador() {
    }
    
    public static Habitacioncontrolador getInstance() {
        return habitacioncontroladorHolder.INSTANCE;
    }
    
    private static class habitacioncontroladorHolder {

        private static final Habitacioncontrolador INSTANCE = new Habitacioncontrolador();
    }
}
