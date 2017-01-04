package br.com.caelum.invest.exception;

@SuppressWarnings("serial")
public class AplicacaoInvalidaException extends RuntimeException {

	public AplicacaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
