package com.desafio.crawlers;

import javax.validation.constraints.NotNull;

public class Comando {
	
	@NotNull
	private String comandoTexto;
	private String msg;
	private String msgErro;
	
	public String getComandoTexto() {
		return comandoTexto;
	}
	public void setComandoTexto(String comandoTexto) {
		this.comandoTexto = comandoTexto;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getMsgErro() {
		return msgErro;
	}
	public void setMsgErro(String msgErro) {
		this.msgErro = msgErro;
	}

	



	
}
