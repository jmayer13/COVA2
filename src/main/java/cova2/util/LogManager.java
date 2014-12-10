package cova2.util;

import java.io.PrintWriter;
import java.time.Instant;

/**
 * Manage logging
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class LogManager {

    /**
     * Trace level - What was done exatly in details
     *
     * @param className
     * @param message
     */
    public void trace(String className, String message) {
        Instant timestamp = Instant.now();
        String line = "TRACE: " + (timestamp) + ": " + className + " - " + message;
        addLine(line);
    }//end of the method trace

    /**
     * Debug level - useful to debug an application
     *
     * @param className
     * @param message
     */
    public void debug(String className, String message) {
        Instant timestamp = Instant.now();
        String line = "DEBUG: " + (timestamp) + ": " + className + " - " + message;
        addLine(line);
    }//end of the method debug

    /**
     * Info level - highlight the progress of the application
     *
     * @param className
     * @param message
     */
    public void info(String className, String message) {
        Instant timestamp = Instant.now();
        String line = "INFO: " + (timestamp) + ": " + className + " - " + message;
        addLine(line);
    }//end of the method info

    /**
     * Warn level - potentially harmful situations
     *
     * @param className
     * @param message
     */
    public void warn(String className, String message) {
        Instant timestamp = Instant.now();
        String line = "WARN: " + (timestamp) + ": " + className + " - " + message;
        addLine(line);
    }//end of the method warn

    /**
     * Error level - error events that might still allow the application to
     * continue running
     *
     * @param className
     * @param message
     */
    public void error(String className, String message) {
        Instant timestamp = Instant.now();
        String line = "ERROR: " + (timestamp) + ": " + className + " - " + message;
        addLine(line);
    }//end of the method error

    /**
     * Fatal level - very severe error events that will presumably lead the
     * application to abort
     *
     * @param className
     * @param message
     */
    public void fatal(String className, String message) {
        Instant timestamp = Instant.now();
        String line = "FATAL: " + (timestamp) + ": " + className + " - " + message;
        addLine(line);
    }//end of the method fatal

    /**
     * Trace level - What was done exatly in details
     *
     * @param className
     * @param message
     * @param exception
     */
    public void trace(String className, String message, Exception exception) {
        trace(className, message);
        setPrintStackTrace(exception);
    }//end of the method trace

    /**
     * Debug level - useful to debug an application
     *
     * @param className
     * @param message
     * @param exception
     */
    public void debug(String className, String message, Exception exception) {
        debug(className, message);
        setPrintStackTrace(exception);
    }//end of the method debug

    /**
     * Info level - highlight the progress of the application
     *
     * @param className
     * @param message
     * @param exception
     */
    public void info(String className, String message, Exception exception) {
        info(className, message);
        setPrintStackTrace(exception);
    }//end of the method info

    /**
     * Warn level - potentially harmful situations
     *
     * @param className
     * @param message
     * @param exception
     */
    public void warn(String className, String message, Exception exception) {
        warn(className, message);
        setPrintStackTrace(exception);
    }//end of the method warn

    /**
     * Error level - error events that might still allow the application to
     * continue running
     *
     * @param className
     * @param message
     * @param exception
     */
    public void error(String className, String message, Exception exception) {
        error(className, message);
        setPrintStackTrace(exception);
    }//end of the method error

    /**
     * Fatal level - very severe error events that will presumably lead the
     * application to abort
     *
     * @param className
     * @param message
     * @param exception
     */
    public void fatal(String className, String message, Exception exception) {
        fatal(className, message);
        setPrintStackTrace(exception);
    }//end of the method fatal

    /**
     * Add line in log
     *
     * @param line
     */
    private void addLine(String line) {
        LogFileStream logFileStream = LogFileStream.getLogFileStream();
        PrintWriter printWriter = logFileStream.getPrintWriter();
        printWriter.println(line);
        logFileStream.close();
    }//end of the method addLine

    /**
     * Save PrintStackTrace in the log
     *
     * @param exception
     */
    private void setPrintStackTrace(Exception exception) {
        LogFileStream logFileStream = LogFileStream.getLogFileStream();
        exception.printStackTrace(logFileStream.getPrintWriter());
        logFileStream.close();
    }//end of the method setPrintStackTrace

}//end of the class LogManager 
