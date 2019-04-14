package org.fergonco.music.mjargon.model;

public class Section {
	private int start = -1;
	private int end = -1;

	public Section(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isComplete() {
		return start != -1 && end != -1;
	}
}
