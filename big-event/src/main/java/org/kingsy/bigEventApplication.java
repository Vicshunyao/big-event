package org.kingsy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class bigEventApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(bigEventApplication.class,args);

        System.out.println( "Hello World!" );
    }
}
