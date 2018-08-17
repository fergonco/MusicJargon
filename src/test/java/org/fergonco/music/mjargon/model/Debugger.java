package org.fergonco.music.mjargon.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.fergonco.music.mjargon.antlr.MJargonLexer;
import org.fergonco.music.mjargon.antlr.MJargonParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Debugger {
	private static final Gson PRETTY_PRINT_GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Gson GSON = new Gson();

	public static String toJson(ParseTree tree) {
		return toJson(tree, true);
	}

	public static String toJson(ParseTree tree, boolean prettyPrint) {
		return prettyPrint ? PRETTY_PRINT_GSON.toJson(toMap(tree)) : GSON.toJson(toMap(tree));
	}

	public static Map<String, Object> toMap(ParseTree tree) {
		Map<String, Object> map = new LinkedHashMap<>();
		traverse(tree, map);
		return map;
	}

	public static void traverse(ParseTree tree, Map<String, Object> map) {

		if (tree instanceof TerminalNodeImpl) {
			Token token = ((TerminalNodeImpl) tree).getSymbol();
			map.put("type", token.getType());
			map.put("text", token.getText());
		} else {
			List<Map<String, Object>> children = new ArrayList<>();
			String name = tree.getClass().getSimpleName().replaceAll("Context$", "");
			map.put(Character.toLowerCase(name.charAt(0)) + name.substring(1), children);

			for (int i = 0; i < tree.getChildCount(); i++) {
				Map<String, Object> nested = new LinkedHashMap<>();
				children.add(nested);
				traverse(tree.getChild(i), nested);
			}
		}
	}

	public static void main(String[] args) {
		String source = "voices | drums \"right hand\"\n       | hh on [xxxx] on 4/4";
		MJargonLexer lexer = new MJargonLexer(new ANTLRInputStream(source));
		MJargonParser parser = new MJargonParser(new CommonTokenStream(lexer));
		System.out.println(toJson(parser.script()));
	}
}
