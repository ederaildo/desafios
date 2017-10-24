package com.desafio.crawlers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @author Ederaildo Fontes
 *
 */
public class CliBasicParser {

	private static final Logger log = Logger.getLogger(CliBasicParser.class.getName());
	private String[] args = null;
	private Options options = new Options();
	private final int THREAD_BOMBANDO = 5000;
	private final String URL_REDDIT = "https://www.reddit.com/r/";

	public CliBasicParser(String[] args) {
		this.args = args;
		options.addOption("subreddits", "var", true, "Lista de subreddits");
	}

	public CliBasicParser() {
	}

	public void parse() {
		CommandLineParser parser = new BasicParser();
		CommandLine cmd = null;
		ArrayList<String> arrSubreddits = new ArrayList<String>();

		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption("subreddits")) {
				Scanner retParamSubreddits = new Scanner(cmd.getOptionValue("subreddits"));
				retParamSubreddits.useDelimiter(";");

				while (retParamSubreddits.hasNext()) {
					arrSubreddits.add(retParamSubreddits.next());
				}
				retParamSubreddits.close();
				listaSimples(arrSubreddits);

			} else {
				System.out.println("\n\n>>>>>>> Parametro subreddits nao informado. \n\n");
			}

		} catch (org.apache.commons.cli.ParseException e) {
			System.out.println("\\n\\n>>>>>>> Falha na recuperação de parametro \n\n");
		}
	}

	public void listaSimples(ArrayList<String> subreddits) throws ParseException {

		try {
			List<SubReddit> lista = getSubReddits(subreddits);

			int i = 0;
			while (i < lista.size()) {
				
				SubReddit subRObj = (SubReddit)lista.get(i);
				
				System.out.println("# subreddit ....: " + subRObj.getSubreddit());
				System.out.println("# upvotes ......: " + subRObj.getUpvotes());
				System.out.println("# titulo .......: " + subRObj.getTitle());
				System.out.println("# link .........: " + subRObj.getUrl());
				System.out.println("# link comments.: " + subRObj.getPermalink());
				System.out.println("\n");
				
				i++;
			}
		} catch (Exception e) {
			System.out.println("\n\n>>>>>>> Erro no carregamento das Threads: \n");
			e.printStackTrace();
		}

	}
	
	
    public List<SubReddit> getSubReddits(ArrayList<String> subreddits){
    	
        List<SubReddit> lista =new ArrayList<SubReddit>();
        
		try {
			int i = 0;
			while (i < subreddits.size()) {
				String url = lerConteudo(URL_REDDIT + subreddits.get(i) + "/.json");
				
				JSONObject data = new JSONObject(url).getJSONObject("data");
				JSONArray children = data.getJSONArray("children");

				for (int ii = 0; ii < children.length(); ii++) {

					JSONObject chd = children.getJSONObject(ii).getJSONObject("data");
					if(chd.optInt("ups") >= THREAD_BOMBANDO) {
						
						SubReddit subR = new SubReddit();
						
						subR.setSubreddit(chd.optString("subreddit"));
						subR.setUpvotes(chd.optInt("ups"));
						subR.setTitle(chd.optString("title"));
						subR.setUrl(chd.optString("url"));
						subR.setPermalink(chd.optString("permalink"));
						
						lista.add(subR);
					}
				}
				i++;
			}
			
			Collections.sort(lista, SubReddit.SubRedditsUpVotesComparator);
			
		} catch (Exception e) {
			System.out.println("\n\n>>>>>>> Erro no carregamento da URL: \n");
			e.printStackTrace();
		}
		return lista;       
    }
		

	public static HttpURLConnection conexao(String url) {
		HttpURLConnection hcon = null;
		try {
			hcon = (HttpURLConnection) new URL(url).openConnection();
			hcon.setReadTimeout(30000);
			hcon.setRequestProperty("User-Agent", "Alien V1.0");
		} catch (MalformedURLException e) {
			System.out.println("\n\n>>>>>>> URL invalida: \n");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("\n\n>>>>>>> Não foi possível conectar: \n");
			e.printStackTrace();
		}
		return hcon;
	}

	public static String lerConteudo(String url) {
		HttpURLConnection hcon = conexao(url);
		if (hcon == null)
			return null;
		try {
			StringBuffer sb = new StringBuffer(8192);
			String tmp = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(hcon.getInputStream()));
			while ((tmp = br.readLine()) != null)
				sb.append(tmp).append("\n");
			br.close();
			return sb.toString();
		} catch (IOException e) {
			return null;
		}
	}

}
