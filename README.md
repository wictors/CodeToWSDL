# 1. Od kódu k WSDL

Projekt bol vytvorený v Eclipse IDE for Java Developers // Version: Oxygen.2 Release (4.7.2)
Použitá JAVA verzia: jdk1.8.0_151
Pre náhľad WSDL bol použitý prehliadač Chrome. 

Po stiahnutí projekt do IDE, sa serverovská časť spúšta cez triedu Server.java, ktorá sa nachádza v balíčku sk.kopr.server.
Klientská časť sa spúšťa cez triedu Client.java, ktorý sa nachádza v balíčku sk.kopr.client.

JUNIT testy na otestovanie celej aplikácie sa nachádzajú v balíčku sk.kopr.test. DaoTestServer testuje serverovskú časť unit testmi 
a TestClient testuje klientskú časť unit testmi. 

Projekt využíva SQL databázu. Pre zostavenie databázy využite príkazy v priloženom súbore sql.sql. Vytvorí novú schému s tabuľkami
a používateľa na prístup do schémy. Údaje na otestovanie nie sú potrebné. 

Vytvorenie databázy stačí vykonať raz. Prvý aj druhý projekt využívajú rovnakú databázu aj prístupového klienta. 
