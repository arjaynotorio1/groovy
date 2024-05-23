import groovy.io.FileType

class SearchReplace {
    // my main method
    static void main(String[] args) {
        if (args.length < 3) {
            println "Usage: groovy SearchReplace.groovy <directory> <searchText> <replaceText> [<logFile>]"
            System.exit(1)
        }

        String directoryPath = args[0]
        String searchText = args[1]
        String replaceText = args[2]
        String logFilePath = args.length >= 4 ? args[3] : null

        SearchReplace sr = new SearchReplace()
        sr.processFiles(directoryPath, searchText, replaceText, logFilePath)
    }

    void processFiles(String directoryPath, String searchText, String replaceText, String logFilePath) {
        File dir = new File(directoryPath)
        if (!dir.exists() || !dir.isDirectory()) {
            println "Invalid directory path: ${directoryPath}" //error handler
            System.exit(1)
        }

        def logEntries = []

        //used eachFileRecurse from groovy.io.FileType to transverse directories recursively
        dir.eachFileRecurse(FileType.FILES) { files ->
            def originalText = files.text
            if (originalText.contains(searchText)) {
                def backupFile = new File("${files.path}.bak") //set filesname of the backup just + .bak
                backupFile.text = originalText // creation of backup

                //used replaceAll method for string replacement, ensuring all occurences are replaced.
                def newText = originalText.replaceAll(searchText, replaceText)
                files.text = newText // Replace text in the original files
                
//implemented logging for logging start time, end time, modified filess, and occurrences of the search pattern.
                logEntries << "Modified: ${files.path}"
                logEntries << "Pattern found at: " + getOccurrences(originalText, searchText).join(", ")
            }
        }

        if (logFilePath) {
            File logFile = new File(logFilePath)
            logFile.text = "Log Start Time: ${new Date()}\n"
            logEntries.each { entry ->
                logFile.append("${entry}\n")
            }
            logFile.append("Log End Time: ${new Date()}\n")
        }
    }

    // helper method to find all occurrences of a pattern in a string
    List<Integer> getOccurrences(String text, String pattern) {
        List<Integer> occurrences = []
        int index = text.indexOf(pattern)
        while (index >= 0) {
            occurrences << index
            index = text.indexOf(pattern, index + 1)
        }
        return occurrences
    }
}
