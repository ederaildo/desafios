package com.desafio.crawlers.sb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.desafio.crawlers.CliBasicParser;
import com.desafio.crawlers.Comando;
import com.desafio.crawlers.SubReddit;

/**
 * @author Ederaildo Fontes
 *
 */
@Controller
public class CrawlersController {
	
    public static final String BASEURL = "https://api.telegram.org/bot";
    public static final String TOKEN = "472628283:AAGeawEPr6OkUYBWKrJoc-Y6UV7OU9fq0GQ";
    public static final String PATH = "sendmessage";
    public static final String CHAT_ID = "@nadaprafazerIDwall";
    public static final String PARSE_MODE = "Markdown";
	
	private CliBasicParser cliParser = new CliBasicParser();
	
	@GetMapping("/")
    public String formataForm(Model modelo) {
		Comando comando = new Comando();
		modelo.addAttribute("comando", comando);
        return "index";
    }

    @PostMapping("/")
    public String enviaTelegram(@Valid Comando comando, BindingResult bindingResult, Model model) {
    	
    	//Recuperar Lista
    	ArrayList<String> arrSubreddits = new ArrayList<String>();
    	StringBuilder sbRet = new StringBuilder();
    	
    	if (bindingResult.hasErrors()) {
            return "index";
        }
    	
    	///NadaPraFazer programming;dogs;brazil
    	
    	if(!comando.getComandoTexto().startsWith("/NadaPraFazer")) {
    		comando.setMsgErro("Comando invalido. Exemplo: /NadaPraFazer programming;dogs;brazil");
    		model.addAttribute("comando", comando);
    		return "index";
    	}
    	
    	
		Scanner retParamSubreddits = new Scanner(comando.getComandoTexto().replace("/NadaPraFazer ", ""));
		retParamSubreddits.useDelimiter(";");
    	
		while (retParamSubreddits.hasNext()) {
			arrSubreddits.add(retParamSubreddits.next());
		}
		retParamSubreddits.close();
		
		try {
			List<SubReddit> lista = cliParser.getSubReddits(arrSubreddits);

			int i = 0;
			while (i < lista.size()) {
				SubReddit subRObj = (SubReddit)lista.get(i);
				sbRet.append(subRObj.getURLFormatada()+" ");
				i++;
			}
		} catch (Exception e) {
			System.out.println("\n\n>>>>>>> Erro no carregamento das Threads: \n");
			e.printStackTrace();
		}
		
		//Enviar para o Canal @nadaprafazerIDwall do Telegram
		try {
			
			CloseableHttpClient client = HttpClients.createDefault();
            String url = BASEURL + TOKEN + "/" + PATH;
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("Content-type", "application/x-www-form-urlencoded");
            httppost.addHeader("charset", "UTF-8");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("chat_id", CHAT_ID + ""));
            nameValuePairs.add(new BasicNameValuePair("parse_mode", PARSE_MODE+ ""));
	        nameValuePairs.add(new BasicNameValuePair("text", sbRet.toString()));
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
	        CloseableHttpResponse response = client.execute(httppost);

            if(response.getStatusLine().getStatusCode() == 200) {
            	comando.setMsg("Mensagem enviada.");
            }else {
            	comando.setMsgErro("Erro ao enviar mensagem.");
            }
                       
            client.close();
        } catch (IOException e) {
        	System.out.println("\n\n>>>>>>> Erro ao enviar Telegram: \n");
            e.printStackTrace();
        }
		
		model.addAttribute("comando", comando);
  	
        return "index";
    }

}
