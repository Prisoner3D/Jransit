/*
 * Copyright (c) 2000-2017 TeamDev Ltd. All rights reserved.
 * Use is subject to Apache 2.0 license terms.
 */
package UserInterface;

import javax.swing.*;

public interface ControlPanel {
	void configureControlPanel();

	JComponent getControlPanel();

	int getPreferredHeight();
}