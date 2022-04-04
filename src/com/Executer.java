package com;

import java.util.Scanner;

/**
 * @author Gaurav
 * Student ID: 110077023
 *
 */
public class Executer {
	//Displays Main menu
	public static void menu1() {		
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		boolean menu = true;
		while(menu) {
			System.out.println("\n-----------------------------------------\nSearch Engine\n-----------------------------------------");
			System.out.println("Press 1 to search for a keyword");
			System.out.println("Press 2 to crawl the pages again (It might take few minuites to crawl)");
			System.out.println("Press 0 to exit");
			System.out.println("-----------------------------------------");
			System.out.print("Select an option: ");
			String ans = sc.nextLine();

			switch(ans) {
			case "1":
				System.out.print("Enter keyword to search: ");
				Search.searchPhrase(sc2.nextLine(),10);
				break;
			case "2":
				menu2();
				menu=false;
				break;
			case "0":
				System.out.println("Exiting, thanks for using our search");
				System.exit(0);
				break;
			default:
				System.out.println("Wrong Input, Try again.");
			}
		}
		sc.close();
		sc2.close();

	}
	//Displays CrwalMenu.
	public static void menu2() {
		Scanner sc = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		boolean menu = true;
		while(menu) {
			System.out.println("\n\n-----------------------------------------\nWeb Crawling\n-----------------------------------------");
			System.out.println("Press 1 to enter websites to crawl");
			System.out.println("Press 2 to crawl the default web pages");
			System.out.println("Press 3 to Erase the webpages crawled");
			System.out.println("Press 0 to exit");
			System.out.println("-----------------------------------------");
			System.out.print("Select an option: ");
			String ans = sc.nextLine();

			switch(ans) {
			case "1":
				System.out.println("Enter websites to crawl\n");
				String url = sc2.nextLine();
				Thread newThread = new Thread(() -> {
					WebCrawler.crawlCustom(url);
				});
				newThread.start();
				System.out.println("Crawling in the back! You can continue searching as files update in the backend.");
				menu1();
				break;
			case "2":
				System.out.println("Executing crawl on default links");
				Thread newThread2 = new Thread(() -> {
					WebCrawler.crawlDefault();
				});
				newThread2.start();
				System.out.println("Crawling in the back! You can continue searching as files update in the backend.");
				menu1();
				break;
			case "3":
				System.out.println("Deleting WebPages");
				WebCrawler.wipeWebPages();
				System.out.println("Webpages deleted! Please crawl now to search.");
				menu1();
				break;
			case "0":
				System.out.println("Exiting, thanks for using our search");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Input!");
			}
			menu = false;
		}
		System.out.println("Exiting Program.");
		sc.close();
		sc2.close();
	}
	public static void main(String[] args) {
		menu1();
	}
}
