package br.com.gescolar.types;

public enum DiaEnum {
	SEGUNDA, TERCA, QUARTA, QUINTA, SEXTA, SABADO;

	public static DiaEnum getDia(int dia) {

		switch (dia) {
			case 1:
				return DiaEnum.SEGUNDA;
			case 2:
				return DiaEnum.TERCA;
			case 3:
				return DiaEnum.QUARTA;
			case 4:
				return DiaEnum.QUINTA;
			case 5:
				return DiaEnum.SEXTA;
			case 6:
				return DiaEnum.SABADO;

			default:
				return null;
		}

	}

	/**
	 * @param dia
	 * @return
	 */
	public static String getDiaStr(DiaEnum dia) {

		if (dia.equals(DiaEnum.SEGUNDA)) {
			return "Segunda";
		} else if (dia.equals(DiaEnum.TERCA)) {
			return "Terça";
		} else if (dia.equals(DiaEnum.QUARTA)) {
			return "Quarta";
		} else if (dia.equals(DiaEnum.QUINTA)) {
			return "Quinta";
		} else if (dia.equals(DiaEnum.SEXTA)) {
			return "Sexta";
		} else if (dia.equals(DiaEnum.SABADO)) {
			return "Sábado";
		}
		return null;
	}

	/**
	 * @param dia
	 * @return
	 */
	public static String getDiaInt(DiaEnum dia) {
		if (dia.equals(DiaEnum.SEGUNDA)) {
			return "1";
		} else if (dia.equals(DiaEnum.TERCA)) {
			return "2";
		} else if (dia.equals(DiaEnum.QUARTA)) {
			return "3";
		} else if (dia.equals(DiaEnum.QUINTA)) {
			return "4";
		} else if (dia.equals(DiaEnum.SEXTA)) {
			return "5";
		} else if (dia.equals(DiaEnum.SABADO)) {
			return "6";
		}
		return null;
	}

}
