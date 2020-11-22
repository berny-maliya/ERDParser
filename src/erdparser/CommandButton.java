/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erdparser;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author dell
 */
public class CommandButton {

    JTable table;
    String name;

    CommandButton(JTable table, String name) {
        this.table = table;
        this.name = name;
    }

    public String sentencia(int length) {
        String sql = null;
        int count=0;
        sql = "CREATE TABLE IF NOT EXISTS " + name + "(\n\t";
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 5; j++) {
                
                switch(j){
                    case 0:
                        char[] letras = table.getValueAt(i, j).toString().toCharArray();
                    int compare = letras.length - 1;

                    if (letras[compare] == '*') {
                        String cell = nombrecampo(letras);
                        sql = sql + cell + " ";
                    } else {
                        sql = sql + table.getValueAt(i, j) + " ";
                    }
                    
                        break;
                    case 1:
                        String coll = table.getValueAt(i, j).toString();
                    //System.out.println(coll);
                    switch (coll) {
                        case "Texto":
                            sql = sql + "VARCHAR(";
                            break;
                        case "Numero":
                            sql = sql + "NUMERIC(";
                            break;
                        case "Fecha":
                            sql = sql + "timestamp";
                            break;
                    }
                        break;
                    case 2:
                        String cell = table.getValueAt(i, j).toString();
                    if (table.getValueAt(i, 1).equals("Fecha") == false) {
                        sql = sql + cell + ") ";
                    } else {
                        sql = sql + ",\n\t";
                    }
                        break;
                    case 3:
                        Boolean nui = (Boolean) table.getValueAt(i, j);
                    if (i == length - 1) {
                        if (nui == Boolean.TRUE) {
                            sql = sql + "NOT NULL";
                        } else {
                            //sql = sql + ",\n\t";
                        }
                    } else {
                        if (nui == Boolean.TRUE) {
                            sql = sql + "NOT NULL,\n\t";
                        } else if(table.getValueAt(i, 1).equals("Fecha")==false){
                            sql = sql + ",\n\t";
                        }
                    }
                        break;
                    case 4:
                         Boolean nu = (Boolean) table.getValueAt(i, j);
                    if (nu == Boolean.TRUE) {
                        sql = sql + "";
                        count++;
                    }
                        break;
                }
            }
        }
        sql = sql + "\n);";
        System.out.println(count);
        return sql;
    }

    String nombrecampo(char[] tokens) {
        String n = "";
        for (int i = 0; i < tokens.length - 1; i++) {
            n = n + tokens[i];
        }
        return n;
    }
}
