import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Run GUI in Swing thread
        SwingUtilities.invokeLater(() -> {
            RecruitmentUI ui = new RecruitmentUI();
            ui.setVisible(true);
        });
    }
}
