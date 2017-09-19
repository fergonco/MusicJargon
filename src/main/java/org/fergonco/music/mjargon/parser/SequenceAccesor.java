package org.fergonco.music.mjargon.parser;

public class SequenceAccesor {

	private int startIndex;
	private Integer endIndex = null;

	public SequenceAccesor(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void setUnbounded() {
		this.endIndex = -1;
	}

	public boolean singleNote() {
		return endIndex == null;
	}

	public int getIndex() {
		return startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

}
