/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.examengpj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;

/**
 *
 * @author Gonzalo
 */
public class AppDiccionario {

    public static void main(String[] args) {

        try {
            Connection con = DictionaryConnection.getInstance().getConnection();

            WordDAO wordDAO = new WordDAO(con);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.println("Introduce palabra: ");
                String input = br.readLine();
                if (input.isEmpty()) {
                    break;
                }

                Word palabra = wordDAO.get(input);
                if (palabra != null) {
                    palabra.toString();
                } else {
                    System.out.println("Non existe a palabra.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error en app " + e.getMessage());
        }

    }
}
