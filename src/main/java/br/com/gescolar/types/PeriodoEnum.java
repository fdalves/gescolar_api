package br.com.gescolar.types;

public enum PeriodoEnum {

	PRIMEIRO_PERIODO,
	SEGUNDO_PERIODO,
	TERCEIRO_PERIODO,
	QUARTO_PERIODO,
	QUINTO_PERIODO,
	SEXTO_PERIODO,
	SETIMO_PERIODO;
	
	
	public static PeriodoEnum getPeriodoEnum(int periodo) {
		switch (periodo) {
		case 1:
			return PRIMEIRO_PERIODO;
		case 2:
			return SEGUNDO_PERIODO;
		case 3:
			return TERCEIRO_PERIODO;
		case 4:
			return QUARTO_PERIODO;
		case 5:
			return SEXTO_PERIODO;
		case 6:
			return SEXTO_PERIODO;
		case 7:
			return SETIMO_PERIODO;
		default:
			return null;
		}
	}
}
