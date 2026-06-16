package main;

import controlador.Logincontrolador;
import vista.Loginvista;
import javax.swing.SwingUtilities;

public class ArrancarPersonal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Loginvista login = new Loginvista();
            new Logincontrolador(login);
            login.setVisible(true);
        });
    }
}
