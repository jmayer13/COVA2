/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
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
