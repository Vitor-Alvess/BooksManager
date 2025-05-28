/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.vitorAlves;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vitor-alves
 */
public class LoggingTest {
    private static final Logger log = LoggerFactory.getLogger(LoggingTest.class);
    
    @Test
    void testLogs(){
        log.info("info");
        log.error("error");
        log.warn("warn");
        log.debug("debug");
    }
}
