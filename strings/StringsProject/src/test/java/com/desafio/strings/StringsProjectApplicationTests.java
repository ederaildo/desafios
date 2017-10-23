package com.desafio.strings;

import java.util.Scanner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StringsProjectApplicationTests {
	
    @Autowired
    MainController mainCtrl;

	@Test
    public void testLarguraTextoOutput1() throws Exception {
		
	    int larguraFixaLinha = 32;
        String texto = "If starry space no limit knows and sun succeeds to sun, " + 
        		"there is no reason to suppose ou earth the only one. " + 
        		"Mid countless constellations cast a million worlds may be, " + 
        		"with each a God to bless or blast and steer to destiny.";
        
        Scanner linhaResultado = new Scanner(mainCtrl.formataLargura(texto, larguraFixaLinha));
        linhaResultado.useDelimiter("\\n");
        
        while(linhaResultado.hasNext() ){
        	String linha = linhaResultado.next();
        	assertThat(linha.length()).isLessThanOrEqualTo(larguraFixaLinha);
        }
        
        linhaResultado.close();
    }
	
	@Test
    public void testLarguraTextoOutput2() throws Exception {
		
        int larguraFixaLinha = 32;
        String texto = "If starry space no limit knows and sun succeeds to sun, " + 
        		"there is no reason to suppose ou earth the only one. " + 
        		"Mid countless constellations cast a million worlds may be, " + 
        		"with each a God to bless or blast and steer to destiny.";
        
        Scanner linhaResultado = new Scanner(mainCtrl.justificaTexto(texto, larguraFixaLinha));
        linhaResultado.useDelimiter("\\n");
        
        while(linhaResultado.hasNext() ){
        	String linha = linhaResultado.next();
        	assertThat(linha.length()).isEqualTo(larguraFixaLinha);
        }
        
        linhaResultado.close();
    }

}
