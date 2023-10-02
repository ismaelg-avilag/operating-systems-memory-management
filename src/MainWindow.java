import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private JButton buttonLoadFiles;
    private JButton buttonRunAlgorithm;
    private JPanel mainPanel;
    private JPanel panelLoadFiles;
    private JPanel panelFilesEntered;
    private JPanel panelMemoryPartitioning;
    private JPanel panelMemoryManager;
    private JRadioButton radioButtonFirstFit;
    private JRadioButton radioButtonBestFit;
    private JRadioButton radioButtonWorstFit;
    private JRadioButton radioButtonNextFit;
    private JTable tableFiles;
    private JTable tableMemoryPartitioning;
    private JTable tableOutput;


    public MainWindow()
    {
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonFirstFit);
        group.add(radioButtonBestFit);
        group.add(radioButtonWorstFit);
        group.add(radioButtonNextFit);

        buttonLoadFiles.addActionListener(e -> {

        });

        buttonRunAlgorithm.addActionListener(e -> {

        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Algoritmos de Administración de Memoria");
        frame.setContentPane(new MainWindow().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
