package cova2.view;

import cova2.util.InternationalizationCentral;
import cova2.util.LogManager;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import jone.swing.JFormFrame;

/**
 * Form frame to add/edit anime
 *
 * @see
 * @author Jonas Mayer (jonas.mayer.developer@gmail.com)
 */
public class AddAnimeView {

    private JFormFrame frame;
    private JLabel animeTitleLabel;
    private JLabel currentEpisodeLabel;
    private JTextField animeTitleField;
    private JTextField currentEpisodeField;
    private LogManager logManager;

    /**
     * Constructor stars view
     */
    public AddAnimeView() {
        logManager = new LogManager(AddAnimeView.class.getName());
        logManager.trace("Initializing view ...");
        startView();
    }//end of the constructor

    /**
     * Start view
     */
    private void startView() {
        InternationalizationCentral iCentral = new InternationalizationCentral();

        logManager.trace("Initializing components");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int xPosition = (int) (dimension.getWidth() - 400);
        frame = new JFormFrame(new Point(xPosition, 0), 400);
        frame.setInternalSize(300, 200);
        animeTitleLabel = new JLabel(iCentral.getWord("title"));
        currentEpisodeLabel = new JLabel(iCentral.getWord("current_episode"));
        animeTitleField = new JTextField();
        currentEpisodeField = new JTextField();

        frame.setOkButtonLabel(iCentral.getWord("ok"));
        frame.setCancelButtonLabel(iCentral.getWord("cancel"));

        logManager.trace("Setting bounds");
        animeTitleLabel.setBounds(10, 20, 150, 25);
        currentEpisodeLabel.setBounds(10, 60, 150, 25);
        animeTitleField.setBounds(160, 20, 150, 25);
        currentEpisodeField.setBounds(160, 60, 50, 25);

        logManager.trace("Joining components");
        frame.addToInternalPanel(animeTitleLabel);
        frame.addToInternalPanel(currentEpisodeLabel);
        frame.addToInternalPanel(animeTitleField);
        frame.addToInternalPanel(currentEpisodeField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//end of the method startView

    /**
     * Check if frame is open
     *
     * @return <code>boolean</code> if problem open?
     */
    public boolean isOpen() {
        return frame.isVisible();
    }//end of the method isOpen

    /**
     * Close view
     */
    public void close() {
        logManager.debug("Closing view");
        frame.dispose();
    }//end of the method close

    /**
     * Set text to field of title of anime
     *
     * @param title text to be displayed on field
     */
    public void setTitleAnime(String title) {
        logManager.debug("Set titleAnime " + title);
        animeTitleField.setText(title);
    }//end of the method setTitleAnime

    /**
     * Set text to field of current epidode
     *
     * @param currentEpisode text to be displayed on field
     */
    public void setCurrentEpisode(double currentEpisode) {
        logManager.debug("Set currentEpisode " + currentEpisode);
        if (currentEpisode < 0) {
            currentEpisodeField.setText("");
        } else {
            currentEpisodeField.setText("" + currentEpisode);
        }
    }//end of the method setCurrentEpisode

    /**
     * Get title of anime in field
     *
     * @return <code>String</code> title of anime
     */
    public String getTitleAnime() {
        logManager.debug("Get titleAnime " + animeTitleField.getText());
        return animeTitleField.getText();
    }//end fo the method getTitleAnime

    /**
     * Get current Episode from form
     *
     * @return <code>Double</code> Current Episode
     */
    public double getCurrentEpisode() {
        logManager.debug("Get CurrentEpisode " + currentEpisodeField.getText());
        if (currentEpisodeField.getText() == null || currentEpisodeField.getText().isEmpty()) {
            return -1;
        } else {
            return Double.valueOf(currentEpisodeField.getText());
        }
    }//end of the method getCurrentEpisode

    /**
     * Is data in form valid?
     *
     * @return <code>Boolean</code> answer
     */
    public boolean isValid() {
        if (animeTitleField.getText() != null) {
            if (!animeTitleField.getText().isEmpty()) {
                logManager.debug("The data in form is valid");
                return true;
            }
        }
        logManager.debug("The data in form is  not valid");
        return false;
    }//end of the method isValid

    /**
     * Add actionListener for the button OK
     *
     * @param ac
     */
    public void addOKActionListener(ActionListener ac) {
        logManager.trace("Action Listener added to the button OK");
        frame.addOkButtonActionListener(ac);
    }//end of the method addOKActionListener

    /**
     * Add actionListener for the button cancel
     *
     * @param ac
     */
    public void addCancelActionListener(ActionListener ac) {
        logManager.trace("Action Listener added to the button OK");
        frame.addCancelButtonActionListener(ac);
    }//end of the method addCancelActionListener

    /**
     * Add WindowAdapter for the frame
     *
     * @param windowAdapter
     */
    public void addWindowListener(WindowAdapter windowAdapter) {
        logManager.trace("Window Listener added to the frame");
        frame.addWindowListener(windowAdapter);
    }//end of the method addWindowListener

    //GUI test
    public static void main(String args[]) {
        new AddAnimeView();
    }//end main
}//end of the class AddAnimeFrame 
