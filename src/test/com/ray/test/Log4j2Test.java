package com.ray.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ray
 * @date 2018/5/29 0029
 */
public class Log4j2Test extends BaseJunit4Test {

    private Logger logger = LoggerFactory.getLogger(Log4j2Test.class);

    @Test
    public void logTest(){
        logger.error("error");
        logger.debug("debug");
        logger.info("info");
        logger.trace("trace");
        logger.warn("warn");
        logger.error("error {}", "param");
    }
}
