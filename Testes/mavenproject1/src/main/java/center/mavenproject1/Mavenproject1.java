/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package center.mavenproject1;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
/**
 *
 * @author matheus.sampaio
 */
public class Mavenproject1 {

    private JFrame frame;
    private JList<String> scriptList;
    private DefaultListModel<String> listModel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                Mavenproject1 window = new Mavenproject1();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Mavenproject1() {
        frame = new JFrame("Executar Scripts PowerShell");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        scriptList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(scriptList);
        
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JButton executeButton = new JButton("Executar Script");
        executeButton.addActionListener(e -> executeScript());
        frame.getContentPane().add(executeButton, BorderLayout.SOUTH);

        loadScripts("C:\\Users\\MATHEUS.SAMPAIO\\repositorio-scripts-powershell"); // Caminho onde estão os scripts PS1
    }

    private void loadScripts(String folderPath) {
        try {
            Path path = Paths.get(folderPath);
            List<String> scripts = Files.walk(path)
                                        .filter(p -> p.toString().endsWith(".ps1"))
                                        .map(Path::toString)
                                        .toList();
            
            for (String script : scripts) {
                listModel.addElement(script);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void executeScript() {
        String selectedScript = scriptList.getSelectedValue();
        if (selectedScript != null) {
            try {
                ProcessBuilder processBuilder = new ProcessBuilder("powershell", "-ExecutionPolicy", "ByPass", "-File", selectedScript);
                Process process = processBuilder.start();
                process.waitFor();
                JOptionPane.showMessageDialog(frame, "Script executado com sucesso!");
            } catch (IOException | InterruptedException e) {
                JOptionPane.showMessageDialog(frame, "Erro ao executar o script: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um script para executar.");
        }
    }
}
