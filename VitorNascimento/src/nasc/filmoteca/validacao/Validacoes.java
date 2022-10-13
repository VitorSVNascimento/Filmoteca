package nasc.filmoteca.validacao;

public class Validacoes {

		
		/**
		 * verifica se a data é valida caso não seja retorna 0
		 */
		public static int validaData(String data){
		
			int dia,mes,ano,cont=0,bisexto=0;
			
			if(data.length()!=10) {
				return 0;
			}
			
			String dataAux=data.substring(6);
			
			for(int i=0;i<data.length();i++) {
			//verifica se existe caracter além de numeros ou "/"	
				if(!Character.isDigit(data.charAt(i)) && data.charAt(i)!='/') {
					return 0;}
						
			}
			
			for(int i=0;i<data.length();i++) {
				//verifica se existe mais de duas "/" na string
				if(data.charAt(i)=='/') {
					cont++;}
						
			}
			if(cont!=2) {
				return 0;
				
			}
			
			if(data.charAt(2)!='/' && data.charAt(5)!='/') {
				return 0;
			}
		
				ano=Integer.parseInt(dataAux);
				if(ano<1000 || ano>2100) {
					//verica se o ano é valido
					return 0;
				}
				//verifica se o ano é bisexto
				if(((ano % 4 == 0) &&(ano % 100 !=0))|| (ano%400 ==0)) {
					bisexto=1;
				}
				
				
				 dataAux=data.substring(3,5);
				mes=Integer.parseInt(dataAux);
				
				if(mes<1 || mes>12) {
					// verifica se o mes é valido
					return 0;
				}
				
				 dataAux=data.substring(0,2);
				 dia=Integer.parseInt(dataAux);
				 //verifica se o dia é valido de acordo com o mes	
				 switch(mes) {
				 	case 1:
				 	case 3:
				 	case 5:
				 	case 7:
				 	case 8:
				 	case 10:
				 	case 12:
				 	if(dia<1 || dia>31) {
				 		return 0;
				 	}
				 	break;
				 	case 4:
				 	case 6:
				 	case 9:
				 	case 11:
				 	if(dia<1 || dia>30) {
					return 0;
					 	}
				 	break;
				 	case 2:
				 		if(bisexto==1) {
				 			
				 			if(dia<1 || dia>29) {
				 				return 0;
				 			}
				 		}else {
				 			if(dia<1 || dia>28) {
				 				return 0;
				 			}
				 		}
				 	}
				return 1;
		
		}
		
		public static boolean validaClassificacao(String classificacao) {
		System.out.println(classificacao);	
		String[] classificacoes = {"Livre","L","10","12","14","16","18"};
		
		for(int i=0;i<classificacoes.length;i++) {
			if(classificacao.equalsIgnoreCase(classificacoes[i])) {
				return true;
			}
			
		}
		return false;	
		}
	
		
}