package com.practicaljava.lesson24;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Prints out a randomly generated price from the stock server for the
 * stock provided in the argument.
 * 
 * @author David Lall
 */

public class Client {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("\n Sample usage: java client.Client AAPL");
			System.exit(0);
		}
		try {
			StockServer myServer = (StockServer)
					Naming.lookup("rmi://localhost:1099/QuoteService");
			String price = myServer.getQuote(args[0]);
			if (price != null) {
				System.out.println("The price of " + args[0] + " is: $ " 
						+ price); 
			}
			else {
				System.out.println("Invalid NASDAQ symbol. Please use one of"
						+ " these: " + myServer.getNasdaqSymbols().toString());
			}
		}
		catch (MalformedURLException exMF) {
			System.out.println(exMF.getMessage());
		}
		catch (NotBoundException exNB) {
			System.out.println(exNB.getMessage());
		}
		catch (RemoteException exRE) {
			System.out.println(exRE.getMessage());
		}
	}
}