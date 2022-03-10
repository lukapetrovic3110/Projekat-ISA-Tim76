# Projekat Internet Softverske Arhitekture 2021/2022 -ISA-Tim76
## Dobro došli!
FTN Novi Sad - Primenjene računarske nauke i informatika 
- Članovi tima:

|       Student 1       |       Student 2     |
|:----------------------:|:----------------------:|
| [Luka Petrović](https://github.com/lukapetrovic3110) <br> <img src="https://avatars.githubusercontent.com/u/58331163?s=400&u=07ea033ef6ec9a611746bac3e8fb257f0099e261&v=4" width="240" height="240"> | [Goran Lojanica](https://github.com/goranlojanica1) <br> <img src="https://avatars.githubusercontent.com/u/58367748?v=4" width="240" height="240"> |

## Tehnologije korišćene na projektu
- Spring Boot
- Vue.js
- Node.js
- PostgreSQL

## Git pravila

- Naziv commit-a:

    [feat/refactor] + ": " + opis
    
- Naziv grane:

    [feat/refactor] + "/" + opis
    
## Baza - PostgreSQL
- Server baze podataka radi na podrazumevanom portu `5432`
- Nakon pokretanja pgAdmin-a, potrebno je kreirati bazu podataka pod nazivom `internetsoftwarearchitecture-db` 
- Korisničko ime : `postgres`, lozinka : `root`
- Postoji data-postgres.sql skripta u direktorijumu resursa koja se automatski pokreće pri pokretanju Spring Boot projekat ( back-end )
- Namešteno u podešavanju Spring Boot projekat ( application.properties - `spring.jpa.hibernate.ddl-auto = create-drop` )

## Koraci prilikom pokretanja back-end dela projekta u Eclipse razvojnom okruženju
- Uvezite `InternetSoftwareArchitecture` projekat u radni prostor ( workspace: Import -> Maven -> Existing Maven Project )
- Zatim instalirajte sve zavisnosti iz datoteke pom.xml
- Na kraju, kliknite desnim tasterom miša na projekat -> Run as -> Java Application / Spring Boot app ( ako je instaliran STS dodatak sa Eclipse marketplace-a )
- Back-end projekat će biti pokrenut na portu `8091`

## Koraci prilikom pokretanja front-end dela projekta u okruženju Visual Studio Code
- Morate instalirati node.js
- Otvorite svoj Visual Studio Code i uvezite `internetsoftwarearchitecturefrontend` projekat
- Otvorite terminal u Visual Studio Code-u, a zatim otkucajte sledeće komande:
```sh
                                $ npm install
                                $ npm run serve
```
- Web aplikacija će biti pokrenut na portu 8083
- Ako Vam je port zauzet, pratite uputstva u nastavku:
- Pokrenite Cmd.exe ( Komandu liniju - Command Prompt ) kao administrator
- Zatim otkucajte sledeće komande:
```sh
                                $ netstat -ano | findstr :8083
```
- Unesite broj na kojem port "sluša" umesto `<PID>`
```sh
                                $ taskkill /PID <PID> /F
```
## Klasni diagram u početnoj fazi razvoja projekta
- link : https://drive.google.com/file/d/10I9a75-qnUK2D0VeekkDGR_-jmltM9Vq/view?usp=sharing

    
