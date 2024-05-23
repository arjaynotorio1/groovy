# groovy
Groovy Exercise


## how to run?

1. First delete the sample.txt.bak as this is a result of my test.

2. in your shell, run:

```
groovy SearchReplace.groovy /path/to/directory "originalText" "newText" /path/to/logfile.txt
```
if you want with logging.

if without logging

run: 

```
groovy SearchReplace.groovy /path/to/directory "originalText" "newText"
```
***---------------------------------------------------***

You can replace the ***"OriginalText"*** of whatever text inside the sample.txt.

***Example***
    Arjay Notorio


then you want to replace it with,
    ARJAYNOTORIO

so the command will be for with logging:

```
groovy SearchReplace.groovy /path/to/directory "Arjay Notorio" "ARJAYNOTORIO" /path/to/logfile.txt
```

For without logging:
```
groovy SearchReplace.groovy /path/to/directory "Arjay Notorio" "ARJAYNOTORIO"
```