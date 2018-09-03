package br.com.gescolar.types;

public enum DiaEnum {
	SEGUNDA,
	TERCA,
	QUARTA,
	QUINTA,
	SEXTA,
	SABADO;
	
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
	
}
