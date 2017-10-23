package com.desafio.strings;

import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
    public String formataForm(Model modelo) {
		Texto textoModel = new Texto();
		textoModel.setNumMaxChars(40);
		textoModel.setTxtOriginal("In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" + 
				"And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day." );
        
		modelo.addAttribute("texto", textoModel);
        return "index";
    }

    @PostMapping("/")
    public String formataSubmit(@ModelAttribute Texto texto, BindingResult bindingResult) {
    	
    	if (bindingResult.hasErrors()) {
            return "index";
        }
    	
    	System.out.println(formataLargura(texto.getTxtOriginal(),texto.getNumMaxChars()));
    	System.out.println(justificaTexto(texto.getTxtOriginal(),texto.getNumMaxChars()));
    	
    	texto.setTxtOutput1(formataLargura(texto.getTxtOriginal(),texto.getNumMaxChars()));
    	texto.setTxtOutput2(justificaTexto(texto.getTxtOriginal(),texto.getNumMaxChars()));
    	
        return "index";
        
    }
    
    public String formataLargura(String texto, int largura) {
    	
        StringBuilder txtFormated = new StringBuilder();
        
        Scanner words = new Scanner(texto);
        words.useDelimiter("\\s");
        
        StringBuilder currentLine = new StringBuilder();
        String lineCompare = "";

        while(words.hasNext()) {
          String word = words.next();
          lineCompare = currentLine.toString() + word;

          if (lineCompare.length() > largura) {
            txtFormated.deleteCharAt(txtFormated.length() - 1);
            txtFormated.append("\n" + word + " ");
            currentLine = new StringBuilder();
            currentLine.append("\n" + word + " ");
          } else {
            txtFormated.append(word + " ");
            currentLine.append(word + " ");
          }
        }
        
        words.close();

        return txtFormated.deleteCharAt(txtFormated.length() - 1).toString();
    }
    
    public String justificaTexto(String texto, int largura) {
    	
		String txtFormated = formataLargura(texto, largura);
		StringBuilder result = new StringBuilder();
		Scanner words = new Scanner(txtFormated);
		Scanner linhaAtual = new Scanner(txtFormated);
		linhaAtual.useDelimiter("\\n");

		while (linhaAtual.hasNext()) {

			String linha = linhaAtual.next();

			int espacosFaltantes = largura - linha.length();

			int countSpace = 0;
			for (int i = 0; i < linha.length(); i++) {
				if (linha.charAt(i) == ' ') {
					countSpace++;
				}
			}

			int espacoExtra = espacosFaltantes - countSpace;
			
			int countSpaceFor = 0;

			for (int i = 0; i < linha.length(); i++) {
				result.append(linha.charAt(i));
				
				if (linha.charAt(i) == ' ') {
					countSpaceFor++;
				}

				if (espacosFaltantes > 0) {
					if (linha.charAt(i) == ' ') {
						result.append(" ");
						espacosFaltantes--;
					}
				}
				
				if (espacoExtra > 0) {
					if (linha.charAt(i) == ' ') {
						result.append(" ");
						espacoExtra--;
						if(countSpaceFor == countSpace) {
							for(int ii=0;ii<espacoExtra;ii++) {
								result.append(" ");
							}
						}
						
					}
				}
			}
			result.append("\n");
		}

		linhaAtual.close();
		words.close();

		return result.toString();
    }

}