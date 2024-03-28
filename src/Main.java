import Core.Services.AgendaService;
import Presentation.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AgendaScreen());
    }
}