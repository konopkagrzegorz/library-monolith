# L03: Zaawansowane techniki programowania

Celem zadania będzie przygotowanie monolitycznej aplikacji udostępniającej prosty interfejs REST w oparciu Spring Boot i język Java.

## Przygotowanie

Przed zajęciami proszę o zapoznanie się z [załączonymi materiałami](docs/introduction.md) oraz o zainstalowanie niezbędnego oprogramowania, które będzie wykorzystywane na tych i kolejnych zajęciach.
W przypadku braku znajomości języka SQL polecam zapoznanie się z [dodatkowymi materiałami pomocniczymi](docs/sql_cheetsheet.md) wymaganymi do realizacji komunikacji z bazą danych.

## Wymagania dotyczące aplikacji

1. Dostęp do aplikacji nie powinien wymagać autoryzacji, a po uruchomieniu aplikacji wszystkie zasoby powinny być dostępne pod adresem [http://localhost:8080](http://localhost:8080)
1. Zasoby i funkcjonalność powinny być udostępniane przez REST API zgodne z dokumentacją zawartą w [docs/library-rest-service.yaml](docs/library-rest-service.yaml)
1. Aplikacja powinna wykorzystywać wbudowaną w aplikację bazę danych H2 do przechowywania stanu systemu

## Kryterium oceny rozwiązania

Za poprawne rozwiązania zadania można otrzymać 5 punktów.

1. Zgodność oraz poprawność implementacji funkcjonalności opisanej w ramach REST API
> Aplikacja powinna umożliwiać:
> * pobranie listy użytkowników, usuwanie oraz dodawanie nowych użytkowników,
> * pobranie listy dostępnych książek,
> * pobranie listy książek wypożyczonych przez wybranego użytkownika
> * wypożyczenie oraz zwrot książki przez użytkownika
>
> Proszę zwrócić szczególną uwagę na trzymanie się formatu danych (JSON) oraz obsługi błędów zdefiniowanych w ramach dokumentacji REST API.
> Dodatkowa funkcjonalność nie wymagana w ramach zadania nie będzie wypływała na ocenę końca przyznaną za zadanie.

2. Sprawozdanie z laboratorium
> Dołączenie sprawozdania do zrealizowanego zdania jest warunkiem koniecznym do uzyskania pozytywnej oceny z laboratorium. Zadania bez sprawozdania lub ze sprawozdaniem zawierającym znaczące braki merytoryczne nie będą podlegały ocenie.
>
> Sprawozdanie powinno zostać zamieszczone w katalogu [docs](docs) i dodane do repozytorium w oddzielnym commicie pod tytułem "Sprawozdanie".

3. Termin dostarczenia rozwiązania
> Bezpośrednio po zajęciach w repozytorium powinny znaleźć się wynik prac zakończonych w trakcie laboratorium.
>
> Ostatecznym terminem na wysłanie rozwiązania zadania wraz ze sprawozdaniem jest **14.11.2021 23:59** (decyduje data ostatniego commitu).
>
> Ocena za zadania dostarczone po tym terminie będzie automatycznie obniżana o 1 punkt, natomiast zadania nadesłane po **21.11.2021 23:59** nie będą podlegały ocenie.

## Zadania do wykonania w ramach laboratorium 