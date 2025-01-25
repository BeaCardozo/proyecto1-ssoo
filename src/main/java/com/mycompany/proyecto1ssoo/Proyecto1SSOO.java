
package com.mycompany.proyecto1ssoo;

/**
 *
 * @author beacardozo
 */
 import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Proyecto1SSOO extends JFrame {
    private JTextField processNameField;
    private JRadioButton ioBoundOption, cpuBoundOption;
    private JTextField cyclesNeededField, cyclesSatisfiedField, cycleDurationField;
    private JComboBox<String> activeProcessorsCombo;
    private JComboBox<String> schedulingPolicyCombo;
    private JLabel currentProcessLabel, executingInstructionLabel, clockCycleLabel;
    private JButton startSimulationButton, downloadButton;

    // Opciones que solo se muestran cuando I/O Bound
    private JPanel ioFieldsPanel;

    public Proyecto1SSOO() {
        setTitle("Simulación de Procesos");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de Información del Proceso 
        JPanel inputPanel = new JPanel();
        inputPanel.setBorder(BorderFactory.createTitledBorder("Información del Proceso"));
        inputPanel.setLayout(new GridLayout(9, 1));
        inputPanel.setPreferredSize(new Dimension(500, 0)); 

        // Campo del nombre del proceso
        processNameField = new JTextField(20);
        processNameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isLetterOrDigit(e.getKeyChar())) {
                    e.consume(); // Ignorar el input si no es letra o número
                }
            }
        });

        inputPanel.add(createPanelForLabelAndField("Nombre del Proceso:", processNameField));

        // Selección característica
        JPanel typePanel = new JPanel();
        typePanel.add(new JLabel("Tipo:"));
        ButtonGroup typeGroup = new ButtonGroup();
        ioBoundOption = new JRadioButton("I/O Bound");
        cpuBoundOption = new JRadioButton("CPU Bound");

        typeGroup.add(ioBoundOption);
        typeGroup.add(cpuBoundOption);
        typePanel.add(ioBoundOption);
        typePanel.add(cpuBoundOption);
        inputPanel.add(typePanel);

        // Panel oculto de I/O Bound
        ioFieldsPanel = new JPanel();
        ioFieldsPanel.setLayout(new GridLayout(3, 2));
        
        // Ciclos necesarios para excepción
        cyclesNeededField = new JTextField(10);
        cyclesNeededField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignorar el input si no es un número
                }
            }
        });

        // Ciclos necesarios para satisfacerla
        cyclesSatisfiedField = new JTextField(10);
        cyclesSatisfiedField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignorar el input si no es un número
                }
            }
        });

        ioFieldsPanel.add(createPanelForLabelAndField("Ciclos necesarios para excepción:", cyclesNeededField));
        ioFieldsPanel.add(createPanelForLabelAndField("Ciclos necesarios para satisfacerla:", cyclesSatisfiedField));
        ioFieldsPanel.setVisible(false); 
        inputPanel.add(ioFieldsPanel);

        // Campo duración del ciclo
        cycleDurationField = new JTextField(10);
        cycleDurationField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume(); // Ignorar el input si no es un número
                }
            }
        });
        inputPanel.add(createPanelForLabelAndField("Duración del ciclo (ms):", cycleDurationField));

        // Campo número de procesadores activos
        JPanel processorPanel = new JPanel();
        processorPanel.add(new JLabel("Número de procesadores activos:"));
        activeProcessorsCombo = new JComboBox<>(new String[]{"2", "3"});
        processorPanel.add(activeProcessorsCombo);
        inputPanel.add(processorPanel);

        // Selector de políticas de planificación
        JPanel schedulingPanel = new JPanel();
        schedulingPanel.add(new JLabel("Política de planificación:"));
        schedulingPolicyCombo = new JComboBox<>(new String[]{"FCFS", "SJF", "Round Robin"});
        schedulingPanel.add(schedulingPolicyCombo);
        inputPanel.add(schedulingPanel);

        // Botón de iniciar simulación
        startSimulationButton = new JButton("Iniciar Simulación");
        inputPanel.add(startSimulationButton);
        startSimulationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validateInputs();
            }
        });

        // ActionListener para mostrar/ocultar campos en función de la selección
        ioBoundOption.addActionListener(e -> toggleIoFields());
        cpuBoundOption.addActionListener(e -> toggleIoFields());

        // Panel de Detalles de Ejecución 
        JPanel executionPanel = new JPanel();
        executionPanel.setBorder(BorderFactory.createTitledBorder("Detalles de Ejecución"));
        executionPanel.setLayout(new GridLayout(1, 3)); 

        // Lista de listos
        executionPanel.add(createUneditableListPanel("Lista de Listos", new String[]{"P1", "P2"}));
        // Lista de bloqueados
        executionPanel.add(createUneditableListPanel("Lista de Bloqueados", new String[]{"P3"}));
        // Lista de culminados
        executionPanel.add(createUneditableListPanel("Lista de Culminados", new String[]{"P0"}));

        
        add(executionPanel, BorderLayout.EAST);

        // Información del estado actual - arreglar mejor...
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(3, 1));
        currentProcessLabel = new JLabel("Proceso Actual: N/A");
        executingInstructionLabel = new JLabel("Instrucción a ejecutar: N/A");
        clockCycleLabel = new JLabel("Ciclo de reloj: 0");
        statusPanel.add(currentProcessLabel);
        statusPanel.add(executingInstructionLabel);
        statusPanel.add(clockCycleLabel);

        
        executionPanel.add(statusPanel);
        
        // Panel contenedor
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(inputPanel);
        mainPanel.add(executionPanel);
        
        
        add(mainPanel, BorderLayout.CENTER);

        // Espacio para la gráfica
        JPanel graphicPanel = new JPanel();
        graphicPanel.setBorder(BorderFactory.createTitledBorder("Gráfica"));
        graphicPanel.setPreferredSize(new Dimension(1000, 300)); // Alto grande para el área gráfica
        graphicPanel.add(new JLabel("Aquí irá la gráfica"));
        add(graphicPanel, BorderLayout.SOUTH);

        // Botón de descargar TXT
        downloadButton = new JButton("Descargar TXT");
        graphicPanel.add(downloadButton);
    }

    private JPanel createPanelForLabelAndField(String label, JTextField field) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        panel.add(new JLabel(label));
        panel.add(field);
        return panel;
    }

    private JPanel createUneditableListPanel(String title, String[] items) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

        for (String item : items) {
            JLabel label = new JLabel(item);
            label.setBackground(Color.LIGHT_GRAY);
            label.setOpaque(true);
            label.setPreferredSize(new Dimension(100, 30)); 
            panel.add(label);
        }

        return panel;
    }

    private void toggleIoFields() {
        // Mostrar u ocultar los campos de I/O 
        ioFieldsPanel.setVisible(ioBoundOption.isSelected());
        revalidate();
        repaint();    
    }

    private void validateInputs() {
        // Validacion que todos los campos estén llenos
        if (processNameField.getText().trim().isEmpty() ||
            (ioBoundOption.isSelected() && (cyclesNeededField.getText().trim().isEmpty() || cyclesSatisfiedField.getText().trim().isEmpty())) ||
            cycleDurationField.getText().trim().isEmpty() ||
            activeProcessorsCombo.getSelectedItem() == null ||
            schedulingPolicyCombo.getSelectedItem() == null) {

            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        } 
        JOptionPane.showMessageDialog(this, "Simulación iniciada.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Proyecto1SSOO().setVisible(true);
        });
    }
}

