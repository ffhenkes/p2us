 --For CSV import/export. The CSV file must contain a defined separator in the xtractor.properties file(don't use pipe "|"), must have the first row with the desired JSON/MONGO attributes.

 --The second row and the last as well must contain the desired values for fill the attributes. Empty or dirty rows will return exceptions.


 --Import a csv file and export a JSONArray. 
 #java -jar xtractor.jar EXPORT_JSON CSV 
 
 --Import a csv file to mongo defined in xtractor.properties
 #java -jar xtractor.jar IMPORT_MONGO
 
 --Import multiple csv files to mongo as defined in xtractor.properties (uses path, prefix, and list. the list are the filenames to import, must use ; as separator).
 #java -jar xtractor.jar IMPORT_MONGO_MULTIPLE 
 
 --Show all collections from mongo connected db defined in xtractor.properties
 #java -jar xtractor.jar SHOW_COLLECTIONS  
 
 --Show the first one from mongo db connected and collection defined in xtractor.properties
 #java -jar xtractor.jar SHOW_FIRST  
 
 --Show all the docs from mongo db connected and collection defined in xtractor.properties
 #java -jar xtractor.jar LIST_ALL_DOCUMENTS 
 
 --Show a count(*) from mongo db connected and collection defined in xtractor.properties
 #java -jar xtractor.jar GET_COLLECTION_COUNT
 
 --For developers use. Create a static method in the XService and switch the implementation manually in the XMainStrategy Enum.
 #java -jar xtractor.jar QUERY idFind
 
  --UPDATE. Under development
 #java -jar xtractor.jar UPDATE
 
 --Drop configured collection
 #java -jar xtractor.jar DROP_COLLECTION