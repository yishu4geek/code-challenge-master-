Class Structure
Except Customer, Image,Order,SiteVisit required models, 
Calculator model contains ingest and topXSimpleLTVCustomer methods and some helper methods. 
Dataset model contain collection of Customer,Order,SiteVist and Image objects.
test cases are under code-challenge-master/src/test
Sample input files are under code-challenge-master/input

Improvement
If the input file is very big cannot put the whole file into computer RAM. We
can split the file into small files and use external sort to get topXSimpleLTVCustomer 

