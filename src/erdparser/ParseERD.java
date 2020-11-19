/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erdparser;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

///import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;

/**
 *
 * @author rnavarro
 */
public class ParseERD {

    public static void main(String[] args) throws MalformedURLException, IOException {

        FileReader fp = new FileReader("university-erd.json");

        
        JsonElement element;
        element = JsonParser.parseReader(fp);

       
        if (element.isJsonObject()) {
            JsonObject erd = element.getAsJsonObject();

            //System.out.println( erd );
           //System.out.println(erd.get("entidades"));
           
           
           System.out.println("**** Entidades en el diagram ****\n");

            JsonArray entidades = erd.getAsJsonArray("entidades");

            for (int i = 0; i < entidades.size(); i++) {
                JsonObject entidad = entidades.get(i).getAsJsonObject();
                System.out.print(entidad.get("nombre").getAsString());
                
                System.out.print("(");

                JsonArray atributos = entidad.getAsJsonArray("atributos");
                
                int attCount = atributos.size();
                for (int j = 0; j < attCount; j++) {
                    
                    JsonObject atributo = atributos.get(j).getAsJsonObject();
                    System.out.print(atributo.get("nombre").getAsString());
                    
                    int type = atributo.get("tipo").getAsInt();
                    
                    if( type == 1) {
                        System.out.print("*");
                    }
                    
                    if(j < attCount -1 ) {
                        System.out.print(",");
                    }
                }
                System.out.println(")");

            }
        }

    }

}
