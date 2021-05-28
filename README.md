# Das große PiS-FAQ

## Generelles

### Struktur von Projekten
Grundsätzlich ist es wichtig den Source-Code direkt zu Beginn eines Projekts richtig zu organisieren. Bei den aktuellen Projekten mag dies momentan "unnötig" erscheinen, gerade bei großen Projekten ist eine gute Struktur aber unabdingbar, da ihr ansonsten schnell den Überblick verlieren werdet.

Auch ist es wichtig, die einzelnen Projekte voneinander zu trennen. So ist es z.B. keine gute Struktur, wenn ihr ein Projekt aufsetzt, in welchem ihr den Code von mehreren Projekten ablegt. Legt lieber für jedes einzelne Projekt ein neues Projekt mit der IDE eurer Wahl an.

### Anwendungskern und Interaktionsklasse - Wofür?

#### Was gehört zum Anwendungskern?
Der Anwendungskern besteht grundsätzlich aus allen Methoden und Variablen, welche ein Spiel für den Logikteil benötigt. Bei dem Spiel Tick-Tack-Toe wären dies unter anderem:
* board: Variable, welche das aktuelle Spielfeld hält.
* move(...): Methode, welche einen Zug durchführt.
* isGameOver(...): Methode, welche prüft, ob das Spiel vorbei ist oder ob noch Züge gemacht werden dürfen.

> Bei den hier angegebenen Beispielen handelt es sich nicht um konkrete Java-Deklarationen und Implementierungen, sondern lediglich um beispielhafte Nennungen von Dingen, die in einem Anwendungskern enthalten sein sollten.

Wichtig ist außerdem, dass der Anwendungskern keinerlei Ausgaben enthält und auch nicht in irgendeiner Form das Aussehen der späteren _Anwendung bestimmt.

#### Was gehört zur Interaktionsklasse?
Zur Interaktionsklasse gehört alles, was mit der Darstellung des Spiels zu tun hat. Dabei darf und soll die Interaktionsklasse auf den Anwendungskern zurückgreifen können. Eine Interaktionsklasse kann unter anderem die folgenden Methoden haben:
* draw(...): Zeichnen einer Darstellung
* mouseClicked(...): Methode, welche aufgerufen wird, sobald eine Maustaste gedrückt wurde.
* askForInput(): Eine Methode, die bei einer Konsolenumsetzung nach einer Eingabe fragt.
* printBoard(...): Eine Methode, welche bei einer Konsolenumsetzung das Board und evtl. andere wichtige Informationen ausgibt. Hier kann beispielsweise eine toString()-Methode des Anwendungskerns verwendet werden.

#### Wozu die Trennung?
Ganz simpel gesagt, gibt uns die genannte Trennung zwei große Vorteile:
1. Der Code ist übersichtlicher und besser strukturiert.
2. Wir können den Anwendungskern für zwei verschiedene Darstellungen verwenden. Beispielsweise ist es möglich eine CLI und eine GUI mit dem gleichen Kern zu schreiben.

## Java

### Was sind "sprechende" Variablennamen und wieso sollte ich sie verwenden?
Variablennamen werden als "sprechend" bezeichnet, wenn durch den Namen die Verwendung oder auch der Inhalt klar wird. Das gilt dabei nicht nur für Variablennamen, sondern für Code generell. Also auch bei Klassen, Methoden, etc. sollte man darauf achten gute Namen zu wählen.

#### Ein Beispiel
```java
int add(int x, int y) { // doSomething wäre hier ein schlechter Name
    return x + y; //x und y sind einfach zwei Komponenten einer Gleichung, daher gibt es hier keinen besseren Namen
}

int result = add(5, 6); // result speichert das Ergebnis einer Operation;
```

### Switch-Case statt if-else
Nehmen wir an, wir hätten folgenden Code vor uns:

```java
int value = 7;
if(value == 0) System.out.println("Value is 0.");
else if(value == 1) System.out.println("Value is 1.");
...
else if(value == 7) System.out.println("Value is 7.");
```

Dieser Code ist gerade mit vielen Werten sehr unübersichtlich und erfordert jede Menge Tipparbeit. Stattdessen könnt ihr ein Switch-Case-Statement verwenden. Dieses sieht, bezogen auf das obige Beispiel, so aus:

```java
int value = 7;
switch(value) {
    case 0: System.out.println("Value is 0."); break;
    case 1:
        System.out.println("Value is 1.");
        break;
    ...
    case 7:
        System.out.println("Value is 7.");
        break;
    default:
        System.out.println("Unknown value.");
        break;
}
```

Der Teil `switch(value)` spezifiziert dabei, dass ihr im folgenden Statements Möglichkeiten zum Umgang mit verschiedenen Werten von der Variable `value` zur Verfügung stellen wollt. Mit `case <wert>: ...` könnt ihr einzelne Werte angeben, für welche eine Aktion ausgeführt werden soll.

`default` beschreibt den Fall, dass keiner der Cases eingetreten ist. Im Beispiel muss der Wert von `value` dafür einfach < 0 oder > 7 sein.

### Google Style Guide
Für die Formatierung von Quellcode gibt es verschiedenste Regelwerke. Relevant für die Veranstaltung Programmierung interaktiver Systeme ist der [Google-Style-Guide](https://google.github.io/styleguide/javaguide.html). Dieser definiert die Regeln, welchen eure Code-Formatierung folgen sollte.
Mit Einhaltung der Konventionen sorgt ihr dafür, dass auch andere Personen euren Code gut lesen können.

## Processing

### Zeichnen in mehreren Klassen
In Processing ist es möglich die Methode `draw` auf mehrere Klassen aufzuteilen, wenn ihr beispielsweise mehrere Objekte habt, welche ihr zeichnen wollt. Um dies zu ermöglichen, sollte aber nur eure Hauptklasse von `PApplet` erben. Hier könnt iher wie gehabt den folgenden Code ausführen:

```java
public void draw() {
    // Do something
}
```

Wenn ihr nun eine zweite Klasse habt, beispielsweise `Ball`, so importiert ihr in dieser Klasse `processing.core.PGraphics`. Die Draw-Methode ruft ihr dann wie folgt auf:

```java
public void draw(PGraphics g) {
    // Draw something
}
```

#### Was ist dieses `PGraphics`?
Bei `PGraphics` handelt es sich um das Objekt, auf welchem ihr zeichnet. In eurer Hauptklasse wird dies ohne weiteren Zusatz von euch getan, da ihr von `PApplet`erbt. In allen anderen "Subklassen" müsst ihr den Methodenkopf wie oben definiert schreiben. Dabei müsst ihr im Funktionskörper alle Methoden zum Zeichnen auf `g` anwenden. Hier ein kurzes Beispiel:

```java
public void draw(PGraphics g) {
    g.ellipse(100.0f, 100.0f, 100.0f, 100.0f);
    // ...
}
```

Aufgerufen wird diese `draw`-Methode von eurer Hauptklasse dann wie folgt:

```java
public void draw() {
    <instanceVariableName>.draw(super.g);
}
```

> `<instanceVariableName>` ist dabei durch den Namen einer Instanzvariable der anderen Klasse zu ersetzen, welche die gewünschte `draw`-Methode enthält.

#### Ein längeres und vollständiges Beispiel
Um den Ablauf von mehreren `draw `-Methoden zu verdeutlichen, findet ihr [hier](Blockbreaker/) ein vollständiges Beispiel, welches von mir im Rahmen der Projektwoche im Wintersemester 2019/2020 entstanden ist. Dieses ist vollständig lauffähig.

#### Alternative: toDraw()-Methode für Objekte
Ihr könnt auch, um Code sauber zu strukturieren, einem Objekt eine toDraw()-Methode hinzufügen, damit es sich selbst auf einer Zeichenfläche repräsentieren kann. Um den Code sauber zu strukturieren, bietet es sich an, diese Methode in eine Klasse auszulagern, die von dem darzustellenden Objekt erbt. Wie das genau aussieht, könnt ihr [hier](MultipleDraw/) nachschauen. Dabei handelt es sich um ein kleines Beispielprogramm, welches ihr mittels `gradle run` ausführen könnt.

### Tests mittels Gradle durchführen
Bei der Erstellung eines Gradle-Projekts könnt ihr Unit-Tests direkt einbinden und später ganz leicht ausführen. Dazu müsst ihr später nur `gradle test` ausführen. Wie ihr ein Projekt aufsetzt, welches die Tests unterstützt, seht ihr [in diesem Video](https://youtu.be/oXNCF9PbYug?t=319). Die Wiedergabe beginnt dabei ab dem Zeitpunkt der Projekterstellung. Solltet ihr Gradle noch nicht installiert haben, so solltet ihr dies natürlich vorher erledigen.

Nach der Erstellung eures Projekts, findet ihr im Ordner `app/src/test/java` eine Datei, welche verschiedene Methoden enthält, die unterschiedliche Funktionalität testen. Wie solche Tests genau geschrieben werden, seht ihr beispielsweise im Projekt [MultipleDraw](MultipleDraw).

Wie bereits erwähnt, könnt ihr die Tests, nach dem implementieren, mittels `gradle test` starten und bekommt eine Übersicht, wenn ein Test fehlgeschlagen ist.