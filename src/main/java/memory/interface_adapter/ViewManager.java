package memory.interface_adapter;

import javax.swing.*;
import java.awt.*;

public class ViewManager extends JPanel implements ViewManagerModelListener {

    private final CardLayout cardLayout;
    private final ViewManagerModel viewManagerModel;

    public ViewManager(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;

        this.cardLayout = new CardLayout();
        setLayout(cardLayout);

        viewManagerModel.addListener(this);
    }

    public void addView(String viewName, JPanel view) {
        add(view, viewName);
    }

    @Override
    public void viewChanged() {
        cardLayout.show(this, viewManagerModel.getCurrentViewName());
    }
}

