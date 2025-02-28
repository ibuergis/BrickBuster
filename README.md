# README

build gradle: 
````gradlew build````

run linter:
````gradlew ktlintCheck````

# Reflexion
Insgesamt bin ich mit diesem Projekt zufrieden. Obwohl ich zum ersten mal mit Kotlin arbeite, habe ich mich entschieden
ein kleines game mit framerates zu erstellen. Leider konnte ich nicht alles so zu machen, wie ich es wollte. Ich würde sagen
der grösste Grund war die Zeit. Für Levels und Fähigkeiten fehlt einem zu viel Zeit. Dieser üK hatte uns sowieso viel zu wenig
Zeit gegeben. Ich fand, es gäbe viel zu viel Code in GameLoop, hätte ich das alles dorthin reingetan, darum erstellte ich GameState.
Ich hätte es auch für Levels gebraucht. Die Views kamen hinzu, weil ich davor geplant hatte, alle UI Komponente mit dem Loop zu
rendern. Am Ende fand ich, dass es aber keinen Sinn gibt, es so zu machen. Wäre auch viel zu kompliziert gewesen. GameEntity macht Sinn,
weil es viel Code gibt, dass bei Videospielobjekte oft wiederholt wird. Letztendlich kann ich sagen, dass ich mir zu viel Vorgenommen habe.
Das nächste mal werde ich ein kleineres Projekt planen oder mir mehr Zeit geben.