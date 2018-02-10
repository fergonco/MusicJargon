package org.fergonco.music.mjargon.jam;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.fergonco.music.midi.MidiPlayer;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;
import org.fergonco.music.mjargon.antlr.MJargonParser.ScriptContext;
import org.fergonco.music.mjargon.model.Model;
import org.fergonco.music.mjargon.parser.MJargonError;
import org.fergonco.music.mjargon.parser.ScriptLineVisitor;

public class Jammer {

	private class UIModel {
		private int tempo = 90;
		private boolean bass = true;
		private boolean drums = true;
		private String rhythm = "x..x..x.";
		private int numNotes = 8;
		private int noteLength = 8;
		private String chordSequence = "Cmaj Gmaj Amin Fmaj";

		public int getTempo() {
			return tempo;
		}

		public void setTempo(int tempo) {
			this.tempo = tempo;
		}

		public boolean isBass() {
			return bass;
		}

		public void setBass(boolean bass) {
			this.bass = bass;
		}

		public boolean isDrums() {
			return drums;
		}

		public void setDrums(boolean drums) {
			this.drums = drums;
		}

		public String getRhythm() {
			return rhythm;
		}

		public void setRhythm(String rhythm) {
			this.rhythm = rhythm;
		}

		public int getNumNotes() {
			return numNotes;
		}

		public void setNumNotes(int numNotes) {
			this.numNotes = numNotes;
		}

		public int getNoteLength() {
			return noteLength;
		}

		public void setNoteLength(int noteLength) {
			this.noteLength = noteLength;
		}

		public String getChordSequence() {
			return chordSequence;
		}

		public void setChordSequence(String chordSequence) {
			this.chordSequence = chordSequence;
		}

		public String[] getChordProgression() {
			return getChordSequence().split("\\s");
		}

		public String getScript() {
			StringBuilder builder = new StringBuilder();
			builder.append("r = [").append(getRhythm()).append("] on ").append(getNumNotes()).append("/")
					.append(getNoteLength()).append("\n");
			builder.append("seq = rDrumSeq(rhythmHits(r))").append("\n");
			builder.append("voices");
			if (isBass()) {
				builder.append("| acoustic_bass");
			}
			if (isDrums()) {
				builder.append("| drums \"right\"       | drums \"rhythm\"");
			}
			builder.append("\n");
			builder.append("tempo ").append(getTempo()).append("\n");
			String[] chords = getChordProgression();
			boolean first = true;
			for (String chord : chords) {
				if (first) {
					builder.append("start:");
					first = false;
				}
				if (isBass()) {
					builder.append("| rNotes(").append(chord).append(", rhythmHits(r)) on r");
				}
				if (isDrums()) {
					builder.append("| hh on [xxxx] on ").append(getNumNotes()).append("/").append(getNoteLength())
							.append(" | seq on r");
				}
				builder.append("\n");
			}
			builder.append("repeat start 100").append("\n");
			return builder.toString();
		}

	}

	private UIModel model = new UIModel();
	private JLabel lblErrors;
	private JTextField txtTempo;
	private JCheckBox chkBass;
	private JCheckBox chkDrums;
	private JTextField txtRhythm;
	private JTextField txtRandomTS;
	private JTextField txtChordSequence;
	private JTextArea txtScript;
	private Sequencer sequencer;

	private ActionListener playAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			stopAction.actionPerformed(e);
			MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(txtScript.getText()));
			MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
			Model model = new Model();
			ScriptContext root = parser.script();
			new ScriptLineVisitor(model).visit(root);
			model.validate();
			List<MJargonError> errors = model.getErrors();
			if (errors.size() > 0) {
				for (MJargonError mJargonError : errors) {
					System.out.println(mJargonError);
				}
			}
			try {
				File midi = File.createTempFile("jammer", ".mjargon");
				model.writeMidi(midi);
				sequencer = MidiPlayer.play(midi);
			} catch (IOException | MidiUnavailableException | InvalidMidiDataException e1) {
				e1.printStackTrace();
			}
		}
	};

	private ActionListener stopAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (sequencer != null) {
				if (sequencer.isRunning()) {
					sequencer.stop();
				}
				if (sequencer.isOpen()) {
					sequencer.close();
				}
			}
		}
	};

	private ActionListener randomRhythmAction = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			String[] pieces = new String[] { "x", "x.", "x..", "." };
			StringBuffer txt = new StringBuffer();
			while (txt.length() < model.getNumNotes()) {
				int rnd = (int) (4 * Math.random());
				txt.append(pieces[rnd]);
			}
			txt.setLength(model.getNumNotes());
			txtRhythm.setText(txt.toString());
		}
	};

	private KeyListener tempoKeyListener = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			boolean up = e.getKeyCode() == KeyEvent.VK_UP;
			boolean down = e.getKeyCode() == KeyEvent.VK_DOWN;
			if (up || down) {
				int inc;
				if (e.isControlDown()) {
					inc = 15;
				} else if (e.isShiftDown()) {
					inc = 1;
				} else {
					inc = 5;
				}
				if (down) {
					inc = -inc;
				}
				model.setTempo(model.getTempo() + inc);
			}
			updateUI();
		}
	};

	private ChangeListener randomTSKeyListener = new ChangeListener() {

		@Override
		public void newValue(String value) {
			String[] parts = value.split(Pattern.quote("/"));
			if (parts.length == 2) {
				try {
					model.setNumNotes(Integer.parseInt(parts[0]));
					model.setNoteLength(Integer.parseInt(parts[1]));
					ok();
				} catch (NumberFormatException e) {
					lblErrors.setText("invalid format in random time signature");
				}
			} else {
				lblErrors.setText("invalid format in random time signature");
			}

		}

	};

	private ChangeListener chordSequenceKeyListener = new ChangeListener() {

		@Override
		public void newValue(String value) {
			model.setChordSequence(value);
			updateScript();
		}

	};

	private ChangeListener rhythmKeyListener = new ChangeListener() {

		@Override
		public void newValue(String value) {
			model.setRhythm(value);
			updateScript();
		}

	};

	private ItemListener bassItemListener = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			model.setBass(chkBass.isSelected());
			updateScript();
		}
	};

	private ItemListener drumsItemListener = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent e) {
			model.setDrums(chkDrums.isSelected());
			updateScript();
		}
	};

	public static void main(String[] args) {
		JFrame frm = new JFrame();
		frm.setSize(500, 500);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Jammer jammer = new Jammer();
		// frm.addWindowListener(instance);
		// frm.addKeyListener(instance);
		frm.setVisible(true);
		frm.setFocusTraversalKeysEnabled(false);
		jammer.ui((JComponent) frm.getContentPane());
		jammer.ok();
		jammer.randomRhythmAction.actionPerformed(null);
		jammer.updateUI();
		frm.pack();

	}

	private void ok() {
		lblErrors.setText("All ok");
	}

	private void updateUI() {
		txtTempo.setText(Integer.toString(model.getTempo()));
		txtRandomTS.setText(model.getNumNotes() + "/" + model.getNoteLength());
		txtRhythm.setText(model.getRhythm());
		txtChordSequence.setText(model.getChordSequence());
		chkBass.setSelected(model.isBass());
		chkDrums.setSelected(model.isDrums());
		updateScript();
	}

	private void updateScript() {
		txtScript.setText(model.getScript());
	}

	private void ui(JComponent contentPane) {
		contentPane.registerKeyboardAction(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				playAction.actionPerformed(null);

			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		{
			c.gridwidth = 4;
			contentPane.add(lblErrors = newJLabel(""), c);
			c.gridwidth = 1;
		}
		c.gridy++;
		{
			c.gridx = 0;
			contentPane.add(newJLabel("Tempo:"), c);
			c.gridx = 1;
			contentPane.add(txtTempo = newJTextField(tempoKeyListener), c);
			c.gridx = 0;
		}
		c.gridy++;
		c.gridx = 1;
		{
			contentPane.add(chkBass = newJCheckBox("Bass", bassItemListener), c);
		}
		c.gridy++;
		{
			contentPane.add(chkDrums = newJCheckBox("Drums", drumsItemListener), c);
		}
		c.gridx = 0;
		c.gridy++;
		{
			contentPane.add(newJLabel("Rhythm:"), c);
			c.gridx = 1;
			contentPane.add(txtRhythm = newJTextField(rhythmKeyListener), c);
			c.gridx = 2;
			contentPane.add(newJButton("Random", randomRhythmAction), c);
			c.gridx = 3;
			contentPane.add(txtRandomTS = newJTextField(randomTSKeyListener), c);
			c.gridx = 0;
		}
		c.gridy++;
		{
			contentPane.add(newJLabel("Chord sequence:"), c);
			c.gridx = 1;
			c.gridwidth = 3;
			contentPane.add(txtChordSequence = newJTextField(chordSequenceKeyListener), c);
			c.gridwidth = 1;
			c.gridx = 0;
		}
		c.gridy++;
		{
			c.gridwidth = 4;
			contentPane.add(txtScript = newJTextArea(), c);
			c.gridwidth = 1;
		}
		c.gridy++;
		{
			c.gridwidth = 2;
			contentPane.add(newJButton("Play!", playAction), c);
			c.gridx = 2;
			contentPane.add(newJButton("Stop", stopAction), c);
			c.gridx = 0;
			c.gridwidth = 1;
		}
	}

	private JCheckBox newJCheckBox(String text, ItemListener listener) {
		JCheckBox ret = new JCheckBox(text);
		ret.addItemListener(listener);
		return ret;
	}

	private JTextArea newJTextArea() {
		return new JTextArea(12, 40);
	}

	private JButton newJButton(String text, ActionListener listener) {
		JButton ret = new JButton(text);
		ret.addActionListener(listener);
		return ret;
	}

	private JLabel newJLabel(String text) {
		JLabel ret = new JLabel(text);
		ret.setHorizontalAlignment(SwingConstants.RIGHT);
		return ret;
	}

	private JTextField newJTextField(final ChangeListener changeListener) {
		final JTextField ret = newJTextField();
		ret.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				changeListener.newValue(ret.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changeListener.newValue(ret.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				changeListener.newValue(ret.getText());
			}
		});
		return ret;
	}

	private JTextField newJTextField(KeyListener keyListener) {
		final JTextField ret = newJTextField();
		ret.addKeyListener(keyListener);
		return ret;
	}

	private JTextField newJTextField() {
		final JTextField ret = new JTextField();
		Dimension size = new Dimension(100, 30);
		ret.setPreferredSize(size);
		ret.setMaximumSize(size);
		ret.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
			}

			@Override
			public void focusGained(FocusEvent e) {
				ret.setSelectionStart(0);
				ret.setSelectionEnd(ret.getText().length());
			}
		});
		return ret;
	}

	private interface ChangeListener {
		void newValue(String value);
	}
}
