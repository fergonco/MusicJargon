package org.fergonco.music.mjargon.model;

import java.util.ArrayList;

import org.fergonco.music.midi.Dynamic;

public class DynamicsLine extends AbstractSongLine implements SongLine {

	private ArrayList<Dynamic> dynamics;

	public DynamicsLine(ArrayList<Dynamic> dynamics) {
		this.dynamics = dynamics;
	}

	@Override
	public boolean isDynamics() {
		return true;
	}

	@Override
	public Dynamic[] getDynamics() {
		return dynamics.toArray(new Dynamic[dynamics.size()]);
	}

	@Override
	public void validate(Model model) throws SemanticException {
	}
}
