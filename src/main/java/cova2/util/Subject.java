package cova2.util;

//WHY: because java observable is a class
/**
 * Subject interface to observer design pattern
 *
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public interface Subject {

    public void addObserver(Observer observer);

    public void deleteObserver(Observer observer);

    public default void notifyObservers() {
        notifyObservers(null);
    }

    public void notifyObservers(Object arg);

}//end of the interface Subject
