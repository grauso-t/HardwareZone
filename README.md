# HardwareZone
---
## Panoramica del progetto
Questo progetto, sviluppato nell'ambito del corso di **Tecnologie Software per il Web** presso l'Università degli Studi di Salerno, è un sito web di e-commerce. L'obiettivo principale è offrire agli utenti un'esperienza di acquisto online intuitiva e funzionale.

Il sito presenta un'ampia gamma di prodotti organizzati per categoria, con opzioni di filtro avanzate per prezzo e specifiche. Gli utenti possono gestire il proprio profilo personale, visualizzare la cronologia degli ordini e aggiungere articoli al carrello per completare acquisti in modo sicuro.

Il sistema gestisce due ruoli utente principali: **clienti** e **amministratori**. Al momento del login, il sistema identifica il ruolo dell'utente per fornire un'esperienza personalizzata.

* **Clienti**: possono navigare il catalogo, gestire il carrello e visualizzare la cronologia degli ordini.
* **Amministratori**: hanno accesso a un'area dedicata per la gestione completa del sito. Possono aggiungere, modificare o eliminare prodotti, e supervisionare gli ordini e gli accessi degli utenti.

---
## Funzionalità principali
* **Pagina principale**: una vetrina che introduce i prodotti e le categorie disponibili.
* **Catalogo prodotti**: permette la navigazione e la ricerca avanzata di articoli.
* **Carrello**: un'interfaccia intuitiva per la gestione dei prodotti selezionati.
* **Area utente**: gestione del profilo personale e tracciamento degli ordini.
* **Pannello di amministrazione**: una dashboard per il controllo completo del sito, inclusa la gestione di prodotti, ordini e utenti.

---
## Screenshot del progetto
* **Homepage**
    ![Homepage](https://github.com/grauso-t/ecommerce/blob/master/Screenshot/HomePage.png)
* **Catalogo**
    ![Catalogo](https://github.com/grauso-t/ecommerce/blob/master/Screenshot/Catalogo.png)
* **Carrello**
    ![Carrello](https://github.com/grauso-t/ecommerce/blob/master/Screenshot/Carrello.png)

---
## Requisiti e installazione
Per avviare il progetto, sono necessari i seguenti componenti:

1.  **Server web**: un'istanza di **Apache Tomcat**. È possibile installare Tomcat seguendo le istruzioni ufficiali e successivamente caricare il file `ecommerce-1.0-SNAPSHOT.war` nella cartella `webapps`.
2.  **Database**: un'istanza di **MySQL**. Sarà necessario importare i file SQL (`*.sql`) contenuti nella cartella `database` per configurare lo schema e i dati iniziali. Si consiglia l'uso di **MySQL Workbench** per facilitare l'importazione.

Una volta configurati sia il server Tomcat che il database MySQL, il progetto sarà accessibile tramite browser.
