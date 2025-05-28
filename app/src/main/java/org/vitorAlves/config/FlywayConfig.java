/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vitorAlves.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.flywaydb.core.Flyway;

/**
 *
 * @author vitor-alves
 */
public class FlywayConfig {
    static {
        Dotenv dotenv = Dotenv.load();
        Flyway flyway = Flyway.configure()
                .dataSource(dotenv.get("DB_URL"), dotenv.get("DB_USERNAME"), dotenv.get("DB_PASSWORD"))
                .locations("classpath:db/migrations")
                .load();
        
        flyway.migrate();
    }
    
    public static void init(){}; 
}
