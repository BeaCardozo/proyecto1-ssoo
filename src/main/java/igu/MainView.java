package igu;

import javax.swing.JFrame;

/**
 *
 * @author beacardozo
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form PantallaPrincipal
     */
    public MainView() {
        // Inicializa la configuración de la ventana
        initComponents();
        IOBoundPanel.setVisible(false);
        setTitle("Process Simulator");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        SystemSpecificationsPanel = new javax.swing.JPanel();
        PlanningPolicyLabel = new javax.swing.JLabel();
        msLabel = new javax.swing.JLabel();
        CicleDurationLabel = new javax.swing.JLabel();
        ActiveProcessorsTextField = new javax.swing.JLabel();
        CicleDurationTextField1 = new javax.swing.JTextField();
        PlanninPolicyComboBox = new javax.swing.JComboBox<>();
        TwoProcessorsOption = new javax.swing.JRadioButton();
        ThreeProcessorsOption = new javax.swing.JRadioButton();
        StartSimulationButton = new javax.swing.JButton();
        SimulationPanel = new javax.swing.JPanel();
        SystemPerfomanceMetricsPanel = new javax.swing.JPanel();
        GraphicsPanel = new javax.swing.JPanel();
        SimulationDetailsPanel = new javax.swing.JPanel();

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

        ProcessTable.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        ProcessTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Name", "Type"
            }
        ));
        ProcessTable.setSelectionBackground(new java.awt.Color(169, 217, 241));
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
        ProcessNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProcessNameTextFieldActionPerformed(evt);
            }
        });
        ProcessDetailsPanel.add(ProcessNameTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 250, -1));

        IOBoundPanel.setBackground(new java.awt.Color(255, 255, 255));
        IOBoundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        jLabel1.setText("Cycles to satisfy an exception:");
        IOBoundPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel2.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        jLabel2.setText("Cycles to generate an exception:");
        IOBoundPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        IOBoundPanel.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, -1));
        IOBoundPanel.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        ProcessDetailsPanel.add(IOBoundPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 260, 120));

        ConfigPanel.add(ProcessDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 280, 550));

        SystemSpecificationsPanel.setBackground(new java.awt.Color(255, 255, 255));
        SystemSpecificationsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "System Specifications", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        SystemSpecificationsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PlanningPolicyLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        PlanningPolicyLabel.setText("Planning Policy:");
        SystemSpecificationsPanel.add(PlanningPolicyLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 210, -1));

        msLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        msLabel.setText("ms.");
        SystemSpecificationsPanel.add(msLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 50, -1, -1));

        CicleDurationLabel.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        CicleDurationLabel.setText("Cycle Duration:");
        SystemSpecificationsPanel.add(CicleDurationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 230, -1));

        ActiveProcessorsTextField.setFont(new java.awt.Font("Geneva", 1, 13)); // NOI18N
        ActiveProcessorsTextField.setText("Number of Active Processors:");
        SystemSpecificationsPanel.add(ActiveProcessorsTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 200, -1));

        CicleDurationTextField1.setFont(new java.awt.Font("Geneva", 0, 13)); // NOI18N
        CicleDurationTextField1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CicleDurationTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CicleDurationTextField1ActionPerformed(evt);
            }
        });
        SystemSpecificationsPanel.add(CicleDurationTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 220, -1));

        PlanninPolicyComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        PlanninPolicyComboBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        SystemSpecificationsPanel.add(PlanninPolicyComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 250, -1));

        ActiveProcessorsGroup.add(TwoProcessorsOption);
        TwoProcessorsOption.setText("2");
        SystemSpecificationsPanel.add(TwoProcessorsOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        ActiveProcessorsGroup.add(ThreeProcessorsOption);
        ThreeProcessorsOption.setText("3");
        SystemSpecificationsPanel.add(ThreeProcessorsOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

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
        SimulationPanel.add(SystemPerfomanceMetricsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 490, 300));

        GraphicsPanel.setBackground(new java.awt.Color(255, 255, 255));
        GraphicsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Graphics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        GraphicsPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        SimulationPanel.add(GraphicsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 590, 540, 300));

        SimulationDetailsPanel.setBackground(new java.awt.Color(255, 255, 255));
        SimulationDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Simulation", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Geeza Pro", 3, 14))); // NOI18N
        SimulationPanel.add(SimulationDetailsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 1040, 560));

        getContentPane().add(SimulationPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 1070, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ClearProcessTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearProcessTableButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClearProcessTableButtonActionPerformed

    private void StartSimulationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartSimulationButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_StartSimulationButtonActionPerformed

    private void IOBoundOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IOBoundOptionActionPerformed
        if (IOBoundOption.isSelected()) {
        IOBoundPanel.setVisible(true);  // Muestra el panel
        }
    }//GEN-LAST:event_IOBoundOptionActionPerformed

    private void AddProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddProcessButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddProcessButtonActionPerformed

    private void DeleteProcessButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteProcessButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeleteProcessButtonActionPerformed

    private void ProcessNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProcessNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProcessNameTextFieldActionPerformed

    private void CicleDurationTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CicleDurationTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CicleDurationTextField1ActionPerformed

    private void CPUBoundOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CPUBoundOptionActionPerformed
        if (CPUBoundOption.isSelected()) {
        IOBoundPanel.setVisible(false);  // Muestra el panel
        }      
    }//GEN-LAST:event_CPUBoundOptionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup ActiveProcessorsGroup;
    private javax.swing.JLabel ActiveProcessorsTextField;
    private javax.swing.JButton AddProcessButton;
    private javax.swing.JRadioButton CPUBoundOption;
    private javax.swing.JLabel CicleDurationLabel;
    private javax.swing.JTextField CicleDurationTextField1;
    private javax.swing.JButton ClearProcessTableButton;
    private javax.swing.JPanel ConfigPanel;
    private javax.swing.JButton DeleteProcessButton;
    private javax.swing.JPanel GraphicsPanel;
    private javax.swing.JRadioButton IOBoundOption;
    private javax.swing.JPanel IOBoundPanel;
    private javax.swing.JComboBox<String> PlanninPolicyComboBox;
    private javax.swing.JLabel PlanningPolicyLabel;
    private javax.swing.JPanel ProcessDetailsPanel;
    private javax.swing.JLabel ProcessNameLabel;
    private javax.swing.JTextField ProcessNameTextField;
    private javax.swing.JTable ProcessTable;
    private javax.swing.ButtonGroup ProcessTypeGroup;
    private javax.swing.JLabel ProcessTypeLabel;
    private javax.swing.JPanel SimulationDetailsPanel;
    private javax.swing.JPanel SimulationPanel;
    private javax.swing.JButton StartSimulationButton;
    private javax.swing.JPanel SystemPerfomanceMetricsPanel;
    private javax.swing.JPanel SystemSpecificationsPanel;
    private javax.swing.JRadioButton ThreeProcessorsOption;
    private javax.swing.JRadioButton TwoProcessorsOption;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JLabel msLabel;
    // End of variables declaration//GEN-END:variables
}
