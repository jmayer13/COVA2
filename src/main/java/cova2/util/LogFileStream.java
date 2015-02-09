package cova2.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.swing.JOptionPane;

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
                } catch (SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, exception.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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
        if (printWriter == null) {
            try {
                createPrintWriter();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return printWriter;
    }//end of the method getPrintWriter

    /**
     * Create PrintWriter
     *
     * @throws FileNotFoundException
     */
    private void createPrintWriter() throws FileNotFoundException {
        File directory = new File("log");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        Instant timestamp = Instant.now();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault());
        String dateTitle = localDateTime.getDayOfMonth() + "_" + localDateTime.getMonth().name() + "_" + localDateTime.getYear() + "_" + localDateTime.getHour() + "_" + localDateTime.getMinute();
        File log = new File(directory, dateTitle);
        printWriter = new PrintWriter(log);
    }//end of the method createPrintWriter

}//end of the class LogFileStream 
