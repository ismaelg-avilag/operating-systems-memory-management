import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow {
    private JButton buttonRunAlgorithm;
    private JButton buttonUploadPhysicalFiles;
    private JButton buttonAddVirtualFile;
    private JButton buttonAddMemoryPartition;
    private JPanel mainPanel;
    private JPanel panelFilesEntered;
    private JPanel panelMemoryPartitioning;
    private JPanel panelMemoryManager;
    private JRadioButton radioButtonFirstFit;
    private JRadioButton radioButtonBestFit;
    private JRadioButton radioButtonWorstFit;
    private JRadioButton radioButtonNextFit;
    private JRadioButton radioButtonAddMemoryPartitionToBeginning;
    private JRadioButton radioButtonAddMemoryPartitionAtTheEnd;
    private JTable tableFiles;
    private JTable tableMemoryPartitioning;
    private JTable tableOutput;
    private JTextField textFieldFileName;
    private JTextField textFieldFileSize;
    private JTextField textFieldMemoryPartitionSize;

    private ArrayList<MemoryPartition> memoryPartitions;
    private ArrayList<File> files;


    public MainWindow()
    {
        ButtonGroup algorithmSelectionGroup = new ButtonGroup();
        algorithmSelectionGroup.add(radioButtonFirstFit);
        algorithmSelectionGroup.add(radioButtonBestFit);
        algorithmSelectionGroup.add(radioButtonWorstFit);
        algorithmSelectionGroup.add(radioButtonNextFit);

        ButtonGroup memoryPartitionsGroup = new ButtonGroup();
        memoryPartitionsGroup.add(radioButtonAddMemoryPartitionToBeginning);
        memoryPartitionsGroup.add(radioButtonAddMemoryPartitionAtTheEnd);

        uploadMemoryPartitions();
        uploadFiles();

        buttonUploadPhysicalFiles.addActionListener(e -> {

        });

        buttonAddVirtualFile.addActionListener(e -> {
            files.add(new File(textFieldFileName.getText(), Integer.parseInt(textFieldFileSize.getText())));
            textFieldFileName.setText("");
            textFieldFileSize.setText("");

            updateFilesTable();
        });

        buttonAddMemoryPartition.addActionListener(e -> {
            if(radioButtonAddMemoryPartitionToBeginning.isSelected())
                memoryPartitions.add(0, new MemoryPartition(Integer.parseInt(textFieldMemoryPartitionSize.getText()), true));
            else
                memoryPartitions.add(new MemoryPartition(Integer.parseInt(textFieldMemoryPartitionSize.getText()), true));

            updateMemoryPartitions();
        });

        buttonRunAlgorithm.addActionListener(e -> {
            freeMemoryPartitions();

            if(radioButtonFirstFit.isSelected())
                showResults(MemoryManagementAlgorithms.FirstFit(memoryPartitions, files));

            else if(radioButtonBestFit.isSelected())
                showResults(MemoryManagementAlgorithms.BestFit(memoryPartitions, files));

            else if(radioButtonWorstFit.isSelected())
                showResults(MemoryManagementAlgorithms.WorstFit(memoryPartitions, files));

            else if(radioButtonNextFit.isSelected())
                showResults(MemoryManagementAlgorithms.NextFit(memoryPartitions, files));

            updateMemoryPartitions();
        });
    }

    private void uploadMemoryPartitions()
    {
        memoryPartitions = readMemoryPartitionsFile("input-files/memory-partitions.txt");

        updateMemoryPartitions();
    }

    private void uploadFiles()
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

    private void showResults(ArrayList<String> output)
    {
        String[] columnNames = {"Partición", "Tamaño", "Archivo", "Tamaño"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableOutput.setModel(tableModel);

        for (String line : output) {
            String[] data = line.split(",");
            tableModel.addRow(data);
        }
    }

    private void freeMemoryPartitions()
    {
        for(MemoryPartition memoryPartition : memoryPartitions)
            memoryPartition.setFree(true);
    }

    private void updateMemoryPartitions()
    {
        String[] columnNames = {"Partición", "Tamaño", "Disponible"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableMemoryPartitioning.setModel(tableModel);

        for(int i=0; i<memoryPartitions.size(); i++) {
            Object[] data = {i, memoryPartitions.get(i).getSize() + " kb", memoryPartitions.get(i).isFree()};
            tableModel.addRow(data);
        }

        tableMemoryPartitioning.getColumnModel().getColumn(2).setCellRenderer(new CustomRenderer());
    }

    private void updateFilesTable()
    {
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
