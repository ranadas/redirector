import com.eclipsesource.json.Json
import com.eclipsesource.json.JsonArray
import com.rdas.json.JsonParser
import spock.lang.Specification

/**
 * Created by rdas on 16/07/2016.
 */
class JsonParserTest extends Specification {

    def jsonString = '''
[
{
    "createTime": "2014-05-12 04:51:46.513343",
    "powered": false,
    "description": "s",
    "current": false,
    "children": [
        {
            "createTime": "2014-05-13 03:50:43.050442",
            "powered": false,
            "description": "Snapshot description",
            "current": false,
            "children": [
                {
                    "createTime": "2014-05-13 03:57:08.209319",
                    "powered": false,
                    "description": "s",
                    "current": false,
                    "children": [
                        {
                            "createTime": "2014-05-13 04:27:00.646064",
                            "powered": false,
                            "description": "s",
                            "current": false,
                            "label": "snap3"
                        },
                        {
                            "createTime": "2014-05-13 21:00:16.374178",
                            "powered": false,
                            "description": "sd",
                            "current": false,
                            "label": "sddsds"
                        }
                    ],
                    "label": "snap2"
                }
            ],
            "label": "snapshot-name5"
        },
        {
            "createTime": "2014-05-14 00:49:33.415858",
            "powered": false,
            "description": "a",
            "current": false,
            "children": [
                {
                    "createTime": "2014-05-14 02:35:10.076829",
                    "powered": false,
                    "description": "sdfsdfsdf",
                    "current": true,
                    "label": "ssfsdf"
                }
            ],
            "label": "assa"
        }
    ],
    "label": "snap1"
}
]
    '''

    def jsonToParse = '''
[
  {
    "_id":"4dd90e0f-31d1-4976-9e95-2f619ebb81f2",
    "workspaceId":"4dd90e0f-31d1-4976-9e95-2f619ebb81f2",
    "dob":"1981-05-02",
    "taxYear":2015,
    "taxIdentificationNumber":"102231608",
    "version":"6555123d-9043-4136-9b2b-53066f5c79cb",
    "filings":{
      "c99f1ecf-495b-4e65-b220-57e5eb7968fc":{
        "officeId":null,
        "returnType":"1040",
        "filingId":"c99f1ecf-495b-4e65-b220-57e5eb7968fc",
        "creationDate":1447997601924,
        "filingEntities":{
          "IRS":{
            "agency":"US",
            "guid":"GUID000102231608",
            "creationDate":1447997601924,
            "method":"ELECTRONIC",
            "filingStatus":"PENDING_TRANSMISSION",
            "amendment":false,
            "extension":false,
            "resources":[
              {
                "name":"test1.pdf",
                "uriPath":"/4dd90e0f-31d1-4976-9e95-2f619ebb81f2/r/5d5713aa-d917-49e2-a894-8eb470fe7733/e/US/f/test1.pdf?rev=5201e2e5e8a855900ec5592f0688a6e4ccf2ce05",
                "contentType":"application/octet-stream",
                "metadata":{
                  "x-hrb-taxdoc-type":"pdf",
                  "x-hrb-office-id":"1009",
                  "x-hrb-taxpro-id":"16589",
                  "taxdoc-type":"pdf",
                  "x-hrb-guid":"GUID000102231608",
                  "x-hrb-ptin":"12345",
                  "tax-entity":"US",
                  "x-hrb-content-version":"1"
                }
              }
            ],
            "efaReceivedDate":null,
            "irsReceivedDate":null,
            "irsStatusDate":null,
            "efaUniqueKey":0
          }
        },
        "filingResources":[

        ]
      }
    }
  }
  ]
    '''

    def "parse a string for jso keys & values"() {
        given:
        def parser = new JsonParser()
        def slurpedJson = parser.parse(jsonToParse)

        when:
        1 == 1//println slurpedJson

        then:
        parser.isJsonRootAnArray(slurpedJson) == true

        when:
        def data = parser.getDataFromArray(slurpedJson)

        then:
        //println parser.getKeyValues()

        println parser.getValueWithin("_id")

    }


    def jx(String str) {
        JsonArray jsonArray = Json.parse(str);//JsonArray.readFrom(str);
//        println jsonArray

        for (int i = 0; i < jsonArray.size(); i++) {
            //for (JsonObject jsonObject : jsonArray) {
//            JsonValue jv = jsonObject.get(jsonObject)
//            println "\n here $jsonObject and "
//            JsonValue value = jsonObject.get( name );

            def get = jsonArray.get(i)
            if (get in String) {
                println jsonArray.get(i).asString()
            } else {
                println "Not String"
            }
        }

        println "rana"
    }

    def recursiveChildrenScan(map, key, value) {
        if (!map) {
            null
        } else if (map[key] == value) {
            map.children
        } else {
            map.children.findResult { recursiveChildrenScan(it, key, value) }
        }
    }
}
