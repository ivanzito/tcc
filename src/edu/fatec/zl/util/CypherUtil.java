package edu.fatec.zl.util;

import org.springframework.stereotype.Component;

@Component
public class CypherUtil {

	public CypherUtil() {
		super();
	}

	public String cypherLogin(String codUsuario, String senha) {

		String senhaResult;
		String arrumaResult = new String("");

		StringBuffer codUsuarioAsciiAux;
		StringBuffer senhaAsciiAux;
		StringBuffer valorAscii;

		Short codUsuarioShort;
		Short senhaShort;
		Short shortAux;

		short somaSenha = 0;

		codUsuarioAsciiAux = new StringBuffer("000000000000000000000000");
		senhaAsciiAux = new StringBuffer("000000000000000000000000");
		valorAscii = new StringBuffer("000000000000000000000000");

		int ind2 = 1;
		for (int ind1 = codUsuario.length(); ind1 >= 1; ind1--) {
			codUsuarioAsciiAux.replace(ind2 - 1, ind2 + 1,
					obtemValorAscii(codUsuario.substring(ind1 - 1)));
			ind2 = ind2 + 3;

		}

		codUsuarioAsciiAux.setLength(24);

		// Obtendo usuario criptografado
		ind2 = 1;
		for (int ind1 = 0; ind1 < senha.length(); ind1++) {
			senhaAsciiAux.replace(ind2 - 1, ind2 + 1,
					obtemValorAscii(senha.substring(ind1)));
			ind2 = ind2 + 3;
		}

		senhaAsciiAux.setLength(24);

		for (int ind1 = 0; ind1 < 22; ind1 += 3) {
			codUsuarioShort = new Short(codUsuarioAsciiAux.substring(ind1,
					ind1 + 3).toString());
			senhaShort = new Short(senhaAsciiAux.substring(ind1, ind1 + 3)
					.toString());
			somaSenha = (short) (codUsuarioShort.shortValue() + senhaShort
					.shortValue());
			shortAux = new Short(somaSenha);
			arrumaResult = Short.toString(shortAux.shortValue());
			if ((arrumaResult != null) && (arrumaResult.length() == 2)) {
				arrumaResult = "0" + arrumaResult;
			}
			valorAscii.replace(ind1, ind1 + 2, arrumaResult);
			// System.out.println("1 : "+ shortAux.shortValue());//teste2
			// System.out.println("2 : " + valorAscii.toString()); //teste3
		}

		valorAscii.setLength(24);
		// Truncando p/ os 24 caracteres da criptografia do Logix

		senhaResult = (String) valorAscii.toString();

		// Obtendo senha criptografada
		return senhaResult;
	}

	public String obtemValorAscii(String caracterString) {
		Character caracter;
		Character caracter2;
		Character caracter3;
		Character caracter4;

		String caracterAux;
		String resultCharacter;

		StringBuffer valorAscii;

		int compara = 0;

		short posIni = 0;
		short posFim = 0;
		short comparador = 0;

		byte valorAsciiAux;

		posIni = (short) 32;
		posFim = (short) 126;
		caracter = new Character(caracterString.charAt(0));
		caracter2 = new Character('0');
		caracter3 = new Character('A');
		caracter4 = new Character('a');

		valorAscii = new StringBuffer();

		compara = caracter.compareTo(caracter2);
		// Retorna 0 (zero) se for igual numericamente
		// Retorna um numero menor que zero se for menor numericamente
		// Retorna um numero maior que zero se for maior numericamente

		if (compara == 0 || compara > 0) {
			posIni = (short) 48;
			compara = caracter.compareTo(caracter3);
			if (compara == 0 || compara > 0) {
				posIni = (short) 65;
				compara = caracter.compareTo(caracter4);
				if (compara == 0 || compara > 0) {
					posIni = (short) 97;
				} else {
					posFim = (short) 90;
				}
			} else {
				posFim = (short) 90;
			}
		} else {
			posFim = (short) 47;
		}

		for (int i = posIni; i <= posFim; i++) {
			caracterAux = caracter.toString();
			valorAsciiAux = (byte) caracterAux.charAt(0);
			comparador = valorAsciiAux;
			if (comparador == i) {
				if (comparador < 100) {
					valorAscii.append("0").append(i);
					// apenas para fazer igual ao 4GL USING &&&
				} else {
					valorAscii.append(i);
				}
			}
		}
		resultCharacter = new String(valorAscii);
		return resultCharacter;
	}
}
