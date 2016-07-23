package com.rdas.json

/**
 * Created by rdas on 18/07/2016.
 */
class JsonParser {

    Map<String, String> keyValues = new TreeMap<>()


    public getKeyValues() {
        return keyValues
    }

    public def parse(String jsonString) {
        def slurpedJson = new groovy.json.JsonSlurper().parseText(jsonString)
        return slurpedJson
    }

    def getDataFromArray(List jsonArray) {
        for (int i = 0; i < jsonArray.size(); i++) {
            def arrayItemAtPosition = jsonArray.get(i)
//            isJsonRootAMap(arrayItemAtPosition)
            if (isJsonRootAMap(arrayItemAtPosition)) {
                arrayItemAtPosition.each {
                    entry ->
                        def value = entry.value
                        if (isPlainData(value)) {
//                            println "map key ${entry.key} is plain "
                            keyValues.put(entry.key, value)
                        } else {
//                            println "map key ${entry.key} is NOT plain "
                            getDataFromMap(value)
                        }
                        // get string data if isPlainData
                }
            }
        }
    }

    def getDataFromMap(Map keyVals) {
        keyVals.each { entry ->
            def key = entry.key
            def value = entry.value
//            println " \t\t\t Found $value for $key"
            if (isPlainData(value)) {
                keyValues.put(entry.key, value)
            } else {
//                println "\t\tmap key ${entry.key} DOES NOT have plain values."
//                println "1. Map " + isJsonRootAMap(value)
//                println "2. array " + isJsonRootAnArray(value)
                if (isJsonRootAMap(value)) {
                    getDataFromMap(value)
                }
                if (isJsonRootAnArray(value)) {
                    getDataFromArray(value)
                }
            }

        }
    }

    def isPlainData(node) {
        (node instanceof String) || (node instanceof Integer)
    }

    def isJsonRootAnArray(slurpedJson) {
        def a = slurpedJson instanceof List
        return a
    }

    def isJsonRootAMap(slurpedJson) {
        def m = slurpedJson instanceof Map
        return m
    }

    def getValueWithin(String key) {
        return keyValues.get(key)
    }
}
