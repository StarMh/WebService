

/**
 * MyService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */

    package cn.wangtao34.webservice.server;

    /*
     *  MyService java interface
     */

    public interface MyService {
          

        /**
          * Auto generated method signature
          * 
                    * @param sayHello0
                
         */

         
                     public cn.wangtao34.webservice.server.SayHelloResponse sayHello(

                        cn.wangtao34.webservice.server.SayHello sayHello0)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param sayHello0
            
          */
        public void startsayHello(

            cn.wangtao34.webservice.server.SayHello sayHello0,

            final cn.wangtao34.webservice.server.MyServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        /**
          * Auto generated method signature
          * 
                    * @param add2
                
         */

         
                     public cn.wangtao34.webservice.server.AddResponse add(

                        cn.wangtao34.webservice.server.Add add2)
                        throws java.rmi.RemoteException
             ;

        
         /**
            * Auto generated method signature for Asynchronous Invocations
            * 
                * @param add2
            
          */
        public void startadd(

            cn.wangtao34.webservice.server.Add add2,

            final cn.wangtao34.webservice.server.MyServiceCallbackHandler callback)

            throws java.rmi.RemoteException;

     

        
       //
       }
    