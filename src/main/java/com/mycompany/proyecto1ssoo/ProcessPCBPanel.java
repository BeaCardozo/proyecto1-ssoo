/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1ssoo;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author beacardozo
 */



public class ProcessPCBPanel extends JPanel {
    public ProcessPCBPanel(Process process) {
        // Configurar el borde del panel con un título
        setBorder(BorderFactory.createEtchedBorder());
        setBorder(BorderFactory.createTitledBorder(process.getName()));
        
        // Establecer tamaño preferido
        setPreferredSize(new Dimension(200, 150)); // Cambia estos valores según tus necesidades

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Espaciado y alineación
        gbc.insets = new Insets(5, 5, 5, 5); // Espacio alrededor de los componentes
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        add(new JLabel(String.valueOf(process.getId())), gbc);
        
        // Agrega aquí otros componentes según sea necesario
    }
}


  /*gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Values:"), gbc);

        // Crear un área de texto para los valores
        JTextArea valuesArea = new JTextArea(5, 15);
        valuesArea.setLineWrap(true);
        valuesArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(valuesArea);*/
        
        // Añadir el área de texto para valores
       /* gbc.gridx = 1;
        gbc.gridwidth = 1; 
        gbc.fill = GridBagConstraints.BOTH; // Permitir que ocupe todo el espacio disponible
        add(scrollPane, gbc);*/
