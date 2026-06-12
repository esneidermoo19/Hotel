/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package controlador;

/**
 *
 * @author moren
 */
public class Reservacioncontrolador {
    
    private Reservacioncontrolador() {
    }
    
    public static Reservacioncontrolador getInstance() {
        return reservacioncontroladorHolder.INSTANCE;
    }
    
    private static class reservacioncontroladorHolder {

        private static final Reservacioncontrolador INSTANCE = new Reservacioncontrolador();
    }
}
