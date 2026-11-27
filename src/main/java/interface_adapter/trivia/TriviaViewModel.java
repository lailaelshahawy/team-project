package interface_adapter.trivia;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Stores the trivia screen's state and alerts the view when it changes (Observer pattern).
 * Shared by the Start, Submit Answer, and Review Results presenters.
 */
public class TriviaViewModel {
    public static final String STATE_PROPERTY = "state";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private TriviaState state = new TriviaState();

    public TriviaState getState() {
        return state;
    }

    public void setState(TriviaState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange(STATE_PROPERTY, null, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
