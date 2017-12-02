package really.game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.JButton;

public class TransparentButton extends JButton {
	private static final long serialVersionUID = 1L;
	private int r = 8;

	public TransparentButton(String text) {
		super(text);
	}

	public TransparentButton(Icon icon) {
		super(icon);
	}

	public TransparentButton(String text, Icon icon) {
		super(text, icon);
	}

	@Override
	public void updateUI() {
		super.updateUI();
		setContentAreaFilled(false);
		setFocusPainted(false);
		setOpaque(false);
		setForeground(Color.WHITE);
		setBorder(null);
		setBorderPainted(false);
		setRolloverEnabled(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		int x = 0;
		int y = 0;
		int w = getWidth();
		int h = getHeight();
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		Shape area = new RoundRectangle2D.Float(x, y, w - 1, h - 1, r, r);
		ButtonModel m = getModel();
		Color col = getBackground();
		if (m.isPressed()) {
			col = getBackground().darker().darker();
		} else if (m.isRollover()) {
			col = getBackground().darker();
		}
		g2.setPaint(col);
		g2.fill(area);
		g2.dispose();
		super.paintComponent(g);
	}
}