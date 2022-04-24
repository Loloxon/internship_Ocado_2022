# internship_Ocado_2022
---
Złożoność programu: Parsowanie danych jest w czasie linowym, tak samo jak tworzenie struktur oraz wybieranie najlepszego produktu. Najbardziej czasochłonny jest algorym Dijktry, 
którego złożoność to O(V+ElogV), w naszym przypadku V=XY, E=4*XY zatem złożoność algorytmu to O(XYlog(XY)), a więc ostateczna złożoność dla n=XY to O(nlogn).

Kolejne kroki funkcjonowania programu:
1) dane wejściowe są wczytywane z obu podanych plików oraz parsowane (za pomocą GridParser oraz JobParser)
2) tworzone są odpowiednie struktury (Module, Grid, Product)
3) na stworzonych strukturach uruchamiany jest Solver który działa następująco:
	a) w pierwszej kolejności, z wykorzystaniem algorymu Dijkstry oblicza odległości (tzn czas przejazdu) zarówno od punktu początkowego jak i stacji odbiorczej do każdego z poszukiwanych produktów
	b) na podstawie wyliczonych odległości określa który produkt najlepiej wybrać (czas = odległość od stacji odbiorczej+odległość od punktu startowego+czas wyjęcia)
	c) generuje ścieżkę którą bot będzie się poruszał

Pomimo iż w treści zadania sa podane przykłady z pojedyńczymi zapytaniami o dostarczenia produktów, zdecydowałem się umożliwić wielokrotne zapytania 
(można tego dokonywać za pomocą ponownych wywołań funkcji solver.setData() oraz solver.calculate()).

Program został pokryty kilkoma testami integralnymi oraz weryfikacją poprawności danych wejściowych (w odpowadających miejscach).


---
### Zadanie otwarte:
**Dodaliśmy w naszym gridzie możliwość zamontowania wielu stacji odbiorczych. Nasz
bot może dowieźć produkty do dowolnej stacji odbiorczej. Jak zmodyfikować nasz
algorytm, tak aby bot wybrał stację, do której dojedzie najszybciej? Jak wpłynie to na
złożoność obliczeniową? (tylko analiza pisemna, maksymalnie 2000 znaków)**

Jako że mamy do znalezienia najkrótsze połączenia między dowolną parą stacja-produkt, to narzucać się może algorytm Floyda-Warshalla (O(V^3) <=> O(n^3)), jednakże należy zauważyć, że
wywoływanie Dijkstry dla każdej ze stacji odbiorczych (których potencjalnie jest 'n') zajmuje O(n*nlogn), zatem jest to bardziej efektywna możliwość. 
Skoro określono już który algorytm należy zastosować, wprowadzenie modyfikacji jest stosunkowo proste. Solver powiniem wywołać Dijkstre dla każdej stacji odbiorczej (w poszukiwaniu
najbliższego produktu), a dla każdego produktu porównać wwszystkie "odległości" czasowe i wybrać najlepszy produkt (dla każdego produktu jest 'n' możliwych czasów, zatem złożoność
tej części wynosi O(n^2) co nie pogarsza złożoności algorytmu). Reszta algorytmu tzn parsowanie, generowanie struktur oraz generowanie ścieżki nie wymaga znaczących modyfikacji.
