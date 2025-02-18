/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import gui.Panel01;
import gui.Panel02;
import gui.Panel03;
import gui.Panel04;
import gui.Panel05;
import gui.Panel06;
import gui.Panel07;
import gui.Panel08;
import gui.Panel09;
import gui.Panel10;

import java.awt.CardLayout;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import service.ControladorView;

/**
 *
 * @author matheus.sampaio
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("PROJETO SISTEMAS DISTRIBUIDOS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1010, 840);
        frame.setLocationRelativeTo(null);
        
        
        JPanel cardPanel = new JPanel(new CardLayout());
        ControladorView controller = new ControladorView(cardPanel);

        
        Panel01 panel01 = new Panel01(controller);
        Panel02 panel02 = new Panel02(controller);
        Panel03 panel03 = new Panel03(controller);
        Panel04 panel04 = new Panel04(controller);
        Panel05 panel05 = new Panel05(controller);
        Panel06 panel06 = new Panel06(controller);
        Panel07 panel07 = new Panel07(controller);
        Panel08 panel08 = new Panel08(controller);
        Panel09 panel09 = new Panel09(controller);
        Panel10 panel10 = new Panel10(controller);
        
        
        cardPanel.add(panel01, "Panel01");
        cardPanel.add(panel02, "Panel02");
        cardPanel.add(panel03, "Panel03");
        cardPanel.add(panel04, "Panel04");
        cardPanel.add(panel05, "Panel05");
        cardPanel.add(panel06, "Panel06");
        cardPanel.add(panel07, "Panel07");
        cardPanel.add(panel08, "Panel08");
        cardPanel.add(panel09, "Panel09");
        cardPanel.add(panel10, "Panel10");

        frame.add(cardPanel);
        frame.setVisible(true);
    }
}
