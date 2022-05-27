package MainPackage;

public interface Observer {

    /**
     * notify the Observer Classes that state is changed to re observe the state.
     */
    void stateChanged();

    /**
     * When the model is changed, this Method used to set the new Model in the Observer.
     * @param model the new Model
     */
    void setModel(GameModel model);
}
