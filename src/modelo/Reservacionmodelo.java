/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package modelo;

/**
 *
 * @author moren
 */
public class Reservacionmodelo {
    
    private Reservacionmodelo() {
    }
    
    public static Reservacionmodelo getInstance() {
        return reservacionHolder.INSTANCE;
    }
    
    private static class reservacionHolder {

        private static final Reservacionmodelo INSTANCE = new Reservacionmodelo();
    }
}
