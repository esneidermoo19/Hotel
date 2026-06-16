package main;

import vista.Habitacionvista;
import javax.swing.SwingUtilities;

public class ArrancarClientes {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Habitacionvista vista = new Habitacionvista();
            vista.setVisible(true);
        });
    }
}
