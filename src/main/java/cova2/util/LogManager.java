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

    private final String _className;

    /**
     * Constructor with name of the class
     *
     * @param className
     */
    public LogManager(String className) {
        _className = className;
    }//end of the method LogManager  

    /**
     * Trace level - What was done exatly in details
     *
     * @param message
     */
    public void trace(String message) {
        Instant timestamp = Instant.now();
        String line = "TRACE: " + (timestamp) + ": " + _className + " - " + message;
        addLine(line);
    }//end of the method trace

    /**
     * Debug level - useful to debug an application
     *
     * @param message
     */
    public void debug(String message) {
        Instant timestamp = Instant.now();
        String line = "DEBUG: " + (timestamp) + ": " + _className + " - " + message;
        addLine(line);
    }//end of the method debug

    /**
     * Info level - highlight the progress of the application
     *
     * @param message
     */
    public void info(String message) {
        Instant timestamp = Instant.now();
        String line = "INFO: " + (timestamp) + ": " + _className + " - " + message;
        addLine(line);
    }//end of the method info

    /**
     * Warn level - potentially harmful situations
     *
     * @param message
     */
    public void warn(String message) {
        Instant timestamp = Instant.now();
        String line = "WARN: " + (timestamp) + ": " + _className + " - " + message;
        addLine(line);
    }//end of the method warn

    /**
     * Error level - error events that might still allow the application to
     * continue running
     *
     * @param message
     */
    public void error(String message) {
        Instant timestamp = Instant.now();
        String line = "ERROR: " + (timestamp) + ": " + _className + " - " + message;
        addLine(line);
    }//end of the method error

    /**
     * Fatal level - very severe error events that will presumably lead the
     * application to abort
     *
     * @param message
     */
    public void fatal(String message) {
        Instant timestamp = Instant.now();
        String line = "FATAL: " + (timestamp) + ": " + _className + " - " + message;
        addLine(line);
    }//end of the method fatal

    /**
     * Trace level - What was done exatly in details
     *
     * @param message
     * @param exception
     */
    public void trace(String message, Exception exception) {
        trace(message);
        setPrintStackTrace(exception);
    }//end of the method trace

    /**
     * Debug level - useful to debug an application
     *
     * @param message
     * @param exception
     */
    public void debug(String message, Exception exception) {
        debug(message);
        setPrintStackTrace(exception);
    }//end of the method debug

    /**
     * Info level - highlight the progress of the application
     *
     * @param message
     * @param exception
     */
    public void info(String message, Exception exception) {
        info(message);
        setPrintStackTrace(exception);
    }//end of the method info

    /**
     * Warn level - potentially harmful situations
     *
     * @param message
     * @param exception
     */
    public void warn(String message, Exception exception) {
        warn(message);
        setPrintStackTrace(exception);
    }//end of the method warn

    /**
     * Error level - error events that might still allow the application to
     * continue running
     *
     * @param message
     * @param exception
     */
    public void error(String message, Exception exception) {
        error(message);
        setPrintStackTrace(exception);
    }//end of the method error

    /**
     * Fatal level - very severe error events that will presumably lead the
     * application to abort
     *
     * @param message
     * @param exception
     */
    public void fatal(String message, Exception exception) {
        fatal(message);
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

    }//end of the method addLine

    /**
     * Save PrintStackTrace in the log
     *
     * @param exception
     */
    private void setPrintStackTrace(Exception exception) {
        LogFileStream logFileStream = LogFileStream.getLogFileStream();
        exception.printStackTrace(logFileStream.getPrintWriter());

    }//end of the method setPrintStackTrace

}//end of the class LogManager 
