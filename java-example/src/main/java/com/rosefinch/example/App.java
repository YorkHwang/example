package com.rosefinch.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        final String orderId = "OD001f6d41c76-2175-4d4a-8f2f-e027e2ea9edf";
        System.out.println( orderId.hashCode() % 31);
        System.out.println( "Hello World!" );
    }
}
