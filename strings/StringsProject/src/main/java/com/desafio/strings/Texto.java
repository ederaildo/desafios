package com.desafio.strings;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Texto {
	
	@NotNull
	@Digits(fraction = 0, integer = 10000)
	private int numMaxChars;
	
	@NotNull
	@Size(min=1)
	private String txtOriginal;
	
	private String txtOutput1;
	private String txtOutput2;
	
	public int getNumMaxChars() {
		return numMaxChars;
	}
	public void setNumMaxChars(int numMaxChars) {
		this.numMaxChars = numMaxChars;
	}
	public String getTxtOriginal() {
		return txtOriginal;
	}
	public void setTxtOriginal(String txtOriginal) {
		this.txtOriginal = txtOriginal;
	}
	public String getTxtOutput1() {
		return txtOutput1;
	}
	public void setTxtOutput1(String txtOutput1) {
		this.txtOutput1 = txtOutput1;
	}
	public String getTxtOutput2() {
		return txtOutput2;
	}
	public void setTxtOutput2(String txtOutput2) {
		this.txtOutput2 = txtOutput2;
	}
	
	
}
