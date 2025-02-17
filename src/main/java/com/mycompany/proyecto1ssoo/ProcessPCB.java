package com.mycompany.proyecto1ssoo;

/**
 *
 * @author beacardozo
 */
public class ProcessPCB extends javax.swing.JPanel {

    /**
     * Creates new form ProcessPCB
     */
    public ProcessPCB() {
        initComponents();
    }

    public ProcessPCB(Process process) {
        initComponents(); 
        ProcessIDLabel.setText(String.valueOf(process.getId()));
        ProcessNameLabel.setText(process.getName());
        ProcessStatusLabel.setText(process.getState());
        ProcessTypeLabel.setText(process.isCpuBound() ? "CPU BOUND" : "I/O BOUND");
        ProcessPCLabel.setText(String.valueOf(process.getProgramCounter()));
        ProcessMARLabel.setText(String.valueOf(process.getMAR()));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ProcessPCBSquare = new javax.swing.JPanel();
        ProcessNameLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ProcessIDLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ProcessStatusLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ProcessTypeLabel = new javax.swing.JLabel();
        ProcessPCLabel = new javax.swing.JLabel();
        ProcessMARLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProcessPCBSquare.setBackground(new java.awt.Color(255, 255, 255));
        ProcessPCBSquare.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        ProcessPCBSquare.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ProcessNameLabel.setFont(new java.awt.Font("Geeza Pro", 3, 14)); // NOI18N
        ProcessNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ProcessNameLabel.setText("PROCESO");
        ProcessNameLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        ProcessPCBSquare.add(ProcessNameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 30));

        jLabel1.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        jLabel1.setText("ID:");
        ProcessPCBSquare.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        ProcessIDLabel.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        ProcessIDLabel.setText("id");
        ProcessPCBSquare.add(ProcessIDLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 110, -1));

        jLabel2.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        jLabel2.setText("STATUS: ");
        ProcessPCBSquare.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        ProcessStatusLabel.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        ProcessStatusLabel.setText("status");
        ProcessPCBSquare.add(ProcessStatusLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 80, -1));

        jLabel3.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        jLabel3.setText("PC:");
        ProcessPCBSquare.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel4.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        jLabel4.setText("TYPE:");
        ProcessPCBSquare.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jLabel5.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        jLabel5.setText("MAR:");
        ProcessPCBSquare.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        ProcessPCBSquare.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 180, 10));

        ProcessTypeLabel.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        ProcessTypeLabel.setText("type");
        ProcessPCBSquare.add(ProcessTypeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 100, -1));

        ProcessPCLabel.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        ProcessPCLabel.setText("pc");
        ProcessPCBSquare.add(ProcessPCLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 110, -1));

        ProcessMARLabel.setFont(new java.awt.Font("Geneva", 3, 12)); // NOI18N
        ProcessMARLabel.setText("mar");
        ProcessPCBSquare.add(ProcessMARLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 110, -1));

        add(ProcessPCBSquare, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 150));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ProcessIDLabel;
    private javax.swing.JLabel ProcessMARLabel;
    private javax.swing.JLabel ProcessNameLabel;
    private javax.swing.JPanel ProcessPCBSquare;
    private javax.swing.JLabel ProcessPCLabel;
    private javax.swing.JLabel ProcessStatusLabel;
    private javax.swing.JLabel ProcessTypeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

public void updateStatus(String status) {
        ProcessStatusLabel.setText("STATUS: " + status);
    }
}


