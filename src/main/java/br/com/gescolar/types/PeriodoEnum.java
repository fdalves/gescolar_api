package br.com.gescolar.types;

public enum PeriodoEnum {

	PRIMEIRO_PERIODO,
	SEGUNDO_PERIODO,
	TERCEIRO_PERIODO,
	QUARTO_PERIODO,
	QUINTO_PERIODO,
	SEXTO_PERIODO,
	SETIMO_PERIODO,
	OITAVO_PERIODO,
	NONO_PERIODO,
	DECIMO_PERIODO;
	
	
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
			return QUINTO_PERIODO;
		case 6:
			return SEXTO_PERIODO;
		case 7:
			return SETIMO_PERIODO;
		case 8:
			return OITAVO_PERIODO;
		case 9:
			return NONO_PERIODO;
		case 10:
			return DECIMO_PERIODO;
		default:
			return null;
		}
	}
	
	/**
	 * parse
	 * @param periodo
	 * @return PeriodoEnum
	 */
	public static PeriodoEnum parse(String periodo) {
		if (periodo.equals("PRIMEIRO_PERIODO")) {
			return PRIMEIRO_PERIODO;
		} else if (periodo.equals("SEGUNDO_PERIODO")) {
			return SEGUNDO_PERIODO;
		} else if (periodo.equals("TERCEIRO_PERIODO")) {
			return TERCEIRO_PERIODO;
		} else if (periodo.equals("QUARTO_PERIODO")) {
			return QUARTO_PERIODO;
		} else if (periodo.equals("QUINTO_PERIODO")) {
			return QUINTO_PERIODO;
		} else if (periodo.equals("SEXTO_PERIODO")) {
			return SEXTO_PERIODO;
		} else if (periodo.equals("SETIMO_PERIODO")) {
			return SETIMO_PERIODO;
		} else if (periodo.equals("OITAVO_PERIODO")) {
			return OITAVO_PERIODO;
		} else if (periodo.equals("NONO_PERIODO")) {
			return NONO_PERIODO;
		} else if (periodo.equals("DECIMO_PERIODO")) {
			return DECIMO_PERIODO;
		}
		return null;
	}
}
