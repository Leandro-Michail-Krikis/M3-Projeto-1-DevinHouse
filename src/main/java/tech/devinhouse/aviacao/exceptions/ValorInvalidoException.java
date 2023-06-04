package tech.devinhouse.aviacao.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValorInvalidoException extends RuntimeException {
	private static final long serialVersionUID = 5500398080166437740L;
	
	public final Map<String, Object> detalhes;
	
	public ValorInvalidoException() {
		this.detalhes = new HashMap<>();
	}
	
	public ValorInvalidoException(Map<String, Object> detalhes) {
		this.detalhes = new HashMap<>(detalhes);
	}
	
	public Map<String, Object> getDetalhes(){
		return detalhes;
	}
	
	public Map<String, Object> put(String key, Object value) {
		this.detalhes.put(key, value);
		return this.detalhes;
	}
}
