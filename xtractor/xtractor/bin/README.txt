 
 --Import a csv file and export a JSONArray.
 #java -jar xtractor.jar EXPORT_JSON /csvpath/geodebug.csv CSV 
 
 --Import a csv file to mongo defined in xtractor.properties
 #java -jar xtractor.jar IMPORT_MONGO /csvpath/geodebug.csv CSV 
 
 --Show all collections from mongo connected db defined in xtractor.properties
 #java -jar xtractor.jar SHOW_COLLECTIONS  
 
 --Show the first one from mongo db connected and collection defined in xtractor.properties
 #java -jar xtractor.jar SHOW_FIRST  
 
 --Show all the docs from mongo db connected and collection defined in xtractor.properties
 #java -jar xtractor.jar LIST_ALL_DOCUMENTS 
 
 --Show a count(*) from mongo db connected and collection defined in xtractor.properties
 #java -jar xtractor.jar GET_COLLECTION_COUNT
 
 --For developers use. Create a method in the XService and switch the implementation manually in the XMainStrategy enum.
 #java -jar xtractor.jar QUERY idFind
 
  --UPDATE. Under development
 #java -jar xtractor.jar UPDATE