package org.test.celue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
@SpringBootApplication(scanBasePackages = {"org.test.celue"})
public class RunApplication {

    public static void main(String[] args){
        SpringApplication.run(RunApplication.class,args);
    }
}
