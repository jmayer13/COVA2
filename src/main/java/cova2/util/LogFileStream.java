package cova2.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.time.Instant;

/**
 * Manage strean to write on log file
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class LogFileStream {

    //volatile = variable's value will be modified by different threads
    private static volatile LogFileStream _logFileStream;
    private PrintWriter printWriter;

    /**
     * Private constructor to avoid problem with cuncurrent access to stream
     *
     * @throws FileNotFoundException
     */
    private LogFileStream() throws FileNotFoundException {
        createPrintWriter();
    }//end of the constructor 

    /**
     * Create/Get instance of the class
     *
     * @return <code>LogFileStream</code>
     */
    public static LogFileStream getLogFileStream() {
        if (_logFileStream == null) {
            synchronized (LogFileStream.class) {
                try {
                    Constructor constructors[] = LogFileStream.class.getDeclaredConstructors();
                    constructors[0].setAccessible(true);
                    _logFileStream = (LogFileStream) constructors[0].newInstance();
                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        }
        return _logFileStream;
    }//end of the method LogFileStream

    /**
     * Close PrintWriter
     */
    public void close() {
        printWriter.close();
        printWriter = null;
        _logFileStream = null;
    }//end of the method close

    /**
     * Get PrintWriter to write in the log
     *
     * @return <code>PrintWriter</code>
     */
    public PrintWriter getPrintWriter() {
        return printWriter;
    }//end of the method getPrintWriter

    /**
     * Create PrintWriter
     *
     * @throws FileNotFoundException
     */
    private void createPrintWriter() throws FileNotFoundException {
        File directory = new File("log" + File.separator + "err");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Instant timestamp = Instant.now();
        File log = new File(directory, timestamp.toString());
        printWriter = new PrintWriter(log);
    }//end of the method createPrintWriter

}//end of the class LogFileStream 
