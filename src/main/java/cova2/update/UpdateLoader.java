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
package cova2.update;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

/**
 * Descrição
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class UpdateLoader {

    private final int[] actualVersion;

    public UpdateLoader(String actualVersionString) {
        actualVersion = splitVersion(actualVersionString);
    }

    public boolean isUpdateAvaliable() throws MalformedURLException, IOException {
        try {
            URL url = new URL("https://github.com/jmayer13/COVA2/raw/master/bin/version.txt");
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            Stream<String> higherVersion = reader.lines().filter(line -> isVersionHigher(line));
            return higherVersion.findAny().isPresent();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isVersionHigher(String repoVersionString) {
        int[] repoVersion = splitVersion(repoVersionString);
        if (repoVersion[0] > actualVersion[0]) {
            return true;
        } else if (repoVersion[0] == actualVersion[0]) {
            if (repoVersion[1] > actualVersion[1]) {
                return true;
            } else if (repoVersion[1] == actualVersion[1]) {
                if (repoVersion[2] > actualVersion[2]) {
                    return true;
                } else if (repoVersion[2] == actualVersion[2]) {
                    if (repoVersion[3] > actualVersion[3]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void runUpdater() throws IOException {
        Process process = Runtime.getRuntime().exec("java -jar updater.jar");
    }

    public int[] splitVersion(String version) {
        String[] versionSpited = version.split("\\.");
        System.out.println(versionSpited[0]);
        int[] versionNumbers = new int[versionSpited.length];
        for (int i = 0; i < versionSpited.length; i++) {
            versionNumbers[i] = Integer.valueOf(versionSpited[i]);
        }
        return versionNumbers;
    }

}//end of class UpdateLoader 
