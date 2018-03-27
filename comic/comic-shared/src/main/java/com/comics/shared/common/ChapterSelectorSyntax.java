package com.comics.shared.common;

public class ChapterSelectorSyntax extends SelectorSyntax{
	private String nameAttKey;
	private String indexAttKey;
	private boolean increasing = false;
	public String getNameAttKey() {
		return nameAttKey;
	}
	public void setNameAttKey(String nameAttKey) {
		this.nameAttKey = nameAttKey;
	}
	public String getIndexAttKey() {
		return indexAttKey;
	}
	public void setIndexAttKey(String indexAttKey) {
		this.indexAttKey = indexAttKey;
	}
	public boolean isIncreasing() {
		return increasing;
	}
	public void setIncreasing(boolean increasing) {
		this.increasing = increasing;
	}
	@Override
	public String toString() {
		return "ChapterSelectorSyntax [nameAttKey=" + nameAttKey + ", indexAttKey=" + indexAttKey + ", increasing="
				+ increasing + ", cssQuery=" + cssQuery + ", urlAttKey=" + urlAttKey + "]";
	}
	
	
}
