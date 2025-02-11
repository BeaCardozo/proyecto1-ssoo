package igu;

import java.awt.Component;
import javax.swing.JOptionPane;
import com.mycompany.proyecto1ssoo.Simulator;
import com.mycompany.proyecto1ssoo.Process;
import com.mycompany.proyecto1ssoo.ProcessPCBPanel;
import com.mycompany.proyecto1ssoo.ProcessQueue;
import java.awt.Color;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel; 
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent; 
import javax.swing.JPanel;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.Component;
import javax.swing.BoxLayout;

/**
 *
 * @author beacardozo
 */
public class MainView extends javax.swing.JFrame {
    private Simulator simulator;

    /**
     * Creates new form PantallaPrincipal
     */
    public MainView() {
        initComponents();
        simulator = new Simulator(2); 
        updateButtonStates(); 
        setTitle("Process Simulator");
        disableJPanel(IOBoundPanel, IOBoundOption.isSelected());
        enablePanels(false);
    }
    
    public void enablePanels(boolean value) {
        disableJPanel(SimulationDetailsPanel, value);
        disableJPanel(DetailsPanel,value);
        disableJPanel(QueuePanel,value);
        disableJPanel(PCBPanel,value);
        ProccesesPerProcessorsTable.setEnabled(value);
        ReadyQueueList.setEnabled(value);
        BlockedQueueList.setEnabled(value);
        FinishedQueueList.setEnabled(value);
    } 
     
    public void disableJPanel(JPanel panel, boolean value) {
    for (Component a : panel.getComponents()) {
        a.setEnabled(value);
    }
}
    
    private void resetSpinners() {
        CyclesGenerateExcepSpinner.setValue(1);
        CyclesSatisfyExcepSpinner.setValue(1);
    }
    
    private void resetFields() {
        ProcessNameTextField.setText("");
        IOBoundOption.setSelected(true);
        CPUBoundOption.setSelected(false);
        resetSpinners();
    }
    
    private void updateButtonStates() {
        boolean isQueueEmpty = simulator.getReadyQueue().isEmpty();
        System.out.println("Is queue empty: " + isQueueEmpty); // Para depurar
        ClearProcessTableButton.setEnabled(!isQueueEmpty);
        DeleteProcessButton.setEnabled(!isQueueEmpty);
        StartSimulationButton.setEnabled(!isQueueEmpty);
    }
    
    private Process getSelectedProcess() {
        int selectedRow = ProcessTable.getSelectedRow(); 
        if (selectedRow >= 0) {
        return simulator.getReadyQueue().get(selectedRow);
        }
        return null; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProcessTypeGroup = new javax.swing.ButtonGroup();
        ActiveProcessorsGroup = new javax.swing.ButtonGroup();
        ConfigPanel = new javax.swing.JPanel();
        ProcessDetailsPanel = new javax.swing.JPanel();
        ProcessTypeLabel = new javax.swing.JLabel();
        IOBoundOption = new javax.swing.JRadioButton();
        CPUBoundOption = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ProcessTable = new javax.swing.JTable();
        ClearProcessTableButton = new javax.swing.JButton();
        AddProcessButton = new javax.swing.JButton();
        DeleteProcessButton = new javax.swing.JButton();
        ProcessNameLabel = new javax.swing.JLabel();
        ProcessNameTextField = new javax.swing.JTextField();
        IOBoundPanel = new javax.swing.JPanel();
        CyclesSatisfyExcepLabel = new javax.swing.JLabel();
        CyclesGenerateExcepLabel = new javax.swing.JLabel();
        CyclesSatisfyExcepSpinner = new javax.swing.JSpinner();
        CyclesGenerateExcepSpinner = new javax.swing.JSpinner();
        SystemSpecificationsPanel = new javax.swing.JPanel();
        PlanningPolicyLabel = new javax.swing.JLabel();
        CicleDurationLabel = new javax.swing.JLabel();
        ActiveProcessorsTextField = new javax.swing.JLabel();
        CycleDurationTextField = new javax.swing.JTextField();
        PlanninPolicyComboBox = new javax.swing.JComboBox<>();
        TwoProcessorsOption = new javax.swing.JRadioButton();
        ThreeProcessorsOption = new javax.swing.JRadioButton();
        TimeUnitComboBox = new javax.swing.JComboBox<>();
        StartSimulationButton = new javax.swing.JButton();
        SimulationPanel = new javax.swing.JPanel();
        SystemPerfomanceMetricsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        GraphicsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        SimulationDetailsPanel = new javax.swing.JPanel();
        PCBPanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        DetailsPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ProccesesPerProcessorsTable = new javax.swing.JTable();
        ExecutionModeLabel = new javax.swing.JLabel();
        ProcessesProLabel = new javax.swing.JLabel();
        GlobalClockLabel = new javax.swing.JLabel();
        ModeLabel = new javax.swing.JLabel();
        QueuePanel = new javax.swing.JPanel();
        ReadyQueueLabel = new javax.swing.JLabel();
        BlockedQueueLabel = new javax.swing.JLabel();
        FinishedQueueLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ReadyQueueList = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        FinishedQueueList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        BlockedQueueList = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1000, 1000));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ConfigPanel.setBackground(new java.awt.Color(255, 255, 255));
        ConfigPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProcessDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        ProcessDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        ProcessDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProcessTypeLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        ProcessTypeLabel.setText("Process Type:");
        ProcessDetailsPanel.add(ProcessTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 230, -1));

        ProcessTypeGroup.add(IOBoundOption);
        IOBoundOption.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        IOBoundOption.setSelected(true);
        IOBoundOption.setText("I/O Bound");
        IOBoundOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IOBoundOptionActionPerformed(evt);
            }
        });
        ProcessDetailsPanel.add(IOBoundOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        ProcessTypeGroup.add(CPUBoundOption);
        CPUBoundOption.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        CPUBoundOption.setText("CPU Bound");
        CPUBoundOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CPUBoundOptionActionPerformed(evt);
            }
        });
        ProcessDetailsPanel.add(CPUBoundOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        ProcessTable.setAutoCreateRowSorter(true);
        ProcessTable.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        ProcessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process Name", "Process Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ProcessTable.setSelectionBackground(new java.awt.Color(169, 217, 241));
        ProcessTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ProcessTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ProcessTable);

        ProcessDetailsPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 260, 150));

        ClearProcessTableButton.setBackground(new java.awt.Color(169, 217, 241));
        ClearProcessTableButton.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        ClearProcessTableButton.setText("Clear");
        ClearProcessTableButton.setBorderPainted(false);
        ClearProcessTableButton.setOpaque(true);
        ClearProcessTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearProcessTableButtonActionPerformed(evt);
            }
        });
        ProcessDetailsPanel.add(ClearProcessTableButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 110, 30));

        AddProcessButton.setBackground(new java.awt.Color(169, 217, 241));
        AddProcessButton.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        AddProcessButton.setText("Add Process");
        AddProcessButton.setBorderPainted(false);
        AddProcessButton.setOpaque(true);
        AddProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddProcessButtonActionPerformed(evt);
            }
        });
        ProcessDetailsPanel.add(AddProcessButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 200, 30));

        DeleteProcessButton.setBackground(new java.awt.Color(169, 217, 241));
        DeleteProcessButton.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        DeleteProcessButton.setText("Delete");
        DeleteProcessButton.setBorderPainted(false);
        DeleteProcessButton.setOpaque(true);
        DeleteProcessButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteProcessButtonActionPerformed(evt);
            }
        });
        ProcessDetailsPanel.add(DeleteProcessButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 110, 30));

        ProcessNameLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        ProcessNameLabel.setText("Process Name:");
        ProcessDetailsPanel.add(ProcessNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, -1));

        ProcessNameTextField.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        ProcessNameTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ProcessNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ProcessNameTextFieldKeyTyped(evt);
            }
        });
        ProcessDetailsPanel.add(ProcessNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, -1));

        IOBoundPanel.setBackground(new java.awt.Color(255, 255, 255));
        IOBoundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CyclesSatisfyExcepLabel.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        CyclesSatisfyExcepLabel.setText("Cycles to satisfy an exception:");
        IOBoundPanel.add(CyclesSatisfyExcepLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        CyclesGenerateExcepLabel.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        CyclesGenerateExcepLabel.setText("Cycles to generate an exception:");
        IOBoundPanel.add(CyclesGenerateExcepLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        CyclesSatisfyExcepSpinner.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        CyclesSatisfyExcepSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        IOBoundPanel.add(CyclesSatisfyExcepSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));

        CyclesGenerateExcepSpinner.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        CyclesGenerateExcepSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        IOBoundPanel.add(CyclesGenerateExcepSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        ProcessDetailsPanel.add(IOBoundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 260, 120));

        ConfigPanel.add(ProcessDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 550));

        SystemSpecificationsPanel.setBackground(new java.awt.Color(255, 255, 255));
        SystemSpecificationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Specifications", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        SystemSpecificationsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PlanningPolicyLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        PlanningPolicyLabel.setText("Planning Policy:");
        SystemSpecificationsPanel.add(PlanningPolicyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 210, -1));

        CicleDurationLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        CicleDurationLabel.setText("Cycle Duration:");
        SystemSpecificationsPanel.add(CicleDurationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, -1));

        ActiveProcessorsTextField.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        ActiveProcessorsTextField.setText("Number of Active Processors:");
        SystemSpecificationsPanel.add(ActiveProcessorsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 200, -1));

        CycleDurationTextField.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        CycleDurationTextField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CycleDurationTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CycleDurationTextFieldKeyTyped(evt);
            }
        });
        SystemSpecificationsPanel.add(CycleDurationTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 160, -1));

        PlanninPolicyComboBox.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        PlanninPolicyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FCFS - First-Come, First-Served", "Round Robin", "SPN", "HRRN", "SJF - Shortest Job First", " " }));
        PlanninPolicyComboBox.setBorder(null);
        SystemSpecificationsPanel.add(PlanninPolicyComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 260, -1));

        ActiveProcessorsGroup.add(TwoProcessorsOption);
        TwoProcessorsOption.setSelected(true);
        TwoProcessorsOption.setText("2");
        SystemSpecificationsPanel.add(TwoProcessorsOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        ActiveProcessorsGroup.add(ThreeProcessorsOption);
        ThreeProcessorsOption.setText("3");
        SystemSpecificationsPanel.add(ThreeProcessorsOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        TimeUnitComboBox.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        TimeUnitComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "s", "ms" }));
        SystemSpecificationsPanel.add(TimeUnitComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, -1, -1));

        ConfigPanel.add(SystemSpecificationsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 570, 280, 240));

        StartSimulationButton.setBackground(new java.awt.Color(181, 241, 169));
        StartSimulationButton.setFont(new java.awt.Font("Geneva", 1, 14)); // NOI18N
        StartSimulationButton.setText("Start Simulation");
        StartSimulationButton.setBorderPainted(false);
        StartSimulationButton.setOpaque(true);
        StartSimulationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartSimulationButtonActionPerformed(evt);
            }
        });
        ConfigPanel.add(StartSimulationButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 830, 250, 50));

        getContentPane().add(ConfigPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 900));

        SimulationPanel.setBackground(new java.awt.Color(255, 255, 255));
        SimulationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SystemPerfomanceMetricsPanel.setBackground(new java.awt.Color(255, 255, 255));
        SystemPerfomanceMetricsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Perfomance Metrics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        SystemPerfomanceMetricsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("No information available yet.");
        SystemPerfomanceMetricsPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        SimulationPanel.add(SystemPerfomanceMetricsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 490, 250));

        GraphicsPanel.setBackground(new java.awt.Color(255, 255, 255));
        GraphicsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Graphics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        GraphicsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("No information available yet.");
        GraphicsPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, -1, -1));

        SimulationPanel.add(GraphicsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 630, 530, 250));

        SimulationDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        SimulationDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Simulation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        SimulationDetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PCBPanel.setBackground(new java.awt.Color(255, 255, 255));
        PCBPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Process Control Blocks", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 13))); // NOI18N
        PCBPanel.setLayout(new javax.swing.BoxLayout(PCBPanel, javax.swing.BoxLayout.LINE_AXIS));
        PCBPanel.add(jSeparator1);

        SimulationDetailsPanel.add(PCBPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 980, 250));

        DetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        DetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 13))); // NOI18N
        DetailsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProccesesPerProcessorsTable.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        ProccesesPerProcessorsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Processor 1", "Processor 2", "Processor 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ProccesesPerProcessorsTable);

        DetailsPanel.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 380, 70));

        ExecutionModeLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        ExecutionModeLabel.setForeground(new java.awt.Color(51, 204, 0));
        ExecutionModeLabel.setText("Operative System");
        DetailsPanel.add(ExecutionModeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 270, -1));

        ProcessesProLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        ProcessesProLabel.setText("Current Processes Per Processor:");
        DetailsPanel.add(ProcessesProLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 380, -1));

        GlobalClockLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        GlobalClockLabel.setText("Global Clock Cycle Number:");
        DetailsPanel.add(GlobalClockLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 200, -1));

        ModeLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        ModeLabel.setText("Execution Mode:");
        DetailsPanel.add(ModeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, -1));

        SimulationDetailsPanel.add(DetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 440, 280));

        QueuePanel.setBackground(new java.awt.Color(255, 255, 255));
        QueuePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Queue", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 13))); // NOI18N
        QueuePanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ReadyQueueLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        ReadyQueueLabel.setText("Ready");
        QueuePanel.add(ReadyQueueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 90, -1));

        BlockedQueueLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        BlockedQueueLabel.setText("Blocked");
        QueuePanel.add(BlockedQueueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 90, -1));

        FinishedQueueLabel.setFont(new java.awt.Font("Geeza Pro", 3, 12)); // NOI18N
        FinishedQueueLabel.setText("Finished");
        QueuePanel.add(FinishedQueueLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 90, -1));

        ReadyQueueList.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        ReadyQueueList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Proceso 1", "Proceso 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        ReadyQueueList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ReadyQueueList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ReadyQueueList.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        ReadyQueueList.setSelectionBackground(null);
        ReadyQueueList.setVerifyInputWhenFocusTarget(false);
        jScrollPane5.setViewportView(ReadyQueueList);

        QueuePanel.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 90, 170));

        FinishedQueueList.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        FinishedQueueList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Proceso 2", "Proceso 4" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        FinishedQueueList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        FinishedQueueList.setSelectionBackground(null);
        jScrollPane6.setViewportView(FinishedQueueList);

        QueuePanel.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 90, 170));

        BlockedQueueList.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        BlockedQueueList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Proceso 3" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(BlockedQueueList);

        QueuePanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 90, 170));

        SimulationDetailsPanel.add(QueuePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 460, 280));

        SimulationPanel.add(SimulationDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1040, 600));

        getContentPane().add(SimulationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 1070, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClearProcessTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearProcessTableButtonActionPerformed
        int response = JOptionPane.showConfirmDialog(
            null,
            "Are you sure you want to clear the processes?",
            "Yes",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.YES_OPTION) {
            simulator.getReadyQueue().clear(); 
            updateInterface(); 
            updateButtonStates();
            JOptionPane.showMessageDialog(null, "Process queue cleared successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Process clearing canceled."); 
        }
    }//GEN-LAST:event_ClearProcessTableButtonActionPerformed

    private void StartSimulationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartSimulationButtonActionPerformed
    if (CycleDurationTextField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Cycle Duration.", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    } else {

        PCBPanel.removeAll();
        
  
        ProcessQueue readyQueue = simulator.getReadyQueue();

   
        if (readyQueue.size() > 0) {
            for (int i = 0; i < readyQueue.size(); i++) {
                Process process = readyQueue.get(i);
                if (process != null) {
                    ProcessPCBPanel processPanel = new ProcessPCBPanel(process);
                    PCBPanel.add(processPanel); // Añade el panel al PCBPanel
                }
            }
        }

        
        PCBPanel.revalidate(); 
        PCBPanel.repaint();

   
        saveToFile();
        disableJPanel(ProcessDetailsPanel, false);
        disableJPanel(IOBoundPanel, false);
        ProcessTable.setEnabled(false);
        enablePanels(true);
        StartSimulationButton.setText("Stop Simulation");
        StartSimulationButton.setBackground(Color.RED);
        int cycleDuration = Integer.parseInt(CycleDurationTextField.getText());
        int numProcessors = TwoProcessorsOption.isSelected() ? 2 : 3;
        simulator = new Simulator(numProcessors);
        
        Timer timer = new Timer(cycleDuration * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulator.executeCycle();
                updateInterface(); 
            }
        });
        timer.start();
    }
    }//GEN-LAST:event_StartSimulationButtonActionPerformed

    private void IOBoundOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IOBoundOptionActionPerformed
        disableJPanel(IOBoundPanel,true);
    }//GEN-LAST:event_IOBoundOptionActionPerformed

    private void AddProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProcessButtonActionPerformed
       // Validar campos
        String message = "Attention: Please fill in all required fields."; 
        if (ProcessNameTextField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        String name = ProcessNameTextField.getText();
        if (simulator.getReadyQueue().containsName(name)) { 
            JOptionPane.showMessageDialog(null, "A process with that name already exists.", "Error", JOptionPane.WARNING_MESSAGE);
            return; 
        }

        boolean isCpuBound = CPUBoundOption.isSelected();
        int exceptionCycle = (int) CyclesGenerateExcepSpinner.getValue();
        int satisfactionCycle = (int) CyclesSatisfyExcepSpinner.getValue();

        System.out.println("Creating process:");
        System.out.println("Name: " + name);
        System.out.println("Type: " + (isCpuBound ? "CPU Bound" : "I/O Bound"));
        System.out.println("Cycles to generate an exception: " + exceptionCycle);
        System.out.println("Cycles to satisfy an exception: " + satisfactionCycle);

        // Crear el proceso
        Process process = new Process(simulator.getGlobalCycle(), name, 10, isCpuBound, exceptionCycle, satisfactionCycle);
    
        // Agregar el proceso al simulador
        simulator.addProcess(process);

        DefaultTableModel modelo = (DefaultTableModel) ProcessTable.getModel();
        modelo.addRow(new Object[]{process.getName(), isCpuBound ? "CPU Bound" : "I/O Bound"});
        JOptionPane.showMessageDialog(null, "Process Created!", "Success", JOptionPane.INFORMATION_MESSAGE);
        resetFields();
        updateButtonStates();
    }//GEN-LAST:event_AddProcessButtonActionPerformed

    public void updateInterface() {
        // Actualizar la tabla de procesos listos
        DefaultTableModel modeloListos = (DefaultTableModel) ProcessTable.getModel();
        modeloListos.setRowCount(0);
        for (int i = 0; i < simulator.getReadyQueue().size(); i++) {
            Process process = simulator.getReadyQueue().get(i);
            modeloListos.addRow(new Object[]{process.getName(), process.isCpuBound() ? "CPU Bound" : "I/O Bound"});
        }
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }
    private void DeleteProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProcessButtonActionPerformed
       Process selectedProcess = getSelectedProcess(); 
        if (selectedProcess != null) {
            if (simulator.getReadyQueue().remove(selectedProcess)) { 
                updateInterface();
                JOptionPane.showMessageDialog(null, "Process deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateButtonStates();
            } else {
                JOptionPane.showMessageDialog(null, "Process not found in the queue.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No process selected to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteProcessButtonActionPerformed

    private void CPUBoundOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPUBoundOptionActionPerformed
        disableJPanel(IOBoundPanel,false);
        resetSpinners();
    }//GEN-LAST:event_CPUBoundOptionActionPerformed

    private void ProcessNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProcessNameTextFieldKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetterOrDigit(c) && c != ' ') {
            evt.consume(); 
        }
    }//GEN-LAST:event_ProcessNameTextFieldKeyTyped

    private void CycleDurationTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CycleDurationTextFieldKeyTyped
        char c = evt.getKeyChar();
        if(c < '0' || c > '9') evt.consume();
    }//GEN-LAST:event_CycleDurationTextFieldKeyTyped
    
    
    //TXT CONFIG
    private void saveToFile() {
    String filename = "simulation_results.txt";

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        writer.write("#Datos de la simulación");
        writer.newLine();

        writer.write("#PROCESOS");
        writer.newLine();

        simulator.getReadyQueue().iterateProcesses(writer);

        writer.write("#CONFIGURACION");
        writer.newLine();

        writer.write("Cycle Duration: " + CycleDurationTextField.getText().trim());
        writer.newLine();
        writer.write("Number of Active Processors: " + 
                     (TwoProcessorsOption.isSelected() ? "2" : "3"));
        writer.newLine();
        writer.write("Planning Policy: " + PlanninPolicyComboBox.getSelectedItem());
        writer.newLine();

        JOptionPane.showMessageDialog(null, "Datos guardados en " + filename);
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al guardar los datos.");
    }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ActiveProcessorsGroup;
    private javax.swing.JLabel ActiveProcessorsTextField;
    private javax.swing.JButton AddProcessButton;
    private javax.swing.JLabel BlockedQueueLabel;
    private javax.swing.JList<String> BlockedQueueList;
    private javax.swing.JRadioButton CPUBoundOption;
    private javax.swing.JLabel CicleDurationLabel;
    private javax.swing.JButton ClearProcessTableButton;
    private javax.swing.JPanel ConfigPanel;
    private javax.swing.JTextField CycleDurationTextField;
    private javax.swing.JLabel CyclesGenerateExcepLabel;
    private javax.swing.JSpinner CyclesGenerateExcepSpinner;
    private javax.swing.JLabel CyclesSatisfyExcepLabel;
    private javax.swing.JSpinner CyclesSatisfyExcepSpinner;
    private javax.swing.JButton DeleteProcessButton;
    private javax.swing.JPanel DetailsPanel;
    private javax.swing.JLabel ExecutionModeLabel;
    private javax.swing.JLabel FinishedQueueLabel;
    private javax.swing.JList<String> FinishedQueueList;
    private javax.swing.JLabel GlobalClockLabel;
    private javax.swing.JPanel GraphicsPanel;
    private javax.swing.JRadioButton IOBoundOption;
    private javax.swing.JPanel IOBoundPanel;
    private javax.swing.JLabel ModeLabel;
    private javax.swing.JPanel PCBPanel;
    private javax.swing.JComboBox<String> PlanninPolicyComboBox;
    private javax.swing.JLabel PlanningPolicyLabel;
    private javax.swing.JTable ProccesesPerProcessorsTable;
    private javax.swing.JPanel ProcessDetailsPanel;
    private javax.swing.JLabel ProcessNameLabel;
    private javax.swing.JTextField ProcessNameTextField;
    private javax.swing.JTable ProcessTable;
    private javax.swing.ButtonGroup ProcessTypeGroup;
    private javax.swing.JLabel ProcessTypeLabel;
    private javax.swing.JLabel ProcessesProLabel;
    private javax.swing.JPanel QueuePanel;
    private javax.swing.JLabel ReadyQueueLabel;
    private javax.swing.JList<String> ReadyQueueList;
    private javax.swing.JPanel SimulationDetailsPanel;
    private javax.swing.JPanel SimulationPanel;
    private javax.swing.JButton StartSimulationButton;
    private javax.swing.JPanel SystemPerfomanceMetricsPanel;
    private javax.swing.JPanel SystemSpecificationsPanel;
    private javax.swing.JRadioButton ThreeProcessorsOption;
    private javax.swing.JComboBox<String> TimeUnitComboBox;
    private javax.swing.JRadioButton TwoProcessorsOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
