package sap;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.ext.DestinationDataProvider;
import com.sap.conn.jco.ext.ServerDataProvider;
import com.sap.conn.jco.server.DefaultServerHandlerFactory;
import com.sap.conn.jco.server.JCoServer;
import com.sap.conn.jco.server.JCoServerContext;
import com.sap.conn.jco.server.JCoServerFactory;
import com.sap.conn.jco.server.JCoServerFunctionHandler;
import com.sap.conn.jco.server.JCoServerTIDHandler;

public class ServiceInitMain
{
    static String SERVER_NAME1 = "jcoServer";
    static String DESTINATION_NAME1 = "ABAP_AS_WITHOUT_POOL";
    static String DESTINATION_NAME2 = "ABAP_AS_WITH_POOL";
    static MyTIDHandler myTIDHandler = null;
    
    static Log log=LogFactory.getLog(ServiceInitMain.class);

    static
    {
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.130.251");
        //TODO 实际部署时放开正式ip
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.168.130.2");
//        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "889");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, "barcode");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "barcode");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "");
        createDataFile(DESTINATION_NAME1, "jcoDestination", connectProperties);
        
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3");
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");
        createDataFile(DESTINATION_NAME2, "jcoDestination", connectProperties);
        
        Properties servertProperties = new Properties();
//        servertProperties.setProperty(ServerDataProvider.JCO_GWHOST, "192.168.130.251");
        servertProperties.setProperty(ServerDataProvider.JCO_GWHOST, "192.168.130.2");
        servertProperties.setProperty(ServerDataProvider.JCO_GWSERV, "sapgw00");
        servertProperties.setProperty(ServerDataProvider.JCO_PROGID, "NETSERVER01");
//        servertProperties.setProperty(ServerDataProvider.JCO_PROGID, "NETSERVER02");
        servertProperties.setProperty(ServerDataProvider.JCO_REP_DEST, "ABAP_AS_WITHOUT_POOL");
        servertProperties.setProperty(ServerDataProvider.JCO_CONNECTION_COUNT, "2");
        createDataFile(SERVER_NAME1, "jcoServer", servertProperties);
    }
    
    /**
     * This function is only used to create the destination and server configuration at first run
     * (if the files do not exist in the working directory). Once the files are created you
     * may modify them using a text editor.
     */
    static void createDataFile(String name, String suffix, Properties properties)
    {
        File cfg = new File(name + "." + suffix);
        if(!cfg.exists())
        {
            try
            {
                FileOutputStream fos = new FileOutputStream(cfg, false);
                properties.store(fos, "for tests only !");
                fos.close();
            }
            catch(Exception e)
            {
                throw new RuntimeException("Unable to create the destination file " + cfg.getName(), e);
            }
        }
    }

    /**
     * This class provides the implementation for the function STFC_CONNECTION. You will
     * find the RFC enabled function STFC_CONNECTION in almost each ABAP system. The
     * function is pretty easy - it has 1 input parameter and 2 output parameter. The content
     * of the input parameter REQUTEXT is copied to the output parameter ECHOTEXT. The 
     * output parameter RESPTEXT is set to "Hello World". 
     */
    static class StfcConnectionHandler implements JCoServerFunctionHandler
    {
        public void handleRequest(JCoServerContext serverCtx, JCoFunction function)
        {
        	log.debug("handleRequest(" + function.getName() + ")");
    		SapBusiService service = new SapBusiService();
    		try {
    			service.businessHandler(function);
    		} catch (Exception e) {
    			Logger.getLogger(this.getClass().getName()).log(Level.WARNING, e.getMessage());
    			e.printStackTrace();
    			log.error("handleRequest方法错误:",e);
    		}
    		
            if(myTIDHandler != null)
                myTIDHandler.execute(serverCtx);
        }
    }
    
    /**
	 * Start the server
	 */
	public static void startServers() {
        JCoServer server;
        try
        {
            server = JCoServerFactory.getServer(SERVER_NAME1);
        }
        catch(JCoException ex)
        {
            throw new RuntimeException("Unable to create the server " + SERVER_NAME1 + ", because of " + ex.getMessage(), ex);
        }
        
        JCoServerFunctionHandler stfcConnectionHandler = new StfcConnectionHandler();
        DefaultServerHandlerFactory.FunctionHandlerFactory factory = new DefaultServerHandlerFactory.FunctionHandlerFactory();
        factory.registerHandler("ZSTFC_CONNECTION_RFID_01", stfcConnectionHandler);//排序接口 
        factory.registerHandler("ZSTFC_CONNECTION_RFID_02", stfcConnectionHandler);//排产接口 
        factory.registerHandler("ZSTFC_CONNECTION_RFID_03", stfcConnectionHandler);//批量领料
        factory.registerHandler("ZSTFC_CONNECTION_RFID_04", stfcConnectionHandler);//变更接口 
        factory.registerHandler("ZSTFC_CONNECTION_RFID_05", stfcConnectionHandler);//领料回传接口 
        factory.registerHandler("ZSTFC_CONNECTION_RFID_06", stfcConnectionHandler);//退料接口 
        
        factory.registerHandler("ZRFC_RFIDAUFNR_DATA", stfcConnectionHandler);//订单信息回传
        
        server.setCallHandlerFactory(factory);
        
        server.start();
       log.debug("The SapService has start...");
    }
    
    
    static class MyTIDHandler implements JCoServerTIDHandler
    {
        
        Map<String, TIDState> availableTIDs = new Hashtable<String, TIDState>();
        
        public boolean checkTID(JCoServerContext serverCtx, String tid)
        {
            // This example uses a Hashtable to store status information. But usually
            // you would use a database. If the DB is down, throw a RuntimeException at
            // this point. JCo will then abort the tRFC and the R/3 backend will try
            // again later.
            
            System.out.println("TID Handler: checkTID for " + tid);
            TIDState state = availableTIDs.get(tid);
            if(state == null)
            {
                availableTIDs.put(tid, TIDState.CREATED);
                return true;
            }
            
            if(state == TIDState.CREATED || state == TIDState.ROLLED_BACK)
                return true;

            return false;
            // "true" means that JCo will now execute the transaction, "false" means
            // that we have already executed this transaction previously, so JCo will
            // skip the handleRequest() step and will immediately return an OK code to R/3.
        }
        
        public void commit(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: commit for " + tid);
            
            // react on commit e.g. commit on the database
            // if necessary throw a RuntimeException, if the commit was not
            // possible
            availableTIDs.put(tid, TIDState.COMMITTED);
        }
        
        public void rollback(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: rollback for " + tid);
            availableTIDs.put(tid, TIDState.ROLLED_BACK);
            
            // react on rollback e.g. rollback on the database
        }
        
        public void confirmTID(JCoServerContext serverCtx, String tid)
        {
            System.out.println("TID Handler: confirmTID for " + tid);
            
            try
            {
                // clean up the resources
            }
            // catch(Throwable t) {} //partner wont react on an exception at
            // this point
            finally
            {
                availableTIDs.remove(tid);
            }
        }
        
        public void execute(JCoServerContext serverCtx)
        {
            String tid = serverCtx.getTID();
            if(tid != null)
            {
                System.out.println("TID Handler: execute for " + tid);
                availableTIDs.put(tid, TIDState.EXECUTED);
            }
        }
        
        private enum TIDState
        {
            CREATED, EXECUTED, COMMITTED, ROLLED_BACK, CONFIRMED;
        }
    }
    
    
    
    

    public static void main(String[] a)
    {
    	startServers();
    }
}
