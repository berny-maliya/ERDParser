/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erdparser;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author 
 */
public class TableDemo extends JPanel{
    private boolean DEBUG = false;
    Object[][] out;
    
    public TableDemo(Object[][] out) {
        super(new GridLayout(1,0));
        this.out=out;
        MyTableModel my=new MyTableModel();
        JTable table = new JTable(my);
        
        TableColumn column=new TableColumn();
        column=table.getColumnModel().getColumn(1);
        JComboBox combo=new JComboBox();
        
        combo.addItem("Texto");
        combo.addItem("Numero");
        combo.addItem("Fecha");
        
        column.setCellEditor(new DefaultCellEditor(combo));
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    class MyTableModel extends AbstractTableModel{
        String[] columnNames = {"Nombre", "Tipo", "Longitud", "No Nulo", "Clave Primaria", "Descripción"};
         public int getColumnCount() {
            return this.columnNames.length;
        }

        public int getRowCount() {
            return out.length;
        }

        public String getColumnName(int col) {
            return this.columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return out[row][col];
        }
         /*
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }*/
        @Override
    public Class<?> getColumnClass(int c){
        Class clazz=String.class;
        switch(c){
            case 0:
                clazz=String.class;
                break;
            case 1:
                clazz=String.class;
                break;
            case 2:
                clazz=Integer.class;
                break;
            case 3:
                clazz=Boolean.class;
                break;
            case 4:
                clazz=Boolean.class;
                break;
            case 5:
                clazz=String.class;
                break;
            default:
                break;
        }
        return clazz;
    }
        
        
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 1) {
                return false;
            } else {
                return true;
            }
        }
        
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            out[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + out[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
    private static void createAndShowGUI(Object[][] salida,String name) {
        //Create and set up the window.
        JFrame frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TableDemo newContentPane = new TableDemo(salida);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.setSize(500, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void main(Object[][] out,String name) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(out,name);
            }
        });
    }
}
