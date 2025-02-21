package igu;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author beacardozo
 */
public class SystemPerformanceMetricsChart extends javax.swing.JFrame {
    
    private DefaultCategoryDataset dataset;
    /**
     * Creates new form SystemPerformanceMetricsChart
     */
    public SystemPerformanceMetricsChart(String title) {
        super(title);
        dataset = new DefaultCategoryDataset(); 
        JFreeChart chart = ChartFactory.createBarChart(
                "System Stadistics", 
                "Metrics", 
                "Values", 
                dataset,
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );
        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800, 600));
        setContentPane(panel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 
    }

    public void updateDataset(Double value, String metric, String category) {
        dataset.addValue(value, metric, category);
    }

    public void clearDataset() {
        dataset.clear(); 
    }

    public void loadNewDataset(Object[][] values) {
        clearDataset(); 
        for (Object[] value : values) {
            if (value.length >= 3) {
                Double numericValue = null;
                if (value[0] instanceof Long) {
                    numericValue = ((Long) value[0]).doubleValue();
                } else if (value[0] instanceof Double) {
                    numericValue = (Double) value[0]; 
                } else {
                    continue; 
                }
                updateDataset(numericValue, (String) value[1], (String) value[2]);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SystemPerformanceMetricsChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SystemPerformanceMetricsChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SystemPerformanceMetricsChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SystemPerformanceMetricsChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
SwingUtilities.invokeLater(() -> {
            SystemPerformanceMetricsChart example = new SystemPerformanceMetricsChart("Desarrollo del Sistema");
            example.setSize(800, 600);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
