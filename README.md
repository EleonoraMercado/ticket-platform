# 🚀 Sistema di Gestione Ticket

Benvenuto nel **Sistema di Gestione Ticket**, un'applicazione web sviluppata con **Spring Boot** per facilitare la gestione efficiente dei ticket di supporto all'interno di un'organizzazione. Questo sistema offre una serie di funzionalità  per amministratori e operatori, garantendo sicurezza e facilità d'uso. Tuttavia, l'applicazione è ancora a un livello embrionale, e ci sono molti dettagli e funzionalità che desidero implementare per renderla più completa e raffinata.


<img src="src/main/resources/static/screenshot/banner_README.png" alt="Banner del Progetto" width="800" height="200">


## 🛠️ **Funzionalità Principali**

### 🎫 **Gestione dei Ticket**
- **Creazione, Visualizzazione, Modifica ed Eliminazione:** Gestisci i ticket in modo semplice e intuitivo.
- **Assegnazione agli Operatori:** Assegna i ticket agli operatori disponibili per una gestione ottimale.
- **Aggiornamento dello Stato dei Ticket:** Monitora lo stato dei ticket con categorie come **Da Fare**, **In Corso**, **Completato** e **Annullato**.
- **Aggiunta di Note:** Inserisci note per comunicazioni interne e aggiornamenti sullo stato dei ticket.

### 👥 **Gestione degli Operatori**
- **Aggiunta e Rimozione:** Gli amministratori possono aggiungere o rimuovere operatori facilmente.
- **Attivazione e Disattivazione:** Gestisci lo stato degli operatori con verifiche sui ticket pendenti.
- **Visualizzazione Stato Operatori:** Controlla se gli operatori sono **Attivi** o **Inattivi**.

### 📂 **Gestione delle Categorie**
- **Creazione, Modifica ed Eliminazione:** Organizza i ticket tramite categorie per una migliore classificazione.
- **Assegnazione di Categorie ai Ticket:** Migliora la gestione e il tracciamento dei ticket assegnando categorie specifiche.

### 🔒 **Sicurezza e Autenticazione**
- **Autenticazione Basata su Ruoli:** Differenzia tra **amministratori** e **operatori** per controllare l'accesso alle funzionalità.
- **Protezione delle Rotte Sensibili:** Utilizza **Spring Security** per proteggere le aree critiche dell'applicazione.
- **Codifica delle Password:** Garantisce la sicurezza degli utenti mediante la codifica delle password.

### 📊 **Dashboard Personalizzate**
- **Interfaccia Amministratori:** Accesso completo a tutte le funzionalità per una gestione centralizzata.
- **Interfaccia Operatori:** Focus sui ticket assegnati, facilitando la gestione quotidiana.

## 🖥️ **Tecnologie Utilizzate**

### 🔧 **Backend**
- **Java 11+**
- **Spring Boot**
- **Spring MVC**
- **Spring Data JPA**
- **Spring Security**

### 🎨 **Frontend**
- **Thymeleaf**
- **Bootstrap 5**

### 🗄️ **Database**
- **MySQL** 

### 🛠️ **Strumenti di Gestione Database**
- **DBeaver:** Utilizzato per la gestione e l'amministrazione del database.

## 🔐 **Credenziali di Accesso Predefinite**

### **Amministratore**
- **Email:** `admin@esempio.com`
- **Password:** `password`

### **Operatore**
- **Email:** `operatore@esempio.com`
- **Password:** `password`


## 📜 **Licenza**

Questo progetto è distribuito sotto la **Licenza MIT**. Vedi il file [LICENSE](LICENSE) per ulteriori dettagli.


## 📸 **Screenshot**

## 👨‍💼 Admin

### 📊 Dashboard Amministratore
<img src="src/main/resources/static/screenshot/dashboardAmministratore.png" alt="Dashboard Amministratore" width="800"/>

### 🎫 Gestione dei Ticket (Admin)
- **Lista Tutti i Ticket**
  <img src="src/main/resources/static/screenshot/lista_tuttiTicket_dashboardAdmin.png" alt="Lista Tutti i Ticket - Dashboard Admin" width="800"/>
- **Dettaglio Singolo Ticket**
  <img src="src/main/resources/static/screenshot/dettaglio_singoloTicket_dashboardAdmin.png" alt="Dettaglio Singolo Ticket - Dashboard Admin" width="800"/>
- **Form Crea Nuovo Ticket**
  <img src="src/main/resources/static/screenshot/form_creaNuovoTicket_dashboardAdmin.png" alt="Form Crea Nuovo Ticket - Dashboard Admin" width="800"/>
- **Modifica Ticket**
  <img src="src/main/resources/static/screenshot/form_modificaTicket_dashboardAdmin.png" alt="Modifica Ticket - Dashboard Admin" width="800"/>

### 👥 Gestione degli Operatori (Admin)
- **Lista Operatori**
  <img src="src/main/resources/static/screenshot/listaOperatori_dashboardAdmin.png" alt="Lista Operatori - Dashboard Admin" width="800"/>
- **Form Crea Nuovo Operatore**
  <img src="src/main/resources/static/screenshot/form_creaNuovoOperatore_dashboardAdmin.png" alt="Form Crea Nuovo Operatore - Dashboard Admin" width="800"/>

### 📂 Gestione delle Categorie (Admin)
- **Lista Categorie**
  <img src="src/main/resources/static/screenshot/listaCategorie_dashboardAdmin.png" alt="Lista Categorie - Dashboard Admin" width="800"/>
- **Crea Nuova Categoria**
  <img src="src/main/resources/static/screenshot/modale_creaNuova_categoria_dashboardAdmin.png" alt="Crea Nuova Categoria - Dashboard Admin" width="800"/>

---

## 👨‍🔧 Operatore

### 📊 Dashboard Operatore
<img src="src/main/resources/static/screenshot/dashboardOperatore.png" alt="Dashboard Operatore" width="800"/>

### 🎫 Gestione dei Ticket (Operatore)
- **Dettaglio Singolo Ticket**
  <img src="src/main/resources/static/screenshot/dettaglio_singolo_ticket_dashboardOperatore.png" alt="Dettaglio Singolo Ticket - Dashboard Operatore" width="800"/>
- **Modifica Stato Ticket**
  <img src="src/main/resources/static/screenshot/modale_modificaStatoTicket_dashboardOperatore.png" alt="Modifica Stato Ticket - Dashboard Operatore" width="800"/>

### 👥 Profilo Operatore
- **Modifica Profilo Operatore**
  <img src="src/main/resources/static/screenshot/form_modifica_profiloOperatore.png" alt="Modifica Profilo Operatore" width="800"/>
- **Visualizzazione Profilo Operatore**
  <img src="src/main/resources/static/screenshot/profiloOperatore_dashboardOperatore.png" alt="Profilo Operatore - Dashboard Operatore" width="800"/>

---

## 📝 Gestione delle Note (Admin e Operatore)

- **Lista Note Singolo Ticket**
  <img src="src/main/resources/static/screenshot/listaNote_singoloTicket.png" alt="Lista Note Singolo Ticket" width="800"/>

### Admin:
- **Form Aggiungi Nota (Admin)**
  <img src="src/main/resources/static/screenshot/form_aggiungiNota_dashboardAdmin.png" alt="Form Aggiungi Nota - Dashboard Admin" width="800"/>
- **Modifica Nota (Admin)**
  <img src="src/main/resources/static/screenshot/modale_modifica_nota.png" alt="Modifica Nota (Admin)" width="800"/>

### Operatore:
- **Form Aggiunta Nota (Operatore)**
  <img src="src/main/resources/static/screenshot/form_aggiuntaNota_dashboardOperatore.png" alt="Form Aggiunta Nota - Dashboard Operatore" width="800"/>
- **Modifica Nota (Operatore)**
  <img src="src/main/resources/static/screenshot/modale_modifica_nota.png" alt="Modifica Nota (Operatore)" width="800"/>

---

## 🔐 Pagina di Login
<img src="src/main/resources/static/screenshot/paginaLogin_standard.png" alt="Pagina di Login" width="800"/>
