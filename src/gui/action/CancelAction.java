package gui.action;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Junior on 27/05/2016.
 */
public class CancelAction extends AbstractAction {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2635931020464442449L;
	private JDialog component;

    public CancelAction( JDialog component) {
        super("Cancelar");
        this.component = component;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        component.dispose();
    }
}
