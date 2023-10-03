import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow {
    private JButton buttonRunAlgorithm;
    private JPanel mainPanel;
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

    private ArrayList<MemoryPartition> memoryPartitions;
    private ArrayList<File> files;


    public MainWindow()
    {
        ButtonGroup group = new ButtonGroup();
        group.add(radioButtonFirstFit);
        group.add(radioButtonBestFit);
        group.add(radioButtonWorstFit);
        group.add(radioButtonNextFit);

        loadMemoryPartitions();
        loadFiles();

        buttonRunAlgorithm.addActionListener(e -> {

        });
    }

    private void loadMemoryPartitions()
    {
        memoryPartitions = readMemoryPartitionsFile("input-files/memory-partitions.txt");

        String[] columnNames = {"Tamaño", "Libre"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableMemoryPartitioning.setModel(tableModel);

        for (MemoryPartition memoryPartition : memoryPartitions) {
            Object[] data = {memoryPartition.getSize() + " kb", memoryPartition.isFree()};
            tableModel.addRow(data);
        }
    }

    private void loadFiles()
    {
        files = readFiles("input-files/files.txt");

        String[] columnNames = {"Nombre", "Tamaño"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableFiles.setModel(tableModel);

        for (File file : files) {
            Object[] data = {file.getName(), file.getSize() + " kb"};
            tableModel.addRow(data);
        }
    }


    private ArrayList<MemoryPartition> readMemoryPartitionsFile(String path)
    {
        ArrayList<MemoryPartition> memoryPartitions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while(line != null) {
                memoryPartitions.add(new MemoryPartition(Integer.parseInt(line), true));
                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return memoryPartitions;
    }

    private ArrayList<File> readFiles(String path)
    {
        ArrayList<File> files = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();

            while(line != null) {
                String[] data = line.split(",");

                files.add(new File(data[0], Integer.parseInt(data[1])));
                line = reader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return files;
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
