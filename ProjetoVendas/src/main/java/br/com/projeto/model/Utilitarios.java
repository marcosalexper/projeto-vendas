/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.model;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Marcos Alexandre Pereira
 */
public class Utilitarios {
    
//Metodo limparCampos  
public void LimpaTela(JPanel container){
    Component components[] = container.getComponents();
    for(Component component : components){
        if(component instanceof JTextField){
            ((JTextField) component).setText(null);
        }
    }
}    
    
    
}
